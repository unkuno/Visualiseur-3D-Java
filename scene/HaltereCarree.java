package scene;
import java.lang.Math;
import java.util.Iterator;
import java.util.ArrayList;
import geometrie.Point;
import scene.Cube;
import scene.Cylindre;


/**
  *Classe representant un modele de type HaltereCarree constitué de deux cubes et d'un cylinde
  **/

public class HaltereCarree implements Modele3D {
     /**
       *Centre du cercle representant la premiere base du cylindre
       **/
     Point p1;
     /**
       *Centre du cercle representant la deuxieme base du cylindre
       **/     
     Point p2; 
     /**
       *Coté des cubes
       **/          
     double cote; 
     /**
       *Resolution du cylindre
       **/          
     int resolution; 
     
     
    

     /**
     *Constructeur 
     **/  
     public HaltereCarree( Point c1, Point c2,double c, int res ) {
	  p1=c1;
	  p2=c2;
	  cote=c;
	  resolution=res;
     }       
	  



     /** Methode de l'interface
     *@see Modele3D
     **/ 
     public Iterator<Facette3D> getFacettes() {

	  //Creation d'un iterateur//
	  Iterator<Facette3D> Itr;
	       
	  //Creation d'une liste de facettes 3D// 
	  ArrayList<Facette3D> liste=new ArrayList<Facette3D>();;
		    
	  //On définit le premier cube de l'haltere//	
	  Cube Cube1=new Cube( new Point( p1.getXCartesien()-cote/2,p1.getYCartesien()-cote/2,p1.getZCartesien()-cote/2) , cote);	
	  //On ajoute ses facettes a la liste//
	  Itr=Cube1.getFacettes();
	  while(Itr.hasNext()) {
	       liste.add(Itr.next());
	  }  
	  
	  //Idem pour le deuxieme cube de l'haltere//	
	  Cube Cube2=new Cube( new Point( p2.getXCartesien()-cote/2,p2.getYCartesien()-cote/2,p2.getZCartesien()-cote/2) , cote);	
	  Itr=Cube2.getFacettes();
	  while(Itr.hasNext()) {
	       liste.add(Itr.next());
	  }  	
	  
	  //Idem pour le cylindre
	  Cylindre barre=new Cylindre( p1,p2,cote/4,resolution);
	  Itr=barre.getFacettes();
	  while(Itr.hasNext()) {
	       liste.add(Itr.next());
	  } 	
	  
     
	  //On retourne l'iterateur contenant la liste des facettes 
	  Itr=liste.iterator();
	  return Itr;		
	  }		    

}