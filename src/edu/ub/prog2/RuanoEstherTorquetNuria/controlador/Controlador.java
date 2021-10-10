
package edu.ub.prog2.RuanoEstherTorquetNuria.controlador;

import edu.ub.prog2.RuanoEstherTorquetNuria.model.Dades;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.InControlador;


import java.io.IOException;
import java.util.List;



/**
 * @author Esther Ruano Hortoneda
 * @author Nuria Torquet Luna
 *
 * Controlador s'encarrega de fer crides als mètodes a la classe Dades del
 * package model. La seva finalitat és que l'usuari no pugui accedir directament
 * a cap classe de model.
 * {@link edu.ub.prog2.RuanoEstherTorquetNuria.model.Dades [Classe d'on provenen els mètodes]}
 *
 */
public class Controlador implements InControlador{
    
    /**
     * Creem tres variables de referència per les classes Dades, Reproductor i EscoltadorReproduccio.
     * 
     * dades s'encarrega de la gestió(afegir, mostrar, eliminar...)
     * dels fitxers per individual, dels àlbums i de la biblioteca. A més, també
     * crida els mètodes per a serialitzar/desserialitzar les dades i canviar el
     * tipus de reproducció (contínua, aleatòria).
     * 
     * escoltador l'emprem per reproduir fitxers, àlbums o la biblioteca fent la
     * crida als mètodes pertinents de la classe.
     * 
     * reproductor fa la crida a les diferents accions que podem realitzar durant
     * la reproducció(salta, atura, reempren...)
     * 
     * creem també un booleà per saber si la finestra de reproducció està oberta i
     * s'ha de tancar
     * 
     */
    private Dades dades;
    private Reproductor reproductor;
    private EscoltadorReproduccio escoltador;
    private boolean finestraOberta;
    /**
     * Constructor de la classe, inicialitza objecte de la classe Dades i el reproductor.
     */
    public Controlador() {
        dades = new Dades();
        escoltador = new EscoltadorReproduccio();
        reproductor = new Reproductor(escoltador);
    }
   
    /**
     * Crida al mètode afegirVideo de la classe Dades. En cas de rebre una
     * excepció, la llança altre cop.
     *
     * @param path
     * @param nomVideo
     * @param codec
     * @param durada
     * @param alcada
     * @param amplada
     * @param fps
     * @throws AplicacioException
     */
    @Override
    public void afegirVideo(String path, String nomVideo, String codec, 
            float durada, int alcada, int amplada, float fps) throws AplicacioException {
            dades.afegirVideo(path, nomVideo, codec, durada, alcada, amplada, fps, reproductor);
      
    }
    
    /**
     * Crida al mètode afegirAudio de la classe Dades. En cas de rebre una
     * excepció la llança altre cop.
     *
     * @param cami
     * @param camiImatge
     * @param nomAudio
     * @param codec
     * @param durada
     * @param kbps
     * @throws AplicacioException
     */
    @Override
    public void afegirAudio(String cami, String camiImatge, String nomAudio, 
            String codec, float durada, int kbps) throws AplicacioException {  
            dades.afegirAudio(cami, camiImatge, nomAudio, codec, durada,kbps, reproductor);
        
    }

   /**
     * Crida al mètode mostrarBiblioteca de la Classe Dades
     * 
     * @return dades.mostrarBiblioteca()
     */
    @Override
    public List<String> mostrarBiblioteca() {//la capçalera del mètode hauria de ser List<String>
        return dades.mostrarBiblioteca();
    }
    
    /**
     * Crida al mètode esborrarFitxer de la classe Dades. En cas de rebre una
     * excepció la llança altre cop.
     *
     * @param id
     * @throws AplicacioException
     */
    @Override
    public void	esborrarFitxer(int id) throws AplicacioException{
            dades.esborrarFitxer(id);
    }
    
    /**
     * Crida al mètode guardarDadesDisc de la classe Dades. En cas de rebre una
     * excepció la llança altre cop.
     *
     * @param camiDesti
     * @throws AplicacioException
     */
    @Override
    public void guardarDadesDisc(String camiDesti) throws AplicacioException{
        try {
            dades.guardarDadesDisc(camiDesti);
        } catch (IOException ex) {
            throw new AplicacioException("Error al guardar les dades");
        }
       
    }
    
