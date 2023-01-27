package cl.ecommerce.productmanagement.controller;

import cl.ecommerce.productmanagement.dto.PriceDTO;
import cl.ecommerce.productmanagement.response.PriceResponse;
import cl.ecommerce.productmanagement.service.PricesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("prices")
@RequiredArgsConstructor
public class PricesController {

    private final PricesService pricesService;

    @GetMapping("")
    public  ResponseEntity<PriceResponse> getPrice(@Valid PriceDTO priceDTO) {
        return ResponseEntity.ok(pricesService.getPrice(priceDTO));
    }

}
