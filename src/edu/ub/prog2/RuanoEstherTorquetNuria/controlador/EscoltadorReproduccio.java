package edu.ub.prog2.RuanoEstherTorquetNuria.controlador;

import edu.ub.prog2.RuanoEstherTorquetNuria.model.CarpetaFitxers;
import edu.ub.prog2.RuanoEstherTorquetNuria.model.FitxerReproduible;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.EscoltadorReproduccioBasic;


/**
 * @author Esther Ruano Hortoneda
 * @author Nuria Torquet Luna
 * 
 * Aquesta classe implementa els metodes abstractes d'EscoltadorReproduccioBasic
 * i a més té un mètode propi per iniciar la reproducció i un altre per comprovar
 * si s'està reproduint algun fitxer.
 */
public class EscoltadorReproduccio extends EscoltadorReproduccioBasic {
    /**
     * Els atributs de la classe són.
     * Variable de la classe CarpetaFitxers, on es troben els fitxers que s'han de
     * reproduir.
     * Array de booleans que controla quins arxius de CarpetaFitxers s'han 
     * reproduit(true reproduït/false no reproduït).
     * Dos booleans que responen a si l'usuari ha triat o no reproducció ciclica
     * o aleatoria.
     * Un enter indicant la posició de l'arxiu que s'està reproduint.
     * Boolean reproduint que permet saber si s'estan o no reproduint fitxers en
     * aquest moment
     * 
     */
    private CarpetaFitxers llistaReproduint;
    private boolean [ ] llistaCtrl;//True indica que el fitxer ja s'ha reproduit
    private boolean reproduccioCiclica, reproduccioAleatoria;
    private boolean reproduint;
    private int posicio;
    
    /**
     * Constructor sense paràmetres, crida al constructor de la classe mare 
     * EscoltadorReproduccioBasic.
     */
    public EscoltadorReproduccio(){
        super();
    }
    
    /**
     * Getter del boolean reproduint. Servirà per gestionar els mètodes que gestionen
     * la reproducció.
     * 
     * @return reproduint
     */
    public boolean isReproduint(){
        return reproduint;
    }
    
    /**
     * Setter del boolean reproduint.
     * 
     * @param reproduint
     */
    public void setReproduint(boolean reproduint){
        this.reproduint = reproduint;
    }
    
    
    /**
     * Sobreescriu el metode d'EscoltadorReproduccioBasic. Es crida automàticament
     * quan un fitxer s'acaba de reproduir i, si encara queden arxius per reproduir
     * a la carpeta, crida al mètode next() d'aquesta mateixa classe perquè segueixi
     * amb la reproducció.
     */
    @Override
    protected void onEndFile() {
    if (this.hasNext())
        next();
    }

    /**
     * És cridat només si queden fitxers per reproduir.
     * 
     * En funció de la tria de reproducció que s'hagi fet decideix quin és el següent
     * arxiu a reproduir i el reprodueix. Això ho fa amb el mètode reproduir de 
     * la classe reproductor, al qual accedeix mitjançant el reproductor que tots
     * els fitxers reproduibles tenen com atribut.
     * També actualitza el valor de l'atribut posicio i marca l'arxiu com a reproduit.
     * 
     * Cal observar que, en acabar la reproduccio, s'activa automaticament el mètode
     * onEndFile i que, per tant, el mètode next() es crida en bucle fins que s'ha
     * completat la reproducció
     */
    @Override
    protected void next() {
        //1. Trobar la posició de l'arxiu que reproduirem
        if(!reproduccioAleatoria){
            //Tant si és lineal o ciclica
            posicio = (posicio + 1) % llistaReproduint.getSize();
        } else if(!reproduccioCiclica){
            //Si es aleatoria i ciclica
            int aleatori = (int) Math.round(Math.random() * (llistaCtrl.length - 1));
            if (! llistaCtrl[aleatori]){
                //Si és possible, reproduim l'arxiu de la posició aleatoria obtinguda
                posicio = aleatori;
            } else {
                //Sino, avancem per la llista fins que trobem un arxiu que encara 
                //no s'hagi reproduit
                boolean trobat = false;
                while (!trobat) {
                    aleatori = (aleatori + 1) % llistaCtrl.length;
                    trobat = !llistaCtrl[aleatori];
                }
                posicio = aleatori;
            }
        } else {
            //Si és aleatoria i ciclica
            posicio = (int)Math.round(Math.random()*(llistaCtrl.length-1));
        }
        //2. Obtenir el fitxer a reproduir
        FitxerReproduible aux = (FitxerReproduible) llistaReproduint.getAt(posicio);
        //3. Reproduir el fitxer
        llistaCtrl[posicio] = true;//tant si es pot reproduir com si no, marquem que ja l'hem visitat 
        try {
            aux.reproduir();
        } catch (AplicacioException ex) {
            //Si no es pot reproduir, l'ignorem
            if(this.hasNext())
                this.next();
        }
    }

    /**
     * Retorna true si encara queden arxius per reproduir 
     * 
     * @return boolean
     */
    @Override
    protected boolean hasNext() {
        if(reproduccioCiclica){
            return true;
        } else if(reproduccioAleatoria){
            //Cerquem si hi ha algun false
            boolean trobat = false;
            int i = 0;
            while(!trobat && i < llistaCtrl.length){
                trobat = !llistaCtrl[i];
                i++;
            }
            return trobat;
        } else {
        //en cas que tots dos tipus de reproduccio siguin false, fem una reproduccio lineal
            if(posicio == llistaCtrl.length -1)
                return false;
            return !llistaCtrl[posicio + 1];
        }
    }
    
    /**
     * Mètode que inicialitza la llista de fitxers que es reproduiran i el tipus 
     * de reproducció(cíclica, aleatòria) mitjançant els paràmetres. Actualitza 
     * el boolean reproduint(true) i la posició(-1 ja que next() reprodueix 
     * següent fitxer) i crida el next() per iniciar el bucle de reproducció.
     * En cas que la carpeta sigui buida, llancem una AplicacioException.
     * 

     * @param llistaReproduint 
     * @param reproduccioCiclica 
     * @param reproduccioAleatoria 
     * @throws edu.ub.prog2.utils.AplicacioException 
     */
    public void iniciarReproduccio(CarpetaFitxers llistaReproduint, boolean reproduccioCiclica, boolean reproduccioAleatoria) throws AplicacioException{
        if(llistaReproduint.getSize() == 0){
            throw new AplicacioException("No hi ha fitxers per reproduir");
        }
        this.llistaReproduint = llistaReproduint;
        llistaCtrl = new boolean[llistaReproduint.getSize()]; //queden inicialitzats a False
        this.reproduccioCiclica = reproduccioCiclica;
        this.reproduccioAleatoria = reproduccioAleatoria;
        reproduint = true;
        posicio = -1;
        next();
    }

    
}
