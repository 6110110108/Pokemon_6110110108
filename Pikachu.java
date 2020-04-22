public class Pikachu extends Pokemon {
    public Pikachu(String name, int hp, int power, int level) {
        super(name, hp, power, level);
    }

    @Override
    public String skillPokemon() {
        return "Electric";
    }

    @Override
    public int maxAttack() {
        return 30000;
    }

    @Override
    public int maxHp() {
        return 40000;
    }
}
