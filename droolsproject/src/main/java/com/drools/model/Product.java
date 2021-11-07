package com.drools.model;

public class Product {
	
	private String type;
	private int Gst;  // Tax
	private int price;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getGst() {
		return Gst;
	}
	public void setGst(int gst) {
		Gst = gst;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
