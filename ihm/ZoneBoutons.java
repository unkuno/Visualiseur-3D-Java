package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/** 
 * Boutons de controle de l'application:
 * <li>un bouton pour reinitialiser la position de la camera</li>
 * <li>un groupe de boutons a choix pour modifier le type de la camera
 * (orthographique, perspective)</li>
 */
@SuppressWarnings("serial")
class ZoneBoutons extends JPanel {
	
	private ControleurApplication controleur;
	
	public ZoneBoutons(ControleurApplication controleur) {
		this.controleur = controleur;
		this.setBorder(BorderFactory.createLineBorder(Color.black));

		BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(box);				
		ajouteEspaceVertical();
		creeBoutonReset();
		ajouteEspaceVertical();
		creeBoutonsCamera();
		this.add(Box.createVerticalGlue()); // tasse en haut
	}
	
	private void ajouteEspaceVertical() {
		this.add(Box.createRigidArea(new Dimension(0,20)));
	}	

//=== RESET
	private void creeBoutonReset() {
		JButton boutonResetView = new JButton("Reset view");
		this.add(boutonResetView);
		boutonResetView.addActionListener(new ActionResetView());
	}

	private class ActionResetView implements ActionListener {
		// classe interne: a acces aux attributs (controleur)
		public void actionPerformed(ActionEvent e) {
			controleur.initPositionCamera();
			controleur.miseAJourAffichage();
		}
	}

	
//=== CAMERA
	private void creeBoutonsCamera() {
		JRadioButton radioCameraOrtho = new JRadioButton("orthographique");
		radioCameraOrtho.setActionCommand("ortho");
		radioCameraOrtho.setSelected(true);
		JRadioButton radioCameraPers30 = new JRadioButton("perspective 30°");
		radioCameraPers30.setActionCommand("per30");
		JRadioButton radioCameraPers45 = new JRadioButton("perspective 45°");
		radioCameraPers45.setActionCommand("per45");
		
		ButtonGroup radioGroupCamera = new ButtonGroup();
		radioGroupCamera.add(radioCameraOrtho);
		radioGroupCamera.add(radioCameraPers30);
		radioGroupCamera.add(radioCameraPers45);
		JPanel cameraPanel = new JPanel();
		cameraPanel.setLayout(new BoxLayout(cameraPanel, BoxLayout.Y_AXIS));
		cameraPanel.add(new JLabel("Camera",JLabel.LEFT));
		cameraPanel.add(radioCameraOrtho);
		cameraPanel.add(radioCameraPers30);
		cameraPanel.add(radioCameraPers45);
        this.add(cameraPanel);
        
        // radioListener: joli nom, non?
        ActionRadioCamera radioListener = new ActionRadioCamera();
        radioCameraOrtho.addActionListener(radioListener);
        radioCameraPers30.addActionListener(radioListener);
        radioCameraPers45.addActionListener(radioListener);
	}

	private class ActionRadioCamera implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String selection = e.getActionCommand();
			if (selection.equals("ortho")) {
				controleur.setToCameraOrthographique();
			} else if (selection.equals("per30")) {
				controleur.setToCameraPerspective(30);
			} else if (selection.equals("per45")) {
				controleur.setToCameraPerspective(45);
			}
			controleur.miseAJourAffichage();
		}
	}
}
