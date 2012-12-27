package ihm;


import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Classe representant la fenetre principale de l'application.
 * Elle est composee:
 * <li>d'une zone de boutons controlant l'application</li>
 * <li>d'une zone de dessin de la scene observee<li>.
 * <p>
 * Cette classe gère les évènements claviers de l'application:
 * <li> flèche haut : zoom in, rapproche la caméra de la scène. 
 * <li> flèche bas : zoom out, éloigne la caméra de la scène.
 */
@SuppressWarnings("serial")
public class FenetrePrincipale extends JFrame {

	private ControleurApplication controleur;
	
	public FenetrePrincipale(ControleurApplication controleur) {
		this.controleur = controleur;
		setTitle("TP APOO: Visualisation d'objets 3D");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ZoneDessin zoneDessin = new ZoneDessin(controleur);
		ZoneBoutons zoneBoutons = new ZoneBoutons(controleur);

		JPanel mainPane = new JPanel();
		BoxLayout box = new BoxLayout(mainPane, BoxLayout.X_AXIS);
		mainPane.setLayout(box);
		mainPane.add(zoneBoutons);
		mainPane.add(zoneDessin);
		
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	    manager.addKeyEventDispatcher(new KeyDispatcher());

		setContentPane(mainPane);
		pack();
		setVisible(true);
	}


	/** Traite les evenements claviers. */
//	Note technique: 
//	Un KeyEventDispatcher permet de gerer les evenements pour toute
//	l'application, quel que soit le focus sur tel ou tel composant
//	(par rapport a un KeyListener)
	private class KeyDispatcher implements KeyEventDispatcher {
	    public boolean dispatchKeyEvent(KeyEvent e) {
	        if (e.getID() == KeyEvent.KEY_PRESSED) {
	    		switch (e.getKeyCode()) {
	    		case KeyEvent.VK_UP:    // Fleche Haut
	    			controleur.approcheCamera();
	    			break;
	    		case KeyEvent.VK_DOWN:  // Fleche Bas
	    			controleur.eloigneCamera();
	    			break;
	    		default:
	    		}
	        }
	        // KEY_RELEASED et KEY_TYPED ignores
	        return false;
	    }
	}
}
