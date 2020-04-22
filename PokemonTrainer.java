
public class PokemonTrainer {
    protected String name;
    protected int level = 1;
    protected int rank = 1;
    protected int money = 200;
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

    public void setRank(int rank){
        this.rank = rank;
    }

    public int getRank(){
        return rank;
    }

    public void setMoney(){
        this.money = money;
    }

    public int getMoney(){
        return money;
    }

    public int expRank(int dmgToRank) {
        return 0;
    }

    public int startCheck(int ch){
        return ch;
    }
}
