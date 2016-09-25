/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculator;

import java.util.Map;

/**
 *
 * @author shamsi
 */
public class Calculation {
    private static double plus(String x, String y){
        return Double.valueOf(x) + Double.valueOf(y);
    } 
    
    private static double minus(String x, String y){
        return Double.valueOf(x) - Double.valueOf(y);
    } 
    
    private static double multiply(String x, String y){
        return Double.valueOf(x) * Double.valueOf(y);
    } 
    
    private static double division(String x, String y){
        return Double.valueOf(x) / Double.valueOf(y);
    } 
    
    private static double chooser(Map<String,String> x) throws Exception{
       switch (x.get("sign")){
           case "plus":{
               return plus(x.get("val1"),x.get("val2"));
               }
           case "minus":{
               return minus(x.get("val1"),x.get("val2"));
           }
           case "div":{
               return division(x.get("val1"),x.get("val2"));
           }
           case "mul":  {
               return multiply(x.get("val1"),x.get("val2"));
           }  
           default: throw new Exception("Не правильный знак "+ x.get("sign"));
       }
    }       
     public static String calculator (Map<String,String> x)throws Exception{
        String answer = String.valueOf(chooser(x));
        String s = String.format("%s %s %s = %s", x.get("val1"), x.get("sign") ,x.get("val2"), answer);
        return s;
    }
}
