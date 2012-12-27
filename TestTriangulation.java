import geometrie.Point;
import ihm.ControleurApplication;
import ihm.FenetrePrincipale;
import scene.Scene;
import vision.AfficheurScene;
import java.util.Locale;
import scene.Cone;
import scene.Polygone;
import scene.Facette3D;
import java.util.*;

/** Programme de test de la triangulation d'un polygone 
  * Le programme crée un cone (sans base) formé de n=5 points et stocke la série de points formant sa base dans un tableau
  * Un modèle "Polygone" va être crée prenant pour parametre cette série de points
  * la méthode getFacettes de la classe Polygone implemente l'algorithme de rebouchage afin d'obtenir en sortie une série de facette correspondant a
  * la triangulation optimale 
  * On affiche le polygone et on verifie qu'il forme bien une base
  * On ajoute eventuellement le cone(sans base) à la scène afin de former un modèle complet (sans trous)
  **/   


public class TestTriangulation {
	/**
	 * Point de lancement de l'application. Utilisation: java TestTriangulation
	 */
	public static void main(String[] args) {
		Scene scene = creeScene();
		int dimU = 800;
		int dimV = 600;
		// 1 pixel == 0.5 unites dans Ref_Scene (ad hoc)
		double taillePixel = 0.5;	
		
		new FenetrePrincipale(new ControleurApplication(
				new AfficheurScene(scene, dimU, dimV, taillePixel),
				dimU, dimV));
	}

	/**
	 * Crée la scène à afficher dans l'interface.
	 */
	private static Scene creeScene() {
	       Scene scene = new Scene();
	       Locale.setDefault(Locale.US);

	       //On crée un cone dont la base est constitué de n=5 points et le rayon de la base vaut 100// 
/* MC: parametrer le nb de sommets! */
	       Cone C=new Cone(new Point(),5,100);
	       Iterator<Facette3D> Itr=C.getFacettes();
	  
	       //On stocke l'ensemble des points formants le base dans un tableau de points//
	       Point [] base = new Point [5];	  
	       int i;
	       for(i=0; i<5;i++){
/* MC: toujours utilise base.length => code independant de la constante
 * utilisee (ici 5) */
	    	   Facette3D F=Itr.next();
	       base[i]=F.getS3();
	       
	       }
     
	  
	       Polygone P=new Polygone(base);
	       scene.addModele(P);

	  
	       //Si on souhaite afficher le cone "complet", on ajoute le cone sans base à la scène//
	       scene.addModele(C);       
	       
		return scene;
	}
}
