package br.com.fiap.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
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
	@DisplayName("Deve listar todas categorias e retornar status 200")
	public void shouldListAllCategories() throws Exception {

		mvc.perform(get("/categoria").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	@DisplayName("Deve salvar uma categoria e retornar com status 200")
	public void shouldSaveCategory() throws Exception{
		mvc.perform(post("/categoria")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"nomeCategoria\": \"Games\"}"))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(header().exists("Location"));
	}
	
	@Test
	@DisplayName("Deve retornar uma categoria pelo ID e com status 200")
	public void shouldFindCategoryById() throws Exception{
		mvc.perform(get("/categoria/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string("{\"idCategoria\":1,\"nomeCategoria\":\"Notebook\"}"));
	}
	
	@Test
	@DisplayName("Deve atualizar uma categoria pelo ID, retornar status 200")
	public void shouldUpdateCategory() throws Exception{
		mvc.perform(put("/categoria/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"nomeCategoria\": \"Games Atualizado\"}"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Deve deletar uma categoria pelo ID, retornar status 204")
	public void shouldDeleteCategoryById() throws Exception{
		
		mvc.perform(delete("/categoria/5")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
	/*@Test
	@DisplayName("Não deve deletar uma categoria pelo ID, retornar status 500")
	public void shouldNotDeleteCategoryById() throws Exception{
		
		mvc.perform(delete("/categoria/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError());
	}*/
}
