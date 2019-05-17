package pl.coderslab.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.entity.Message;
import pl.coderslab.entity.User;
import pl.coderslab.repository.MessageRepository;
import pl.coderslab.repository.UserRepository;

@Controller
@RequestMapping("/message")
public class MessageController {

	private final MessageRepository messageRepository;
	private final UserRepository userRepository;

	public MessageController(MessageRepository messageRepository, UserRepository userRepository) {
		this.messageRepository = messageRepository;
		this.userRepository = userRepository;
	}

	@GetMapping("")
	public String messageList(Model model) {
		model.addAttribute("message", new Message());
		return "mess/message_list";
	}

	
	@GetMapping("/{id}")
	public String message(@PathVariable Long id, Model model, HttpSession session) {
		if (session != null) {
			User sender = (User) session.getAttribute("user");
			if (sender != null && sender.getId() == id) {
				return "redirect:/message";
			}
		}
		model.addAttribute("message", new Message());
		model.addAttribute("receiverId", id);
		return "mess/send_message";
	}
	
	@PostMapping("/add/{receiverId}")
	public String addPost(@Valid @ModelAttribute Message message, @PathVariable Long receiverId,
			BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "redirect:/message/" + receiverId;
		}
		User sender = (User) session.getAttribute("user");
		message.setSender(sender);
		User receiver = this.userRepository.findOne(receiverId);
		message.setReceiver(receiver);
		message.setCreated(new Date());
		this.messageRepository.save(message);
		return "redirect:/message";
	}
	
	@GetMapping("/details/{id}")
	public String messageDetails(@PathVariable Long id, Model model) {
		Message message = this.messageRepository.findOne(id);
		message.setChecked(1);
		this.messageRepository.save(message);
		model.addAttribute("message", message);
		return "mess/read_message";
	}
	
	@ModelAttribute("userMessages")
	public List<Message> getMessages(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return this.messageRepository.findByReceiverOrderByCreatedDesc(user);
	}

	@ModelAttribute("readMessages")
	public List<Message> getReadMessages(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return this.messageRepository.findByReceiverAndCheckedLikeOrderByCreatedDesc(user, 1);
	}
	
	@ModelAttribute("unreadMessages")
	public List<Message> getUnreadMessages(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return this.messageRepository.findByReceiverAndCheckedLikeOrderByCreatedDesc(user, 0);
	}
	
	@ModelAttribute("sentMessages")
	public List<Message> getSentMessages(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return this.messageRepository.findBySenderOrderByCreatedDesc(user);
	}
}
