package com.cos.photogramstart.hanlder;

import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ControllerExceptionHanlder {

    @ExceptionHandler(CustomValidationException.class)
    public CMRespDto<?> validationException(CustomValidationException e) {
        return new CMRespDto<Map<String,String>>(-1,e.getMessage(),e.getErrorMap());
    }
}
