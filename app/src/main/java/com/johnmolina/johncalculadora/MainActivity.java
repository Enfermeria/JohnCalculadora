/************************************************************************************************
 *              TRABAJO PRACTICO 4 y 5: CALCULADORA PARA ANDRO                                  *
 *                              John David Molina                                               *
 ************************************************************************************************/

package com.johnmolina.johncalculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.johnmolina.johncalculadora.Utils;

public class MainActivity extends AppCompatActivity {
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        utils = new Utils(getApplicationContext());
        //Vinculo display
        utils.display = (TextView) findViewById(R.id.display);
        utils.display.setText("0");

        //vinculo botones
        utils.tecla0          = (Button) findViewById(R.id.tecla0);
        utils.tecla1          = (Button) findViewById(R.id.tecla1);
        utils.tecla2          = (Button) findViewById(R.id.tecla2);
        utils.tecla3          = (Button) findViewById(R.id.tecla3);
        utils.tecla4          = (Button) findViewById(R.id.tecla4);
        utils.tecla5          = (Button) findViewById(R.id.tecla5);
        utils.tecla6          = (Button) findViewById(R.id.tecla6);
        utils.tecla7          = (Button) findViewById(R.id.tecla7);
        utils.tecla8          = (Button) findViewById(R.id.tecla8);
        utils.tecla9          = (Button) findViewById(R.id.tecla9);
        utils.teclaPunto      = (Button) findViewById(R.id.teclaPunto);
        utils.teclaC          = (Button) findViewById(R.id.teclaC);
        utils.teclaIgual      = (Button) findViewById(R.id.teclaIgual);
        utils.teclaSuma       = (Button) findViewById(R.id.teclaSuma);
        utils.teclaResta      = (Button) findViewById(R.id.teclaResta);
        utils.teclaMultiplica = (Button) findViewById(R.id.teclaMultiplica);
        utils.teclaDivide     = (Button) findViewById(R.id.teclaDivide);

        //vinculo switch de normal/cientifica
        utils.switchCientifica = (Switch) findViewById(R.id.switchCientifica);
    } //onCreate


    /****************************************************************************************
     *         Metodos para atender los click de los botones del teclado                    *
     ****************************************************************************************/
    public void onClickTeclaDigito(View view) {
        utils.onClickTeclaDigito(view);
    } // onClickTeclaDigito


    public void onClickTeclaPunto(View view) {
       utils.onClickTeclaPunto(view);
    } // onClickTeclaPunto


    public void onClickTeclaC(View view) {
        utils.onClickTeclaC(view);
    } // onClickTeclaC


    public void onClickTeclaOperacionBinaria(View view) {
        utils.onClickTeclaOperacionBinaria(view);
    } // onClickTeclaOperacionBinaria

    public void onClickTeclaOperacionUnaria(View view) {
        utils.onClickTeclaOperacionUnaria(view);
    } // onClickTeclaOperacionUnaria

    public void cambioACientifica(View v) {
        Intent intentCientifica = new Intent(this, Cientifica.class); //creo un intent para que se llame a la otra activity
        startActivity(intentCientifica); //llama a la otra activity
    }
} //class MainActivity

