package nl.plaatsoft.redsquare.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;

import nl.plaatsoft.redsquare.tools.Constants;

public class CloudProduct {

	private static final Logger log = LogManager.getLogger( CloudProduct.class);
	
	private static int pid=0;

	private CloudProduct() {
	    throw new IllegalStateException("CloudProduct class");
    }
	
	public static void fetch() {

		String parameters = "action=getProduct"+
				"&product=" + Constants.APP_WS_NAME+
				"&version=" + Constants.APP_VERSION+
				"&os="+System.getProperty("os.name").replace(" ","");
						
		log.info("TX: {}?{}",Constants.APP_WS_URL, parameters);
		String json = CloudUtils.executePost(Constants.APP_WS_URL, parameters);
		log.info("RX: {}", json);
		
		try {
			JSONObject obj = new JSONObject(json);
			pid = obj.getInt("pid");		
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public static int getPid() {
		if (pid==0) {
			fetch();
		}
		return pid;
	}
}
