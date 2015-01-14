package it.accenture.prova.utils;

import java.io.Serializable;

public class D3PieConverter implements Serializable{

	private static final long serialVersionUID = -7162369296034046712L;
	private String id;
	private String label;
	private int value;
	private String color;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	

}
