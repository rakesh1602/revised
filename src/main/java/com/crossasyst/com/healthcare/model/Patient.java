package com.crossasyst.com.healthcare.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    private String firstName;

    private String middleName;

    private String lastName;

    private String gender;

    private Date dateOfBirth;
}
