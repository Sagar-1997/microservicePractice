package com.microservice.Account.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Account", description = "Schema hold Account details")
public class AccountsDto {
    @NotEmpty(message = "Account Number can not be null or empty")
    @Pattern(regexp = "($|[0-9]{10})", message = "Account Number must be 10 digits")
    @Schema(description = "AccountNumber of Customer", example = "8999273809")
    private Long accountNumber;

    @NotEmpty(message = "Account Type can not be null or empty")
    @Schema(description = "AccountType of Customer", example = "Saving")
    private String accountType;

    @NotEmpty(message = "Branch Address can not be null or empty")
    @Schema(description = "Branch Address of Bank")
    private String branchAddress;
}
