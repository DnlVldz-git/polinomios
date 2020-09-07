/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

/**
 *
 * @author macbook
 */
import java.util.Scanner;

public class main {

    public static void main(String[] args){
        lista listita = new lista();
        polinomio inicio = new polinomio();
        polinomio inicio2 = new polinomio();
        inicio2 =null;
        inicio = null;

        Scanner leer = new Scanner(System.in);
        char resp,resp2;


        System.out.println("Inserte un monomio");       //SE INTRODUCE MONOMIO POR MONOMIO
        inicio = listita.insertar_monomio(inicio);
        do{
            System.out.println("\nDesea insertar otro?(Y/N)");
            resp = leer.next().charAt(0);
            if(resp == 'N'|| resp=='n') break;
            inicio = listita.insertar_monomio(inicio);
        }while(resp == 'Y'||resp == 'y');



        listita.imprimir_poli(inicio);

        inicio = listita.simplificar(inicio);       //AQUÍ SE SIMPLIFICA

        System.out.println("\nSimplificado: ");
        inicio = listita.invertir(inicio);
        listita.imprimir_poli(inicio);

        System.out.println("\nDesea hacer una suma(S) o resta(R) o multiplicación(M)  o salir(E)?");
        resp2 = leer.next().charAt(0);          //INTERFAZ DE OPCIONES
        switch(resp2){
            case 'S' -> {               //AQUÍ HACE LA SUMA
                inicio2 = listita.insertar_monomio(inicio2);
                do{

                    System.out.println("\nDesea insertar otro?(Y/N)");
                    resp = leer.next().charAt(0);
                    if(resp == 'N'|| resp=='n') break;
                    inicio2 = listita.insertar_monomio(inicio2);
                }while(resp == 'Y'||resp == 'y');

                inicio = listita.suma(inicio, inicio2);
                System.out.println("\nLa suma es:");

                listita.imprimir_poli(inicio);
            }
            case 'R' -> {           //AQUI HACE LA RESTA
                inicio2 = listita.insertar_monomio(inicio2);
                do{
                    System.out.println("\nDesea insertar otro?(Y/N)");
                    resp = leer.next().charAt(0);
                    if(resp == 'N'|| resp=='n') break;
                    inicio2 = listita.insertar_monomio(inicio2);
                }while(resp == 'Y'||resp == 'y');

                inicio = listita.resta(inicio, inicio2);
                System.out.println("\nLa resta es:");
                listita.imprimir_poli(inicio);
            }
            case 'M' -> {           //AQUÍ SE HACE LA MULTIPLICACIÓN
                inicio2 = listita.insertar_monomio(inicio2);

                do{
                    System.out.println("\nDesea insertar otro?(Y/N)");
                    resp = leer.next().charAt(0);
                    if(resp == 'N'|| resp=='n') break;
                    inicio2 = listita.insertar_monomio(inicio2);
                }while(resp == 'Y'||resp == 'y');


                int grande=listita.comparar(inicio, inicio2);
                switch(grande){
                    case 1:
                        inicio = listita.multiplicacion(inicio2, inicio);
                     break;
                    case 2:
                        inicio = listita.multiplicacion(inicio, inicio2);
                    break;
                }

                System.out.println("\nLa multiplicación es:");
                listita.imprimir_poli(inicio);

            }
            case 'D' -> {
                inicio2 = listita.insertar_monomio(inicio2);
                do{
                    System.out.println("\nDesea insertar otro?(Y/N)");
                    resp = leer.next().charAt(0);
                    if(resp == 'N'|| resp=='n') break;
                    inicio2 = listita.insertar_monomio(inicio2);
                }while(resp == 'Y'||resp == 'y');


                inicio = listita.division(inicio, inicio2);
                listita.imprimir_poli(inicio);
                break;
            }
            case 'E' -> {
                return;
            }
        }

    }
}
class monomio {
    float coeficiente;
    char literal;
    int exponente;

}
class polinomio {
    monomio datos;
    polinomio siguiente;
}
class lista {



