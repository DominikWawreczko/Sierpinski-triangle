package mandelbrot;


public interface Field<T> {
    T add(T f);          // dodawanie
    T sub(T f);          // odejmowanie
    T mul(T f);          // mnożenie
    T div(T f);          // dzielenie
    String toString(); // zwraca napis reprezentujący liczbę
}
