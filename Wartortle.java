public class Wartortle extends Pokemon {
    public Wartortle(String name, int hp, int power, int level) {
        super(name,hp,power, level);
    }
    @Override
    public String skillPokemon() {
        return "Water";
    }

    @Override
    public int maxAttack() {
        return 27000;
    }

    @Override
    public int maxHp() {
        return 37000;
    }
}
