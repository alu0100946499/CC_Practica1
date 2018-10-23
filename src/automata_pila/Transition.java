package automata_pila;

public class Transition {

	private String nextState;
	private char tapeRead;
	private String stackRead;
	private String stackWrite;
	private String label;
	
	/**
	 * @param nextState
	 * @param tapeRead
	 * @param stackRead
	 * @param stackWrite
	 * @param label
	 */
	public Transition(String nextState, char tapeRead, String stackRead, String stackWrite, String label) {
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
	public char getTapeRead() {
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
	public String getStackWrite() {
		return stackWrite;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	
	
}
