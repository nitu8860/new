package com.example.Mct.service;
import com.example.Mct.model.Product;
import com.example.Mct.exception.ResourceNotFoundException;
import com.example.Mct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product getProductById(int id){
        return productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product","id",Integer.toString(id)));

    }
    public Product createProduct(Product product){
        return productRepository.save(product);
    }
    public Product updateProduct(int id,Product product){
        Product existingProduct=productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product","id",Integer.toString(id)));
        existingProduct.setTitle(product.getTitle());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", Integer.toString(id)));
        productRepository.delete(product);
    }
}
