public class Player {

    int score;
    int id;

    public Player(int id, int score){
        this.id = id;
        this.score = score;
    }

    public Player(){}

    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof Player)!= true)
            return false;

        Player p = (Player)obj;
        return p.id == this.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
