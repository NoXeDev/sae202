package fr.sae202.Utils;

/**
 * Utils vector of two <T> value 
 * @param <T> the type of the value
 * @param x first value
 * @param y second value
 */
public class Vector2<T> {
    private T x;
    private T y;

    public Vector2(T ax, T ay) {
        x = ax;
        y = ay;
    }

    public T getX()
    {
        return x;
    }

    public T getY()
    {
        return y;
    }

    public void setX(T ax)
    {
        x = ax;
    }

    public void setY(T ay)
    {
        y = ay;
    }

    public void set(T ax, T ay)
    {
        x = ax;
        y = ay;
    }

    public String toString()
    {
        return "(" + (this.x != null ? this.x.toString() : "") + "," + (this.y != null ? this.y.toString() : "") + ")";
    }
}
