package com.drools.main;

import java.util.Scanner;

import org.drools.compiler.compiler.DroolsParserException;
import org.drools.core.base.RuleNameEndsWithAgendaFilter;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.drools.model.Product;

public class Test {
	
	public static void main(String args[]) {
		System.err.close();
		System.setErr(System.out);
		
		try {
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rule");
			
			// Initial outside the loop
			Product product = new Product();
			product.setType("Start");
			Scanner inputType = new Scanner(System.in);
			Scanner inputPrice = new Scanner(System.in);
			
			// Exit instructions
			System.out.println("Type \"Quit\" to exit the program");
			
			while(!product.getType().equals("Quit")) {
				
				System.out.print("Enter the product type: ");
				//Packaged coffee, Packaged tea or Packaged chicken
				
				// Press Enter is carriage return \\r\\n accept multiple tokens
				inputType = new Scanner(System.in).useDelimiter("\\r\\n");
				
				product.setType(inputType.next());
				
				// Quit typed as product type exit
				if (product.getType().equals("Quit")) {
					break;
				}
				
				kSession.insert(product);
				kSession.fireAllRules();
				
				System.out.print("Enter our price for "+product.getType()+": ");
				int price = inputPrice.nextInt();
				product.setPrice(price);
				
				System.out.println("The Gst tax for the "+product.getType()+" is "+product.getGst()+"%");
				System.out.println("You have to pay: "+((product.getPrice()+(product.getPrice()*product.getGst())/100)+" $"));
			} 
			System.out.println("Program has exited!!");
			inputType.close();
			inputPrice.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
