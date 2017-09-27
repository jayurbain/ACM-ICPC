package a;
/**
 * 
 * A - Count Islands in Data Stream
 * urbain
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class A_Islands {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
        try {
            FileReader reader = new FileReader("data/in/a.in");
            BufferedReader bufferedReader = new BufferedReader(reader);
            FileWriter writer = new FileWriter("data/out/a.out");
 
            String line = bufferedReader.readLine().trim();
    		int n=0;
    		if( line.length() > 0 ) {
    			n = Integer.parseInt(line);
    		}
    		for(int i=1; i<=n; i++) {
    			int nIslands = 0;
    			int previous = 0;
    			int current = 0;
    			line = bufferedReader.readLine().trim();
    			String [] list = line.split(" ");
    			int index = Integer.parseInt(list[0]);
    			for(int j=1; j<=15; j++) {
    				current = Integer.parseInt( list[j] );
    				if(current > previous) {
    					nIslands++;
    				}
    				previous = current;
    			}
    			writer.write("" + index + " " + nIslands + "\n");
    		}
    		reader.close();
    		writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
