package com.crossasyst.com.healthcare.utils;

import com.crossasyst.com.healthcare.entity.AddressEntity;
import com.crossasyst.com.healthcare.entity.ContactEntity;
import com.crossasyst.com.healthcare.entity.DoctorEntity;
import com.crossasyst.com.healthcare.model.Address;
import com.crossasyst.com.healthcare.model.Contact;
import com.crossasyst.com.healthcare.model.Doctor;
import com.crossasyst.com.healthcare.response.RequestResponse;

import java.sql.Date;

public class TestUtils {

    public static Doctor doctor(){
        Doctor doctor = new Doctor();
        doctor.setFirstName("Sachin");
        doctor.setLastName("Tendulkar");
        doctor.setMiddleName("Ramesh");
        doctor.setEducation("MBBS");

        Address address=new Address();
        address.setCity("mumbai");
        address.setCountry("india");
        address.setState("maharashtra");
        address.setZipcode("400079");
        address.setFirstLine("first line");
        address.setSecondLine("second line");

        Contact contact=new Contact();
        contact.setContactNumberOne("9817123243");
        contact.setContactNumberTwo("9812323454");
        contact.setContactType("personal");
        return doctor;
    }

    public static DoctorEntity doctorEntity(){
        DoctorEntity doctor = new DoctorEntity();
        doctor.setDoctorId(1L);
        doctor.setFirstName("Sachin");
        doctor.setLastName("tendulkar");
        doctor.setMiddleName("Ramesh");
        doctor.setEducation("MBBS");

        AddressEntity address=new AddressEntity();
        address.setCity("mumbai");
        address.setCountry("india");
        address.setState("maharashtra");
        address.setZipcode("400079");
        address.setFirstLine("first line");
        address.setSecondLine("second line");

        ContactEntity contact=new ContactEntity();
        contact.setContactNumberOne("9817123243");
        contact.setContactNumberTwo("9812323454");
        contact.setContactType("personal");
        return doctor;
    }

    public static RequestResponse requestResponse(){
        RequestResponse response=new RequestResponse();
        response.setResponseId(1L);
        return response;
    }
}
