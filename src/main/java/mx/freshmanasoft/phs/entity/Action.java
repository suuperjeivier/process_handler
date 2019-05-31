package mx.freshmanasoft.phs.entity;

import org.springframework.data.annotation.Id;

public class Action {

	@Id
	private Long id;
	private String name;
	private Double value;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}

}
