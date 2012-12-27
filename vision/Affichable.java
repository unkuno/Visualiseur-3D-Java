package vision;

import geometrie.Transformation;

import java.util.Iterator;

/** 
 * Une classe implementant cette interface peut etre utilisee
 * dans l'application du TP: visualisation de facettes, avec
 * vue controlee par une camera.
 */
public interface Affichable {
	/**
	 * Retourne l'ensemble des images de facettes 3D a afficher.
	 * L'ordre d'affichage est celui du parcours de l'iterateur.
	 */
	public Iterator<ImageFacette> getImagesFacettes();
	
	/**
	 * Definit la position de la camera.
	 * @param t nouvelle position, remplace la position actuelle.
	 */
	public void positionneCamera(Transformation t);
	
	/** 
	 * Met la camera en mode de projection orthographique.
	 */
	public void setToCameraOrthographique();
	
	/** 
	 * Met la camera en mode de projection perspective.
	 * @param angle l'angle d'ouverture de la camera. 
	 */
	public void setToCameraPerspective(int angle);
}
