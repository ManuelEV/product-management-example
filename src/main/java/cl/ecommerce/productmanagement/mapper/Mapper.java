package cl.ecommerce.productmanagement.mapper;

import cl.ecommerce.productmanagement.model.Price;
import cl.ecommerce.productmanagement.response.PriceResponse;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public PriceResponse toPriceResponse(Price price) {
        return new PriceResponse(
                price.getProduct().getId(),
                price.getBrand().getId(),
                price.getPriority(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice()
        );
    }

}
