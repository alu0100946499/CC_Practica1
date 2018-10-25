package automata_pila;

import java.util.Stack;

public class APMemory {
	String word;
	Stack<String> stack;

	/**
	 * @param word
	 * @param stack
	 */
	public APMemory(String word, Stack<String> stack) {
		this.word = word;
		this.stack = (Stack<String>) stack.clone();
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @return the stack
	 */
	public Stack<String> getStack() {
		return (Stack<String>) stack.clone();
	}

}
