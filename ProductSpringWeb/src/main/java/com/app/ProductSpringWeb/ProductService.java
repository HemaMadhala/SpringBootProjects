package com.app.ProductSpringWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductDB db;
    public List<Product> printAll() {

        return db.findAll();
    }

    public Product findAllByName(String name) {

        return db.findAllByName(name);
    }
    public Product findbyname(String name){
        return db.findByName("Oneplus1");
    }

    public Product findByPlace(String place){
        return db.findByPlace(place);
    }

    public Optional<Product> findById(Integer id){
        return db.findById(id);
    }

    public void addProduct(Product p) {

        //this.list.add(p);
        db.save(p);
    }


















/*    Database d=new Database();

    public ProductService() {
    }


    public Product getProduct(String name) {
        Iterator var2 = this.list.iterator();

        Product p;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            p = (Product)var2.next();
        } while(!p.getName().equals(name));

        return p;
    }*/
}
