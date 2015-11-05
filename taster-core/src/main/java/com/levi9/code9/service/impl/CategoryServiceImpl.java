package com.levi9.code9.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.levi9.code9.model.Category;
import com.levi9.code9.repository.CategoryRepository;
import com.levi9.code9.service.CategoryService;

/**
 * @author s.racicberic
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category findOne(Long id) {
		return categoryRepository.findOne(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	@Transactional
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	@Transactional
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void remove(Long id) throws IllegalArgumentException {
		Category category = categoryRepository.findOne(id);
		if (category == null) {
			throw new IllegalArgumentException(String.format(
					"Category with id=%d does not exist.", id));
		}
		/*List<Question> questions = questionRepository.findByCategory(category);
		
		for (Question question : questions) {
			question.setCategory(null);
			questionRepository.save(question);
		}*/
		categoryRepository.delete(id);
	}

}
