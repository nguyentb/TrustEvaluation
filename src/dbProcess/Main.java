package dbProcess;

public class Main {

	public static void main(String[] args) {
		EntityCreate ec = new EntityCreate();
		ec.entitycreation(100);
		InteractionCreate ic = new InteractionCreate();
		ic.interactioncreation(50000);
	}
}