    /**
     * Crida al mètode carregarDadesDisc de la classe Dades. Aquest actualitza
     * el contingut de dades.
     * En cas de rebre una excepció la llança altre cop.
     *
     * @param camiOrigen
     * @throws AplicacioException
     */
    @Override
    public void carregarDadesDisc(String camiOrigen) throws AplicacioException{
        try {
            dades = Dades.carregarDadesDisc(camiOrigen);
            dades.setReproductor(reproductor);
        } catch (IOException ex) {
            throw new AplicacioException("Error al carregar les dades");
        }
       
    }

    /**
     * Crida al mètode afegirAlbum de la classe Dades
     * @param string
     * @throws AplicacioException 
     */
    @Override
    public void afegirAlbum(String string) throws AplicacioException {//suposem llavors que els àlbums són de mida 10
        dades.afegirAlbum(string);
    }
    
    /**
     * Crida al mètode afegirAlbum de la classe Dades
     * @param string
     * @param mida
     * @throws AplicacioException 
     */
    public void afegirAlbum(String string, int mida) throws AplicacioException {//suposem llavors que els àlbums són de mida 10
        dades.afegirAlbum(string, mida);
    }


    /**
     * Crida al mètode mostrarAlbums de la classe Dades.
     * @return dades.mostrarAlbums()
     */
    @Override
    public List<String> mostrarLlistatAlbums() {
        return dades.mostrarAlbums();
    }

    /**
     * Crida al mètode eliminarAlbum de la classe Dades
     * @param string
     * @throws AplicacioException 
     */
    @Override
    public void esborrarAlbum(String string) throws AplicacioException {
         dades.eliminarAlbum(string);
    }

    /**
     * Crida al mètode existeixAlbum de la classe Dades
     * @param string
     * @return boolean existeixAlbum 
     */
    @Override
    public boolean existeixAlbum(String string) {
        boolean existeixAlbum = dades.existeixAlbum(string);
        return existeixAlbum;
    }

    /**
     * Crida al mètode afegirFitxerAlbum de la classe Dades
     * @param string
     * @param i
     * @throws AplicacioException 
     */
    @Override
    public void afegirFitxer(String string, int i) throws AplicacioException {

            dades.afegirFitxerAlbum(i, string);

    }
    
    /**
     * Crida al mètode mostrarAlbum de la classe Dades
     * @param string
     * @return dades.mostrarAlbum(string)
     */
    @Override
    public List<String> mostrarAlbum(String string){
        return dades.mostrarAlbum(string);
    }

    /**
     * Crida al mètode eliminarFitxerAlbum de la classe Dades
     * @param string
     * @param i
     * @throws AplicacioException 
     */
    @Override
    public void esborrarFitxer(String string, int i) throws AplicacioException {
       dades.eliminarFitxerAlbum(i, string);
    }
    
    /**
     * Cridem mètode ja implementat de ReproductorBasic open().
     */
    @Override
    public void obrirFinestraReproductor() {
      this.reproductor.open();
      finestraOberta = true;
    }
    
    /**
     * Retorna true si la finestra de reproducció està oberta
     * @return boolean
     */
    public boolean finestraOberta(){
        return finestraOberta;
    }

    /**
     * Cridem mètode ja implementat de ReproductorBasic close().
     * @throws edu.ub.prog2.utils.AplicacioException
     */
    @Override
    public void tancarFinestraReproductor() throws AplicacioException {
        this.reproductor.close();
        finestraOberta = false;
    }
    
    /**
     * Aquest metode recorre la Biblioteca i assigna a tots els fitxers el reproductor
     */
    public void setReproductor(){
        dades.setReproductor(reproductor);
    }
    
