package automata_pila;

public class MainAP {

	public static void main(String[] args) {
		if(args.length == 3) {
			AP automata = new AP(args[0], args[1], Integer.parseInt(args[2]));
			System.out.println(automata.toString());
			automata.drawHeader();
			boolean result = automata.compute();
			if(result)
				System.out.println("\n\nThe word " + args[1] + " belongs to the language");
			else
				System.out.println("\n\nThe word " + args[1] + " does not belong to the language");
		}
		else
			usage();

	}
	
	public static void usage() {
		System.out.println("Wrong arguments number");
		System.out.println("usage: \n");
		System.out.println("\tMainAp APfile.txt word trace\n");
		System.out.printf("%-10s File where the SA is defined.\n", "APfile");
		System.out.printf("%-10s The word we want to check.\n", "Word");
		System.out.printf("%-10s 1 if you want to show the trace, 0 otherwise.\n", "Trace");
		
	}

}
