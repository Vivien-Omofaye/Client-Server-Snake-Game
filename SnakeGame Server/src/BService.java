import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//processing routine on server (B)
public class BService implements Runnable {
	
	private Snake mySnake;
	
	final int CLIENT_PORT = 5656;

	private Socket s;
	private Scanner in;

	public BService (Socket aSocket, Snake mySnake) {
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
				//System.out.println("command received");
				executeCommand(command);
			}
		}
	}
	
	public void executeCommand(String command) throws IOException{
		
		if (command.equals("SNAKE")) {
			//get the snake's current position
			int x = mySnake.getSpriteX();
			int y = mySnake.getSpriteY();
			
			String playerAction = in.next();
			
			if (playerAction.equals("UP")) {
				y -= GameProperties.CHARACTER_STEP;
				mySnake.setSpriteY(y); 
			} else if (playerAction.equals("DOWN")) {
				y += GameProperties.CHARACTER_STEP;
				mySnake.setSpriteY(y);
			} else if (playerAction.equals("RIGHT")) {
				x += GameProperties.CHARACTER_STEP;
				mySnake.setSpriteX(x);
			} else if (playerAction.equals("LEFT")) {
				x -= GameProperties.CHARACTER_STEP;
				mySnake.setSpriteX(x); 
			}
			System.out.println("Snake is moving " + playerAction);
			System.out.println("SNAKE Position: "+x+", "+y+"\n");
			
			//send a response
			Socket s2 = new Socket("localhost", CLIENT_PORT);
			
			//Initialize data stream to send data out
			OutputStream outstream = s2.getOutputStream();
			PrintWriter out = new PrintWriter(outstream);

			String commandOut = "SNAKE "+x+" "+y+ "\n";
			out.println(commandOut);
			out.flush();
				
			s2.close();
		}

	}
}
