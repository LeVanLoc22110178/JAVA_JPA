package com.dao;

import java.util.List;

import com.entity.Video;

public interface IVideoDao {

	int count();

	List<Video> findAll(int page, int pagesize);

	List<Video> findByVideoname(String Tite);

	List<Video> findAll();

	Video findById(int videoid);

	void delete(int videoid) throws Exception;

	void update(Video video);

	void insert(Video video);

}
