package com.services.Impl;

import java.util.List;

import com.dao.IVideoDao;
import com.dao.impl.VideoDao;
import com.entity.Video;
import com.services.IVideoService;

public class VideoService implements IVideoService{
	IVideoDao videodao = new VideoDao();

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return videodao.count();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		// TODO Auto-generated method stub
		return videodao.findAll();
	}

	@Override
	public List<Video> findByVideoname(String Tite) {
		// TODO Auto-generated method stub
		return videodao.findByVideoname(Tite);
	}

	@Override
	public List<Video> findAll() {
		// TODO Auto-generated method stub
		return videodao.findAll();
	}

	@Override
	public Video findById(int videoid) {
		// TODO Auto-generated method stub
		return videodao.findById(videoid);
	}

	@Override
	public void delete(int videoid) throws Exception {
		// TODO Auto-generated method stub
		videodao.delete(videoid);
	}

	@Override
	public void update(Video video) {
		videodao.update(video);
		
	}

	@Override
	public void insert(Video video) {
		videodao.insert(video);
		
	}

}
