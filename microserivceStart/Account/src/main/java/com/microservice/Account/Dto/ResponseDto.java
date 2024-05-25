package com.microservice.Account.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Response", description = "Schema to hold the successfull response informatio")
public class ResponseDto {

    @Schema(description = "Status code in the response", example = "200")
    private String statusCode;

    @Schema(description = "Status Message in the response", example = "Request processed Successfully")
    private String statusMsg;
}
