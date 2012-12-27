package ihm;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Iterator;
import java.awt.Color;
import javax.swing.JPanel;

import vision.ImageFacette;

/**
 * Zone de dessin de la scène à afficher.
 * <p>
 * Evenements captes par la zone de dessin:
 * <li> Rotation à la souris. Leur gestion est déleguée à un
 * gestionnaire externe.
 * <li> Translation via les flèches haut et bas.
 * <p>
 * Cette classe implémente l'interface Observateur, et s'enregistre
 * auprès du contrôleur (qui implement Observable). Ainsi, elle
 * reçevra les signaux de mise a jour envoyés par le contrôleur.
 */ 
@SuppressWarnings("serial")
class ZoneDessin extends JPanel implements Observateur {
	private ControleurApplication controleur;
	private GestionnaireSouris mickey;
	
	public ZoneDessin(ControleurApplication controleur) {
		this.controleur = controleur;
		setPreferredSize(new Dimension(controleur.getDimU(),
		                               controleur.getDimV()));
		mickey = new GestionnaireSouris(controleur);
		this.addMouseListener(mickey);
		// ecoute les signaux de maj du controleur
		this.controleur.ajouteObservateur(this);
	}

	/** Methode appelee suite a la notification de maj du controleur */
	@Override
	public void actualise() {
		repaint();
		// provoque le reaffichage du composant, avec au bout de
		// la chaine l'execution de paintComponent, redefinie ci-dessous
	}	

	/**	
	 * Methode de dessin effectif de la scene.
	 * Utiliser les methodes de Graphics: fillPolygon, drawPolygon...
	 */
	
	public void paintComponent(Graphics g) {
	ImageFacette img= new ImageFacette();
		// super.paintComponent clears offscreen pixmap,
		// since we're using double buffering by default.
	       super.paintComponent(g);
		
	       g.translate(300,200);
	       
	       Iterator<ImageFacette> itr=controleur.modele.getImagesFacettes();
	       
	       while (itr.hasNext()) {
		    img=itr.next();		    
		    g.setColor(Color.BLACK);
		    g.drawPolygon(img.CoordX(),img.CoordY(),3);
		    g.setColor(Color.ORANGE);		    
		    g.fillPolygon(img.CoordX(),img.CoordY(),3);
	       }   
		    
	       

	}
}
