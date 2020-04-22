public class Charmander extends Pokemon {
    public Charmander(String name, int hp, int power, int level) {
        super(name,hp,power, level);
    }
    @Override
    public String skillPokemon() {
        return "Fire";
    }

    @Override
    public int maxAttack() {
        return 29000;
    }

    @Override
    public int maxHp() {
        return 39000;
    }
}