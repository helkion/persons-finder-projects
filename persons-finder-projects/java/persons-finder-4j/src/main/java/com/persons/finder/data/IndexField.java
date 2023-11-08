package com.persons.finder.data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Data
public class IndexField {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Long id;
	
	public IndexField() {
		
	}
	
	public IndexField(Long id) {
		this.id = id;
	}
}
