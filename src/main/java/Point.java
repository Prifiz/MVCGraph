public class Point {
    private float x;
    private float y;
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
}
