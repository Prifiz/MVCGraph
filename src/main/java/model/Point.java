package model;

public class Point implements Comparable<Point> {
    private float x;
    private float y;

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    protected boolean empty;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return Float.compare(point.x, x) == 0 && Float.compare(point.y, y) == 0 && empty == point.empty;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (empty ? 1 : 0);
        return result;
    }

    @Override
    public int compareTo(Point o) {
        return Float.compare(this.getX(), o.getX());
    }
}
