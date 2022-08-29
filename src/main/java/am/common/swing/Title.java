package am.common.swing;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Title extends JLabel {

	private static final long serialVersionUID = -3117580502973274916L;

	public Title(String text) {
		super(text, SwingConstants.CENTER);
		this.setFont(new Font("Verdana", Font.BOLD, 16));
	}

}
