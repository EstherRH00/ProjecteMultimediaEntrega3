
package edu.ub.prog2.RuanoEstherTorquetNuria.model;

import edu.ub.prog2.RuanoEstherTorquetNuria.controlador.Reproductor;
import edu.ub.prog2.utils.AplicacioException;

/**
 * @author Esther Ruano Hortoneda
 * @author Nuria Torquet Luna
 *
 * Video hereta de la classe FitxerReproduible i s'encarrega d'especificar un tipus
 * de FitxerMultimedia concret. La classe consta dels atributs propis de la classe,
 * del constructor i d'un mètode, reproduir() que encara no està implementat.
 */
public class Video extends FitxerReproduible{
    /**
     * Com a atributs propis de la classe Audio disposem:
     * - int alcada i amplada, resolució espacial en píxels d’alçada x píxels d’amplada.
     * - float fps, esolució temporal en frames per segon.
     */
    private int alcada;
    private int amplada;
    private float fps;
    
    /**
     * Constructor de la classe Video
     * Atributs que enviem al constructor File
     * @param cami
     * Atributs que enviem al constructor FitxerMultimedia
     * @param nom //descripcio del fitxer
     * Atributs que enviem al constructor FitxerReproduible
     * @param codec
     * @param durada
     * @param r
     * atributs propis de la classe Video
     * @param alcada
     * @param amplada
     * @param fps
     *  
     */
    public Video(String cami, String nom, String codec, float durada, int alcada,
            int amplada, float fps, Reproductor r) {
        super(cami, nom, codec, durada, r);//enviats al constructor de FitxerReproduible
        this.alcada = alcada;
        this.amplada = amplada;
        this.fps = fps;
    }
    
    /**
     * Mètode que implementa el mètode reproduir() de la classe FitxerReproduible
     * Crida al metode reprodueix() de la classe Reproductor mitjançant la instància
     * d'aquesta classe que video té com atribut.
     * 
     * @throws edu.ub.prog2.utils.AplicacioException
     */
    @Override
    public void reproduir() throws AplicacioException {
        r.reprodueix(this);
    }
    
}
