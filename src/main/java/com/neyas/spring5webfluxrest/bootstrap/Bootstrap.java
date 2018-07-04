package com.neyas.spring5webfluxrest.bootstrap;

import com.neyas.spring5webfluxrest.domain.Category;
import com.neyas.spring5webfluxrest.domain.Vendor;
import com.neyas.spring5webfluxrest.repository.CategoryRepository;
import com.neyas.spring5webfluxrest.repository.VendorRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Bootstrap implements CommandLineRunner {

    private final VendorRespository vendorRespository;
    private final CategoryRepository categoryRepository;

    public Bootstrap(VendorRespository vendorRespository, CategoryRepository categoryRepository) {
        this.vendorRespository = vendorRespository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        populateData();
    }

    private void populateData() {

        if(categoryRepository.count().block() == 0) {

            log.info("Starting to populate the data");

            categoryRepository.save(Category
                    .builder()
                    .description("Fruits")
                    .build()).block();
            categoryRepository.save(Category
                    .builder()
                    .description("Nuts")
                    .build()).block();
            categoryRepository.save(Category
                    .builder()
                    .description("Breads")
                    .build()).block();
            categoryRepository.save(Category
                    .builder()
                    .description("Meats")
                    .build()).block();
            categoryRepository.save(Category
                    .builder()
                    .description("Eggs")
                    .build()).block();

            System.out.println("Loaded Categories" + categoryRepository.count().block());

            vendorRespository.save(Vendor
                    .builder()
                    .firstName("Joe").lastName("Buck")
                    .build()).block();

            vendorRespository.save(Vendor
                    .builder()
                    .firstName("Joe").lastName("Buck")
                    .build()).block();
            vendorRespository.save(Vendor
                    .builder()
                    .firstName("Micheal").lastName("Weston")
                    .build()).block();
            vendorRespository.save(Vendor
                    .builder()
                    .firstName("Jessie").lastName("Waters")
                    .build()).block();
            vendorRespository.save(Vendor
                    .builder()
                    .firstName("Bill").lastName("Nershi")
                    .build()).block();
            vendorRespository.save(Vendor
                    .builder()
                    .firstName("Jimmy").lastName("Buffet")
                    .build()).block();

            System.out.println("Loaded Vendors " + vendorRespository.count().block());
        }
    }
}
