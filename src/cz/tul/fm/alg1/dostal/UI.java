
package cz.tul.fm.alg1.dostal;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 27.&nbsp;Program searches for the maximum scalar product from given set of vectors.
 * @author jan.dostal
 * @version 0.8 12/10/22
 */
public final class UI 
{
    private static final Scanner SCANNER = new Scanner (System.in); //konstanta
    
    private static int userChoice;
    private static boolean menuEnd;
    
    private UI ()
    {
    }
    
    public static void main (String[] args) 
    {
        menuEnd = false;
        
        do 
        {
            displayMenu();
            
            try 
            {
                userChoice =  loadUserChoice();
                
                switch(userChoice)
                {
                    case 1:
                        ChristmasApplesDrawing.startProgram();
                        break;
                    case 2: 
                        MaxScalarProductCalculation.startProgram();
                        break;
                    case 0: 
                        menuEnd = true; 
                        break;
                    default:
                        displayErrorMessage("Nevalidni cislo volby z menu");
                }
            }
            catch (InputMismatchException ex) 
            {
                displayErrorMessage("Nevalidni cislo volby z menu");
            }
        }
        while(!menuEnd);
    }

    private static void displayMenu () 
    {
        System.out.println();
        System.out.println("Vitej ve vyberu spustitelnych programu");
        System.out.println("1. Vykreslovani vanocnich jablek");
        System.out.println("2. Vypocet maximalniho skalarniho soucinu ze sady "
                           + "vektoru");
        System.out.println("0. Konec");
    }
    
    private static int loadUserChoice () 
    {
        System.out.println();
        System.out.print("Zadej cislo volby z menu: ");
        return SCANNER.nextInt();
    }
    
    public static void displayErrorMessage (String message) 
    {
        System.out.println();
        System.out.println(message);
        SCANNER.nextLine(); //zajisteni nenacteni predchoziho vstupniho tokenu
    }
    
    //pouzije se v programu pro hledani maximalniho skalarniho soucinu
    public static int loadVectorsCount () 
    {
        System.out.println();
        System.out.println("Pocet vektoru");
        return SCANNER.nextInt();
    }
    
    //pouzije se v programu pro hledani maximalniho skalarniho soucinu
    public static int loadVectorElementsCount () 
    {
        System.out.println("Delka vektoru");
        return SCANNER.nextInt();
    }
    
    //pouzije se v programu pro hledani maximalniho skalarniho soucinu
    public static double[][] loadVectorsSet (int vectorsCount, int vectorElementsCount) 
    {
        System.out.println("Zadej vektory");
        double[][] vectorsSet = new double[vectorsCount][vectorElementsCount];
        
        for (double[] vector : vectorsSet) 
        {
            for (int elementIndex = 0; elementIndex < vector.length; elementIndex++) 
            {
                vector[elementIndex] = SCANNER.nextDouble();
            }
        }
        
        return vectorsSet;
    }
    
    //pouzije se v programu pro hledani maximalniho skalarniho soucinu
    public static void displayTwoFoundVectors (double[][] foundVectors) 
    {
        System.out.println("Vektory s maximalnim skalarnim soucinem");
        
        for (double[] foundVector : foundVectors) 
        {
            System.out.print("(");
            
            for (int elementIndex = 0; elementIndex < foundVector.length - 1; elementIndex++) 
            {
                System.out.printf("%.2e  ", foundVector[elementIndex]);
            }
            
            System.out.printf("%.2e)%n", foundVector[foundVector.length - 1]);
        }        
    }
    
    //pouzije se v programu pro hledani maximalniho skalarniho soucinu
    public static void displayFoundMaxScalarProduct (double maxScalarProduct) 
    {
        System.out.printf("Skalarni soucin techto vektoru %e%n", maxScalarProduct);
    }
}
