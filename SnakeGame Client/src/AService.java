import java.io.IOException;
//import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//processing routine on server (B)
public class AService implements Runnable {
	
	private Snake mySnake;

	private Socket s;
	private Scanner in;

	public AService (Socket aSocket, Snake mySnake) {
		this.mySnake = mySnake;
		this.s = aSocket;
	}
	public void run() {
		
		try {
			in = new Scanner(s.getInputStream());
			processRequest( );
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	//processing the requests
	public void processRequest () throws IOException {
		//if next request is empty then return
		while(true) {
			if(!in.hasNext( )){
				return;
			}
			String command = in.next();
			if (command.equals("Quit")) {
				return;
			} else {
				executeCommand(command);
			}
		}
	}
	
	public void executeCommand(String command) throws IOException{
	
		if (command.equals("SNAKE")) {
			int playerX = in.nextInt();
			int playerY = in.nextInt();
			System.out.println("SNAKE "+playerX+", "+playerY);
			
			mySnake.setSpriteX(playerX);
			mySnake.setSpriteY(playerY);
		}
		
	}
}
