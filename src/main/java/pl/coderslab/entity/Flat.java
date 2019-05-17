package pl.coderslab.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Flat {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min = 3)
	private String name;
	
	@ManyToOne
	private User user;

	@CreationTimestamp
	private Date created;
	
	@NotBlank
	@Size(min = 4)
	private String voivodeship;

	@NotBlank
	private String postCode;

	@NotBlank
	@Size(min = 3)
	private String city;

	@NotBlank
	@Size(min = 3)
	private String street;

	@NotBlank
	@Size(min = 3)
	private String typeOfFlat;

	@NotNull
	private double surface;

	@NotNull
	private double price;

	@NotNull
	private int numberOfGuests;

	@NotBlank
	private String description;
	
	private String photo;
	
	@OneToMany(mappedBy = "flat", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comment = new ArrayList<>();

	
	@OneToMany(mappedBy = "flat",cascade = CascadeType.ALL)
	private List<Photo> photos = new ArrayList<>();
	
	public Flat() {
		this.created = new Date();
	}

	public Flat(String name, User user, Date created, String voivodeship, String postCode, String city, String street,
				String typeOfFlat, double surface, double price, int numberOfGuests, String description, String photo, List<Comment> comment, List<Photo> photos) {
		this.name = name;
		this.user = user;
		this.created = created;
		this.voivodeship = voivodeship;
		this.postCode = postCode;
		this.city = city;
		this.street = street;
		this.typeOfFlat = typeOfFlat;
		this.surface = surface;
		this.price = price;
		this.numberOfGuests = numberOfGuests;
		this.description = description;
		this.photo = photo;
		this.comment = comment;
		this.photos = photos;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Date getCreated() {
		return created;
	}

	public String getVoivodeship() {
		return voivodeship;
	}

	public String getPostCode() {
		return postCode;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getTypeOfFlat() {
		return typeOfFlat;
	}

	public double getSurface() {
		return surface;
	}

	public double getPrice() {
		return price;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public String getDescription() {
		return description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setVoivodeship(String voivodeship) {
		this.voivodeship = voivodeship;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setTypeOfFlat(String typeOfFlat) {
		this.typeOfFlat = typeOfFlat;
	}

	public void setSurface(double surface) {
		this.surface = surface;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	
	public int getCommentSize() {
		return this.comment.size();
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "Flat{" +
				"id=" + id +
				", name='" + name + '\'' +
				", user=" + user +
				", created=" + created +
				", voivodeship='" + voivodeship + '\'' +
				", postCode='" + postCode + '\'' +
				", city='" + city + '\'' +
				", street='" + street + '\'' +
				", typeOfFlat='" + typeOfFlat + '\'' +
				", surface=" + surface +
				", price=" + price +
				", numberOfGuests=" + numberOfGuests +
				", description='" + description + '\'' +
				", photo='" + photo + '\'' +
				", comment=" + comment +
				", photos=" + photos +
				'}';
	}
}
