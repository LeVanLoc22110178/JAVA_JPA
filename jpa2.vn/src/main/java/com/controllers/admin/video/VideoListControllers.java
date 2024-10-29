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

@WebServlet(urlPatterns = {"/admin/video"})
public class VideoListControllers extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IVideoService videoService = new VideoService();
        List<Video> videos = videoService.findAll(); // Fetch videos
        
        req.setAttribute("videos", videos); // Set the list of videos in the request

        // Check if the videos list is null or empty
        if (videos != null && !videos.isEmpty()) {
            req.getRequestDispatcher("/views/admin/video/video-list.jsp").forward(req, resp); // Forward to video list view
        } else {
            req.setAttribute("errorMessage", "No videos found."); // Set an error message if no videos exist
            req.getRequestDispatcher("/views/admin/video/error.jsp").forward(req, resp); // Forward to an error view
        }
    }
}
