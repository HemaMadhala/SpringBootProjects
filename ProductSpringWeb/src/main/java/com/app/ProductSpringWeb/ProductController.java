package com.app.ProductSpringWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    ProductService ps;
    @GetMapping("/home")
    public String home(){
        System.out.println("This is home");
        return "home.jsp";
    }
    @GetMapping("/products")
    public List<Product> printAll() {
        return ps.printAll();
    }

    @GetMapping("/products/{name}")
    public Product getProduct(@PathVariable String name){
        return ps.db.findByName(name);
    }
    /*@GetMapping("/products/name")
    public Product getProduct(String name){
        return ps.db.findByName("Oneplus2");
    }*/
    @GetMapping("/products/find/{place}")
    public Product findByPlace(@PathVariable String place){
        return ps.findByPlace(place);
    }

    /*@GetMapping("/products/id")
    public Optional<Product> findById() {
        return ps.findById(2);
    }*/

    @PostMapping("/products")
    public void addProduct(@RequestBody Product p){
        ps.addProduct(p);
    }
}
