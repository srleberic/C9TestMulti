package com.levi9.code9.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.levi9.code9.model.Category;
import com.levi9.code9.service.CategoryService;

/**
 * Converts string to category object. String is id (long).
 * 
 * @author Srle
 *
 */
public class StringToCategoryConverter implements Converter<String, Category> {
	
	@Autowired
	private CategoryService categoryService;

	/* 
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public Category convert(String categoryId) {
		return categoryService.findOne(Long.parseLong(categoryId));
	}
	
}
