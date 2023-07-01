package com.crossasyst.com.healthcare.service;

import com.crossasyst.com.healthcare.entity.AddressEntity;
import com.crossasyst.com.healthcare.entity.ContactEntity;
import com.crossasyst.com.healthcare.entity.DoctorEntity;
import com.crossasyst.com.healthcare.mapper.DoctorMapper;
import com.crossasyst.com.healthcare.model.Doctor;
import com.crossasyst.com.healthcare.repository.DoctorRepository;
import com.crossasyst.com.healthcare.response.RequestResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class DoctorService {

    private final DoctorMapper doctorMapper;

    private final DoctorRepository doctorRepository;

    private DoctorEntity doctorEntity = new DoctorEntity();

    private final RequestResponse requestResponse = new RequestResponse();

    private Doctor doctor;

    @Autowired
    public DoctorService(DoctorMapper doctorMapper, DoctorRepository doctorRepository) {
        this.doctorMapper = doctorMapper;
        this.doctorRepository = doctorRepository;
    }

    public RequestResponse addDoctor(Doctor doctor) {

        log.info(() -> "Adding doctor details.");

        try {
            doctorEntity = doctorMapper.modelToEntity(doctor);

            List<AddressEntity> addressEntities = doctorEntity.getAddressEntity();
            if (addressEntities != null) {
                for (AddressEntity addressEntity : addressEntities) {
                    addressEntity.setDoctorEntity(doctorEntity);
                }
            }

            List<ContactEntity> contactEntities = doctorEntity.getContactEntity();
            if (contactEntities != null) {
                for (ContactEntity contactEntity : contactEntities) {
                    contactEntity.setDoctorEntity(doctorEntity);
                }
            }

            doctorRepository.save(doctorEntity);
            log.info(() -> "Doctor details saved successfully.");
        } catch (Exception exception) {
            log.error("Exception: {} ", exception::getMessage);
            throw new RuntimeException("Failed to add doctor details", exception);
        }

        requestResponse.setResponseId(doctorEntity.getDoctorId());
        log.info("Doctor id :- {}", requestResponse.getResponseId());
        return requestResponse;
    }

    public Doctor getDoctorById(Long doctorId) {

        log.info("Retrieving doctor info for doctor id {}", doctorId);

        Optional<DoctorEntity> optionalDoctorEntity = Optional.ofNullable(doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor details for doctor id " + doctorId + " not found")));

        if (optionalDoctorEntity.isPresent()) {
            doctor = doctorMapper.entityToModel(optionalDoctorEntity.get());
            log.info("Returning doctors details for doctorId {}", doctorId);
        }
        return doctor;
    }

    public List<DoctorEntity> getAllDoctors() {

        log.info("Retrieving all doctors details");
        List<DoctorEntity> doctors = doctorRepository.findAll();
        return Optional.ofNullable(doctors)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new RuntimeException("No doctors found"));
    }

    public void deleteByDoctorById(Long doctorId) {

        log.info("Deleting doctor details for doctor id {}", doctorId);
        doctorRepository.deleteById(doctorId);
    }

    public Doctor updateDoctorById(Doctor updatedDoctor, Long doctorId) {
        log.info("Updating doctor details for doctor id {}", doctorId);

        Optional<DoctorEntity> optionalDoctorEntity = doctorRepository.findById(doctorId);
        DoctorEntity existingDoctorEntity = optionalDoctorEntity.orElseThrow(() -> new RuntimeException("Doctor details for doctor id " + doctorId + " not found"));

        // Retrieve the original AddressEntity and ContactEntity lists
        List<AddressEntity> originalAddressEntities = existingDoctorEntity.getAddressEntity();
        List<ContactEntity> originalContactEntities = existingDoctorEntity.getContactEntity();

        log.info("Update the DoctorEntity with the new values from the updated Doctor model");
        DoctorEntity updatedDoctorEntity = doctorMapper.modelToEntity(updatedDoctor);
        updatedDoctorEntity.setDoctorId(existingDoctorEntity.getDoctorId());

        log.info("Retrieve the updated AddressEntity and ContactEntity lists from the updated Doctor model");
        List<AddressEntity> updatedAddressEntities = updatedDoctorEntity.getAddressEntity();
        List<ContactEntity> updatedContactEntities = updatedDoctorEntity.getContactEntity();

        log.info("Set the DoctorEntity reference for each AddressEntity in the updated list");
        if (updatedAddressEntities != null) {
            for (AddressEntity addressEntity : updatedAddressEntities) {
                addressEntity.setDoctorEntity(updatedDoctorEntity);
            }
        }

        log.info("Set the DoctorEntity reference for each ContactEntity in the updated list");
        if (updatedContactEntities != null) {
            for (ContactEntity contactEntity : updatedContactEntities) {
                contactEntity.setDoctorEntity(updatedDoctorEntity);
            }
        }

        try {
            // Save the updated DoctorEntity
            DoctorEntity savedDoctorEntity = doctorRepository.save(updatedDoctorEntity);
            log.info("Doctor details updated successfully for doctor id {}", doctorId);

            // Set the updated AddressEntity and ContactEntity lists back to the existingDoctorEntity
            savedDoctorEntity.setAddressEntity(updatedAddressEntities);
            savedDoctorEntity.setContactEntity(updatedContactEntities);

            // Map the updated DoctorEntity to the Doctor model and return it
            return doctorMapper.entityToModel(savedDoctorEntity);
        } catch (Exception exception) {
            // Revert the DoctorEntity back to the original lists on failure
            existingDoctorEntity.setAddressEntity(originalAddressEntities);
            existingDoctorEntity.setContactEntity(originalContactEntities);
            throw new RuntimeException("Failed to update doctor details for doctor id " + doctorId, exception);
        }
    }


}
