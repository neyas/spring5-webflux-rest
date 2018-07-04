package com.neyas.spring5webfluxrest.repository;

import com.neyas.spring5webfluxrest.domain.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VendorRespository extends ReactiveMongoRepository<Vendor, String> {
}
