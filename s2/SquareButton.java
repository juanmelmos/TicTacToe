package s2;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.io.Serializable;

public class SquareButton extends JButton implements Serializable{
    private static final long serialVersionUID = 1L;

	public SquareButton() {
        setPreferredSize(new Dimension(100, 100));
        setBackground(new Color(255, 255, 255));
    }
    
    public SquareButton(String text) {
    	setText(text);
        setPreferredSize(new Dimension(100, 100));
        setBackground(new Color(255, 255, 255));
    }
}
