package forall;

import java.util.Scanner;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Main {
	public static void main(String[] args) {
		System.err.close();
		System.setErr(System.out);
		
		Scanner input = null;
		Scanner inputExit = new Scanner(System.in);
		String quit = "c"; 
		
		try {
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rule");
			
			while(!quit.equals("q")) {
				Department grocery = new Department();
				grocery.setName("Grocery");
				
				Product[] groceryProducts;
				groceryProducts = new Product[4];
				
				Product tea = new Product();
				tea.setName("tea");
				tea.setPrice(40);
				groceryProducts[0] = tea;
				Product coffee = new Product();
				coffee.setName("coffee");
				coffee.setPrice(50);
				groceryProducts[1] = coffee;
				Product chicken = new Product();
				chicken.setName("chicken");
				chicken.setPrice(100);
				groceryProducts[2] = chicken;
				Product meat = new Product();
				meat.setName("meat");
				meat.setPrice(200);
				groceryProducts[3] = meat;
				
				Department electronics = new Department();
				electronics.setName("Electronics");
				
				Product[] electronicsProducts;
				electronicsProducts = new Product[4];
				
				Product tv = new Product();
				tv.setName("TV");
				tv.setPrice(4000);
				electronicsProducts[0] = tv;
				Product laptop = new Product();
				laptop.setName("LAPTOP");
				laptop.setPrice(5000);
				electronicsProducts[1] = laptop;
				Product oven = new Product();
				oven.setName("OVEN");
				oven.setPrice(1500);
				electronicsProducts[2] = oven;
				Product pc = new Product();
				pc.setName("PC");
				pc.setPrice(5000);
				electronicsProducts[3] = pc;
						
				System.out.println();
				System.out.print("What is the budget? ");
				input = new Scanner(System.in);
				int funds = input.nextInt();
				Budget budget = new Budget();
				budget.setBudget(funds);
			
				electronics.setProducts(electronicsProducts);
				grocery.setProducts(groceryProducts);
				
				kSession.insert(electronics);
				kSession.insert(grocery);
				kSession.insert(budget);
				kSession.fireAllRules();
				
				System.out.print("Do you want to continue? (q for quit, c for continue) ");
				quit = inputExit.next();
				// reset session
				kSession = kContainer.newKieSession("ksession-rule");
		    }		
			System.out.println("Exit Program!!");
			
		} catch(Exception e) {
			e.printStackTrace();		
		}
		inputExit.close();
		input.close();
	}
}

