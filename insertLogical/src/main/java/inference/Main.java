package inference;

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
			
			System.out.print("How many customers will be there? ");
			Scanner input = new Scanner(System.in);
			int n = input.nextInt();
			
			Scanner inputDepartment = new Scanner(System.in);
			Scanner inputName = new Scanner(System.in);
			Scanner inputStudent = new Scanner(System.in);
			
			for(int i=0; i<n; i++) {
				Person person = new Person();
				System.out.print("What is your name? ");
				inputName = new Scanner(System.in);
				String name = inputName.nextLine();
				person.setName(name);
				
				System.out.print("In which department do you want to visit? ");
				inputDepartment = new Scanner(System.in);
				String department = inputDepartment.nextLine();
				Department dept = new Department();
				dept.setName(department);
				person.setDepartment(dept);
				
				System.out.print("Are you a student? ");
				inputStudent = new Scanner(System.in);
				boolean bn  = inputStudent.nextBoolean();
				person.setStudent(bn);
				
				kSession.insert(person);
				kSession.insert(dept);
			}		
			inputName.close();
			inputDepartment.close();
			inputStudent.close();
			input.close();
			
			kSession.fireAllRules();
		}	
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}