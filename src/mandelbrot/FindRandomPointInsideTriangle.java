package mandelbrot;
import java.lang.Math;

public class FindRandomPointInsideTriangle {

    public static Complex findUsingInterceptTheorem(Complex pointA, Complex pointE, Complex pointD) {
        double randomNumber;
        do {
            randomNumber = Math.random();
        } while (randomNumber == 0);
        Complex vectorAE = Complex.sub(pointE, pointA);
        Complex vectorAC = Complex.scale(vectorAE, randomNumber);
        Complex pointC = Complex.add(vectorAC, pointA);
        Complex vectorED = Complex.sub(pointD, pointE);
        double lengthCB = (Complex.abs(vectorAC) * Complex.abs(vectorED)) / Complex.abs(vectorAE);
        randomNumber = Math.random();
        Complex vectorCG = Complex.scale(vectorED, randomNumber);

        while (Complex.abs(vectorCG) > lengthCB) {
            randomNumber = Math.random();
            vectorCG = Complex.scale(vectorCG, randomNumber);
        } 
        Complex gamepoint = Complex.add(vectorCG, pointC);


        return gamepoint;


    }

}
