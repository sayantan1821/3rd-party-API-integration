package com.example.demoapi.commons;

import com.example.demoapi.dtos.UserDTO;
import com.example.demoapi.exceptions.InvalidTokenException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Auth {
    private RestTemplate restTemplate;
    public Auth(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDTO validateToken(String token) {
        ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity("http://localhost:8080/users/validate/" + token, UserDTO.class);
        if(responseEntity.getBody() == null) {
            throw new InvalidTokenException("Provided token is not valid!");
        }
        return responseEntity.getBody();
    }
}
