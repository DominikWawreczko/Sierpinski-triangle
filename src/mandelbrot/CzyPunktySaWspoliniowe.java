package mandelbrot;

public class CzyPunktySaWspoliniowe {
    private Complex p1,p2,p3;

    public CzyPunktySaWspoliniowe(Complex p1, Complex p2, Complex p3){

        this.p1=p1;
        this.p2=p2;
        this.p3=p3;
    }
    public Boolean sprawdzamWspoliniowosc(){
        System.out.println("Poczatek współlinioweeeeeeeeeeee");
        System.out.println(p3.getIm());

        Complex nowy = new Complex(Complex.sub(p3,p1));

        nowy.div( Complex.sub(p2,p1) );
        //można w jednej linijce Complex nowy = new Complex(Complex.sub(p3,p1).div( Complex.sub(p2,p1) )); ale tak widać co się dzieje
        System.out.println("Koniec współlinioweeeeeeeeeeee");


        if(nowy.im()==0) return true;
        else return false;

    }
}
