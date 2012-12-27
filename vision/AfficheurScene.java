package vision;

import geometrie.Transformation;
import geometrie.Point;
import geometrie.Point2D;
import geometrie.Matrice4;
import scene.Scene;
import scene.Facette3D;
import java.util.Iterator;
import java.util.ArrayList;
import scene.Modele3D;
import java.util.Collections;


/**Cette classe contient la scène et la camera, elle génère les données à fournir à l'IHM pour l'affichage **/

public class AfficheurScene implements Affichable {
	/**Elements de la scene**/
	private Scene scene;
	private int dimU, dimV;
	private double taillePixel;
	
	/**Elements de la camera **/
	private Matrice4 Mproj;
        private Transformation MsAc; 


	
	/**Par defaut, à l'appel de l'afficheur scene, la camera sera en position projection orthogonale et la matrice de transformation vaudra l'identité**/
	public AfficheurScene(Scene scene,int dimU, int dimV, double taillePixel) {
	       this.scene=scene; this.dimU=dimU; this.dimV=dimV; this.taillePixel=taillePixel;
	       Mproj=Mproj.setOrtho();
	       MsAc=MsAc.identite();
	}


	/**Methode de l'interface Affichable **/
	public Iterator<ImageFacette> getImagesFacettes() {

	Point2D p1= new Point2D();
	Point2D p2= new Point2D();
	Point2D p3= new Point2D();
	Point s1= new Point();
	Point s2= new Point();
	Point s3= new Point();

	ArrayList<ImageFacette> liste=new ArrayList<ImageFacette>(); //On crée une liste ou l'on va stocker les images des facettes//
	
	Iterator<Modele3D> itrMod=(scene.getSceneModele()).iterator(); //itrMod recoit les modeles de la scene//
	Iterator<Facette3D> itrF3D;
	
	//Tant qu'il reste un modele, on depile itrMod//
	while(itrMod.hasNext()){  	
	  itrF3D=(itrMod.next()).getFacettes();
	  
	  //Tant qu'il reste une facette3D dans le modele, on depile//
	  while(itrF3D.hasNext()){
	       Facette3D F3D=itrF3D.next();
	       
	       //On calcule l'image de la facette à partir de projection
	       //prodTP execute le produit d'une transformation et un point => renvoit un point3D//
	       //prodMP execute le produit d'une matrice et d'un point => renvoit un point2D//
	            
	       //Effet de la transformation sur les points (sommets de la facette)
	       s1=s1.prodTP(MsAc, F3D.getS1());
	       s2=s2.prodTP(MsAc, F3D.getS2());
	       s3=s3.prodTP(MsAc, F3D.getS3());
	       
	       //Effet de la projection sur les points
	       p1=p1.prodMP( Mproj, s1);
	       p2=p2.prodMP( Mproj, s2);
	       p3=p3.prodMP( Mproj, s3);
	       
	       
	       //z est la moyennne des coordonnées en z des sommets de la facette 3D
	       double z= ((s1.getZCartesien())+(s2.getZCartesien())+(s3.getZCartesien()))/3  ;
	       	     
	       // On l'ajoute dans la liste //
	       liste.add(new ImageFacette(p1,p2,p3,z)); 
	   }
	 }
	  
	  //On trie la liste par z decroissant//
	  Collections.sort(liste);
	  Iterator<ImageFacette> Itr=liste.iterator();
	  return Itr;
	 }



	/**Permet d'appliquer une transformation à la camera **/ 
	public void positionneCamera(Transformation t){		
	  MsAc=t; 
	}

	/**Permet de se placer en projection orthographique**/
	public void setToCameraOrthographique() {
	  Mproj=Mproj.setOrtho();
	}
	   
	/**Permet de se placer en projection perspective **/
	public void setToCameraPerspective(int angle) {
	  Mproj=Mproj.setPerspe(dimU,dimV,angle);
	}
	
}
