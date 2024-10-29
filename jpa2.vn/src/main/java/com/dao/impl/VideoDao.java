package com.dao.impl;

import java.util.List;

import com.configs.JPAConfig;
import com.dao.IVideoDao;
import com.entity.Category;
import com.entity.Video;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class VideoDao implements IVideoDao {
	
	@Override
	public void insert(Video video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();

        try {
            trans.begin();
            enma.persist(video);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }
	
	@Override
	public void update(Video video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(video); 
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }
	
	@Override
	public void delete(int videoid) throws Exception {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();

        try {
            trans.begin();
            Video video = enma.find(Video.class, videoid);
            if (video != null) {
                enma.remove(video);
            } else {
                throw new Exception("Khong tim thay video");
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }
	
	@Override
	public Video findById(int videoid) {
        EntityManager enma = JPAConfig.getEntityManager();
        Video video = enma.find(Video.class, videoid);
        return video;
    }
	@Override
	public List<Video> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
        return query.getResultList();
    }
	@Override
	public List<Video> findByVideoname(String Tite) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT v FROM Video v WHERE v.title LIKE :catname";  // Corrected
        TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
        query.setParameter("catname", "%" + Tite + "%");  // Parameter name corrected
        return query.getResultList();
    }
	
	@Override
	public List<Video> findAll(int page, int pagesize) {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
        query.setFirstResult(page * pagesize);
        query.setMaxResults(pagesize);
        return query.getResultList();
    }
	@Override
	public int count() {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT count(v) FROM Video v";
            Query query = enma.createQuery(jpql);
            return ((Long) query.getSingleResult()).intValue();
        } finally {
            if (enma != null) {
                enma.close();
            }
        }
    }

}
