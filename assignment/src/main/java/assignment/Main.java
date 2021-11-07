package assignment;

import java.util.Scanner;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


public class Main {
	public static void main(String[] args) {
		System.err.close();
		System.setErr(System.out);
		
		try {
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rule");
			
			// Initial outside the loop
			Gender gender = new Gender();
			Product budget = new Product();
			
			Scanner inputGender = new Scanner(System.in);
			Scanner inputBudget = new Scanner(System.in);
			
				System.out.print("Enter the gender: ");
					
				String sex = inputGender.next();
				gender.setGender(sex);
				
				System.out.print("Enter the budget(price of Product): ");
				
				int price = inputBudget.nextInt();
				budget.setBudget(price);
					
				kSession.insert(gender);
				kSession.insert(budget);
				kSession.fireAllRules();
			
			inputGender.close();
			inputBudget.close();
			System.out.println("Program has exited!!");	
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
}

