package nl.plaatsoft.redsquare.network;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import nl.plaatsoft.redsquare.tools.Constants;

/**
 * The Class CloudUser.
 * 
 * @author wplaat
 */
public class CloudUser {

	/** The Constant log. */
	private static final Logger log = Logger.getLogger( CloudUser.class);
	
	/** The uid. */
	private static int uid=0;
	
	/** The nick name. */
	private static String nickName = System.getProperty("user.name");
	
	/**
	 * Gets the.
	 */
	public static void get() {

		String parameters = "action=getUser"+
				"&username=" + System.getProperty("user.name") +
				"&nickname=" + nickName +
				"&country=" +  CloudGeoCode.getCountry() +
				"&city=" +  CloudGeoCode.getCity();
						
		log.info(Constants.APP_WS_URL+ " "+parameters);
		String json = CloudUtils.executePost(Constants.APP_WS_URL, parameters);
		log.info(json);
		
		try {
			JSONObject obj = new JSONObject(json);
			uid = obj.getInt("uid");
			nickName = obj.getString("nickname");		
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	/**
	 * Sets the.
	 *
	 * @param newNickName the new nick name
	 */
	public static void set(String newNickName) {

		String parameters = "action=setUser"+
				"&username=" + System.getProperty("user.name") +
				"&nickname=" + newNickName;
						
		log.info(Constants.APP_WS_URL+ " "+parameters);
		String json = CloudUtils.executePost(Constants.APP_WS_URL, parameters);
		log.info(json);
		
		try {
			JSONObject obj = new JSONObject(json);
			uid = obj.getInt("uid");
			nickName = obj.getString("nickname");		
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	/**
	 * Gets the uid.
	 *
	 * @return the uid
	 */
	public static int getUid() {
		if (uid==0) {
			get();
		}
		return uid;
	}
	
	/**
	 * Gets the nickname.
	 *
	 * @return the nickname
	 */
	public static String getNickname() {
		return nickName;
	}
}

