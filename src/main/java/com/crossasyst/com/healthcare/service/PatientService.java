package com.crossasyst.com.healthcare.service;

import com.crossasyst.com.healthcare.entity.PatientEntity;
import com.crossasyst.com.healthcare.mapper.PatientMapper;
import com.crossasyst.com.healthcare.model.Patient;
import com.crossasyst.com.healthcare.repository.PatientRepository;
import com.crossasyst.com.healthcare.response.RequestResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PatientService {

    private final PatientRepository patientRepository;

    private final PatientMapper patientMapper;

    private PatientEntity patientEntity=new PatientEntity();

    private RequestResponse requestResponse=new RequestResponse();

    private Patient patient;

    @Autowired
    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public RequestResponse addPatient(Patient patient) {

        log.info("Adding patient details.");

        try {
            patientEntity = patientMapper.modelToEntity(patient);
            patientRepository.save(patientEntity);

            log.info("Patient details saved successfully.");
        } catch (Exception exception) {
            log.error("Failed to saved patient details.");
            throw new RuntimeException("Failed to save patient details " + exception.getMessage());
        }
        requestResponse.setResponseId(patientEntity.getPatientId());
        log.info("Patient id is {}", requestResponse.getResponseId());
        return requestResponse;
    }

    public Patient getPatient(Long patientId) {

        log.info("Retrieving patient details for patient id {}", patientId);

        Optional<PatientEntity> optionalPatientEntity = Optional.ofNullable(patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient details of patient id " + patientId + "not found")));

        if (optionalPatientEntity.isPresent()) {
            patient = patientMapper.entityToModel(patientEntity);
            log.info("Returning patient details for patient id {}", patientId);
        }
        return patient;


    }

    public List<PatientEntity> getAllPatient() {

        log.info("Retrieving all patient details.");
        List<PatientEntity> patientEntityList = patientRepository.findAll();
        return Optional.ofNullable(patientEntityList)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new RuntimeException("Patients not found"));
    }

    public Patient updatePatientDetails(Patient patient, Long patientId) {

        log.info("Retrieving patient details for patient id {}", patientId);

        Optional<PatientEntity> optionalPatientEntity = Optional.ofNullable(patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient details of patient id " + patientId + "not found")));

        Long currentPatientId = optionalPatientEntity.get().getPatientId();

        PatientEntity patientEntities;

        try {
            if (optionalPatientEntity.isPresent()) {
                patientEntities = patientMapper.modelToEntity(patient);
                patientEntities.setPatientId(currentPatientId);
                patientRepository.save(patientEntities);
                log.info("Patient details updated successfully.");
            }
        } catch (Exception exception) {
            log.error("Failed to update patient details.");
            throw new RuntimeException("Failed to update patient details of patient id " + patientId);
        }

        return patient;
    }
}
