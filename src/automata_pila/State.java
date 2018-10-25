package automata_pila;

import java.util.ArrayList;

public class State {

	private String label;
	private ArrayList<Transition> transitions;
	/**
	 * @param label
	 * @param transitions
	 */
	public State(String label, ArrayList<Transition> transitions) {
		this.label = label;
		this.transitions = transitions;
	}
	/**
	 * @param label
	 */
	public State(String label) {
		this.label = label;
		this.transitions = new ArrayList<Transition>();
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	
//	public void setLabel(String label) {
//		this.label =  label;
//	}
	
	public void addTransition(String nextState, Character tapeRead, String stackRead, String[] stackWrite, int label) {
		this.transitions.add(new Transition(nextState, tapeRead, stackRead, stackWrite, label));
	}
	
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
