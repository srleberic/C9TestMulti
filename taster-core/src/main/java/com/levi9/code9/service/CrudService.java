package com.levi9.code9.service;

import java.util.List;

import com.levi9.code9.model.AbstractBaseEntity;

/**
 * @author s.racicberic
 *
 * @param <T>
 */
public interface CrudService <T extends AbstractBaseEntity> {
	
	/**
	 * @param id
	 * @return
	 */
	T findOne(Long id);
	
	/**
	 * @return list of existing entities
	 */
	List<T> findAll(); 
	
	/**
	 * @param t
	 * @return saved entity
	 */
	T save(T t);
	
	/**
	 * @param id
	 * @throws IllegalArgumentException
	 */
	void remove(Long id) throws IllegalArgumentException;

}
