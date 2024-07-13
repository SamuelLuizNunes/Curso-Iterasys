package br.com.iterasys;

public class Calculadora {

    public double somarDoisNumeros(double num1, double num2){
        return num1 + num2;
    }

    public double subtrairDoisNumeros(double num1, double num2){
        return num1 - num2;
    }

    public double multiplicarDoisNumeros(double num1, double num2){
        return num1 * num2;
    }

    public double dividirDoisNumeros(double num1, double num2){
        if (num2 == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida.");
        }
        return num1 / num2;
    }
}
