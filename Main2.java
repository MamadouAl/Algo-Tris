 public class Main2 {
    
    public static void main(String[] args) {
        int MAX_TAILLE = 1200*1000;
       // int taille = 1000*1000;
        for (int taille = 100000; taille < MAX_TAILLE; taille+=100000) {
                
            
            TabTriee tab0 = new TabTriee(taille);
            tab0.initAleatoire();
            TabTriee tab1 = new TabTriee(tab0); //utilisé dans tri_Rapide
            TabTriee tab2 = new TabTriee(tab0); //utilisé dans tri par denombrement

            /* TESTONS NOS METHODES DE TRI */

            System.out.println("APRES LE TRI RAPIDE :  **************************************** ");
            long t11 = System.currentTimeMillis();
            tab1.tri_rapide(0, tab1.getTaille() -1);
            long t12 = System.currentTimeMillis();
            //System.out.println(tab1);
            System.out.println("Le Tri Rapide a réalisé : "+tab1.getOpE_qSort()+" OpElementaires");
            System.out.println("[TEMPS]: tri Rapide : "+(t12-t11)+"ms pour TAILLE = "+taille);
            System.out.println();


            System.out.println("APRES LE TRI DENOMBREMENT :  **************************************** ");
            long t21 = System.currentTimeMillis();
            tab2.tri_denom();
            long t22 = System.currentTimeMillis();
            //System.out.println(tab2);
            System.out.println("Le Tri par Denombrement a réalisé : "+tab2.getOpE_denomb()+" OpElementaires");
            System.out.println("[TEMPS]: tri Denombrement : ["+(t22-t21)+"ms] pour TAILLE = "+taille);
            System.out.println();
    
         }
    }
 }