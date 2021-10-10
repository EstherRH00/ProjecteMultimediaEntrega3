
package edu.ub.prog2.RuanoEstherTorquetNuria.model;

import edu.ub.prog2.RuanoEstherTorquetNuria.controlador.Reproductor;
import edu.ub.prog2.utils.AplicacioException;

import java.io.File;


/**
 * @author Esther Ruano Hortoneda
 * @author Nuria Torquet Luna
 *
 * Audio hereta de la classe FitxerReproduible i s'encarrega d'especificar un tipus
 * de FitxerMultimedia concret. La classe consta dels atributs propis de la classe,
 * del constructor i d'un mètode, reproduir() que encara no està implementat.
 */

public class Audio extends FitxerReproduible {
    /**
     * Com a atributs propis de la classe Audio disposem:
     * - File fitxerImatge, variable de referència que mostrarà la imatge de la
     * caràtula o una per defecte.
     * - int kbps, emmagatzema la qualitat de l'àudio expressada en kilobits per segon.
     */
    private File fitxerImatge;
    private int kbps;

    /**
     * Constructor de la classe Audio.
     * Atributs que enviem al constructor File
     * @param cami -->que correspondria al path
     * Atributs que enviem al constructor FitxerMultimedia
     * @param nom -->descripcio del fitxer
     * Atributs que enviem al constructor FitxerReproduible
     * @param codec
     * @param durada
     * @param r
     * Atributs pròpis de la classe Audio
     * @param kbps
     * @param fitxerImatge
     * Si el fitxerImatge no existeix al disc, li associem un fons negre que tenim 
     * desat a la carpeta del projecte
     */
    public Audio(String cami, File fitxerImatge, String nom, String codec, 
            float durada, int kbps, Reproductor r) {
        //nom correspon a la descripcio del fitxer
        super(cami, nom, codec, durada, r); //el constructor de FitxerReproduible crida al de FitxerMultimedia
        if(fitxerImatge.exists()){
            this.fitxerImatge = fitxerImatge;
        } else {
            File fonsNegre = new File("fonsNegre.png");//si no troba imatge, carreguem fons negre
            this.fitxerImatge = fonsNegre;
        }
        this.kbps = kbps;
    }

    /**
     * Mètode que implementa el mètode reproduir() de la classe FitxerReproduible
     * Crida al metode reprodueix() de la classe Reproductor mitjançant la instància
     * d'aquesta classe que Àudio té com atribut.
     * 
     * @throws edu.ub.prog2.utils.AplicacioException
     */
    @Override
    public void reproduir() throws AplicacioException {
        r.reprodueix(this, fitxerImatge);
    }
    
}
