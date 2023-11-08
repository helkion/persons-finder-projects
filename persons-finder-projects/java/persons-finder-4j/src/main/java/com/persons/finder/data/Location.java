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
public class Location extends IndexField{

//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Getter @Setter private Long id;
	@Getter @Setter private Long referenceId;
	@Getter @Setter private Double latitude;
	@Getter @Setter private Double longitude;
}
