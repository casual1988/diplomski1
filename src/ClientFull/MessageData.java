/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientFull;

import java.util.Scanner;

/**
 *
 * @author student
 */
class MessageData {

    private String message;

    public synchronized void setMessage(String message) {
        this.message = message;
        notify();
    }

    public synchronized String getMessager() {
        try {
            wait();
        } catch (Exception e) {
        }
        return message;
    }

    public String writeMessage() {
        Scanner ulaz = new Scanner(System.in);
        String message = ulaz.nextLine();
        this.message = message;
        return message;
    }
}
