import ihm.ControleurApplication;
import ihm.FenetrePrincipale;
import scene.Scene;
import vision.AfficheurScene;
import scene.ModeleMaillage;
import geometrie.Point;
import scene.Cube;
import scene.Cylindre;
import scene.HaltereCarree;
import java.util.Locale;
import scene.Cone;


/**
 * Programme principal du TP: crée une scène, puis lance l'interface
 * d'affichage de cette scène 
 * Différents modèles pourront être testé en decommentant les lignes
 */
public class TPVisu3D {
	/**
	 * Point de lancement de l'application. Utilisation: java TPVisu3D.
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
	 * Crée la scène à afficher dans l'interface
	 * Différents modèles peuvent être afficher en décommentant les lignes misent en commentaires
	 */
	private static Scene creeScene() {
		Scene scene = new Scene();
		
		//Necessaire pour l'ouverture des fichiers.off//
		Locale.setDefault(Locale.US);

//-------------------------- Le modele à ajouter est à décommenter ----------------------------------------------/
		//Un facteur de grandissement=30 dans ModeleMaillage est à redefinir selon le fichier off//		
		ModeleMaillage modele=new ModeleMaillage("temple.off",30);
//		scene.addModele(modele);	  
		//ModeleMaillage modele=new ModeleMaillage("camel.off",30);
		//scene.addModele(modele)
		
//		scene.addModele(new Cube(new Point(200,200,200),100));
		//scene.addModele(new Cylindre(new Point(100,0,100),new Point(),100,500));
		scene.addModele(new Cone(new Point(100,100,100),100,50));
//		scene.addModele(new HaltereCarree(new Point(100,0,100),new Point(),50,500));

		return scene;
	}
}
