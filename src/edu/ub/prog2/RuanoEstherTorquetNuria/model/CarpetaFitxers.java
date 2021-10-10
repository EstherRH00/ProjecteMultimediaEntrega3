package edu.ub.prog2.RuanoEstherTorquetNuria.model;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.InFileFolder;
import java.io.File;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Esther Ruano Hortoneda
 * @author Nuria Torquet Luna
 * La classe CarpetaFitxers conté un ArrayList de fitxers i un atribut amb la 
 * seva mida màxima.
 * Els mètodes permeten afegir, treure elements i conèixer l'estat de la carpeta.
 */
public class CarpetaFitxers implements Serializable, InFileFolder{
    //Atributs
    private ArrayList<FitxerMultimedia> carpeta; 
    protected int maxFitxers;

    
    /**Constructor per defecte:
     Assigna a l'atribut carpeta un ArrayList pel qual ha reservat un espai de
     100 FitxersMultimedia i assigna a maxFitxers el valor de 100. */
    public CarpetaFitxers() {
        carpeta = new ArrayList<>(100);
        /*
        Ara s'ha reservat memòria per a 100 fitxers multimèdia però si se'n 
        volguéssin afegir més a la carpeta no hi hauria problema, ja que és
        memòria dinàmica.
        Per això es crea l'atribut maxFitxers amb la finalitat de controlar la 
        mida de la carpeta.
        */
        maxFitxers = 100;
    }
    
    /**
    * Constructor amb paràmetres:
    * @param capacitat desitjada de la CarpetaFitxers
    * Assigna a l'atribut carpeta un ArrayList pel qual ha reservat l'espai
    * passat com a paràmetre de FitxersMultimedia i assigna a maxFitxers 
    * el valor indicat. */
    public CarpetaFitxers(int capacitat){
        maxFitxers = capacitat;
        carpeta = new ArrayList<>(capacitat);
    }
    
    /**
     * Retorna quans elements té l'ArrayList
     * @return int
     */
    @Override
    public int getSize(){
        return carpeta.size();
    }
    public ArrayList<FitxerMultimedia> getCarpeta(){
        return this.carpeta;
    }
    
    /**
    * Mitjançant el mètode isFull(), s'evita que la carpeta excedeixi la 
    * mida màxima estipulada.  
    * Fa servir el mètode .add dels ArrayList per afegir l'element a la
    * primera posició lliure, si n'hi ha.
    * 
    * @param fitxer a afegir
     * @throws edu.ub.prog2.utils.AplicacioException i la carpeta està plena
    * 
    */
    @Override
    public void addFitxer(File fitxer) throws AplicacioException{
        if (!this.isFull()) {
            carpeta.add((FitxerMultimedia) fitxer);
        } else {
            throw new AplicacioException("Has excedit el nombre màxim de fitxers que pots guardar");
        }
    }
    
    /**
    * Elimina el Fitxer amb l'index passat com a parametre
    * 
     * @param pos
     * @throws edu.ub.prog2.utils.AplicacioException
    */
    public void removeFitxer(int pos) throws AplicacioException{
        /*
        El mètode .remove(int num) elimina l'objecte situat en la posició pos de
        l'arrayList. Si l'index no és apropiat tira una excepcio del tipus 
        IndexOutOfBoundsException. Com que només volem tractar amb excepcions del 
        tipus AplicacioException, evitem que això passi filtrant els index
        abans de cridar al mètode remove
        */
         try{
            FitxerMultimedia b = carpeta.remove(pos);
        } catch (IndexOutOfBoundsException e) {
            throw new AplicacioException("Posició no trobada");
        }

    }
    
    @Override
    /**
    * Elimina totes les ocurrències del fitxer
    * 
    * @param fitxer a eliminar
    */
    public void removeFitxer(File fitxer){
        /*
        El mètode .remove esborra la primera ocurrència de fitxer i desplaça tots
        els fitxers que el segueixin a l'ArrayList una posició enrere, de manera
        que totes les posicions lliures queden al final.
        També retorna un booleà que és True si ha trobat el fitxer i l'ha esborrat
        i pel contrari False si després de recórrer l'ArrayList no ha trobat cap
        coincidència amb el fitxer passat per paràmetre
        */
        boolean b = true;
        while(b){
            b = carpeta.remove((FitxerMultimedia) fitxer);
        }
    }
    

    /** 
     * Retorna el fitxer que es troba a la posició passada per paràmetre
     * 
     * @param position (índex) de l'arxiu que es vol conèixer
     * @return File
     * @throws IndexOutOfBoundsException si no hi ha cap element a la posició
     * o la posició és major que la mida de l'ArrayList. No pot llencar una
     * AplicacioException ja que el mètode al qual sobreescriu no ho fa
    */
    @Override
    public File getAt(int position) {
        return carpeta.get(position);
    }
    
    /**
    * Recorre l'ArrayList esborrant l'element que hi ha en cada posició. 
    */
    @Override
    public void clear(){
        carpeta.clear();
    }
    
    /**
    * Retorna True si la carpeta està plena, False en cas contrari
    * 
    * @return boolean
    */
    @Override
    public boolean isFull(){
        return (carpeta.size() == maxFitxers);
    }
    
    /**
    * Retorna, seguint el format indicat, un String amb els elements de 
    * la carpeta de fitxers. 
    * 
    * @return String
    */
    @Override
    public String toString(){
        String resultat = "Carpeta Fitxers: \n" + "=================\n";
        for (int i = 0; i < carpeta.size(); i++) {
            resultat = resultat + "\n[" + (i + 1) + "] " + carpeta.get(i).toString();
        }
        return resultat;
    
    }
    
    /**
     * Mètode que implementa el mètode públic addFitxer(File fitxer) de 
     * la classe. Comprova si el fitxer introduit com a paràmetre es troba a 
     * la carpeta fent servir una estructura de cerca. Per a comparar entre fitxers,
     * empra el mètode equals de la classe FitxerMultimedia.
     * 
     * @param fitxer
     * @return boolean trobat
     */
    public boolean contains(File fitxer){
        int i = 0;
        boolean trobat = false;
        while((i < this.getSize()) && (!trobat)){
            trobat = this.getAt(i).equals((FitxerMultimedia)fitxer); //Mai hauria de rebre una excepció, però la estructura try-catch
            //és necessaria igual
            i++;
        }
        return trobat;
        }

    
    
}
