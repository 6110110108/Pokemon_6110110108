
public abstract class Pokemon {
    protected String name;
    protected int hp;
    protected int energy;

    public Pokemon(String name) {
        this.name = name;
    }

    public String getName() {
        return name; // or return this.name
    }

    public abstract void attack(Pokemon enemy,int damage);
    public abstract void fruit(Pokemon myPokemon,int powerUp);

}

/*public abstract class Pokemon {
    protected String name;
    protected int hp = 0;

    public Pokemon(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void rename(String name){
        this.name = name;
    }

    //public abstract void attack(Pokemon enemy);
    //public abstract void favoriteFruit(int power);
}*/