package automata_pila;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 * This class represents a Pushdown automaton.
 * @author Javier Esteban Pérez Rivas (alu0100946499@ull.edu.es)
 *
 */
public class AP {
	
	private static final String COMMENTARY_REGEX = "\\s*#.*"; //Regex used to remove the comentaries
	private static final String WHITE_SPACES_REGEX = "\\s+"; //Regex used to detec white spaces
	public static final Character EPSYLON_SYMBOL = '.';
	
	private String word;
	private ArrayList<State> states = null; //Set of states
	private ArrayList<String> sigma = null; //Input alphabet
	private ArrayList<String> gamma = null; //Stack alphaber
	private State actualState; 
	private String firstStack; //Symbol that is at the stack when the AP is initialized
	private ArrayList<State> finalStates = null; 
	private Stack<String> stack = null;
	private Stack<APMemory> memoryStack = null; //Stack that store the situation of the AP in a certain instant
	private Boolean mode; // If True, final state AP, else, stack emptying AP.
	private Boolean trace; // True if the trace must be shown

	/**
	 * @param filename File where the AP is defined.
	 * @param mode  True, final state AP, else, stack emptying AP.
	 */
	public AP(String filename, Boolean mode) {
		this.word = "";
		this.mode = mode;
		this.trace = false;
		this.states = new ArrayList<State>();
		this.sigma = new ArrayList<String>();
		this.gamma = new ArrayList<String>();
		this.finalStates = new ArrayList<State>();
		this.stack = new Stack<String>();
		this.memoryStack = new Stack<APMemory>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line = "";
			
			
			do{
				line = reader.readLine().replaceAll(COMMENTARY_REGEX, "");
			} while(line.equals(""));
			
				
			generateStates(line.split(WHITE_SPACES_REGEX));
			generateSigma(reader.readLine().replaceAll(COMMENTARY_REGEX, "").split(WHITE_SPACES_REGEX));
			generateGamma(reader.readLine().replaceAll(COMMENTARY_REGEX, "").split(WHITE_SPACES_REGEX));
			
			
			int aux = inStates(reader.readLine().replaceAll(COMMENTARY_REGEX, "").replaceAll(WHITE_SPACES_REGEX, ""));
			if(aux != -1) {
				actualState = states.get(aux);
			}
			else {
				throw new IOException("First state is not in the states group");
			}
			
			
			aux = gamma.indexOf(reader.readLine().replaceAll(COMMENTARY_REGEX, "").replaceAll(WHITE_SPACES_REGEX, ""));
			if(aux != -1) {
				firstStack = gamma.get(aux);
				this.stack.push(gamma.get(aux));
			}
			else {
				throw new IOException("First stack symbol is not in the stack alphabet");
			}
			
			if(mode) {
				genererateFinalStates(reader.readLine().replaceAll(COMMENTARY_REGEX, "").split(WHITE_SPACES_REGEX));
			}
			
			
			int counter = 1;
			while(reader.ready()) {
				String[] transitionTokens = reader.readLine().replaceAll(COMMENTARY_REGEX, "").split(WHITE_SPACES_REGEX);
				int index = inStates(transitionTokens[0]);
				if(index != -1) {
					String[] stackWrite = new String[transitionTokens.length-4];
					for(int i = 0; i < stackWrite.length; i++)
						stackWrite[i] = transitionTokens[i+4];
					
					this.states.get(index).addTransition(transitionTokens[3], transitionTokens[1].charAt(0), transitionTokens[2], stackWrite, counter);
					counter++;
				}
				else
					throw new IOException("Invalid transition. " + transitionTokens[0] + " is not defined");
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("" + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error opening the file. " + e.getMessage());
			
		}
	}
	
	
	public String getWord() {
		return word;
	}


	public void setWord(String word) {
		this.word = word;
	}

	
	public Boolean getTrace() {
		return trace;
	}


	public void setTrace(Boolean trace) {
		this.trace = trace;
	}

	
	@Override
	public String toString() {
		String output = "";
		
		if(mode)
			output += "APf:\n";
		else
			output += "APv:\n";
		
		output += "\tStates:\n";
		for(int i = 0; i < this.states.size(); i++)
			output += "\t\t" + this.states.get(i);
		
		output += "\tWord Alphabet: " + this.sigma + "\n";
		output += "\tStack Alphabet: " + this.gamma + "\n";
		output += "\tFirst State: " + this.actualState.getLabel() + "\n";
		output += "\tFirst Stack Symbol: " + this.firstStack + "\n";
		output += "\tFinal States: [";
		for(int i = 0; i < this.finalStates.size() - 1; i++)
			output += this.finalStates.get(i).getLabel() + ",";
		if(this.finalStates.size() == 0)
			output += "]\n";
		else
			output += this.finalStates.get(this.finalStates.size()-1).getLabel() + "]\n";
		output += "\tStack: " + this.stack + "\n\n";
		
		return output;
	}

	
	/**
	 * Recursive function that checks if the current word belongs to the AP's language.
	 * @return True if a successful way was found.
	 */
	public Boolean compute() {
		if(this.word.length() == 0) {
			if(mode && inFinalStates(this.actualState))
				return true;
			else if(!mode && this.stack.empty())
				return true;
		}
		
		Boolean result = false;
		if(this.stack.empty())
			return false;
		else {
			this.memoryStack.push(new APMemory(this.word, this.stack, this.actualState)); // NO SE BIEN DONDE VA (tengo que hacer uno antes para guardar la situacion inicial)
			ArrayList<Transition> moves = new ArrayList<Transition>();
			if(this.word.length() > 0)
				moves = this.actualState.getTransitions(this.word.charAt(0), this.stack.peek());
			else
				moves = this.actualState.getTransitions(EPSYLON_SYMBOL, this.stack.peek());
			
			while(!result && moves.size() > 0) {
				if(trace) {
					drawInstant(moves);
				}
				update(moves.get(0));
				this.actualState = this.states.get(inStates(moves.get(0).getNextState()));
				result = compute();
				restore();
				moves.remove(0);
				if(trace && moves.size() > 0 && !result) {
					System.out.println("\n\n\tTURN BACK\n");
					drawHeader();
				}

			}
			
			this.memoryStack.pop();
			return result;
		}
	}
	
	
	/**
	 * It is used for the trace. Show the current situation of the AP and the possible moves it can do.
	 * @param moves Possible moves
	 */
	public void drawInstant(ArrayList<Transition> moves) {
		ArrayList<String> transitionLabels = new ArrayList<String>();
		for(int i = 0; i < moves.size(); i++)
			if(i == 0)
				transitionLabels.add(moves.get(i).getLabel() + "*");
			else
				transitionLabels.add(moves.get(i).getLabel() + "");
		
		System.out.printf("|%-10s|%-20s|%-30s|%-20s|\n", this.actualState.getLabel(), this.word, this.stack, transitionLabels);
	}
	
	
	/**
	 * Draw a header for the trace
	 */
	public void drawHeader() {
		System.out.printf("|%-10s|%-20s|%-30s|%-20s|\n\n", "State", "Word", "Stack", "Transitions");
	}

	
	/**
	 * Change the values of the AP using a transition.
	 * @param transition 
	 */
	private void update(Transition transition) {
		if(!transition.getTapeRead().equals(EPSYLON_SYMBOL))
			this.word = this.word.substring(1);
		this.stack.pop();
		if(!transition.getStackWrite()[0].equals(EPSYLON_SYMBOL.toString()))
			for(int i = transition.getStackWrite().length - 1; i >= 0; i--)
				this.stack.push(transition.getStackWrite()[i]);
	}
	
	
	/**
	 * Restore the AP to the instant that is at the top of the stack
	 */
	private void restore() {
		this.word = this.memoryStack.peek().getWord();
		this.stack = this.memoryStack.peek().getStack();
		this.actualState = this.memoryStack.peek().getActualState();
	}

	
	/**
	 * Check if a state is in the FinalStates set
	 * @param state
	 * @return
	 */
	private Boolean inFinalStates(State state) {
		for(int i = 0; i < this.finalStates.size(); i++)
			if(this.finalStates.get(i).equals(state))
				return true;
		return false;
	}

	
	/**
	 * Check if a state label match with any state in the states set
	 * @param state
	 * @return
	 */
	private int inStates(String state) {
		for(int i = 0; i < this.states.size(); i++)
			if(this.states.get(i).getLabel().equals(state))
				return i;
		return -1;
	}

	
	private void genererateFinalStates(String[] split) throws IOException {
		if(!split[0].equals("")) {
			for(int i = 0; i < split.length; i++) {
				int index = inStates(split[i]);
				if(index != -1)
					this.finalStates.add(this.states.get(index));
				else
					throw new IOException("Final states are wrong defined");
			}
		}
		else
			throw new IOException("Final states are wrong defined");
	}

	
	private void generateGamma(String[] symbols) {
		for(int i = 0; i < symbols.length; i++)
			this.gamma.add(symbols[i]);
	}

	
	private void generateSigma(String[] symbols) {
		for(int i = 0; i < symbols.length; i++)
			this.sigma.add(symbols[i]);
	}

	
	private void generateStates(String[] split) {
		for(int i = 0; i < split.length; i++) {
			this.states.add(new State(split[i]));
		}
	}

}
