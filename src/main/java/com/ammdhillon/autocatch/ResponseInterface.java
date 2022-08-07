package com.ammdhillon.autocatch;

import com.ammdhillon.autocatch.model.entity_model.wrapper.ErrorResponse;
import com.ammdhillon.autocatch.model.entity_model.wrapper.SuccessResponse;
import com.ammdhillon.autocatch.model.pojo.OperationStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ResponseInterface {

    default ResponseEntity<Object> respondObject(Object model) {
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse(HttpStatus.OK.value(),false, model));
    }

    default ResponseEntity<Object> respondMsgSuccess(String msg) {
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse(HttpStatus.OK.value(),false, new OperationStatus(msg)));
    }

    default ResponseEntity<Object> respondFail(HttpStatus status) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),false, status.toString()));
    }

    default ResponseEntity<Object> respondMsgFail(String msg) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),false, msg));
    }
}
