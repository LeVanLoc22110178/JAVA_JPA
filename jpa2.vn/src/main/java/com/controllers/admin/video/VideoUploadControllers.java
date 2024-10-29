package com.controllers.admin.video;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.entity.Video;
import com.services.ICategoryService;
import com.services.IVideoService;
import com.services.Impl.CategoryService;
import com.services.Impl.VideoService;
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
@WebServlet(urlPatterns = {"/admin/video/upload"})
public class VideoUploadControllers extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/admin/video/video-upload.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IVideoService videoservice = new VideoService();
		ICategoryService categoryService = new CategoryService();
		String title = req.getParameter("Title");
		String category_id = req.getParameter("category_id");
		String description = req.getParameter("description");
		String active = req.getParameter("status");
		
		Video videos = new Video();
		
		
		Part filePart = req.getPart("poster"); // Retrieves <input type="file" name="image">
		if (filePart != null && filePart.getSize() > 0) {
		    // Generate a file name and save the file
		    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		    String uploadPath = Constant.UPLOAD_DIRECTORY + File.separator + fileName;
		    
		    // Save the file on disk
		    filePart.write(uploadPath);
		    
		    // Update the image path in the category
		    videos.setPoster(fileName);
		}
		Part viewvideo = req.getPart("views"); // Retrieves <input type="file" name="image">
		if (viewvideo != null && viewvideo.getSize() > 0) {
		    // Generate a file name and save the file
		    String filevName = Paths.get(viewvideo.getSubmittedFileName()).getFileName().toString();
		    String uploadvPath = Constant.UPLOAD_DIRECTORY + File.separator + filevName;
		    
		    // Save the file on disk
		    viewvideo.write(uploadvPath);
		    
		    // Update the image path in the category
		    videos.setViews(filevName);
		}

        
        videos.setTitle(title);
        videos.setActive(Integer.parseInt(active));
        videos.setDescription(description);
        videos.setCategory(categoryService.findById((Integer.parseInt(category_id))));
        videoservice.insert(videos);
        resp.sendRedirect(req.getContextPath() + "/admin/video");
        
        
        
	}

}
