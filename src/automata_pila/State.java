package automata_pila;

import java.util.ArrayList;

/**
 * This class represents a state for a Pushdown automaton.
 * @author Javier Esteban Pérez Rivas (alu0100946499@ull.edu.es)
 *
 */
public class State {

	private String label;
	private ArrayList<Transition> transitions;
	
	/**
	 * @param label Name of the State
	 * @param transitions Set of transitions
	 */
	public State(String label, ArrayList<Transition> transitions) {
		this.label = label;
		this.transitions = transitions;
	}

	
	public State(String label) {
		this.label = label;
		this.transitions = new ArrayList<Transition>();
	}

	
	public String getLabel() {
		return label;
	}
	
	
	/**
	 * Add a new transition to the transition's set
	 * @param nextState 
	 * @param tapeRead Symbol that is read from the input tape.
	 * @param stackRead Symbol that is read from the stack
	 * @param stackWrite Symbol or symbols that will be written in the stack
	 * @param label This value is use to enumerate the transitions. The order is based on how the transitions were at the specification file.
	 */
	public void addTransition(String nextState, Character tapeRead, String stackRead, String[] stackWrite, int label) {
		this.transitions.add(new Transition(nextState, tapeRead, stackRead, stackWrite, label));
	}
	
	/**
	 * Return all the possibles moves with the values specified.
	 * @param tapeRead First symbol of the currently word.
	 * @param stackRead First symbol of the stack.
	 * @return
	 */
	public ArrayList<Transition> getTransitions(Character tapeRead, String stackRead){
		ArrayList<Transition> output = new ArrayList<Transition>();
		
		for(int i = 0; i < this.transitions.size(); i++)
			if((this.transitions.get(i).getTapeRead().equals(tapeRead) || this.transitions.get(i).getTapeRead().equals(AP.EPSYLON_SYMBOL)) && this.transitions.get(i).getStackRead().equals(stackRead))
				output.add(this.transitions.get(i));
		
		return output;
	}
	
	@Override
	public String toString() {
		String output = this.label + ":\n";
		for(int i = 0; i < transitions.size(); i++)
			output += "\t\t\t" + transitions.get(i) + "\n";
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
		State other = (State) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (transitions == null) {
			if (other.transitions != null)
				return false;
		} else if (!transitions.equals(other.transitions))
			return false;
		return true;
	}
}
