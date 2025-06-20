package com.shopify.app.repositories;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shopify.app.entity.Product;

@Repository
public class ProductRepository {
	
	@Autowired
	private SessionFactory factory ;

	
	public void saveProduct(Product product) {
		
		Session session = null ;
		Transaction transaction = null ;
		
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			
			session.save(product) ;
			transaction.commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something Went Wrong !!!");
		} finally {
			session.close();
		}
		
	}


	public List<Product> getAllProducts() {
		
		Session session = null ;
		List<Product> list = null ;
		
		try {
			session = factory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something Went Wrong !!!");
		} finally {
			session.close();
		}
		
		return list ;
	}


	public Product getProductByProductId(long id) {
		
		Session session = null ;
		Product product = null ;
		
		try {
			session = factory.openSession();
			product = session.get(Product.class, id) ;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something Went Wrong !!!");
		} finally {
			session.close();
		}
		
		return product ;
		
	}


	public void updateProduct(Product existingProduct) {
		
		Session session = null ;
		Transaction transaction = null ;
		
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			
			session.update(existingProduct) ;
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something Went Wrong !!!");
		} finally {
			session.close();
		}
		
	}


	public void deleteProduct(Product product) {
		Session session = null ;
		Transaction transaction = null ;
		
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			Product existingProduct = (Product) session.merge(product);
			session.delete(existingProduct);
			
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something Went Wrong !!!");
		} finally {
			session.close();
		}
		
	}

}
