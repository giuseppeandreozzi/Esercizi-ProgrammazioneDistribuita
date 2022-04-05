package client;

public class ClientMain {

	public static void main(String[] args) {
		try {
			Impiegato impiegato = new ImpiegatoStub("localhost", 9000);
			String ID;
			String name;
			int stipendio;
			
			System.out.println("Ottengo le informazioni dell'Impiegato dal server e le stampo...");
			ID = impiegato.getID();
			name = impiegato.getName();
			stipendio = impiegato.getStipendio();
			
			System.out.println("ID: " + ID + " name: " + name + " stipendio: " + stipendio);
			
			System.out.println("Aumento stipendio di 500...");
			impiegato.aumentaStipendio(500);
			stipendio = impiegato.getStipendio();
			
			System.out.println("Nuovo stipendio: " + stipendio);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

}
