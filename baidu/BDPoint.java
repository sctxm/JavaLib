package com.web.baidu;

public class BDPoint {
	private String x;
	private String y;
	
	public BDPoint(){
		x = "";
		y = "";
	}
	
	public BDPoint(String x, String y){
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public String getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(String x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public String getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(String y) {
		this.y = y;
	}
	
	public void output(){
		System.out.println(String.format("X=%s,Y=%s",x,y));
	}
	
}
