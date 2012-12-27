import geometrie.Rebouchage;
import geometrie.Point;
import scene.Cone;
import java.util.Iterator;
import scene.Facette3D;
import java.util.*;
import java.lang.Math;


/**
 * Programme de Test du rebouchage : Le programme génère un cône dont la base est constitué de n arrêtes ( paramètre determiné par l'utilisateur )
 * Il calcule ensuite la suite de points qui constitue la base du cône, la stocke dans un tableau et applique les 3 méthodes de rebouchages
 */
public class TestRebouchage {

     public static void main(String[] args) {
	  int i;
	  int n;

	  System.out.println("Début du test:");
	  Scanner clavier = new Scanner(System.in);
	  System.out.println("Entrer un entier (nombre de points constituant la base du cone): ");
	  n = clavier.nextInt();
	  //On crée un cone dont la base est constitué de n points et le rayon de la base vaut 100// 
	  Cone C=new Cone(new Point(),n,100);
	  Iterator<Facette3D> Itr=C.getFacettes();
	  
	  //On stocke l'ensemble des points formants le base dans un tableau de points//
	  Point [] polygone = new Point [n];	  
	  for(i=0; i<n;i++){
	       Facette3D F=Itr.next();
	       polygone[i]=F.getS3();
	       
	  }
	  
	  double aire;
	  Rebouchage r=new Rebouchage();
	  
	  //Variable qui va nous permettre calculer le temps d'execution des algorithmes//
	  long startTime;
	  long endTime;
	  
	  System.out.println("debut rebouchage:");

	  System.out.println("Methode avec cache:");     
	  try {
	       startTime = System.currentTimeMillis();
	       //On lance la methode recursive avec cache
	       aire=r.rebouchageTrouRecCache(polygone); 
	       endTime = System.currentTimeMillis();
	       System.out.println("Aire: "+aire);	     
	       System.out.println("temps: "+ (endTime-startTime)+ "ms");
	   } catch( Exception e)
	   {System.out.println("erreur");} 
	   	  
	  
	  System.out.println("Methode iterative:");     
	  try { 
	       startTime = System.currentTimeMillis();
	       //On lance la methode iterative
	       aire=r.rebouchageTrouIter(polygone); 
	       endTime = System.currentTimeMillis();
	       System.out.println("Aire: "+aire);	     
	       System.out.println("temps: "+ (endTime-startTime)+ "ms");
	  } catch( Exception e)
	   {System.out.println("erreur Iter");}

//	  System.out.println("Methode recursive naive:"); //methode recursive naive    
//	  try { 
//	       startTime = System.currentTimeMillis();
//	       //On lance la methode recursive naive
//	       aire=r.rebouchageTrouRecNaif(polygone); 
//	       endTime = System.currentTimeMillis();
//	       System.out.println("Aire: "+aire);	     
//	       System.out.println("temps: "+ (endTime-startTime)+ "ms");
//	  } catch( Exception e)
//	   {System.out.println("erreur");}	  
     } 
}        