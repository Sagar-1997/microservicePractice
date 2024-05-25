package com.microservice.Account.Dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

//record act as a data store where we can get the data but can't change it
@ConfigurationProperties(prefix = "accounts") // bind external configuration with pojo class
@Getter
@Setter
public class AccountConfigurationDto {

    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupports;
}
// public record AccountConfigurationDto(String message, Map<String, String>
// contactDetails, List<String> onCallSupports) {

// }
