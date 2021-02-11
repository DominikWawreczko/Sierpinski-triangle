package mandelbrot;


public interface Field<T> {
    T add(T f);
    T sub(T f);
    T mul(T f);
    T div(T f);
    String toString();
}
