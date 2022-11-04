package com.hoaxify.ws.errors;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ErrorHandler implements ErrorController {


    private ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    ApiError handleError(WebRequest webRequest){
        Map<String, Object> attributes = this.errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE, ErrorAttributeOptions.Include.BINDING_ERRORS));
        String message=(String) attributes.get("message");
        String path = (String) attributes.get("path");
        int status= (int) attributes.get("status");
        ApiError error = new ApiError(status,message,path);
        if(attributes.containsKey("errors")){
            @SuppressWarnings("unchecked")
            List<FieldError> fieldErrors= (List<FieldError>) attributes.get("errors");
            Map<String, String> validationsErrors = new HashMap<>();
            for(FieldError fieldError :fieldErrors){
                validationsErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            error.setValidationsErrors(validationsErrors);
        }

        return error;
    }

    @Override
    public String getErrorPath(){
        return "/error";
    }
}
