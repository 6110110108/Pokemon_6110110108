public class Squirtle extends Pokemon {
    public Squirtle(String name, int hp, int power, int level) {
        super(name,hp,power, level);
    }
    @Override
    public String skillPokemon() {
        return "Water";
    }

    @Override
    public int maxAttack() {
        return 26000;
    }

    @Override
    public int maxHp() {
        return 36000;
    }
}
