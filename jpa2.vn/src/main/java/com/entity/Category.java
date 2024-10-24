package com.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="categories")
@NamedQuery(name="Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int category_id;
	
	@Column(name = "category_name", columnDefinition = "VARCHAR(200) NOT NULL")
	private String category_name;
	
	@Column(name="images", columnDefinition = "VARCHAR(200) NULL")
	private String images;
	
	@Column(name="status")
	private String status;
	
	@OneToMany(mappedBy = "category")
	private List<Video> videos;

	public Category() {

	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	
	public Video addVideo(Video video)
	{
		getVideos().add(video);
		video.setCategory(this);
		return video;
	}
	public Video removeVideo(Video video)
	{
		getVideos().remove(video);
		video.setCategory(null);
		return video;
	}
	
	
}
