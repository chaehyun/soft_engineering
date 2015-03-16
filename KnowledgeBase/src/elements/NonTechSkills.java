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
public enum NonTechSkills {
	TEAMWORK("Teamwork"),
	PRESENTATION("Presentation"),
	COMMUNICATION("Communication"),
	DRIVINGLICENCE("Driving licence"),
	QUICKLEARNING("Quick learning");
	
	
	private String value;
	
	private NonTechSkills(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
