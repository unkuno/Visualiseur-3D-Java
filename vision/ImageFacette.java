package vision;

import java.awt.Color;
import geometrie.Point2D;


/** Cette classe nous permet de representer l'image d'une facette sur un plan **/

public class ImageFacette implements Comparable<ImageFacette> {
	
	/** Une facette est constitué de 3 points 2D **/
	Point2D p1,p2,p3;
	/** Permet de caracteriser l'éloignement de la facette (lors de l'affichage) **/
	double z;

	public ImageFacette() {
	  p1=new Point2D();
	  p2=new Point2D();
	  p3=new Point2D();
	  z=0;
	}  
	  
	public ImageFacette( Point2D a, Point2D b, Point2D c, double d) {
	  p1=a; p2=b; p3=c;
	  z=d;
	}

  
	/** Méthode compareTo utile pour "trier" les facettes **/
	public int compareTo(ImageFacette arg0) {
		if (z>arg0.z) { return 1; }
		else if (z==arg0.z) { return 0; }
		else return -1; 
	}

	public int[] CoordX() {
	int[] tab= new int[3];
	tab[0]=p1.getX2D(); tab[1]=p2.getX2D(); tab[2]=p3.getX2D();
	return tab;
	}
	
	public int[] CoordY() {
	int[] tab= new int[3];
	tab[0]=p1.getY2D(); tab[1]=p2.getY2D(); tab[2]=p3.getY2D();
	return tab;
	}  
	
}


