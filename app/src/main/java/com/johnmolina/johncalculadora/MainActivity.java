/************************************************************************************************
 *              TRABAJO PRACTICO 3: CALCULADORA PARA ANDROID                                    *
 *                              John David Molina                                               *
 ************************************************************************************************/

package com.johnmolina.johncalculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Variables de la calculadora
    String ultimoResultado = "0"; // el resultado de la ultima operacion
    String ultimaOperacion = "+"; // la ultima operacion realizada
    boolean inicioDigito = true;  // si ya esta al principio del digito (no ingreso nada)

    //Variables de Botones y display
    TextView Display;
    Button tecla0;
    Button tecla1;
    Button tecla2;
    Button tecla3;
    Button tecla4;
    Button tecla5;
    Button tecla6;
    Button tecla7;
    Button tecla8;
    Button tecla9;
    Button teclaPunto;
    Button teclaC;
    Button teclaIgual;
    Button teclaSuma;
    Button teclaResta;
    Button teclaMultiplica;
    Button teclaDivide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Vinculo display
        Display = (TextView) findViewById(R.id.Display);
        Display.setText("0");

        //vinculo botones
        tecla0          = (Button) findViewById(R.id.tecla0);
        tecla1          = (Button) findViewById(R.id.tecla1);
        tecla2          = (Button) findViewById(R.id.tecla2);
        tecla3          = (Button) findViewById(R.id.tecla3);
        tecla4          = (Button) findViewById(R.id.tecla4);
        tecla5          = (Button) findViewById(R.id.tecla5);
        tecla6          = (Button) findViewById(R.id.tecla6);
        tecla7          = (Button) findViewById(R.id.tecla7);
        tecla8          = (Button) findViewById(R.id.tecla8);
        tecla9          = (Button) findViewById(R.id.tecla9);
        teclaPunto      = (Button) findViewById(R.id.teclaPunto);
        teclaC          = (Button) findViewById(R.id.teclaC);
        teclaIgual      = (Button) findViewById(R.id.teclaIgual);
        teclaSuma       = (Button) findViewById(R.id.teclaSuma);
        teclaResta      = (Button) findViewById(R.id.teclaResta);
        teclaMultiplica = (Button) findViewById(R.id.teclaMultiplica);
        teclaDivide     = (Button) findViewById(R.id.teclaDivide);
    } //onCreate


    /****************************************************************************************
     *         Metodos para atender los click de los botones del teclado                    *
     ****************************************************************************************/
    public void onClickTeclaDigito(View view) {
        final String digito = ((Button) view).getText().toString(); //texto del boton
        if ( ! Display.getText().toString().equals("ERROR") ) {
            if (Display.getText().toString().equals("0") | inicioDigito) // Si es un 0, lo reemplazo, sino lo agrego al final.
                Display.setText(digito);
            else
                Display.setText(Display.getText().toString() + digito);
            inicioDigito = false;
        }
    } // onClickTeclaDigito


    public void onClickTeclaPunto(View view) {
        if ( Display.getText().toString().contains (".")) // si ya tiene un punto, le aviso
            mensaje("Ya tiene el punto...");
        else
            Display.setText(Display.getText().toString() + ".");
        inicioDigito = false;
    } // onClickTeclaPunto


    public void onClickTeclaC(View view) {
        Display.setText("0");
        ultimaOperacion = "+";
        ultimoResultado = "0";
        inicioDigito = true;
    } // onClickTeclaC


    public void onClickTeclaOperacion(View view) {
        final String textoDelBoton = ((Button) view).getText().toString();
        if ( ! inicioDigito )
            calcular(ultimaOperacion);
        ultimaOperacion = textoDelBoton;
        inicioDigito = true;
    } // onClickTeclaOperacion


    public void calcular(String operacion) {
        //si el display tiene un . final, lo quito
        String sDisplay = Display.getText().toString();  //pasa etDisplay a string
        if (sDisplay.charAt(sDisplay.length()-1) == '.' ) {  // si el ultimo char es '.' lo saca
            sDisplay = sDisplay.substring(0, sDisplay.length() - 1);
        }

        //calcula segun la "operacion" pasada como parametro, es decir, hace un
        // ultimoResultado = ultimoResultado (operacion) etDisplay
        if ( operacion.equals("+") ) {
            ultimoResultado = String.valueOf( Double.parseDouble(ultimoResultado) + Double.parseDouble(sDisplay)  );
        } else if ( operacion.equals("-") ) {
            ultimoResultado = String.valueOf( Double.parseDouble(ultimoResultado) - Double.parseDouble(sDisplay)  );
        } else if ( operacion.equals("x") ) {
            ultimoResultado = String.valueOf( Double.parseDouble(ultimoResultado) * Double.parseDouble(sDisplay) );
        } else if ( operacion.equals("÷") ) {
            if ( Double.parseDouble(sDisplay) == 0.0 )
                Display.setText("ERROR");
            else
                ultimoResultado = String.valueOf( Double.parseDouble(ultimoResultado) / Double.parseDouble(sDisplay) );
        }
        else if ( operacion.equals("=") ) {
            ultimoResultado = sDisplay;
        }
        else { // operacion no valida
            mensaje("Operación no válida");
        }

        if (! Display.getText().toString().equals("ERROR") ) {
            if ( Double.parseDouble(ultimoResultado) == Math.round( Double.parseDouble(ultimoResultado)) )
                ultimoResultado = String.valueOf( Math.round( Double.parseDouble(ultimoResultado)) );
            Display.setText(ultimoResultado);
        }
    } //calcular


    /****************************************************************************************
     *                               Metodos utilitarios                                    *
     ****************************************************************************************/
    private void mensaje(String s) {
        Context contexto = getApplicationContext();
        int duracion = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(contexto, s, duracion);
        toast.show();
    } //mensaje
} //class MainActivity

