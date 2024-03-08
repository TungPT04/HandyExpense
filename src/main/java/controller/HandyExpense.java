/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import view.Menu;
import view.Validation;
import model.Expenses;
/**
 *
 * @author tung
 */
public class HandyExpense extends Menu<String> {
    private ArrayList<Expenses> expenses;
    private Validation validation;

    public HandyExpense() {
        title = "Expenses Management System";
        String[] options = {"Add Expense", "Display Expenses", "Delete Expense", "Exit"};
        super.mChon = new ArrayList<>();
        for (String option : options) {
            super.mChon.add(option);
        }
        expenses = new ArrayList<>();
        validation = new Validation();
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                addExpense(expenses, expenses.size() + 1);
                break;
            case 2:
                displayExpense(expenses);
                break;
            case 3:
                deleteExpense(expenses);
                break;
            case 4:
                System.out.println("Exiting program...");
                System.exit(0);
                break;
        }
    }

    private void addExpense(ArrayList<Expenses> le, int id) {
        System.out.print("Enter Date:");
        String date = validation.checkInputDate();
        System.out.print("Enter Amount: ");
        double amount = validation.checkInputDouble();
        System.out.print("Enter Content: ");
        String content = validation.checkInputString();
        le.add(new Expenses(id, date, amount, content));
    }

    private void displayExpense(ArrayList<Expenses> le) {
        if (le.isEmpty()) {
            System.err.println("List Expenses empty");
            return;
        }
        double total = 0;
        System.out.printf("%-7s%-20s%-20s%-20s\n", "ID", "Date", "Amount of money", "Content");
        for (Expenses expense : le) {
            System.out.printf("%-7d%-20s%-20.0f%-20s\n", expense.getId(), expense.getDate(),
                    expense.getAmount(), expense.getContent());
            total += expense.getAmount();
        }
        System.out.printf("Total: %-20.0f\n", total);
    }

    private int checkIdExist(ArrayList<Expenses> le, int id) {
        for (int i = 0; i < le.size(); i++) {
            if (le.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private void deleteExpense(ArrayList<Expenses> le) {
        System.out.print("Enter id: ");
        int id = validation.checkInputInt();
        int checkIdExist = checkIdExist(le, id);
        if (checkIdExist != -1) {
            le.remove(checkIdExist);
            System.out.println("Delete an expense successful");
            for (int i = id - 1; i < le.size(); i++) {
                le.get(i).setId(le.get(i).getId() - 1);
            }
        } else {
            System.err.println("Delete an expense fail");
        }
    }
}