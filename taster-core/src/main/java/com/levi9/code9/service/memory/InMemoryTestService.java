package com.levi9.code9.service.memory;

import org.springframework.stereotype.Service;

import com.levi9.code9.model.Question;
import com.levi9.code9.model.Test;
import com.levi9.code9.service.TestService;

@Service
public class InMemoryTestService extends AbstractInMemoryService<Test> 
	implements TestService {

	@Override
	public void addQuestion(Test test, Question question)
			throws IllegalArgumentException {
		if (question == null || test.getQuestions().contains(question)) {
			throw new IllegalArgumentException("Error: No question provided or question is already in test.");
		}
		test.getQuestions().add(question);
	}

	@Override
	public void removeQuestion(Test test, Question question)
			throws IllegalArgumentException {
		if (question == null || !test.getQuestions().contains(question)) {
			throw new IllegalArgumentException("Error: No question provided or question is not in test.");
		}
		test.getQuestions().remove(question);
	}

}
