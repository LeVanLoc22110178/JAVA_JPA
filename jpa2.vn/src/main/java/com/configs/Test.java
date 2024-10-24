package com.configs;

import com.entity.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class Test {
	public static void main(String[] args) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		Category cate = new Category();

		try
		{
			trans.begin();
			enma.persist(cate);
			trans.commit();
			
		} catch (Exception e) {
		
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

}
