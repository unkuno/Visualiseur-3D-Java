package scene;
import geometrie.Rebouchage;
import java.util.Iterator;
import java.util.ArrayList;
import geometrie.Point;

/**
  *Classe permettant de tester l'algorithme de rebouchage
  *Elle contient un tableau de points representant une suite de points formants un polygone à reboucher   
  **/
public class Polygone implements Modele3D {
     /**
       *Suite de points formants un polygone à reboucher.
       **/
     private Point [] polygone;


     public Polygone(Point[] p){
	  polygone=p;
	  
     }
     
     /** Methode de l'interface,
       * L'algorithme du rebouchage va être employé sur la suite de points "polygone" afin de renvoyer la triangulation optimale du polygone formée
       * par ces points.
       *@see Modele3D
       **/ 
    public Iterator<Facette3D> getFacettes() { 
	  
	  Rebouchage r=new Rebouchage();
	  
	  Iterator<Facette3D> Itr=null;
	  
	  try { Itr=r.rebouchageTrouRecCachemod(polygone);
	  } catch (Exception e) {
	   System.out.println("Erreur rebouchage");
	  }

	  return Itr;
	  
    }

}