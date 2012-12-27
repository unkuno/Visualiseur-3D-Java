package scene;
import java.lang.Math;
import java.util.Iterator;
import java.util.ArrayList;
import geometrie.Point;

/**
  *Classe representant un modele de type Cylindre
  **/

public class Cylindre implements Modele3D {
     /**
       *Centre du cercle representant la premiere base du cylindre
       **/
     Point p1;
     /**
       *Centre du cercle representant la deuxieme base du cylindre
       **/     
     Point p2; 
     /**
       *Rayon des cercles
       **/          
     double r; 
     /**
       *Resolution, plus celle ci est grande, et plus la construction du cône est précise (=nombre de segments dont est constitué chaque cercle)
       **/          
     int resolution; 
     
     
    

     /**
       *Constructeur 
       **/  
     public Cylindre( Point c1, Point c2,double rayon, int res ) {
	  p1=c1;
	  p2=c2;
	  r=rayon;
	  resolution=res;
     }	       
     



     /** Methode de l'interface
       *@see Modele3D
       **/ 
     public Iterator<Facette3D> getFacettes() {


	  
	//Creation d'une liste de facettes 3D// 
	ArrayList<Facette3D> liste=new ArrayList<Facette3D>();;
		
	for (int i=0; i<resolution; i++){
	
		//On découpe le cercle representant la premiere base en série de segments (=resolutions)
		//On stocke en xx et yy les coordonnées du premier point du segments
		double xx = p1.getXCartesien() + r * Math.cos(2*i*Math.PI/resolution);
		double yy = p1.getYCartesien() + r * Math.sin(2*i*Math.PI/resolution);		
		
		//On stocke la facette constitué du centre p1, du point calculé precedemment et de sa projection sur l'axe des y
		liste.add(new Facette3D(p1,new Point(xx,yy,p1.getZCartesien()),new Point(xx,0,p1.getZCartesien())));
		
		
		//On stocke en xxx et yyy les coordonnées du deuxieme point du segments
		double xxx = p2.getXCartesien() + r * Math.cos(2*i*Math.PI/resolution);
		double yyy = p2.getYCartesien() + r * Math.sin(2*i*Math.PI/resolution);
		
		//On stocke la facette constitué du centre p2, du point calculé precedemment et de sa projection sur l'axe des y		
		liste.add(new Facette3D(p2,new Point(xxx,yyy,p2.getZCartesien()),new Point(xxx,0,p2.getZCartesien())));
		
		
		//On ajoute les facettes correspondants au "tube"
		liste.add(new Facette3D(p2,p1,new Point(xx,yy,p1.getZCartesien())));
		liste.add(new Facette3D(new Point(xxx,yyy,p2.getZCartesien()),new Point(xx,yy,p1.getZCartesien()),p2));
		
	}
	 
	//On retourne l'iterateur contenant la liste des facettes 
	Iterator<Facette3D> Itr=liste.iterator();
	return Itr;
     }		    

}
