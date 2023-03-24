/**
 * Projet : RAPPORT SUR LES METHODES DE TRI
 * @autor : DIALLO Mamadou Aliou
 * Date : 02/2023
 * Ce programme implemente un tableau d'Eléments qui utilise le tri à bulles, le tri dénombrement et rapide 
 * pour trier les clés de ses éléménts
 */

import java.util.Scanner;
import java.util.Random;
//import javax.lang.model.element.Element;

public class TabTriee {
    /*Attributs */
    private Element[] tab;
    private int taille;

    /* Operations Elementaires */
        /*OE tri à bulle */
    private int Affectation;
    private int Comparaison;
        /*OE tri rapide */
    private int qAffectation;
    private int qComparaison;
        /*OE tri par dénombrement */
    private int denoAffectation;
    private int denoComparaison;


    /**
     * Constructeur par defaut 
     */
    public TabTriee(){
        taille =0;
        tab = new Element[0];
    }
    /**
     * Constructeur  avec taille pour parametre, 
     * crée un tableau de taille t et initialise les élements à nul
     * @param taille
     */

    public TabTriee(int taille) {
        this.taille = taille;
        tab = new Element[taille];

        /* Initialisation */
        for (int i = 0; i < taille; i++) {
            tab[i] = new Element(0, 0);
        }
        Affectation =0;
        Comparaison =0;

        qAffectation=0;
        qComparaison =0;

        denoAffectation =0;
        denoComparaison=0;
    }

    /**
     * Constructeurs par recopie qui cree une copie d'un tableau identique a t
     * @param t
     */
    public TabTriee(TabTriee t){
        taille = t.taille;
        tab = new Element[taille];

        for (int i=0; i< taille; i++){
            tab[i] = t.tab[i];
        }
    }


    public int getTaille() {
        return taille;
    }

    
    /**
     * Methode qui permet à l'utilisateur de lire au clavier les clé et le contenu 
     */
    public void initLecture() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < taille; i++) {
            System.out.println("Entrer la clé : ");
            this.tab[i].setCle(sc.nextInt());

            System.out.println("Entrer le Contenu : ");
            this.tab[i].setContenu(sc.nextDouble());
        }
    }

    /** 
     * Methode qui permet de générer aleatoirement les clés et, le contenu contient l'indice du tableau
    */
    public void initAleatoire() {
        Random alea = new Random();
        for (int i = 0; i < getTaille(); i++) {
            tab[i].setCle(alea.nextInt(getTaille()));
            tab[i].setContenu(i);
            ;
        }
    }

    /* Affichage des Elements du tableau*/
    public String toString() {
        String res = "";
        for (int i = 0; i < getTaille(); i++) {
            res += tab[i].toString();
        }
        return res;
    }

    /**
    * Methodes qui permet de récupérer le Opérations élémentaires du tri à bulles
    * @return le nombre d'operations élémentaires
    */
    public int getOpE_bulles() {
        return (Affectation + Comparaison);
    }

    /**
     * Methodes qui permet de récupérer le Opérations élémentaires du tri par dénombement
     * @return le nombre d'operations élémentaires
     */
    public int getOpE_denomb() {
        return (denoAffectation + denoComparaison);
    }

    /**
     * Methodes qui permet de récupérer le Opérations élémentaires du tri
     * @return le nombre d'operations élémentaires
     */
    public int getOpE_qSort() {
        return (qAffectation + qComparaison);
    }


    /************** METHODES DE TRI **********************/
    /**
     * Methode de tri à bulle
     */
    public void triBulle() {
        int ind_echange = 0;
        int fin = getTaille() -1;
        boolean echange = true;
        Affectation+=2;

        while (fin > 0 && echange) {
            Comparaison++;
            echange = false;
            for (int j = 0; j < fin; j++) {

                if (tab[j].getCle() > tab[j + 1].getCle()) {
                    Comparaison++;
                    /*Echange */
                    Element temp = tab[j];
                    tab[j] = tab[j + 1];
                    tab[j + 1] = temp;
                    Affectation +=3;

                    echange = true;
                    ind_echange = j;
                    Affectation ++;  
                }
            }
            fin = ind_echange;
            Affectation ++;
        }
    }


    /* Tri rapide */
    /**
     * Methode de partition du tableau pour trier les éléments
     * par le TRI RAPIDE, qui prend comme pivot le premier élément du tableau
     * @param deb
     * @param fin
     * @return l'indice du pivot
     */
    public int partition(int deb, int fin){
        int pivot =deb;
        int i=deb+1;
        int j=fin;

        while(i < j){
            while(i<j && tab[i].getCle() <= tab[pivot].getCle())
            {
                qComparaison++;
                i++;
                qAffectation++;
            }

            while(i < j && tab[j].getCle() >= tab[pivot].getCle()){
                qComparaison++;
                j--;
                qAffectation++;
            }
            if(i<j){
                qComparaison++;
                Element temp = tab[i];
                tab[i] = tab[j];
                tab[j] = temp;
                qAffectation+=3;

                i++;
                j--;
                qAffectation+=2;
            }
        }

        /* Mettre le pivot à sa place */
        if((i==j) && tab[i].getCle() <= tab[pivot].getCle()){
            qComparaison+=2;
            pivot = i;
        }else {
            pivot = i-1;
        }
        qAffectation++;

        Element temp = tab[deb];
        tab[deb] = tab[pivot];
        tab[pivot] = temp;
        qAffectation+=3;

        return pivot;
    }


    /**
     * Methode de tri rapide Récurisive, qui utilise une méthode de partition
     * @param deb
     * @param fin
     */
    public void tri_rapide(int deb, int fin){
        if(deb < fin){
            qComparaison++;
            int ind = partition(deb, fin);
            qAffectation++;
            
            tri_rapide(deb, ind-1);
            tri_rapide(ind+ 1, fin);
        }
    }

    /**
     * Methode de tri par dénombrement qui s'effetue sans comparaison,
     *  en gardant l'ordre de placement des valeurs identiques.
     */

    public void tri_denom(){
        /* Recherche du max*/
        int max = tab[0].getCle();
        denoAffectation++;
        for(int i=0; i< taille; i++){
            if(tab[i].getCle() > max){
                denoComparaison++;
                max = tab[i].getCle();
                denoAffectation++;
            }
        }
        /* Creation et initialisation du tableau intermédiare */
        int[] c = new int[max +1];
        for(int i=0; i< c.length; i++){
            c[i] = 0;
        }

        for(int i=0; i< taille;  i++){
            c[tab[i].getCle()]++;
            denoAffectation++;

        }
        for (int i = 1; i < c.length; i++) {
            c[i] += + c[i-1];
            denoAffectation++;
        }
        Element[] B = new Element[taille];
        
        for (int i =B.length-1; i>=0; i--) {
            int pos = tab[i].getCle();
            B[c[pos] - 1] = tab[i];
            c[pos]--;
            denoAffectation+=3;
        }
        tab = B;
        denoAffectation++;

    }

}
