package com.crossasyst.com.healthcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @SequenceGenerator(name = "seq_address_id", sequenceName = "seq_address_id", initialValue = 10000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address_id")
    private Long addressId;
    private String firstLine;

    private String secondLine;

    private String city;

    private String state;

    private String country;

    private String zipcode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id", nullable = false)
    @JsonIgnoreProperties("addressEntity")
    private DoctorEntity doctorEntity;
}
