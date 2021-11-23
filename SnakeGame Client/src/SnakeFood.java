
public class SnakeFood extends Sprite {
	
	private Boolean visible;
	private Boolean eaten;
	
	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getEaten() {
		return eaten;
	}

	public void setEaten(Boolean eaten) {
		this.eaten = eaten;
	}

	public SnakeFood() {
		super(0, 0, "images/redlight.png", 11, 11);
	} 
	
}
