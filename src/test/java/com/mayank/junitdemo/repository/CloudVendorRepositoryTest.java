package com.mayank.junitdemo.repository;

import com.mayank.junitdemo.model.CloudVendor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class CloudVendorRepositoryTest {

    @Autowired
    private CloudVendorRepository cloudVendorRepository;

    CloudVendor cloudVendor;

    @BeforeEach
    void setUp() {
        cloudVendor=new CloudVendor("1","Amazon","USA","xxxxxx");
        cloudVendorRepository.save(cloudVendor);
    }

    @AfterEach
    void tearDown() {
        cloudVendor=null;
        cloudVendorRepository.deleteAll();
    }

    // Test case Success
    @Test
    void testFindByVendorName_Found(){
        List<CloudVendor> cloudVendorList=cloudVendorRepository.findByVendorName("Amazon");
        assertThat(cloudVendorList.get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
        assertThat(cloudVendorList.get(0).getVendorAddress()).isEqualTo(cloudVendor.getVendorAddress());
    }

    // Test case Failure

    @Test
    void testFindByVendorName_NotFound(){
        List<CloudVendor> cloudVendorList=cloudVendorRepository.findByVendorName("GCP");
        assertThat(cloudVendorList.isEmpty()).isTrue();
    }
}
