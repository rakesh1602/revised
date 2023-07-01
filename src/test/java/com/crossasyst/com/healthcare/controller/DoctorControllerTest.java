package com.crossasyst.com.healthcare.controller;

import com.crossasyst.com.healthcare.entity.DoctorEntity;
import com.crossasyst.com.healthcare.model.Doctor;
import com.crossasyst.com.healthcare.response.RequestResponse;
import com.crossasyst.com.healthcare.service.DoctorService;
import com.crossasyst.com.healthcare.utils.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DoctorControllerTest {

    private static final String ADD_DOCTOR_URL = "/add-doctors";
    private static final String GET_DOCTOR_BY_ID_URL = "/doctors/{doctorId}";
    private static final String GET_ALL_DOCTORS_URL = "/doctors";
    @InjectMocks
    private DoctorController doctorController;
    @Mock
    private DoctorService doctorService;
    private MockMvc mockMvc;
    private DoctorEntity doctorEntity;
    private Doctor doctor = new Doctor();
    private RequestResponse requestResponse;
    @Autowired
    private JacksonTester<Doctor> jsonPostDoctorRequest;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(doctorController).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void addDoctor() throws Exception {
        doctorEntity = TestUtils.doctorEntity();
        doctor = TestUtils.doctor();
        requestResponse = TestUtils.requestResponse();

        String addDoctorUrl = ADD_DOCTOR_URL;

        mockMvc.perform(MockMvcRequestBuilders
                        .post(addDoctorUrl)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonPostDoctorRequest.write(doctor).getJson())
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        when(doctorService.addDoctor(doctor)).thenReturn(requestResponse);

        ResponseEntity<RequestResponse> response = doctorController.addDoctor(doctor);

        verify(doctorService, times(1)).addDoctor(doctor);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(doctorEntity.getDoctorId(), response.getBody().getResponseId());
    }

    @Test
    void getDoctorDetailsById() throws Exception {
        doctorEntity = TestUtils.doctorEntity();
        doctor = TestUtils.doctor();
        requestResponse = TestUtils.requestResponse();

        String getDoctorByIdUrl = GET_DOCTOR_BY_ID_URL;
        Long doctorId = 1L;

        mockMvc.perform(MockMvcRequestBuilders
                        .get(getDoctorByIdUrl, doctorId)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        when(doctorService.getDoctorById(requestResponse.getResponseId())).thenReturn(doctor);

        ResponseEntity<Doctor> response = doctorController.getDoctorDetailsById(requestResponse.getResponseId());

        verify(doctorService, times(2)).getDoctorById(requestResponse.getResponseId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response);
    }

    @Test
    void getAllDoctors() throws Exception {
        List<DoctorEntity> doctorEntityList = new ArrayList<>();
        doctorEntityList.add(TestUtils.doctorEntity());

        String getAllDoctorUrl = GET_ALL_DOCTORS_URL;

        mockMvc.perform(MockMvcRequestBuilders.get(getAllDoctorUrl)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        when(doctorService.getAllDoctors()).thenReturn(doctorEntityList);

        ResponseEntity<List<DoctorEntity>> response = doctorController.getAllDoctors();
        verify(doctorService, times(2)).getAllDoctors();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(doctorEntityList, response.getBody());
    }

    @Test
    void deleteDoctorById() throws Exception {

        Long doctorId = 1L;

        String deleteUrl = GET_DOCTOR_BY_ID_URL;

        mockMvc.perform(MockMvcRequestBuilders.delete(deleteUrl, doctorId))
                .andExpect(status().isOk());

        doctorController.deleteDoctorById(doctorId);

        verify(doctorService, times(2)).deleteByDoctorById(doctorId);
    }
}