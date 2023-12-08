/*  
 *  © Copyright yannickhuggler.com
 *
 *  [Project Title]     Roulette
 *  [Description]       The king of all casino-games implemented in JavaFX.
 *  [Authors]           Yannick Huggler
 *  [Version]           Version 1.0      
 */

package app.casinoroyale.Model.DataModels.UserModels;


import app.casinoroyale.Model.DataModels.GameModels.BlackJackModel.deck.Deck;
import app.casinoroyale.Model.DataModels.GameModels.BlackJackModel.game.Chip;
import app.casinoroyale.Model.DataModels.GameModels.BlackJackModel.role.Cardholder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import app.casinoroyale.Controller.FirebaseControllers.Person;

public class Player extends Cardholder {




    public static final Person person = null;
    private static Player instance = null;
    private double bet;
   public String name;
    private double accountBalance;
    
    
    /**
     * Private constructor due to the use of the singleton-pattern.
     */



    public Player() {
        super();
    }


    /**
     * If there is no player-object, it gets created, otherwise it just returns the aforementioned object.
     * @return Player object
     */
    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public void reset() {
        this.bet = 0;
        this.clearHand();
        // Reset any other game-specific information in the Player class
    }


    public void deal(ArrayList<Chip> chips) {
        bet = 0; // Reset the bet amount
        chips.forEach(chip -> bet += chip.getValue());
        accountBalance -= bet;
    }


    public void doubleDeal() {
        bet *= 2;
        accountBalance -= bet; // Deduct the adjusted bet amount
    }


    public String getName(){return name;}
    public void setName(String name) {
        this.name = name;
    }

    public void hit(Deck deck) {
        takeCard(deck);
    }

    public void setBalance(double balance) {
        this.accountBalance = balance;
    }

    public void clearBet() {
        bet = 0;
    }

    public double getBet() {
        return bet;
    }

    public double getBalance() {
        return accountBalance;
    }

    public String getAccountBalanceFormatted() {
        DecimalFormat formatter = new DecimalFormat("\t$###,###");
        return formatter.format(accountBalance);
    }

    public void setAccountBalanceFromFirebase(Double balance){
        this.accountBalance  = balance;
    }
    public double getAccountBalance() {
        return accountBalance;
    }
    
    /**
     * Adds the difference of balance to the player's account balance.
     * @param difference 
     */
    public void setAccountBalance(double balance) {
        this.accountBalance = balance;
    }


    
    
}
