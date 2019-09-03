package com.sg.luckysevens;

public class App {

    public static void main(String[] args) {
        LuckySevens luckySevens = new LuckySevens();
        int bettingAmount = luckySevens.getBettingAmount();
        luckySevens.playGame(bettingAmount);
    }
}
