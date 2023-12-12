package quinbay.Inventory.dao.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import quinbay.Inventory.model.entity.Product;
import java.util.*;
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    List<Product> findAll();



    @Override
    Product save(Product entity);
    List<Product> findByName(String name);
    List<Product> findByCategory(int category);

    @Override
    void delete(Product entity);



}
