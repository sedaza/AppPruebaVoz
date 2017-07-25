/**
 * Created by Windows 10 Pro on 11/07/2017.
 */

public class Algoritmos {
    float[] arreglo;
    ArrayList matrizPicos;
    ArrayList matrizPos;
    ArrayList picos;//declaracion
    ArrayList posicion;
    public float[] getArreglo() {
        return arreglo;
    }

    public float[] getArreglo1() {
        return arreglo1;
    }

    float [] arreglo1;
    //int[] arreglo1 = new int[arreglo.length];//arreglo vacio
    int Fs = 44100; //frecuencia de muestreo
    int N;//total de periodos

    public Algoritmos(ArrayList arr) {
        System.out.println("Areglooooooooooooooooooooo" +arr.size());
        this.arreglo = new float[arr.size()];
        for (int i = 0; i<arreglo.length; i++){
            arreglo[i] = Float.parseFloat(Short.toString((short)arr.get(i)));

        }
        System.out.println("Areglooooooooooooooooooooo" +arreglo.length);
    }

    private float maximos(float[] arr) {//maximos de cualquier arreglo arr
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

    private float[] normalizar(float[] arr) {//normaliza cualquier arreglo
        int i;
        float max = maximos(arr);
        System.out.println("ddddddeedd" +max);
        float [] nx = new float[arr.length];
        for (i = 0; i < arr.length; i++)
        {
            nx [i] = arr[i]/max;

        }
        return nx;
    }



    public void calcular() { // seleccionar los picos del arreglo

        picos=new ArrayList();//instanciacion
        posicion = new ArrayList();
        arreglo1= normalizar(arreglo);
//System.out.println("qqqqqqqqqqqqqqqqqqqqqq"+arreglo1.length);
        float umbral = 0.8f;
        float umbral2 = -0.6f;
        int k;
        for (k = 1; k < arreglo1.length-1; k++)//
        {
            // System.out.println("qqqqqqqqqqqqqqqqqqqqqq"+arreglo1[k]);
            if((arreglo1[k]>arreglo1[k-1] && arreglo1[k]>arreglo1[k+1] && arreglo1[k]>umbral) || (arreglo1[k]<arreglo1[k-1] && arreglo1[k]<arreglo1[k+1] &&  arreglo1[k]<umbral2))
            {

                System.out.println("aaaaaaaaaaaaaaaaaaaaaa" + arreglo1[k - 1]);
                System.out.println("qqqqqqqqqqqqqqqqqqqqqq" + arreglo1[k]);
                System.out.println("llllllllllllllllllllll" + arreglo1[k + 1]);

                picos.add(arreglo1[k]);
                posicion.add(k);
                // System.out.println("estosssssssss sonnnnnnnnnnnn"+ posicion);

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
            }
            else
            {
                matrizPicos.add(picosTem);
                picosTem = new ArrayList();
                matrizPos.add(posicionTem);
                posicionTem = new ArrayList();
                System.out.println("siiiiiiiiiiiiiiiii" + matrizPicos);
                System.out.println("nooooooooooooooooo" + matrizPos);
            }
        }

    }

    public void prueba (){


    }

}
