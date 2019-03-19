import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Leaderboard {

    TreeMap<Player,Integer> scoreMap;
    Map<Integer,Player> playerMap;
    boolean changed = false;
    private String cachedBoard = "";
    public Leaderboard(){
        playerMap = new HashMap();
        scoreMap = new TreeMap<>(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return Integer.compare(o2.score,o1.score);
            };
        });

    }

    public String getLeaderBoard(){
        if (changed){
            updateCache();
        }
        return cachedBoard;
    }

    private void updateCache(){
        int lastScore = 0;
        int rank = 0;
        StringBuilder sb = new StringBuilder();
        int limit = 10;
        for (Map.Entry<Player,Integer> rankingEntry : scoreMap.entrySet()){
            if (limit--<=0)break;
            Player p = rankingEntry.getKey();
            if (lastScore!=p.score){
                lastScore = p.score;
                rank++;
            }
            sb.append(String.format("Player %d, Rank %d, Score %d\n",p.id,rank,p.score));
        }
        cachedBoard = sb.toString();
        changed = false;
    }

    public int getPlayerScore(int playerId){
        if (!playerMap.containsKey(playerId))
            return -1;

        return playerMap.get(playerId).score;
    }
    public void updateScore(int playerId, int score){
        // player not found case
        if (playerMap.containsKey(playerId)==false)return;
        Player old = playerMap.get(playerId);
        scoreMap.remove(old);

        // update new score then re-insert into treemap
        old.score = score;
        scoreMap.put(old,score);
        playerMap.put(playerId,old);

        changed = true;

    }

    public void addPlayer( Player p ){
        if (p==null)return;
        // check for existing player
        if (playerMap.containsKey(p.id))
            return;
        changed = true;

        playerMap.put(p.id,p);
        scoreMap.put(p,p.score);


    }


}
