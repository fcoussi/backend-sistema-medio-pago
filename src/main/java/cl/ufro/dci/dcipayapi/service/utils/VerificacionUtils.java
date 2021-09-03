package cl.ufro.dci.dcipayapi.service.utils;

import java.math.*;
import java.util.Random;


public class VerificacionUtils {
    private int primo;
    private int base;
    private int numSecretoA;
    private int numSecretoB;
    private int cifradoA;
    private int cifradoB;
    private int claveCompartidaA;
    private int claveCompartidaB;
    private boolean valido=false;

    public void generarLlavePublica() {
        generarPrimo();
        generarBase();
        }

    public void generarNumSecretoA(){
        boolean esMenor = false;
        Random rnum = new Random();
        while(esMenor==false) {
            this.numSecretoA = rnum.nextInt();
            if(this.numSecretoA<this.primo){
                esMenor=true;
            }
        }
    }

    public void generarNumSecretoB(){
        boolean esMenor = false;
        Random rnum = new Random();
        while(esMenor==false) {
            this.numSecretoB = rnum.nextInt();
            if(this.numSecretoB<this.primo){
                esMenor=true;
            }
        }
    }


    private void generarPrimo() {
        boolean esPrimo = false;
        Random rnum = new Random();
        int contador = 0;
        while (esPrimo == false) {
            this.primo = rnum.nextInt();
            for (int i = 1; i <= this.primo; i++) {
                if (this.primo % i == 0) {
                    contador++;

                }
            }
            if (contador == 2) {
                esPrimo = true;
            } else {
                contador = 0;
            }
        }

    }

    private void generarBase(){
        boolean esMenor = false;
        Random rnum = new Random();
        while(esMenor==false) {
            this.base = rnum.nextInt();
            if(this.base<this.primo){
                esMenor=true;
            }
        }
    }

    public void generarCifradoA(){
        cifradoA = (int) (Math.pow(base,numSecretoA)%primo);
    }
    public void generarCifradoB(){
        cifradoB = (int) (Math.pow(base,numSecretoB)%primo);
    }
    public void descifrar(){
        claveCompartidaA= (int) Math.pow(cifradoB,numSecretoA)%primo;
        claveCompartidaB= (int) Math.pow(cifradoA,numSecretoB)%primo;
    }

    public int getCifradoA() {
        return cifradoA;
    }

    public void setCifradoA(int cifradoA) {
        this.cifradoA = cifradoA;
    }

    public int getCifradoB() {
        return cifradoB;
    }

    public void setCifradoB(int cifradoB) {
        this.cifradoB = cifradoB;
    }

    public int getClaveCompartidaA() {
        return claveCompartidaA;
    }

    public void setClaveCompartidaA(int claveCompartidaA) {
        this.claveCompartidaA = claveCompartidaA;
    }

    public int getClaveCompartidaB() {
        return claveCompartidaB;
    }

    public void setClaveCompartidaB(int claveCompartidaB) {
        this.claveCompartidaB = claveCompartidaB;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido() {
        if(claveCompartidaA==claveCompartidaB){
            this.valido = true;
        }

    }

    public int getPrimo() {
        return primo;
    }

    public void setPrimo(int primo) {
        this.primo = primo;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getNumSecretoA() {
        return numSecretoA;
    }

    public void setNumSecretoA(int numSecretoA) {
        this.numSecretoA = numSecretoA;
    }

    public int getNumSecretoB() {
        return numSecretoB;
    }

    public void setNumSecretoB(int numSecretoB) {
        this.numSecretoB = numSecretoB;
    }


    public String llavePublicaString() {
        return
                 primo +"|"+ base;
    }
    public String llavePrivadaAString() {
        return
                ""+numSecretoA+"";
    }
    public String llavePrivadaBString() {
        return
                ""+numSecretoB+"";
    }
}
