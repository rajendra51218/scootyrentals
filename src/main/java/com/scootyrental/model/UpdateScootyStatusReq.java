package com.scootyrental.model;

import lombok.Data;

@Data
public class UpdateScootyStatusReq {
    private Integer id;
    private String status;
}
