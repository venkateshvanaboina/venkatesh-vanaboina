package com.abc.healthcenter.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.healthcenter.model.News;
import com.abc.healthcenter.model.Response;
import com.abc.healthcenter.service.NewsService;

@RestController
@Validated
@RequestMapping("/news")
public class NewsController {
	Response response = new Response();
	private static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);

	
	@Autowired
	private NewsService newsService;

	@PostMapping("/save")
	public ResponseEntity<?> addNews(@Valid @RequestBody News news){
		newsService.saveNews(news);
		response.setMsg("News "+news.getNewsHeader()+",News Added.");
		response.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteNews(@Valid @Min(1) @PathVariable int id){
		newsService.deleteNewsbyId(id);
		response.setMsg("Deleted Successfully");
		response.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<>(response,HttpStatus.FOUND);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateAdmin(@Valid @RequestBody News news) {
		newsService.updateNewsbyId(news);
		response.setMsg("Hello Admin "+news.getNewsId()+",News have been updated");
		response.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<>(response,HttpStatus.FOUND);
	}
}
