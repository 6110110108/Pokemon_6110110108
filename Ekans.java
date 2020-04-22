public class Ekans extends Pokemon {
    public Ekans(String name, int hp, int power, int level) {
        super(name,hp,power, level);
    }
    @Override
    public String skillPokemon() {
        return "Poison";
    }

    @Override
    public int maxAttack() {
        return 28000;
    }

    @Override
    public int maxHp() {
        return 38000;
    }
}
