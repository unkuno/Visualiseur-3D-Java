package geometrie;

/** 
 *Classe representant un point dans un espace 2D 
 **/

public class Point2D {
     private int x;
     private int y;
     
        public Point2D(int a, int b) {
	  x=a; y=b;
        }
	public Point2D(){
	  x=0; y=0; 
        }	  

	public void set(int a, int b) {
	 x=a; y=b; 
	}


	public int getX2D() {
		return x;
	}
	
	public int getY2D() {
		return y;
	}

	 /**
	   *Multiplication matricielle d'un point 3D avec une matrice 4D homogène, seul les produits en x et y seront a calculer 
	   *@return  point2D 
	   **/
	public Point2D prodMP( Matrice4 M, Point P) {
	  //Coefficient lorsque l'on se place en coordonnée homogène //
	  double s= (M.a41*P.getXCartesien()+ M.a42*P.getYCartesien() + M.a43*P.getZCartesien() + M.a44);
	  
	  int a= (int)((M.a11*P.getXCartesien()+ M.a12*P.getYCartesien() + M.a13*P.getZCartesien() + M.a14)/s);
	  int b= (int)((M.a21*P.getXCartesien()+ M.a22*P.getYCartesien() + M.a23*P.getZCartesien() + M.a24)/s);
	  
	  return new Point2D(a,b);

	}
	
}