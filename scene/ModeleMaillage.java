package scene;

import java.util.Iterator;
import java.util.ArrayList;
import geometrie.Point;
import java.util.Scanner;
import java.io.FileReader;
import java.net.URL;



/**
*Classe permettant de lire un fichier définit par un chemin "path" et de renvoyer un Modele3D
**/

public class ModeleMaillage implements Modele3D {
     /** Parametre indiquant le chemin du fichier**/
     String path;
     double grandissement;
     
     public ModeleMaillage(String p,double g){
	  path=p;
	  grandissement=g;
     }	  
     


/** Attention, cette methode necessite imperativement un fichier au format enoncé dans le sujet 
  * Elle Construit le modèle definit par le fichier
**/
     public Iterator<Facette3D> getFacettes() {

	 try{ 
	  Scanner scanner=new Scanner(new FileReader(path)); 	  


	  String off=scanner.nextLine(); // en tête OFF
	  int Nvert=scanner.nextInt(); // nbr de sommets Nvert
	  int NFace=scanner.nextInt(); // nbr de faces Nface
	  int zero=scanner.nextInt(); // marque le debut des coordonnees des sommets => 0 	  	  
	  int i;

	  //Construction d'une table dont on va rentrer dans l'ordre les différents sommets//
          Point []table= new Point[Nvert];
	  
	  for(i=0;i<Nvert;i++){	               
	       	       //On applique le facteur de grandissement//
	       	       //De même on applique un facteur -1 sur l'axe des y afin d'afficher les objets dans le bon sens (axe y negatif)//
	       table[i]=new Point(scanner.nextDouble()*grandissement,-1*scanner.nextDouble()*grandissement,scanner.nextDouble()*grandissement);
	  }	  
	  	

        
	  ArrayList<Facette3D> liste=new ArrayList<Facette3D>();
	  
	  while(scanner.hasNext()) { 
	  //Etant donne que nous avons que des facettes triangulaires...//	  
	       i=scanner.nextInt(); // i vaut toujours 3//	  
	       liste.add(new Facette3D( table[scanner.nextInt()], table[scanner.nextInt()], table[scanner.nextInt()]));	 //On complete la liste à partir de la table//

	  }

	  //Creation d'un itérateur pointant sur la liste//
	  Iterator<Facette3D> Itr=liste.iterator();
	  //On renvoit l'itérateur
	  return Itr; 

	  } catch (Exception e) {
	   System.out.println("Erreur dans l'ouverture du fichier");
	  }
	  return null;
	       
      }

}