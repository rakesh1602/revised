package com.crossasyst.com.healthcare.controller;

import com.crossasyst.com.healthcare.entity.DoctorEntity;
import com.crossasyst.com.healthcare.entity.PatientEntity;
import com.crossasyst.com.healthcare.model.Patient;
import com.crossasyst.com.healthcare.response.RequestResponse;
import com.crossasyst.com.healthcare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(value = "/patients", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestResponse> addPatient(@RequestBody Patient patient){

        RequestResponse requestResponse=patientService.addPatient(patient);
        return new ResponseEntity<>(requestResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/patient/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> getPatient(@PathVariable Long patientId){

        Patient patient=patientService.getPatient(patientId);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping(value = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatientEntity>> getAllPatient(){

        List<PatientEntity> patientEntityList=patientService.getAllPatient();
        return new ResponseEntity<>(patientEntityList,HttpStatus.OK);
    }

    @PutMapping(value = "/patient/{patientId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> updatePatientDetails(@RequestBody Patient patient, @PathVariable Long patientId){

        patient=patientService.updatePatientDetails(patient, patientId);
        return new ResponseEntity<>(patient,HttpStatus.OK);
    }
}
