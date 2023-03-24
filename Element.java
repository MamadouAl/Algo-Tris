
public class Element {

    /* Attributs */
    private int cle;
    private double contenu;

    public Element() {
        this.cle = 0;
        this.contenu = 0;
    }
/**
 * Constructeur qui crée un Element avec la clé et le contenu en paramètre
 * @param cle
 * @param c
 */
    public Element (int cle, double c){
        this.cle = cle;
        contenu = c;
    }

    /* Accesseurs */
    public int getCle() {
        return cle;
    }

    public double getContenu() {
        return contenu;
    }
    /* Modifieurs */
    public void setCle(int c){
        cle = c;
    }

    public void setContenu(double c){
        contenu = c;
    }

    public String toString() {
        String res = new String("");
        res += getCle() +":->["+getContenu()+"] ";
        return res;
    }
}