public abstract class Pokemon {
    protected String name;
    protected int hp;

    public Pokemon(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return name;
    }

    public abstract void attack(Pokemon enemy);

}