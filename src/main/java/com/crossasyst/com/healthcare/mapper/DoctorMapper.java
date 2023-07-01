package com.crossasyst.com.healthcare.mapper;

import com.crossasyst.com.healthcare.entity.DoctorEntity;
import com.crossasyst.com.healthcare.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    @Mapping(target = "addressEntity", source = "addressList")
    @Mapping(target = "contactEntity", source = "contactList")
    DoctorEntity modelToEntity(Doctor doctor);

    @Mapping(target = "addressList", source = "addressEntity")
    @Mapping(target = "contactList", source = "contactEntity")
    Doctor entityToModel(DoctorEntity doctorEntity);
}
