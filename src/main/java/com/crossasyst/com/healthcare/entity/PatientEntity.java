package com.crossasyst.com.healthcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
public class PatientEntity {

    @Id
    @SequenceGenerator(name = "seq_patient_id", sequenceName = "seq_patient_id", initialValue = 10100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_patient_id")
    private Long patientId;
    private String firstName;

    private String lastName;

    private String gender;

    private Date dateOfBirth;
}
