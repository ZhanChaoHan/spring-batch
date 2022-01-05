package com.jachs.j_se_batch.entity;

/***
 * 
 * @author zhanchaohan
 *
 */
public class Dog {
	private String dName;
	private int dAge;
	
	public Dog() {
		super();
	}
	public Dog(String dName, int dAge) {
		super();
		this.dName = dName;
		this.dAge = dAge;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public int getdAge() {
		return dAge;
	}
	public void setdAge(int dAge) {
		this.dAge = dAge;
	};
	
}
