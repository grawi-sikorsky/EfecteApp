/*
 * Copyright (c) 2023.
 * @author Jakub Sikora
 */

package pl.js.efecteback.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import pl.js.efecteback.dto.CreateModifyNoteDTO;
import pl.js.efecteback.services.NoteServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(value = "classpath:sql/insert-dummy-notes.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:sql/cleanup-db.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles("test")
class NoteControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	@MockBean
	private NoteServiceImpl noteService;

	@BeforeAll
	public void setup() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}


	public String noteToJson() throws JsonProcessingException {
		CreateModifyNoteDTO slimNoteDTO = new CreateModifyNoteDTO("test title", "test content");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(slimNoteDTO);
	}

	@Test
	void addNote() throws Exception {
		this.mockMvc.perform(post("/notes/add")
						.contentType(MediaType.APPLICATION_JSON)
						.content(noteToJson()))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void addNote_noBody_returnsBadRequest() throws Exception {
		mockMvc.perform(post("/notes/add")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

		Mockito.verifyNoInteractions(noteService);
	}

	@Test
	public void addUser_emptyBody_returnsBadRequest() throws Exception {
		mockMvc.perform(post("/notes/add")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{}"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		Mockito.verifyNoInteractions(noteService);
	}

	@Test
	void getNote() throws Exception {
		this.mockMvc.perform(get("/notes/10")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	void listAllNotes() throws Exception {
		this.mockMvc.perform(get("/notes")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	void updateNote() throws Exception {
		this.mockMvc.perform(patch("/notes/10")
						.contentType(MediaType.APPLICATION_JSON)
						.content(noteToJson()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void updateNote_noBody_returnsBadRequest() throws Exception {
		mockMvc.perform(patch("/notes/10")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

		Mockito.verifyNoInteractions(noteService);
	}

	@Test
	void deleteNote() throws Exception {
		this.mockMvc.perform(delete("/notes/10")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent())
				.andDo(MockMvcResultHandlers.print());
	}
}