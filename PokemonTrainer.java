
public class PokemonTrainer {
    protected String name;
    protected int level = 1, rank = 1, check = 0, hpDrop = 0, atkDrop = 0;

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

    public void itemDrop(double rand) {
        if(rand >= 30.0 && rand < 55.0) {
            hpDrop++;
        }
        if(rand >= 55.0 && rand < 80.0) {
            atkDrop++;
        }
        if(rand >= 80.0) {
            atkDrop++;
            hpDrop++;
        }
    }

    public void setHpDrop(int hpDrop) {
        this.hpDrop = hpDrop;
    }

    private void setAtkDrop(int atkDrop) {
        this.atkDrop = atkDrop;
    }

    public int getHpDrop() {
        return hpDrop;
    }

    public int getAtkDrop() {
        return atkDrop;
    }
}