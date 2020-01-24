package tn.gc.demo4.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.gc.demo4.Model.Product;
import tn.gc.demo4.Repository.ProductRepository;

import java.util.List;

@RestController
public class ProductRest {
    @Autowired
    ProductRepository productRepository;


    @GetMapping(value = "/products")
    public List<Product> getProducts(@RequestParam(value = "nom",required = false) String nom){
        if(nom!=null)
            return productRepository.findAllByNameContains(nom);
        else return productRepository.jibhom();
    }
    @GetMapping(value = "/create-product/{name}")
    public void createProduct(@PathVariable(value = "name") String name){
        Product product=new Product(name);

        productRepository.save(product);
    }
    @PostMapping(value = "/post-product")
    public Product newProduct(@RequestBody Product newProduct){
        return productRepository.save(newProduct);
    }
    @PutMapping(value="/products/{id}")
    public Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id){
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    return productRepository.save(product);
                 })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return productRepository.save(newProduct);
                });
    }
    @DeleteMapping(value="/product/{id}")
    public void deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
    }
}
