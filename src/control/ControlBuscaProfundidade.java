package control;

import ballsortpuzzle.BallsortPuzzle;
import busca.BuscaProfundidade;
import busca.Nodo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import visao.ViewBuscaProfundidade;

public class ControlBuscaProfundidade extends ControlPuzzle {

    private ArrayList<BallsortPuzzle> caminho;
    private int atual;
    private String ms;

    public ControlBuscaProfundidade(BallsortPuzzle puzzle) {
        view = new ViewBuscaProfundidade();
        caminho = new ArrayList<>();
        this.puzzle = puzzle;
        ((ViewBuscaProfundidade) view).togglePrimeiroEstado(false);

        addActions();
    }

    public void addActions() {
        resolvePuzzle();
        addActionPrimeiroEstado();
        addActionAnterior();
        addActionProximo();
        addActionUltimoEstado();
    }

    public void updateView() {
        buildPuzzle(caminho.get(atual).returnBalls());
        ((ViewBuscaProfundidade) view).setTextPosicaoLabel("Profundidade: " + (caminho.size() - atual) + " Tempo: " + ms);
    }

    public void resolvePuzzle() {
        BuscaProfundidade<BallsortPuzzle> bProfundidade = new BuscaProfundidade<>();
        try {
            Nodo n = bProfundidade.busca(puzzle);
            if (n == null) {
                System.out.println("não existe solução");
            } else {
                Nodo w = n;
                while (w != null) {
                    caminho.add((BallsortPuzzle) w.getEstado());
                    w = w.getPai();
                }
                atual = caminho.size() - 1;
                ms = bProfundidade.getStatus().getTempoDecorrido() + " ms";
                updateView();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void addActionPrimeiroEstado() {
        ((ViewBuscaProfundidade) view).addActionPrimeiroEstado(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atual = caminho.size() - 1;
                ((ViewBuscaProfundidade) view).togglePrimeiroEstado(false);
                ((ViewBuscaProfundidade) view).toggleUltimoEstado(true);
                updateView();
            }
        });
    }

    public void addActionAnterior() {
        ((ViewBuscaProfundidade) view).addActionAnterior(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atual = atual + 1;
                if (atual == caminho.size() - 1) {
                    ((ViewBuscaProfundidade) view).togglePrimeiroEstado(false);
                }
                ((ViewBuscaProfundidade) view).toggleUltimoEstado(true);
                updateView();
            }
        });
    }

    public void addActionProximo() {
        ((ViewBuscaProfundidade) view).addActionProximo(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atual = atual - 1;
                if (atual == 0) {
                    ((ViewBuscaProfundidade) view).toggleUltimoEstado(false);
                }
                ((ViewBuscaProfundidade) view).togglePrimeiroEstado(true);
                updateView();
            }
        });
    }

    public void addActionUltimoEstado() {
        ((ViewBuscaProfundidade) view).addActionUltimoEstado(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atual = 0;
                ((ViewBuscaProfundidade) view).toggleUltimoEstado(false);
                ((ViewBuscaProfundidade) view).togglePrimeiroEstado(true);
                updateView();
            }
        });
    }
}
