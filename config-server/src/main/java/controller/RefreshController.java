package controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping(value = "/refresh")
public class RefreshController {


    @RequestMapping(value = "/git", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> gitRefresh() throws Exception {

        String clientIp = System.getenv("CONFIG_SERVICE_SERVICE_HOST");
        String port = System.getenv("CONFIG_SERVICE_SERVICE_PORT_CONFIG_SERVER_PORT");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("", headers);

        RestTemplate restTemplate = new RestTemplate();
        URI uri = restTemplate.postForLocation("http://" + clientIp + ":" + port + "/actuator/bus-refresh", request);
        return new ResponseEntity<>(Objects.nonNull(uri) ? "Success" : "failed", HttpStatus.OK);
    }
}
