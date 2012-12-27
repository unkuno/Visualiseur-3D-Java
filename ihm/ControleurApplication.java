package ihm;

import geometrie.Transformation;

import java.util.LinkedList;
import java.util.List;

import vision.Affichable;


/**
 * Contrôleur de l'application, au sens MVC: reçoit des notifications
 * des vues et communique en conséquence avec le modele.
 * Cette classe contrôle en particulier la position de la
 * caméra par rapport à la scène. Elle est gérée en deux temps:
 * <li>rotation de la caméra autour du centre du référentiel scène.
 * Ainsi, la caméra est toujours pointée vers le centre de la scène.</li>
 * <li>translation du centre de projection suivant l'axe z, dans le
 * référentiel caméra.</li>
 * La translation est appliquée après la composition de toutes les 
 * rotations, pour gérer un zoom.
 * <p>
 * Valeurs initiales:
 * <li>Rotation: identité, soit la caméra placée sur l'axe z de Ref_Scene</li>
 * <li>Translation: ad hoc, pour voir une scène centrée de hauteur dimV/2
 * avec une camera perspective d'ouverture 30 degrés. Pourquoi pas!</li>
 * <p>
 * Cette classe implémente l'interface Observable, pour notifier aux
 * Observateur enregistrés qu'ils doivent se mettre a jour.
 */
public class ControleurApplication implements Observable {

	/** Le modèle (au sens MVC) auquel ce contrôleur est associé. */
	Affichable modele;

// DONNEES DE L'APPLICATION
	private int dimU;
	private int dimV;

	protected int getDimU() {
		return dimU;
	}
	
	protected int getDimV() {
		return dimV;
	}
	
	public ControleurApplication(Affichable modele, int dimU, int dimV) {
		this.modele = modele;
		this.dimU = dimU;
		this.dimV = dimV;
		observateurs = new LinkedList<Observateur> ();
		initPositionCamera();
	}
		
// GESTION DE LA CAMERA
	/** Position de la caméra: rotation courante */
	private Transformation cameraRot;
	/** La distance courante entre la camera et la scene */
	private double translationZ;
	private double deltaZ;
	
	/** 
	 * Compose les transformation courantes (rotation puis translation), met
	 * à jour la caméra du modèle, puis lance le réaffichage.
	 */
	private void metAJourCamera() {
		Transformation transfo = Transformation.compose(
				Transformation.translation(0, 0, translationZ), cameraRot);
		modele.positionneCamera(transfo);
		miseAJourAffichage();		
	}

	/** Remet la caméra dans sa position initiale */
	protected void initPositionCamera() {
		cameraRot = Transformation.identite();

		// avance le modele de tz dans Ref_Camera (ou recule la camera de tz...)
		translationZ = dimV/2;		
		// par defaut: 1/10 de la distance initiale
		deltaZ = translationZ/10; 
		metAJourCamera();
	}
	
	/** 
	 * Compose une rotation avec la rotation actuelle de la caméra.
	 * @rot la nouvelle rotation, composée A GAUCHE de la rotation actuelle.
	 */
	protected void composeRotationCamera(Transformation rot) {
		// composition avec la rotation actuelle (ordre!)
		cameraRot = Transformation.compose(rot,cameraRot);
		metAJourCamera();
	}
	
	/** Reduit la distance caméra/scène. */
	protected void approcheCamera() {
		translationZ -= deltaZ;
		metAJourCamera();
	}
	
	/** Augnmente la distance caméra/scène */
	protected void eloigneCamera() {
		translationZ += deltaZ;		
		metAJourCamera();
	}

	/** Passe la caméra en mode orthographique (parallèle) */
	protected void setToCameraOrthographique() {
		modele.setToCameraOrthographique();
		miseAJourAffichage();
	}
	
	/** 
	 * Passe la caméra en mode perspective.
	 *  @param ouverture l'angle d'ouverture de la caméra (pas de check). 
	 */
	protected void setToCameraPerspective(int ouverture) {
		modele.setToCameraPerspective(ouverture);
		miseAJourAffichage();
	}
	
	/** Envoi le signal de mise à jour à tous les Observateurs enregistrés */
	protected void miseAJourAffichage() {
		notifieObservateurs();
	}

	
// IMPLEMENTATON OBSERVABLE
	private List<Observateur> observateurs;
	
	public void ajouteObservateur(Observateur obs) {
		observateurs.add(obs);		
	}

	public void notifieObservateurs() {
		for (Observateur obs: observateurs)
			obs.actualise();
	}
}
