package Login.Controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Login.Bean.User;
import Login.Dao.UserDao;
import Login.Dao.UserDaoImpl;
import Login.Enum.LoginStatus;
import Login.Service.LoginService;
import Login.Service.LoginServiceImpl;

@Controller
@RequestMapping("/mvc")
public class LoginController {

	@Autowired
	UserDao dao;

	@Autowired
	LoginServiceImpl loginService;

	// 登入首頁
	@GetMapping("/login")
	public String loginPage() {

		return "login";
	}
	
	// 登出
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/app/mvc/login";

	}

	// @ResponseBody
	@PostMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("userpwd") String userpwd,
			HttpSession session, Model model) {
		LoginStatus loginStatus = loginService.isValidUser(username, userpwd);
		if (loginStatus == LoginStatus.EXIST_LEVEL_2) {
			User user = dao.findUserByUsername(username).get();
			session.setAttribute("user", user);
			return "redirect:/app/main";
		} else if (loginStatus == LoginStatus.EXIST_LEVEL_1) {
			User user = dao.findUserByUsername(username).get();
			session.setAttribute("OTPcode", loginService.sendOTP(username));
			session.setAttribute("username", username);
			session.setAttribute("email", user.getEmail());
			session.setAttribute("user", user);
			return "redirect:/app/mvc/verifypage";

		} else if (loginStatus == LoginStatus.WRONG_PASSWORD) {

			model.addAttribute("loginMessage", "密碼錯誤,請重新輸入");
		} else if (loginStatus == LoginStatus.NON_EXIST_USER) {
			model.addAttribute("loginMessage", "無此使用者,請註冊帳號");
		}
		session.invalidate();
		return "/login";
	}

	// 註冊頁面
	@GetMapping("/signin")
	public String Signin() {
		return "Signin";
	}

	@GetMapping("/checkUsername")
	@ResponseBody
	public String checkUsername(@RequestParam(value = "username", required = false) String username) {

		if (username != null) {
			if (loginService.checkUsername(username)) {
				System.out.println("帳號註冊過 controller");
				return "exists";
			} else {
				System.out.println("帳號可使用 controller");
				return "available";
			}
		}
		return "error";
	}

	@GetMapping("/checkUseremail")
	@ResponseBody
	public String checkUseremail(@RequestParam(value = "email", required = false) String email) {

		if (email != null) {
			if (loginService.checkUseremail(email)) {
				System.out.println("信箱註冊過 controller");
				return "exists";
			} else {
				System.out.println("信箱可使用 controller");
				return "available";
			}
		}
		return "error";
	}

	// 註冊帳號
	// @ResponseBody
	@PostMapping("/signin")
	public String SigninPage(@Valid User user, BindingResult result, Model model, HttpSession session)
			throws Exception {
		if (loginService.isSigninDataPass(user, result, model)) {
			session.setAttribute("user", user);
			System.out.println("註冊成功" + user);
			return "redirect:/app/mvc/login";
		}

		System.out.println("註冊失敗");
		return "Signin";
	}

	// 跳轉到信箱驗證畫面
	// @ResponseBody
	@GetMapping("/verifypage")
	public String getverifypage(Model model, HttpSession session) {
		String OTPcode = (String) session.getAttribute("OTPcode");
		String email = (String) session.getAttribute("email");
		String username = (String) session.getAttribute("username");
		model.addAttribute("email", email);
		model.addAttribute("OTPcode", OTPcode);
		model.addAttribute("username", username);

		return "verifyState";
	}

	// 信箱驗證介面
	// @ResponseBody
	@PostMapping("/verify")
	public String verifypage(Model model, HttpSession session, @RequestParam("verify") String verify,
			@RequestParam("OTPcode") String OTPcode, @RequestParam("email") String email) {
		User user = (User) session.getAttribute("user");
		System.out.println("驗證信箱" + user.getEmail());
		if (loginService.isOTPValidUser(verify, OTPcode, email)) {
			session.setAttribute("user", user); 
			System.out.println("信箱驗證成功" + user);
			return "redirect:/app/main"; 
		} else if(verify == null) {
			model.addAttribute("verifyvalue","請輸入驗證碼");
			return "redirect:/app/mvc/verifypage";
		}
		return "login";
	}

	// 導入忘記密碼介面
	@GetMapping("/forgetpwdpage")
	public String getforgetpwdpage(Model model, HttpSession session) {
		return "forgetpwd";

	}

	// 忘記密碼確認
	@PostMapping("/forgetpwd")
	public String forgetpwdpage(Model model, HttpSession session, @RequestParam("username") String username) {
		LoginStatus loginStatus = loginService.findUser(username);
		if (loginStatus == LoginStatus.Success) {
			loginService.sendpwd(username);
			session.setAttribute("username", username);
			return "redirect:/app/mvc/login";
		} else if (loginStatus == LoginStatus.NON_EXIST_USER) {
			model.addAttribute("loginMessage", "無此使用者,重新確認");
		}
		session.invalidate();
		return "/forgetpwd";

	}

}
