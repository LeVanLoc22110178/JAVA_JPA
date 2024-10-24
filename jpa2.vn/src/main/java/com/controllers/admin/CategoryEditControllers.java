package com.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.entity.Category;
import com.services.ICategoryService;
import com.services.Impl.CategoryService;
import com.utils.Constant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(urlPatterns = { "/admin/categories/edit" })
@MultipartConfig
public class CategoryEditControllers extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    ICategoryService cateservice = new CategoryService();
	    String id = req.getParameter("id");
	    
	    // Fetch the category by ID
	    Category cateen = cateservice.findById(Integer.parseInt(id));
	    
	    if (cateen != null) {
	        req.setAttribute("cate", cateen);
	        req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
	    } else {
	        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Category not found");
	    }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    ICategoryService cateservice = new CategoryService();
	    String id = req.getParameter("id");
	    
	    Category cateen = cateservice.findById(Integer.parseInt(id));
	    
	    if (cateen != null) {
	        String name = req.getParameter("name");
	        cateen.setCategory_name(name);

	        // Handle file upload
	        Part filePart = req.getPart("image"); // Retrieves <input type="file" name="image">
	        if (filePart != null && filePart.getSize() > 0) {
	            // Generate a file name and save the file
	            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	            String uploadPath = Constant.UPLOAD_DIRECTORY + File.separator + fileName;
	            
	            // Save the file on disk
	            filePart.write(uploadPath);
	            
	            // Update the image path in the category
	            cateen.setImages(fileName);
	        }

	        String status = req.getParameter("status");
	        cateen.setStatus(status);

	        // Save the updated category
	        cateservice.update(cateen);
	        
	        // Redirect to category list
	        resp.sendRedirect(req.getContextPath() + "/admin/categories");
	    } else {
	        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Category not found");
	    }
	}
}
