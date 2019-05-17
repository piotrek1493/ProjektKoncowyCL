package pl.coderslab.entity;

import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Flat flat;

	@CreationTimestamp
	private Date created;

	private String url;

	private String description;

	public Photo() {
		this.created = new Date();
	}

	public Photo(Flat flat, Date created, String url, String description) {
		this.flat = flat;
		this.created = created;
		this.url = url;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public Flat getFlat() {
		return flat;
	}

	public Date getCreated() {
		return created;
	}

	public String getUrl() {
		return url;
	}

	public String getDescription() {
		return description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFlat(Flat flat) {
		this.flat = flat;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Photo{" +
				"id=" + id +
				", flat=" + flat +
				", created=" + created +
				", url='" + url + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
