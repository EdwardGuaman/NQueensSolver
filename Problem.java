import java.util.Arrays;

/**
 * 
 */

/**
 * @author EdwardGuaman
 *
 */
public class Problem {
	int[] initialState;
	
	Problem(int[] is){
		initialState = Arrays.copyOf(is, is.length);
	}

	public int[] getInitialState() {
		return initialState;
	}

	public void setInitialState(int[] initialState) {
		this.initialState = initialState;
	}
	
}
