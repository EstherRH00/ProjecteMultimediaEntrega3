package edu.ub.prog2.RuanoEstherTorquetNuria.model;

import java.io.File;
import java.util.Date;
import java.util.Objects;

/**
 * @author Esther Ruano Hortoneda
 * @author Nuria Torquet Luna
 * 
 * La classe FitxerMultimedia conté un atribut propi, descripció, que guarda un
 * String amb la descripció del Fitxer. Els altres atributs que emprem s'hereten
 * de la classe File(nom, camí, extensió, última modificació).
 * Els mètodes, permeten modificar els atributs (si és possible) i obtenir-ne els
 * valors. També comparar dos FitxersMultimedia.
 */

public class FitxerMultimedia extends File{
    
    private String descripcio;
    
    /**
    * Constructor: a partir del camí, data actual i la descripció, assigna els 
    * valors pertinents a tots els atributs i crida al constructor de File
    * 
    * @param cami (path) cap a l'arxiu
    * @param descripcio de l'arxiu
    */
    public FitxerMultimedia(String cami, String descripcio){
        //Exemple -> C://Reproductor//BibliotecaMusica.txt
        super(cami);
        this.descripcio = descripcio;
        /*
        Com que el call al super ha de ser la primera línea del constructor per 
        definició, no podem posar una estructura del tipus if else per tal de fer un
        throw d'una excepció.
        Conseqüentment és al main on filtrarem que l'String camí sigui vàlid comprovant
        que com a mínim contingui els caràcters necessaris per separar el nom de 
        l'extensió i del camí ja que, en cas contrari, el constructor no podira funcionar
        */
    }
 
    //getters 
    

/**
     * Retorna l'última modificació del fitxer simplement cridant el mètode ja
     * implementat de la classe File
     *
     * @return Date
     */
    public Date getUltimaModificacio(){

        Date aux = new Date(this.lastModified());

        return aux;

    }

 

    /**
     * Retorna el camí absolut del fitxer (sense el nom d'aquest)
     * 
     * @return String
     */
    public String getCamiAbsolut(){
        return this.getPath().substring(0, this.getPath().lastIndexOf("\\")+1);
        
    }
   
    /**
     * Retorna el nom del fitxer
     * 
     * @return String
     */
    public String getNomFitxer(){
        return this.getPath().substring(this.getPath().lastIndexOf("\\")+1, this.getPath().lastIndexOf("."));
    }

    /**String getExtensio(){
        return this.getPath().s
     * Retorna l'extensió del fitxer
     * 
     * @return String
     */
    public String getExtensio(){
        return this.getPath().substring(this.getPath().lastIndexOf(".")+1);
    }
    
    
    /**
     * Retorna la descripcio del fitxer
     * 
     * @return String
     */
    public String getDescripcio(){
        return descripcio;
    }
    
    // setters 
    
    /**
     * Modifica la descripció del fitxer
     * 
     * @param descripcio del fitxer
     */
    public void setDescripció(String descripcio){
        this.descripcio = descripcio;
    }
    
    /**
     * Com que el camí (path) és un atribut privat i final de la classe File, no 
     * podem tenir setters per modificar-lo
     */    
    
    /**
    * Sobreescriu el mètode equals de la classe File.
    * Compara el fitxer amb el qual es fa la crida amb el que es passa com a 
    * paràmetre. Diem que dos FitxersMultimedia son iguals si el seu estat 
    * (atributs) és el mateix
    * Retorna True si són iguals, False en cas contrari
    * 
    * @param fitxerMultimedia amb el qual es compararà
    * @return boolean
    */
    @Override
    public boolean equals(Object fitxerMultimedia){
        if (fitxerMultimedia == null) {
            return false;
        }
        if (this.getClass() != fitxerMultimedia.getClass()) {//comparem el tipus de classe de cada obj
            return false;
        }
        // other es un valor constant, li assignem el casting de l'objecte que volem comparar
        final FitxerMultimedia other = (FitxerMultimedia) fitxerMultimedia;
        //si els camins absoluts no coincideixen, els fitxers son diff
        if (!Objects.equals(this.getAbsolutePath(), other.getAbsolutePath())|| 
                !Objects.equals(this.descripcio, other.descripcio)) {
            return false;
        }
        return true;
    }
    
    /**
    * Sobreescriu el mètode toString de la classe File.
    * Seguint el format indicat, crea un String amb la informació del fitxer
    * No accedeix directament als atributs, sinó que fa servir els getters
    * 
    * @return String
    */
    @Override
    public String toString(){
        return "Descripció = " + this.getDescripcio() 
               + ", data = " + this.getUltimaModificacio()
               + ", nom fitxer = " + this.getNomFitxer()
               + ", ext = " + this.getExtensio() 
               + ", camí complet: " + this.getAbsolutePath()
               + "\n";
    }
    
}
