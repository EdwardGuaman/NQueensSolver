import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 */

/**
 * @author EdwardGuaman
 *
 */
public class Genetic {
//	population most likely will be a list of nodes
	public GNode genetic(ArrayList<GNode> population, fitness fit) {
		long start = System.currentTimeMillis();
		long end = start + 2 * 1000;
		Random rd = ThreadLocalRandom.current();
		GNode x;
		GNode y;
		GNode child;
		int gen = 0;
//		checking most fit individual
		do {
			ArrayList<GNode> npop = new ArrayList<>();
			assert population.size() % 2 == 0;
			for (int i = 0; i < population.size(); i++) {
				x = population.get(rd.ints(1, (population.size() - 1) / 2).limit(1).findFirst().getAsInt());
				y = population.get(rd.ints(1, (population.size() - 1) / 2).limit(1).findFirst().getAsInt());
				while (x.equals(y)) {
					y = population.get(rd.ints(1, (population.size() - 1) / 2).limit(1).findFirst().getAsInt());
				}
//				for (int i = 0; i < population.size(); i++) {
				child = Reproduce(x, y, fit);
				if (Math.random() < .75) {
					child.mutate(fit);
				}
				npop.add(child);
//				}
			}
			population = npop;
			Collections.sort(population);
			gen++;
		} while ((population.get(0).getValue()) != 0 && System.currentTimeMillis() < end);
		System.out.println("Number of generations: " + gen);
		return population.get(0);
	}

	private GNode Reproduce(GNode x, GNode y, fitness f) {
		Random r = ThreadLocalRandom.current();
		int[] temp = y.getState();
		int n = x.getState().length;
		int c = r.ints(1, n - 2).limit(1).findFirst().getAsInt();
		int[] rep = Arrays.copyOf(x.getState(), n);
		for (int i = c; i < n; i++) {
			rep[i] = temp[i];
		}
		return new GNode(rep, f);
	}

}
