package pl.js.efecteback.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
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
