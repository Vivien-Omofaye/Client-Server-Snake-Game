//import java.util.Random;


import javax.swing.JLabel;

public class Snake extends Sprite implements Runnable {
	
	//new attributes for Snake
	private Boolean visible;
	private Boolean moving;
	
	@SuppressWarnings("unused")
	private Snake mySnake;
	private SnakeFood mySnakeFood;
	@SuppressWarnings("unused")
	private SnakeBody mySnakeBody;
	
	@SuppressWarnings("unused")
	private JLabel snakeLabel;
	private JLabel snakeBody [];
	
	private JLabel snakeFood[]; 
	private SnakeFood snakeFoodSprite[];
	 
	private Thread t1; 
	
	private int segments = 0;
	private int score = 0;
	
	
	//getters and setters
	//setters for the snakeFood, snakeFoodSprite
	public void setSnakeLabel(JLabel temp) {
		snakeLabel = temp;
	}
	
	public void setSnakeFoodLabel(JLabel[] temp) {
		snakeFood = temp;
	}
	public void setSnakeFoodSprite(SnakeFood[] temp) {
		snakeFoodSprite = temp; 
	}
	public void setMySnakeFood(SnakeFood temp) {
		mySnakeFood = temp; 
	}
	
	public void setMySnakeBody(JLabel[] temp) {
		snakeBody = temp; 
	}
	public void setSnakeBody(SnakeBody temp) {
		mySnakeBody = temp; 
	}
	
	public Boolean getVisible() {
		return visible;
	}
	public Boolean isVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible; 
	}
	public Boolean getMoving() {
		return moving;
	}
	public Boolean isMoving() {
		return moving;
	}
	public void setMoving(Boolean moving) {
		this.moving = moving;
	} 
	
	public Snake(SnakeFood tempmySnakeFood, 
		    JLabel tempsnakeLabel, 
		    JLabel[] tempsnakeFood,
		    JLabel[] tempsnakeBody,
		    SnakeBody tempmysnakeBody,
		    SnakeFood[] tempsnakeFoodSprite) {
		super(0, 0, "images/SnakeHeadUp.png", 81, 81);
		this.visible = true;
		this.moving = false; 
		mySnakeFood = tempmySnakeFood;
		snakeLabel = tempsnakeLabel;
		snakeBody = tempsnakeBody;
		mySnakeBody = tempmysnakeBody;
		snakeFood = tempsnakeFood;
		snakeFoodSprite = tempsnakeFoodSprite;
	} 
	 
	public void hide() {
		this.visible = false;
	}
	
	public void show() {
		this.visible = true;
	}
	
	public void Display () {
		System.out.println("X,Y: "+ this.spriteX + ", " + this.spriteY +
						   " / v:" + this.visible + " /m:" + this.moving);
	}
	
	public void moveSnake() {
		//animate
		t1 = new Thread(this, "Move Snake");
		t1.start();
	}
	
	public void stopSnake() {
		this.moving = false;
	}
	
	//check for collision
	private void detectCollision() {
		for (int j=0; j < 100; j++ ) {
			if (snakeFoodSprite[j].getVisible() == true && snakeFoodSprite[j].getEaten() == false) {
				
				//detect collision between snakeFoodSprite[j] and snakeBody
				mySnakeFood = snakeFoodSprite[j];
				if ( this.r.intersects(mySnakeFood.getRectangle() ) ) {
					this.moving = true;
					//System.out.println("detected collision");
					mySnakeFood.setEaten(true);
					mySnakeFood.setVisible(false);
					snakeFood[j].setVisible(false);
					snakeFoodSprite[j].setVisible(false);
					
					segments++;
					if (segments > 50) segments = 50;
					for (int i=0; i<segments; i++) {
						snakeBody[i].setVisible(true);
						score +=1;
						int x = this.getSpriteX();
						int y = this.getSpriteY();
						x -= 20*i;
						y -= 20*i;
						snakeBody[i].setLocation ( x, y );
						//System.out.println("segment:" + i + " / "+ x + "," + y);
						//System.out.println("Your score: " + score);
					}
					
				}
			}
			
		}
		
	}
	
	@Override
	public void run() {
		while (moving) {
			//update label position
			for (int k = 0; k < segments; k++) {
				int x = this.getSpriteX();
				int y = this.getSpriteY();
				x -= 20*k;
				y -= 20*k;
				snakeBody[k].setLocation ( x, y );
			}
			this.detectCollision();
			
			//pause
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
