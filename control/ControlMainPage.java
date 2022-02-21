package control;

import ballsortpuzzle.BallsortPuzzle;
import busca.BuscaLargura;
import busca.Nodo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import visao.MainPage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import visao.Puzzle;
import visao.Sobre;
import visao.ViewBuscaLargura;
import visao.ViewBuscaProfundidade;

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
        actionBuscaProfundidade();
        actionSobre();
    }

    public void toggleView(boolean stage) {
        this.mainPage.toggleView(stage);
    }

    public void iniciarBuscaLargura() {
        ViewBuscaLargura viewBuscaLargura = new ViewBuscaLargura();
        buildPuzzle(puzzle.returnBalls(), viewBuscaLargura);
        viewBuscaLargura.toggleView(true);

        BuscaLargura<BallsortPuzzle> bLargura = new BuscaLargura<BallsortPuzzle>();
        Nodo n = bLargura.busca(puzzle);
        if (n == null) {
            System.out.println("não existe solução");
        } else {
            Nodo w = n;
            while (w != null) {
                BallsortPuzzle bsp = (BallsortPuzzle) w.getEstado();
                System.out.println(bsp.toString());
                w = w.getPai();
            }
        }
    }

    public void iniciarBuscaProfundidade() {
        ViewBuscaProfundidade viewBuscaProfundidade = new ViewBuscaProfundidade();
        buildPuzzle(puzzle.returnBalls(), viewBuscaProfundidade);
        viewBuscaProfundidade.toggleView(true);
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
                mainPage.toggleView(false);
                iniciarBuscaLargura();
            }
        });
    }

    public void actionBuscaProfundidade() {
        mainPage.addActionBuscaProfundidade(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarBuscaProfundidade();
                mainPage.toggleView(false);
            }
        });
    }

    public void actionSobre() {
        mainPage.addActionSobre(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sobre jif = new Sobre();
                mainPage.add(jif);
                jif.setVisible(true);
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

        String[] result = s.split("\\s");

        final int yInit = 120;

        int y = yInit;
        int x = 0;

        for (String r : result) {
            if (!r.equals("brk")) {
                frame.addBall(r, x, y);
                y = y - 40;
            } else {
                y = yInit;
                x = x + 40;
            }
        }

        return result;

    }
}
