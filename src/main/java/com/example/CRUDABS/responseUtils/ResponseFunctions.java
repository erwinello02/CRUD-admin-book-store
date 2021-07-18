package com.example.CRUDABS.responseUtils;

import com.example.CRUDABS.models.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class ResponseFunctions {
    public ResponseEntity<ResponseModel> response(HttpStatus status, Object result){
        ResponseModel response = new ResponseModel();
        if (status.value() == 200 || status.value() == 201){
            response.setStatus("Success");
        } else {
            response.setStatus("Failed");
        }
        response.setResult(result);
        return ResponseEntity.status(status).body(response);
    }
}