
package cz.tul.fm.alg1.dostal;

import java.util.InputMismatchException;

public class MaxScalarProductCalculation 
{
    //in variables
    private static int vectorsCount;
    private static int vectorElementsCount;
    private static double[][] vectorsSet;
    
    //out variables
    private static double vectorsSetMaxScalarProduct;
    private static double[][] twoFoundVectors = new double[2][];
    
    //testing main
    public static void main (String[] args)  
    {
        /*
        //emptyOutput() method
        System.out.println();
        System.out.println("emptyOutput() metoda");
        System.out.println();
        
        vectorsSetMaxScalarProduct = 5;
        twoFoundVectors[0] = new double[] {2, 3};
        twoFoundVectors[1] = new double[] {2, 3};
        emptyOutput();
        
        System.out.println(vectorsSetMaxScalarProduct);
        System.out.println(twoFoundVectors[0]);
        System.out.println(twoFoundVectors[1]);
        
        //searchVectorsSet() method
        System.out.println();
        System.out.println("searchVectorsSet() metoda");
        System.out.println();
        
        vectorsSet = new double[][] {{3, 3},
                                     {5, 7},
                                     {2, 2},
                                     {9, 8}};
        searchVectorsSet();
        
        UI.displayTwoFoundVectors(twoFoundVectors);
        UI.displayFoundMaxScalarProduct(vectorsSetMaxScalarProduct);
        emptyOutput();
        
        //calculateScalarProduct() method
        System.out.println();
        System.out.println("calculateScalarProduct() metoda");
        System.out.println();
        
        vectorsSet = new double[][] {{1, 8},
                                     {2, 3},
                                     {4, 5},
                                     {10, 2}};
        double scalarProduct = calculateScalarProduct(1, 2);
        
        System.out.println(scalarProduct);
        
        //compareCalculatedAndMaxScalarProduct() method
        System.out.println();
        System.out.println("compareCalculatedAndMaxScalarProduct() metoda");
        System.out.println();
        
        vectorsSetMaxScalarProduct = 100;
        scalarProduct = 53;
        boolean isProductEqualOrBiggerThanMax = 
                compareCalculatedAndMaxScalarProduct(scalarProduct);
        
        String textIsEqualOrBigger = isProductEqualOrBiggerThanMax ? "je" : "neni";
        System.out.printf("Skalarni soucin %.2f %s roven nebo vetsi "
                          + "nez maximalni skalarni soucin %.2f%n", scalarProduct, 
                          textIsEqualOrBigger, vectorsSetMaxScalarProduct);
        emptyOutput();
        
        //setNewMaxScalarProduct() method
        System.out.println();
        System.out.println("setNewMaxScalarProduct() metoda");
        System.out.println();
        
        vectorsSetMaxScalarProduct = 100;
        System.out.println(vectorsSetMaxScalarProduct);
        setNewMaxScalarProduct(scalarProduct);
        
        System.out.println(vectorsSetMaxScalarProduct);
        emptyOutput();
        
        //setNewTwoFoundVectors() method
        System.out.println();
        System.out.println("setNewTwoFoundVectors() metoda");
        System.out.println();
        
        twoFoundVectors[0] = new double[] {1, 2, 3};
        twoFoundVectors[1] = new double[] {4, 5, 6};
        
        UI.displayTwoFoundVectors(twoFoundVectors);
        
        vectorsSet = new double[][] {{2, 12},
                                     {3, 4},
                                     {8, 2},
                                     {4, 3}};
        setNewTwoFoundVectors(2, 3);
        
        UI.displayTwoFoundVectors(twoFoundVectors);
        emptyOutput();
        */
    }
    
    public static void startProgram ()
    {
        boolean programEnd = false;
        
        do
        {
            emptyOutput();
            
            try 
            {
                //in
                vectorsCount = UI.loadVectorsCount();
                
                if (vectorsCount <= 0) 
                {
                    programEnd = true;
                }
                else if (vectorsCount == 1) 
                {
                    throw new Exception("Skalarni soucin nelze spocitat pro pouze "
                                        + "jeden zadany vektor");
                }
                else 
                {
                    vectorElementsCount = UI.loadVectorElementsCount();
                    
                    if (vectorElementsCount <= 1) 
                    {
                        throw new Exception("Vektor musi mit minimalne 2 slozky");
                    }
                    
                    vectorsSet = UI.loadVectorsSet(vectorsCount, vectorElementsCount);
                    
                    //cal
                    searchVectorsSet();
                    
                    //out
                    UI.displayTwoFoundVectors(twoFoundVectors);
                    UI.displayFoundMaxScalarProduct(vectorsSetMaxScalarProduct);
                }
            }
            catch (InputMismatchException ex) 
            {
                UI.displayErrorMessage("Nevalidni vstupni data");
            }
            catch (Exception ex) 
            {
                UI.displayErrorMessage(ex.getMessage());
            }
        }
        while(!programEnd);
    }
    
