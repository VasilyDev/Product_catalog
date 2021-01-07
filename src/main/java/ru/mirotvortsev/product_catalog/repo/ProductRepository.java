package ru.mirotvortsev.product_catalog.repo;

import org.springframework.data.repository.CrudRepository;
import ru.mirotvortsev.product_catalog.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
