package com.group.libraryapp.HW;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("숫자를 입력하세요 : ");
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();

        System.out.println("주사위의 눈의 수를 입력하세요 : ");
        int dice_eyes = scanner.nextInt();

        int[] array = new int[dice_eyes];

        for (int i = 1; i <= a; i++) {
            double b = Math.random() * dice_eyes;
            int result = (int) (b + 1);
            array[result-1]++;
        }

        for (int i = 0; i < dice_eyes; i++) {
            int count = array[i];
            System.out.printf("%d은 %d번 나왔습니다.\n", i+1, count);
        }
    }
}