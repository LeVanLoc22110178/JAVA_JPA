package com.controllers.admin.video;

import java.io.File;
import java.io.FileInputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/video/*")
public class DownloadVideo extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static final String UPLOAD_DIR = "D:/upload";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getPathInfo().substring(1);  // Lấy tên file từ URL
        File file = new File(UPLOAD_DIR, fileName);
        
        if (file.exists()) {
            // Set content type cho video (có thể là mp4 hoặc định dạng khác)
            resp.setContentType("video/mp4");
            resp.setHeader("Content-Length", String.valueOf(file.length()));
            resp.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");

            // Đọc file và ghi vào response
            try (FileInputStream in = new FileInputStream(file);
                 OutputStream out = resp.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);  // 404 nếu file không tồn tại
        }
    }

	
}
