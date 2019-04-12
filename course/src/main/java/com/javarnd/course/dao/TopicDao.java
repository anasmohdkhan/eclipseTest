package com.javarnd.course.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javarnd.course.model.Topic;

@Repository
public interface TopicDao extends CrudRepository<Topic, Integer> {

	
	
}
