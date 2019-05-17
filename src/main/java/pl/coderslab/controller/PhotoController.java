package pl.coderslab.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pl.coderslab.entity.Flat;
import pl.coderslab.entity.Photo;
import pl.coderslab.repository.FlatRepository;
import pl.coderslab.repository.PhotoRepository;

@Controller
@RequestMapping
public class PhotoController {

	private final FlatRepository flatRepository;
	private final PhotoRepository photoRepository;

	public PhotoController(FlatRepository flatRepository, PhotoRepository photoRepository) {
		this.flatRepository = flatRepository;
		this.photoRepository = photoRepository;
	}

	@GetMapping("/add/photos/{id}")
	public String addOffer(@PathVariable Long id, Model model) {
		model.addAttribute("flat", new Flat());
		model.addAttribute("photos", new Photo());
		return "multipleUpload";
	}

	@PostMapping("/add/photos/{id}")
	public String addOfferPost(@ModelAttribute Photo photo, @RequestParam("photo") MultipartFile[] files,
                               @PathVariable Long id, Model model) {
		Flat flat = this.flatRepository.findFirstById(id);
		String fileName;
		String msg = null;
		for (int i = 0; i < files.length; i++) {
			Photo newPhoto = new Photo();
			newPhoto.setFlat(flat);
			newPhoto.setUrl(null);
			newPhoto.setCreated(new Date());
			this.photoRepository.save(newPhoto);
			try {
				Random random = new Random();
				int num = random.nextInt(500);
				String extension = FilenameUtils.getExtension(files[i].getOriginalFilename());
				if (extension.equals("jpg") || extension.equals("jpeg")) {
					fileName = "room_" + num + "_" + newPhoto.getId() + "." + extension;
				byte[] bytes = files[i].getBytes();
				BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(
						"/home/ps/Desktop/ProjektKoncowyCL/src/main/webapp/WEB-INF/resources/picture" + fileName)));
				buffStream.write(bytes);
				buffStream.close();
				newPhoto.setUrl(fileName);
				this.photoRepository.save(newPhoto);
				msg += "You have successfully uploaded " + fileName + "<br/>";
				} else {
					model.addAttribute("errorMessage", "Niepoprawny format pliku graficznego.");
					return "redirect:/add/photos/{id}";
				}
			} catch (Exception e) {
				return "redirect:/";
			}
		}
		return "redirect:/";
	}
}
