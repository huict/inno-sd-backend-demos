package nl.hu.sd.inno.shop.presentation;

import nl.hu.sd.inno.shop.data.ProductRepository;
import nl.hu.sd.inno.shop.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository products;

    public ProductController(ProductRepository products) {
        this.products = products;
    }

    public record ProductDto(Long id, String name, int nrAvailable) {
        public static ProductDto fromProduct(Product product) {
            return new ProductDto(product.getId(), product.getName(), product.getNrAvailable());
        }
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> results = this.products.findAll().stream().map(ProductDto::fromProduct).toList();
        return ResponseEntity.ok(results);
    }

}
