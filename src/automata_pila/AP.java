package automata_pila;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AP {
	
	private ArrayList<State> Q;
	private ArrayList<Character> sigma;
	private ArrayList<Character> gamma;
	private State s;
	private String Z;
	private ArrayList<State> F;

	public AP(String filename) {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			
			generateQ(line.split("s+"));
			
			line = reader.readLine();
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error al abrir el fichero");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error al leer del fichero");
		}
	}

	private void generateQ(String[] states) {
		for(int i = 0; i < states.length; i++)
			Q.add(new State(states[i]));
	}
	
	

}
