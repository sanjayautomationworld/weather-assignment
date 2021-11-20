package com.example.locationDemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api/v1")
public class LocationController {

  @Value("${url}")
  private String url;

  @Value("${rapid-api-host}")
  private String rapidApiHost;

  @Value("${rapidapi-key}")
  private String rapidApiKey;

  @GetMapping("/location")
  public String getLocationByCity(@RequestParam String city) throws Exception {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.set("x-rapidapi-host", rapidApiHost);
    headers.set("x-rapidapi-key", rapidApiKey);
    HttpEntity<String> entity = new HttpEntity<>("body", headers);
    ResponseEntity<String> data =
        restTemplate.exchange(url + city, HttpMethod.GET, entity, String.class);
    return data.getBody();
  }
}
