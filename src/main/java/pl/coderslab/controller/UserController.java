package pl.coderslab.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.coderslab.misc.LoginData;
import pl.coderslab.misc.SessionManager;
import pl.coderslab.entity.Message;
import pl.coderslab.entity.User;
import pl.coderslab.repository.MessageRepository;
import pl.coderslab.repository.UserRepository;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public UserController(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "account/register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/register";
        }
        List<User> users = this.userRepository.findAll();
        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                model.addAttribute("msg", "This email address is already used. Try different email.");
                return "account/register";
            }
            if (u.getUserName().equals(user.getUserName())) {
                model.addAttribute("msg1", "This username is already used. Try different username.");
                return "account/register";
            }
        }
        this.userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/accountOpt")
    public String accountOpt(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "account/accountOpt";
    }

    @PostMapping("/accountOpt")
    public String accountOptPost(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "account/accountOpt";
        }
        this.userRepository.save(user);
        model.addAttribute("user", user);
        HttpSession session = SessionManager.session();
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(Model model) {
        HttpSession session = SessionManager.session();
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "account/delete";
    }

    @GetMapping("/delete/{decision}")
    public String deletePost(@PathVariable int decision) {
        if (decision == 1) {
            HttpSession session = SessionManager.session();
            User user = (User) session.getAttribute("user");
            session.invalidate();
            List<Message> messages = this.messageRepository.findBySenderOrderByCreatedDesc(user);
            for (Message message : messages) {
                this.messageRepository.delete(message);
            }
            this.userRepository.delete(user);
            return "redirect:/login";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginData", new LoginData());
        return "account/login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute LoginData loginData, Model model, RedirectAttributes redirectAttributes) {
        User user = this.userRepository.findOneByuserName(loginData.getUserName());
        if (user != null && user.isPasswordCorrect(loginData.getPassword())) {
            HttpSession session = SessionManager.session();
            session.setAttribute("user", user);
            redirectAttributes.addFlashAttribute("msg", "Logged");
            return "redirect:/";
        }
        model.addAttribute("msg", "Invalid username or password");
        return "account/login";
    }

    @GetMapping("/logout")
    public String logout() {
        HttpSession session = SessionManager.session();
        session.invalidate();
        return "redirect:/";
    }
}
