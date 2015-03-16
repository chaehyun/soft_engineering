/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;


/**
 *
 * @author szedjani
 */
public enum TechSkills {
	JAVA("Java"),
	CPP("C++"),
	SQL("SQL"),
	ORACLE("Oracle SQL"),
	OOP("Object-Oriented Programming"),
	APACHE("Apache server");
	
	private String value;
	
	private TechSkills(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
