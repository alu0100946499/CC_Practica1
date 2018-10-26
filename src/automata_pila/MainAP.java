package automata_pila;

import java.util.Scanner;

public class MainAP {

	public static void main(String[] args) {
		if(args.length == 2) {
			AP automaton;
			if(args[1].equals("1"))
				automaton = new AP(args[0], true);
			else
				automaton = new AP(args[0], false);
			
			String trace = "[Disabled]";
			String input;
			Scanner inputScanner = new Scanner(System.in);
			int option = -1;
			
			while(option != 0) {
//				System.out.print("\033[H\033[2J");
				System.out.print("\n\n");
				System.out.println("\t[1] Set word - Actual: [" + automaton.getWord() + "]" );
				System.out.println("\t[2] Enable/Disable trace " + trace);
				System.out.println("\t[3] Compute");
				System.out.println("\t[4] Show");
				System.out.println("\t[0] Exit");
				System.out.print("\n\n\t>>> ");
				
				input = inputScanner.next();
				option = Integer.parseInt(input);
				
				switch(option) {
					case 1: {
						System.out.print("\n\tNew word: ");
						automaton.setWord(inputScanner.next());
					}; break;
					
					case 2: {
						if(automaton.getTrace().equals(true)) {
							trace = "[Disabled]";
							automaton.setTrace(false);
						}
						else {
							trace = "[Enabled]";
							automaton.setTrace(true);
						}
							
					}; break;
					
					case 3: {
						if(automaton.getWord().equals(""))
							System.out.println("\n\tWord is not stablished");
						else {
							if(automaton.getTrace().equals(true)) {
								System.out.println("\n\n");
								automaton.drawHeader();
							}
							boolean result = automaton.compute();
							if(result)
								System.out.println("\tThe word \"" + automaton.getWord() + "\" belongs to the language");
							else
								System.out.println("\tThe word \"" + automaton.getWord() + "\" does not belong to the language");
							inputScanner.nextLine();
							inputScanner.nextLine();
						}
					}; break;
					
					case 4: {
						System.out.println(automaton.toString()); 
						inputScanner.nextLine();
						inputScanner.nextLine();
					}; break;
					
					case 0: break;
					
					default: System.out.println("\tWrong option");
				}
			}
			
			
			
		}
		else
			usage();

	}
	
	public static void usage() {
		System.out.println("Wrong arguments number");
		System.out.println("usage: \n");
		System.out.println("\tMainAp APfile.txt mode\n");
		System.out.printf("%-10s File where the automaton is defined.\n", "APfile");
		System.out.printf("%-10s 1 for APf, 0 for APv", "Mode");
		
	}
	
}

