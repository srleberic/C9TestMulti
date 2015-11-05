package com.levi9.code9.service;

import com.levi9.code9.model.Question;
import com.levi9.code9.model.Test;

/**
 * @author Srle
 *
 */
public interface TestService extends CrudService<Test> {
	
	/**
	 * @param test
	 * @param question
	 * @throws IllegalArgumentException
	 */
	void addQuestion(Test test, Question question) throws IllegalArgumentException;
	
	/**
	 * @param test
	 * @param question
	 * @throws IllegalArgumentException
	 */
	void removeQuestion(Test test, Question question) throws IllegalArgumentException;

}
