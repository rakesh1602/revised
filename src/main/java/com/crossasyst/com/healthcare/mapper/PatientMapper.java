package com.crossasyst.com.healthcare.mapper;

import com.crossasyst.com.healthcare.entity.PatientEntity;
import com.crossasyst.com.healthcare.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    
    PatientEntity modelToEntity (Patient patient);

    Patient entityToModel(PatientEntity patientEntity);
}
