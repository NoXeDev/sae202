package fr.sae202.Utils;

/**
 * Utils vector of two T value
 */
public class Vector2<T> {
    private T x;
    private T y;

    public Vector2(T ax, T ay) {
        if((ax instanceof Integer || ax instanceof Vector2<?> || ax == null) && (ay instanceof Integer || ay instanceof Vector2<?> || ay == null))
        {
            x = ax;
            y = ay;
        } else {
            throw new IllegalArgumentException("The type of the vector must be Integer or Vector2<Integer>");
        }
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
            @SuppressWarnings("unchecked")      // Checked, and anyway, vector2 can not be other values than Vector2 or Integer 
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


    /**
     * Compare two vector2 and return true if the first is greater than the second
     * @param vector2 the other vector2
     * @return true if the first is greater than the second
     */
    public boolean greaterThan(Vector2<T> vector2)
    {
        if(vector2.x == null || vector2.y == null || this.x == null || this.y == null)
        {
            return false;
        } else {
            if(!(this.x instanceof Integer) || !(this.y instanceof Integer) || !(vector2.x instanceof Integer) || !(vector2.y instanceof Integer))
            {
                return false;
            } else {
                return (Integer)this.x + (Integer)this.y > (Integer)vector2.x + (Integer)vector2.y;
            }
        }
    }
    

    /**
     * Compare two vector2 and return true if the first is less than the second
     * @param vector2 the other vector2
     * @return true if the first is less than the second
     */
    public boolean lessThan(Vector2<T> vector2)
    {
        if(vector2.x == null || vector2.y == null || this.x == null || this.y == null)
        {
            return false;
        } else {
            if(!(this.x instanceof Integer) || !(this.y instanceof Integer) || !(vector2.x instanceof Integer) || !(vector2.y instanceof Integer))
            {
                return false;
            } else {
                return (Integer)this.x + (Integer)this.y < (Integer)vector2.x + (Integer)vector2.y;
            }
        }
    }

    /**
     * Check if vector is empty
     * @return true if vector is empty
     */
    @SuppressWarnings("unchecked")
    public boolean isEmpty()
    {
        if(this.x instanceof Vector2<?> && this.y instanceof Vector2<?>) {
            return ((Vector2<T>)this.x).isEmpty() && ((Vector2<T>)this.y).isEmpty();
        } else if(this.x == null && this.y == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calculate the distance between two vector2
     * @param a     the first vector2
     * @param b    the second vector2
     * @return the distance between the two vector2
     */
    public static int distance(Vector2<Integer> a, Vector2<Integer> b)
    {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }
}
