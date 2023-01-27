package cl.ecommerce.productmanagement.repository;

import cl.ecommerce.productmanagement.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query(value = "SELECT p FROM Price p " +
            "WHERE p.brand.id = :brandId AND p.product.id = :productId " +
            "AND p.startDate <= :dateTime AND p.endDate >= :dateTime")
    List<Price> findAllByBrandIdAndProductIdAndDateRange(Long brandId, Long productId, LocalDateTime dateTime);

}
