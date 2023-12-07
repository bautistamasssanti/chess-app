package src.logic;

public class Tile {
    private final int x;
    private final int y;
    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Tile other = (Tile) obj;
        if (x == other.x) {
            if (y == other.y) {
                return true;
            }
        }
        return false;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}