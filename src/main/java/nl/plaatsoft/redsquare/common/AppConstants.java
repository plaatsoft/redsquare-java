package nl.plaatsoft.redsquare.common;

public class AppConstants {

	private AppConstants() {
	    throw new IllegalStateException("Constants class");
    }
		
	public static final String APP_NAME = "RedSquare";
	public static final String APP_VERSION = "1.0.0";
	public static final String APP_BUILD = "Build (07-06-2026)";
	public static final String APP_WS_NAME = "Java-RedSquare";
	public static final String APP_WS_URL = "https://service.plaatsoft.nl";
	public static final int BORDER_SIZE = 35;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	// Calculate frame rate
	public static final double TARGET_FPS = 100.0;
	public static final double TIME_PER_FRAME = 1_000_000_000.0 / TARGET_FPS;
}
