public class MainApp {

    public static void main(String[] args){
        Leaderboard board = new Leaderboard();
        int BENCH_MARK = 1000000;
        for (int i =0;i<BENCH_MARK;i++){
            board.addPlayer(new Player(i,i));
        }

        System.out.println("Created leaderboard");

        for (int i =0; i<BENCH_MARK;i++) {
            board.updateScore(1, 10);
            board.getLeaderBoard();
        }
    }
}
