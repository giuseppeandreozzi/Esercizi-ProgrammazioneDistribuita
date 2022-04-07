package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ImpiegatoSkeleton {

	public static class ImpiegatoThread implements Runnable {
		private Socket socket;
		private ImpiegatoServer impiegato;
		
		public ImpiegatoThread(Socket aSocket, ImpiegatoServer aImpiegato) {
			socket = aSocket;
			impiegato = aImpiegato;
		}
		
		public void run() {
			try {
				System.out.println("Connessione accettata");
				ObjectInputStream in = null;
				ObjectOutputStream out = null;
				String method;
				
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());
				
				while(true) {
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
					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		ImpiegatoServer impiegato = new ImpiegatoServer(1000, "IDTEST", "Mario Rossi");
		
		try {
			serverSocket =  new ServerSocket(9000);

			while(true) {
				System.out.println("Attendo connessioni...");
					Thread thread = new Thread(new ImpiegatoThread(serverSocket.accept(), impiegato));
					thread.start();
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}


