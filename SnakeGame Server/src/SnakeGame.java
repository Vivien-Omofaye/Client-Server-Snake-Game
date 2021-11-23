//import java.awt.Color;
//import java.awt.Container;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseMotionListener;
//import java.sql.Connection;
//import java.sql.DatabaseMetaData;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.Random;

//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JLabel; 
//import javax.swing.JOptionPane; 

//Server
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SnakeGame {
//extends JFrame implements MouseMotionListener, KeyListener {
	
	//create instances of sprites
	private static Snake mySnake;  
	private static BackgroundTile myBackgroundTile;
	private static SnakeFood mySnakeFood; 
	private static SnakeBody mySnakeBody;
	
	//image icons to hold the graphics
	//private ImageIcon snakeImage, snakeFoodImage, snakeBodyImage, BackgroundTileImage;
	
	//label to display the icons
	//private JLabel snakeLabel, BackgroundTileLabel;
	//private JLabel snakeBody [], snakeFood []; //arrays, randomizer
	private SnakeFood snakeFoodSprite [];
	Random rand;
	
	//private Container content;  

	//for mouse listener
	//private int oldX = 0;
	//private int oldY = 0;
	
	
		public SnakeGame() {
			//super("Snake.io");
			//setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
			//content = getContentPane();
			//content.setBackground(Color.red); 
			//setLayout(null);
			
			rand = new Random();
			
			//display snake 
			mySnake = new Snake(mySnakeFood, mySnakeBody, snakeFoodSprite);
			//mySnake = new Snake(snakeLabel, snakeFood, snakeBody);
			//snakeImage = new ImageIcon( getClass().getResource( mySnake.getFilename() ) );
			//snakeLabel = new JLabel();
			//mySnake.setSnakeLabel(snakeLabel);
			
			mySnake.setSpriteX(500);
			mySnake.setSpriteY(300); 
			//snakeLabel.setIcon(snakeImage); 
			//snakeLabel.setSize(mySnake.getSpriteW(), mySnake.getSpriteH());
			//snakeLabel.setLocation(mySnake.getSpriteX(), mySnake.getSpriteY());
			//content.add(snakeLabel);
			//snakeLabel.setFocusable(false);
			
			//display snake food
			mySnakeFood = new SnakeFood();
			//snakeFoodImage = new ImageIcon( getClass().getResource( mySnakeFood.getFilename() ) );
			//snakeFood =  new JLabel[1000];
			snakeFoodSprite = new SnakeFood[1000];
			mySnake.setMySnakeFood(mySnakeFood);
			mySnake.setSnakeFoodSprite(snakeFoodSprite);
			
			for (int i=0; i<1000; i++) { 
				//snakeFood[i] = new JLabel();
				snakeFoodSprite[i] = new SnakeFood();
				//snakeFood[i].setIcon(snakeFoodImage);
				int randomX = rand.nextInt(GameProperties.SCREEN_WIDTH);
				int randomY = rand.nextInt(GameProperties.SCREEN_HEIGHT);
				//snakeFood[i].setSize(11, 11);
				snakeFoodSprite[i].setSpriteX(randomX);
				snakeFoodSprite[i].setSpriteY(randomY); 
				if (i < 50) {
					snakeFoodSprite[i].setVisible(true);
				} else {
					snakeFoodSprite[i].setVisible(false);
				}
				snakeFoodSprite[i].setEaten(false);
				

				//snakeFood[i].setVisible(snakeFoodSprite[i].getVisible());
				//snakeFood[i].setLocation(snakeFoodSprite[i].getSpriteX(), snakeFoodSprite[i].getSpriteY());
				//content.add(snakeFood[i]);
			}
			//mySnake.setSnakeFoodLabel(snakeFood);
			
			//grow snake body
			mySnakeBody = new SnakeBody();
			//snakeBodyImage = new ImageIcon( getClass().getResource( mySnakeBody.getFilename() ) );
			//snakeBody =  new JLabel[50];
			for (int i=0; i<50; i++) {
				//snakeBody[i] = new JLabel();
				//snakeBody[i].setIcon(snakeBodyImage);
				//snakeBody[i].setSize(31, 31);
				//snakeBody[i].setVisible(false);
				//snakeBody[i].setLocation(i*mySnake.spriteX, mySnake.spriteY);
				//content.add(snakeBody[i]);
			}
			//mySnake.setMySnakeBody(snakeBody);
			mySnake.setSnakeBody(mySnakeBody);
			
			myBackgroundTile = new BackgroundTile(); 
			//BackgroundTileImage = new ImageIcon( getClass().getResource( myBackgroundTile.getFilename() ) );
			//BackgroundTileLabel = new JLabel();
			myBackgroundTile.setSpriteX(0);
			myBackgroundTile.setSpriteY(0);
			//BackgroundTileLabel.setIcon(BackgroundTileImage); 
			//BackgroundTileLabel.setSize(myBackgroundTile.getSpriteW(), myBackgroundTile.getSpriteH());
			//BackgroundTileLabel.setLocation(myBackgroundTile.getSpriteX(), myBackgroundTile.getSpriteY());
			//content.add(BackgroundTileLabel);
			//BackgroundTileLabel.setFocusable(false);
			
			/*
			//Player name
			String name = JOptionPane.showInputDialog(null, "Enter your name to start game:");
			int score = 0;
			
			Connection conn = null; 
			Statement stmt = null; 

	        try { 
	            Class.forName("org.sqlite.JDBC"); 
	            String dbURL = "jdbc:sqlite:SnakeGame.db"; 
	            conn = DriverManager.getConnection(dbURL);  
	             
	            if (conn != null) { 
	            	conn.setAutoCommit(false); 
	                System.out.println("Connected to the database"); 

	                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData(); 

	                stmt = conn.createStatement(); 

	                String sql = "CREATE TABLE if NOT exists SNAKEGAME " + 
	                               "(NAME TEXT NOT NULL, " +  
	                               " SCORE INT NOT NULL)";  
	                stmt.executeUpdate(sql); 
	                conn.commit(); 
	                System.out.println("Table created successfully"); 

	                sql = "INSERT INTO SNAKEGAME (NAME,SCORE) " + 
	                               "VALUES ('"+name+"', '"+score+"')";  
	                stmt.executeUpdate(sql); 
	                conn.commit(); 
	                
	                System.out.println("Player's Name and Score:"); 
	                ResultSet rs = stmt.executeQuery("SELECT * FROM SNAKEGAME LIMIT 1"); 
	                DisplayRecords(rs);                
	                rs.close();
	            } 

	        } catch (ClassNotFoundException ex) { 
	            ex.printStackTrace(); 
	        } catch (SQLException ex) { 
	            ex.printStackTrace(); 
	        } 
	        */
	
			//content.addMouseMotionListener(this);
			
			//content.addKeyListener(this);
			
			//content.setFocusable(true);
			
			mySnake.setMoving(true);
			mySnake.moveSnake();
			
			//setVisible(true);
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		}
		
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		SnakeGame myGame = new SnakeGame();
		//myGame.setBounds(0, 0, 1200, 840); 
		//myGame.setBackground(Color.RED);
		//myGame.setVisible(true);
		//myGame.setResizable(true);
		//myGame.setLocationRelativeTo(null);
		
		//start a server to listen for commands from the client
		mySnake.moveSnake();
		
		final int SERVER_PORT = 5556;
		ServerSocket server = null;
		try {
			server = new ServerSocket(SERVER_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Waiting for clients to connect...");
		while(true) {
			Socket s = null;
			try {
				s = server.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("client connected");
			
			BService myService = new BService (s, mySnake);
			Thread t = new Thread(myService);
			t.start();
		}
    } 

	/*
	public static void DisplayRecords (ResultSet rs) throws SQLException { 

		while ( rs.next() ) { 
            String name = rs.getString("name"); 
            int score = rs.getInt("score");

            System.out.println("Welcome " + name); 
            System.out.println("SCORE = " + score);
            System.out.println(); 
		} 

	} 
	*/
	

	//@Override 
	//public void mouseDragged(MouseEvent e) {}

	//MouseListener
	//@Override
	//public void mouseMoved(MouseEvent e) {
		/*
		//get the Snake's current position
		int x = mySnake.getSpriteX();
		int y = mySnake.getSpriteY();
		
		//check mouse movement direction to determine where snake moves
		//left
		if (oldX > e.getX()) {
			try {
				Thread.sleep(20);
				//snakeLabel.setIcon(new ImageIcon(getClass().getResource("images/SnakeHeadLeft.png")));
				x -= GameProperties.CHARACTER_STEP;
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
		//right
		if (oldX < e.getX()) {
			try {
				Thread.sleep(20);
				//snakeLabel.setIcon(new ImageIcon(getClass().getResource("images/SnakeHeadRight.png")));
				x += GameProperties.CHARACTER_STEP;
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
		//up
		if (oldY > e.getY()) {
			try {
				Thread.sleep(20);
				//snakeLabel.setIcon(new ImageIcon(getClass().getResource("images/SnakeHeadUp.png")));
				y -= GameProperties.CHARACTER_STEP;
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
		//down
		if (oldY < e.getY()) {
			try {
				Thread.sleep(20);
				//snakeLabel.setIcon(new ImageIcon(getClass().getResource("images/SnakeHeadDown.png")));
				y += GameProperties.CHARACTER_STEP;
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
		//diagonal (bottom left)
		if (oldX > e.getX()) {
			try {
				Thread.sleep(20);
				//snakeLabel.setIcon(new ImageIcon(getClass().getResource("images/SnakeHeadBottomLeft.png")));
				x -= GameProperties.CHARACTER_STEP;
				y += GameProperties.CHARACTER_STEP;
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
		//diagonal (bottom right)
		if (oldY < e.getY()) {
			try {
				Thread.sleep(20);
				//snakeLabel.setIcon(new ImageIcon(getClass().getResource("images/SnakeHeadBottomRight.png")));
				x += GameProperties.CHARACTER_STEP;
				y += GameProperties.CHARACTER_STEP;
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
		//diagonal (top left)
		if (oldY > e.getY()) {
			try {
				Thread.sleep(20);
				//snakeLabel.setIcon(new ImageIcon(getClass().getResource("images/SnakeHeadTopLeft.png")));
				x -= GameProperties.CHARACTER_STEP;
				y -= GameProperties.CHARACTER_STEP;
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
		//diagonal (top right)
		if (oldX < e.getX()) {
			try {
				Thread.sleep(20);
				//snakeLabel.setIcon(new ImageIcon(getClass().getResource("images/SnakeHeadTopRight.png")));
				x += GameProperties.CHARACTER_STEP;
				y -= GameProperties.CHARACTER_STEP;
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}

		oldX = e.getX();
		oldY = e.getY();
		
		//update the Snake's state and update position
		mySnake.setSpriteX(x);
		mySnake.setSpriteY(y);
		//snakeLabel.setLocation(mySnake.getSpriteX(), mySnake.getSpriteY()); 
		 * 
		 */
	//}

	//@Override
	//public void keyTyped(KeyEvent e) {}

	//KeyListener
	//@Override
	//public void keyPressed(KeyEvent e) {
		/*
		mySnake.setMoving(true);
		mySnake.moveSnake();
		
		//get the snakes's current position
		int x = mySnake.getSpriteX();
		int y = mySnake.getSpriteY();
		
		//if up key pressed, move up
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			//SNAKEMOVES VK_UP
			mySnake.setMoving(true);
			mySnake.moveSnake();
			//snakeLabel.setIcon(new ImageIcon(getClass().getResource("images/SnakeHeadUp.png")));
			y -= GameProperties.CHARACTER_STEP;
		}
		
		
		//if down key pressed, move down
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			mySnake.setMoving(true);
			mySnake.moveSnake();
			//snakeLabel.setIcon(new ImageIcon(getClass().getResource("images/SnakeHeadDown.png")));
			y += GameProperties.CHARACTER_STEP;
		} 
		
		//if left key pressed, move left
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			mySnake.setMoving(true);
			mySnake.moveSnake();
			//snakeLabel.setIcon(new ImageIcon(getClass().getResource("images/SnakeHeadLeft.png")));
			x -= GameProperties.CHARACTER_STEP;
		}
		
		//if right key pressed, move right
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			mySnake.setMoving(true);
			mySnake.moveSnake();
			//snakeLabel.setIcon(new ImageIcon(getClass().getResource("images/SnakeHeadRight.png")));
			x += GameProperties.CHARACTER_STEP;
		} 
		
		//diagonal (bottom left)
		if (e.getKeyCode() == KeyEvent.VK_1) {
			mySnake.setMoving(true);
			mySnake.moveSnake();
			//snakeLabel.setIcon(new ImageIcon(getClass().getResource("images/SnakeHeadBottomLeft.png")));
			x -= GameProperties.CHARACTER_STEP;
			y += GameProperties.CHARACTER_STEP;
		}
		
		//diagonal (bottom right)
		if (e.getKeyCode() == KeyEvent.VK_2) {
			mySnake.setMoving(true);
			mySnake.moveSnake();
			//snakeLabel.setIcon(new ImageIcon(getClass().getResource("images/SnakeHeadBottomRight.png")));
			x += GameProperties.CHARACTER_STEP;
			y += GameProperties.CHARACTER_STEP;
		}
		
		//diagonal (top left)
		if (e.getKeyCode() == KeyEvent.VK_3) {
			mySnake.setMoving(true);
			mySnake.moveSnake();
			//snakeLabel.setIcon(new ImageIcon(getClass().getResource("images/SnakeHeadTopLeft.png")));
			x -= GameProperties.CHARACTER_STEP;
			y -= GameProperties.CHARACTER_STEP;
		}
		
		//diagonal (top right)
		if (e.getKeyCode() == KeyEvent.VK_4) {
			mySnake.setMoving(true);
			mySnake.moveSnake();
			//snakeLabel.setIcon(new ImageIcon(getClass().getResource("images/SnakeHeadTopRight.png")));
			x += GameProperties.CHARACTER_STEP;
			y -= GameProperties.CHARACTER_STEP;
		}
		
		//update the doctor's state and update position
		mySnake.setSpriteX(x);
		mySnake.setSpriteY(y);
		//snakeLabel.setLocation(mySnake.getSpriteX(), mySnake.getSpriteY()); 
		 * 
		 */
		
	//}
 
	//@Override
	//public void keyReleased(KeyEvent e) {}

}
