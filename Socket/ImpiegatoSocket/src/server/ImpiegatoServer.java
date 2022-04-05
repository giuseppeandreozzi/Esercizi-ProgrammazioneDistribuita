package server;

public class ImpiegatoServer implements Impiegato {

	private int stipendio;
	private String ID;
	private String name;
	
	public ImpiegatoServer(int aStipendio, String aID, String aName) {
		stipendio = aStipendio;
		ID = aID;
		name = aName;
	}
	
	public void aumentaStipendio(int cifraAumento) {
		stipendio += cifraAumento;
	}


	public String getID() {
		return ID;
	}


	public String getName() {
		return name;
	}


	public int getStipendio() {
		return stipendio;
	}

}
