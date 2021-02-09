package mandelbrot;

import java.lang.Math;

public class Complex implements Field<Complex> {
    private double r, i;

    Complex() {
        r=0;i=0;
    }

    Complex(double r){
        this.r=r;
    };
    Complex(double r, double i){
        this.r=r;
        this.i=i;
    }
    Complex(Complex f){
        this.r=f.r;
        this.i=f.i;
    }
    Complex(String stringznazwa){//dostajemy stringa w stylu -123+23i
        String rzeczywista;
        String urojona;
        //Wpierw sprawdzamy czy jest + bo jeśli jest to sprawa jest prosta
        boolean sprawdzacz=false;
        for(int iterator=0;iterator<stringznazwa.length();iterator++)
        {
            if(stringznazwa.charAt(iterator)=='+'){
                sprawdzacz=true;
                break;
            }
        }
        if(sprawdzacz) {
            String[] parts = stringznazwa.split("\\+");//dzielimy stringa po znaku +
            rzeczywista=parts[0];

            urojona=parts[1];



        }
        //gdy nie ma + to sprawa się kompilikuje bo mamy 2 warianty
        else if(stringznazwa.charAt(0)=='-'){//czyli sytuacja -1-2i
            String[] parts = stringznazwa.split("-");//dzielimy stringa po znaku -
            rzeczywista="-"+parts[1];
            urojona="-"+parts[2];
        }
        else{//czyli  1-2i
            String[] parts = stringznazwa.split("-");//dzielimy stringa po znaku -
            rzeczywista=parts[0];
            urojona="-"+parts[1];

        }
        //teraz zawsze przy urojonej zostaje 2i itd więc trzeba pozbyć się 'i'
        String nowaurojona=urojona.substring(0, urojona.length() - 1);//odcinamy koncowke i
        this.r=Double.parseDouble(rzeczywista);
        this.i=Double.parseDouble(nowaurojona);



    }

    @Override
    public boolean equals(Object obj){
        //wpierw sprawdzamy czy obiekty są takie same
        if (this == obj) {
            return true;
        }
        //sprawdzić, czy przekazany jako argument obiekt nie jest wartością null
        if (obj == null) {
            return false;
        }
        //sprawdzić, czy przekazany obiekt jest tego samego typu co obiekt, w na którym operujemy,
        // a jeśli tak to dokonać rzutowania
        if (getClass() != obj.getClass()) {
            return false;
        }
        Complex inny=(Complex) obj;
        if(this.i==inny.i&&this.r==inny.r){
            return true;
        }
        else {
            return false;
        }




    };
    /* Poniższe metody modyfikują aktualny obiekt i zwracają 'this' */
    // Dodawanie

    @Override
    public Complex add(Complex f){
        this.r=this.r+f.r;
        this.i=this.i+f.i;
        return this;
    }
    // Odejmowanie
    @Override
    public Complex sub(Complex f){
        this.r=this.r-f.r;
        this.i=this.i-f.i;
        return this;
    }
    // Mnożenie
    @Override
    public Complex mul(Complex f){

        double temp, temp2,temp3,temp4;
        temp=(this.r*f.r);
        temp2=(this.i*f.i);

        temp3=(this.i*f.r);
        System.out.println("T3"+temp3);
        temp4=(f.i*this.r);
        System.out.println("fi"+this.r);
        this.r=temp-temp2;
        this.i=temp3+temp4;
        System.out.println("T4"+temp4);

        return this;
    }
    // Dzielenie
    @Override
    public Complex div(Complex f){
        double temp1,temp2;
        temp1=((this.r*f.r)+(this.i*f.i))/((f.i*f.i)+(f.r*f.r));
        temp2=((this.i*f.r)-(f.i*this.r))/((f.i*f.i)+(f.r*f.r));
        this.r=temp1;
        this.i=temp2;
        return this;
    }
    // Moduł
    double abs(){
        return Math.sqrt((this.r*this.r)+(this.i*this.i));
    }
    // Kwadrat modułu
    double sqrAbs(){
        return (abs()*abs());
    }
    //Faza
    double phase(){
        return Math.atan2(this.r,this.i);
    }
    // Część rzeczywista
    double re() {
        return this.r;
    }
    // Część urojona
    double im(){
        return this.i;
    }
    static Complex scale(Complex p, double a){

        Complex zwrot=new Complex(p);
        zwrot.i=zwrot.i*a;
        zwrot.r=zwrot.r*a;
        return zwrot;

    }



    /* Metody statyczne dla powyższych operacji */

    static Complex add(Complex a, Complex f){
        double temp1,temp2;

        temp1=a.r+f.r;
        temp2=a.i+f.i;
        Complex nowy=new Complex(temp1,temp1);
        return nowy;

    }
    static Complex sub(Complex a, Complex f){
        double temp1,temp2;

        temp1=a.r-f.r;
        temp2=a.i-f.i;
        Complex nowy=new Complex(temp1,temp1);
        return nowy;
    }
    static Complex mul(Complex a, Complex f){
        double temp1,temp2;
        temp1=(a.r*f.r)-(a.i*f.i);
        temp2=(a.i*f.r)+(f.i*a.r);
        Complex nowy=new Complex(temp1,temp1);
        return nowy;

    }
    static Complex div(Complex a, Complex f){
        double temp1,temp2;
        temp1=((a.r*f.r)+(a.i*f.i))/((f.i*f.i)+(f.r*f.r));
        temp2=((a.i*f.r)-(f.i*a.r))/((f.i*f.i)+(f.r*f.r));
        Complex nowy=new Complex(temp1,temp1);
        return nowy;

    }
    static double abs(Complex a){
        return Math.sqrt((a.r*a.r)+(a.i*a.i));
    }
    static double phase(Complex a){
        return Math.atan2(a.r,a.i);
    }
    static double sqrAbs(Complex a){
        return (abs(a)*abs(a));
    }
    static double re(Complex a){
        return a.r;
    }
    static double im(Complex a){
        return a.i;
    }

    /* Dodatkowe metody */
   /* Zwraca String z zapisaną
        liczbą zespoloną formacie "-1.23+4.56i" */
    @Override
    public String toString(){
        String zwrotny;
        if(this.i<0){
            zwrotny=Double.toString(this.r)+Double.toString(this.i)+"i";
        }
        else{
            zwrotny=Double.toString(this.r)+"+"+Double.toString(this.i)+"i";
        }
        return zwrotny;

    };


    /* Zwraca liczbę zespolona o wartości podanej
     w argumencie w formacie "-1.23+4.56i" */
    static Complex valueOf(String a){
        Complex mytotalynewobj=new Complex(a);
        return mytotalynewobj;

    }


    /* Przypisuje podaną wartość części rzeczywistej */
    void setRe(double r){
        this.r=r;
    }

    /* Przypisuje podaną wartość części urojonej */

    void setIm(double i){
        this.i=i;
    }

    void setVal(Complex f){
        this.i=f.i;
        this.r=f.r;
    }
    void setVal(double r, double i){
        this.i=i;
        this.r=r;
    }
    double getRe(){
        return this.r;
    }
    double getIm(){
        return this.i;
    }
}