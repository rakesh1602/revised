package com.crossasyst.com.healthcare.service;

import com.crossasyst.com.healthcare.entity.DoctorEntity;
import com.crossasyst.com.healthcare.mapper.DoctorMapper;
import com.crossasyst.com.healthcare.model.Doctor;
import com.crossasyst.com.healthcare.repository.DoctorRepository;
import com.crossasyst.com.healthcare.response.RequestResponse;
import com.crossasyst.com.healthcare.utils.TestUtils;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.MockUtil;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private DoctorMapper doctorMapper;

    private DoctorEntity doctorEntity;
    private Doctor doctor = new Doctor();
    private RequestResponse requestResponse;

    @Test
    void testDoctorAddSuccess() {

        doctorEntity = TestUtils.doctorEntity();
        doctor = TestUtils.doctor();
        requestResponse = TestUtils.requestResponse();

        //Mock the mapper
        when(doctorMapper.modelToEntity(doctor)).thenReturn(doctorEntity);

        //Mock the Repository
        when(doctorRepository.save(doctorEntity)).thenReturn(doctorEntity);

        RequestResponse response = doctorService.addDoctor(doctor);

        verify(doctorMapper, times(1)).modelToEntity(doctor);
        verify(doctorRepository, times(1)).save(doctorEntity);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(doctorEntity.getDoctorId(), response.getResponseId());
    }

    @Test
    void getDoctorById() {
        doctorEntity = TestUtils.doctorEntity();
        doctor = TestUtils.doctor();
        requestResponse = TestUtils.requestResponse();

        when(doctorRepository.findById(anyLong())).thenReturn(Optional.of(doctorEntity));
        when(doctorMapper.entityToModel(doctorEntity)).thenReturn(doctor);

        Doctor result = doctorService.getDoctorById(requestResponse.getResponseId());

        verify(doctorRepository, times(1)).findById(1L);
        verify(doctorMapper, times(1)).entityToModel(doctorEntity);

        assertEquals("Sachin", result.getFirstName());
        assertNotNull(result);
    }

    @Test
    void getAllDoctors() {
        doctorEntity = TestUtils.doctorEntity();
        doctor = TestUtils.doctor();
        requestResponse = TestUtils.requestResponse();

        when(doctorRepository.findAll()).thenReturn(List.of(doctorEntity));

        List<DoctorEntity> response = doctorService.getAllDoctors();

        verify(doctorRepository, times(1)).findAll();

        assertNotNull(response);
    }


    @Test
    public void testDeleteByDoctorById() {
        Long doctorId = 123L;

        doctorService.deleteByDoctorById(doctorId);

        verify(doctorRepository, times(1)).deleteById(doctorId);
    }
}