package com.microservice.Account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.microservice.Account.Dto.AccountConfigurationDto;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
// used to automatically populate auditing-related fields in your JPA entities,
// such as createdBy, createdDate, lastModifiedBy, and lastModifiedDate
@OpenAPIDefinition(info = @Info(title = "Account MicroService REST API Documentation", description = "Eazy Bank Account MicroService REST API Documentation", version = "v1", contact = @Contact(email = "abc@gmail.com", url = "http://eazybank.com", name = "Jhon Smith"), license = @License(name = "Apache 2.0", url = "http://eazybank.com")), externalDocs = @ExternalDocumentation(description = "Eazy Bank Account MicroService REST API Documentation", url = "http://localhost:8080/swagger-ui/index.html"))
// its use for define documentation details
@EnableConfigurationProperties(value = { AccountConfigurationDto.class })
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

}
