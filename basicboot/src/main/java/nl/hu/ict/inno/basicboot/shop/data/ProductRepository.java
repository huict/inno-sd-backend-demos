package nl.hu.ict.inno.basicboot.shop.data;

import nl.hu.ict.inno.basicboot.shop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
