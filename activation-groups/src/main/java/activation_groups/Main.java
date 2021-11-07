package activation_groups;

import java.util.Scanner;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Main {
	public static void main(String[] args) {
		System.err.close();
		System.setErr(System.out);
		
		Scanner inputCoupon = null;
		Scanner inputAns = null;
		Scanner input = null;
		
		Scanner inputExit = new Scanner(System.in);
		String quit = "no"; 
		
		try {
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rule");
				
			while(!quit.equals("q")) {
				Product product = new Product();
				System.out.print("What do you want to buy? ");
				input = new Scanner(System.in);
				String item = input.nextLine();
				product.setType(item);
				
				System.out.println("These are the available coupons: ");
				System.out.println("RETAILER");
				System.out.println("FIRST50");
				System.out.print("Do you have any of these coupons? (yes/no) ");
				inputAns = new Scanner(System.in);
				String ans = inputAns.next();
				String yes = "yes";
				if(ans.contains(yes)) {
					System.out.print("Apply Coupon Code: ");
					inputCoupon = new Scanner(System.in);
					String coupon = inputCoupon.nextLine();
					product.setCoupon(coupon);
				}
				kSession.insert(product);
				kSession.fireAllRules();
					
				System.out.print("Do you want to continue? (q for quit, c for continue) ");
				quit = inputExit.next();
				// reset session
				kSession = kContainer.newKieSession("ksession-rule");
			}
			System.out.println("Exit Program!!");
			
		} catch(Exception e) {
			e.printStackTrace();
			
			input.close();
			inputAns.close();
			inputCoupon.close();
			inputExit.close();		
		}
	}
}
