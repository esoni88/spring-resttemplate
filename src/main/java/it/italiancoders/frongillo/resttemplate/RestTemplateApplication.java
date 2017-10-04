package it.italiancoders.frongillo.resttemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.italiancoders.frongillo.resttemplate.model.HelloMessage;
import it.italiancoders.frongillo.resttemplate.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@SpringBootApplication
public class RestTemplateApplication {

	private static final Logger log = LoggerFactory.getLogger(RestTemplateApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestTemplateApplication.class, args);
	}


	@Bean
	public CommandLineRunner testRestTemplate() {
		return (args) -> {

			/*
			  Semplice Richiesta Get
			 */
			RestTemplate restTemplate = new RestTemplate();
			HelloMessage response1 = restTemplate.getForObject("http://localhost:8080/public/api/hello", HelloMessage.class);
			log.info("Semplice richiesta get:"+response1.toString());

			/*
			  Richiesta Get con Header e  request params
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

			HttpEntity<String> responseString = restTemplate.exchange(
					builder.build().encode().toUri(),
					HttpMethod.GET,
					entity,
					String.class);

			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(responseString.getBody());
			JsonNode fieldMessage =root.get("message");
			String myString = fieldMessage.asText();
			log.info("Richiesta get con header e variabile nel path utilizzando json anziche un pojo per parsare la risposta:"+myString);

			HttpEntity<Person> requestToPost = new HttpEntity<>(new Person("dario", "frongillo"));
			UriComponentsBuilder builderPost = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/public/api/helloPost");

			ResponseEntity<HelloMessage> postResponse = restTemplate.exchange(builderPost.build().encode().toUri(), HttpMethod.POST,requestToPost, HelloMessage.class);
			log.info("Richiesta Post di un oggetto Person con ritorno nella risposta un oggetto HelloMessage:"+postResponse.getBody().toString());


		};

	}

}
