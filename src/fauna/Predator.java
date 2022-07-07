package fauna;

public abstract class Predator extends Animal {
    public Predator(int maxEnergy) {
        super(maxEnergy);
    }

    public abstract boolean hunt(Animal prey);
}
