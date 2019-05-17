package pl.coderslab.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import pl.coderslab.misc.SessionManager;
import pl.coderslab.entity.Flat;
import pl.coderslab.entity.User;
import pl.coderslab.repository.FlatRepository;

@Controller
public class UserViewController {
	
	private final FlatRepository flatRepository;

	public UserViewController(FlatRepository flatRepository) {
		this.flatRepository = flatRepository;
	}

	@GetMapping("/user")
	public String user(Model model) {
		HttpSession session = SessionManager.session();
		User user = (User) session.getAttribute("user");
		model.addAttribute("flat", new Flat());
		model.addAttribute("user", user);
		return "user";
	}
		
	@ModelAttribute("userFlats")
	public List<Flat> getUserFlats() {
		HttpSession session = SessionManager.session();
		User user = (User) session.getAttribute("user");
		return this.flatRepository.findByUserId(user.getId());
	}
}
