public class PokemonTrainer {
    protected String name;
    protected int level = 1;
    protected int rank = 1;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setLevel(){
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

}
