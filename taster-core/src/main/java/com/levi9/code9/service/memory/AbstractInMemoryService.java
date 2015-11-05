package com.levi9.code9.service.memory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.levi9.code9.model.AbstractBaseEntity;
import com.levi9.code9.service.CrudService;

public abstract class AbstractInMemoryService<T extends AbstractBaseEntity> 
		implements CrudService<T> {
	
	protected static final String NON_EXISTING_ENTITY = "Error: Tried to remove non-existing entity with id=%d.";
	
	/**
	 * 
	 */
	private final Map<Long, T> map = new LinkedHashMap<>();
	
	/**
	 * 
	 */
	protected final AtomicLong sequence = new AtomicLong(1);

	/* 
	 * @see com.levi9.code9.service.CrudService#findById(java.lang.Long)
	 */
	@Override
	public T findOne(Long id) {
		return map.get(id);
	}

	/* 
	 * @see com.levi9.code9.service.CrudService#findAll()
	 */
	@Override
	public List<T> findAll() {
		return new ArrayList<>(map.values());
	}

	/* 
	 * @see com.levi9.code9.service.CrudService#save(com.levi9.code9.model.AbstractBaseEntity)
	 */
	@Override
	public T save(T t) {
		if (t.getId() == null) {
			t.setId(sequence.getAndIncrement());
		}
		map.put(t.getId(), t);
		return t;
	}

	/* 
	 * @see com.levi9.code9.service.CrudService#remove(java.lang.Long)
	 */
	@Override
	public void remove(Long id) throws IllegalArgumentException {
		T t = map.remove(id);
		if (t == null) {
			throw new IllegalArgumentException(String.format(NON_EXISTING_ENTITY, id));
		}
	}
	
}
