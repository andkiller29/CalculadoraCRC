package domain;

public class AlgoritmoCRC {

    public Integer[] mensajes;
    public Integer[] pGeneradores;
    public Integer[] r;
    public boolean estado;

    public AlgoritmoCRC() {
        //...
    }

    /**
     * Metodo para convertir un arreglo de tipo Integer a String
     */
    public String ArrayToString(Integer[] arreglo) {
        String dato = "";
        for (Integer numero : arreglo) {
            dato = dato + String.valueOf(numero);
        }
        return dato;
    }

    /**
     * Metodo utilizado para validar que los datos recibidos desde el mensaje y
     * pGerador sean validor de tipo 1 y 0
     */
    public boolean validador(String dato) {
        int largo = dato.length();
        boolean validador = false;
        for (int i = 0; i < largo; i++) {
            if (dato.charAt(i) == '0' || dato.charAt(i) == '1') {
                validador = true;
            } else {
                validador = false;
                break;
            }
        }
        return validador;
    }

    /**
     * Metodo para crear los arreglos en tiempo de ejecucion
     */
    public void crearArreglos(int tamanioMensaje, int tamnioPGeneradores) {
        this.mensajes = new Integer[tamanioMensaje];
        this.pGeneradores = new Integer[tamnioPGeneradores];
        this.r = new Integer[tamnioPGeneradores - 1];
    }

    /**
     * Metodo encargado de comvertir el polinomio de String a un arreglo de
     * numeros para poder realizar los respectivos calculos se utilizara para
     * obtener los []mensaje y []pGenerador
     */
    public void convertirStringToArray(String mensaje, String generador) {
        int largoMensaje = mensaje.length();
        int LargoGenerador = generador.length();
        for (int i = 0; i < largoMensaje; i++) {
            this.mensajes[i] = Integer.parseInt(String.valueOf(mensaje.charAt(i)));
        }
        for (int i = 0; i < LargoGenerador; i++) {
            this.pGeneradores[i] = Integer.parseInt(String.valueOf(generador.charAt(i)));
        }
        obtenerR();
    }

    //Segundo metodo para conver de un String en arreglo
    public Integer[] convertirStringToArray(String mensaje) {
        int largoMensaje = mensaje.length();
        Integer[] arregloEnteros = new Integer[largoMensaje];
        for (int i = 0; i < largoMensaje; i++) {
            arregloEnteros[i] = Integer.parseInt(String.valueOf(mensaje.charAt(i)));
        }
        return arregloEnteros;
    }

    /**
     * Metodo para obtener el tamaño de r sabiendo que una secuentacion de 0 con
     * un tamaño igual al generador - 1
     */
    public void obtenerR() {
        int largo = this.r.length;
        for (int i = 0; i < largo; i++) {
            this.r[i] = 0;
        }
    }

    /**
     * Metodo utilizando para unir para unir dos arreglos en este caso
     * []mensajes con []r y luego []mensajes con el crc este metodo une el
     * arreglo de mayor tamañao primero y despues el de menor tamaño
     */
    public Integer[] combinarArreglos(Integer[] arreglo1, Integer[] arreglo2) {
        int contador = 0;
        int largoArreglo1 = arreglo1.length;
        int largoNuevoArreglo = largoArreglo1 + arreglo2.length;
        Integer[] nuevoArreglo = new Integer[largoNuevoArreglo];
        for (int i = 0; i < largoNuevoArreglo; i++) {
            if (i < largoArreglo1) {
                nuevoArreglo[i] = arreglo1[i];
            } else {
                nuevoArreglo[i] = arreglo2[contador++];
            }
        }

        return nuevoArreglo;
    }

    /**
     * Metodo usado para realizar la operacion XOR cuando se este ejecutando el
     * algoritmo CRC se puede usaar de dos formas una usando el operador ^ o
     * devolviendo el valor dependiendo de una condicion
     */
//    public int operacionXor(int a, int b) {
//        return a ^ b;
//    }
//    public int operacionXor(int a, int b) {
//        int resultado = 1;
//        resultado = (a == 0 && b == 0) || (a == 1 && b == 1) ? resultado = 0 : resultado;
//        return resultado;
//    }
    /**
     * Metodo que contiene la logica para el calculo del CRC y que devuelve el
     * arreglo con el crc
     */
    public Integer[] algoritmoCRC(Integer[] arreglo1) {
        /**
         * Arreglo que almacenara los valores del resto
         */
        Integer[] crc;
        /**
         * Se hace una llamada al metodo combinar dos arreglos se obtiene los
         * largos del nuevo arreglo del pgeneradores[] se les asigna el tamaño a
         * los arreglos auxiliar y resto
         */
        Integer[] dividendo = arreglo1;
        int largoDividendo = dividendo.length;
        int largoDivisor = this.pGeneradores.length;
        int largoR = this.r.length;
        crc = new Integer[largoDivisor - 1];
        int largoCRC = crc.length;

        /**
         * En el primer for se reccore el largo del del dividendo menos el
         * tamaño de r que es los 0 que se le agregan al mensaje en el
         * condicional se valida que se empienze desde la posicion que contenga
         * 1 en el segundo for recorre el tamaño de divisor osea el polinomio
         * generador despues por cada poscion que se recorra del largo del
         * dividendo sumado a la poscion de recorido de divisor se va realiar la
         * operacion xor con el divisor en este caso el arreglo pGenradores []
         * est operacion se realiza con el operador ^ que corresponde operacion
         * xor bit a bit
         */
        for (int i = 0; i < largoDividendo - largoR; i++) {
            if (dividendo[i] == 1) {
                for (int j = 0; j < largoDivisor; j++) {
                    dividendo[i + j] ^= pGeneradores[j];
                }
            }
        }

        /**
         * En este el dato del crc se va igresar al arreglo crc[] con el fin de
         * eliminar todos los ceros en la izquierda y que solo queden los datos
         * correspondientes al crc si todos los datos son 0 del dividendo
         * entonces llena el crc con ceros para la respectiva validacion en el receptor
         */
//        for (int j = 0; j < largoDividendo; j++) {
//            if (dividendo[j] != 0) {
//                for (int k = 0; k < largoCRC; k++) {
//                    crc[k] = dividendo[k + j];
//                }
//                break;
//            } else {
//                for (int k = 0; k < largoCRC; k++) {
//                    crc[k] = 0;
//                }
//            }
//        }

        for (int j = 0; j < largoCRC; j++) {
            int asignar = (largoDividendo - largoCRC) + j;
            crc[j] = dividendo[asignar];
        }
        return crc;
    }
}
