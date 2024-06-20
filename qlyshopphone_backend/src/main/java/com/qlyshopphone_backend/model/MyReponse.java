package com.qlyshopphone_backend.model;

import lombok.Data;

@Data
public class MyReponse {
    public int status;
    public String message;
    public Object data;
}
