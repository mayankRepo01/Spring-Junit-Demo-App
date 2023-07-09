package com.mayank.junitdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mayank.junitdemo.model.CloudVendor;
import com.mayank.junitdemo.service.CloudVendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CloudVendorController.class)
class CloudVendorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CloudVendorService cloudVendorService;
    CloudVendor cloudVendorOne;
    CloudVendor cloudVendorTwo;

    ObjectMapper objectMapper=new ObjectMapper();

    List<CloudVendor> cloudVendorList=new ArrayList<>();

    @BeforeEach
    void setUp() {
        cloudVendorOne=new CloudVendor("1","AWS","USA","XXXXXXXXX");
        cloudVendorOne=new CloudVendor("2","GCP","USA","XXXXXXXXX");
        cloudVendorList.add(cloudVendorOne);
        cloudVendorList.add(cloudVendorTwo);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetCloudVendorDetails() throws Exception {
        when(cloudVendorService.getCloudVendor("1")).thenReturn(cloudVendorOne);
        this.mockMvc.perform(get("/cloudvendor/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetAllCloudVendorDetails() throws Exception {
        when(cloudVendorService.getAllCloudVendors()).thenReturn(cloudVendorList);
        this.mockMvc.perform(get("/cloudvendor/")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testCreateCloudVendorDetails() throws Exception {
        when(cloudVendorService.createCloudVendor(cloudVendorOne)).thenReturn("Success");
        String requestJson=objectMapper.writeValueAsString(cloudVendorOne);
        this.mockMvc.perform(post("/cloudvendor/").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testUpdateCloudVendorDetails() throws Exception {
        when(cloudVendorService.updateCloudVendor(cloudVendorOne)).thenReturn("Success");
        String requestJson=objectMapper.writeValueAsString(cloudVendorOne);
        this.mockMvc.perform(put("/cloudvendor/").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testDeleteCloudVendorDetails() throws Exception {
        when(cloudVendorService.deleteCloudVendor("1")).thenReturn("Success");
        this.mockMvc.perform(delete("/cloudvendor/1")).andDo(print()).andExpect(status().isOk());
    }
}