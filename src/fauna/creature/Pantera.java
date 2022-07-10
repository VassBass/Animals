package fauna.creature;

import exception.NotEnoughEnergyException;
import fauna.Animal;
import fauna.organ.Claws;
import fauna.organ.Fangs;
import fauna.organ.Glottis;
import fauna.organ.Legs;

import java.util.concurrent.ThreadLocalRandom;

public class Pantera extends Animal implements Legs, Claws, Fangs, Glottis {
    protected Pantera(int maxEnergy) {
        super(maxEnergy);
    }

    public static Pantera create(int maxEnergy){
        if (maxEnergy <= 0){
            return null;
        }else {
            return new Pantera(maxEnergy);
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
    public boolean scratch(Animal animal) {
        if (animal.survive(90)){
            System.out.println(this + " tried to scratch the " + animal + ", but failed");
            return false;
        }else {
            System.out.println(this + " scratch the " + animal);
            return true;
        }
    }

    @Override
    public boolean bite(Animal animal) {
        if (animal.survive(65)){
            System.out.println(this + " tried to bite the " + animal + ", but failed");
            return false;
        }else {
            System.out.println(this + " bites the " + animal);
            return true;
        }
    }

    @Override
    public void makeSound() {
        System.out.println("Rrrrrrar!");
    }

    @Override
    public boolean climb() throws NotEnoughEnergyException {
        decreaseEnergy(1);
        System.out.println(this + " climbed a tree and lay down.");
        return true;
    }

    @Override
    public void walk(int distance) throws NotEnoughEnergyException {
        if (distance < 200){
            decreaseEnergy(2);
        }else {
            decreaseEnergy(distance/100);
        }
        System.out.println(this + " walked " + distance + " meters.");
    }

    @Override
    public void run(int distance) throws NotEnoughEnergyException {
        if (distance < 100){
            decreaseEnergy(2);
        }else {
            decreaseEnergy(distance/50);
        }
        System.out.println(this + " ran " + distance + " meters.");
    }

    @Override
    public boolean jump(double height) throws NotEnoughEnergyException {
        int successChance;
        if (height < 1D) {
            decreaseEnergy(1);
            successChance = 100;
        }else if (height < 2D) {
            decreaseEnergy(2);
            successChance = ThreadLocalRandom.current().nextInt(60, 101);
        }else if (height < 4) {
            decreaseEnergy(3);
            successChance = ThreadLocalRandom.current().nextInt(50, 91);
        }else {
            System.out.println(this + " thought to jump" + height + " meters high, but remembered that he don't need it.");
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
}
