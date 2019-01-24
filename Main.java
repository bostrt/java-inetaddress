import java.net.InetAddress;
import java.util.Date;
import java.security.Security;

public class Main {
  public static void main(String args[]) throws InterruptedException {
    String hostname = System.getenv("TEST_HOSTNAME");
    String debugStr = System.getenv("DEBUG");
    String delayStr = System.getenv("DELAY");
    String nocacheStr = System.getenv("NO_CACHE");
    boolean debug = false; // Debug off by default
    int delay = 10; // Default 10s delay between each query

    Security.setProperty("networkaddress.cache.ttl", "0");
    Security.setProperty("networkaddress.cache.negative.ttl", "0");
    
    if (hostname == null) {
        System.err.println("TEST_HOSTNAME environment variable must be set!");
        System.exit(1);
    }
    if (debugStr != null && debugStr.equals("true")) {
        debug = true;
    }
    if (delayStr != null) {
        delay = Integer.parseInt(delayStr);
    }
    while (true) {
      try {
	long start = System.currentTimeMillis();
        InetAddress addr = InetAddress.getByName(hostname);
	long finish = System.currentTimeMillis();
        if (debug) {
          Date now = new Date();
          System.out.println(now.toString());
          System.out.println(hostname + " => " + addr.toString());
	  System.out.println("Time to resolve (ms) => " + (finish - start));
	  System.out.println();
	}	
      } catch (Exception e) {
          Date now = new Date();
	  System.err.println(now.toString());
          e.printStackTrace();
      } finally {
	Thread.sleep(delay * 1000);
      }
    }
  }
}
