package b;

/**
 * 
 * B - Von Neumann's Fly
 * urbain
 * 
 * Distance = Rate * Time.
 * Given d (the initial distance), a (cyclist A's speed) and 
 * b (cyclist B's speed), compute the time at which
 * A and B meet, t:
 *      a*t = the distance traveled by A when they meet
 *      b*t = the distance traveled by B when they meet
 *      a*t + b*t = d
 *      (a + b)*t = d
 *      t = d/(a + b)
 * Multiply t by f (the fly's speed) to determine
 * the distance traveled by the fly in that time.
 *
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class B_VonNeumannsFly {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			FileReader reader = new FileReader("data/in/b.in");
			BufferedReader bufferedReader = new BufferedReader(reader);
			FileWriter writer = new FileWriter("data/out/b.out");

			String line = bufferedReader.readLine().trim();
			int n=0;
			if( line.length() > 0 ) {
				n = Integer.parseInt(line);
			}
			NumberFormat formatter = new DecimalFormat("#0.00"); 
			for(int i=0; i<n; i++) {
				// data set n, miles traveled by fly
				// initial d between bikes 10<=D<=1000
				// A cyclist speed mph
				// B cyclist speed mph
				// F fly's speed mph
				line = bufferedReader.readLine().trim();
				String [] list = line.split(" ");
				int index = Integer.parseInt(list[0]);
				double d = Double.parseDouble(list[1]);
				double A = Double.parseDouble(list[2]);
				double B = Double.parseDouble(list[3]);
				double F = Double.parseDouble(list[4]);
				double t = d/(A + B);
				double Fd = t*F;

				writer.write("" + index + " " + formatter.format(Fd) + "\n");
			}
			reader.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

