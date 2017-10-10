package com.amcaicedo.sena.apppruebavoz;



import android.util.Log;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by YEIMY BRAVO on 20/06/2017.
 */

public class Algoritmos {

    private float[] arreglo;
    private float[] tmfArreglo;
    private float Jitta;
    private float frecFund;
    private ArrayList datosAmplitud;
    private float Jitt;
    private float RAP;
    private float TemPeriodos;
    private ArrayList matrizPicos;
    private ArrayList matrizPos;
    private ArrayList picos;//declaracion
    private ArrayList posicion;
    public ArrayList posicionesMax;
    public ArrayList posicionesMin;
    private ArrayList restaPi;
    private ArrayList matrizShimPicosPositivos;//reformo array
    private ArrayList matrizShimPosPositivos;
    private ArrayList matrizShimPicosNegativos;//
    private ArrayList matrizShimPosNegativos;
    private ArrayList Amp;
    private float ShimArriba;
    private float Shimm;
    private float apq3;
    private float Resta22;
    float Suma = 0;

    public static ArrayList datosFrecuencia = new ArrayList();


    public float[] getArreglo() {
        return arreglo;
    }

    public float[] getArreglo1() {
        return arreglo1;
    }

    public void setArreglo(float[] arreglo) {
        this.arreglo = arreglo;
    }

    public float getJitta() {
        return Jitta;
    }

    public void setJitta(float jitta) {
        Jitta = jitta;
    }

    public float getFrecFund() {
        return frecFund;
    }

    public void setFrecFund(float frecFund) {
        this.frecFund = frecFund;
    }

    public float getJitt() {
        return Jitt;
    }

    public void setJitt(float jitt) {
        Jitt = jitt;
    }

    public float getRAP() {
        return RAP;
    }

    public void setRAP(float RAP) {
        this.RAP = RAP;
    }

    public float getTemPeriodos() {
        return TemPeriodos;
    }

    public void setTemPeriodos(float temPeriodos) {
        TemPeriodos = temPeriodos;
    }

    public ArrayList getMatrizPicos() {
        return matrizPicos;
    }

    public void setMatrizPicos(ArrayList matrizPicos) {
        this.matrizPicos = matrizPicos;
    }

    public ArrayList getMatrizPos() {
        return matrizPos;
    }

    public void setMatrizPos(ArrayList matrizPos) {
        this.matrizPos = matrizPos;
    }

    public ArrayList getPicos() {
        return picos;
    }

    public void setPicos(ArrayList picos) {
        this.picos = picos;
    }

    public ArrayList getPosicion() {
        return posicion;
    }

    public void setPosicion(ArrayList posicion) {
        this.posicion = posicion;
    }

    public ArrayList getPosicionesMax() {
        return posicionesMax;
    }

    public void setPosicionesMax(ArrayList posicionesMax) {
        this.posicionesMax = posicionesMax;
    }

    public ArrayList getPosicionesMin() {
        return posicionesMin;
    }

    public void setPosicionesMin(ArrayList posicionesMin) {
        this.posicionesMin = posicionesMin;
    }

    public ArrayList getRestaPi() {
        return restaPi;
    }

    public void setRestaPi(ArrayList restaPi) {
        this.restaPi = restaPi;
    }

    public ArrayList getMatrizShimPicosPositivos() {
        return matrizShimPicosPositivos;
    }

    public void setMatrizShimPicosPositivos(ArrayList matrizShimPicosPositivos) {
        this.matrizShimPicosPositivos = matrizShimPicosPositivos;
    }

    public ArrayList getMatrizShimPosPositivos() {
        return matrizShimPosPositivos;
    }

    public void setMatrizShimPosPositivos(ArrayList matrizShimPosPositivos) {
        this.matrizShimPosPositivos = matrizShimPosPositivos;
    }

    public ArrayList getMatrizShimPicosNegativos() {
        return matrizShimPicosNegativos;
    }

    public void setMatrizShimPicosNegativos(ArrayList matrizShimPicosNegativos) {
        this.matrizShimPicosNegativos = matrizShimPicosNegativos;
    }

    public ArrayList getMatrizShimPosNegativos() {
        return matrizShimPosNegativos;
    }

    public void setMatrizShimPosNegativos(ArrayList matrizShimPosNegativos) {
        this.matrizShimPosNegativos = matrizShimPosNegativos;
    }

    public void setArreglo1(float[] arreglo1) {
        this.arreglo1 = arreglo1;
    }

    public ArrayList getAmp() {
        return Amp;
    }

    public void setAmp(ArrayList amp) {
        Amp = amp;
    }

    public float getShimArriba() {
        return ShimArriba;
    }

