package com.microservice.cards.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "cards")
@Getter
@Setter
public class CardsConfigurationDto {

    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupports;
}
// public record CardsConfigurationDto(String message, Map<String, String>
// contactDetails, List<String> onCallSupports) {

// }
