package pl.js.efecteback.model;

import jakarta.persistence.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Table(name = "notes")
public class NoteModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, nullable = false)
	private String title;

	@Column(length = 200, nullable = false)
	private String content;

	private ZonedDateTime date;

	@PrePersist
	public void prepersist() {
		this.date = ZonedDateTime.now(ZoneId.of("Europe/Warsaw"));
	}

	public NoteModel() {
	}

	public NoteModel(Long id, String title, String content, ZonedDateTime date) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ZonedDateTime getDate() {
		return date;
	}

	public void setDate(ZonedDateTime date) {
		this.date = date;
	}
}
