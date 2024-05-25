package com.microservice.Account.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// to described the DTO class documentation
@Schema(name = "CustomerDetails", description = "Schema hold Customer,Account,Cards and Loans details")
public class CustomerDetailsDto {

    @Schema(description = "Name of Customer", example = "Jhon Smith")
    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 5, max = 30, message = "The length of customer name should be between 5 and 30")
    private String name;

    @Schema(description = "Email of Customer", example = "jhon@example.com")
    @NotEmpty(message = "Email address can not be null or empty")
    @Email(message = "Email address should be valid value")
    private String email;

    @Schema(description = "Mobile Number of Customer", example = "8893478098")
    @Pattern(regexp = "($|[0-9]{10})", message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    @Schema(description = "Account details of the Customer")
    private AccountsDto accountsDto;

    @Schema(description = "Card details of the Customer")
    private CardsDto cardsDto;

    @Schema(description = "Loans details of the Customer")
    private LoansDto loansDto;
}
