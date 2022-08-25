package am.root;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import am.pages.LoginPage;

public class Application {
	private static JFrame app;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createApp();
			}
		});
	}

	public static void createApp() {
		app = new JFrame("DeliverAreMe");

		setPanel(new LoginPage());

		app.setVisible(true);
	}

	public static void setPanel(JPanel panel) {

		panel.setBorder(new EmptyBorder(12, 12, 12, 12));

		app.getContentPane().removeAll();
		app.getContentPane().add(panel);

		repack();
		panel.revalidate();

	}

	public static void dispose() {
		app.dispose();
	}

	public static void repack() {

		app.pack();
		app.setLocationRelativeTo(null);

	}
}
