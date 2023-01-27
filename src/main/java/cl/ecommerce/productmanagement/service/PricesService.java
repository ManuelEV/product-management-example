package cl.ecommerce.productmanagement.service;

import cl.ecommerce.productmanagement.dto.PriceDTO;
import cl.ecommerce.productmanagement.response.PriceResponse;

public interface PricesService {
    PriceResponse getPrice(PriceDTO priceDTO);
}
