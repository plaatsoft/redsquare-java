package nl.plaatsoft.redsquare.network;

import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 * The Class CloudGeoCode.
 * 
 * @author wplaat
 */
public class CloudGeoCode {

	/** The Constant log. */
	private static final  Logger log = Logger.getLogger( CloudGeoCode.class);
	
	/** The country. */
	private static String country;
	
	/** The city. */
	private static String city;
			
	/**
	 * Fetch.
	 */
	private static void fetch() {
		
		String url = "http://freegeoip.net/json";
			
		log.info(url);		
		String json = CloudUtils.executeGet(url);
		log.info(json);
		
		try {
			JSONObject obj = new JSONObject(json);
			country = obj.getString("country_code");
			city = obj.getString("city");
				
		} catch (Exception e) {
			log.error(e.getMessage());
		}		
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public static String getCountry() {
		
		if (country==null) {
			fetch();
		}
		return country.toLowerCase();
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public static String getCity() {
		if (city==null) {
			fetch();
		}
		return city.toLowerCase();
	}
}
