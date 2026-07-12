package ma.ensetm.orm.repository;

import ma.ensetm.orm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByPriceGreaterThan(double price);

    List<Product> findByQuantityLessThan(int quantity);

}
