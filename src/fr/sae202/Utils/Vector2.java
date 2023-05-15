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

    /**
     * Compare two vector2
     * @param obj the other vector2
     * @return true if the two vector2 are equals
     */
    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Vector2<?>))
        {
            return false;
        }
        else {
            Vector2<T> object = (Vector2<T>)obj;
            if(object.x == this.x && object.y == this.y)
            {
                return true;
            } else {
                if(object.x != null && object.y != null)
                {
                    return object.x.equals(this.x) && object.y.equals(this.y);
                } else {
                    if(object.x == null)
                    {
                        return object.y.equals(this.y) && object.x == this.x;
                    } else {
                        return object.x.equals(this.x) && object.y == this.y;
                    }
                }
            }
        }
    }
}
