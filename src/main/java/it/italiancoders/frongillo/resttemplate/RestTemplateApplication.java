package it.italiancoders.frongillo.resttemplate;

import it.italiancoders.frongillo.resttemplate.model.HelloMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@SpringBootApplication
public class RestTemplateApplication {

	private static final Logger log = LoggerFactory.getLogger(RestTemplateApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestTemplateApplication.class, args);
	}


	@Bean
	public CommandLineRunner loadData() {
		return (args) -> {

			RestTemplate restTemplate = new RestTemplate();
			HelloMessage response1 = restTemplate.getForObject("http://localhost:8080/public/api/hello", HelloMessage.class);
			log.info("Semplice richiesta get:"+response1.toString());

			/*
			  Richiesta Get con Header e variabile nel path
			 */
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/public/api/helloPath")
					.queryParam("name", "dario");

			HttpEntity<?> entity = new HttpEntity<>(headers);

		    HttpEntity<HelloMessage> response2 = restTemplate.exchange(
				builder.build().encode().toUri(),
				HttpMethod.GET,
				entity,
				HelloMessage.class);

			log.info("Richiesta get con header e variabile nel path"+response2.getBody().toString());

	};

	}

}
