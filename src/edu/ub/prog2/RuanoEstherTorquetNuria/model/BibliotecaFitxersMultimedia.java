/**
 * Conté el conjunt total de fitxers de l'aplicació.
 * No permet que dos fitxers siguin iguals
 * Quan s'introdueix info d'un fitxer, es verifica si el fitxer associat
 * existeix al disc
 * No té límit de fitxers
 */
package edu.ub.prog2.RuanoEstherTorquetNuria.model;

import edu.ub.prog2.utils.AplicacioException;
import java.io.File;

/**
 *
 * @author Esther Ruano Hortoneda
 * @author Nuria Torquet Luna
 * 
 * Hereta de CarpetaFitxers i s'encarrega de gestionar l'afegida de fitxers.
 * Implementa mètodes de CarpetaFitxers per a que la carpeta no tingui mida màxima
 * i no permet que es guardin fitxers idèntics.
 */

public class BibliotecaFitxersMultimedia extends CarpetaFitxers {
    /**
     * Constructor de la classe BibliotecaFitxersMultimedia. Mitjançant aquest
     * mètode, cridem el constructor per defecte de la classe CarpetaFitxers,
     * fet que ens permet crear un ArrayList de mida màxima 100.
     */
    public BibliotecaFitxersMultimedia() {
        super();
    }

    /**
     * Mètode per afegir un fitxer a la carpeta de CarpetaFitxers. Sobreescriu 
     * el mètode amb el mateix nom de la superClasse. Comprova que el fitxer
     * existeix al disc (fitxer.exists()) i que no es troba a la carpeta (mètode
     * private fitxerExists(file)). Si es compleixen els requisits, cridem al mètode
     * addFitxer de la superClasse. En cas constrari, llancem una excepció.
     * 
     * @param fitxer
     * @throws AplicacioException
     */
    @Override
    public void addFitxer(File fitxer) throws AplicacioException {
        //cridem el mètode private contains() que comprova si la carpeta ja conté
        //el fitxer i el mètode exist() de la classe File que comprova si el path introduit
        //pel fitxer és vàlid
        if ((!super.contains(fitxer)) && fitxer.exists()) {
            super.addFitxer((FitxerMultimedia) fitxer);
        } else if(!fitxer.exists()) {
            throw new AplicacioException("El fitxer no existeix al disc");
        } else{
            throw new AplicacioException("El fitxer ja esta a la biblioteca");
        }
    }

    /**
     * Mètode per comprovar si la carpeta de CarpetaFitxers es troba plena. 
     * Sobreescrivim el mètode de la superClasse per evitar restringir la mida
     * màxima de l'ArrayList. Sempre retorna false, mai tindrem la carpera plena.
     * 
     * @return false
     */
    @Override
    public boolean isFull() {//
        return false;//sempre retornarà el mateix, la carpeta no té mida max.
    }
    
    /**
     *
     * @return
     */
    public boolean isEmpty(){
        return this.getCarpeta().isEmpty();
    }
    
    /**
     *
     * @param position
     * @return
     * @throws AplicacioException
     */
    public File getA(int position)throws AplicacioException{
        try{
            return super.getAt(position);
        }catch(IndexOutOfBoundsException e){
            throw new AplicacioException("Posicio no trobada");
        }
        
        

    } 

   
    

    
}
