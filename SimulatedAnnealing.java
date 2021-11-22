import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 */

/**
 * @author EdwardGuaman
 *
 */
public class SimulatedAnnealing {
	public Node simulatedannealing(Problem p, Schedule s) {
		Node current;
		Node next;
		double T;
		int count = 0;
		current = new Node(p.getInitialState());
		for (int t = 1; t < 100000; t++) {
			T = s.schedule(t);
//			may remove goal check
			if (T == 0 || current.getValue() == (current.getState().length - 1) * (current.getState().length - 1)) {
//				System.out.println(t);
				System.out.println("Steps " + count);
				return current;
			}
			next = new Node(current);
			int de = next.getValue() - current.getValue();
			if (de > 0) {
				current = next;
				count++;
			} else if (Math.random() <= Math.pow(Math.E, de / T)) {
				current = next;
				count++;
			}
		}
		return null;
	}
}

class Node {
	private int[] state;
	private int value;
	Random r = ThreadLocalRandom.current();

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int[] getState() {
		return state;
	}

	public void setState(int[] state) {
		this.state = state;
	}

	Node(int[] is) {
		state = Arrays.copyOf(is, is.length);
		value();
	}

	Node(Node n) {
		state = Arrays.copyOf(n.getState(), n.getState().length);
		int index = r.nextInt(state.length);
		int old = state[index];
		while (state[index] == old) {
			state[index] = r.nextInt(state.length);
		}
		value();
	}

//	should return number of nonconflicting queens 
	private void value() {
//		may need to change 1 to 2
		int counter = 0;
		for (int i = 0; i < state.length - 1; i++) {
			for (int j = i + 1; j < state.length; j++) {
				int di = j - i;
				int dq = Math.abs(state[i] - state[j]);
				if (state[i] == state[j] || di == dq) {
					counter++;
				}
			}
		}
		value = ((state.length - 1) * (state.length - 1)) - counter;
	}
}
