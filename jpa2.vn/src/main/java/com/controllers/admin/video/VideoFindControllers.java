package com.controllers.admin.video;

import java.io.IOException;
import java.util.List;

import com.entity.Video;
import com.services.IVideoService;
import com.services.Impl.VideoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/admin/video/find"})
public class VideoFindControllers extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		IVideoService videoservice = new VideoService();
		List<Video> videos = videoservice.findByVideoname(name);
		
		req.setAttribute("videod", videos);
		
		req.getRequestDispatcher("/views/admin/video/video-find.jsp").forward(req, resp);
	}

}
