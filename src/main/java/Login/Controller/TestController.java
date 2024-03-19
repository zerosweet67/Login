package Login.Controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Login.Bean.User;
import Login.Dao.UserDaoImpl;
import Login.Service.LoginServiceImpl;

public class TestController {

	@Controller
	@RequestMapping("/test")
	public class LoginController {

		@Autowired
		UserDaoImpl dao;

		@Autowired
		LoginServiceImpl loginService;

		// 登入首頁
		@GetMapping("/login")
		public String loginPage() {

			return "login";
		}

		// 登入
		@ResponseBody
		@PostMapping("/login")
		public String login(@RequestParam("username") String username, @RequestParam("userpwd") String userpwd,
				HttpSession session, Model model) {
			List<User> alluser = dao.findAllUsers();
			
			return alluser.toString();

		}

	}
}