    public void setShimArriba(float shimArriba) {
        ShimArriba = shimArriba;
    }

    public float getShimm() {
        return Shimm;
    }

    public void setShimm(float shimm) {
        Shimm = shimm;
    }

    public float getApq3() {
        return apq3;
    }

    public void setApq3(float apq3) {
        this.apq3 = apq3;
    }

    public float getResta22() {
        return Resta22;
    }

    public void setResta22(float resta22) {
        Resta22 = resta22;
    }



    float [] arreglo1;

    public Algoritmos(ArrayList arr) {
        System.out.println("Oriiiiiginal" +arr.size());
        this.arreglo = new float[arr.size()];
        for (int i = 0; i<arreglo.length; i++){
            arreglo[i] = Float.parseFloat(Short.toString((short)arr.get(i)));
        }
        System.out.println("Areglooooooooooooooooooooo" +arreglo.length);
    }

    private float maximos(float[] arr) {//maximos de cualquier arreglo de positivos
        int i;
        float max = 0f;
        for (i = 0; i < arr.length; i++)
        {
            if (arreglo[i]>max)
            {
                max = arreglo[i];
            }
        }
        return max;
    }

  /*  public ArrayList obtenerdB(){
        short amplitude = 0;
        for(int x = 0; x < maximos().size; x+=2000){
            amplitude =  (20 * Math.log10(maximos(arr) / 1));
            if (amplitude > 0)
                datosAmplitud.add(amplitude);
            Log.e("VALOR dB MAXIMOS", ""+amplitude);
        }
        return datosAmplitud;
    }
*/



    public void calcular(float umbral) { // seleccionar los picos del arreglo

        picos=new ArrayList();//instanciacion
        posicion = new ArrayList();
        arreglo1= normalizar(arreglo);
        //System.out.println("qqqqqqqqqqqqqqqqqqqqqq"+arreglo1.length);
        // umbral = 0.8f;
        float umbral2 = -umbral;
        int k;
        for (k = 1; k < arreglo1.length-1; k++)//
        {
            // System.out.println("qqqqqqqqqqqqqqqqqqqqqq"+arreglo1[k]);
            if((arreglo1[k]>arreglo1[k-1] && arreglo1[k]>arreglo1[k+1] && arreglo1[k]>umbral) || (arreglo1[k]<arreglo1[k-1] && arreglo1[k]<arreglo1[k+1] &&  arreglo1[k]<umbral2))
            {
                System.out.println("qqqqqqqqqqqqqqqqqqqqqq" + arreglo1[k]);
                System.out.println("aaaaaa" +k);
                picos.add(arreglo1[k]);
                posicion.add(k);
            }
        }
        System.out.println("ooooooooooooooo"+ posicion.size());
        System.out.println("uuuuuuuuuuuuuu"+ picos.size()); // comparar que hay 24 picos en matlab
    }

    public void negativos(){
        ArrayList picosTem = new ArrayList();
        ArrayList posicionTem = new ArrayList();
        matrizPicos = new ArrayList();
        matrizPos = new ArrayList();
        for (int x = 0; x<(picos.size()-1); x++){

            if ((float)picos.get(x)>0f && (float)picos.get(x+1)>0f){

                picosTem.add(picos.get(x));
                posicionTem.add(posicion.get(x));
                System.out.println("aaaaaaaaaaaaa" + picos.get(x));
            }
            else if((float)picos.get(x)>0f && (float)picos.get(x+1)<0f)
            {
                picosTem.add(picos.get(x));
                posicionTem.add(posicion.get(x));
                System.out.println("eeeeeeeeeeee" + picos.get(x));

                matrizPicos.add(picosTem);
                picosTem = new ArrayList();
                matrizPos.add(posicionTem);
                posicionTem = new ArrayList();
            }
            if(x == (picos.size()-2 )&& (float)picos.get(x+1)>0f)
            {
                picosTem.add(picos.get(x+1));
                posicionTem.add(posicion.get(x+1));

                System.out.println("iiiiiiiiiii" + picos.get(x+1));
                matrizPicos.add(picosTem);
                matrizPos.add(posicionTem);
            }
        }
    }

    public void prueba (){
        System.out.println("siiiiiiiiiiiiiiiii" + matrizPicos.size()); //total de picos
        System.out.println("nooooooooooooooooo" + matrizPos.size());
    }

