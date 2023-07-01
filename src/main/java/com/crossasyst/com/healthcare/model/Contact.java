package com.crossasyst.com.healthcare.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    private String contactNumberOne;

    private String contactNumberTwo;

    private String contactType;
}
