package com.controllers.admin;

import java.io.IOException;
import java.util.List;

import com.entity.Category;

import com.services.ICategoryService;

import com.services.Impl.CategoryService;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin/categories"})
public class CategoryControllers extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> list = cateService.findAll();
		req.setAttribute("listcate", list);
		ICategoryService cateservice = new CategoryService();
		List<Category> cate = cateservice.findAll();
		req.setAttribute("cate", cate);
		req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
	}
	
}
