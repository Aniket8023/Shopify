package com.shopify.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	
	@Column(nullable = false)
	private String productTitle ;
	
	@Column(nullable = false)
	private double originalPrice ;
	
	@Column(nullable = false)
	private double sellingPrice ;
	
	@Column(nullable = false)
	private int discountPercentage ;
	
	@Column(nullable = false)
	private String description ;
	
	@Column(nullable = false)
	private int productQuantity ;
	
	@Column(nullable = false)
	private String productCategory ;
	
	@Column(nullable = false)
	private String productImagePath ;
	
	@Column(nullable = false)
	private String productImageName ;
	
	@Column(nullable = false)
	private String productImageType ;
	
	private int ratings ;

	public Product( String productTitle, double originalPrice, double sellingPrice, int discountPercentage,
			String description, int productQuantity, String productCategory, String productImagePath,
			String productImageName, String productImageType, int ratings) {
		super();
		this.productTitle = productTitle;
		this.originalPrice = originalPrice;
		this.sellingPrice = sellingPrice;
		this.discountPercentage = discountPercentage;
		this.description = description;
		this.productQuantity = productQuantity;
		this.productCategory = productCategory;
		this.productImagePath = productImagePath;
		this.productImageName = productImageName;
		this.productImageType = productImageType;
		this.ratings = ratings;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductImagePath() {
		return productImagePath;
	}

	public void setProductImagePath(String productImagePath) {
		this.productImagePath = productImagePath;
	}

	public String getProductImageName() {
		return productImageName;
	}

	public void setProductImageName(String productImageName) {
		this.productImageName = productImageName;
	}

	public String getProductImageType() {
		return productImageType;
	}

	public void setProductImageType(String productImageType) {
		this.productImageType = productImageType;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productTitle=" + productTitle + ", originalPrice=" + originalPrice
				+ ", sellingPrice=" + sellingPrice + ", discountPercentage=" + discountPercentage + ", description="
				+ description + ", productQuantity=" + productQuantity + ", productCategory=" + productCategory
				+ ", productImagePath=" + productImagePath + ", productImageName=" + productImageName
				+ ", productImageType=" + productImageType + ", ratings=" + ratings + "]";
	}
	

	
}
