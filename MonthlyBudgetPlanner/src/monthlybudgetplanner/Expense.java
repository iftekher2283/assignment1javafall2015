/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monthlybudgetplanner;

import java.time.LocalDate;

/**
 *
 * @author iftekher
 */
public class Expense {
    private double amount;
    private String category;
    private String date;
    private String month;

    public Expense() {
    }

    public Expense(double amount, String category, String date, String month) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.month = month;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getMonth() {
        return month;
    }

    @Override
    public String toString() {
        return date + "   " + amount + "                 " + category;
    }
    
}
