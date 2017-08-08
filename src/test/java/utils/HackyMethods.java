package utils;

public class HackyMethods {

	/**
	 * Sometimes the wait method does not work, so this is a quick hack 
	 * that will definitely work because of the while(true).
	 * @param t, the time in ms
	 */
	public static void sleep(int t){
		while(true) {
			try {
				Thread.sleep(t);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
	
}

