package novlib2d.utils;

public class Point {
    private float x;
    private float y;

    public Point() {}
    public Point(float x, float y) { this.x = x; this.y = y; }

    public static Point zero() { return new Point(0,0); }

    public float getX() { return x; }
    public float getY() { return y; }

    public void setToZero() { this.x = 0; this.y = 0; }
    public void set(float x, float y) { this.x = x; this.y = y; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
}
