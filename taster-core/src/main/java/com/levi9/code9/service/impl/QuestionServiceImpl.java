package com.levi9.code9.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.levi9.code9.model.Answer;
import com.levi9.code9.model.Question;
import com.levi9.code9.repository.QuestionRepository;
import com.levi9.code9.repository.TestRepository;
import com.levi9.code9.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private TestRepository testRepository;

	@Override
	public Question findOne(Long id) {
		return questionRepository.findOne(id);
	}

	@Override
	public List<Question> findAll() {
		return questionRepository.findAll();
	}

	@Override
	@Transactional
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Question save(Question question) {
		return questionRepository.save(question);
	}

	@Override
	@Transactional
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void remove(Long id) throws IllegalArgumentException {
		/*Question question = questionRepository.findOne(id);
		if (question == null) {
			throw new IllegalArgumentException(String.format(
							"Question with id=%d does not exist.",
							id));
		}
		List<Test> tests = testRepository.findByContainsQuestion(question);
		for (Test test : tests) {
			test.getQuestions().remove(question);
			testRepository.save(test);
		}*/
		questionRepository.delete(id);
	}

	@Override
	public List<Question> findByCategory(Long categoryId) {
		return questionRepository.findQuestionsByCategory(categoryId);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void addAnswer(Question question) {
		Answer answer = new Answer();
		question.getAnswers().add(answer);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void removeAnswer(Question question, Long answerId)
			throws IllegalArgumentException {
		if (answerId == null || answerId < 0
				|| answerId > question.getAnswers().size() - 1) {
			throw new IllegalArgumentException(String.format(
					"Error: Tried to delete non-existing entity with id=%d.",
					answerId));
		}
		question.getAnswers().remove(answerId.intValue());
	}

}
