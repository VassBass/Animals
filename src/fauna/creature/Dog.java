package fauna.creature;

import exception.NotEnoughEnergyException;
import fauna.Animal;
import fauna.Pet;
import fauna.organ.Fangs;
import fauna.organ.Glottis;
import fauna.organ.Legs;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Dog extends Pet implements Legs, Glottis, Fangs {
    protected Dog(int maxEnergy, int intelligence, String name) {
        super(maxEnergy, intelligence, name);
    }

    public static Dog create(int maxEnergy, int intelligence, String name){
        if (maxEnergy <= 0 || intelligence < 0
                || name == null || name.isEmpty() || name.isBlank()){
            return null;
        }else {
            return new Dog(maxEnergy, intelligence, name);
        }
    }

    @Override
    public void eat() {
        increaseEnergy(getMaxEnergy()/2);
        System.out.println(this + " eats.");
    }

    @Override
    public void sleep() {
        increaseEnergy(getMaxEnergy()/2);
        System.out.println(this + " sleep.");
    }

    @Override
    public boolean bite(Animal animal) {
        if (animal.survive(50)){
            System.out.println(this + " tried to bite the " + animal + ", but failed");
            return false;
        }else {
            System.out.println(this + " bites the " + animal);
            return true;
        }
    }

    @Override
    public void makeSound() {
        System.out.println("Woof woof!");
    }

    @Override
    public void walk(int distance) throws NotEnoughEnergyException {
        if (distance < 400){
            decreaseEnergy(2);
        }else {
            decreaseEnergy(distance/200);
        }
        System.out.println(this + " walked " + distance + " meters.");
    }

    @Override
    public void run(int distance) throws NotEnoughEnergyException {
        if (distance < 300){
            decreaseEnergy(2);
        }else {
            decreaseEnergy(distance/150);
        }
        System.out.println(this + " ran " + distance + " meters.");
    }

    @Override
    public boolean jump(double height) throws NotEnoughEnergyException {
        int successChance;
        if (height < 1D) {
            decreaseEnergy(1);
            successChance = ThreadLocalRandom.current().nextInt(60, 101);
        }else if (height < 2D) {
            decreaseEnergy(5);
            successChance = ThreadLocalRandom.current().nextInt(20, 101);
        }else {
            System.out.println(this + " is trying to jump to a height of " + height + " meters, but it's too high for him");
            return false;
        }

        if (successChance < 80){
            System.out.println(this + " tried to jump to a height of" + height + " meter, but fell");
            return false;
        }else {
            System.out.println(this + " jumped to a height of" + " meter");
            return true;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMaxEnergy(), getIntelligence());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        if (obj == this) return true;

        Dog dog = (Dog) obj;
        return dog.getName().equals(this.getName());
    }

    @Override
    public String toString() {
        return getName();
    }
}
