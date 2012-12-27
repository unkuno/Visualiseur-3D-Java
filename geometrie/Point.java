package geometrie;

/**
  *Classe representant un point dans un espace 3D 
  **/

public class Point {
     private double x;
     private double y;
     private double z;
     
       public Point(){
	  x=0; y=0; z=0;
       }	  
       public Point(double a, double b, double c) {
	  x=a; y=b; z=c;
       }


	public double getXCartesien() {
		return x;
	}
	
	public double getYCartesien() {
		return y;
	}
	
	public double getZCartesien() {
		return z;
	}

	public void set(double a, double b, double c) {
	 x=a; y=b; z=c;
	} 
	

      /**
       *Multiplication matricielle d'un point 3D avec une transformation homogene 
       *@return un point 
       **/
	public Point prodTP( Transformation T, Point P) {

	  Point A = new Point();
	  
	  //On realise le produit matriciel sachant que le point en coordonnée homogene est de la forme (x,y,z,1) 
	  A.x=(T.a11*P.x+ T.a12*P.y + T.a13*P.z + T.a14);
	  A.y=(T.a21*P.x+ T.a22*P.y + T.a23*P.z + T.a24);
	  A.z=(T.a31*P.x+ T.a32*P.y + T.a33*P.z + T.a34);

	  return A;
	}
  

	
	
}



