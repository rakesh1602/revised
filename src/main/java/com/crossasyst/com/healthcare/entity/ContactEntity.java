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
@Table(name = "contact")
public class ContactEntity {

    @Id
    @SequenceGenerator(name = "seq_contact_id", sequenceName = "seq_contact_id", initialValue = 100000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_contact_id")
    private Long contactId;

    private String contactNumberOne;

    private String contactNumberTwo;

    private String contactType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id", nullable = false)
    @JsonIgnoreProperties("doctorEntity")
    private DoctorEntity doctorEntity;
}
