package com.company;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinForm extends JFrame{
    private JPanel MainPanel;
    private JButton button1;
    private JTextArea textArea1;
    //private DefaultTableModel model;
    private JTable table1;

    static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; // Alphabet chars
    static String digits = "0123456789"; // Numbers
    static char symbols[] = {'!', '=' , '<', '>' , '+', '-', '*', '/', '(', ')', '{', '}', '\n', '\t'}; // Symbols
    static String keywords[] = {"true", "false", "do", "while"}; // Keywords
    static DefaultTableModel model = new DefaultTableModel();

    /*Constructor*/
    public WinForm(){
        /*Frame init*/
        setContentPane(MainPanel);
        setSize(800,800);
        setLocation(400,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        //model = new DefaultTableModel();
        model.addColumn("TYPE");
        model.addColumn("POS");
        model.addColumn("VALUE");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clearTableModel();
                String code = textArea1.getText();
                make_token(code);
                //JOptionPane.showMessageDialog(MainPanel, expression);
                //String column[] = {"123s", "!!!!", "@@@@@"};
                //model.addRow(column);
                //check_token(expression, model);
            }
        });
        table1.setModel(model);
    }

    public static void make_token(String code){
        for (int i = 0; i < code.length();){
            if (check_digit(code, i)){
                String value_dig = "";
                String starting_pos = String.valueOf(i);
                while (check_digit(code, i)){
                    value_dig = value_dig + code.charAt(i);
                    i++;
                }
                Lex token = new Lex("DIGIT", starting_pos + "-" + String.valueOf(i-1), value_dig);
                String column[] = {token.type, token.pos, token.value};
                model.addRow(column);
            }
            if (check_letter(code, i)){
                String value_let = "";
                String starting_pos = String.valueOf(i);
                while (check_letter(code, i)){
                    value_let += code.charAt(i);
                    i++;
                }
                Lex token = new Lex("VAR", starting_pos + "-" + String.valueOf(i-1), value_let);
                String column[] = {token.type, token.pos, token.value};
                model.addRow(column);
            }
            if (code.charAt(i) == ' '){
                i++;
            }
            }
        }

    /*Make true if symbol is digit*/
    public static boolean check_digit(String code, int i){
        boolean flag_digit = false;
        if (i >= code.length()){
            return false;
        }
        for (int j = 0; j < digits.length(); j++) {
            if (code.charAt(i) == digits.charAt(j)) {
                flag_digit = true;
                return flag_digit;
            }
        }
        return false;
    }
    /*Make true if symbol is letter*/
    public static boolean check_letter(String code, int i){
        if (i >= code.length()){
            return false;
        }
        boolean flag_let = false;
        for (int j = 0; j < letters.length(); j++) {
            if (code.charAt(i) == letters.charAt(j)) {
                flag_let = true;
                return flag_let;
            }
        }
        return false;
    }

    /*Lexeme's param*/
    static class Lex{
        String type, pos, value;
        Lex(String type, String pos, String value){
            this.type = type;
            this.pos = pos;
            this.value = value;
        }

    }

    /*Clear table*/
    public static void clearTableModel(){
        if (model.getRowCount() > 0) {
            for (int i = model.getRowCount() - 1; i > -1; i--) {
                model.removeRow(i);
            }
        }
    }

}

