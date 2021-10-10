package edu.ub.prog2.RuanoEstherTorquetNuria.controlador;


import edu.ub.prog2.RuanoEstherTorquetNuria.model.Audio;
import edu.ub.prog2.RuanoEstherTorquetNuria.model.FitxerReproduible;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.ReproductorBasic;
import java.io.File;
import java.io.Serializable;

/**
 * @author Esther Ruano Hortoneda
 * @author Nuria Torquet Luna
 * 
 * Classe per a gestionar la reproducció d'un fitxer. Hereda de ReproductorBasic
 * i utilitza els mètodes ja implementats en la classe mare stop, play, resume,
 * pause... Per al mètode saltaReproducció i el control de les excepcions requereix
 * d'un objecte del tipus EscoltadorReproduccio.
 */
 public class Reproductor extends ReproductorBasic implements Serializable {
     EscoltadorReproduccio escoltador;
    
     /**
      * Constructor de la classe, passem per paràmetres un objecte EscoltadorReproduccio
      * per assignar el nostre attribut i passem el path del VLC a la classe mare.
      * @param e 
      */
     public Reproductor(EscoltadorReproduccio e) {
        super("C:\\Program Files\\VideoLAN\\VLC", e);
        escoltador = e;
    }

    
    
    /**
     * Crida al metode de ReproductorBasic que reprodueix un fitxer. Aquest llenca
     * una AplicacioException i, per tant, reprodueix() també.
     * 
     * @param fitxer
     * @throws edu.ub.prog2.utils.AplicacioException
     */
    public void reprodueix(FitxerReproduible fitxer) throws AplicacioException {
        super.play(fitxer);
    }
    
    /**
     * Crida al metode de ReproductorBasic que reprodueix dos fitxers. Aquest llenca
     * una AplicacioException i, per tant, reprodueix() també.
     * Com que Audio hereda de File, no cal fer un cast explícit a l'hora de cridar 
     * el mètode.
     * @param audio
     * @param fitxerImatge
      * @throws edu.ub.prog2.utils.AplicacioException
      */
     public void reprodueix(Audio audio, File fitxerImatge) throws AplicacioException {
         super.play(audio, fitxerImatge);
    }
    
    
    /**
     * Crida al metode de ReproductorBasic que reempren la reproduccio en cas d'haverla pausat
     * @throws edu.ub.prog2.utils.AplicacioException
     */
    public void reemprenReproduccio() throws AplicacioException {
        if (escoltador.isReproduint()) {
            throw new AplicacioException("Ja s'esta reproduint un fitxer");
        }
        super.resume();
        escoltador.setReproduint(true);
    }
    

    /**
     * Crida al metode de ReproductorBasic que pausa la reproduccio 
     * @throws edu.ub.prog2.utils.AplicacioException
     */
    public void pausaReproduccio() throws AplicacioException{
        if (!escoltador.isReproduint()) {
            throw new AplicacioException("No s'estava reproduint cap fitxer");
        }
        super.pause();
        escoltador.setReproduint(false);
    }

    /**
     * Crida al metode de ReproductorBasic que atura la reproduccio 
     * @throws edu.ub.prog2.utils.AplicacioException
     */
    public void aturaReproduccio() throws AplicacioException{
        if (!escoltador.isReproduint()) {
            throw new AplicacioException("No s'estava reproduint cap fitxer");
        }
        super.stop();
        escoltador.setReproduint(false);
    }
    
    /**
     * Per saltar al següent fitxer, fem dues comprovacions: si hi ha un següent
     * fitxer per a poder reproduir i si s'està reproduint un fitxer en aquell moment.
     * En cas de complir-se totes dues premises, avancem al següent element
     * @throws AplicacioException 
     */
    public void saltaReproduccio() throws AplicacioException {
        if(!escoltador.isReproduint()){
            throw new AplicacioException("No s'estava reproduint cap fitxer per a poder saltar al següent");
        }
        else if(!escoltador.hasNext()){
            throw new AplicacioException("No hi han més fitxers per a poder reproduir");
        }
        escoltador.next();
    }
    


    
}
