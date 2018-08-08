package system.repository;

import org.springframework.data.repository.CrudRepository;
import system.domain.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product , Long> {

    Product findByProductCode(String code);
    Product findBySecureId(String secureId);
    List<Product> findAll();
}