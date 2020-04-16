package com.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Alberto
 *
 */
public class Agreement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String signedBy;
	private List<Products> products;  //La regla de negocio que indica, desde mi perspectiva, se requiere validarlos desde base de datos o algún otro elemento de persistencia
									//ya que habría que verificar los productos que se agregan desde la petición, cuando llegue al backend, pero contra qué se debe 
									//revisar, si no existen las referencias previas.
	public List<Products> getProducts() {
		return products;
	}
	public void setProducts(List<Products> products) {
		this.products = products;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSignedBy() {
		return signedBy;
	}
	public void setSignedBy(String signedBy) {
		this.signedBy = signedBy;
	}


}
