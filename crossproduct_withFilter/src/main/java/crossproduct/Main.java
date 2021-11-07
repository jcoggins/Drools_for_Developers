package crossproduct;

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
			
			Product apple = new Product();
			apple.setName("apple");
			
			Product cheese = new Product();
			cheese.setName("cheese");
			
			Department grocery = new Department();
			grocery.setName("grocery");
			
			Department essentials = new Department();
			essentials.setName("essentials");
			
			kSession.insert(cheese);
			kSession.insert(apple);
			kSession.insert(grocery);
			kSession.insert(essentials);
			
			kSession.fireAllRules();
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
}