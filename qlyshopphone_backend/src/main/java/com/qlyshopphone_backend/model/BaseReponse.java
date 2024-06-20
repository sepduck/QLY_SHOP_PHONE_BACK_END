package com.qlyshopphone_backend.model;

import org.springframework.http.ResponseEntity;

public class BaseReponse {
    protected ResponseEntity<?> getResponseEntity(Object data) {
        return ResponseEntity.ok().body(getMyReponse(data));
    }
    private MyReponse getMyReponse(Object data) {
        MyReponse myReponse = new MyReponse();
        myReponse.status = 200;
        myReponse.data = data;
        myReponse.message = "success";
        return myReponse;
    }
}