    public polinomio insertar_monomio(polinomio inicio){
        monomio nuevo_mon = new monomio();
        polinomio nuevo_pol = new polinomio();
        polinomio aux = inicio;
        Scanner leer = new Scanner(System.in);
        System.out.println("\nIntroduzca el coeficiente del monomio");
        nuevo_mon.coeficiente = leer.nextFloat();
        System.out.println("Introduzca el literal del monomio");
        nuevo_mon.literal = leer.next().charAt(0);
        System.out.println("Introduzca el exponente del monomio");
        nuevo_mon.exponente = leer.nextInt();
        System.out.println("El monomio es: \t");
        System.out.print(nuevo_mon.coeficiente);
        System.out.print(nuevo_mon.literal);
        if(nuevo_mon.exponente == 0){
            System.out.print("\n");
        }else{
            System.out.print("^");
            System.out.print(nuevo_mon.exponente);
        }
        nuevo_pol.datos = nuevo_mon;

        if(inicio == null){
            inicio = nuevo_pol;
        } else{
            while(aux.siguiente!=null){
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo_pol;
        }
        return inicio;
    }

    public polinomio simplificar(polinomio inicio){
        if(inicio == null){
            return inicio;
        }
        inicio = ordenar(inicio);
        polinomio aux = inicio,ant = null;
        polinomio inicio2 = new polinomio();
        inicio2 = inicio;
        int bandera = 0,bandera_2;
        polinomio aux2 = inicio2, ant2=null;
        float suma;

        while(aux != null){
            ant = aux;
            aux = aux.siguiente;
            suma=0;
            bandera++;
            bandera_2=0;
            do{

                if(ant.siguiente!=null&&ant.datos.exponente!=ant.siguiente.datos.exponente){
                    suma = suma+ant.datos.coeficiente;
                    break;
                }
                if(ant.siguiente==null){
                    suma = suma+ant.datos.coeficiente;
                    break;
                }
                if(bandera_2==0){
                   suma=suma+ant.datos.coeficiente;
                }


                if(aux.siguiente !=null&&aux.siguiente.datos.exponente!=aux.datos.exponente){
                    suma = suma+aux.datos.coeficiente;
                    ant = aux;
                    aux = aux.siguiente;
                    break;
                }
                suma=suma+aux.datos.coeficiente;
                ant = aux;
                aux = aux.siguiente;
                bandera_2++;
            } while(aux != null&&ant.datos.exponente==aux.datos.exponente);
            if(bandera ==1){
                inicio = ant;
            }
            aux2.siguiente = ant;
            aux2 = aux2.siguiente;
            aux2.datos.coeficiente=suma;
            aux2.datos.literal = ant.datos.literal;
            aux2.datos.exponente= ant.datos.exponente;

            aux2.siguiente = null;
        }


        return inicio;
    }
    public polinomio ordenar(polinomio inicio){
        if(inicio == null||inicio.siguiente == null){
            return inicio;
        }
        polinomio temp = inicio, lento = inicio, rapido = inicio;

        while(rapido != null&& rapido.siguiente != null){
            temp = lento;
            lento = lento.siguiente;
            rapido = rapido.siguiente.siguiente;
        }
        temp.siguiente = null;

        polinomio izquierda = ordenar(inicio);
        polinomio derecha = ordenar(lento);

        return unir(izquierda, derecha);

    }
    public polinomio unir(polinomio l1, polinomio l2){
        polinomio ordenado_temp = new polinomio();

        polinomio nodo_actual = ordenado_temp;

        while(l1 != null && l2 != null){
            if(l1.datos.exponente < l2.datos.exponente){
                nodo_actual.siguiente = l1;
                l1 = l1.siguiente;
            } else {
                nodo_actual.siguiente = l2;
                l2 = l2.siguiente;
            }
            nodo_actual = nodo_actual.siguiente;
        }
        if(l1 != null){
            nodo_actual.siguiente = l1;
            l1 = l1.siguiente;
        }
        if(l2 != null){
            nodo_actual.siguiente = l2;
            l2= l2.siguiente;
        }
        return ordenado_temp.siguiente;

    }

    public polinomio invertir(polinomio inicio){
        if(inicio == null){
            return inicio;
        }
        polinomio aux = inicio, ant = null;
        polinomio previo = null;
        while (aux != null){
            ant = aux;
            aux = aux.siguiente;
            ant.siguiente = previo;
            previo = ant;




        }
        inicio = ant;


        return inicio;
    }
    public polinomio division(polinomio inicio, polinomio inicio2){
        if(inicio == null){
            return inicio;
        }else if(inicio2==null){
            return inicio2;
        }
        polinomio aux = inicio, aux2 = inicio2;
        float divisor, dividendo, div;
        polinomio resta = new polinomio();

        polinomio inicio3 = new polinomio();
        polinomio aux3 = inicio3;
        int bandera = 0;
        do{
            bandera++;
            int res_expo = aux.datos.exponente-aux2.datos.exponente;
            float division = aux.datos.coeficiente/aux2.datos.coeficiente;

            monomio nuevo  = new monomio ();
            polinomio nuevo_p = new polinomio();
            polinomio res = new polinomio();

            if(aux.datos.coeficiente%aux2.datos.coeficiente!=0){
                dividendo = aux.datos.coeficiente;
                divisor = aux2.datos.coeficiente;
                nuevo.coeficiente= dividendo;
                nuevo.exponente = res_expo;
                nuevo.literal = aux.datos.literal;
                nuevo_p.datos = nuevo;
                res = multiplicacion(nuevo_p, aux2);
                res.datos.coeficiente=res.datos.coeficiente /divisor;
                resta= resta(aux,res);
                quitar_cero(resta);
                aux=resta;
                continue;
            }
            nuevo.coeficiente= division;
            nuevo.exponente=res_expo;
            if(res_expo==0){
                nuevo.literal=0;
            }else{
                nuevo.literal = aux.datos.literal;
            }
            if(bandera ==1){
                inicio3=nuevo_p;
                nuevo_p.datos=nuevo;
                aux=inicio3;
            }else{
                 aux3.siguiente = nuevo_p;
                 nuevo_p.datos=nuevo;
                 aux3=nuevo_p;
            }


            res = multiplicacion(nuevo_p, aux2);

            resta=resta(aux, res);
            resta=quitar_cero(resta);
            aux=resta;
        }while(resta!=null&&resta.datos.literal!=0);
        return inicio3;
    }
    public polinomio quitar_cero(polinomio inicio){
        if(inicio == null){
            return inicio;
        }
        polinomio aux = inicio, ant = null;
        if(inicio.datos.coeficiente == 0){
            inicio=null;
        }
        while(aux!=null){
            ant = aux;
            aux=aux.siguiente;
            if(aux!=null&&aux.datos.coeficiente==0){
                ant.siguiente = aux.siguiente;
            }
            if(aux==null&&ant.datos.coeficiente==0){
                ant.siguiente = null;
            }
        }

        return inicio;
    }


    public polinomio suma(polinomio inicio, polinomio inicio2){
        if (inicio == null){
            return inicio;
        }else if(inicio2==null){
            return inicio2;
        }
        polinomio aux = inicio;
        while(aux.siguiente!=null){
            aux=aux.siguiente;
        }
        aux.siguiente = inicio2;





        return inicio;
    }
    public polinomio resta(polinomio inicio, polinomio inicio2){
        if (inicio == null){
            return inicio;
        }else if(inicio2==null){
            return inicio2;
        }
        polinomio aux = inicio;
        while(aux.siguiente!=null){
            aux=aux.siguiente;
        }
        aux.siguiente = inicio2;
        aux = aux.siguiente;
        while(aux!=null){
            aux.datos.coeficiente=aux.datos.coeficiente*-1;
            aux=aux.siguiente;
        }
        inicio = simplificar(inicio);
        return inicio;
    }
    public polinomio multiplicacion(polinomio inicio, polinomio inicio2){
        if (inicio == null){
            return inicio;
        }else if(inicio2==null){
            return inicio2;
        }
        float numero_coef ;
        int numero_exp ;
        int bandera = 0;
        polinomio aux = inicio;
        polinomio inicio3 = new polinomio();
        polinomio aux3 = inicio3;
        polinomio aux2;
        while(aux!=null){
            aux2=inicio2;
            numero_coef =0;
            numero_exp=0;
            bandera =0;
            while(aux2!=null){
                bandera++;
                numero_coef = aux.datos.coeficiente*aux2.datos.coeficiente;
                numero_exp= aux.datos.exponente+aux2.datos.exponente;

                if(bandera==1){
                    monomio nuevo = new monomio();
                    nuevo.coeficiente=numero_coef;
                    nuevo.exponente=numero_exp;
                    nuevo.literal=aux.datos.literal;
                    inicio3.datos = nuevo;
                    aux3 = inicio3;
                    aux2 = aux2.siguiente;
                    continue;
                }
                monomio nuevo = new monomio();
                polinomio nuevo_1 = new polinomio();
                aux3.siguiente = nuevo_1;
                nuevo.coeficiente=numero_coef;
                nuevo.exponente=numero_exp;
                nuevo.literal = aux.datos.literal;
                nuevo_1.datos = nuevo;
                aux3=nuevo_1;
                aux3.siguiente=null;
                aux2=aux2.siguiente;
            }
            aux = aux.siguiente;

        }
        if(bandera==1){
            return inicio3;
        }


        inicio3 = simplificar(inicio3);



    return inicio3;
    }

    public int comparar(polinomio inicio, polinomio inicio2){
        if(inicio == null){
            return 0;
        }else if(inicio2==null){
            return 0;
        }
        polinomio aux = inicio, aux2 = inicio2;
        int contador1= 0, contador2 =0;
        while (aux!= null){
            contador1 ++;
            aux = aux.siguiente;

        }
        while (aux2!= null){
            contador2 ++;
            aux2 = aux2.siguiente;

        }
        int retorno = (contador1>contador2)?contador1:contador2;

        return retorno;
    }




    public polinomio imprimir_poli(polinomio inicio){
        if(inicio == null){
            return inicio;
        }
        polinomio aux = inicio;
        int bandera = 0;
        System.out.println("\n");
        while (aux != null)
        {
            if(aux.datos.coeficiente >0 && bandera != 0){
               System.out.print("+");
            }
            System.out.print(aux.datos.coeficiente );
            System.out.print(aux.datos.literal);
            if(aux.datos.exponente == 0){

            }else{
                System.out.print("^");
                System.out.print( aux.datos.exponente);
            }
            aux = aux.siguiente;
            bandera ++;

        }
        return inicio;
    }

}
