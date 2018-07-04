package com.neyas.spring5webfluxrest.controllers;

import com.neyas.spring5webfluxrest.domain.Vendor;
import com.neyas.spring5webfluxrest.repository.VendorRespository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class VendorController {
    private final VendorRespository vendorRespository;

    public VendorController(VendorRespository vendorRespository) {
        this.vendorRespository = vendorRespository;
    }

    @GetMapping("/api/v1/vendors")
    public Flux<Vendor> list() {
        return vendorRespository.findAll();
    }

    @GetMapping("/api/v1/vendors/{id}")
    public Mono<Vendor> getById(@PathVariable String id) {
        return vendorRespository.findById(id);
    }
}
