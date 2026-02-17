package com.game;

import java.util.Random;

public class Slime {

    private String name;
    private int hp;
    private int maxHp;
    private int attack;

    public Slime(String name, int hp, int maxHp, int attack) {
        this.name = name;
        this.hp = hp;
        this.maxHp = maxHp;
        this.attack = attack;
    }

    /**
     * 슬라임이 대상 용사를 공격한다.
     * @param target
     */
    public int attack(Hero target) {

        // 추가 데미지 계산
        Random random = new Random();
        int additionalAttack = random.nextInt(6); // 0~5 추가공격력
        int damage = this.attack + additionalAttack;

        target.takeDamage(damage);
        return damage;
    }

    /**
     * 슬라임이 피해를 입는다.
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
     * 슬라임의 생존 여부를 반환
     * @return
     */
    public boolean isAlive() {
        return this.hp > 0;
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
}
