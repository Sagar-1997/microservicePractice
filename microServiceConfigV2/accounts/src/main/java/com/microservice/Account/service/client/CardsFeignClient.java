package com.microservice.Account.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservice.Account.Dto.CardsDto;

@FeignClient("cards") // indicate feign client for cards microservice
public interface CardsFeignClient {

    // its declarative approach to get data from other microservice
    // method declaration same as web Service method in cards
    @GetMapping("/api/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam String mobileNumber);
}
