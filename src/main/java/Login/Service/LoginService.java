package Login.Service;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import Login.Bean.User;
import Login.Enum.LoginStatus;

public interface LoginService {

	// 登入

	public LoginStatus isValidUser(String username, String userpwd);

	// 新增使用者
	boolean isSigninDataPass(@Valid User user, BindingResult result, Model model) throws Exception;

	// 檢查信箱
	LoginStatus isEmailValid(String email);
	
	public boolean isOTPValidUser(String validcode, String OTPcode, String email);

	public String sendOTP(String username);
	
	public boolean checkUsername(String username);
	public boolean checkUseremail(String email);

}
