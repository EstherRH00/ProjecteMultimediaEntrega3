
package edu.ub.prog2.RuanoEstherTorquetNuria.model;



import java.util.ArrayList;

/**
 * @author Esther Ruano Hortoneda
 * @author Nuria Torquet Luna
 *
 * Classe que hereda de CarpetaFitxers. Es poden instanciar tants objectes AlbumFitxers
 * com es vulgui. Cada un d'ells conté un objecte del tipus CarpetaFitxers(ArrayList 
 * FitxerMultimèdia) a més d'un títol que l'identifica. Aquest és únic per a cada obj.
 * 
 */
public class AlbumFitxersMultimedia extends CarpetaFitxers{
    /**
     * Compta amb un identificador: el seu títol, i un nombre màxim de fitxers
     * que pot emmagatzemar en cas d'escollir el constructor per defecte.
     */
    protected static final int MAX_FITXERS_ALBUM = 10;
    private String titol;
    
    /**
     * Constructor de la classe AlbumFitxersMultimedia. En ell inicialitzem el 
     * títol de l'àlbum i el nombre d'elements que contindrà(màxim nombre de fitxers
     * ve predeterminat)
     * @param titol
     */
    public AlbumFitxersMultimedia(String titol){
        super(MAX_FITXERS_ALBUM);
        this.titol = titol;
        
    }
    /**
     * Constructor de la classe amb paràmetres, el nombre de fitxers els escull 
     * l'usuari.
     * @param titol
     * @param fitxers
     */
    public AlbumFitxersMultimedia(String titol, int fitxers){
        super(fitxers);
        this.titol = titol;
        
    }
    
    /**
     * Getter de l'atribut carpeta de la classe mare CarpetaFitxers,
     * @return getCarpeta()
     */
    public ArrayList<FitxerMultimedia> getAlbum() {
        return getCarpeta();
    }
    
    /**
     * Getter del títol de l'àlbum
     * @return titol
     */
    public String getTitol(){
        return titol;
    }
    
    /**
     * Mètode toString que sobreescriu el que ja es trobava en CarpetaFitxers.
     * Retornem únicament el títol de l'àlbum.
     * @return this.getTitol()
     */

    @Override
    public String toString(){
        return this.getTitol();
    }
    
    
    
           
    
}
