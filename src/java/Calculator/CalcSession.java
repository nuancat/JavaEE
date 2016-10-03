/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Ilyas
 */
public class CalcSession implements Serializable{
    String sessionID;
    ArrayList<String>alist;

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public ArrayList<String> getAlist() {
        return alist;
    }

    public void setAlist(ArrayList<String> alist) {
        this.alist = alist;
    }

    public CalcSession(String sessionID, ArrayList<String> alist) {
        this.sessionID = sessionID;
        this.alist = alist;
    }
    
    
}
