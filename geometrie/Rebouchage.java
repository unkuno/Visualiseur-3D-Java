package geometrie;
import java.lang.Math;
import java.util.Iterator;
import java.util.ArrayList;
import scene.Facette3D;

/**
*Cette classe implemente les différentes methodes de Rebouchage d'un polygone defini par une suite de points 
**/

public class Rebouchage {
	  static double INF=Double.POSITIVE_INFINITY; //Constante global//		    
	  
          
          //Renvoit l'aire définit par 3 points//
	  private static double aire( Point[] polygone,int i, int k, int j) {
	       double a= Math.sqrt(Math.pow(polygone[i].getXCartesien()-polygone[j].getXCartesien(),2)+Math.pow(polygone[i].getYCartesien()-polygone[j].getYCartesien(),2)+Math.pow(polygone[i].getZCartesien()-polygone[j].getZCartesien(),2));
	       double b= Math.sqrt(Math.pow(polygone[i].getXCartesien()-polygone[k].getXCartesien(),2)+Math.pow(polygone[i].getYCartesien()-polygone[k].getYCartesien(),2)+Math.pow(polygone[i].getZCartesien()-polygone[k].getZCartesien(),2));
	       double c= Math.sqrt(Math.pow(polygone[k].getXCartesien()-polygone[j].getXCartesien(),2)+Math.pow(polygone[k].getYCartesien()-polygone[j].getYCartesien(),2)+Math.pow(polygone[k].getZCartesien()-polygone[j].getZCartesien(),2));

	       double p=(a+b+c)/2;
	       return Math.sqrt((p-a)*(p-b)*(p-c)*p);
	  }


//****************************************************** Methode recursive naive **********************************************************************// 
	  
	  /**
	  *Methode permettant de calculer l'aire minimale d'un polygone par triangularisation de facon recursive naive
	  **/	  
	  public static double rebouchageTrouRecNaif ( Point polygone[] ) throws Exception {
	        //On calcule A0n , n etant l'indice du dernier point//
	        return aireMinRN(polygone,0,(polygone.length-1));
	  }	  
	  
	  /**
	   *Permet de calculer l'aire minimale entre le point i et le point j(facon recursive naive)
	   **/
	  private static double aireMinRN ( Point polygone[], int i, int j) throws Exception {
	       double Aire=INF;
	       int k;
	       double resultat;
	       
	       //cas d'erreur//
	       if(j<i || j>(polygone.length-1)) {
		    throw new Exception("Erreur dans le calcul de l'aire minimale");
	       }
	       //On traite les différents cas possible//
	       else if (i==j || (i+1)==j) { return 0; }
	       else if ((i+2)==j) { return aire(polygone,i,i+1,i+2); }
	       else {
		    for(k=i+1;k<j;k++){
			 resultat=aireMinRN(polygone,i,k)+aireMinRN(polygone,k,j)+aire(polygone,i,k,j);
			 if(Aire>resultat){	//On stocke le minimum, Aire vaut infinity initialement donc le premier test sera toujours vrai//		      	  
			      Aire=resultat;
			 }
		     }
	       return Aire; //On retourne le minimum//
	       }
	  }
/* MC: ras, tb */	 

 

//****************************************************** Methode recursive avec cache ******************************************************************//
	  
	  /**
	   *Methode permettant de calculer l'aire minimale d'un polygone par triangularisation avec cache
	  **/ 
	  public static double rebouchageTrouRecCache ( Point polygone[]) throws Exception {
	       double[][] Cache= new double[polygone.length][ polygone.length];
	       int i,j;
	       
	       //On initialise le cache à infinity, ce cache sera représenté par une matrice 2D ou seul le 2eme demie triangle sera utilisé
	       //l'element aij de la matrice contiendra le résultat Aij ( aire minimale entre la suite de point i..j)
	       for (i=0;i<polygone.length;i++){
		    for(j=i;j<polygone.length;j++){
			 Cache[i][j]=INF;
		    }
	       }
	       //On renvoit A0n, n indice du dernier point du polygone//
	       return aireMinRC(polygone,0,polygone.length-1,Cache);
	  }
	  
	  
	  /**
	  *Methode permettant de savoir si le cache contient une valeur en (i,j)
	  * @return false si le cache ne contient pas de valeur, true sinon
	  **/
	  private static boolean isComputed(int i, int j, double[][] Cache) {
	       if(Cache[i][j]==INF) { return false;}
	       else { return true; }
	  }
	  
	  /**
	  *Permet de mettre le cache en (i,j) à une valeur val
	  **/
	  private static void setCache(int i, int j, double val, double[][] Cache) {
	       Cache[i][j]=val; 
	  }     
	  
	  /**
	  *Renvoit la valeur du cache situé en (i,j)
	  **/
	  private static double getCache(int i, int j, double[][] Cache) {
	       return Cache[i][j];
	  }
	  
