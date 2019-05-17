package pl.coderslab.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


import pl.coderslab.misc.SessionManager;
import pl.coderslab.entity.Flat;
import pl.coderslab.entity.User;
import pl.coderslab.repository.FlatRepository;

@Controller
public class HomeController {

	private final FlatRepository flatRepository;

	public HomeController(FlatRepository flatRepository) {
		this.flatRepository = flatRepository;
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("flat", new Flat());
		return "home";
	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}

	@GetMapping("userFlats/{userId}")
	public String userTweets(@PathVariable Long userId, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null && user.getId() == userId) {
			return "redirect:/user";
		}
		List<Flat> userFlats = this.flatRepository.findByUserIdOrderByCreatedDesc(userId);
		Flat flat = this.flatRepository.findFirstByUserId(userId);
		
		model.addAttribute("flat",flat);
		model.addAttribute("userFlats", userFlats);
		return "userId";
	}
	
	@ModelAttribute("availableFlats")
	public List<Flat> getAllFlats() {
		return this.flatRepository.findAllOrder();
	}
}
