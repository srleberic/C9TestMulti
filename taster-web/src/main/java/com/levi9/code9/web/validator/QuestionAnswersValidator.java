package com.levi9.code9.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.levi9.code9.model.Answer;
import com.levi9.code9.model.Question;

/**
 * Validates that there is minimum required number of answers and that at least
 * one is correct.
 * 
 * @author s.racicberic
 *
 */
@Component
public class QuestionAnswersValidator implements Validator {

	private static final int MIN_NUM_OF_ANSWERS = 2;
	private static final int MIN_NUM_OF_CORRECT_ANSWERS = 1;
	private static final String ANSWERS_FIELD_NAME = "answers";
	private static final String MIN_TWO_ANSWERS_MESSAGE_KEY = "question.answers.minimumTwoAnswers";
	private static final String MIN_ONE_CORRECT_ANSWER_MESSAGE_KEY = "question.answers.minimumOneCorrect";

	@Override
	public boolean supports(Class<?> clazz) {
		return Question.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (target != null && supports(target.getClass())) {
			Question question = (Question) target;
			if (question.getAnswers().size() < MIN_NUM_OF_ANSWERS) {
				errors.rejectValue(ANSWERS_FIELD_NAME, MIN_TWO_ANSWERS_MESSAGE_KEY, 
						"Question must have at least two answers.");
			} else {
				int correctCount = 0;
				for (Answer a : question.getAnswers()) {
					if (a.isCorrect()) {
						correctCount++;
						break;
					}
				}
				if (correctCount < MIN_NUM_OF_CORRECT_ANSWERS) {
					errors.rejectValue(ANSWERS_FIELD_NAME, MIN_ONE_CORRECT_ANSWER_MESSAGE_KEY, 
							"Question must have at least one correct answer.");
				}
			}
		}
	}

}
