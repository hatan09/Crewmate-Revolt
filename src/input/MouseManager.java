package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import ui.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener{
	
	private int mouseX, mouseY;
	private boolean mouseLeft, mouseRight;
	private UIManager uiManager;
	
	public MouseManager() {
		
	}
	
	//make mouseListener listen to this UI besides the component that has this class as a mouseListener
	public void setUIManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}
	
	
	public boolean isPressingLeft() {
		return mouseLeft;
	}
	
	public boolean isPressingRight() {
		return mouseRight;
	}
	
	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}
	
	public void update() {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		//besides setting new values read from the cursor's position, we update the UI's values
		if(uiManager != null) {
			uiManager.onMouseMove(e);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			mouseLeft = true;
		else if(e.getButton() == MouseEvent.BUTTON3)
			mouseRight = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			mouseLeft = false;
		else if(e.getButton() == MouseEvent.BUTTON3)
			mouseRight = false;
		
		//besides setting new values when the user releases the mouse button, we also tell the UI that this event occured
		if(uiManager != null) {
			uiManager.onMouseRelease(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
