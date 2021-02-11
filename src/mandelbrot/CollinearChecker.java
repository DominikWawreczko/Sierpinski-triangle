package mandelbrot;

public class CollinearChecker {
    private final Complex complex, complex1, complex2;

    public CollinearChecker(Complex complex, Complex complex1, Complex complex2){

        this.complex = complex;
        this.complex1 = complex1;
        this.complex2 = complex2;
    }
    public Boolean checkIfTheyAreCollinear(){
        Complex resultComplex = new Complex(Complex.sub(complex2, complex));
        resultComplex.div( Complex.sub(complex1, complex) );
        return resultComplex.im() == 0;

    }
}
