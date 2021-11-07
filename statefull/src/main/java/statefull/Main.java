package statefull;

import java.util.Scanner;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

// Repeat customer so, you have recognized state
public class Main {
	
	public static void main(String[] args) {
		System.err.close();
		System.setErr(System.out);
		
		try {
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rule");
			
			// Initial outside the loop
			Bill amt = new Bill();
			Scanner inputPrice = new Scanner(System.in);
			Scanner inputQuit = new Scanner(System.in);
			String s = "Continue";
		
			while(!s.equals("q")) {
				
				System.out.print(
						"Enter the amount(Press Enter after input): ");
				//200 then 300 then 900
				
				// instantiating again simulates a new visit
				amt = new Bill(); 
				amt.setAmount(inputPrice.nextInt());
				kSession.insert(amt);
				
				System.out.print(
						"Do you want to quit (type q, c to continue)?  ");
				s = inputQuit.next();	
			} 
			kSession.fireAllRules();			
			System.out.println("Program has exited!!");
			inputPrice.close();
			inputQuit.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
