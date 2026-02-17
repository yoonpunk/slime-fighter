package com.game;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        System.out.println(
            """
                ========================================
                    🏰 슬라임 군단이 마을을 습격했습니다!
                ========================================
            """
        );

        Scanner scanner = new Scanner(System.in);
        System.out.println("[시스템] 용사의 이름을 입력하세요: ");
        String heroName = scanner.nextLine();

        System.out.println("[시스템] " + heroName + " 용사님, 슬라임 3마리와의 전투를 시작합니다!");

        Hero hero = new Hero(heroName, 100, 100, 20, 3);

        Slime greenSlime = new Slime("초록", 50, 50, 1);
        Slime blueSlime = new Slime("파랑", 50, 50, 1);
        Slime redSlime = new Slime("빨강", 50, 50, 1);

        ArrayList<Slime> slimeList = new ArrayList<>();
        slimeList.add(greenSlime);
        slimeList.add(blueSlime);
        slimeList.add(redSlime);

        printStatus(hero, slimeList);
    }

    /**
     * 예시
     * ----------------------------------------
     * [용사] 지오 (HP: 100/100) | 포션: 3개
     * [적들]
     * 1. 초록 슬라임 (HP: 50/50)
     * 2. 파란 슬라임 (HP: 50/50)
     * 3. 빨간 슬라임 (HP: 50/50)
     * ----------------------------------------
     */
    private static void printStatus(Hero hero, ArrayList<Slime> slimeList) {
        System.out.println("----------------------------------------");
        System.out.println("[용사] " + hero.getName() + "(HP: " + hero.getHp() + "/" + hero.getMaxHp() + ") | 포션: " + hero.getPotionCount() + "개");
        System.out.println("[적들]");

        int num = 1;
        for (Slime slime : slimeList) {
            System.out.println(num + ". " + slime.getName() + " 슬라임 (HP: " + slime.getHp() + "/" + slime.getMaxHp() + ")");
            num++;
        }

        System.out.println("----------------------------------------");

//        // 기존 for-loop 사용하기
//        for (int i = 0; i < slimeList.size(); i++) {
//            int number = i + 1;
//            Slime currentSlime = slimeList.get(i);
//            System.out.println(i+1 + ". " + slimeList.get(i).getName() + " 슬라임 (HP: " + slimeList.get(i).getHp() + "/" + slimeList.get(i).getMaxHp() + ")");
//            System.out.println(number + ". " + currentSlime.getName() + " 슬라임 (HP: " + currentSlime.getHp() + "/" + currentSlime.getMaxHp() + ")");
//        }
    }
}