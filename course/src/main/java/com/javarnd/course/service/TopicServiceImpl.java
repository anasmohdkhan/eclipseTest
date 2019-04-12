package com.javarnd.course.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javarnd.course.dao.TopicDao;
import com.javarnd.course.model.Topic;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicDao topicDao;

	@Autowired
	private Topic topic;

	@Override
	public List<Topic> getAllTopics() {

		List<Topic> listTopics = new ArrayList<>();
		topicDao.findAll().forEach(listTopics::add);

		return listTopics;
	}

	@Override
	public Topic getTopicById(int topicId) {

		topic = topicDao.findById(topicId).get();

		return topic;
	}

	@Override
	public void addTopic(Topic topic) {

		topicDao.save(topic);

	}

	@Override
	public void updateTopic(int topicId, Topic topics) {

		topic = topicDao.findById(topicId).get();
		
		topic.setTopicName(topics.getTopicName());
		topic.setTopicDescription(topics.getTopicDescription());
		
		topicDao.save(topic);

	}

	@Override
	public void deleteTopic(int topicId) {

		topicDao.deleteById(topicId);

	}

}