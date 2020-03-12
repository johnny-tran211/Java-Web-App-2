/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.templetes;

import java.io.Serializable;

/**
 *
 * @author Dell
 */
public class CheckValidation implements Serializable{
    public static boolean checkNumber(String numberStr){
        try {
            int number = Integer.parseInt(numberStr);
            if(number < 0){
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean checkDoubleNumber(String numberStr){
        try {
            double number = Double.parseDouble(numberStr);
            if(number < 0){
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
