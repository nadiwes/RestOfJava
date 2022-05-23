import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Program {
	
	public static PrintStream getOutputPrintStream(Scanner console){
		boolean validFile = false;
		PrintStream ps = null;
		
		do {
			try {
				System.out.print("Output file name: ");
				File f = new File(console.nextLine());
				ps = new PrintStream(f);
			}
			catch (FileNotFoundException e) {
				System.out.println("File Exceptoin" + e);
			}
		}while(!validFile);
		return ps;
		
		
	}
	
	
	public static Scanner getInputScanner(Scanner console) throws FileNotFoundException{
		boolean validFile = false;
		System.out.print("Input file name: ");
		
		File f = null;
		do {
			f = new File(console.nextLine());
			if(f.exists()) {
				validFile = true;
			} else {
				System.out.print("File not found. Try again: ");
			}
		} while(!validFile);
		Scanner input = new Scanner(f);
		return input;
	}
	
	public static void createFile(Scanner in, PrintStream out, Scanner console) {
	
		while(in.hasNextLine()) {
			String ls = in.nextLine();
			Scanner line = new Scanner(ls);
			while(line.hasNext()){
				String token = line.next();
				if(token.substring(0, 1).equals("<")) {
					System.out.print("Give me a/an " + token.substring(1, token.length()-1));;
					token = console.nextLine();
				}
				out.print(token + " ");
			}
			out.println();
		}
		out.close();
		in.close();
	}
	
	public static void viewFile(Scanner input) {
		System.out.println();
		while(input.hasNextLine()) {
			System.out.println(input.nextLine());
		}
		System.out.println();
	}
    
    public static void main(String[] args) throws FileNotFoundException {
        // Intro text
        System.out.println("Welcome to the game of Mad Libs.");
        System.out.println("I will ask you to provide various words");
        System.out.println("and phrases to fill in a story.");
        System.out.println("The result will be written to an output file.");
        System.out.println();
        
        // Create the scanner to read from the console
        Scanner console = new Scanner(System.in);
        
        // Enter the command loop and cycle for as long as we're not done.
        boolean done = false;
        do {
            System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
            String command = console.nextLine();
            Scanner input;
            PrintStream output;
            switch(command.toUpperCase()) {
            case "C":
                // TODO: Code processing the 'C' command
            	input = getInputScanner(console);
            	output = getOutputPrintStream(console);
            	createFile(input, output, console);
                break;
            case "V":
                // TODO: Code processing the 'V' command
            	input = getInputScanner(console);
            	viewFile(input);
                break;
            case "Q":
                System.out.println("Goodbye!");
                done = true;
                break;
            default:
                System.out.println("Command '" + command + "' is not supported!");
                break;
            }
        } while(!done);
    }
}
