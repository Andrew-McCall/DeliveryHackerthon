package am.common.swing;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TickBox extends JPanel {

	private static final long serialVersionUID = 4735372540979924601L;

	private JCheckBox checkBox;

	public TickBox(String label) {
		this.add(new JLabel(label));
		this.checkBox = new JCheckBox();
		this.add(checkBox);
	}

	public TickBox(String label, boolean isChecked) {
		this(label);
		checkBox.setSelected(isChecked);
	}

	public boolean isChecked() {
		return checkBox.isSelected();
	}

}
