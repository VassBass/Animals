package fauna.creature;

import exception.NotEnoughEnergyException;
import fauna.Animal;
import fauna.organ.Beak;
import fauna.organ.Glottis;
import fauna.organ.Legs;
import fauna.organ.Wings;

import java.util.concurrent.ThreadLocalRandom;

public class Falcon extends Animal implements Beak, Wings, Legs, Glottis {
    protected Falcon(int maxEnergy) {
        super(maxEnergy);
    }

    public static Falcon create(int maxEnergy){
        if (maxEnergy <= 0){
            return null;
        }else {
            return new Falcon(maxEnergy);
        }
    }

    @Override
    public void eat() {
        increaseEnergy(getMaxEnergy()/4);
        System.out.println(this + " eats.");
    }

    @Override
    public void sleep() {
        increaseEnergy(getMaxEnergy()/4);
        System.out.println(this + " sleep.");
    }

    @Override
    public boolean peck(Animal animal) {
        if (animal.survive(60)){
            System.out.println(this + " tried to peck the " + animal + ", but failed");
            return false;
        }else {
            System.out.println(this + " pecks the " + animal);
            return true;
        }
    }

    @Override
    public void makeSound() {
        System.out.println("Uuuuuuuuuu!");
    }

    @Override
    public void walk(int distance) throws NotEnoughEnergyException {
        if (distance < 100){
            decreaseEnergy(4);
        }else {
            decreaseEnergy(distance/25);
        }
        System.out.println(this + " walked " + distance + " meters.");
    }

    @Override
    public void run(int distance) throws NotEnoughEnergyException {
        if (distance < 100){
            decreaseEnergy(25);
        }else {
            decreaseEnergy(distance/4);
        }
        System.out.println(this + " walked " + distance + " meters.");
    }

    @Override
    public boolean jump(double height) throws NotEnoughEnergyException {
        if (height < 0.3) {
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
        if (distance < 500){
            decreaseEnergy(2);
        }else {
            decreaseEnergy(distance/250);
        }
        System.out.println(this + " flew " + distance + " meters.");
    }

    @Override
    public String toString() {
        return "some falcon";
    }
}
