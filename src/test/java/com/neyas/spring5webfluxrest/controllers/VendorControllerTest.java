package com.neyas.spring5webfluxrest.controllers;

import com.neyas.spring5webfluxrest.domain.Vendor;
import com.neyas.spring5webfluxrest.repository.VendorRespository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class VendorControllerTest {

    VendorRespository vendorRespository;
    VendorController vendorController;

    WebTestClient webTestClient;

    @Before
    public void setUp() throws Exception {
        vendorRespository = Mockito.mock(VendorRespository.class);

        vendorController = new VendorController(vendorRespository);

        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    public void list() {
        BDDMockito.given(vendorRespository.findAll())
                .willReturn(
                        Flux.just(
                                Vendor.builder()
                                        .firstName("Test First Name 1")
                                        .lastName("Test Last Name 1").build(),
                                Vendor.builder()
                                        .firstName("Test First Name 2")
                                        .lastName("Test Last Name 2")
                                        .build()));

        webTestClient.get()
                .uri("/api/v1/vendors")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    public void getById() {
        BDDMockito.given(vendorRespository.findById(ArgumentMatchers.anyString()))
                .willReturn(
                        Mono.just(
                                Vendor.builder()
                                        .firstName("Test First Name")
                                        .lastName("Test Last Name")
                                        .build()));

        webTestClient.get()
                .uri("/api/v1/vendors/someid")
                .exchange()
                .expectBody(Vendor.class);
    }
}