package ballsortpuzzle;

import busca.Estado;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import stack.base.ArrayStack;

/*

Cores:

B5E61D - verde fosforescente
FFF200 - amarelo
C3C3C3 - cinza
FF7F27 - laranja
ED1C24 - vermelho
A349A4 - roxo
22B14C - verde claro
00A2E8 - azul claro
FFAEC9 - rosa

B97A57 - marrom
3F48CC - azul escuro
008040 - verde escuro

 */
public class BallsortPuzzle implements Estado {

    private static class Coluna extends ArrayStack {

        public Coluna() {
            super(4);
        }

        public Coluna(String s) {
            super(4);
            if (s != null) {
                String[] result = s.split("\\s");
                for (int i = result.length - 1; i >= 0; i--) {
                    push(result[i]);
                }
            }
        }

        public boolean podeEntrar(String bola) {
            return isEmpty() || (size() != 4 && top().equals(bola));
        }

        public boolean apenasUmaCor() {
            if (isEmpty()) {
                return true;
            } else if (size() == 4) {
                String[] result = toString().split("\\s");
                for (int i = result.length - 1; i > 0; i--) {
                    if (!result[i].equals(result[i - 1]))
                        return false;
                }
                return true;
            } else {
                return false;
            }
        }

        public String copiar() {
            if (isEmpty()) {
                return null;
            } else {
                return toString();
            }
        }

    }

    private ArrayList<Coluna> colunas;

    public BallsortPuzzle(String[] bolas) {
        colunas = new ArrayList<Coluna>();
        int colunasLength = 0;
        for (int i = 0; i < bolas.length; i++) {
            if (bolas[i].equals("brk")) {
                colunasLength++;
            }
        }

        for (int i = 0; i < colunasLength; i++) {
            colunas.add(new Coluna());
        }

        int posit = 0;

        for (String r : bolas) {
            if (!r.equals("brk")) {
                colunas.get(posit).push(r);
            } else {
                posit++;
            }
        }
    }

    public String returnBalls() {
        String bolas = "";

        for (int i = 0; i < colunas.size(); i++) {
            String coluna = colunas.get(i).copiar();
            if(coluna != null)
                bolas += coluna + " ";
            bolas += "brk ";
        }
        
        return bolas;
    }

    public String[] returnColumnsAsVetor(){
        return returnBalls().split("\\s");
    }
    
    @Override
    public String getDescricao() {
        return null;
    }

    @Override
    public boolean ehMeta() {
        for (Coluna c : colunas) {
            if (!c.apenasUmaCor()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int custo() {
        return 1;
    }

    @Override
    public List<BallsortPuzzle> sucessores() {
        List<BallsortPuzzle> suc = new LinkedList<>();

        for (int colunaI = 0; colunaI < colunas.size(); colunaI++) {
            if (colunas.get(colunaI).top() != null) {
                for (int colunaJ = 0; colunaJ < colunas.size(); colunaJ++) {
                    if (colunaI != colunaJ && colunas.get(colunaJ).podeEntrar((String) colunas.get(colunaI).top())) {
                        if (!colunas.get(colunaI).apenasUmaCor()) {
                            BallsortPuzzle novo = new BallsortPuzzle(returnColumnsAsVetor());
                            novo.getColunas().get(colunaJ).push(novo.getColunas().get(colunaI).pop());
                            suc.add(novo);
                        }
                    }
                }
            }
        }

        return suc;
    }

    @Override
    public String toString() {
        return returnBalls();
    }
    
    public boolean equals(Object o){
        try{
            return this.toString().equals(o.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        return toString().hashCode();
    }

    public ArrayList<Coluna> getColunas() {
        return colunas;
    }
}
