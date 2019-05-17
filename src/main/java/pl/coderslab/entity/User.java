package pl.coderslab.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.mindrot.jbcrypt.BCrypt;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(unique = true)
	private String userName;
	
	@NotBlank
	private String password;
	
	private boolean enabled;
	
	@NotBlank
	@Email(message="Niepoprawny email")
	@Column(unique = true)
	private String email;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Flat> flats = new ArrayList<>();
	
	@OneToMany(mappedBy = "sender",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<User> sender = new ArrayList<>();
	
	@OneToMany(mappedBy = "receiver",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<User> receiver = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comment = new ArrayList<>();
	
	public User() {

	}

	public User(String userName, String password, boolean enabled, String email, List<Flat> flats, List<User> sender, List<User> receiver, List<Comment> comment) {
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
		this.email = email;
		this.flats = flats;
		this.sender = sender;
		this.receiver = receiver;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public boolean isPasswordCorrect(String pwd) {
		return BCrypt.checkpw(pwd, this.password);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Flat> getFlats() {
		return flats;
	}

	public void setFlats(List<Flat> flats) {
		this.flats = flats;
	}

	public List<User> getSender() {
		return sender;
	}

	public List<User> getReceiver() {
		return receiver;
	}

	public void setSender(List<User> sender) {
		this.sender = sender;
	}

	public void setReceiver(List<User> receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", enabled=" + enabled +
				", email='" + email + '\'' +
				", flats=" + flats +
				", sender=" + sender +
				", receiver=" + receiver +
				", comment=" + comment +
				'}';
	}
}
