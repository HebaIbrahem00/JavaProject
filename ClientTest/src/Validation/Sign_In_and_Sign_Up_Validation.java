/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validation;

import java.util.regex.Pattern;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author sali
 */
public class Sign_In_and_Sign_Up_Validation {

    public static boolean nameNotEmpty(TextField name, Text namevali, String nameValidation) {
        boolean result = false;
        String c = null;
        if (name.getText().isEmpty()) {
            c = nameValidation;
            namevali.setText(c);
        } else {
            namevali.setText(" ");
            result = true;
        }
        return result;
    }

    public static boolean passwordValidation(TextField password, Text passvali, String passValidation) {
        boolean result = false;
        String c = null;
        if (password.getText().isEmpty()) {
            c = passValidation;
            passvali.setText(c);
        } else if (password.getText().length() < 8) {
            passvali.setText("Passwrd must be a least 8 characters");
        } else {
            passvali.setText(" ");
            result = true;
        }
        return result;
    }

    public static boolean emailValidation(TextField email, Text emailvali, String emailValidation) {
        boolean result = false;
        String c = null;
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email.getText().isEmpty()) {
            c = emailValidation;
            emailvali.setText(c);
        } else if (!pat.matcher(email.getText()).matches()) {
            emailvali.setText("Email format must be xxxxx@x.com");
        } else {
            emailvali.setText(" ");
            result = true;
        }
        return result;
    }

}
