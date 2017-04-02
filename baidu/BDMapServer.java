package com.web.baidu;

import java.util.ArrayList;

import com.web.http.HttpClient;

public class BDMapServer {

	public static BDAddress getBDAddress(String lat, String lng)
			throws Exception {

		if ((lat.length() > 0) && (lng.length() > 0)) {
			String latlng = lat + "," + lng;
			String url = String.format(BDConsts.bd_url, BDConsts.bd_ak, latlng);

			String xml = HttpClient.get(url);
			BDAddress bd = new BDAddress(xml);
			return bd;
		} else {
			return new BDAddress();
		} 
	}
	
	public static ArrayList<BDAddress> getBDAddresses(ArrayList<Location> locs){
		ArrayList<BDAddress> list = new ArrayList<BDAddress>();
		
		return list;
	}
	
	public static void main(String[] args) throws Exception{
		BDMapServer.getBDAddress("30.7688163308", "106.003255718");
	}
}
