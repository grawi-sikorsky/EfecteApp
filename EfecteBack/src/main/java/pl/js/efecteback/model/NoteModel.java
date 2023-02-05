package pl.js.efecteback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
@Getter
@Entity
public class NoteModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(length = 100)
	String title;
	@Column(length = 200)
	String content;

	ZonedDateTime date;

	public NoteModel() {
	}


}
