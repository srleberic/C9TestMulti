/**
 * 
 */
package com.levi9.code9.service.memory;

import org.junit.Assert;
import org.junit.Before;

import com.levi9.code9.model.Question;
import com.levi9.code9.model.Test;
import com.levi9.code9.service.TestService;

/**
 * @author Srle
 *
 */
public class InMemoryTestServiceTest {
	
	private TestService service;
	
	@Before
	public void setUp() {
		service = new InMemoryTestService();
		Test t = new Test();
		t.setId(1l);
		service.save(t);
	}
	
	@org.junit.Test
	public void testAddAnswer() {
		Question q = new Question();
		q.setId(1L);
		Test t = service.findOne(1L);

		Assert.assertNotNull(t.getQuestions());
		Assert.assertTrue(t.getQuestions().size() == 0);

		t.addQuestion(q);

		Assert.assertNotNull(t.getQuestions());
		Assert.assertTrue(t.getQuestions().size() == 1);
		Assert.assertTrue(t.getQuestions().iterator().hasNext() 
				&& t.getQuestions().iterator().next() != null);
	}
	
	@org.junit.Test
	public void testRemoveAnswer() {
		Question q = new Question();
		q.setId(1L);
		Test t = service.findOne(1L);
		t.addQuestion(q);

		Assert.assertNotNull(t.getQuestions());
		Assert.assertTrue(t.getQuestions().size() == 1);
		Assert.assertTrue(t.getQuestions().iterator().hasNext() 
				&& t.getQuestions().iterator().next() != null);

		t.removeQuestion(q);

		Assert.assertNotNull(t.getQuestions());
		Assert.assertTrue(t.getQuestions().size() == 0);
	}

}
