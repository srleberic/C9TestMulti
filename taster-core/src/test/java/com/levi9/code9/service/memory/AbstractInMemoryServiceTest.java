/**
 * 
 */
package com.levi9.code9.service.memory;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.levi9.code9.model.AbstractBaseEntity;
import com.levi9.code9.service.CrudService;


/**
 * Test class for {@link AbstractInMemoryService}.
 * 
 * @author s.racicberic
 *
 */
public class AbstractInMemoryServiceTest {
	
	private static class NamedEntity extends AbstractBaseEntity {
		private static final long serialVersionUID = 3567716527030341983L;
		private String name;
		
	}
	
	private static class NamedService extends 
			AbstractInMemoryService<NamedEntity> implements 
			CrudService<NamedEntity> {
	}
	
	private CrudService<NamedEntity> service;
	
	@Before
	public void setUp() {
		service = new NamedService();
		
		NamedEntity entity1 = new NamedEntity();
		entity1.setId(1L);
		entity1.name = "Java";
		
		NamedEntity entity2 = new NamedEntity();
		entity2.setId(2L);
		entity2.name = "Scala";
		
		service.save(entity1);
		service.save(entity2);
	}
	
	@Test
	public void testFindById() {
		NamedEntity ent = service.findOne(1L);
		Assert.assertNotNull(ent);
		Assert.assertEquals("Java", ent.name);
	}
	
	@Test
	public void testFindAll() {
		List<NamedEntity> entities = service.findAll();
		Assert.assertEquals(2, entities.size());
		NamedEntity e1 = entities.get(0);
		NamedEntity e2 = entities.get(1);
		if (e1.getId().equals(1L)) {
			Assert.assertEquals("Java", e1.name);
			Assert.assertTrue(e2.getId().equals(2L) && e2.name.equals("Scala"));
		} else {
			Assert.assertTrue(e1.getId().equals(2L) && e1.name.equals("Scala"));
			Assert.assertTrue(e2.getId().equals(1L) && e2.name.equals("Java"));
		}
	}
	
	@Test
	public void testSave() {
		NamedEntity e = new NamedEntity();
		e.name = "C#";
		NamedEntity saved = service.save(e);
		Assert.assertNotNull(saved.getId());
		Assert.assertEquals("C#", service.findOne(saved.getId()).name);
	}
	
	@Test
	public void testRemove() {
		Assert.assertNotNull(service.findOne(1L));
		Assert.assertNotNull(service.findOne(2L));

		service.remove(1L);

		Assert.assertNull(service.findOne(1L));
		Assert.assertNotNull(service.findOne(2L));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveIllegalArg() {
		Assert.assertNull(service.findOne(3L));
		service.remove(3L);
	}

}
