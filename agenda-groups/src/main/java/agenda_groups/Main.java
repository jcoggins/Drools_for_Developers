package agenda_groups;

import java.util.HashMap;
import java.util.Scanner;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Main {
	public static void main(String[] args) {
		System.err.close();
		System.setErr(System.out);
		
		Scanner inputName = null;
		Scanner input = null;
		
		Scanner inputExit = new Scanner(System.in);
		String quit = "no"; 
		
		try {
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rule");
				
			while(!quit.equals("q")) {
				
				HashMap<String, Integer> map = new HashMap<String, Integer>();
				
				// add elements to the map
				map.put("coffee", 50);
				map.put("tea", 40);
				map.put("chicken", 150);
				map.put("meat", 200);
				
				System.out.println();
				System.out.print("How much do you want to spend? ");
				input = new Scanner(System.in);
				int funds = input.nextInt();
				Product product = new Product();
				product.setBalance(funds);
				
				product.setInsufficientBalance(false);
				
				System.out.print("What do you want to purchase? ");
				
				inputName = new Scanner(System.in);
				String name = inputName.nextLine();
				Item item = new Item();
				item.setAmount(map.get(name));
				product.setItem(item);
					
				kSession.insert(product);
				kSession.getAgenda().getAgendaGroup("check").setFocus();
				kSession.fireAllRules();
				product.setBalance(product.getBalance() - item.getAmount());
					
				System.out.print("Do you want to continue? (q for quit, c for continue) ");
				quit = inputExit.next();
				// reset session
				kSession = kContainer.newKieSession("ksession-rule");
			}
			System.out.println("Exit Program!!");
			
		} catch(Exception e) {
			e.printStackTrace();
			
			input.close();
			inputName.close();
			inputExit.close();		
		}
	}
}
