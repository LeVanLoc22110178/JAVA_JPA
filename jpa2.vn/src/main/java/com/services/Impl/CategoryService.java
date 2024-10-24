package com.services.Impl;

import java.util.List;

import com.dao.ICategoryDao;
import com.dao.impl.CategoryDao;
import com.entity.Category;
import com.services.ICategoryService;

public class CategoryService implements ICategoryService{
	ICategoryDao cateDao = new CategoryDao();

	@Override
	public int count() {
		return cateDao.count();
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		// TODO Auto-generated method stub
		return cateDao.findAll(page, pagesize);
	}

	@Override
	public List<Category> findByCategoryname(String catname) {
		// TODO Auto-generated method stub
		return cateDao.findByCategoryname(catname);
	}

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return cateDao.findAll();
	}

	@Override
	public Category findById(int cateid) {
		// TODO Auto-generated method stub
		return cateDao.findById(cateid);
	}

	@Override
	public void delete(int cateid) throws Exception {
		cateDao.delete(cateid);
		
	}

	@Override
	public void update(Category category) {
		cateDao.update(category);
	}

	@Override
	public void insert(Category category) {
		cateDao.insert(category);
	}

}
