package com.foobarts.gadjet.service.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Includes gadjet information")
@Entity
public class Gadjet {
	@Id
	@GeneratedValue
	private Integer id;
	
	@ApiModelProperty(notes="Name must not be blank")
	@NotBlank(message = "Name must not be blank")
    private String name;
	private Date manufacturingDate;
	private String description;
	
	@ApiModelProperty(notes="Colour must not be blank")
	@NotBlank(message = "Colour must not be blank")
    private String colour;
	
	@ApiModelProperty(notes="Status mub not be blank")
	@NotBlank(message = "Status must not be blank")
    private String status;
	
	
	public Gadjet() {
		super();
	}

	public Gadjet(String name, Date manufacturingDate, String description, String colour, String status) {
		super();
		
		this.name = name;
		this.manufacturingDate = manufacturingDate;
		this.description = description;
		this.colour = colour;
		this.status = status;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getManufacturingDate() {
		return manufacturingDate;
	}


	public void setManufacturingDate(Date manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getColour() {
		return colour;
	}


	public void setColour(String colour) {
		this.colour = colour;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return String.format("Gadjet [id=%s, name=%s, manufacturingDate=%s, description=%s, colour=%s, status=%s]", id,
				name, manufacturingDate, description, colour, status);
	}
}
