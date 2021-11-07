package com.retailer;

import java.util.Arrays;
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
			
			Product product = new Product();
			
			System.out.println("Welcome to the Retail Store:");
			System.out.println("Here is the list of items that we provide with their repective discount and taxes:\n");
			System.out.println("Products\t Tax(%)\tDiscount(%)\n");
			String items[] = {"Frozen Vegetable",
			                  "Coffee",
			                  "Tea",
			                  "Meat",
			                  "Butter",
			                  "Cheese",
			                  "Sugar",
			                  "CCTV",
			                  "Optical Fiber",
			                  "Washing Machine"};
			
			for(String item : items) {
				product.setType(item);
				kSession.execute(product);
				System.out.println(product.getType()+"\t"+product.getTax()+"\t"+product.getDiscount());
			}
			System.out.println("What do you want to buy?");
			Scanner input = new Scanner(System.in);
			String type = input.nextLine();
			if(Arrays.asList(items).contains(type)) {
				product.setType(type);
				kSession.execute(product);
				System.out.println("Enter out price for "+product.getType());
				Scanner input1 = new Scanner(System.in);
				int price = input1.nextInt();
				product.setAmount(price);
					
				System.out.println("Tax on your item is "+" $ "+
						((product.getAmount()*product.getTax())/100));
				System.out.println("Discount on your item is "+" $ "+
						((product.getAmount()*product.getDiscount())/100));
				System.out.println("You have to pay "+" $ "+
					((product.getAmount()+(product.getAmount()*product.getTax())/100)
					- ((product.getAmount()*product.getDiscount())/100)));
				input.close();
				input1.close();
		} else {
			System.out.println("Sorry this item is not available in the store");
		}
	} catch(Exception e) {
		e.printStackTrace();
	}
  }
}