    public int maxArray(ArrayList arrayListPicos, ArrayList arrayListPosicion) {//maximos de cualquier arreglo arr
        int i;
        float max = 0f;
        int posicion=0;
        if((float)arrayListPicos.get(0)>0f) {

            for (i = 0; i < arrayListPicos.size(); i++) {
                if ((float) arrayListPicos.get(i) > max) {
                    max = (float) arrayListPicos.get(i);
                    posicion = (int) arrayListPosicion.get(i);
                }
            }
            System.out.println("posicion positiva" + posicion);
        }
        else {
            for (i = 0; i < arrayListPicos.size(); i++) {
                if ((float) arrayListPicos.get(i) < max) {
                    max = (float) arrayListPicos.get(i);
                    posicion = (int) arrayListPosicion.get(i);
                }
            }

            System.out.println("posicion negativa" + posicion);
        }
        return posicion;

    }

    public void PosicionPicos() //ubicar en que posicion se encuentran los picos
    {
        int i;
        posicionesMax = new ArrayList();
        posicionesMin = new ArrayList();
        //hacer otro arreglo positivo y negativo

        for( i = 0; i<matrizShimPicosPositivos.size(); i++){
            posicionesMax.add( maxArray((ArrayList) matrizShimPicosPositivos.get(i), (ArrayList) matrizShimPosPositivos.get(i)));
        }

        for ( i=0; i<posicionesMax.size(); i++){
            System.out.println("pppositivas"+posicionesMax.get(i)); // posiciones totales donde se encuentran los picos
        }

        for( i = 0; i<matrizShimPicosNegativos.size(); i++){
            posicionesMin.add( maxArray((ArrayList) matrizShimPicosNegativos.get(i), (ArrayList) matrizShimPosNegativos.get(i)));
        }
        for ( i=0; i<posicionesMin.size(); i++){
            System.out.println("ppnegativos"+posicionesMin.get(i)); // posiciones totales donde se encuentran los picos
        }

    }


    private float[] normalizar(float[] arr) {//normaliza cualquier arreglo
        int i;
        float max = maximos(arr);
        float maxNeg = maximosNegativos(arr);//este metodo solo sirve para normalizar la parte negativa
        System.out.println("zzzzzzzz" +max);
        float [] nx = new float[arr.length];
        for (i = 0; i < arr.length; i++)
        {
            if(arr[i]>0) {

                nx[i] = arr[i] / max;
            }
            else{
                nx[i] = arr[i] / -maxNeg;
            }
        }
        return nx;
    }

    private float maximosNegativos(float[] arr) {//maximos de cualquier arreglo arr
        int i;
        float max = 0f;
        for (i = 0; i < arr.length; i++)
        {
            if (arreglo[i]<max)
            {
                max = arreglo[i];
            }

        }
        return max;
    }

    public void restaPicos()
    {
        int i;
        float resta1Tem, resta2Tem;

        restaPi = new ArrayList();
        float microSeg = 22.675f;

//resta de los puntos para hallar los periodos
        for (i=0; i<posicionesMax.size()-1; i++)
        {
            resta1Tem =(int)posicionesMax.get(i+1);
            resta2Tem=(int)posicionesMax.get(i); // se esta multiplicando cada picos por 22.675 x e(-6) porque esto equivale al valor de una muestra en microsegundos
            restaPi.add((resta1Tem*microSeg)-(resta2Tem*microSeg));

        }
        System.out.println("restapicos"+restaPi); // muestra los periodos calculados

        for(i=0; i<restaPi.size()-1; i++)
        {
            Suma= Suma + (Math.abs((float)restaPi.get(i+1) - (float)restaPi.get(i)));
            System.out.println("Sumaaaaaaaa+"+Suma);
        }
        Jitta = Suma/restaPi.size()-1; //resultado Jitta

        System.out.println("Jittaaaa"+Jitta);

    }
    // frecuencia fundamental
    public void frecuencia()
    {
        int i;
        float frec=0;
        float frec1=0;
        Log.e("Tamaño de restaPi", ""+restaPi.size());

        for (i=0; i<restaPi.size(); i++){
            frec =  1/(float)restaPi.get(i);
            frec1 = frec*1000000;
            datosFrecuencia.add(frec1);
            frecFund = frecFund + frec1;
            System.out.println("frecuencia"+frec1);
        }
        this.frecFund = frecFund/restaPi.size(); // resultado de frecuencia fundamental

    }

    public void par2()
    {
        int i;
        float Suma2=0f;


        for(i=0; i<restaPi.size(); i++)
        {
            Suma2= Suma2 + (float)restaPi.get(i);
            System.out.println("Suma"+Suma2);
        }

        TemPeriodos =(Suma2/restaPi.size());
        Jitt = (Jitta /TemPeriodos)*100; //resultado Jitt
        System.out.println("Jittttttt"+Jitt);
    }

