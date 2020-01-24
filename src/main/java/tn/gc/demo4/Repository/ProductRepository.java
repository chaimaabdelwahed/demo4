package tn.gc.demo4.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.gc.demo4.Model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByNameContains(String filter);
    @Query("SELECT product from Product product")
    List<Product> jibhom();
}
