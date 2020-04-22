public class Diglett extends Pokemon {
    public Diglett(String name, int hp, int power, int level) {
        super(name,hp,power, level);
    }
    @Override
    public String skillPokemon() {
        return "Ground";
    }

    @Override
    public int maxAttack() {
        return 25000;
    }

    @Override
    public int maxHp() {
        return 35000;
    }
}
