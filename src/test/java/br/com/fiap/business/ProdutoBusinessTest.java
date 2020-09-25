package br.com.fiap.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.fiap.model.CategoriaModel;
import br.com.fiap.repository.CategoriaRepository;

@SpringBootTest
public class ProdutoBusinessTest {

	@InjectMocks
	public ProdutoBusiness produtoBusiness;
	
	@Mock
	public CategoriaRepository categoriaRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testChangeSkuToUpperCase() {
		
		// GIVEN
		String sku = "1234abc";
		String expected = "1234ABC";
		
		// WHEN
		String actual = produtoBusiness.changeSkuToUpperCase(sku);
		
		// THEN
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testAddValueToPrecoWithCategoriaSmartphone() {
		
		// GIVEN
		BigDecimal preco = new BigDecimal(10);
		BigDecimal expected = new BigDecimal(20);
		CategoriaModel categoria = new CategoriaModel(1, "Smartphone");
		
		// WHEN
		Mockito.when(categoriaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(categoria));
		BigDecimal actual = produtoBusiness.addValueToPreco(preco, categoria);
		
		// THEN
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testAddValueToPrecoWithCategoriaNotebook() {
		
		// GIVEN
		BigDecimal preco = new BigDecimal(10);
		BigDecimal expected = new BigDecimal(30);
		CategoriaModel categoria = new CategoriaModel(1, "Notebook");
		
		// WHEN
		Mockito.when(categoriaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(categoria));
		BigDecimal actual = produtoBusiness.addValueToPreco(preco, categoria);
		
		// THEN
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testAddValueToPrecoWithoutCategoria() {
		
		// GIVEN
		BigDecimal preco = new BigDecimal(10);
		BigDecimal expected = new BigDecimal(10);
		CategoriaModel categoria = new CategoriaModel(1, null);
		
		// WHEN
		Mockito.when(categoriaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(categoria));
		BigDecimal actual = produtoBusiness.addValueToPreco(preco, categoria);
		
		// THEN
		assertEquals(expected, actual);
		
	}
	

}
