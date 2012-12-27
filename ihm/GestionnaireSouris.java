package ihm;


import geometrie.Point;
import geometrie.Transformation;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

/**
 * Classe de gestion des évènements souris sur la zone de dessin,
 * qui contrôlent la position de la caméra par rapport à la scène.
 * Manipulations:
 * <li>Les rotations sont gérées en cliquant dans la zone et en déplaçant
 * la souris. Une trackall est utilisée.
 * (voir par exemple http://viewport3d.com/trackball.htm).</li>
 * <li>La translation peut être modifiée via la roulette de la souris.</li>
 */
class GestionnaireSouris extends MouseInputAdapter {

	private ControleurApplication controleur;	
	private int pressedX, pressedY;
	private TrackBall trackBall;
	
	public GestionnaireSouris(ControleurApplication controleur) {
		this.controleur = controleur;
		trackBall = new TrackBall(controleur.getDimU(), controleur.getDimV());
	}

	
//================================= EVENEMENTS GERES
	public void mouseClicked(MouseEvent e) {
		// rien
	}

	public void mousePressed(MouseEvent e) {
		this.pressedX = e.getX();
		this.pressedY = e.getY();
    }

	public void mouseReleased(MouseEvent e) {
		if (pressedX == e.getX() && pressedY == e.getY())
			return;

		Transformation rot = trackBall.calculeRotation(this.pressedX, this.pressedY,
		                                               e.getX(), e.getY());
		// notification au controleur
		controleur.composeRotationCamera(rot);
    }

	
	
	
//================================= TRACKBALL
	private class TrackBall {
		private int dimU, dimV;
		
		public TrackBall(int dimU, int dimV) {
			this.dimU = dimU;
			this.dimV = dimV;
		}
		
		public Transformation calculeRotation(int pressedU, int pressedV,
		                                      int releasedU, int releasedV) {
			Point pressed = projectOnTrackBall(pressedU, pressedV);
			Point released = projectOnTrackBall(releasedU, releasedV);
			normalise(pressed);
			normalise(released);

			Point axeRotation = produitVectoriel(pressed, released);
			normalise(axeRotation);
			double angle = Math.acos(produitScalaire(pressed, released));

			Transformation rot = Transformation.rotation(axeRotation, angle);
			return rot;
		}
		
		private Point projectOnTrackBall(double x, double y) {
			// changement echelle vers [0,0] - [2,2],
			x /= (dimU / 2.0);
			y /= (dimV / 2.0);
			
			// translation vers [-1,1] - [-1,1] et inversion axe Y (camera / world) 
//			x = x - 1;
//			y = 1 - y;
			x = 1 - x;
			y = 1 - y;
			double z = 1 - x*x - y*y;
			z = z > 0 ? Math.sqrt(z) : 0;
			// normalisation
			double norm = Math.sqrt(x*x + y*y + z*z);
			return new Point(x/norm, y/norm, z/norm);
		}
				
		// fondamentalement, ces methodes (normalise, pdtVectoriel, pftScalaire)
		// devraient etre ds Point!! Le demander??
		public void normalise(Point p) {
			double x = p.getXCartesien();
			double y = p.getYCartesien();
			double z = p.getZCartesien();
			double norm = Math.sqrt(x*x + y*y + z*z);
			p.set(x/norm, y/norm, z/norm);
		}
		
		// on considere this p et q comme des vecteurs
		public double produitScalaire(Point p, Point q) {
			return (p.getXCartesien() * q.getXCartesien()
			      + p.getYCartesien() * q.getYCartesien()
			      + p.getZCartesien() * q.getZCartesien()); 
		}

		public Point produitVectoriel(Point p, Point q) {
			double x = p.getYCartesien() * q.getZCartesien() - p.getZCartesien() * q.getYCartesien();
			double y = p.getZCartesien() * q.getXCartesien() - p.getXCartesien() * q.getZCartesien();
			double z = p.getXCartesien() * q.getYCartesien() - p.getYCartesien() * q.getXCartesien();
			return new Point(x, y, z);
		}	
	} // class TrackBall
	
}
