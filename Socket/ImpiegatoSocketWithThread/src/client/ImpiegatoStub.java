package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ImpiegatoStub implements Impiegato {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public ImpiegatoStub(String host, int port) throws IOException{
		socket = new Socket(host, port);
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	public void aumentaStipendio(int cifraAumento) {
		try {
			out.writeObject("aumentaStipendio");
			out.writeInt(cifraAumento);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public String getID(){
		String ID = "";
		
		try {
			out.writeObject("getID");
			out.flush();
			
			ID = (String) in.readObject();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return ID;
	}


	public String getName(){
		String name = "";
		
		try {
			out.writeObject("getName");
			out.flush();
			
			name = (String) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return name;
	}
	
	public int getStipendio() {
		int stipendio = 0;
		
		try {
			out.writeObject("getStipendio");
			out.flush();
			
			stipendio = in.readInt();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return stipendio;
	}
	
	public void close() {
		try {
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
