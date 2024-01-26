package nl.hu.sd.inno.shop.data;

import nl.hu.sd.inno.shop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
