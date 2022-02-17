package control;

import ballsortpuzzle.BallsortPuzzle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import visao.MainPage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import visao.Puzzle;
import visao.ViewBuscaLargura;

public class ControlMainPage {

    private MainPage mainPage;
    private BallsortPuzzle puzzle;

    public ControlMainPage() {
        mainPage = new MainPage();
        addActions();
    }

    public void addActions() {
        actionSelectFile();
        actionBuscaLargura();
    }

    public void toggleView(boolean stage) {
        this.mainPage.toggleView(stage);
    }
    
    public void iniciarBuscaLargura(){
        ViewBuscaLargura viewBuscaLargura = new ViewBuscaLargura(mainPage.getPainelPrincipal());
        buildPuzzle(puzzle.returnBalls(), viewBuscaLargura);
        viewBuscaLargura.toggleView(true);
    }

    public void actionSelectFile() {
        mainPage.addActionSelectFile(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = mainPage.fc;
                int returnVal = fc.showOpenDialog(mainPage);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    readFile(file.getAbsolutePath());
                }
            }
        });
    }

    public void actionBuscaLargura() {
        mainPage.addActionBuscaLargura(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarBuscaLargura();
            }
        });
    }

    public void readFile(String filePath) {
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            String data = "";
            while (myReader.hasNextLine()) {
                data += myReader.nextLine() + " ";
            }
            puzzle = new BallsortPuzzle(buildPuzzle(data, mainPage));
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public String[] buildPuzzle(String s, Puzzle frame) {
        frame.clearMainPanel();

        String[] result = s.split("/");

        final int yInit = 120;

        int y = yInit;
        int x = 0;

        for (String r : result) {
            if (!r.equals("brk")) {
                frame.addBall(r, x, y);
                y = y - 40;
            } else if(!r.equals("fim")) {
                y = yInit;
                x = x + 40;
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                frame.addBall("FFFFFF", x, y);
                y = y - 40;
            }
            y = yInit;
            x = x + 40;
        }
        
        return result;

    }
}
