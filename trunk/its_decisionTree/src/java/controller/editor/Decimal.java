package controller.editor;

import java.text.DecimalFormat;

public class Decimal {
    public static double format(double value){
        DecimalFormat decFormat = new DecimalFormat("#.####");
        return Double.valueOf(decFormat.format(value));
    }
}
