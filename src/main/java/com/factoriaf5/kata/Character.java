package com.factoriaf5.kata;

public class Character {

    private long health;
    private long level;
    private boolean alive;
    private int attackRange;
    private CharacterType type;

    public enum CharacterType {
        MELEE(2),
        RANGED(20);

        private final int range;

        CharacterType(int range) {
            this.range = range;
        }

        public int getRange() {
            return range;
        }
    }

    public Character(CharacterType type) {
        this.health = 1000;
        this.level = 1;
        this.alive = true;
        this.type = type;
        this.attackRange = type.getRange();
    }

    public int getAttackRange() {
        return attackRange;
    }

    public CharacterType getType() {
        return type;
    }

    public long getHealth() {
        return health;
    }

    public long getLevel() {
        return level;
    }

    public boolean isAlive() {
        return alive;
    }

    public void receiveDamage(int damage) {

        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }

        health = Math.max(0, health - damage);
        if (health == 0) {
            alive = false;
        }
    
    }

    public void heal(int amount) {

        if (amount < 0) {
            throw new IllegalArgumentException("Healing amount cannot be negative");
        }
        if (alive) {
            health = Math.min(1000, health + amount);
        
        }
    }
    public void dealDamage(Character target, int damage, int distance) {

        if (this == target) {
            throw new IllegalArgumentException("A 'Character' cannot deal damage to itself.");
        }

        if (distance > this.attackRange) {
            throw new IllegalArgumentException("'Target' is out of range.");
        }

        int levelDifference = (int) (target.getLevel() - this.level);
        int adjustedDamage = damage;

        if (levelDifference >= 5) {
            adjustedDamage = damage / 2; 
        } else if (levelDifference <= -5) {
            adjustedDamage = damage * 3 / 2; 
        }

        target.receiveDamage(adjustedDamage);
    }

    public void healTarget(Character target, int amount) {

        if (this != target) {
            throw new IllegalArgumentException("A 'Character' can only heal itself.");
        }
        this.heal(amount);
    }

    public void levelUp() {
        
        this.level++;
        this.health = Math.min(this.health + 100, 1000); 
    }

}