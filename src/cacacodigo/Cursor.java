package cacacodigo;

public class Cursor {
    
    private int x;
    private int y;

    public Cursor(int x, int y){
        setX(x);
        setY(y);
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }


    // Compara os Cursor ********************
    @Override
    public boolean equals(Object obj){
        Cursor c = (Cursor) obj;
        if (this.getX() != c.getX()){
            return false;
        }
        if (this.getY() != c.getY()){
            return false;
        }

        return true;
        
    }
    // ***************************************

    // Devolve X e Y em uma String ***********
    public String devolveXeY(){
        return "X: " + getX() + "  Y: " + getY();
    }
}

