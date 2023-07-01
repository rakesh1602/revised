package com.crossasyst.com.healthcare.controller;

import com.crossasyst.com.healthcare.entity.DoctorEntity;
import com.crossasyst.com.healthcare.model.Doctor;
import com.crossasyst.com.healthcare.response.RequestResponse;
import com.crossasyst.com.healthcare.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "v1")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(value = "/add-doctors", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestResponse> addDoctor(@RequestBody Doctor doctor){

        RequestResponse response = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/doctors/{doctorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> getDoctorDetailsById(@PathVariable Long doctorId){

        Doctor doctorById= doctorService.getDoctorById(doctorId);
        return new ResponseEntity<>(doctorById, HttpStatus.OK);
    }

    @GetMapping(value = "/doctors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DoctorEntity>> getAllDoctors(){

        List<DoctorEntity> allDoctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(allDoctors, HttpStatus.OK);
    }

    @DeleteMapping(value = "/doctors/{doctorId}")
    public void deleteDoctorById(@PathVariable Long doctorId ){

        doctorService.deleteByDoctorById(doctorId);
    }

    @PutMapping(value = "/doctor/{doctorId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> updateDoctorDetails(@RequestBody Doctor doctor, @PathVariable Long doctorId){
        Doctor updateDoctor = doctorService.updateDoctorById(doctor,doctorId);
        return new ResponseEntity<>(updateDoctor,HttpStatus.OK);
    }
}
