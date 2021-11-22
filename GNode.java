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
public class GNode implements Comparable<GNode> {
	private int[] state;
	private int value;

	public GNode(int[] s, fitness f) {
		state = Arrays.copyOf(s, s.length);
		value = f.fitnessfn(state);
	}

	public void mutate(fitness f) {
		Random t = ThreadLocalRandom.current();
		state[t.nextInt(state.length)] = t.nextInt(state.length);
		value = f.fitnessfn(state);
	}

	public int[] getState() {
		return state;
	}

	public void setState(int[] state) {
		this.state = state;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

// may need to swap the 1's
	@Override
	public int compareTo(GNode arg0) {
		if (arg0.getValue() == value)
			return 0;
		else if (arg0.getValue() > value)
			return -1;
		else
			return 1;
	}

}
