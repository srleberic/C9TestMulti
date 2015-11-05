package com.levi9.code9.service.memory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.levi9.code9.model.Answer;
import com.levi9.code9.model.Question;
import com.levi9.code9.service.QuestionService;

/**
 * @author s.racicberic
 *
 */
@Service
public class InMemoryQuestionService extends AbstractInMemoryService<Question>
		implements QuestionService {

	@Override
	public List<Question> findByCategory(Long categoryId) {
		List<Question> res = new ArrayList<>();
		for (Question q : findAll()) {
			if (q.getCategory() != null 
					&& q.getCategory().getId().equals(categoryId)){
				res.add(q);
			}
		}
		return res;
	}

	@Override
	public void addAnswer(Question question) {
		Answer answer = new Answer();
		/*if (question.getAnswers() == null) {
			question.setAnswers(new ArrayList<Answer>());
		}*/
		answer.setId(new Long(question.getAnswers().size()));
		question.getAnswers().add(answer);
	}

	@Override
	public void removeAnswer(Question question, Long answerId)
			throws IllegalArgumentException {
		if (answerId == null || answerId < 0 
				|| answerId > question.getAnswers().size() - 1) {
			throw new IllegalArgumentException(String.format(
					NON_EXISTING_ENTITY, answerId));
		}
		question.getAnswers().remove(answerId.intValue());
	}

}
