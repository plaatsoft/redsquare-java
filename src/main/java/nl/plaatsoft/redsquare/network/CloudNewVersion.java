package nl.plaatsoft.redsquare.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;

import nl.plaatsoft.redsquare.tools.Constants;

/**
 * The Class CloudNewVersion.
 * 
 * @author wplaat
 */
public class CloudNewVersion {
	
	/** The Constant log. */
	private static final Logger log = LogManager.getLogger( CloudNewVersion.class);
		
	/**
	 * Gets the.
	 *
	 * @return the string
	 */
	public static String get() {

		String returnValue="";		
		String parameters = "action=getVersion&product="+Constants.APP_WS_NAME; 
						
		log.info(Constants.APP_WS_URL+ " "+parameters);
		String json = CloudUtils.executePost(Constants.APP_WS_URL, parameters);
		log.info(json);
		
		try {
			JSONObject obj = new JSONObject(json);
			String newVersion = obj.getString("product");
			if (Float.parseFloat(newVersion)>Float.parseFloat(Constants.APP_VERSION)) {
				returnValue = Constants.APP_NAME+" v"+newVersion+" available.";
			}
			
		} catch (Exception e) {
			returnValue = "No Internet connection!";
			log.error(e.getMessage());			
		}

		return returnValue;			
	}
}
