package visao;

public interface Puzzle {
    public abstract void addBall(String r, int x, int y);
    public abstract void clearMainPanel();
    public abstract void toggleView(boolean stage);
}
