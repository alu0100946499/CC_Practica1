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
	
	public void addTransition(String nextState, char tapeRead, String stackRead, String stackWrite, String label) {
		this.transitions.add(new Transition(nextState, tapeRead, stackRead, stackWrite, label));
	}
	
	public ArrayList<Transition> getTransitions(char tapeRead, String stackRead){
		ArrayList<Transition> output = new ArrayList<Transition>();
		
		for(int i = 0; i < this.transitions.size(); i++)
			if((tapeRead == this.transitions.get(i).getTapeRead() || this.transitions.get(i).getTapeRead() == FALTA PONER ESTA CONSTANTE) && stackRead == this.transitions.get(i).getStackRead())
				output.add(this.transitions.get(i));
		
		return output;
	}
	
	
	
}
