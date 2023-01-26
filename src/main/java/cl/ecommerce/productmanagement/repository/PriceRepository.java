package cl.ecommerce.productmanagement.repository;

import cl.ecommerce.productmanagement.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
