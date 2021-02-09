package mandelbrot;


public interface Field<T> {

    // Poniższe metody modyfikują aktualny obiekt i zwracają "this".
    // Taka konstrukcja pozwala na łańcuchowe łączenie operacji. Np.
    // a.mul(a).add(b) --> a = a^2 + b
    T add(T f);          // dodawanie
    T sub(T f);          // odejmowanie
    T mul(T f);          // mnożenie
    T div(T f);          // dzielenie
    String toString(); // zwraca napis reprezentujący liczbę
}
