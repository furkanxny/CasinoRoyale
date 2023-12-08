/*  
 *  © Copyright yannickhuggler.com
 *
 *  [Project Title]     Roulette
 *  [Description]       The king of all casino-games implemented in JavaFX.
 *  [Authors]           Yannick Huggler
 *  [Version]           Version 1.0      
 */

package app.casinoroyale.Model.DataModels.UserModels;

import app.casinoroyale.Controller.FirebaseControllers.Person;

public class Player extends Person {
    public static final Person person = null;
    private static Player instance = null;
   
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
        if(instance == null) {
            instance = new Player();
        }
        return instance;
    }
    
   

    public double getAccountBalance() {
        return accountBalance;
    }
    
    /**
     * Adds the difference of balance to the player's account balance.
     * @param difference 
     */
    public void setAccountBalance(double difference) {
        this.accountBalance += difference;
    }

    /**
     * Checks if the player can withdraw the specified amount.
     * @param amount The amount to check for withdrawal.
     * @return true if withdrawal is possible, false otherwise.
     */
    public boolean canWithdraw(double amount) {
        return amount > 0 && this.accountBalance >= amount;
    }

    /**
     * Withdraws the specified amount from the player's account balance.
     * @param amount The amount to withdraw.
     */
    public void withdraw(double amount) {
        if (canWithdraw(amount)) {
            setAccountBalance(-amount); // Negative value to deduct from balance
        } else {
            // Handle the case where withdrawal is not possible
            // This could be throwing an exception or logging an error
            throw new IllegalArgumentException("Cannot withdraw the specified amount. Insufficient funds or invalid amount.");
        }
    }
    
    
}
