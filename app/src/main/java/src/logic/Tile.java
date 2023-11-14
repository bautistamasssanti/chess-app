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
        if (obj == null || getClass() != obj.getClass())
            return false;
        Tile other = (Tile) obj;
        return x == other.x && y == other.y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}