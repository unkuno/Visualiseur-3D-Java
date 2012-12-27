package scene;

import java.util.Iterator;
import java.util.ArrayList;
import geometrie.Point;

	
/**
  *Classe representant un modele de type Cube
  **/
public class Cube implements Modele3D {
     /**
       *origine=point en bas gauche
       **/
     private Point o;
     /** 
       * Cote du cube 
       **/
     private double c; 
	  
    


     /**
       *Constructeur 
       **/  
     public Cube(Point p, double d){
	  o=p;
	  c=d;
     }

    
     /** Methode de l'interface
       *@see Modele3D
       **/ 
    public Iterator<Facette3D> getFacettes() {


	  //On definit les facettes du cube de facon brutale en decoupant chaques faces du cube en 2 facettes//
	  
	  //Creation d'une liste de facettes 3D// 
	  ArrayList<Facette3D> liste=new ArrayList<Facette3D>();
	       
	  //Ajout des facettes//
	  liste.add(new Facette3D(new Point(o.getXCartesien(),o.getYCartesien(),o.getZCartesien()),new Point(o.getXCartesien()+c,o.getYCartesien(),o.getZCartesien()),new Point(o.getXCartesien(),o.getYCartesien()+c,o.getZCartesien())));
	  liste.add(new Facette3D(new Point(o.getXCartesien(),o.getYCartesien(),o.getZCartesien()),new Point(o.getXCartesien(),o.getYCartesien(),o.getZCartesien()+c),new Point(o.getXCartesien()+c,o.getYCartesien(),o.getZCartesien()+c)));
	  liste.add(new Facette3D(new Point(o.getXCartesien(),o.getYCartesien(),o.getZCartesien()),new Point(o.getXCartesien(),o.getYCartesien()+c,o.getZCartesien()),new Point(o.getXCartesien(),o.getYCartesien()+c,o.getZCartesien()+c)));  
	  liste.add(new Facette3D(new Point(o.getXCartesien()+c,o.getYCartesien()+c,o.getZCartesien()),new Point(o.getXCartesien()+c,o.getYCartesien(),o.getZCartesien()),new Point(o.getXCartesien(),o.getYCartesien()+c,o.getZCartesien())));	  
	  liste.add(new Facette3D(new Point(o.getXCartesien()+c,o.getYCartesien(),o.getZCartesien()+c),new Point(o.getXCartesien(),o.getYCartesien(),o.getZCartesien()),new Point(o.getXCartesien()+c,o.getYCartesien(),o.getZCartesien())));	  
	  liste.add(new Facette3D(new Point(o.getXCartesien(),o.getYCartesien()+c,o.getZCartesien()+c),new Point(o.getXCartesien(),o.getYCartesien(),o.getZCartesien()+c),new Point(o.getXCartesien(),o.getYCartesien(),o.getZCartesien())));	       
	  liste.add(new Facette3D(new Point(o.getXCartesien(),o.getYCartesien(),o.getZCartesien()+c),new Point(o.getXCartesien()+c,o.getYCartesien(),o.getZCartesien()+c),new Point(o.getXCartesien(),o.getYCartesien()+c,o.getZCartesien()+c)));
	  liste.add(new Facette3D(new Point(o.getXCartesien()+c,o.getYCartesien()+c,o.getZCartesien()+c),new Point(o.getXCartesien()+c,o.getYCartesien(),o.getZCartesien()+c),new Point(o.getXCartesien(),o.getYCartesien()+c,o.getZCartesien()+c)));	  
	  liste.add(new Facette3D(new Point(o.getXCartesien()+c,o.getYCartesien(),o.getZCartesien()),new Point(o.getXCartesien()+c,o.getYCartesien(),o.getZCartesien()+c),new Point(o.getXCartesien()+c,o.getYCartesien()+c,o.getZCartesien())));
	  liste.add(new Facette3D(new Point(o.getXCartesien()+c,o.getYCartesien()+c,o.getZCartesien()+c),new Point(o.getXCartesien()+c,o.getYCartesien(),o.getZCartesien()+c),new Point(o.getXCartesien()+c,o.getYCartesien()+c,o.getZCartesien())));	  
	  liste.add(new Facette3D(new Point(o.getXCartesien(),o.getYCartesien()+c,o.getZCartesien()),new Point(o.getXCartesien()+c,o.getYCartesien()+c,o.getZCartesien()),new Point(o.getXCartesien(),o.getYCartesien()+c,o.getZCartesien()+c)));
	  liste.add(new Facette3D(new Point(o.getXCartesien()+c,o.getYCartesien()+c,o.getZCartesien()+c),new Point(o.getXCartesien()+c,o.getYCartesien()+c,o.getZCartesien()),new Point(o.getXCartesien(),o.getYCartesien()+c,o.getZCartesien()+c)));  
  
	  //Creation d'un iterateur pointant vers la liste//
	  Iterator<Facette3D> Itr=liste.iterator();
	  return Itr;
      }
      
}