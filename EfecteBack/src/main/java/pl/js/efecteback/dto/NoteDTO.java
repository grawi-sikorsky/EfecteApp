package pl.js.efecteback.dto;

import java.time.ZonedDateTime;

public class NoteDTO {
	private Long id;

	private String title;

	private String content;

	private ZonedDateTime date;

	public NoteDTO() {
	}

	public NoteDTO(Long id, String title, String content, ZonedDateTime date) {
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
