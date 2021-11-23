import java.awt.Rectangle;

public class Sprite {
	
	//define any data members, attributes, variables
	protected int spriteX, spriteY; //this represents the upper left corner of sprite
	protected String filename;
	protected int spriteH, spriteW;
	protected Rectangle r;
	
	//define getters and setters, accessor and mutators
	public int getSpriteX() {
		return spriteX;
	}
	public void setSpriteX(int spriteX) {
		this.spriteX = spriteX;
		this.r.x = this.spriteX;
	}
	public int getSpriteY() {
		return spriteY;
	}
	public void setSpriteY(int spriteY) {
		this.spriteY = spriteY; 
		this.r.y = this.spriteY;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getSpriteH() {
		return spriteH;
	}
	public void setSpriteH(int spriteH) {
		this.spriteH = spriteH;
	}
	public int getSpriteW() {
		return spriteW;
	}
	public void setSpriteW(int spriteW) {
		this.spriteW = spriteW;
	}
	
	//constructor
	public Sprite() {
		super();
		this.spriteX=0; this.spriteY=0;
		this.filename="";
		this.spriteW=0; this.spriteH=0;
		this.r = new Rectangle(this.spriteX, this.spriteY, this.spriteW, this.spriteH);
	}
	
	public Sprite(int spriteX, int spriteY, String filename, int spriteH, int spriteW) {
		super();
		this.spriteX = spriteX;
		this.spriteY = spriteY;
		this.filename = filename;
		this.spriteH = spriteH;
		this.spriteW = spriteW;
		this.r = new Rectangle(this.spriteX, this.spriteY, this.spriteW, this.spriteH);
	}
	
	//display function
	public void Display () {
		System.out.println("X,Y: "+ this.spriteX + ", " + this.spriteY);
	}
	
	//miscellaneous functions
	public Rectangle getRectangle() {
		return this.r;  
	}

}
