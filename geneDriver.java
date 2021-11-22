import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 */

/**
 * @author EdwardGuaman
 *
 */
public class geneDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random r = ThreadLocalRandom.current();
		double sf = 0;
		double avgt = 0;
		fitness f1 = (pop) -> {
			int counter = 0;
			for (int i = 0; i < pop.length - 1; i++) {
				for (int j = i + 1; j < pop.length; j++) {
					int di = j - i;
					int dq = Math.abs(pop[i] - pop[j]);
					if (pop[i] == pop[j] || di == dq) {
						counter++;
					}
				}
			}
			return counter;
		};
//		change this for number of test
		int testnumber = 10;
		for (int k = 0; k < testnumber; k++) {
			ArrayList<GNode> test = new ArrayList<>();
//			change this loop for population size
			for (int i = 0; i < 100; i++) { //150 .8
				int[] temp = new int[25];
				for (int j = 0; j < temp.length; j++) {
					temp[j] = r.nextInt(temp.length);
				}
				test.add(new GNode(temp, f1));
			}
			Genetic g = new Genetic();
			long start = System.currentTimeMillis();
			GNode res = g.genetic(test, f1);
			avgt += System.currentTimeMillis() - start;
			int check = res.getValue();
			if (check == 0) {
				System.out.println("Success");
				sf++;
			} else {
				System.out.println("Fail");
			}
			int[] result = res.getState();
			System.out.println("Result");
			for (int i : result) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		System.out.println("Success rate = " + sf / testnumber);
		System.out.println("Average time = " + avgt / testnumber + "ms");

	}

}
