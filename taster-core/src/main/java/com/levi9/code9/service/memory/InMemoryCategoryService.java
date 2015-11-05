package com.levi9.code9.service.memory;

import org.springframework.stereotype.Service;

import com.levi9.code9.model.Category;
import com.levi9.code9.service.CategoryService;

/**
 * @author s.racicberic
 *
 */
@Service
public class InMemoryCategoryService extends AbstractInMemoryService<Category> 
		implements CategoryService {

}
