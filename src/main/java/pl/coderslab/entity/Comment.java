package pl.coderslab.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Comment {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Flat flat;

	@CreationTimestamp
	private Date created;
	
	@Size(max = 300)
	@NotBlank
	private String text;

	public Comment() {
		this.created = new Date();
	}

	public Comment(User user, Flat flat, Date created, String text) {
		this.user = user;
		this.flat = flat;
		this.created = created;
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Flat getFlat() {
		return flat;
	}

	public Date getCreated() {
		return created;
	}

	public String getText() {
		return text;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setFlat(Flat flat) {
		this.flat = flat;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Comment{" +
				"id=" + id +
				", user=" + user +
				", flat=" + flat +
				", created=" + created +
				", text='" + text + '\'' +
				'}';
	}
}
