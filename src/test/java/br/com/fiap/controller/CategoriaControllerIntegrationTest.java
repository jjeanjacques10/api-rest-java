package br.com.fiap.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CategoriaControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void shouldListAllCategories() throws Exception {

		mvc.perform(get("/categoria").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void shouldSaveCategory() throws Exception{
		mvc.perform(post("/categoria")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"nomeCategoria\": \"Games\"}"))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(header().exists("Location"));
	}
	
	@Test
	public void shouldUpdateCategory() throws Exception{
		mvc.perform(put("/categoria/5")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"nomeCategoria\": \"Games\"}"))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(header().exists("Location"));
	}
}
