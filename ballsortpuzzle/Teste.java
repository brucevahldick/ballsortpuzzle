package ballsortpuzzle;

public class Teste {
    public static void main(String[] args) {
        String s = "B5E61D FFF200 FFF200 FFF200 brk FFF200 FFF200 FFF200 B5E61D brk brk brk";
        String[] vetor = s.split("\\s");
        BallsortPuzzle bsp = new BallsortPuzzle(vetor);
        BallsortPuzzle outro = new BallsortPuzzle(bsp.returnColumnsAsVetor());
        
        System.out.println("bsp: "+bsp);
        System.out.println(bsp.hashCode());
        System.out.println("outro: "+bsp);
        System.out.println(outro.hashCode());
        
        System.out.println(bsp.equals(outro));
    }
}
