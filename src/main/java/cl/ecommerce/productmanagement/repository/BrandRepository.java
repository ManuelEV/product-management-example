package cl.ecommerce.productmanagement.repository;

import cl.ecommerce.productmanagement.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
