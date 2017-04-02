package com.web.baidu;

public class GeoResult {
	private Location location;
	private String formatted_address;
	private String sematic_description;
	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	/**
	 * @return the formatted_address
	 */
	public String getFormatted_address() {
		return formatted_address;
	}
	/**
	 * @param formatted_address the formatted_address to set
	 */
	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}
	/**
	 * @return the sematic_description
	 */
	public String getSematic_description() {
		return sematic_description;
	}
	/**
	 * @param sematic_description the sematic_description to set
	 */
	public void setSematic_description(String sematic_description) {
		this.sematic_description = sematic_description;
	}
	
	
}
