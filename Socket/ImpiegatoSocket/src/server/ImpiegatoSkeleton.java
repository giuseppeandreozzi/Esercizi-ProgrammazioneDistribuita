package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ImpiegatoSkeleton {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		ImpiegatoServer impiegato = new ImpiegatoServer(1000, "IDTEST", "Mario Rossi");
		
		try {
			serverSocket =  new ServerSocket(9000);
			
			System.out.println("Attendo connessioni...");
			socket = serverSocket.accept();
			System.out.println("Connessione accettata");
			
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			while(true) {

					String method;
					
					method = (String) in.readObject();
					System.out.println(method);
					if (method.equals("aumentaStipendio")) {
						int cifraAumento = in.readInt();
						impiegato.aumentaStipendio(cifraAumento);
					} else if (method.equals("getID")) {
						out.writeObject(impiegato.getID());
						out.flush();
					} else if (method.equals("getName")) {
						out.writeObject(impiegato.getName());
						out.flush();
					} else if (method.equals("getStipendio")) {
						out.writeInt(impiegato.getStipendio());
						out.flush();
					} else break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}


