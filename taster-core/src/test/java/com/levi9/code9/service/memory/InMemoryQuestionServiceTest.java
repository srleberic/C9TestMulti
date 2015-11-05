/**
 * 
 */
package com.levi9.code9.service.memory;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.levi9.code9.model.Category;
import com.levi9.code9.model.Question;
import com.levi9.code9.service.QuestionService;

/**
 * @author s.racicberic
 *
 */
public class InMemoryQuestionServiceTest {
	
	private QuestionService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new InMemoryQuestionService();
		
		Category categoryJava = new Category();
		categoryJava.setId(1L);
		categoryJava.setName("Java");
		Category categoryC = new Category();
		categoryC.setId(2L);
		categoryC.setName("C#");
		Question q1 = new Question();
		q1.setId(1L);
		q1.setCategory(categoryJava);
		q1.setContent("Test content");
		Question q2 = new Question();
		q2.setId(2L);
		q2.setCategory(categoryJava);
		q2.setContent("Test content");
		Question q3 = new Question();
		q3.setId(3L);
		q3.setCategory(categoryC);
		q3.setContent("Test content");
		Question q4 = new Question();
		q4.setId(4L);
		q4.setCategory(categoryC);
		q4.setContent("Test content");
		Question q5 = new Question();
		q5.setId(5L);
		q5.setContent("Test content");
		
		service.save(q1);
		service.save(q2);
		service.save(q3);
		service.save(q4);
		service.save(q5);
	}

	@Test
	public void testFindByCategoryId() {
		Category cat = new Category();
		cat.setId(1L);
		List<Question> questions = service.findByCategory(cat.getId());
		
		Assert.assertNotNull(questions);
		Assert.assertTrue(questions.size() == 2);

		cat.setId(2L);
		questions = service.findByCategory(cat.getId());

		Assert.assertNotNull(questions);
		Assert.assertTrue(questions.size() == 2);
		
		cat.setId(3L);
		questions = service.findByCategory(cat.getId());

		Assert.assertNotNull(questions);
		Assert.assertTrue(questions.size() == 0);
	}
	
	@Test
	public void testAddAnswer() {
		Question q = service.findOne(1L);
		
		Assert.assertNotNull(q.getAnswers());
		Assert.assertTrue(q.getAnswers().size() == 0);
		
		service.addAnswer(q);
		
		Assert.assertNotNull(q.getAnswers());
		Assert.assertTrue(q.getAnswers().size() == 1);
		Assert.assertNotNull(q.getAnswers().get(0));
	}
	
	@Test
	public void testRemoveAnswer() {
		Question q = service.findOne(1L);
		service.addAnswer(q);

		Assert.assertNotNull(q.getAnswers());
		Assert.assertTrue(q.getAnswers().size() == 1);
		Assert.assertNotNull(q.getAnswers().get(0));

		service.removeAnswer(q, 0L);

		Assert.assertNotNull(q.getAnswers());
		Assert.assertTrue(q.getAnswers().size() == 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveAnswerIllegalArgument() {
		Question q = service.findOne(1L);
		Assert.assertTrue(q.getAnswers().size() < 1);
		service.removeAnswer(q, 0L);
	}

}
