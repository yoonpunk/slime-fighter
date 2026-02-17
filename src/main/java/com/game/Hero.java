package com.game;

import java.util.Random;

public class Hero {

    private String name;
    private int hp;
    private int maxHp;
    private int attack;
    private int potionCount;

    public Hero(String name, int hp, int maxHp, int attack, int potionCount) {
        this.name = name;
        this.hp = hp;
        this.maxHp = maxHp;
        this.attack = attack;
        this.potionCount = potionCount;
    }

    /**
     * 목표 슬라임을 공격한다.
     * 20% 확률로 2배 크리티컬 공격을 수행한다.
     * @param target
     */
    public void attack(Slime target) {
        // 치명타 여부 계산
        Random random = new Random();
        int chance = random.nextInt(10); // 0~9 까지 중 임의의 한 숫자 반환

        boolean isCritical = false;
        if (chance < 2) { // 0~1; 20%
            isCritical = true;
        } else { // 2~9 80%
            isCritical = false;
        }

        // 데미지 계산
        int damage = 0;
        if (isCritical) {
            damage = this.attack * 2;
        } else {
            damage = this.attack;
        }

        // 대상 슬라임의 체력을 깎는다.
        target.takeDamage(damage);
    }

    /**
     * Hero의 체력을 깎는다. 단, 0보다 작아질 순 없다.
     * @param damage
     */
    public void takeDamage(int damage) {
        int currentHp = this.hp - damage;

        if (currentHp < 0) {
            this.hp = 0;
        } else {
            this.hp = currentHp;
        }
    }

    /**
     * 용사의 생존 여부를 반환
     * @return
     */
    public boolean isAlive() {
        if (this.hp > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getPotionCount() {
        return potionCount;
    }
}
