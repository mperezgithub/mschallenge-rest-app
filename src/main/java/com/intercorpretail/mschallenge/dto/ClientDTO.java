package com.intercorpretail.mschallenge.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ClientDTO {
    private String id;
    private String name;
    private String lastname;
    private int age;
    private Date dob;
    private Date dod;
}
