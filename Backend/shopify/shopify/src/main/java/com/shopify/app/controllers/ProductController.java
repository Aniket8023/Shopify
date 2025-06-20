package com.shopify.app.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopify.app.entity.Product;
import com.shopify.app.services.ProductService;


@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping("/create")
	public ResponseEntity<String> saveProduct( @RequestParam("product") String productJson, @RequestParam("image") MultipartFile image)
			 {
//		System.out.println("Product add API Call...");
		try {
//			System.out.println("Inside Try Block...");
			ObjectMapper objectMapper = new ObjectMapper();
            Product product = objectMapper.readValue(productJson, Product.class);
//            System.out.println("After Product Object retrive...");
            // Process image and save the product logic here...
//            System.out.println("Product Name: " + product.getProductTitle());
//            System.out.println("Image: " + image.getOriginalFilename());
            
            service.saveProduct(product.getProductTitle(), product.getOriginalPrice(), product.getDiscountPercentage(), product.getDescription(),product.getRatings(), product.getProductQuantity(),
            		product.getProductCategory(), image);
            
			return ResponseEntity.ok("Product saved successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Failed To save product");
		}

	}

	@GetMapping("get-all")
	public List<Product> getAllProducts() {
		List<Product> list = null;
		try {

			list = service.getAllProducts();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Product> getProductByProductId(@PathVariable("id") long id) {
		Product product = null;
		try {

			product = service.getProductByProductId(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(product);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateProduct( @RequestParam("product") String productJson, @RequestParam("image") MultipartFile image)
			throws IOException {

		try {
			ObjectMapper objectMapper = new ObjectMapper();
            Product product = objectMapper.readValue(productJson, Product.class);

			service.updateProduct(product.getId(), product.getProductTitle(), product.getOriginalPrice(), product.getDiscountPercentage(), product.getDescription(), product.getProductQuantity(),
					product.getProductCategory(), image);
			return ResponseEntity.ok("Product updated successfully");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed To updated product");
		}

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") long id) {
		try {

			service.deleteProduct( id ) ; 
			return ResponseEntity.ok("Product deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed To delete product");
		}
	}

}
