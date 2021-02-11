package mandelbrot;

import java.lang.Math;

public class Complex implements Field<Complex> {
    private double r, i;

    Complex() {
        r=0;i=0;
    }

    Complex(double r){
        this.r=r;
    }

    Complex(double r, double i){
        this.r=r;
        this.i=i;
    }

    Complex(Complex f){
        this.r=f.r;
        this.i=f.i;
    }

    Complex(String numberInString){
        String realNumber;
        String imaginaryNumber;

        if(isImaginaryPositive(numberInString)) {
            String[] parts = numberInString.split("\\+");//dzielimy stringa po znaku +
            realNumber=parts[0];
            imaginaryNumber=parts[1];
        }

        //gdy nie ma + to sprawa się kompilikuje bo mamy 2 warianty
        else if(areBothNegative(numberInString)){
            String[] parts = numberInString.split("-");
            realNumber="-"+parts[1];
            imaginaryNumber="-"+parts[2];
        }
        else{//czyli  1-2i
            String[] parts = numberInString.split("-");//dzielimy stringa po znaku -
            realNumber=parts[0];
            imaginaryNumber="-"+parts[1];

        }
        //teraz zawsze przy urojonej zostaje 2i itd więc trzeba pozbyć się 'i'
        imaginaryNumber = deleteSymbolI(imaginaryNumber);
        this.r=Double.parseDouble(realNumber);
        this.i=Double.parseDouble(imaginaryNumber);



    }

    private String deleteSymbolI(String imaginaryNumber) {
        return imaginaryNumber.substring(0, imaginaryNumber.length() - 1);
    }

    private boolean isImaginaryPositive(String numberInString ) {
        boolean havePlus = false;
        for(int iterator = 0; iterator< numberInString.length(); iterator++)
        {
            if(numberInString.charAt(iterator)=='+'){
                havePlus =true;
                break;
            }
        }
        return havePlus;
    }

    private boolean areBothNegative(String numberInString) {
        return numberInString.charAt(0)=='-';
    }

    @Override
    public boolean equals(Object obj){
        if (areSame(this,obj)) {
            return true;
        }
        else if (isNull(obj)) {
            return false;
        }
        else if (areAnotherClass(this, obj)) {
            return false;
        }
        Complex secondComplex = (Complex) obj;
        return haveSameParams(this, secondComplex);
    }

    private boolean areSame(Object obj1, Object obj2){
        return obj1==obj2;
    }

    private boolean isNull(Object obj){
        return obj==null;
    }

    private boolean areAnotherClass(Object obj1, Object obj2){
        return obj1.getClass()!=obj2.getClass();
    }

    private boolean haveSameParams(Complex complex1, Complex complex2){
        return complex1.i == complex2.i && complex1.r == complex2.r;
    }

    @Override
    public Complex add(Complex complex){
        this.r = countRealNumberAfterAdding(this, complex);
        this.i = countImaginaryNumberAfterAdding(this, complex);
        return this;
    }

    @Override
    public Complex sub(Complex f){
        this.r=this.r-f.r;
        this.i=this.i-f.i;
        return this;
    }

    @Override
    public Complex mul(Complex complex){
        this.r = countRealNumberAfterMultiplication(this, complex);
        this.i = countImaginaryNumberAfterMultiplication(this, complex);
        return this;
    }

    @Override
    public Complex div(Complex denominator){
        this.r = countRealNumberAfterDivision(this, denominator);
        this.i = countImaginaryNumberAfterDivision(this, denominator);
        return this;
    }

    double abs(){
        return Math.sqrt((this.r*this.r)+(this.i*this.i));
    }

    double sqrAbs(){

        return (abs()*abs());
    }

    double phase(){
        return Math.atan2(this.r,this.i);
    }

    double re() {
        return this.r;
    }

    double im(){
        return this.i;
    }

    static Complex scale(Complex p, double a){
        Complex scaled=new Complex(p);
        scaled.i=scaled.i*a;
        scaled.r=scaled.r*a;
        return scaled;
    }

    static Complex add(Complex complex, Complex complex1){
        double real, imaginary;
        real = countRealNumberAfterAdding(complex, complex1);
        imaginary = countImaginaryNumberAfterAdding(complex, complex1);
        return new Complex(real, imaginary);
    }

    static Complex sub(Complex a, Complex f){
        double temp1,temp2;
        temp1=a.r-f.r;
        temp2=a.i-f.i;
        return new Complex(temp1,temp2);
    }

    static Complex mul(Complex complex, Complex complex1){
        double real, imaginary;
        real = countRealNumberAfterMultiplication(complex, complex1);
        imaginary = countImaginaryNumberAfterMultiplication(complex, complex1);
        return new Complex(real, imaginary);
    }

    static Complex div(Complex complex1, Complex complex2){
        double imaginary ,real;
        imaginary = countImaginaryNumberAfterDivision(complex1, complex2);
        real = countRealNumberAfterDivision(complex1, complex2);
        return new Complex(imaginary, real);
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

    private static double countRealNumberAfterAdding(Complex complex, Complex complex1) {
        return complex.r + complex1.r;
    }

    private static double countImaginaryNumberAfterAdding(Complex complex, Complex complex1) {
        return complex.i + complex1.i;
    }

    private static double countRealNumberAfterMultiplication(Complex complex, Complex complex1) {
        return (complex.r * complex1.r) - (complex.i * complex1.i);
    }

    private static double countImaginaryNumberAfterMultiplication(Complex complex, Complex complex1) {
        return (complex.i * complex1.r) + (complex1.i * complex.r);
    }

    private static double countRealNumberAfterDivision(Complex nominator, Complex denominator) {
        return ((nominator.r * denominator.r) + (nominator.i * denominator.i)) / ((denominator.i * denominator.i) + (denominator.r * denominator.r));
    }

    private static double countImaginaryNumberAfterDivision(Complex nominator, Complex denominator) {
        return ((nominator.i*denominator.r)-(denominator.i*nominator.r))/((denominator.i*denominator.i)+(denominator.r*denominator.r));
    }

    /* Dodatkowe metody */
   /* Zwraca String z zapisaną
        liczbą zespoloną formacie "-1.23+4.56i" */
    @Override
    public String toString(){
        String zwrotny;
        if(this.i<0){
            zwrotny=Double.toString(this.r)+ this.i +"i";
        }
        else{
            zwrotny= this.r +"+"+ this.i +"i";
        }
        return zwrotny;

    }


    /* Return number in format: "-1.23+4.56i" */
    static Complex valueOf(String a){
        return new Complex(a);

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