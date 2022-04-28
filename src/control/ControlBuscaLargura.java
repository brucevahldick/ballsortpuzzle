package control;

import ballsortpuzzle.BallsortPuzzle;
import busca.BuscaLargura;
import busca.Nodo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import visao.ViewBuscaLargura;

public class ControlBuscaLargura extends ControlPuzzle {

    private ArrayList<BallsortPuzzle> caminho;
    private int atual;
    private String ms;

    public ControlBuscaLargura(BallsortPuzzle puzzle) {
        view = new ViewBuscaLargura();
        caminho = new ArrayList<>();
        this.puzzle = puzzle;
        ((ViewBuscaLargura) view).togglePrimeiroEstado(false);

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
        ((ViewBuscaLargura) view).setTextPosicaoLabel("Largura: " + (caminho.size() - atual) + " Tempo: " + ms);
    }

    public ViewBuscaLargura getViewBuscaLargura() {
        return (ViewBuscaLargura) view;
    }

    public void resolvePuzzle() {
        BuscaLargura<BallsortPuzzle> bLargura = new BuscaLargura<>();
        Nodo n = bLargura.busca(puzzle);
        if (n == null) {
            System.out.println("não existe solução");
        } else {
            Nodo w = n;
            while (w != null) {
                caminho.add((BallsortPuzzle) w.getEstado());
                w = w.getPai();
            }
            atual = caminho.size() - 1;
            ms = bLargura.getStatus().getTempoDecorrido() + " ms";
            updateView();
        }
    }

    public void addActionPrimeiroEstado() {
        ((ViewBuscaLargura) view).addActionPrimeiroEstado(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atual = caminho.size() - 1;
                ((ViewBuscaLargura) view).togglePrimeiroEstado(false);
                ((ViewBuscaLargura) view).toggleUltimoEstado(true);
                updateView();
            }
        });
    }

    public void addActionAnterior() {
        ((ViewBuscaLargura) view).addActionAnterior(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atual = atual + 1;
                if (atual == caminho.size() - 1) {
                    ((ViewBuscaLargura) view).togglePrimeiroEstado(false);
                }
                ((ViewBuscaLargura) view).toggleUltimoEstado(true);
                updateView();
            }
        });
    }

    public void addActionProximo() {
        ((ViewBuscaLargura) view).addActionProximo(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atual = atual - 1;
                if (atual == 0) {
                    ((ViewBuscaLargura) view).toggleUltimoEstado(false);
                }
                ((ViewBuscaLargura) view).togglePrimeiroEstado(true);
                updateView();
            }
        });
    }

    public void addActionUltimoEstado() {
        ((ViewBuscaLargura) view).addActionUltimoEstado(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atual = 0;
                ((ViewBuscaLargura) view).toggleUltimoEstado(false);
                ((ViewBuscaLargura) view).togglePrimeiroEstado(true);
                updateView();
            }
        });
    }
}
