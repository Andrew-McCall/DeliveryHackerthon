package am;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import am.data.entites.Login;
import am.pages.LoginPage;

public class Application {
	private static JFrame app;
	private static Login currentUser;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createApp();
			}
		});
	}

	public static void createApp() {
		app = new JFrame("DeliverAreMe");

		app.setResizable(false);

		setPanel(new LoginPage());
		// gisetPanel(new ManageRoutes());

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

	public static Login getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(Login currentUser) {
		Application.currentUser = currentUser;
	}

}
