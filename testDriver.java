import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 */

/**
 * @author EdwardGuaman
 *
 */
public class testDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double avgt = 0;
		double sf = 0;
		long start;
		Node result;
		SimulatedAnnealing sa;
		int[] test = { 3, 2, 1, 4, 3, 2, 1, 2, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 1, 3, 6 };
		Schedule s = (t) -> {
			return Math.pow(Math.E, -(t * t) / 10000000);
		};
//		change  for number of tests
		int tests = 50;
		for (int j = 0; j < tests; j++) {
			shuffle(test);
			System.out.println("Before: ");
			for (int i : test)
				System.out.print(i + " ");
			System.out.println();
			sa = new SimulatedAnnealing();
			Problem o = new Problem(test);
			start = System.currentTimeMillis();
			result = sa.simulatedannealing(o, s);
			avgt += System.currentTimeMillis() - start;
			int[] r = result.getState();
			System.out.println("After: ");
			for (int i : r) {
				System.out.print(i + " ");
			}
			System.out.println();
			int fi = result.getValue();
			if (fi == 576) {
				System.out.println("Success");
				sf++;
			} else {
				System.out.println("Fail");
			} 
		}
		System.out.println("Success rate = " + sf/tests);
		System.out.println("Average time = " + avgt/tests + "ms");
	}

	public static void shuffle(int[] arr) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = 8; i > 0; i--) {
			int j = rnd.nextInt(i + 1);
			if (j != i) {
				arr[j] ^= arr[i];
				arr[i] ^= arr[j];
				arr[j] ^= arr[i];
			}
		}
	}

}
