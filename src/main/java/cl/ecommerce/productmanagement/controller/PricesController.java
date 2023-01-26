package cl.ecommerce.productmanagement.controller;

import cl.ecommerce.productmanagement.model.Brand;
import cl.ecommerce.productmanagement.model.Price;
import cl.ecommerce.productmanagement.model.Product;
import cl.ecommerce.productmanagement.repository.BrandRepository;
import cl.ecommerce.productmanagement.repository.PriceRepository;
import cl.ecommerce.productmanagement.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("prices")
@RequiredArgsConstructor
@Slf4j
public class PricesController {

    private final BrandRepository brandRepository;
    private final PriceRepository priceRepository;
    private final ProductRepository productRepository;

    @PostMapping("test")
    public ResponseEntity<?> test() {

        log.info("products: {}", productRepository.findAll());

        log.info("brands: {}", brandRepository.findAll());

        log.info("prices: {}", priceRepository.findAll());

        return ResponseEntity.ok(priceRepository.findAll());
    }


}
