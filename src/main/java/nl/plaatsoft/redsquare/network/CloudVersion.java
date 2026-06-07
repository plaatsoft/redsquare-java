package nl.plaatsoft.redsquare.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;

import nl.plaatsoft.redsquare.tools.Constants;

public class CloudVersion {
	
	private static final Logger log = LogManager.getLogger( CloudVersion.class);
			
	private CloudVersion() {
	    throw new IllegalStateException("CloudNewVersion class");
    }
	
	public static String get() {

		String returnValue="";		
		String parameters = "action=getVersion&product="+Constants.APP_WS_NAME; 
						
		log.info("TX: {}?{}", Constants.APP_WS_URL, parameters);		
		String json = CloudUtils.executePost(Constants.APP_WS_URL, parameters);
		log.info("RX: {}", json);
		
		try {
			JSONObject obj = new JSONObject(json);
			String newVersion = obj.getString("version");
			if (newVersion.compareTo(Constants.APP_VERSION)>0) {
				returnValue = Constants.APP_NAME+" v"+newVersion+" available.";
			}
			
		} catch (Exception e) {
			returnValue = "No Internet connection!";
			log.error(e.getMessage());			
		}

		return returnValue;			
	}
}
