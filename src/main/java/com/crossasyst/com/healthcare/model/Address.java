package com.crossasyst.com.healthcare.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String firstLine;

    private String secondLine;

    private String city;

    private String state;

    private String country;

    private String zipcode;
}
