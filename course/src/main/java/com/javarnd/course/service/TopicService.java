package com.javarnd.course.service;

import java.util.List;

import com.javarnd.course.model.Topic;

public interface TopicService {

	public List<Topic> getAllTopics();

	public Topic getTopicById(int topicId);

	public void addTopic(Topic topic);

	public void updateTopic(int topicId, Topic topic);

	public void deleteTopic(int topicId);

}
