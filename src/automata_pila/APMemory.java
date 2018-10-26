package automata_pila;

import java.util.Stack;

/**
 * This class represents the situation of the automaton in a certain instant. It is use to restore the automaton to check all possibles path.
 * @author Javier Esteban Pérez Rivas (alu0100946499@ull.edu.es)
 *
 */
public class APMemory {
	String word;
	Stack<String> stack;
	State actualState;

	public APMemory(String word, Stack<String> stack, State actualState) {
		this.word = word;
		this.stack = (Stack<String>) stack.clone();
		this.actualState = actualState;
	}

	public State getActualState() {
		return actualState;
	}


	public String getWord() {
		return word;
	}


	public Stack<String> getStack() {
		return (Stack<String>) stack.clone();
	}

}
