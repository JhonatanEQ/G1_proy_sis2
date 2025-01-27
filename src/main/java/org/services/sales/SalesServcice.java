package org.services.sales;

public class SalesServcice {

    // Método para sumar los elementos de un array
    public int sumArrayElements(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    // Método para aplicar un descuento del 15% si la suma es mayor a 200
    public double AplicarDescuento(int total) {
        if (total > 200) {
            return total * 0.85; // Aplicar un descuento del 15%
        }
        return total; // No se aplica descuento
    }

    // Método principal para probar la funcionalidad
    public static void main(String[] args) {
        SalesServcice service = new SalesServcice();
        
        // Ejemplo de un array de números
        int[] sales = {100, 50, 60, 30};
        
        // Sumar los elementos del array
        int total = service.sumArrayElements(sales);
        
        // Aplicar el descuento si es necesario
        double finalTotal = service.AplicarDescuento(total);
        
        // Imprimir los resultados
        System.out.println("La suma de los elementos del array es: " + total);
        System.out.println("El total después de aplicar el descuento es: " + finalTotal);
    }
}
