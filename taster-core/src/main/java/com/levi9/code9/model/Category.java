package com.levi9.code9.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

/**
 * @author s.racicberic
 *
 */
@Entity
@Table(name = "category")
public class Category extends AbstractBaseEntity {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5853533806683734518L;
	
	/**
	 * Category name
	 */
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	@Column(nullable = false, length = 255)
	private String name;
	
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
