package unitTest;

import br.com.iterasys.Calculadora;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CalculadoraTest {

    @ParameterizedTest
    @CsvSource(value = {
            "7, 5, 12",
            "56, 44, 100",
            "10, 0, 10",
            "15, -5, 10",
            "-8, -6, -14"

    }, delimiter = ',')
    public void testSomarDoisNumerosComLista(String txtNum1, String txtNum2, String txtResultadoEsperado){


        Calculadora calculadora = new Calculadora();
        double resultadoAtual = calculadora.somarDoisNumeros(Integer.valueOf(txtNum1), Integer.valueOf(txtNum2));

        assertEquals(Double.valueOf(txtResultadoEsperado), resultadoAtual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/massaSomar.csv", numLinesToSkip = 1)
    public void testSomarDoisNumerosComArquivo(String txtNum1, String txtNum2, String txtResultadoEsperado){
        Calculadora calculadora = new Calculadora();
        double resultadoAtual = calculadora.somarDoisNumeros(Integer.valueOf(txtNum1), Integer.valueOf(txtNum2));
        assertEquals(Double.valueOf(txtResultadoEsperado), resultadoAtual);
    }

    @Test
    public void testSubtrairDoisNumeros(){
        double num1 = 10;
        double num2 = 5;
        double resultadoEsperado = 5;

        Calculadora calculadora = new Calculadora();
        double resultadoAtual = calculadora.subtrairDoisNumeros(num1, num2);
        assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void testDividirPorZero(){
        double num1 = 100;
        double num2 = 0;
        try {
            Calculadora calculadora = new Calculadora();
            calculadora.dividirDoisNumeros(num1, num2);
            fail("Esperava uma divisao por Zero.");
        }catch (ArithmeticException e){
            assertEquals("Divisão por zero não é permitida.", e.getMessage());
        }
    }

    @Test
    public void testDividirDoisNumeros(){
        double num1 = 100;
        double num2 = 2;
        double resultadoEsperado = 50;

        Calculadora calculadora = new Calculadora();
        double resultadoAtual = calculadora.dividirDoisNumeros(num1, num2);
        assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void testMultiplicarDoisNumeros(){
        double num1 = 2;
        double num2 = 3;
        double resultadoEsperado = 6;

        Calculadora calculadora = new Calculadora();
        double resultadoAtual = calculadora.multiplicarDoisNumeros(num1, num2);
        assertEquals(resultadoEsperado, resultadoAtual);
    }
}
