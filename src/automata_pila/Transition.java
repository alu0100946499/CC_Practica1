package automata_pila;

import java.util.Arrays;

/**
 * This class represents a transition for a Pushdown automaton.
 * @author Javier Esteban Pérez Rivas (alu0100946499@ull.edu.es)
 *
 */
public class Transition {

	private String nextState; 
	private Character tapeRead;
	private String stackRead;
	private String[] stackWrite;
	private int label;
	
	/**
	 * @param nextState 
	 * @param tapeRead Symbol that is read from the input tape.
	 * @param stackRead Symbol that is read from the stack
	 * @param stackWrite Symbol or symbols that will be written in the stack
	 * @param label This value is use to enumerate the transitions. The order is based on how the transitions were at the specification file.
	 */
	public Transition(String nextState, Character tapeRead, String stackRead, String[] stackWrite, int label) {
		this.nextState = nextState;
		this.tapeRead = tapeRead;
		this.stackRead = stackRead;
		this.stackWrite = stackWrite;
		this.label = label;
	}


	public String getNextState() {
		return nextState;
	}


	public Character getTapeRead() {
		return tapeRead;
	}


	public String getStackRead() {
		return stackRead;
	}

	
	public String[] getStackWrite() {
		return stackWrite;
	}


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
