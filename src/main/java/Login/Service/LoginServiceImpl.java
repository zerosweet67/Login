package Login.Service;


import java.security.SecureRandom;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import Login.Bean.User;
import Login.Dao.UserDao;
import Login.Enum.LoginStatus;
import Util.MailSender;
import Util.PasswordManager;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserDao dao;

	// 登入

	public LoginStatus isValidUser(String username, String userpwd) {

		Optional<User> userOpt = dao.findUserByUsername(username);

		if (userOpt.isPresent()) {
			System.out.println("找到使用者 : " + userOpt);
			User user = userOpt.get();
			String pwd = PasswordManager.encryptPassword(userpwd);
			if (user.getUserpwd().equals(pwd)) {
				if (user.getVerifystate() == 1) {

					return LoginStatus.EXIST_LEVEL_1;
				}
				return LoginStatus.EXIST_LEVEL_2;

			} else {
				return LoginStatus.WRONG_PASSWORD;

			}

		} else {
			System.out.println("找不到到使用者 : " + userOpt);
			return LoginStatus.NON_EXIST_USER;
		}

	}

	public boolean checkUsername(String username) {

		Optional<User> existingUser = dao.findUserByUsername(username);

		if (existingUser.isPresent()) {
			return true;
		}
		return false;
	}

	public boolean checkUseremail(String email) {
		Optional<User> userexist = dao.findUserByEmail(email);
		System.out.println("使用者輸入的信箱"+email);
		if (userexist.isPresent()) {
			return true;
		}
		return false;
	}

	// 註冊資料是否完整
	public boolean isSigninDataPass(@Valid User user, BindingResult result, Model model) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("errors", result.getAllErrors());
			System.out.println("註冊資料有誤 :" + user);
			return false;
		}
		Optional<User> existingUser = dao.findUserByUsername(user.getUsername());
		Optional<User> userexist = dao.findUserByEmail(user.getEmail());
		if (existingUser.isPresent() || userexist.isPresent()) {
			model.addAttribute("user", user);

			return false;

		}

		String pwd = PasswordManager.encryptPassword(user.getUserpwd());
		user.setUserpwd(pwd);
		dao.addUser(user);

		System.out.println("註冊資料成功(service)" + user);
		return true;
	}

	// 檢查信箱是否正確
	@Override
	public LoginStatus isEmailValid(String email) {
		Optional<User> userOpt = dao.findUserByEmail(email);
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			if (email.equals(user.getEmail())) {
				return LoginStatus.Success;
			}
		}
		return LoginStatus.WRONG_Email;

	}

	// 發送OTP驗證碼
	public String sendOTP(String username) {
		User user = dao.findUserByUsername(username).get();
		// 產生驗證碼並送出
		SecureRandom secureRandom = new SecureRandom();
		int number = secureRandom.nextInt(1000000);
		String OTPcode = String.format("%06d", number);
		MailSender mail = new MailSender("zerosweet67@gmail.com", "ddfgebqbgcmlztbl");
		mail.from("zerosweet67@gmail.com").to(user.getEmail()).personal("註冊系統").subject("驗證碼信件")
				.context("您好，您的驗證碼為【 " + OTPcode + " 】登入後請盡速驗證，謝謝您!").send();
		return OTPcode;
	}

	// 利用OTP登入會員
	public boolean isOTPValidUser(String validcode, String OTPcode, String email) {
		if (validcode.equals(OTPcode)) {
			dao.updateUsersVerifystate(email);
			return true;
		}
		return false;
	}

	// 發送OTP驗證碼
	public String sendpwd(String username) {
		User user = dao.findUserByUsername(username).get();
		// 產生驗證碼並更改為新密碼
		SecureRandom secureRandom = new SecureRandom();
		int number = secureRandom.nextInt(1000000);
		String OTPcode = String.format("%06d", number);
		String newpwd = PasswordManager.encryptPassword(OTPcode);
		int rowcount = dao.updatePasswordByEmail(user.getEmail(), newpwd);
		System.err.println("新密碼" + rowcount);
		MailSender mail = new MailSender("zerosweet67@gmail.com", "ddfgebqbgcmlztbl");
		mail.from("zerosweet67@gmail.com").to(user.getEmail()).personal("系統").subject("更新密碼信件")
				.context("您好，您的新密碼為【 " + OTPcode + " 】，請重新登入").send();
		return OTPcode;
	}

	// 忘記密碼用
	public LoginStatus findUser(String username) {

		Optional<User> userOpt = dao.findUserByUsername(username);

		if (userOpt.isPresent()) {
			System.out.println("找到使用者 : " + userOpt);
			return LoginStatus.Success;

		} else {
			System.out.println("找不到到使用者 : " + userOpt);
			return LoginStatus.NON_EXIST_USER;
		}

	}

}