    /**
     * Resets the output variables to default values when a program's new task starts.
     */
    private static void emptyOutput () 
    {
        /*
          nastaveno na minus nekonecno z duvodu, aby se spravne vyhledala
          dvojice vektoru a maximalni skalarni soucin za situace, ze
          maximalni skalarni soucin je zaporne realne cislo
        */
        vectorsSetMaxScalarProduct = Double.NEGATIVE_INFINITY;
        
        twoFoundVectors[0] = null;
        twoFoundVectors[1] = null;
    }
    
    /**
     * Iterates over nested loop within outer loop to gradually calculate a scalar
     * product for each possible unique combination of two rows from the vectors 
     * set and compare it
     * with the existing max scalar product to decide if calculated scalar product
     * will be the new maximum scalar product.
     * @throws Exception if final maximum scalar product is equal to infinity
     */
    private static void searchVectorsSet () throws Exception 
    {
        double chosenVectorsScalarProduct;
        boolean isScalarProductEqualOrGreaterThanMaxScalarProduct;
        
        for (int firstChosenVectorIndex = 0;  firstChosenVectorIndex < vectorsSet.length - 1; 
                 firstChosenVectorIndex++) 
        {
            for (int secondChosenVectorIndex = firstChosenVectorIndex + 1; 
                     secondChosenVectorIndex < vectorsSet.length; 
                     secondChosenVectorIndex++) 
            {
                chosenVectorsScalarProduct = 
                        calculateScalarProduct(firstChosenVectorIndex, 
                                               secondChosenVectorIndex);
                
                isScalarProductEqualOrGreaterThanMaxScalarProduct = 
                        compareCalculatedAndMaxScalarProduct (chosenVectorsScalarProduct);
                
                if (isScalarProductEqualOrGreaterThanMaxScalarProduct) 
                {
                    setNewMaxScalarProduct(chosenVectorsScalarProduct);
                    setNewTwoFoundVectors(firstChosenVectorIndex, secondChosenVectorIndex);
                }
            }
        }
        
        if (vectorsSetMaxScalarProduct == Double.POSITIVE_INFINITY ||
            vectorsSetMaxScalarProduct == Double.NEGATIVE_INFINITY) 
        {
            throw new Exception("Vypocitany maximalni skalarni soucin nemuze byt nekonecno");
        }
    }
    
    /**
     * From given first and second vector indexes iterates over chosen 
     * vectors columns to gradually make sum of products (scalar product), which 
     * are calculated as multiplication of first and second vector elements in 
     * each column.
     * @param firstChosenVectorIndex represents a row index of a first chosen vector 
     * from the vectors set
     * @param secondChosenVectorIndex represents a row index of a second chosen 
     * vector from the vectors set
     * @return a scalar product of two chosen vectors
     */
    private static double calculateScalarProduct (int firstChosenVectorIndex, 
                                                  int secondChosenVectorIndex) 
    {
        double chosenVectorsScalarProduct = 0;
        
        for (int chosenVectorsElementIndex = 0; 
                 chosenVectorsElementIndex < vectorsSet[firstChosenVectorIndex].length; 
                    chosenVectorsElementIndex++) 
        {
            chosenVectorsScalarProduct += 
                    vectorsSet[firstChosenVectorIndex][chosenVectorsElementIndex] * 
                    vectorsSet[secondChosenVectorIndex][chosenVectorsElementIndex];
        }
        
        return chosenVectorsScalarProduct;
    }
    
    /**
     * Compares the given scalar product with the max scalar product.
     * @param chosenVectorsScalarProduct represents a scalar product of two chosen vectors
     * @return logical one if the given scalar product is greater than or equal to 
     * the max scalar product, otherwise returns logical zero
     */
    private static boolean compareCalculatedAndMaxScalarProduct (double chosenVectorsScalarProduct) 
    {
        return chosenVectorsScalarProduct >=  vectorsSetMaxScalarProduct;
    }
    
    /**
     * Sets the output variable of max scalar product as given scalar product of two 
     * chosen vectors.
     * @param chosenVectorsScalarProduct represents a scalar product of two chosen vectors
     */
    private static void setNewMaxScalarProduct (double chosenVectorsScalarProduct) 
    {
        vectorsSetMaxScalarProduct = chosenVectorsScalarProduct;
    }
    
    /**
     * Sets the output variable of two found vectors as two chosen vectors.
     * @param firstChosenVectorIndex represents a row index of a first chosen vector 
     * from the vectors set
     * @param secondChosenVectorIndex represents a row index of a second chosen 
     * vector from the vectors set
     */
    private static void setNewTwoFoundVectors (int firstChosenVectorIndex,
                                               int secondChosenVectorIndex) 
    {
        twoFoundVectors[0] = vectorsSet[firstChosenVectorIndex];
        twoFoundVectors[1] = vectorsSet[secondChosenVectorIndex];
    }
}
