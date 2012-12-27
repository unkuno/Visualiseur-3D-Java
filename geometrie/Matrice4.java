package geometrie;
import java.lang.Math;


/**
*Cette classe nous permet de representer la matrice de projection (element de la camera) 
*Il s'agit d'une matrice 4x4 
**/
public class Matrice4 {
     //On definit une matrice 4x4 par ses 16 valeurs//
     double a11, a12, a13, a14;
     double a21, a22, a23, a24;
     double a31, a32, a33, a34;
     double a41, a42, a43, a44;
     
     
     /**
      *Methode permettant de placer la camera en projection perspective 
      *@see vision.AfficheurScene
      **/
     public static Matrice4 setPerspe(int dimU, int dimV, int angle){
	    Matrice4 M=new Matrice4();   
	    double f;
	    double anglerad=(angle*Math.PI)/180;
	    if(dimU>dimV) {
	    f= dimV/(2*Math.tan(anglerad)) ;
	    }
	    else {
	    f= dimU/(2*Math.tan(anglerad));
	    }	    
	       
	    M.a11=1; M.a12=0; M.a13=0; M.a14=0;
	    M.a21=0; M.a22=1; M.a23=0; M.a24=0;
	    M.a31=0; M.a32=0; M.a33=1; M.a34=0;
	    M.a41=0; M.a42=0; M.a43=1/f; M.a44=0;

	    return M; 
     }
   
     /**
      *Methode permettant de placer la camera en projection Orthographique 
      *@see vision.AfficheurScene
      **/

     public static Matrice4 setOrtho(){ 
	    Matrice4 M=new Matrice4();   
	    M.a11=1; M.a12=0; M.a13=0; M.a14=0;
	    M.a21=0; M.a22=1; M.a23=0; M.a24=0;
	    M.a31=0; M.a32=0; M.a33=0; M.a34=0;
	    M.a41=0; M.a42=0; M.a43=0; M.a44=1;
	    return M;   
     }

 	    
}   