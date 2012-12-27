package scene;

import java.util.Iterator;
import geometrie.Point;


/**Cette Classe permet de representer les modeles 3D
   *On declare une unique méthode permettant de recuperer les facettes du modèle
**/
public interface Modele3D {
     
     
     /** Méthode permettant d'obtenir les facettes constituant le modèle */
     public Iterator<Facette3D> getFacettes();

}