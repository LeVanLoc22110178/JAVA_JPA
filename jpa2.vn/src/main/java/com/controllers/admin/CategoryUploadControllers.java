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

@MultipartConfig(
	    location = "D:/upload", // Location where files will be temporarily stored
	    maxFileSize = 1024 * 1024 * 10, // Maximum file size (10 MB)
	    maxRequestSize = 1024 * 1024 * 15, // Maximum request size (15 MB)
	    fileSizeThreshold = 1024 * 1024 // Threshold after which files are stored on disk
	)

@WebServlet(urlPatterns = {"/admin/categories/upload"})
public class CategoryUploadControllers  extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/admin/category-upload.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ICategoryService cateservice = new CategoryService();
				
				
		Category cateen = new Category();
	    
	    String name = req.getParameter("name-cate");
	    if (name == null || name.isEmpty()) {
	        System.out.println("Category name is missing or empty!");
	        req.setAttribute("error", "Category name cannot be empty");
	        req.getRequestDispatcher("/views/admin/error.jsp").forward(req, resp);
	        return; // Stop execution if name is not valid
	    } else {
	        System.out.println("Category name: " + name);
	        cateen.setCategory_name(name);
	    }
		

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
		cateservice.insert(cateen);
		
		// Redirect to category list
		resp.sendRedirect(req.getContextPath() + "/admin/categories");
	}
}
