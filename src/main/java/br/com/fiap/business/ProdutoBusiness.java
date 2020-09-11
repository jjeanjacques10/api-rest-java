package br.com.fiap.business;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.exception.ReponseBusinessException;
import br.com.fiap.model.CategoriaModel;
import br.com.fiap.model.ProdutoModel;
import br.com.fiap.repository.CategoriaRepository;

@Component
public class ProdutoBusiness {

	@Autowired
	private CategoriaRepository categoriaRepository;

	// throws ReponseBusinessException {
	public ProdutoModel applyBusiness(ProdutoModel produtoModel) throws ReponseBusinessException {

		String sku = changeSkuToUpperCase(produtoModel.getSku());
		produtoModel.setSku(sku);

		BigDecimal preco = addValueToPreco(produtoModel.getCategoria(), produtoModel.getPreco());
		produtoModel.setPreco(preco);

		verifyNomeProduto(produtoModel.getNome());

		return produtoModel;
	}

	private void verifyNomeProduto(String nome) throws ReponseBusinessException {
		nome = nome.toUpperCase();
		if (nome.contains("TESTE")) {
			throw new ReponseBusinessException("O produto não pode ter teste no nome");
		}
	}

	private BigDecimal addValueToPreco(CategoriaModel categoria, BigDecimal preco) {

		categoria = categoriaRepository.findById(categoria.getIdCategoria()).get();

		if ("Smartphone".equals(categoria.getNomeCategoria())) {
			preco = preco.add(new BigDecimal(10));
		} else if ("Notebook".equals(categoria.getNomeCategoria())) {
			preco = preco.add(new BigDecimal(20));
		}

		return preco;
	}

	private String changeSkuToUpperCase(String sku) {
		return sku.toUpperCase();
	}
}
