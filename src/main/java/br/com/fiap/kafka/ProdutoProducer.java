package br.com.fiap.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProdutoProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public ProdutoProducer(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void publish(String sku, String nome) {
		kafkaTemplate.send("fiap.produto", sku, nome);
	}
}
