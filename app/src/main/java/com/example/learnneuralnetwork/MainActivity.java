package com.example.learnneuralnetwork;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //main();
    }

    private void main() {
        double[][] n0 = new double[10][2]; // входной слой
        n0[9][0] = 1;

        double[][] n1 = new double[5][2]; // входной слой
        n1[4][0] = 1;

        double[][] n2 = new double[4][2]; // входной слой
        n2[3][0] = 1;

        double[][] n3 = new double[2][2]; // входной слой

        double[][] idl = new double[2][1]; //типо идеальный массив

        double[][] w01 = new double[10][4]; //массивы весов
        double[][] w12 = new double[5][3];
        double[][] w23 = new double[4][2];
        //TODO заполнить массивы числами близкими к 0

        Random random = new Random();
        for (int i = 0; i < w01.length; i++) {
            for (int j = 0; j < w01.length; j++) {
                w01[i][j] = random.nextDouble();
            }
        }

    }

    private void forwards(double[] layerIn, double[][] weights, double[] layerOut) {

        for (int i = 0; i < layerOut.length; i++) {
            layerOut[i] = 0;
            for (int j = 0; j < layerIn.length; j++) {
                layerOut[i] = layerOut[i] + layerIn[j] * weights[i][j];
            }

            layerOut[i] = 1 / (1 + Math.exp(-1 * layerOut[i]));
        }
    }

    private void findError(double[][] layerIn, double[][] weights, double[][] layerOut) {

        for (int i = 0; i < layerIn.length - 1; i++) { //Какой то нейрон смещения

            layerIn[i][1] = 0;

            for (int j = 0; j < layerOut.length; j++) {

                layerIn[i][1] = layerIn[i][1] + weights[i][j]*layerOut[j][1];
            }
        }

    }

    private void backwards (double[][] layerIn, double[][] weights, double[][] layerOut,
                            double kLearn) {
        for (int i = 0; i < layerOut.length; i++) {

            for (int j = 0; j < layerIn.length; j++) {
                weights[i][j] = weights[i][j] + kLearn * layerOut[i][1]*layerOut[i][0]*
                        (1 - layerOut[i][0]) * layerIn[j][0]; //Возможно умножение на производную
                //Происходит в другом месте, на 8 минуте - два варианта библиотеки
            }
        }
    }
}
