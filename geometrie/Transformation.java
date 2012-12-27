package geometrie;
import java.lang.Math;


/**
 *Cette classe nous permet de representer la matrice de transformation (element de la camera) 
 **/

public class Transformation {
/** 
 * Ici seuls 12 valeurs sont utiles etant donnee que la derniere ligne d'une transformation est de la forme (0,0,0,1) 
 **/
    double a11,a12,a13,a14;
    double a21,a22,a23,a24;
    double a31,a32,a33,a34;

  
	/**
	@param tx
	       valeur de la translation selon l'axe x
	@param ty
	       valeur de la translation selon l'axe y
	@param tz
	       valeur de la translation selon l'axe z
	@return 
	       Retourne une transformation de translation
	 **/ 
	public static Transformation translation(double tx, double ty, double tz) {
	  Transformation T= new Transformation();
	  T.a11=1;T.a12=0;T.a13=0;T.a14=tx;
	  T.a21=0;T.a22=1;T.a23=0;T.a24=ty;
	  T.a31=0;T.a32=0;T.a33=1;T.a34=tz;
	  return T;
	}
	
	/** 
	 * Methode renvoyant la transformation identite 
	 **/
	public static Transformation identite() {
	  Transformation T= new Transformation();
	  T.a11=1;T.a12=0;T.a13=0;T.a14=0;
	  T.a21=0;T.a22=1;T.a23=0;T.a24=0;
	  T.a31=0;T.a32=0;T.a33=1;T.a34=0;
	  return T;
	}
	
	/** Methode realisant la composition de deux transformations 
	  * @return la transformation composée
	  **/
	public static Transformation compose(Transformation tr1, Transformation tr2) {
	  Transformation T= new Transformation();
	 
	 //On réalise le produit matriciel entre deux transformations // 
	  T.a11=tr1.a11*tr2.a11+tr1.a12*tr2.a21+tr1.a13*tr2.a31; 
	  T.a12=tr1.a11*tr2.a12+tr1.a12*tr2.a22+tr1.a13*tr2.a32;  
	  T.a13=tr1.a11*tr2.a13+tr1.a12*tr2.a23+tr1.a13*tr2.a33; 
	  T.a14=tr1.a11*tr2.a14+tr1.a12*tr2.a24+tr1.a13*tr2.a34+tr1.a14; 
	  T.a21=tr1.a21*tr2.a11+tr1.a22*tr2.a21+tr1.a23*tr2.a31; 
	  T.a22=tr1.a21*tr2.a12+tr1.a22*tr2.a22+tr1.a23*tr2.a32;  
	  T.a23=tr1.a21*tr2.a13+tr1.a22*tr2.a23+tr1.a23*tr2.a33; 
	  T.a24=tr1.a21*tr2.a14+tr1.a22*tr2.a24+tr1.a23*tr2.a34+tr1.a24;
	  T.a31=tr1.a31*tr2.a11+tr1.a32*tr2.a21+tr1.a33*tr2.a31; 
	  T.a32=tr1.a31*tr2.a12+tr1.a32*tr2.a22+tr1.a33*tr2.a32;  
	  T.a33=tr1.a31*tr2.a13+tr1.a32*tr2.a23+tr1.a33*tr2.a33; 
	  T.a34=tr1.a31*tr2.a14+tr1.a32*tr2.a24+tr1.a33*tr2.a34+tr1.a34;
	  
	  return T;

	}
	
	  /**
	    *@param axe
	       axe de translation 
	    *@param angle
	       angle de la rotation
	    *@return 
	       Retourne une transformation de rotation
	    **/	
	public static Transformation rotation(Point axe, double angle) {
	  double c=Math.cos(angle);
	  double s=Math.sin(angle);
	  double k=1-c;
	  Transformation T= new Transformation();
	  
	  T.a11=k*axe.getXCartesien()*axe.getXCartesien()+c; T.a12=(k*axe.getYCartesien()*axe.getXCartesien())-(s*axe.getZCartesien()); T.a13=(k*axe.getZCartesien()*axe.getXCartesien())+(s*axe.getYCartesien()); T.a14=0;
	  T.a21=(k*axe.getYCartesien()*axe.getXCartesien())+(s*axe.getZCartesien()); T.a22=(k*axe.getYCartesien()*axe.getYCartesien())+c; T.a23=(k*axe.getZCartesien()*axe.getYCartesien())-(s*axe.getXCartesien()); T.a24=0;
	  T.a31=(k*axe.getXCartesien()*axe.getZCartesien())-(s*axe.getYCartesien()); T.a32=(k*axe.getYCartesien()*axe.getZCartesien())+(s*axe.getXCartesien()); T.a33=(k*axe.getZCartesien()*axe.getZCartesien())+c; T.a34=0;

	  return T;
	
	  }
	      

}

