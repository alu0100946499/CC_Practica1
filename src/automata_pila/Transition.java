package automata_pila;

import java.util.Arrays;

public class Transition {

	private String nextState;
	private Character tapeRead;
	private String stackRead;
	private String[] stackWrite;
	private int label;
	
	/**
	 * @param nextState
	 * @param tapeRead
	 * @param stackRead
	 * @param stackWrite
	 * @param label
	 */
	public Transition(String nextState, Character tapeRead, String stackRead, String[] stackWrite, int label) {
		this.nextState = nextState;
		this.tapeRead = tapeRead;
		this.stackRead = stackRead;
		this.stackWrite = stackWrite;
		this.label = label;
	}

	/**
	 * @return the nextState
	 */
	public String getNextState() {
		return nextState;
	}

	/**
	 * @return the tapeRead
	 */
	public Character getTapeRead() {
		return tapeRead;
	}

	/**
	 * @return the stackRead
	 */
	public String getStackRead() {
		return stackRead;
	}

	/**
	 * @return the stackWrite
	 */
	public String[] getStackWrite() {
		return stackWrite;
	}

	/**
	 * @return the label
	 */
	public int getLabel() {
		return label;
	}

	@Override
	public String toString() {
		String output = "(" + this.nextState + ", " + this.tapeRead + ", " + this.stackRead + ", ";
		for(int i = 0; i < this.stackWrite.length; i++)
			output += this.stackWrite[i];
		output += ")";
		return output;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transition other = (Transition) obj;
		if (label != other.label)
			return false;
		if (nextState == null) {
			if (other.nextState != null)
				return false;
		} else if (!nextState.equals(other.nextState))
			return false;
		if (stackRead == null) {
			if (other.stackRead != null)
				return false;
		} else if (!stackRead.equals(other.stackRead))
			return false;
		if (!Arrays.equals(stackWrite, other.stackWrite))
			return false;
		if (tapeRead != other.tapeRead)
			return false;
		return true;
	}
	
	
	
	
	
}
