package com.neyas.spring5webfluxrest.controllers;

import com.neyas.spring5webfluxrest.domain.Vendor;
import com.neyas.spring5webfluxrest.repository.VendorRespository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/vendors")
    public Mono<Void> create(@RequestBody Publisher<Vendor> vendorStream) {
        return vendorRespository.saveAll(vendorStream).then();
    }

    @PutMapping("/api/v1/vendors/{id}")
    public Mono<Vendor> update(String id, Vendor vendor){
        vendor.setId(id);
        return vendorRespository.save(vendor);
    }
}
