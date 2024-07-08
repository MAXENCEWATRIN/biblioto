package com.maxencew.biblioto.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class Library {

    private Long id;
    private String name;
    private String location;
    private Integer capacity;

}
