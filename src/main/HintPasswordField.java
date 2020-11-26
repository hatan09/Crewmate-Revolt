package main;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

class HintPasswordField extends JPasswordField implements FocusListener {

	  private final String hint;
	  private boolean showingHint;

	  public HintPasswordField(final String hint) {
	    super(hint);
	    this.hint = hint;
	    this.showingHint = true;
	    this.setEchoChar((char)0);
	    this.setForeground(Color.GRAY);
	    this.setHorizontalAlignment(CENTER);
	    super.addFocusListener(this);
	  }

	  @Override
	  public void focusGained(FocusEvent e) {
	    if(this.getText().isEmpty()) {
	      super.setText("");
	      this.setEchoChar('•');
	      this.setHorizontalAlignment(LEFT);
	      showingHint = false;
	    }
	    this.setForeground(Color.BLACK);
	  }
	  @Override
	  public void focusLost(FocusEvent e) {
	    if(this.getText().isEmpty()) {
	      super.setText(hint);
	      showingHint = true;
	      this.setEchoChar((char)0);
	      this.setForeground(Color.GRAY);
	      this.setHorizontalAlignment(CENTER);
	    }
	  }

	  @Override
	  public String getText() {
	    return showingHint ? "" : super.getText();
	  }
	}