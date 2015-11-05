/**
 * 
 */
package com.levi9.code9.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * @author s.racicberic
 *
 */
@Entity
@Table(name = "question")
public class Question extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3407072150894856625L;
	
	/**
	 * Content of the question
	 */
	@Column(nullable = false, length = 255)
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	private String content;
	
	/**
	 * Category of the question
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	/**
	 * list of possible answers
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false)
	@Valid
	private List<Answer> answers;
	
	public Question() {
		answers = new ArrayList<>();
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the answers
	 */
	public List<Answer> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

}
