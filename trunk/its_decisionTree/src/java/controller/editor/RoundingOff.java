/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.editor;

import java.math.BigDecimal;

/**
 *
 * @author Arin
 */
public class RoundingOff{

   public static double roundNumber(double number){
       int scale=4;
       BigDecimal bd = new BigDecimal(number);
       bd=bd.setScale(scale, BigDecimal.ROUND_HALF_DOWN);
       number=bd.doubleValue();
       return number;
   }
}
