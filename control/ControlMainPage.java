package control;

import ballsortpuzzle.BallsortPuzzle;
import busca.BuscaProfundidade;
import busca.Nodo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import visao.MainPage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import visao.Sobre;
import visao.ViewBuscaProfundidade;

public class ControlMainPage extends ControlPuzzle {
    
    public ControlMainPage() {
        view = new MainPage();
        addActions();
    }

    public void addActions() {
        actionSelectFile();
        actionBuscaLargura();
        actionBuscaProfundidade();
        actionSobre();
    }

    public void iniciarBuscaProfundidade() throws Exception {
        ViewBuscaProfundidade viewBuscaProfundidade = new ViewBuscaProfundidade();
        buildPuzzle(puzzle.returnBalls());
        viewBuscaProfundidade.toggleView(true);

        BuscaProfundidade<BallsortPuzzle> bProfundidade = new BuscaProfundidade<>();
        Nodo n = bProfundidade.busca(puzzle);
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

    public void actionSelectFile() {
        ((MainPage) view).addActionSelectFile(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = ((MainPage) view).fc;
                int returnVal = fc.showOpenDialog(((MainPage) view));

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    readFile(file.getAbsolutePath());
                }
                
                ((MainPage) view).enableButtons();
            }
        });
    }

    public void actionBuscaLargura() {
        ((MainPage) view).addActionBuscaLargura(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ControlBuscaLargura controlBuscaLargura = new ControlBuscaLargura(puzzle);
                    controlBuscaLargura.toggleView(true);
                } catch (Exception ex) {
                }
            }
        });
    }

    public void actionBuscaProfundidade() {
        ((MainPage) view).addActionBuscaProfundidade(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ControlBuscaProfundidade controlBuscaProfundidade = new ControlBuscaProfundidade(puzzle);
                    controlBuscaProfundidade.toggleView(true);
                } catch (Exception ex) {
                }
            }
        });
    }

    public void actionSobre() {
        ((MainPage) view).addActionSobre(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sobre sobre = new Sobre();
                sobre.setVisible(true);
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
            puzzle = new BallsortPuzzle(buildPuzzle(data));
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
