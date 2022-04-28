package control;

import ballsortpuzzle.BallsortPuzzle;
import visao.Puzzle;

public abstract class ControlPuzzle {

    protected BallsortPuzzle puzzle;
    protected Puzzle view;

    public String[] buildPuzzle(String s) {
        view.clearMainPanel();

        String[] result = s.split("\\s");

        final int yInit = 120;

        int y = yInit;
        int x = 0;

        int count = 0;

        for (String r : result) {
            if (!r.equals("brk")) {
                count++;
                view.addBall(r, x, y);
                y = y - 40;
            } else {
                while (count < 4) {
                    view.addBall("FFFFFF", x, y);
                    y = y - 40;
                    count++;
                }
                y = yInit;
                x = x + 40;

                count = 0;
            }
        }

        return result;
    }
    
    public void toggleView(boolean stage) {
        view.toggleView(stage);
    }
}
