package com.jachs.j_se_batch.entity;

/***
 * 
 * @author zhanchaohan
 *
 */
public class Cat {
	private String hName;
	private int hAge;
	
	public Cat() {
		super();
	}
	public Cat(String hName, int hAge) {
		super();
		this.hName = hName;
		this.hAge = hAge;
	}
	public String gethName() {
		return hName;
	}
	public void sethName(String hName) {
		this.hName = hName;
	}
	public int gethAge() {
		return hAge;
	}
	public void sethAge(int hAge) {
		this.hAge = hAge;
	}
	
}
