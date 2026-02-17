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

        // 용사가 죽거나 슬라임이 모두 죽었거나
        while(true) {
            printStatus(hero, slimeList);
            battle(hero, slimeList, scanner);

            if (!hero.isAlive()) {
                System.out.println("[결과] 용사가 죽었습니다. 마을이 침략당했습니다.");
                break;
            } else if (!isAnySlimeAlive(slimeList)) {
                System.out.println("[결과] 모든 슬라임을 물리쳤습니다! 마을에 평화가 찾아왔습니다.");
                break;
            }
        }
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

    public static void battle(Hero hero, ArrayList<Slime> slimes, Scanner scanner) {
        System.out.println("[행동 선택] 1.공격  2.회복");
        System.out.println("[입력]: ");
        int action = scanner.nextInt();

        // 용사 턴
        if (action == 1) {
            // 공격 대상 입력
            System.out.println("[시스템] 몇 번 슬라임을 공격하시겠습니까?");
            System.out.printf("[입력]: ");
            int targetSlimeIndex = scanner.nextInt() - 1;

            // 공격 대상 슬라임 가져오기
            Slime targetSlime = slimes.get(targetSlimeIndex);

            // 슬라임을 공격
            int damage = hero.attack(targetSlime);
            System.out.println("[전투] " + hero.getName() + "의 공격! '" + targetSlime.getName() + " 슬라임'에게 " + damage + "의 데미지를 입혔습니다.");

            // 슬라임 생존여부 확인
            if (!targetSlime.isAlive()) {
                System.out.println("[안내] " + targetSlime.getName()+ " 슬라임을 처치했습니다!");
            }
        } else if (action == 2) {
            hero.drinkPotion();
        } else {
            throw new IllegalArgumentException("잘못된 숫자를 입력했습니다.");
        }

        // 슬라임턴
        System.out.println("[반격] 살아남은 슬라임들의 공격!");

        for (Slime slime : slimes) {
            if (!slime.isAlive()) {
                continue;
            }

            int damage = slime.attack(hero);
            System.out.println(slime.getName() + " 슬라임이 " + damage + "의 데미지를 입혔습니다.");
        }
    }

    public static boolean isAnySlimeAlive(ArrayList<Slime> slimes) {
        boolean isAnySlimeAlive = false;

        for (Slime slime : slimes) {
            if (slime.isAlive()) {
                isAnySlimeAlive = true;
            }

            if (isAnySlimeAlive) {
                break;
            }
        }

        return isAnySlimeAlive;
    }
}