import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChekcRunnedApp {
	  public static void main(String[] args) throws IOException {
		  
	        boolean teamsRunning = isProcessRunning("Teams.exe");
	        
	        if (teamsRunning) {
	        	System.out.println("Microsoft Teams already running.");
	        } else {
	        	Runtime.getRuntime().exec("C:\\Users\\Setup Game\\AppData\\Local\\Microsoft\\Teams\\current\\Teams.exe", null, new File("C:\\Users\\Setup Game\\AppData\\Local\\Microsoft\\Teams\\current"));
	        }
	    }

	    public static boolean isProcessRunning(String processName) {
	        try {
	            Process process = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");

	            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            String line;
	            while ((line = reader.readLine()) != null) {
	                if (line.contains(processName)) {
	                    return true;
	                }
	            }

	            reader.close();
	            process.waitFor();
	        } catch (IOException | InterruptedException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
}
