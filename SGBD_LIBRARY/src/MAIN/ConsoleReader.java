/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MAIN;

/**
 *
 * @author Kevin
 */
import java.util.Scanner;

/**
 *
 * @author mbritto
 */
public class ConsoleReader {

    public static int readInt(String message) {
        System.out.println(message + " : ");
        return ConsoleReader.getInstance().readInt_Internal();
    }

    public static String readString(String message) {
        System.out.println(message + " : ");
        return ConsoleReader.getInstance().readString_Internal();
    }
    private static ConsoleReader _instance;
    private Scanner _scanner;

    private ConsoleReader() {
        _scanner = new Scanner(System.in);
    }

    private void displayError() {
        System.out.println("Valeur incorrecte saisie, veuillez recommencer");
    }

    private static ConsoleReader getInstance() {
        if (_instance == null) {
            _instance = new ConsoleReader();
        }
        return _instance;
    }

    public String readString_Internal() {
        String readLine = null;
        do {
            try {
                readLine = _scanner.nextLine();
            } catch (Exception e) {
                displayError();
            }
        } while (readLine == null);
        return readLine;
    }

    public int readInt_Internal() {
        int readInt = 0;
        boolean readOk = false;
        do {
            try {
                readInt = _scanner.nextInt();
                readOk = true;
            } catch (Exception e) {
                displayError();
            } finally {
                _scanner.nextLine();
            }
        } while (!readOk);
        return readInt;
    }
}