    /**
     * Mètode per reproduir un fitxer de la biblioteca de fitxers.
     * Cridem amb l'objecte escoltador el mètode iniciarReproduccio de la classe
     * EscoltadorReproduccio. Per obtenir el fitxer de la biblioteca cridem amb
     * objecte dades el mètode getCarpetaRepro(int i) i obtenim el fitxer amb 
     * aquest index. A més, comprovem l'estat de la reproducció cíclica i aleatòria.
     * En cas d'error, llancem una excepció.
     * @param i
     * @throws AplicacioException 
     */
    @Override
    public void reproduirFitxer(int i) throws AplicacioException {
        try{
            obrirFinestraReproductor();
            escoltador.iniciarReproduccio(dades.getCarpetaRepro(i), dades.getReproduccioCiclica(), dades.getReproduccioAleatoria());
        }catch (AplicacioException a) {
            tancarFinestraReproductor();
            throw new AplicacioException(a.getMessage());
        }
    }
    
    /**
     * Mètode per reproduir la biblioteca de fitxers.
     * Mateixa idea que amb el mètode reproduirFitxer però en comptes d'obtenir
     * un fitxer de la carpeta, obtenim la biblioteca sencera.
     * Cridem el mètode getCarpetaRepro() sobreescrit a dades que retorna la biblioteca de fitxers.
     * 
     * @throws AplicacioException 
     */
    @Override
    public void reproduirCarpeta() throws AplicacioException {
        try{
            obrirFinestraReproductor(); 
            escoltador.iniciarReproduccio(dades.getCarpetaRepro(), dades.getReproduccioCiclica(), dades.getReproduccioAleatoria());
        }catch (AplicacioException a) {
            tancarFinestraReproductor();
            throw new AplicacioException(a.getMessage());
        }
            
        
    }
    /**
     * Mètode per reproduir un àlbum.
     * Mateixa idea que amb el mètode reproduirFitxer però en comptes d'obtenir
     * un fitxer de la carpeta, obtenim la l'àlbum associat al títol passat per 
     * paràmetre. Cridem el mètode getAlbumRepro(string) de dades que 
     * retorna l'àlbum associat a aquest títol.
     * @param string
     * @throws edu.ub.prog2.utils.AplicacioException
     */
    @Override
    public void reproduirCarpeta(String string) throws AplicacioException {
      try{
            obrirFinestraReproductor(); 
            escoltador.iniciarReproduccio(dades.getAlbumRepro(string), dades.getReproduccioCiclica(), dades.getReproduccioAleatoria());
        }catch (AplicacioException a) {
            tancarFinestraReproductor();
            throw new AplicacioException(a.getMessage());
        }
    }

    /**
     * Cridem mètode amb mateix nom de classe Reproductor
     * @throws AplicacioException 
     */
    @Override
    public void reemprenReproduccio() throws AplicacioException {
        
        reproductor.reemprenReproduccio();
    }

    /**
     * Cridem mètode amb mateix nom de classe Reproductor
     * @throws AplicacioException 
     */
    @Override
    public void pausaReproduccio() throws AplicacioException {
        reproductor.pausaReproduccio();
    }

    /**
     * Cridem mètode amb mateix nom de classe Reproductor
     * @throws AplicacioException 
     */
    @Override
    public void aturaReproduccio() throws AplicacioException {
        reproductor.aturaReproduccio();
    }

    /**
     * Cridem mètode amb mateix nom de classe Reproductor
     * @throws AplicacioException 
     */
    @Override
    public void saltaReproduccio() throws AplicacioException {
        reproductor.saltaReproduccio();
    }
    
    /**
     * Canviem el boolean reproduccioCiclica per a que a la següent reproducció 
     * s'apliqui el canvi. Cridem el setter associat a l'atribut de dades i passem
     * per paràmetre el valor del boolean negat. Retornem el canvi aplicat.
     * @return  boolean reproduccioCiclica
     */
    public boolean OnOffCiclica(){
        dades.setReproduccioCiclica(!dades.getReproduccioCiclica());
        return dades.getReproduccioCiclica();
    }
    
    /**
     * Canviem el boolean reproduccioAleatoria per a que a la següent reproducció 
     * s'apliqui el canvi. Cridem el setter associat a l'atribut de dades i passem
     * per paràmetre el valor del boolean negat. Retornem el canvi aplicat.
     * @return boolean reproduccioAleatoria
     */
    public boolean OnOffAleatoria(){
        dades.setReproduccioAleatoria(!dades.getReproduccioAleatoria());
        return dades.getReproduccioAleatoria();
    }
}
