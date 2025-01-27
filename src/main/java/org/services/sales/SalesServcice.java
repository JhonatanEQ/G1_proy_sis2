package org.services.sales;

public class SalesServcice {
   // Método para sumar los elementos de un array modificar segun lo que se mostrara en pantalla
    public int sumArrayElements(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
/* para probar
    // Método principal para probar la funcionalidad
    public static void main(String[] args) {
        SalesServcice service = new SalesServcice();
        
        // Ejemplo de un array de números
        int[] sales = {10, 20, 30, 40, 50};
        
        // Sumar los elementos del array
        int total = service.sumArrayElements(sales);
        
        // Imprimir el resultado
        System.out.println("La suma de los elementos del array es: " + total);
    }
*/
 
}

