package com.javarnd.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.javarnd.course.model.Topic;
import com.javarnd.course.service.TopicService;

@RestController
@RequestMapping(ActionController.BASE_URI)
public class ActionController {

	protected static final String BASE_URI = "/api/v1/";

	@Autowired
	private TopicService topicService;

	@Autowired
	private Topic topic;
	
	@GetMapping("viewall")
	public ResponseEntity<List<Topic>> viewAll() {
		List<Topic> listTopics = topicService.getAllTopics();

		if (listTopics.isEmpty()) {
			return new ResponseEntity<List<Topic>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Topic>>(listTopics, HttpStatus.OK);
	}

	@GetMapping("view/{topicId}")
	public ResponseEntity<Topic> viewById(@PathVariable("topicId") int topicId) {
		topic = topicService.getTopicById(topicId);

		if (topic == null) {

			return new ResponseEntity<Topic>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Topic>(topic, HttpStatus.OK);

	}

	@PostMapping("viewall")
	public ResponseEntity<Void> insertTopic(@RequestBody Topic topic, UriComponentsBuilder ucBuilder) {

		topicService.addTopic(topic);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("view/{topicId}").buildAndExpand(topic.getTopicId()).toUri());

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@PutMapping("view/{topicId}")
	public ResponseEntity<Topic> updateById(@PathVariable("topicId") int topicId, @RequestBody Topic topics) {

			topicService.updateTopic(topicId, topics);
			return new ResponseEntity<Topic>(topics, HttpStatus.OK);
		

	}

	@DeleteMapping("view/{topicId}")
	public ResponseEntity<Topic> deleteById(@PathVariable("topicId") int topicId) {
		topic = topicService.getTopicById(topicId);
		topic.setTopicId(topicId);
		if (topic == null) {

			return new ResponseEntity<Topic>(HttpStatus.NOT_FOUND);
		}

		topicService.deleteTopic(topicId);
		return new ResponseEntity<Topic>(topic, HttpStatus.OK);

	}
}