    public void par3()
    {
        int k;
        float Suma3=0f;
        float sumaRAP;

        for (k = 1; k < restaPi.size()-1; k++)//
        {
            Suma3 = Suma3 +  Math.abs((float)restaPi.get(k) - ((float)restaPi.get(k) + ((float)restaPi.get(k-1) + (float)restaPi.get(k+1))/3));
        }

        sumaRAP = Suma3/restaPi.size()-1;
        RAP = (sumaRAP/TemPeriodos);//resultado rap *100
        System.out.println("RAPPPPPPP"+RAP);
    }

    // empieza el metodo para calcular las amplitudes del Shimmer
    // escoger todos los puntos positivos y negativos
    public void buscarPicosNeg(){

        ArrayList picosTemShim = new ArrayList();
        ArrayList posicionTemShim = new ArrayList();
        matrizShimPicosPositivos = new ArrayList();
        matrizShimPicosNegativos = new ArrayList();
        matrizShimPosPositivos = new ArrayList();
        matrizShimPosNegativos = new ArrayList();

        picosTemShim.add(picos.get(0));
        posicionTemShim.add(posicion.get(0));

        for (int x = 1; x<(picos.size()); x++) {
            if((float)picos.get(x)>0 && (float)picos.get(x-1)>0){
                picosTemShim.add(picos.get(x));
                posicionTemShim.add(posicion.get(x));
            }

            else if((float)picos.get(x)>0 && (float)picos.get(x-1)<0){
                matrizShimPicosNegativos.add(picosTemShim);
                matrizShimPosNegativos.add(posicionTemShim);
                picosTemShim = new ArrayList(); // se crea nuevo arreglo
                posicionTemShim =new ArrayList();

                picosTemShim.add(picos.get(x));
                posicionTemShim.add(posicion.get(x));

            }
            else if((float)picos.get(x)<0 && (float)picos.get(x-1)>0){
                matrizShimPicosPositivos.add(picosTemShim);
                matrizShimPosPositivos.add(posicionTemShim);
                picosTemShim = new ArrayList(); // se crea nuevo arreglo
                posicionTemShim =new ArrayList();

                picosTemShim.add(picos.get(x));
                posicionTemShim.add(posicion.get(x));

            }

            else{
                picosTemShim.add(picos.get(x));
                posicionTemShim.add(posicion.get(x));
            }
        }
        System.out.println("primero  "+matrizShimPicosNegativos.size());
        System.out.println("segundo  "+matrizShimPosNegativos.size());
        System.out.println("tercero  "+matrizShimPosPositivos.size());
        System.out.println("cuarto  "+matrizShimPicosPositivos.size());
    }

    public void amplitudes(){

        float Resta = 0;
        float Resta2= 0;

        Amp= new ArrayList();
        for (int i = 0; i<posicionesMax.size()-1; i++) {
            Amp.add(arreglo[(int)posicionesMax.get(i)]-arreglo[(int)posicionesMin.get(i)]);
            int x= Integer.parseInt(posicionesMax.get(i).toString());
            System.out.println("Amplitttttt"+posicionesMax.get(i).toString());
            System.out.println("Ampli"+Amp);
            System.out.println("otrooooo"+arreglo[(int)posicionesMax.get(i)]);
            System.out.println("esteeeee"+arreglo[(int)posicionesMin.get(i)]);

        }

        for (int i= 0; i<Amp.size()-1; i++){
            Resta= Resta + (Math.abs((float)Amp.get(i) - (float)Amp.get(i+1)));
            System.out.println("Resta"+Resta);
        }
        ShimArriba = Resta/Amp.size()-1;

        for (int i=0; i<Amp.size();i++){
            Resta2 = Resta2 + (float)Amp.get(i);
        }
        Resta22=Resta2/Amp.size();

        Shimm = (ShimArriba/Resta2)*100;
        System.out.println("Shimm"+Shimm);
    }

    public void apq33()
    {
        int k;
        float Suma33=0f;
        float sumaApq33;

        for (k = 1; k < Amp.size()-1; k++)//
        {
            Suma33 = Suma33 +  Math.abs((float)Amp.get(k) - ((float)Amp.get(k) + ((float)Amp.get(k-1) + (float)Amp.get(k+1))/3));
        }

        sumaApq33 = Suma33/Amp.size()-1;
        apq3 = (sumaApq33/Resta22)*100;//resultado rap
        System.out.println("apq3"+apq3);
    }

    // tiempo maximo de fonacion

    public int ultimospicos()
    {
        int ultimop= (int)posicion.get(posicion.size()-1);
        System.out.println("uluuuu"+ultimop);

        return ultimop;

    }
}
