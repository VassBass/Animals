package fauna;

import exception.NotEnoughEnergyException;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {
    private int MAX_ENERGY;
    private final int INTELLIGENCE;

    private int energy;
    private final HashMap<String, String> commands = new HashMap<>();

    public Animal(int maxEnergy, int intelligence){
        MAX_ENERGY = maxEnergy;
        this.INTELLIGENCE = intelligence;
    }

    public void increaseEnergy(int e){
        if (MAX_ENERGY < 0){
            System.out.println("This animal is dead.");
            return;
        }

        energy += e;
        if (energy > MAX_ENERGY) energy = MAX_ENERGY;
    }

    public void decreaseEnergy(int e) throws NotEnoughEnergyException {
        if (e > energy){
            throw new NotEnoughEnergyException(this);
        }else {
            energy -= e;
        }
    }

    /**
     * Trying to survive from event with percent risk
     * @param risk percent value risk of dying from an event
     * min_value = 0
     * max_value = 100
     *
     * @return result of survival success
     * if risk < 0 returns true
     * if risk > 100 returns false
     */
    public boolean survive(int risk){
        if (risk < 0) return true;
        if (risk > 100) return false;

        int surviveChance = ThreadLocalRandom.current().nextInt(0, 101);
        return surviveChance >= risk;
    }

    public int getMaxEnergy(){
        return MAX_ENERGY;
    }
    public int getIntelligence(){
        return INTELLIGENCE;
    }

    public void died(){
        MAX_ENERGY = -1;
    }

    public boolean isDead(){
        return MAX_ENERGY < 0;
    }

    public abstract void eat();
    public abstract void sleep();
}
