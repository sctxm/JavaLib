package com.web.baidu;

import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GeoconvResponse {
	private int status;
	private ArrayList<BDPoint> result;

	public GeoconvResponse() {
		result = new ArrayList<BDPoint>();
		clear();
	}
	
	public void clear(){
		status = 1;
		result.clear();
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the result
	 */
	public ArrayList<BDPoint> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(ArrayList<BDPoint> result) {
		this.result = result;
	}

	public String toXml() {
		String xml = "";
		XStream xstream = new XStream(new DomDriver()); // does not require XPP3
		// library

		xstream.alias("GeoconvResponse", GeoconvResponse.class);
		xstream.alias("point", BDPoint.class);

		try {
			xml = xstream.toXML(this);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return xml;
	}
	
	public static GeoconvResponse fromXml(String xml){	 
		 
		XStream xstream = new XStream(new DomDriver()); // does not require XPP3
		// library

		xstream.alias("GeoconvResponse", GeoconvResponse.class);
		xstream.alias("point", BDPoint.class);

		try {
			GeoconvResponse gr = (GeoconvResponse)xstream.fromXML(xml);
			return gr;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			return new GeoconvResponse();
		} 
		
	}
	
	public void output(){
		for(BDPoint point : result){
			point.output();
		}
	}

	public static void main(String[] args) {
		GeoconvResponse gr = new GeoconvResponse();
		gr.getResult().add(new BDPoint("123.123123", "32.2344"));
		gr.getResult().add(new BDPoint("123.14123", "32.12344"));
		System.out.println(gr.toXml());
		gr.output();
	}

}
