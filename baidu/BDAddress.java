package com.web.baidu;

import com.web.http.HttpClient;

public class BDAddress {
	private String lat;
	private String lng;
	private String formatAddress;
	private String sematicAddress;

	public BDAddress() {
		lat = "";
		lng = "";
		formatAddress = "";
		sematicAddress = "";
	}

	public BDAddress(String xml) {
		lat = getLat(xml);
		lng = getLng(xml);
		formatAddress = getFormatAddress(xml);
		sematicAddress = getSematicAddress(xml);
	}

	private String getLat(String xml) {
		int beg = xml.indexOf("<lat>");
		if (beg < 1) {
			return "";
		}
		int end = xml.indexOf("</lat>");
		if (end < 1) {
			return "";
		}

		return xml.substring(beg + 5, end);
	}

	private String getLng(String xml) {
		int beg = xml.indexOf("<lng>");
		if (beg < 1) {
			return "";
		}
		int end = xml.indexOf("</lng>");
		if (end < 1) {
			return "";
		}

		return xml.substring(beg + 5, end);
	}

	/**
	 * formatted_address
	 * 
	 * @param xml
	 * @return
	 */
	private String getFormatAddress(String xml) {
		int beg = xml.indexOf("<formatted_address>");
		if (beg < 1) {
			return "";
		}
		int end = xml.indexOf("</formatted_address>");
		if (end < 1) {
			return "";
		}

		return xml.substring(beg + 19, end);
	}

	/**
	 * sematic_description
	 * 
	 * @param xml
	 * @return
	 */
	private String getSematicAddress(String xml) {
		int beg = xml.indexOf("<sematic_description>");
		if (beg < 1) {
			return "";
		}
		int end = xml.indexOf("</sematic_description>");
		if (end < 1) {
			return "";
		}

		return xml.substring(beg + 21, end);
	}

	/**
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * @param lat
	 *            the lat to set
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}

	/**
	 * @return the lng
	 */
	public String getLng() {
		return lng;
	}

	/**
	 * @param lng
	 *            the lng to set
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}

	/**
	 * @return the formatAddress
	 */
	public String getFormatAddress() {
		return formatAddress;
	}

	/**
	 * @param formatAddress
	 *            the formatAddress to set
	 */
	public void setFormatAddress(String formatAddress) {
		this.formatAddress = formatAddress;
	}

	/**
	 * @return the sematicAddress
	 */
	public String getSematicAddress() {
		return sematicAddress;
	}

	/**
	 * @param sematicAddress
	 *            the sematicAddress to set
	 */
	public void setSematicAddress(String sematicAddress) {
		this.sematicAddress = sematicAddress;
	}

	public static void main(String[] args) throws Exception {
		String ak = "41rW84njTKMZKPj2ON9cDltc";
		String latlng = "30.765443,105.992947";
		String url = String
				.format("http://api.map.baidu.com/geocoder/v2/?ak=%s&callback=renderReverse&coordtype=wgs84ll&location=%s&output=xml&pois=1",
						ak, latlng);
		System.out.println(url);

		String xml = HttpClient.get(url);
		System.out.println(xml);

		BDAddress bda = new BDAddress(xml);
		System.out.println(bda.getLat());
		System.out.println(bda.getLng());
		System.out.println(bda.getFormatAddress());
		System.out.println(bda.getSematicAddress());
	}

}
