package pl.coderslab.entity;

import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String text;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User sender;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User receiver;

	@CreationTimestamp
	private Date created;
	
	private int checked;

	public Message() {
		this.created = new Date();
		this.checked = 0;
	}

	public Message(String text, User sender, User receiver, Date created, int checked) {
		this.text = text;
		this.sender = sender;
		this.receiver = receiver;
		this.created = created;
		this.checked = checked;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "Message{" +
				"id=" + id +
				", text='" + text + '\'' +
				", sender=" + sender +
				", receiver=" + receiver +
				", created=" + created +
				", checked=" + checked +
				'}';
	}
}
