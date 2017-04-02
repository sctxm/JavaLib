package com.web.baidu;

import java.util.ArrayList;

import com.web.http.HttpClient;

public class LngLatConvert {

	public static GeoconvResponse toBDPoint(ArrayList<BDPoint> points) {

		StringBuffer sndBuf = new StringBuffer();

		try {
			sndBuf.append("http://api.map.baidu.com/geoconv/v1/?");
			sndBuf.append("ak=41rW84njTKMZKPj2ON9cDltc&from=1&to5&output=xml");

			StringBuffer sb2 = new StringBuffer();
			for (BDPoint point : points) {
				if (sb2.length() > 0) {
					sb2.append(";");
				}
				sb2.append(point.getX() + "," + point.getY());
			}

			sndBuf.append("&coords=" + sb2);
			//System.out.println(sndBuf);
			String xml = HttpClient.get(sndBuf.toString());
			if (xml.length() > 0) {
				//System.out.println(xml);

				GeoconvResponse gr = GeoconvResponse.fromXml(xml);
				return gr;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}

		return new GeoconvResponse();
	}

	public static void main(String[] args) {
		ArrayList<BDPoint> points = new ArrayList<BDPoint>();
		points.add(new BDPoint("114.21892734521", "29.575429778924"));
		points.add(new BDPoint("114.21892734521", "29.575429778924"));
		GeoconvResponse gr = LngLatConvert.toBDPoint(points);
		System.out.println(gr.toXml());
	    gr.output();
	}

}
