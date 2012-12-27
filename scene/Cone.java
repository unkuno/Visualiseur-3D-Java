package scene;

import java.util.Iterator;
import java.util.ArrayList;
import geometrie.Point;
import java.lang.Math;
import geometrie.Rebouchage;
	

/**Classe representant un modele de type Cone
  *@see TestRebouchage
  **/
public class Cone implements Modele3D {
     
     /** 
       * Sommet du cone 
       **/
     private Point s; 
     /**
       *nombre d'arrete du polygone formant sa base
       **/   
     private int nbre; 
     /**
       *hauteur du cone 
        **/
     private double rayon; 

	  
     /**
      *Constructeur , la hauteur du cone est fixé à 200 par defaut
      **/
     public Cone(Point p, int n, double r){
	  s=p; 
	  nbre=n;
	  rayon=r;
     }

     /**
       * Methode de l'interface
       *@see Modele3D
       **/
     public Iterator<Facette3D> getFacettes() {


	  ArrayList<Facette3D> liste=new ArrayList<Facette3D>();
	  
	  int i;	  
	  // Les facettes seront crées à partir du point sommet et de deux points formants la base//
	  // La base est représenté par une "distribution repartie" d'un cercle de rayon r="rayon" en n="nbre" points//
	  for(i=0;i<nbre;i++){     
	      
	       //Calcul des deux points appartenant à la base //
	       Point p1=new Point(s.getXCartesien()+ Math.cos((2*Math.PI*i)/nbre)*rayon,  s.getYCartesien()+ Math.sin((2*Math.PI*i)/nbre)*rayon,  s.getZCartesien()+200);
	       Point p2=new Point(s.getXCartesien()+ Math.cos((2*Math.PI*(i+1))/nbre)*rayon,  s.getYCartesien()+ Math.sin((2*Math.PI*(i+1))/nbre)*rayon,  s.getZCartesien()+200);
	       
	       //On rajoute la facette à la liste//
	       liste.add(new Facette3D(s,p1,p2));
	  
	  }
	  //Creation d'un iterateur que l'on va renvoyer//
	  Iterator<Facette3D> Itr=liste.iterator();
	  return Itr;
      }
     
     /*public Iterator<Facette3D> Triangulation(Point polygone[]) {
	 
	 return RebouchageTrouRecCachemod(polygone);
     }*/
     
      
}