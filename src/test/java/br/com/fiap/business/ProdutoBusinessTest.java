package br.com.fiap.business;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.fiap.exception.ReponseBusinessException;
import br.com.fiap.model.CategoriaModel;
import br.com.fiap.model.MarcaModel;
import br.com.fiap.model.ProdutoModel;
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
		String sku = "1234acb";
		String expected = "1234ACB";

		// WHEN
		String actual = produtoBusiness.changeSkuToUpperCase(sku);

		// THEN
		assertEquals("Erro ao tentar transformar SKU em maiúsculo", expected, actual);

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
		assertEquals("Erro ao adicionar 10 reais caso a categoria seja Smartphone", expected, actual);

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
		assertEquals("Erro ao adicionar 20 reais caso a categoria seja Notebook", expected, actual);

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
		assertEquals("Erro ao retornar o preço caso a categoria não seja Smartphone ou Notebook", expected, actual);

	}

	@Test(expected = ReponseBusinessException.class)
	public void testVerifyNomeProdutoWithTest() throws ReponseBusinessException {

		// GIVEN
		String nome = "Produto Teste";

		// WHEN
		produtoBusiness.verifyNomeProduto(nome);
	}

	@Test
	public void testVerifyNomeProdutoWithoutTest() throws ReponseBusinessException {

		// GIVEN
		String nome = "Produto Novo";

		// WHEN
		produtoBusiness.verifyNomeProduto(nome);
	}

	@Test
	public void testApplyBusiness() throws ReponseBusinessException {

		// GIVEN
		CategoriaModel categoriaModel = new CategoriaModel(1, "Notebook");
		ProdutoModel produto = new ProdutoModel(1, "banana", "123abc", "Descrição", new BigDecimal(10),
				"Caracteristica teste", categoriaModel, null);

		ProdutoModel expected = new ProdutoModel(1, "banana", "123ABC", "Descrição", new BigDecimal(30),
				"Caracteristica teste", categoriaModel, null);

		// WHEN
		Mockito.when(categoriaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(categoriaModel));
		ProdutoModel actual = produtoBusiness.applyBusiness(produto);

		// THEN
		Mockito.verify(categoriaRepository, Mockito.times(1)).findById(Mockito.anyLong());
		assertEquals("Erro", expected.toString(), actual.toString());

	}

}
