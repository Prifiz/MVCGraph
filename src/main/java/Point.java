import static java.lang.Float.NaN;

public class Point implements Comparable {
    private float x;
    private float y;

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    protected boolean empty;

//    public Point() {
//        this.x = NaN;
//        this.y = NaN;
//        empty = false;
//    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
        empty = false;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isEmpty() {
        return empty;
    }

    @Override
    public int compareTo(Object o) {
        if(this.getX() < ((Point)o).getX()) {
            return -1;
        } else if(this.getX() > ((Point)o).getX()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (Float.compare(point.x, x) != 0) return false;
        if (Float.compare(point.y, y) != 0) return false;
        return empty == point.empty;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (empty ? 1 : 0);
        return result;
    }
}
