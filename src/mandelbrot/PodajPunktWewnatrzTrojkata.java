package mandelbrot;
import java.lang.Math;

public class PodajPunktWewnatrzTrojkata {
    static double max = 512;
    static double min = 0;
    static Boolean znalezione = false;


    public static Complex znajdz(Complex pktA, Complex pktE, Complex pktD) {
    /* Complex szukane= new   Complex();

       do {
            szukane.setVal(((Math.random()*((max-min)+1))+min),((Math.random()*((max-min)+1))+min));//((Math.random()*((max-min)+1))+min)
            if(sprawdzacz(p1,p2,p3,szukane)) znalezione=true;
            System.out.println("losu losu");
        }while (znalezione);
       znalezione=false;
        return szukane;
   }
   public static Boolean sprawdzacz(Complex p1, Complex p2, Complex p3, Complex szukane){
        int w1,w2;
        w1=((((int)p1.getRe()*((int)p3.getIm()-(int)p1.getIm()))+(((int)szukane.getIm()-(int)p1.getIm())*((int)p3.getRe()-(int)p1.getRe()))-(int)szukane.getRe()*((int)p3.getIm()-(int)p1.getIm()))/((((int)p2.getIm()-(int)p1.getIm())*((int)p3.getRe()-(int)p1.getRe()))-(((int)p2.getRe()-(int)p1.getRe())*((int)p3.getIm()-(int)p1.getIm()))));
        w2=(((int)szukane.getIm()-(int)p1.getIm()-(w1*((int)p2.getIm()-(int)p1.getIm())))/((int)p3.getIm()-(int)p1.getIm()));
        if(w1>=0 && w2>=0 &&(w1+w2)<1){
            znalezione=true;
        }
        return znalezione;
    */
     /*   Complex w1;
        w1 = new Complex(Complex.sub(p1, p2));
        w1.setVal(Math.abs(w1.getRe() / 2), Math.abs(w1.getIm() / 2));
        Complex w2;
        w2 = new Complex(Complex.sub(p1, p3));
        w2.setVal(Math.abs(w2.getRe() / 2), Math.abs(w2.getIm() / 2));
        w2.sub(w1);
        w2.setVal(Math.abs(w2.getRe() / 2), Math.abs(w2.getIm() / 2));
        return w2;*/
        double s;
        do {
            s = Math.random();
        } while (s == 0);
        Complex wektorAE = Complex.sub(pktE, pktA);
        Complex wektorAC = Complex.scale(wektorAE, s);
        Complex pktC = Complex.add(wektorAC, pktA);
        Complex wektorED = Complex.sub(pktD, pktE);
        double dlugoscCB = (Complex.abs(wektorAC) * Complex.abs(wektorED)) / Complex.abs(wektorAE);//Tw Talesa
        Complex wektorCG;
        s = Math.random();

        wektorCG = Complex.scale(wektorED, s);

        while (Complex.abs(wektorCG) > dlugoscCB) {
            s = Math.random();

            wektorCG = Complex.scale(wektorCG, s);
        } 
        Complex gamepoint = Complex.add(wektorCG, pktC);


        return gamepoint;


    }

}
