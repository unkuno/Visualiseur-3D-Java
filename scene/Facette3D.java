package scene;
import geometrie.Point;


/**Classe permettant de representer une facette dans un environnement 3D **/

public class Facette3D {

/**On facette3D est définit dans l'espace 3D par 3 points**/
     private Point s1;
     private Point s2;
     private Point s3;
	  
     public Facette3D() {
	  s1= new Point();	       
	  s2= new Point();
	  s3= new Point();
     }
     
     public Facette3D(Point p1, Point p2, Point p3) {
	  s1=p1;
	  s2=p2;
	  s3=p3;
     }     
	  
     public Point getS1() {
	  return s1;
     } 
     public Point getS2() {
	  return s2;
     } 
     public Point getS3() {
	  return s3;
     }



 
}