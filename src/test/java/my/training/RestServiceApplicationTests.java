package my.training;

import my.training.model.Greeting;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

import static java.lang.String.format;
import static my.training.controller.GreetingController.TEMPLATE;

//Note that the tests start the application on a random port.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestServiceApplicationTests {
    private String name = "Yaser";

    @LocalServerPort
	private int port;

    @Autowired
    private RestTemplate restTemplate;

	@Test
	public void integrationTestWhenSendingGetRequestToController() {
		ResponseEntity<Map> entity = this.restTemplate.getForEntity(
				"https://localhost:" + this.port + "/greeting", Map.class);
        BDDAssertions.then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

    @Test
    public final void getCorrectMessage_whenSendingGetRequestToController() {
        Greeting response = restTemplate.getForObject(
                UriComponentsBuilder.fromUriString("https://localhost:" + this.port + "/greeting")
                        .queryParam("name", name)
                        .build().toUriString()
                , Greeting.class);

        BDDAssertions.then(response.getContent()).isEqualTo(format(TEMPLATE, name));
    }
}