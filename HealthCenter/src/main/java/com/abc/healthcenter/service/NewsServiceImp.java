package com.abc.healthcenter.service;

import java.util.Optional;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.healthcenter.entity.AdminEntity;
import com.abc.healthcenter.entity.NewsEntity;
import com.abc.healthcenter.exceptions.ResourceAlreadyExistException;
import com.abc.healthcenter.exceptions.ResourceNotFoundException;
import com.abc.healthcenter.model.News;
import com.abc.healthcenter.repository.AdminRepository;
import com.abc.healthcenter.repository.NewsRepository;

/**
 * 
 * @author venkatesh
 *
 *Date : 06-Jul-2021
 */
@Service
public class NewsServiceImp implements NewsService {
	
	@Autowired
	private NewsRepository newsRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NewsServiceImp.class);
	
    /**
     * Implement save news details from newsService Interface
     * {@inheritDoc}
     */
	@Override
	public void saveNews(News news) throws ResourceAlreadyExistException {
		Optional<NewsEntity> newsEntity1 = newsRepository.findById(news.getNewsId());
		if(newsEntity1.isPresent()) {
		throw new ResourceAlreadyExistException("News is already exist with this Id"+news.getNewsId());

	}
	else {
		NewsEntity newsEntity = convertModeltoEntity(news);
		Optional<AdminEntity> optionalEntity =adminRepository.findById(news.getAdminId());
		AdminEntity adminEntity = new AdminEntity();
		
		adminEntity.setAdminId(optionalEntity.get().getAdminId());
		
		newsRepository.save(newsEntity);
	
	}
		
	}
	/**
     * {@inheritDoc}
     */
	@Override
	public void deleteNewsbyId(int id) throws ResourceNotFoundException {
		Optional<NewsEntity> newsEntity = newsRepository.findById(id);
		if(newsEntity.isPresent()) {
			newsRepository.deleteById(id);
		}
		else {
			
			throw new ResourceNotFoundException ("can not find News with this Id "+id);
		}

	}
	/**
     * {@inheritDoc}
     */
	@Override
	public void updateNewsbyId(News news) throws ResourceNotFoundException {
		
		NewsEntity newsEntity =newsRepository.findById(news.getNewsId()).get();
		if(newsEntity == null) {
			throw new ResourceNotFoundException ("can not find News with this Id"+news.getNewsId());
			
		}
		else {
			newsEntity.setNewsId(news.getNewsId());
			newsEntity.setNewsHeader(news.getNewsHeader());
			newsEntity.setNewsBody(news.getNewsBody());;
			newsEntity.setNewsContact(news.getNewsContact());
			newsEntity.setNewsDate(LocalDate.parse(news.getNewsDate()));
			newsRepository.save(newsEntity);
		}

	}
	@Override
	public News findNewsbyId(int id) throws ResourceNotFoundException {
		
		Optional<NewsEntity> newsEntity = newsRepository.findById(id);
		if(newsEntity.isPresent()) {
			
			News news = convertEntitytoModel(newsEntity);
			return news;
		}
		else {
			throw new ResourceNotFoundException("can not find news with this id "+id);
		}
	}
	/**
	 * this methods convert model to entity
	 * @param news
	 * @return
	 */
	private NewsEntity convertModeltoEntity(News news) {
		NewsEntity newsEntity = new NewsEntity();
		newsEntity.setNewsId(news.getNewsId());
		newsEntity.setNewsHeader(news.getNewsHeader());
		newsEntity.setNewsBody(news.getNewsBody());
		newsEntity.setNewsContact(news.getNewsContact());
		newsEntity.setNewsDate(LocalDate.parse(news.getNewsDate()));
		return newsEntity;
	}
	
	
	/**
	 * this method convert entity to model
	 * @param newsEntity
	 * @return
	 */
	private News convertEntitytoModel(Optional<NewsEntity> newsEntity) {
		
		News news = new News();
		news.setNewsId(newsEntity.get().getNewsId());
		news.setNewsHeader(newsEntity.get().getNewsHeader());
		news.setNewsBody(newsEntity.get().getNewsBody());
		news.setNewsContact(newsEntity.get().getNewsContact());
		//newsEntity.setNewsDate(LocalDate.parse(news.getNewsDate()));
		return news;
		
		
	}
	
	

}
