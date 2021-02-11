package mandelbrot;

public class divideComplexNumberFactory {

    public static String[] divideComplexNumberString(String numberInString, Complex complex){
        if(complex.isImaginaryPositive(numberInString)) {
            return complex.devideWhenImaginaryisPositive(numberInString);
        }

        //gdy nie ma + to sprawa się kompilikuje bo mamy 2 warianty
        else if(complex.areRealAndImaginaryNegative(numberInString)){
            return complex.devideWhenBothAreNegative(numberInString);
        }
        else{
            return complex.devideWhenOnlyImaginaryisNegative(numberInString);
        }
    }
}
