package pl.coderslab.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Flat;
import pl.coderslab.entity.Photo;
import pl.coderslab.entity.User;
import pl.coderslab.repository.CommentRepository;
import pl.coderslab.repository.FlatRepository;
import pl.coderslab.repository.PhotoRepository;

@Controller
@RequestMapping("/flat")
public class FlatController {

	private final FlatRepository flatRepository;
	private final CommentRepository commentRepository;
	private final PhotoRepository photoRepository;

	public FlatController(FlatRepository flatRepository, CommentRepository commentRepository, PhotoRepository photoRepository) {
		this.flatRepository = flatRepository;
		this.commentRepository = commentRepository;
		this.photoRepository = photoRepository;
	}

	@GetMapping("/addOffer")
	public String addOffer(Model model) {
		model.addAttribute("flat", new Flat());
		return "addOffer";
	}

	@PostMapping("/addOffer")
	public String addOfferPost(@Valid @ModelAttribute Flat flat, BindingResult bindingResult,
			@RequestParam("photo") MultipartFile file, Model model, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "addOffer";
		}
		User user = (User) session.getAttribute("user");
		flat.setUser(user);
		flat.setCreated(new Date());
		flat.setPhoto(null);
		String fileName = null;
		this.flatRepository.save(flat);
		Long imgId = flat.getId();
		if (!file.isEmpty()) {
			try {
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
					fileName = "flat_" + imgId + "." + extension;
					byte[] bytes = file.getBytes();
					BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(
							"/home/ps/Desktop/ProjektKoncowyCL/src/main/webapp/WEB-INF/resources/picture" + fileName)));
					buffStream.write(bytes);
					buffStream.close();
					flat.setPhoto(fileName);
					this.flatRepository.save(flat);
					model.addAttribute("message", "Dodano produkt do bazy.");
					return "redirect:/";
				} else {
					model.addAttribute("eMessage", "Niepoprawny format pliku graficznego.");
					return "redirect:/addOffer";
				}

			} catch (Exception e) {
				return "home";
			}
		}
		model.addAttribute("eMessage", "Brak zdjęcia.");
		return "addOffer";
	}

	@GetMapping("/{id}")
	public String particularFlat(@PathVariable Long id, Model model, HttpSession session) {
		Flat flat = flatRepository.findOne(id);
		User user = (User) session.getAttribute("user");
		List<Comment> comments = commentRepository.findByFlatIdOrderByCreatedAsc(id);
		List<Photo> photos =photoRepository.findByFlatId(id);
		model.addAttribute("user", user);
		model.addAttribute("flat", flat);
		model.addAttribute("comments", comments);
		model.addAttribute("photos", photos);
		model.addAttribute("comment", new Comment());
		return "single_flat";
	}

	@GetMapping("/edit/{id}")
	public String editFlat(@PathVariable Long id, Model model) {
		Flat flat = this.flatRepository.findOne(id);
		model.addAttribute("flat", flat);
		return "editOffer";
	}

	@PostMapping("/edit/{id}")
	public String editFlatPost(@Valid @ModelAttribute Flat flat, BindingResult bindingResult,
			@RequestParam("photo") MultipartFile file, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "editOffer";
		}
		User user = (User) session.getAttribute("user");
		flat.setUser(user);
		flat.setCreated(new Date());
		String fileName = null;
		if (!file.isEmpty()) {
			try {
				Random random = new Random();
				int num = random.nextInt(500);
				Random random1 = new Random();
				int num1 = random1.nextInt(500);
				fileName = "room" + num + "_" + num1 + file.getOriginalFilename();
				byte[] bytes = file.getBytes();
				BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(
						"/home/ps/Desktop/ProjektKoncowyCL/src/main/webapp/WEB-INF/resources/picture" + fileName)));
				buffStream.write(bytes);
				buffStream.close();
				flat.setPhoto(fileName);
				this.flatRepository.save(flat);
				return "redirect:/";
			} catch (Exception e) {
				return "redirect:/";
			}
		}
		this.flatRepository.save(flat);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable Long id) {
		this.flatRepository.delete(id);
		return "redirect:/";
	}

	@PostMapping("/addComment/{flatId}")
	public String addPost(@Valid @ModelAttribute Comment comment, BindingResult bindingResult,
			@PathVariable Long flatId, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "redirect:/flat/" + flatId;
		}
		User user = (User) session.getAttribute("user");
		Flat flat = this.flatRepository.findOne(flatId);
		comment.setFlat(flat);
		comment.setUser(user);
		comment.setCreated(new Date());
		this.commentRepository.save(comment);
		return "redirect:/flat/" + flatId;
	}

	@ModelAttribute("voivodeship")
	public List<String> getVoivodeship() {
		String[] voivodeships = new String[] { "Podkarpackie", "Malopolskie", "Swietokrzyskie", "Slaskie", "Opolskie",
				"Dolnoslaskie", "Mazowieckie", "Lubelskie", "Wielkopolskie", "Lubuskie", "Pomorskie", "Lodzkie",
				"Podlaskie", "Kujawsko-pomorskie", "Warminsko-Mazurskie", "Zachodniopomorskie" };
		Arrays.sort(voivodeships);
		return Arrays.asList(voivodeships);
	}

	@ModelAttribute("typeOfFlat")
	public List<String> gettypeOfFlat() {
		String[] typeOfFlat = new String[] { "Dom", "Mieszkanie", "Pokoj", "Apartament" };
		Arrays.sort(typeOfFlat);
		return Arrays.asList(typeOfFlat);
	}

	@GetMapping("/search")
	public String searchFlats(@RequestParam String name, @RequestParam String city,
			@RequestParam(defaultValue="1") Double num1, @RequestParam(defaultValue="100000000") Double num2,
			@RequestParam(defaultValue="1") int num3, @RequestParam(defaultValue="200") int num4, Model model) {
		model.addAttribute("searchResult", this.flatRepository.findByGivenCityNameNumsGuests(name, city,num1, num2, num3, num4));
		model.addAttribute("name", name);
		model.addAttribute("city", city);
		model.addAttribute("num1", num1);
		model.addAttribute("num2", num2);
		model.addAttribute("num3", num3);
		model.addAttribute("num4", num4);
		return "search";
	}
}
