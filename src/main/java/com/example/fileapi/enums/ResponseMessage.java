package com.example.fileapi.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseMessage {

    //Save
    SUCCESS_SAVE("0000", "Successfully saved!", HttpStatus.OK),
    ERROR_SAVE("0001", "The file could not be saved!", HttpStatus.BAD_REQUEST),

    //Update
    SUCCESS_UPDATE("0000", "Successfully update!", HttpStatus.OK),
    ERROR_UPDATE("0002", "The file could not be update!", HttpStatus.BAD_REQUEST),

    //Delete
    SUCCESS_DELETE("0000", "Successfully delete!", HttpStatus.OK),
    ERROR_DELETE("0003", "The file could not be delete!", HttpStatus.BAD_REQUEST),

    //Generic
    SUCCESS("0000", "Transaction successfully  done!", HttpStatus.OK),
    GENERIC_ERROR("0004", "The operation could not be performed!", HttpStatus.BAD_REQUEST),

    //EmptyList
    EMPTY_LIST_WARNING("0005", "List is empty!", HttpStatus.OK),

    //File Not Found
    FILE_NOT_FOUND("0006","File not found!", HttpStatus.NOT_FOUND),

    //Not Allowed
    FILE_EXTENSION_NOT_ALLOWED("0007","File extension not allowed!",HttpStatus.METHOD_NOT_ALLOWED),

    //Not Allowed
    FILE_SIZE_ERROR("0008","File size too large!",HttpStatus.PAYLOAD_TOO_LARGE);


    private String code;
    private String message;
    private HttpStatus status;

    private ResponseMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private ResponseMessage(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
