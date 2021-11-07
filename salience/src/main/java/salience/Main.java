package salience;

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
			Department department;
			Product product;
			Product[] products;
			Scanner inputQuit = new Scanner(System.in);
			Scanner inputCustomerNum = new Scanner(System.in);
			Scanner inputName = new Scanner(System.in);
			Scanner inputDept = new Scanner(System.in);
			Scanner inputYesNo = new Scanner(System.in);
			String s = "Continue";
		
			while(!s.equals("q")) {
				
				System.out.print(
						"How many customers will be there?(Press Enter after input): ");
				
				// instantiating again simulates a new visit
				int n = inputCustomerNum.nextInt();
				products = new Product[n];
				for(int i=0; i<n; i++) {
					product = new Product();
					System.out.print("What is customer name(joe, bill, dan): ");
					inputName = new Scanner(System.in);
					String name = inputName.nextLine(); // type is important
					product.setName(name);
					System.out.print("In which department are you (apparel, electronics)? ");
					inputDept = new Scanner(System.in);
					String departmentName = inputDept.nextLine(); // type is important
					department = new Department();
					department.setName(departmentName);
					product.setDepartment(department);
					System.out.print("Are you a student(true or false)? ");
					inputYesNo = new Scanner(System.in);
					boolean bn = inputYesNo.nextBoolean(); // type is important
					product.setStudent(bn);
					products[i] = product;
				}
				for (int i=0; i<n; i++) {
					kSession.insert(products[i]);
				}
				kSession.fireAllRules();
				
				System.out.print(
						"Do you want to quit (type q, c to continue)?  ");
				s = inputQuit.next();	
			} 			
			System.out.println("Program has exited!!");
			inputQuit.close();
			inputCustomerNum.close();
			inputName.close();
			inputDept.close();
			inputYesNo.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
