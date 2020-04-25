
public class PokemonTrainer {
    protected String name;
    protected int level = 1;
    protected int rank = 1;
    protected int check = 0;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        check++;
        return name;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getLevel(){
        return level;
    }

    public void setRank(int cmtDmg) {
        if(cmtDmg >= 10000 && cmtDmg < 20000) {
            rank = 2;
        }
        else if(cmtDmg >= 20000 && cmtDmg < 40000) {
            rank = 3;
        }
        else if(cmtDmg >= 40000 && cmtDmg < 80000) {
            rank = 4;
        }
        else if(cmtDmg >= 80000 && cmtDmg < 100000) {
            rank = 5;
        }
        else if(cmtDmg >= 100000 && cmtDmg < 200000) {
            rank = 6;
        }
        else if(cmtDmg >= 200000) {
            rank = 7;
        }
    }

    public int getRank() {
        return rank;
    }

    public int startCheck(int ch){
        return ch;
    }
}
