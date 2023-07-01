package com.crossasyst.com.healthcare.model;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    private String firstName;

    private String middleName;

    private String lastName;

    private String education;

    private Date startDateOfPracticing;

    private List<Address> addressList;

    private List<Contact> contactList;
}
