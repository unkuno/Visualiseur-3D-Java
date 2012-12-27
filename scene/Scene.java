package scene;

import geometrie.Transformation;
import geometrie.Matrice4;
import java.util.Iterator;
import java.util.ArrayList;
import scene.Modele3D;

/**
  *Classe representant la scene
**/

public class Scene {
     
     /** La scene est constitué d'un ensemble (ici modelisé par une liste) de modele 3D
      *@see Modele3D
     **/
     private ArrayList<Modele3D> Modele;
     

     public Scene() {
     Modele=new ArrayList<Modele3D>();
     }


     /** Ajoute un modele à la scene **/
     public void addModele(Modele3D M){
	  Modele.add(M);
     }	  
   
     /** Permet d'obtenir les différents modèles dont la scène est constitué 
       *@return une liste contenant l'ensemble des modèles
     **/
     public ArrayList<Modele3D> getSceneModele() {
	  return Modele;
     }	      
}  