package Login.Controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Login.Bean.User;
import Login.Dao.UserDao;


@Controller
@RequestMapping("/main")
public class MainPageController {

	@Autowired
	UserDao userDao;



	@GetMapping
	public String mainPage(Model model, HttpSession session) {
		User user = (User)session.getAttribute("user");
		model.addAttribute("user",user);
		

		return "mainpage";
	}
}
