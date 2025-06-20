package com.shopify.app.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.apache.catalina.WebResourceRoot.ArchiveIndexStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shopify.app.entity.Product;
import com.shopify.app.entity.Role;
import com.shopify.app.entity.User;
import com.shopify.app.repositories.ProductRepository;
import com.shopify.app.repositories.UserRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Value("${file.upload-dir}")
	private String uploadDir;

	private static final String BASE_URL = "http://localhost:8080/images/";

	public void saveProduct(String productTitle, double originalPrice, int discountPercentage,
			String description,int ratings ,int productQuantity, String productCategory, MultipartFile image) throws IOException {

		// create uploads folder if not exist
		File uploadfolder = new File(uploadDir);

		if (!uploadfolder.exists()) {
			uploadfolder.mkdir();
		}

		// MultipartFile -> image data , image name , image type
		byte[] imageData = image.getBytes();
		String imageName = image.getOriginalFilename(); // sample.jpg
		String imageType = image.getContentType();

		// check the image type is valid or not
		if (imageType == null || !imageType.startsWith("image/")) {
			throw new IllegalArgumentException("Invalid Image type");
		}

		// Create FilePath ( uploads , file-serve(Angular) )
		// Generate Random ID for Image
		// 31573575132653712537_sample.jpg
		String newImageName = UUID.randomUUID() + "_" + imageName;

		// uploads/31573575132653712537_sample.jpg
		Path uploadImagePath = Paths.get(uploadDir, newImageName);

		// http://localhost:8080/images/31573575132653712537_sample.jpg - Angular
		// Project
		String imageServePath = BASE_URL + newImageName;

		// save file in uploads folder
		Files.write(uploadImagePath, imageData);

		// Calculate Selling Price
		double sellingPrice = calculateSellingPrice(originalPrice, discountPercentage);

		

		// create product entity object
		Product product = new Product(productTitle, originalPrice, sellingPrice, discountPercentage, description,
				productQuantity, productCategory, imageServePath, newImageName, imageType, ratings);

		// calling repository saveProductMethod to save product into database.
		repository.saveProduct(product);

	}

	public List<Product> getAllProducts() {
		List<Product> list = null;
		try {
			list = repository.getAllProducts();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public Product getProductByProductId(long id) {
		Product product = null;
		try {
			product = repository.getProductByProductId(id);
		} catch (Exception e) {

		}
		return product;
	}

	public void updateProduct(long id, String productTitle, double originalPrice, int discountPercentage,
			String description, int productQuantity, String productCategory, MultipartFile image) throws IOException {

		// Find Product By Id from database
		Product existingProduct = repository.getProductByProductId(id);

		if (existingProduct == null) {
			throw new RuntimeException("Product is not found with Id: " + id);
		}

		// set product updated details
		if (productTitle != null || originalPrice > 0.0 || description != null || productCategory != null) {
			existingProduct.setProductTitle(productTitle);
			existingProduct.setOriginalPrice(originalPrice);
			existingProduct.setDiscountPercentage(discountPercentage);
			existingProduct.setDescription(description);
			existingProduct.setProductQuantity(productQuantity);
			existingProduct.setProductCategory(productCategory);
			// Calculate Selling Price
			double sellingPrice = calculateSellingPrice(originalPrice, discountPercentage);
			existingProduct.setSellingPrice(sellingPrice);

			System.out.println(existingProduct);
		}

		// if image path is found then delete the image using file class delete method
		// and set new image details
		String productImageName = existingProduct.getProductImageName();
		if (image != null && !image.isEmpty()) {

			File file = new File(uploadDir + "/" + productImageName);

			if (file.exists()) {
				file.delete();
			} else {
				System.out.println("File is not found");
			}

			// save updated image in uploads folder
			String imageName = image.getOriginalFilename();
			String newImageName = UUID.randomUUID() + "_" + imageName;

			// uploads/31573575132653712537_sample.jpg
			Path uploadImagePath = Paths.get(uploadDir, newImageName);

			// http://localhost:8080/images/31573575132653712537_sample.jpg - Angular
			// Project
			String imageServePath = BASE_URL + newImageName;

			try (InputStream inputStream = image.getInputStream()) {
				Files.copy(inputStream, uploadImagePath, StandardCopyOption.REPLACE_EXISTING);
			}

			// set image details

			existingProduct.setProductImageName(newImageName);
			existingProduct.setProductImagePath(imageServePath);
			existingProduct.setProductImageType(image.getContentType());

		}

		// call repository updateProduct method to update database product record.
		repository.updateProduct(existingProduct);

	}

	public void deleteProduct(long id) {

		// Find Product By Id from database
		Product existingProduct = repository.getProductByProductId(id);

		if (existingProduct == null) {
			throw new RuntimeException("Product is not found with Id: " + id);
		}

		String productImageName = existingProduct.getProductImageName();

		if (productImageName != null && !productImageName.isEmpty()) {
			File file = new File(uploadDir + "/" + productImageName);
			if (file.exists()) {
				file.delete();
			} else {
				System.out.println("File is not found");
			}
		}

		repository.deleteProduct(existingProduct);

	}

	private double calculateSellingPrice(double originalPrice, int discountPercentage) {
		double discount = originalPrice * discountPercentage / 100.0;
		double sellingPrice = originalPrice - discount;
		return sellingPrice;
	}

}
