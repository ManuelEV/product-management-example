package cl.ecommerce.productmanagement.response;

import java.time.LocalDateTime;

public record PriceResponse(
        Long productId,
        Long brandId,
        int rate,
        LocalDateTime startDate,
        LocalDateTime endDate,
        float price
) {
}
