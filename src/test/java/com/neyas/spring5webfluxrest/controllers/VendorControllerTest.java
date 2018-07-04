package com.neyas.spring5webfluxrest.controllers;

import com.neyas.spring5webfluxrest.domain.Vendor;
import com.neyas.spring5webfluxrest.repository.VendorRespository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

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

    @Test
    public void create() {
        BDDMockito.given(vendorRespository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(Vendor.builder().build()));

        Mono<Vendor> vendorMono = Mono.just(Vendor.builder().build());

        webTestClient.post()
                .uri("/api/v1/vendors")
                .body(vendorMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    public void update() {
        BDDMockito.given(vendorRespository.save(any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().build()));

        Mono<Vendor> vendorMono = Mono.just(Vendor.builder().build());

        webTestClient.put()
                .uri("/api/v1/vendors/asfasdfsadf")
                .body(vendorMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isOk();

    }
}