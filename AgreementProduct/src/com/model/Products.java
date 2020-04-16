package com.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Alberto
 *
 */
public class Products implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String parent; //Es ambigua la definición, ya que puede ser un identificador de producto o un identificador de acuerdo.
	
	private List<Products> childProductsList;
	private String name;
	private Double price;
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public List<Products> getChildProductsList() {
		return childProductsList;
	}
	public void setChildProductsList(List<Products> childProductsList) {
		this.childProductsList = childProductsList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
