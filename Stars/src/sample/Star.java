package sample;

public class Star {
    private float x;
    private float y;
    private float z;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Star(float x, float y, float z, int type) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
        if (type==4){
            this.type= (int) (Math.random()*300+1);

        }
    }

    public Star(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
