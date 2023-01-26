package cl.ecommerce.productmanagement.repository;

import cl.ecommerce.productmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
