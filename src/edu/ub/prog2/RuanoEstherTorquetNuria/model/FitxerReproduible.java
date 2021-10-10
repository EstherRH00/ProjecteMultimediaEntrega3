

package edu.ub.prog2.RuanoEstherTorquetNuria.model;

import edu.ub.prog2.RuanoEstherTorquetNuria.controlador.Reproductor;
import edu.ub.prog2.utils.AplicacioException;

/**
 * @author Esther Ruano Hortoneda
 * @author Nuria Torquet Luna
 * Classe abstracta que ens permet especialitzar els diferents fitxers
 * introduits a la BibliotecaMultimedia. Aquests es poden dividir en:
 *  - Reproduïbles: vídeos i àudio
 *  - No Reproduïbles: imatges i txt
 * En aquesta pràctica només implementarem les classes per als fitxers reproduïbles
 */

public abstract class FitxerReproduible extends FitxerMultimedia{
    /**
     * Atribut privats que no tenia FitxerMultimedia i que comparteixen les 
     * classes Audio i Video. El codec(acrònim de code and decode) correspon al 
     * programa que utilitza el fitxer per a la seva compresió/descompresió.
     * La durada ja es sobreentén i també s'inicia una variable Reproductor.
     */
    protected String codec;
    protected float durada;
    protected transient Reproductor r;
            
    
    /**
     * Constructor de la classe FitxerReproduible. Es declara protected per a que
     * no es pugui cridar fora de l'herència d'aquesta classe(només per 
     * Audio i Video).
     * @param cami
     * @param descripcio
     * @param codec
     * @param durada
     * @param r 
     */
    protected FitxerReproduible(String cami, String descripcio, String codec, 
            float durada, Reproductor r) {
        super(cami, descripcio);//enviats al constructor de FitxerMultimedia
        this.codec = codec;
        this.durada = durada;
        this.r = r;
    }
    /**
     * Mètode abstracte(no permet implementació).
     * Implementat en les classes filles Audio i Video.
     * @throws edu.ub.prog2.utils.AplicacioException
     */
    public abstract void reproduir() throws AplicacioException;
    
    public void setReproductor(Reproductor r){
        this.r = r;
    }
}