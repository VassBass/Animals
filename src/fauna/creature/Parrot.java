package fauna.creature;

import exception.NotEnoughEnergyException;
import fauna.Animal;
import fauna.Pet;
import fauna.organ.Beak;
import fauna.organ.Glottis;
import fauna.organ.Legs;
import fauna.organ.Wings;

import java.util.concurrent.ThreadLocalRandom;

public class Parrot extends Pet implements Beak, Wings, Legs, Glottis {
    protected Parrot(int maxEnergy, int intelligence, String name) {
        super(maxEnergy, intelligence, name);
    }

    public static Parrot create(int maxEnergy, int intelligence, String name){
        if (maxEnergy <= 0 || intelligence < 0
                || name == null || name.isEmpty() || name.isBlank()){
            return null;
        }else {
            return new Parrot(maxEnergy, intelligence, name);
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
    public boolean peck(Animal animal) {
        if (animal.survive(50)){
            System.out.println(this + " tried to peck the " + animal + ", but failed");
            return false;
        }else {
            System.out.println(this + " pecks the " + animal);
            return true;
        }
    }

    @Override
    public void makeSound() {
        System.out.println("Arrrrrrrrr!");
    }

    @Override
    public void walk(int distance) throws NotEnoughEnergyException {
        if (distance < 100){
            decreaseEnergy(10);
        }else {
            decreaseEnergy(distance/10);
        }
        System.out.println(this + " walked " + distance + " meters.");
    }

    @Override
    public void run(int distance) throws NotEnoughEnergyException {
        if (distance < 100){
            decreaseEnergy(50);
        }else {
            decreaseEnergy(distance/2);
        }
        System.out.println(this + " walked " + distance + " meters.");
    }

    @Override
    public boolean jump(double height) throws NotEnoughEnergyException {
        if (height < 0.2) {
            decreaseEnergy(1);
            if (ThreadLocalRandom.current().nextInt(60, 101) < 80){
                System.out.println(this + " tried to jump to a height of" + height + " meter, but fell");
                return false;
            }else {
                System.out.println(this + " jumped to a height of" + " meter");
                return true;
            }
        }else {
            System.out.println(this + " thought to jump" + height + " meters high, but remembered that he can fly.");
            return true;
        }
    }

    @Override
    public void fly(int distance) throws NotEnoughEnergyException {
        if (distance < 200){
            decreaseEnergy(2);
        }else {
            decreaseEnergy(distance/100);
        }
        System.out.println(this + " flew " + distance + " meters.");
    }

    @Override
    public String toString() {
        return getName();
    }
}