	  /**
	  *Renvoit l'aire minimale entre la suite de point i..j (methode avec cache)
	  **/
	  private static double aireMinRC( Point polygone[], int i, int j, double [][] Cache) throws Exception {
	       double Resultat;
	       double Aire=INF;
	       int k;
	       
	       //Cas d'erreur//
	       if(j<i || j>polygone.length-1) {
	    	   throw new Exception("Erreur dans le calcul de l'aire minimale");
	       }
	       
	       //Aij n'a jamais été calculé, on procède au calcul..//
	       else if (!isComputed(i,j,Cache)) {
		    //Différents cas//
		    if( i==j || (i+1)==j) {
			 setCache(i,j,0,Cache);
		    }
		    else if ((i+2)==j) {
			 setCache(i,j,aire(polygone,i,i+1,i+2),Cache);
		    }
		    else {
			 for(k=i+1;k<j;k++){
			      Resultat=aireMinRC(polygone,i,k,Cache)+aireMinRC(polygone,k,j,Cache)+aire(polygone,i,k,j);
			      if(Aire>Resultat) { Aire=Resultat;//On stocke le minmum dans Aire//}
			      }
			 setCache(i,j,Aire,Cache);
		         }	        
	            }
	       }
	       return getCache(i,j,Cache);
	   }
	  
	  
	   
//****************************************************** Methode iterative ***********************************************************************//	   
	   
	   
			      
	/**
	*Methode permettant de calculer l'aire minimale d'un polygone par triangularisation de façon iterative
	**/	 
	  public static double rebouchageTrouIter (Point polygone[]) {
	       
	       int i,j,k;
	       double Aire;
	       double Resultat;
	       int Tmax=polygone.length-1;
	       
	       double [][] Table= new double[polygone.length][polygone.length];
	       
	       
	       //initialisation de la table//
	       ////ON suppose que polygone.lenght >1//
	       for(i=0;i<Tmax;i++) {
		    Table[i][i+1]=0;
	       }
	       
	       //On complete la table en partant du bas a droite en remontant, tout en suivant la diagonale//
	       
	       //i represente le "numero" de la diagonale//
	       //la premiere diagonale, ainsi que la deuxieme valent 0, on commence par la troisieme//
	       for(i=Tmax-2; i>=0; i--) {
		    
		    //j indique la position dans la diagonale//
		    for(j=0;j<i+1;j++){
			 
			 //=> Table[i-j][Tmax-j] = Min (...) //
			 
			 Aire=INF;
			 for(k=0;k< Tmax-1-i; k++) {
			      Resultat= Table[i-j][i+k+1-j] + Table[i+k+1-j][Tmax-j] + aire(polygone,i-j,i+k+1-j,Tmax-j);
			      if(Aire>Resultat) { Aire=Resultat; }
			 }     
			 
			 Table[i-j][Tmax-j]=Aire;
		     }
		}
		
		return Table[0][Tmax];
	  }

			 



//****************************************************** Methode recursive avec cache (modifié) ******************************************************************//
	  
	  
	  /**
	  *Methode permettant d'obtenir la triangularisation effective à partir d'un ensemble de points formant un polygone
	  **/ 
	  public Iterator<Facette3D> rebouchageTrouRecCachemod ( Point polygone[]) throws Exception {

	       double[][] Cache= new double[polygone.length][ polygone.length];
	       int i,j;
	       
	       //On initialise le cache à infinity, ce cache sera représenté par une matrice 2D ou seul le 2eme demie triangle sera utilisé
	       //l'element aij de la matrice contiendra le résultat Aij ( aire minimale entre la suite de point i..j)
	       for (i=0;i<polygone.length;i++){
		    for(j=i;j<polygone.length;j++){
			 Cache[i][j]=INF;
			 Cache[i][j]=INF;
		    }
	       }
	       //On crée une liste liste//
	       ArrayList<Facette3D> liste=new ArrayList<Facette3D>();
	       
	       //L'appel à aireMinRCmod va remplir la liste tout en calculant l'aire minimale A0n//
	       //On crée un itérateur pointant sur cette liste//
	       Iterator<Facette3D> Itr=aireMinRCmod(polygone,0,polygone.length-1,Cache,liste).iterator();
	       
	       //On retourne l'itérateur//
	       return Itr;
	       
	   }
	  	  
	  
	  /**
	  *Methode permettant de calculer Aij, tout en remplissant la liste de facettes3D constituants la triangularisation minimale
	  **/
	  private ArrayList<Facette3D> aireMinRCmod( Point polygone[], int i, int j, double [][] Cache,ArrayList<Facette3D> liste) throws Exception {
	       double Resultat;
	       double Aire=INF;
	       int k;
	       int Sommetmin;
	       
	       //Cas d'erreur//
	       if(j<i || j>polygone.length-1) {
		    throw new Exception("Erreur dans le calcul de l'aire minimale");
	       }
	       
	       //Si i+2=j alors l'aire minimale vaut aire(i,i+1,j) et la facette correspondante est formée des points i, i+1, i+2//
	       else 
		    if ((i+2)==j) {
			liste.add(new Facette3D(polygone[i],polygone[i+1],polygone[j]));
		    }
		    else {
			 // Sinon, on fait appelle à la méthode aireMinRC pour completer le cache et calculer l'aire minimale
			 for(k=i+1;k<j;k++){
			      Resultat=aireMinRC(polygone,i,k,Cache)+aireMinRC(polygone,k,j,Cache)+aire(polygone,i,k,j);
			      if(Aire>Resultat) { 
				   
				   //Si l'aire minimale est obtenue en k, alors on stock ce sommet//
				   Sommetmin=k;
			      }
			
			//On ajoute la facette3D i,k,j puis on fait de nouveau appel à cette méthode entre i et k et k et j
			//Notons que le cache est déja remplis//
			liste.add(new Facette3D(polygone[i],polygone[k],polygone[j]));
		        liste=aireMinRCmod( polygone,i,k,Cache,liste);
		        liste=aireMinRCmod( polygone,k,j,Cache,liste);
		         }	        
	            }
	       
   
	       return liste;
	   }
 }         