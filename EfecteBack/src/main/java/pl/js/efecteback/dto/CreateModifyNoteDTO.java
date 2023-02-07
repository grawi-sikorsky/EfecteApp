package pl.js.efecteback.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class CreateModifyNoteDTO {
	@NotBlank(message = "Note title cannot be empty")
	@Length(max = 100, message = "Note title cannot be longer than 100 characters")
	private String title;

	@NotBlank(message = "Note content cannot be empty")
	@Length(max = 200, message = "Note content cannot be longer than 200 characters")
	private String content;


	public CreateModifyNoteDTO() {
	}

	public CreateModifyNoteDTO(String title, String content) {
		this.title = title;
		this.content = content;
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
}
