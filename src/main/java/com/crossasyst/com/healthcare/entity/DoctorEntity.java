package com.crossasyst.com.healthcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctor")
public class DoctorEntity {

    @Id
    @SequenceGenerator(name = "seq_doctor_id", sequenceName = "seq_doctor_id", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_doctor_id")
    private Long doctorId;
    private String firstName;

    private String middleName;

    private String lastName;

    private String education;

    private Date startDateOfPracticing;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctorEntity", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("doctorEntity")
    private List<AddressEntity> addressEntity;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "doctorEntity", fetch =FetchType.EAGER )
    @JsonIgnoreProperties("doctorEntity")
    private List<ContactEntity> contactEntity;
}
