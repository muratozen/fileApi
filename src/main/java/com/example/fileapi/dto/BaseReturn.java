package com.example.fileapi.dto;

import com.example.fileapi.enums.ResponseMessage;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Data
public class BaseReturn<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -6742167402424412515L ;

    private String code = ResponseMessage.SUCCESS.getCode( );
    private String message = ResponseMessage.SUCCESS.getMessage( );
    private HttpStatus status = ResponseMessage.SUCCESS.getStatus( );
    private T data;

    public BaseReturn( String code, String message, T data ){
        super( ) ;
        this.code = code ;
        this.message = message ;
        this.data = data ;
    }

    public BaseReturn(String code, String message, T data, HttpStatus status) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
        this.status = status;
    }
    //Error
    public BaseReturn(ResponseMessage response) {
        super();
        this.code = response.getCode();
        this.message = response.getMessage();
        this.data = null;
        this.status = response.getStatus();
    }

    public BaseReturn(ResponseMessage response, String message) {
        super();
        this.code = response.getCode();
        this.message = message;
        this.data = null;
        this.status = response.getStatus();
    }

    //Success
    public BaseReturn(ResponseMessage response,T data) {
        super();
        this.code = response.getCode();
        this.message = response.getMessage();
        this.data = data;
        this.status = response.getStatus();
    }
}
