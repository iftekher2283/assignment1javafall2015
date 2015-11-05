/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monthlybudgetplanner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 *
 * @author iftekher
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TextField incomeAmountField;
    @FXML
    private ComboBox<IncomeSource> incomeSourceBox;
    @FXML
    private DatePicker incomeDateField;
    @FXML
    private TextField expenseAmountField;
    @FXML
    private ComboBox<ExpenseCategory> expenseCategoryBox;
    @FXML
    private DatePicker expenseDateField;
    @FXML
    private ComboBox<StatementType> statementTypeBox;
    @FXML
    private ListView<String> statementListView;
    private ObservableList<String> statements;
    @FXML
    private ComboBox<StatementMonth> statementMonthBox;
    @FXML
    private TextField totalIncomeFieldIncome;
    @FXML
    private TextField totalExpenseFieldIncome;
    @FXML
    private TextField remainingBalanceFieldIncome;
    @FXML
    private TextField monthNameFieldIncome;
    @FXML
    private TextField totalIncomeFieldExpense;
    @FXML
    private TextField totalExpenseFieldExpense;
    @FXML
    private TextField remainingBalanceFieldExpense;
    @FXML
    private TextField monthNameFieldExpense;
    @FXML
    private TextField totalStatementField;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        incomeSourceBox.getItems().addAll(IncomeSource.values());
        expenseCategoryBox.getItems().addAll(ExpenseCategory.values());
        statementTypeBox.getItems().addAll(StatementType.values());
        statementMonthBox.getItems().addAll(StatementMonth.values());
      //  String month = statementMonthBox.getSelectionModel().getSelectedItem() + "";
      //  if(month != null){
        statements = FXCollections.observableArrayList();
        statementListView.setItems(statements);
      //  }
    } 
    
    @FXML
    private void handleAddIncomeAction(ActionEvent event) {
        double amount = Double.parseDouble(incomeAmountField.getText());
        String source = incomeSourceBox.getSelectionModel().getSelectedItem() + "";
        LocalDate date = incomeDateField.getValue();
        String incomeDate = date + "";
        String month = "";
        String tokens[] = incomeDate.split("-");
        int intMonth = Integer.parseInt(tokens[1]);
        switch(intMonth){
            case 1:
                month = "January";
                break;
            case 2:
                month = "February";
                break;
            case 3:
                month = "March";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "June";
                break;
            case 7:
                month = "July";
                break;
            case 8:
                month = "August";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "October";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "December";
                break;
        }
        Income income = new Income(amount, source, incomeDate, month);
        monthNameFieldIncome.setText(income.getMonth());
        try {
            RandomAccessFile incomes = new RandomAccessFile("incomes.txt", "rw");
            incomes.seek(incomes.length());
            incomes.writeBytes(income.getAmount() + "\n");
            incomes.writeBytes(income.getSource() + "\n");
            incomes.writeBytes(income.getDate() + "\n");
            incomes.writeBytes(income.getMonth() + "\n");
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            //  ArrayList<Double> totalIncome = new ArrayList<>();
            RandomAccessFile get_incomes = new RandomAccessFile("incomes.txt", "r");
            double totalIncome = 0.0;
         //   double set_amount = 0.0;
            String lineIncome;
            for(int i = 0; ; i++){
                lineIncome = get_incomes.readLine();
                if("".equals(lineIncome)){
                    break;
                }
                double get_amount = Double.parseDouble(lineIncome);
            //    System.out.printf("%f\n", get_amount);
                lineIncome = get_incomes.readLine();
                String get_source = lineIncome;
                lineIncome = get_incomes.readLine();
                String get_income_date = lineIncome;
            //    String get_tokens[] = get_income_date.split("-");
            //    int get_int_month = Integer.parseInt(get_tokens[1]);
            //    System.out.printf("%d\n", get_int_month);
                lineIncome = get_incomes.readLine();
                String get_month = lineIncome;
                Income get_income = new Income(get_amount, get_source, get_income_date, get_month);
            //    System.out.printf("%s\n", get_income.toString());
                totalIncome = totalIncome + get_income.getAmount();
//                set_amount = totalIncome.get(get_int_month);
//                set_amount = set_amount + get_income.getAmount();
//                totalIncome.set(get_int_month, set_amount);
           //     System.out.printf("%f\n", totalIncome);
             //   totalIncome.add(get_int_month, set_amount);
                totalIncomeFieldIncome.setText(totalIncome +"");
             //   System.out.printf("%f\n", totalIncome);
             //   System.out.printf("%f\n", totalIncome.get(intMonth));   
            } 
           // totalIncomeField.setText(totalIncome.get(intMonth) + "");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            RandomAccessFile get_expenses = new RandomAccessFile("expense.txt", "rw");
         //   ArrayList<Double> totalExpense = new ArrayList<>();
            String lineExpense;
            double totalExpense = 0.0;
            for(int j = 0; ; j++){
                lineExpense = get_expenses.readLine();
                System.out.printf("%s\n", lineExpense);
                if("".equals(lineExpense)){
                    break;
                }
                double get_amount = Double.parseDouble(lineExpense);
                System.out.printf("%f\n", get_amount);
                lineExpense = get_expenses.readLine();
                String get_category = lineExpense;
                lineExpense = get_expenses.readLine();
                String get_expense_date = lineExpense;
             //   String get_tokens[] = get_expense_date.split("-");
             //   int get_int_month = Integer.parseInt(get_tokens[1]);
                lineExpense = get_expenses.readLine();
                String get_month = lineExpense;
                Expense get_expense = new Expense(get_amount, get_category, get_expense_date, get_month);
             //   double set_amount = totalExpense.get(get_int_month) + get_expense.getAmount();
             //   totalExpense.set(get_int_month, set_amount);
             //   totalIncome.add(get_int_month, set_amount); 
                totalExpense = totalExpense + get_expense.getAmount();
                System.out.printf("%f\n", totalExpense);
                totalExpenseFieldIncome.setText(totalExpense + "");
            } 
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        double get_total_income = Double.parseDouble(totalIncomeFieldIncome.getText());
//        double get_total_expense = Double.parseDouble(totalExpenseFieldIncome.getText());
//        double remaining_balance = get_total_income - get_total_expense;
//        remainingBalanceFieldIncome.setText(remaining_balance + "");      
    }  

    @FXML
    private void handleAddExpenseAction(ActionEvent event) {
        double amount = Double.parseDouble(expenseAmountField.getText());
        String category = expenseCategoryBox.getSelectionModel().getSelectedItem() + "";
        LocalDate date = expenseDateField.getValue();
        String expenseDate = date + "";
        String month = "";
        String tokens[] = expenseDate.split("-");
        int intMonth = Integer.parseInt(tokens[1]);
        switch(intMonth){
            case 1:
                month = "January";
                break;
            case 2:
                month = "February";
                break;
            case 3:
                month = "March";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "June";
                break;
            case 7:
                month = "July";
                break;
            case 8:
                month = "August";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "October";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "December";
                break;
        }
        Expense expense = new Expense(amount, category, expenseDate, month);
        monthNameFieldExpense.setText(expense.getMonth());
        try {
            RandomAccessFile expenses = new RandomAccessFile("expense.txt", "rw");
            expenses.seek(expenses.length());
            expenses.writeBytes(expense.getAmount() + "\n");
            expenses.writeBytes(expense.getCategory() + "\n");
            expenses.writeBytes(expense.getDate() + "\n");
            expenses.writeBytes(expense.getMonth() + "\n");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            RandomAccessFile totalExpenses = new RandomAccessFile("expense.txt", "r");
         //   ArrayList<Double> totalExpense = new ArrayList<>();
            String line;
            double totalExpense = 0.0;
            for(int i = 0; ; i++){
                line = totalExpenses.readLine();
                if("".equals(line)){
                    break;
                }
                double get_amount = Double.parseDouble(line);
                line = totalExpenses.readLine();
                String get_category = line;
                line = totalExpenses.readLine();
                String get_expense_date = line;
             //   String get_tokens[] = get_expense_date.split("-");
             //   int get_int_month = Integer.parseInt(get_tokens[1]);
                line = totalExpenses.readLine();
                String get_month = line;
                Expense get_expense = new Expense(get_amount, get_category, get_expense_date, get_month);
             //   double set_amount = totalExpense.get(get_int_month) + get_expense.getAmount();
             //   totalExpense.set(get_int_month, set_amount);
             //   totalIncome.add(get_int_month, set_amount); 
                totalExpense = totalExpense + get_expense.getAmount();
                totalExpenseFieldExpense.setText(totalExpense + "");
            }
          //  totalIncomeFieldExpense.setText(totalExpense.get(intMonth) + "");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            //  ArrayList<Double> totalIncome = new ArrayList<>();
            RandomAccessFile get_incomes = new RandomAccessFile("incomes.txt", "r");
            double totalIncome = 0.0;
         //   double set_amount = 0.0;
            String lineIncome;
            for(int j = 0; ; j++){
                lineIncome = get_incomes.readLine();
                if("".equals(lineIncome)){
                    break;
                }
                double get_amount = Double.parseDouble(lineIncome);
            //    System.out.printf("%f\n", get_amount);
                lineIncome = get_incomes.readLine();
                String get_source = lineIncome;
                lineIncome = get_incomes.readLine();
                String get_income_date = lineIncome;
            //    String get_tokens[] = get_income_date.split("-");
            //    int get_int_month = Integer.parseInt(get_tokens[1]);
            //    System.out.printf("%d\n", get_int_month);
                lineIncome = get_incomes.readLine();
                String get_month = lineIncome;
                Income get_income = new Income(get_amount, get_source, get_income_date, get_month);
            //    System.out.printf("%s\n", get_income.toString());
                totalIncome = totalIncome + get_income.getAmount();
//                set_amount = totalIncome.get(get_int_month);
//                set_amount = set_amount + get_income.getAmount();
//                totalIncome.set(get_int_month, set_amount);
           //     System.out.printf("%f\n", totalIncome);
             //   totalIncome.add(get_int_month, set_amount);
                totalIncomeFieldExpense.setText(totalIncome +"");
             //   System.out.printf("%f\n", totalIncome);
             //   System.out.printf("%f\n", totalIncome.get(intMonth));   
            } 
           // totalIncomeField.setText(totalIncome.get(intMonth) + "");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
//            double get_total_income = Double.parseDouble(totalIncomeFieldExpense.getText());
//            double get_total_expense = Double.parseDouble(totalExpenseFieldExpense.getText());
//            double remaining_balance = get_total_income - get_total_expense;
//            remainingBalanceFieldExpense.setText(remaining_balance + "");
    }
    

    @FXML
    private void handleStatementAction(ActionEvent event) {
        statements.remove(0, statements.size());
        
        String type = statementTypeBox.getSelectionModel().getSelectedItem() + "";
        
        
        if ("Income".equals(type)){
            try {
                double totalIncome = 0.0;
                RandomAccessFile incomes = new RandomAccessFile("incomes.txt", "rw");
                String line;
                for(int i = 0; ; i++){
                    line = incomes.readLine();
                    if("".equals(line)){
                        break;
                    }
                    double get_amount = Double.parseDouble(line);
                    line = incomes.readLine();
                    String get_source = line;
                    line = incomes.readLine();
                    String get_income_date = line;
                    line = incomes.readLine();
                    String get_month = line;
                    System.out.printf("%s\n", get_month);
                    Income get_income = new Income(get_amount, get_source, get_income_date, get_month);
                  //  String month = statementMonthBox.getSelectionModel().getSelectedItem() + "";
                  //  if(month.equalsIgnoreCase(get_income.getMonth())){
                 //   System.out.printf("%s\n", get_income.toString());
                    totalIncome = totalIncome + get_income.getAmount();
                    statements.add(i, get_income.toString()); 
                 //   System.out.printf("%f\n", totalIncome);
                    totalStatementField.setText(totalIncome + "");
                 //   }
                }
                //   System.out.printf("%f\n", totalIncome);
                //    totalStatementField.setText(totalIncome + "");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if("Expense".equals(type)){
            try {
                double totalExpense = 0.0;
                RandomAccessFile expenses = new RandomAccessFile("expense.txt", "rw");
                String line;
                ArrayList<Double> totalIncome = new ArrayList<>();
                for(int i = 0; ; i++){
                    line = expenses.readLine();
                    if(line == ""){
                        break;
                    }
                    double get_amount = Double.parseDouble(line);
                    line = expenses.readLine();
                    String get_category = line;
                    line = expenses.readLine();
                    String get_income_date = line;
                    line = expenses.readLine();
                    String get_month = line;
                    Expense get_expense = new Expense(get_amount, get_category, get_income_date, get_month);
                    totalExpense = totalExpense + get_expense.getAmount();
                    statements.add(i, get_expense.toString());
                    totalStatementField.setText(totalExpense + "");
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }

    @FXML
    private void handleNewIncomeAction(ActionEvent event) {
        incomeAmountField.setText("");
        monthNameFieldIncome.setText("");
        totalIncomeFieldIncome.setText("");
        totalExpenseFieldIncome.setText("");
        remainingBalanceFieldIncome.setText("");
    }

    @FXML
    private void handleNewExpenseAction(ActionEvent event) {
        expenseAmountField.setText("");
        monthNameFieldExpense.setText("");
        totalIncomeFieldExpense.setText("");
        totalExpenseFieldExpense.setText("");
        remainingBalanceFieldExpense.setText("");
    }

    @FXML
    private void handleCalculateIncomeAction(ActionEvent event) {

        try {
              //  double totalIncome = 0.0;
//                RandomAccessFile incomes = new RandomAccessFile("incomes.txt", "rw");
//                String line;
//                for(int i = 0; ; i++){
//                    line = incomes.readLine();
//                    if("".equals(line)){
//                        break;
//                    }
//                    double get_amount = Double.parseDouble(line);
//                    line = incomes.readLine();
//                    String get_source = line;
//                    line = incomes.readLine();
//                    String get_income_date = line;
//                    line = incomes.readLine();
//                    String get_month = line;
//                    System.out.printf("%s\n", get_month);
//                    Income get_income = new Income(get_amount, get_source, get_income_date, get_month);
//                    totalIncome = totalIncome + get_income.getAmount(); 
//                 //   System.out.printf("%f\n", totalIncome);
//                    totalIncomeFieldIncome.setText(totalIncome + "");
//                }
                double totalExpense = 0.0;
                RandomAccessFile expenses = new RandomAccessFile("expense.txt", "rw");
                String line;
                for(int i = 0; ; i++){
                    line = expenses.readLine();
                    if(line == ""){
                        break;
                    }
                    double get_amount = Double.parseDouble(line);
                    line = expenses.readLine();
                    String get_category = line;
                    line = expenses.readLine();
                    String get_income_date = line;
                    line = expenses.readLine();
                    String get_month = line;
                    Expense get_expense = new Expense(get_amount, get_category, get_income_date, get_month);
                    totalExpense = totalExpense + get_expense.getAmount();
                    totalExpenseFieldIncome.setText(totalExpense + "");
                }
            
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
//            double remaining_balance =  0.0;
//            double get_total_income = Double.parseDouble(totalIncomeFieldIncome.getText());
//            double get_total_expense = Double.parseDouble(totalExpenseFieldIncome.getText());
//            remaining_balance = get_total_income - get_total_expense;
//            remainingBalanceFieldIncome.setText(remaining_balance + "");
    }

    @FXML
    private void handleCalculateExpenseAction(ActionEvent event) {
                try {
                double totalIncome = 0.0;
                RandomAccessFile incomes = new RandomAccessFile("incomes.txt", "rw");
                String line;
                for(int i = 0; ; i++){
                    line = incomes.readLine();
                    if("".equals(line)){
                        break;
                    }
                    double get_amount = Double.parseDouble(line);
                    line = incomes.readLine();
                    String get_source = line;
                    line = incomes.readLine();
                    String get_income_date = line;
                    line = incomes.readLine();
                    String get_month = line;
                    System.out.printf("%s\n", get_month);
                    Income get_income = new Income(get_amount, get_source, get_income_date, get_month);
                    totalIncome = totalIncome + get_income.getAmount(); 
                 //   System.out.printf("%f\n", totalIncome);
                    totalIncomeFieldExpense.setText(totalIncome + "");
                }
//                double totalExpense = 0.0;
//                RandomAccessFile expenses = new RandomAccessFile("expense.txt", "rw");
//                for(int i = 0; ; i++){
//                    line = expenses.readLine();
//                    if(line == ""){
//                        break;
//                    }
//                    double get_amount = Double.parseDouble(line);
//                    line = expenses.readLine();
//                    String get_category = line;
//                    line = expenses.readLine();
//                    String get_income_date = line;
//                    line = expenses.readLine();
//                    String get_month = line;
//                    Expense get_expense = new Expense(get_amount, get_category, get_income_date, get_month);
//                    totalExpense = totalExpense + get_expense.getAmount();
//                    totalExpenseFieldExpense.setText(totalExpense + "");
//                }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
//                double remaining_balance = 0.0;
//                double get_total_income = Double.parseDouble(totalIncomeFieldExpense.getText());
//                double get_total_expense = Double.parseDouble(totalExpenseFieldExpense.getText());
//                remaining_balance = get_total_income - get_total_expense;
//                remainingBalanceFieldExpense.setText(remaining_balance + "");
        
    }

    @FXML
    private void handleRemainingBalanceIncomeAction(ActionEvent event) {
        double remaining_balance =  0.0;
        double get_total_income = Double.parseDouble(totalIncomeFieldIncome.getText());
        double get_total_expense = Double.parseDouble(totalExpenseFieldIncome.getText());
        remaining_balance = get_total_income - get_total_expense;
        remainingBalanceFieldIncome.setText(remaining_balance + "");
    }

    @FXML
    private void hadleRemainingBalanceExpenseAction(ActionEvent event) {
        double remaining_balance = 0.0;
        double get_total_income = Double.parseDouble(totalIncomeFieldExpense.getText());
        double get_total_expense = Double.parseDouble(totalExpenseFieldExpense.getText());
        remaining_balance = get_total_income - get_total_expense;
        remainingBalanceFieldExpense.setText(remaining_balance + "");
    }

}
