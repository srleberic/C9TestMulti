/**
 * 
 */
package com.levi9.code9.service;

import java.util.List;

import com.levi9.code9.model.Question;

/**
 * @author s.racicberic
 *
 */
public interface QuestionService extends CrudService<Question> {
	
	/**
	 * @param categoryId
	 * @return
	 */
	List<Question> findByCategory(Long categoryId);
	
	/**
	 * @param question
	 */
	void addAnswer(Question question);
	
	/**
	 * @param question
	 * @param answerId
	 * @throws IllegalArgumentException
	 */
	void removeAnswer(Question question, Long answerId) 
			throws IllegalArgumentException;

}
