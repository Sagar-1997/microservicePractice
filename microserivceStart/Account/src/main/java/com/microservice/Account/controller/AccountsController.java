package com.microservice.Account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.microservice.Account.Dto.CustomerDto;
import com.microservice.Account.Dto.ErrorResponseDto;
import com.microservice.Account.Dto.ResponseDto;
import com.microservice.Account.constants.AccountsConstants;
import com.microservice.Account.service.IAccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController // tell spring Ioc container this class will create restfull webservice
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE }) // map http request to controller or //
                                                                                // method and produce json response
@AllArgsConstructor
@Validated // tell spring container to perform validation on the http web service
@Tag(name = "CURD REST API for Accounts in Eazybanks", description = "CURD REST API for Accounts in Eazybanks to CREATE,UPDATE,DELETE,GET operation in EazyBank application")
// add information on each controller for better document understanding
public class AccountsController {

    private IAccountsService iAccountsService;

    // @Valid is use to valid the input request
    // @Operation add details about rest endpoints
    @Operation(summary = "Create Account REST API", description = "REST API to create new customer and account details in Eazy bank")
    // to add correct response code in endpoint
    @ApiResponses({ @ApiResponse(responseCode = "201", description = "HTTP Status Created"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(summary = "Get Account REST API", description = "REST API to get customer and account details in Eazy bank")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(
            @RequestParam @Pattern(regexp = "($|[0-9]{10})", message = "Mobile Number must be 10 digits") String mobileNumber) {
        CustomerDto fetchCustomer = iAccountsService.fetchAccounts(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(fetchCustomer);
    }

    @Operation(summary = "Put Account REST API", description = "REST API to update customer and account details in Eazy bank application")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status Success"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PutExchange("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(summary = "DELETE Account REST API", description = "REST API to delete customer and account details in Eazy bank application")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status Success"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(
            @RequestParam @Pattern(regexp = "($|[0-9]{10})", message = "Mobile Number must be 10 digits") String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }
}
