package com.uniba.udfs;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;

@Description(
	name = "haversinedistance",
	value = "_FUNC_(str) - Calculate Haversine distance between two geo-points",
	extended = "SELECT _FUNC_(LAT1, LNG1, LAT2, LNG2) FROM src LIMIT 1;"
)

public class UDFHaversineDistance extends UDF {
	private final int R = 6371;
		
	public DoubleWritable evaluate(DoubleWritable lat1, DoubleWritable lng1, DoubleWritable lat2, DoubleWritable lng2) {
		if (lat1 == null || lng1 == null || lat2 == null || lng2 == null) {
			return null;
		} else {
			return new DoubleWritable( haversineDistance(lat1.get(), lng1.get(), lat2.get(), lng2.get()) );
		}
	}
	
	private Double haversineDistance(Double lat1, Double lng1, Double lat2, Double lng2) {
		Double latDistance = Math.toRadians(lat2-lat1);
		Double lngDistance = Math.toRadians(lng2-lng1);
		
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
			Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * 
			Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		Double distance = R * c;
		return distance;
	}
}