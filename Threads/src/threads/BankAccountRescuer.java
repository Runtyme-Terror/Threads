/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dgags
 */
public class BankAccountRescuer extends BankAccountUser {
    
    public BankAccountRescuer(String name, BankAccount account, BankAccountUser[] users) {
        super(name, account, null);
        this.users = users;
    }
    
    @Override
    public synchronized void run() {
        while (allFinished() == false) {
            if (allWaiting() == true) {
                BankAccount tempAccount = getAccount();
                tempAccount.deposit(100, this);
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(BankAccountRescuer.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                Thread.sleep((int)(Math.random() * 100));
                } 
                catch (InterruptedException ex) {
                    Logger.getLogger(BankAccountUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           
        }
    }

public boolean allFinished() {
    boolean done = false;
    
    for (BankAccountUser i : users) {
        if (i.getTransactionsRemaining() == 1) {
            done = true;
        }
        else {
            done = false;
        }
    }
    
    return done;
}

public boolean allWaiting() {
    boolean wait = true;
    
    for (BankAccountUser i : users) {
        if (i.getWaiting() == false)
            wait = false;
    }
    
    return wait;
    }





private BankAccountUser[] users;
}