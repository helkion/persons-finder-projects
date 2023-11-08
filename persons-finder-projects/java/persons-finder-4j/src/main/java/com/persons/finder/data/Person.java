/**
 * 
 */
package com.persons.finder.data;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Entity
public class Person extends IndexField{

	@Getter @Setter private String name;
}
