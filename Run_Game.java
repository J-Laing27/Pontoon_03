//  Jordan Laing - 15009237
//  23/10/2018
//  Pontoon v3.0 - Polymorphism
//  Run_Game.java - contains gameCore method which holds game instance variables and handles user input

import java.util.*;

public class Run_Game {

    //Declare variables
    String choice; //user keyboard input
    int card1; //first card drawn
    int card2; //second card drawn
    int newCard; //value of a new card drawn by the user
    int total; //current total of the users cards
    int house; //total of house cards
    int cardList[] = new int[20]; //list of cards drawn by the user
    int cardNumber; //tracks how many cards the user has drawn
    int houseMin; //minimum score for house
    int round = 1;
    int playerWins = 0;
    int houseWins = 0;
    Scanner kboard = new Scanner(System.in);

    public Run_Game(){

    }

    public void startGame() {

        System.out.println("Round "+round);
        Game round01 = new Game();
        round01.gameCore();
        round++;

        System.out.println("Play another round y/n?");
        choice = kboard.next();
        if(choice.equalsIgnoreCase("y")) {

            System.out.println("Round " + round);
            Game round02 = new Game();
            round02.gameCore();
            round++;

            System.out.println("Play another round y/n?");
            choice = kboard.next();
            if (choice.equalsIgnoreCase("y")) {

                System.out.println("Round " + round);
                Game round03 = new Game();
                round03.gameCore();
                round++;
            }
        }
        System.out.println("Game over! Final Score: ");
        System.out.println("Player: "+playerWins);
        System.out.println("House: "+houseWins);
    }

    protected void gameCore() {

        //display users starting cards
        System.out.println("Card1: " + card1);
        System.out.println("Card2: " + card2);

        //prompt user to select another card
        System.out.println("Draw another card y/n?");
        choice = kboard.next();

        //run while user wants to continue drawing cards
        while (choice.equalsIgnoreCase("y")) {
            newCard = getRandomNumber();
            System.out.println("New Card: " + newCard);
            cardList[cardNumber] = newCard;
            cardNumber++;
            total = calcTotal(total, newCard);

            //check if users total is above 21
            if (busted(total)) {
                System.out.println("Busted! Your total is " + total);
                houseWins = houseWins+1;
                break;
            } else
                System.out.println("Draw another card y/n?");
            choice = kboard.next();
        }

        //run if user chooses not to draw another card
        if (getResult(total, house)) {
            System.out.println("You win! Your total is " + total);
            showCards(cardList);
            System.out.println("House total is " + house);
            playerWins = playerWins+1;
        } else {
            System.out.println("You have lost! Your total is " + total);
            showCards(cardList);
            System.out.println("House total is " + house);
            houseWins = houseWins+1;
        }
    }

    protected static int getRandomNumber() {

        //generates a random number
        return (int) (Math.random() * 10 + 1);
    }

    protected static int calcTotal(int card1, int card2) {

        //adds values passed to the method
        return (card1 + card2);
    }

    private static boolean getResult(int total, int house) {

        //checks if the user has a higher score than the house
        if (total > house)
            return (true);
        else
            return (false);
    }

    private static boolean busted(int total) {

        //checks if the users score is above 21
        if (total > 21)
            return (true);
        else
            return (false);
    }

    protected static int houseTotal(int minimum) {

        //generates a score for the house, score must be higher than the passed through minimum
        int house = 0;
        while (house < minimum)
            house = calcTotal(getRandomNumber(), getRandomNumber());
        return (house);
    }

    private static void showCards(int cardList[]) {

        //prints a list of the users cards
        System.out.print("Cards drawn: ");
        for (int inc = 1; inc < 20; inc++) {
            if (cardList[inc] != 0)
                if (inc == 1)
                    System.out.print(cardList[inc]);
                else
                    System.out.print("," + cardList[inc]);
        }
        System.out.println("");
    }
}

class Game extends Run_Game {

 //   int cardList[] = new int[20]; //list of cards drawn by the user

    public Game(){

        //generate starting conditions
        houseMin = 16;
        house = houseTotal(houseMin);
        card1 = getRandomNumber();
        card2 = getRandomNumber();
        total = calcTotal(card1, card2);
        cardList[0] = house;
        cardList[1] = card1;
        cardList[2] = card2;
        cardNumber = 3;
    }

    @java.lang.Override
    protected void gameCore() {
        super.gameCore();
    }
}
