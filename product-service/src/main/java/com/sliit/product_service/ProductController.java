package com.sliit.product_service;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private final ProductRepository productRepository;

	public ProductController(ProductRepository productRepository ) {
		this.productRepository = productRepository;
	}
	
	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@GetMapping
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Long id) {
		return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not Found."));
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		productRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public Product updateProduct(@RequestBody Product updateProduct, @PathVariable Long id) {
		Product existingProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not Found."));
		
		existingProduct.setName(updateProduct.getName());
		existingProduct.setPrice(updateProduct.getPrice());
		
		return productRepository.save(existingProduct);
	}

}
