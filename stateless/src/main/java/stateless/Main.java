package stateless;

import java.util.Scanner;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

public class Main {
		
	public static void main(String[] args) {
		System.err.close();
		System.setErr(System.out);
		
		try {
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			StatelessKieSession kSession = 
					kContainer.newStatelessKieSession();
			
			// Initial outside the loop
			Product product = new Product();
			Scanner inputPrice = new Scanner(System.in);
			Scanner inputQuit = new Scanner(System.in);
			String s = "Continue";
		
			while(!s.equals("q")) {
				
				System.out.print(
						"Enter the amount(Press Enter after input): ");
				//90 then 900
				
				// instantiating again simulates a new visit
				product = new Product(); 
				product.setAmount(inputPrice.nextInt());
				product.setValid(true);
				kSession.execute(product);
				if(product.isValid()) {
					System.out.println("Congrats! You will get a discount");
				} else {
					System.out.println("Sorry! You will not get any discount");
				}
				System.out.print(
						"Do you want to quit (type q, c to continue)?  ");
				s = inputQuit.next();	
			} 
					
			System.out.println("Program has exited!!");
			inputPrice.close();
			inputQuit.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
