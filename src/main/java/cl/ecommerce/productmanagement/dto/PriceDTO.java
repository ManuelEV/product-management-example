package cl.ecommerce.productmanagement.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PriceDTO(
        @NotNull LocalDateTime dateTime,
        @NotNull Long productId,
        @NotNull Long brandId
) {
}
