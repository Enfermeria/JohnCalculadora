/************************************************************************************************
 *              TRABAJO PRACTICO 4 y 5: CALCULADORA PARA ANDRO                                  *
 *                              John David Molina                                               *
 ************************************************************************************************/

package com.johnmolina.johncalculadora;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Utils {
    Context contexto;

    //Variables de la calculadora
    private String ultimoResultado; // el resultado de la ultima operacion
    private String ultimaOperacion; // la ultima operacion realizada
    private boolean inicioDigito;  // si esta al principio del digito (no ingreso nada)

    //Variables de Botones y display
    public TextView display;
    public Button tecla0, tecla1, tecla2, tecla3, tecla4, tecla5, tecla6, tecla7, tecla8, tecla9;
    public Button teclaPunto, teclaC, teclaIgual, teclaSuma, teclaResta, teclaMultiplica, teclaDivide;

    //variable de switch normal/cientifica
    public Switch switchCientifica;

    //variable de spinner de tipo de angulo
    public Spinner tipoAngulo;

    /****************************************************************************************
     *                                  Metodo constructor                                 *
     ****************************************************************************************/
    public Utils(Context contexto) {
        ultimoResultado = "0"; // el resultado de la ultima operacion
        ultimaOperacion = "+"; // la ultima operacion realizada
        inicioDigito = true;  // si esta al principio del digito (no ingreso nada)
        this.contexto = contexto;
    }

    /****************************************************************************************
     *         Metodos para atender los click de los botones del teclado                    *
     ****************************************************************************************/
    public void onClickTeclaDigito(View view) {
        final String digito = ((Button) view).getText().toString(); //texto del boton
        if ( ! display.getText().toString().equals("ERROR") ) {
            if (display.getText().toString().equals("0") | inicioDigito) // Si es un 0, lo reemplazo, sino lo agrego al final.
                display.setText(digito);
            else
                display.setText(display.getText().toString() + digito);
            inicioDigito = false;
        }
    } // onClickTeclaDigito


    public void onClickTeclaPunto(View view) {
        if ( display.getText().toString().contains (".")) // si ya tiene un punto, le aviso
            mensaje("Ya tiene el punto...");
        else if (! display.getText().toString().equals("ERROR") )
            display.setText(display.getText().toString() + ".");
        inicioDigito = false;
    } // onClickTeclaPunto


    public void onClickTeclaC(View view) {
        display.setText("0");
        ultimaOperacion = "+";
        ultimoResultado = "0";
        inicioDigito = true;
    } // onClickTeclaC


    public void onClickTeclaOperacionBinaria(View view) {
        final String textoDelBoton = ((Button) view).getText().toString();
        if ( ! inicioDigito )
            calcular(ultimaOperacion);
        ultimaOperacion = textoDelBoton;
        inicioDigito = true;
    } // onClickTeclaOperacionBinaria


    public void onClickTeclaOperacionUnaria(View view) {
        final String textoDelBoton = ((Button) view).getText().toString();
        if ( ! display.getText().toString().equals("ERROR") )
            calcular(textoDelBoton);
        ultimaOperacion = "=";
        inicioDigito = true;
    } // onClickTeclaOperacionUnaria



    /****************************************************************************************
     *          Metodo que hace las cuentas de la calculadora (el nucleo)                   *
     ****************************************************************************************/
    // me pasan la operación a realizar. Si es binaria, hago ultimoResultado (operacion) sDisplay.
    // Si es una operación unaria, hago (operación) sDisplay.
    public void calcular(String operacion) {
        // Si es calculadora científica, defino las constantes de conversión de angulos para las operaciones trigonometricas
        double anguloARadianes = 1.0; // coeficiente usado para convertir el tipo de ángulo que se usa a radianes
        if (switchCientifica.isChecked()) {
            switch (tipoAngulo.getSelectedItem().toString()) {
                case "Grados":
                    anguloARadianes = Math.PI / 180.0;
                    break;
                case "Radianes":
                    anguloARadianes = 1.0;
                    break;
                case "Gradianes":
                    anguloARadianes = Math.PI /200.0;
            }
        }

        //si el display tiene un . final, lo quito
        String sDisplay = display.getText().toString();  //pasa textview Display a string
        if (sDisplay.charAt(sDisplay.length()-1) == '.' ) {  // si el ultimo char es '.' lo saca
            sDisplay = sDisplay.substring(0, sDisplay.length() - 1);
        }

        //calcula segun la "operacion" pasada como parametro, es decir, hace un:
        // ultimoResultado = ultimoResultado (operacion) sDisplay  cuando es operacion binaria
        // ultimoResultado = (operacion) sDisplay  cuando es operacion unaria
        switch ( operacion )  {
            case "+":
                ultimoResultado = String.valueOf( Double.parseDouble(ultimoResultado) + Double.parseDouble(sDisplay)  );
                break;
            case "-":
                ultimoResultado = String.valueOf( Double.parseDouble(ultimoResultado) - Double.parseDouble(sDisplay)  );
                break;
            case "x":
                ultimoResultado = String.valueOf( Double.parseDouble(ultimoResultado) * Double.parseDouble(sDisplay) );
                break;
            case "÷":
                if ( Double.parseDouble(sDisplay) == 0.0 )
                    display.setText("ERROR");
                else
                    ultimoResultado = String.valueOf( Double.parseDouble(ultimoResultado) / Double.parseDouble(sDisplay) );
                break;
            case "=":
                ultimoResultado = sDisplay;
                break;
            case "Sin":
                ultimoResultado = String.valueOf( Math.sin( Double.parseDouble(sDisplay)*anguloARadianes ) ); //sin() requiere angulo en radianes. Devuelve -1..+1
                break;
            case "Cos":
                ultimoResultado = String.valueOf( Math.cos( Double.parseDouble(sDisplay)*anguloARadianes ) ); //cos() requiere angulo en radianes. Devuelve -1..+1
                break;
            case "Tan":
                ultimoResultado = String.valueOf( Math.tan( Double.parseDouble(sDisplay)*anguloARadianes ) ); //tan() requiere angulo en radianes. Devuelve -1..+1
                if (ultimoResultado == "NaN")
                    display.setText("ERROR");
                break;
            case "Asin":
                ultimoResultado = String.valueOf( Math.asin(Double.parseDouble(sDisplay))/anguloARadianes );
                if (ultimoResultado == "NaN")
                    display.setText("ERROR");
                break;
            case "Acos":
                ultimoResultado = String.valueOf( Math.acos(Double.parseDouble(sDisplay))/anguloARadianes );
                if (ultimoResultado == "NaN")
                    display.setText("ERROR");
                break;
            case "Atan":
                ultimoResultado = String.valueOf( Math.atan(Double.parseDouble(sDisplay))/anguloARadianes );
                if (ultimoResultado == "NaN")
                    display.setText("ERROR");
                break;
            case "√x":
                if ( Double.parseDouble(sDisplay) < 0.0 ) // no existe raiz negativa
                    display.setText("ERROR");
                else
                    ultimoResultado = String.valueOf( Math.sqrt( Double.parseDouble(sDisplay) ) );
                break;
            case "x²":
                ultimoResultado = String.valueOf( Double.parseDouble(sDisplay) * Double.parseDouble(sDisplay) );
                break;
            case "|x|":
                ultimoResultado = String.valueOf( Math.abs( Double.parseDouble(sDisplay) ) );
                break;
            case "1/x":
                ultimoResultado = String.valueOf( 1.0 / Math.abs( Double.parseDouble(sDisplay) ) );
                break;
            case "+-":
                ultimoResultado = String.valueOf( -1.0 * Double.parseDouble(sDisplay)  );
                break;
            default: // operacion no valida
                mensaje("Operación no válida");
                break;
        } //switch

        if (! display.getText().toString().equals("ERROR") ) {
            //Si finaliza en .0 le saca ese .0 y lo deja entero
            if ( Double.parseDouble(ultimoResultado) == Math.round( Double.parseDouble(ultimoResultado)) )
                ultimoResultado = String.valueOf( Math.round( Double.parseDouble(ultimoResultado)) );

            /*//Si el numero es menor que 0.00000000000... 001   [menor que 10^(-16)] es muy chico, que sea 0.
            if ( Math.abs(Double.parseDouble(ultimoResultado)) < Math.pow(1.0, -16) )
                ultimoResultado = "0";*/

            display.setText(ultimoResultado);
        }
    } //calcular



    /****************************************************************************************
     *                               Metodos utilitarios                                    *
     ****************************************************************************************/
    public void mensaje(String s) {
        // Context contexto = getApplicationContext();
        int duracion = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(contexto, s, duracion);
        toast.show();
    } //mensaje

}
