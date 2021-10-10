/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.RuanoEstherTorquetNuria.model;

import edu.ub.prog2.RuanoEstherTorquetNuria.controlador.Reproductor;
import edu.ub.prog2.utils.AplicacioException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
/**
 * @author Esther Ruano Hortoneda
 * @author Nuria Torquet Luna
 * 
 * Classe amb la que interactua controlador per a poder gestionar els fitxers de
 * la carpetaFitxers mitjançant la classe BibliotecaFitxers i permet emmagatzemar
 * aquests fitxers en àlbums.
 * S'encarrega de crear objectes Audio o Video per tal d'afegir-los a l'ArrayList
 * carpeta que conté l'objecte biblioteca per herència, eliminar-los i retornar 
 * les seves característiques com a Strings. També gestiona els mètodes per 
 * afegir/eliminar/mostrar àlbums i permet treballar amb un àlbum concret per a 
 * l'addició/eliminació de fitxers. A més, compta amb els mètodes guardarDadesDisc 
 * i carregarDadesDisc, que s'encarregan de serialitzar i desserialitzar els fitxers.
 */
public class Dades implements Serializable{
    /**
     * Creem variables de referència per a la classe BibliotecaFitxersMultimedia
     */
    private BibliotecaFitxersMultimedia biblioteca;
    private ArrayList<AlbumFitxersMultimedia> albums;
    
    private boolean reproduccioCiclica, reproduccioAleatoria;
    
    /**
     * Constructor de la classe Dades, inicialitzem la biblioteca
     */
    public Dades(){
        biblioteca = new BibliotecaFitxersMultimedia();
        albums = new ArrayList<AlbumFitxersMultimedia>();
        reproduccioCiclica = false;
        reproduccioAleatoria = false;
    }
    
    
     /**
     * Getter per a retornar el conjunt de fitxers que guardem a la biblioteca.
     * @return biblioteca.getCarpeta()
     */
    public BibliotecaFitxersMultimedia getBiblioteca(){
        return biblioteca;
    }
    
    /**
     * Getter per obtenir el fitxer que volem reproduir indicat per la posició 
     * en la biblioteca.
     * El retornem com un objecte de tipus CarpetaFitxers on en l'ArrayList
     * associat hi afegim com a únic FitxerMultimedia el fitxer que volem reproduir
     * de la biblioteca. En cas d'haver-hi errors(biblioteca buida, índex out of 
     * range...) llancem una AplicacioException amb la causa especificada.
     * @param i
     * @return
     * @throws AplicacioException 
     */
    public CarpetaFitxers getCarpetaRepro(int i) throws AplicacioException{
        if(biblioteca.isEmpty()){
            throw new AplicacioException("No hi ha fitxers a la biblioteca");
        }
        try{
            CarpetaFitxers tmp = new CarpetaFitxers(1);
            tmp.addFitxer(biblioteca.getA(i));
            return tmp;
        } catch (IndexOutOfBoundsException o) {
            throw new AplicacioException("L'índex del fitxer és incorrecte");
        }
        
    }
    
    /**
     * Mètode sobreescrit en el que retornem tota la biblioteca de fitxers.
     * @return
     * @throws AplicacioException 
     */
    public CarpetaFitxers getCarpetaRepro() throws AplicacioException {
        if (biblioteca.isEmpty()) {
            throw new AplicacioException("No hi ha fitxers a la biblioteca");
        }
        return biblioteca;
    }
    
    /**
     * Mètode per retornar l'àlbum associat al títol que passem per paràmetre.
     * Cridem el mètode se suport buscarAlbum per a que ens retorni la seva
     * posició en l'ArrayList albums i així puguem accedir amb el get(int indx).
     * En cas de no trobar-se, l'índex associat serà -1 i llancarem una AplicacioException.
     * @param titol
     * @return
     * @throws AplicacioException 
     */
    public CarpetaFitxers getAlbumRepro(String titol) throws AplicacioException {
        int indxAlbum = buscarAlbum(titol);
        if(indxAlbum == -1){
            throw new AplicacioException("No existeix aquest album");
        }else{
            return albums.get(indxAlbum);
        }
    }
    
    /**
     * getter per al boolean reproduccioCiclica
     * @return reproduccioCiclica
     */
    public boolean getReproduccioCiclica() {
        return reproduccioCiclica;
    }

    /**
     * getter per al boolean reproduccioAleatoria
     * @return reproduccioAleatoria
     */
    public boolean getReproduccioAleatoria() {
        return reproduccioAleatoria;
    }
    
    /**
     * setter per al boolean reproduccioCiclica
     * @param reproduccioCiclica 
     */
    public void setReproduccioCiclica(boolean reproduccioCiclica){
        this.reproduccioCiclica = reproduccioCiclica;
    }
    /**
     * setter per al boolean reproduccioAleatoria
     * @param reproduccioAleatoria 
     */
    public void setReproduccioAleatoria(boolean reproduccioAleatoria){
        this.reproduccioAleatoria = reproduccioAleatoria;
    }

   
    
    /**
     * Mètode per afegir un vídeo a la carpetaFitxers mitjançant la crida al 
     * mètode addFitxers sobreescrit a BibliotecaFitxers. Creem un obj de tipus
     * video amb els paràmetres del mètode afegirFitxer i cridem al mètode addFitxer
     * de BibliotecaFitxers, sobreescrit per a que no puguem introduir un vídeo
     * que no es troba en el disc o que ja existeix a la carpetaFitxers. 
     * P.t. llança AplicacioException en cas de produir-se alguna de les excepcions
     * esmentades.
     * @param path
     * @param nomVideo
     * @param codec
     * @param durada
     * @param alcada
     * @param amplada
     * @param fps
     * @param reproductor
     * @throws AplicacioException en cas de no poder afegir el fitxer
     */
    
    
    public void afegirVideo(String path, String nomVideo, String codec, 
            float durada, int alcada, int amplada, float fps, Reproductor reproductor) throws AplicacioException {
        
        biblioteca.addFitxer(new Video(path, nomVideo, codec, durada, alcada, amplada, fps, reproductor));
    }
    
    /**
     * Mateix raonament que amb el mètode afegirVideo
     * @param cami
     * @param camiImatge
     * @param nomAudio
     * @param codec
     * @param durada
     * @param kbps
     * @param reproductor
     * @throws AplicacioException en cas de no poder afegir el fitxer
     */
    public void afegirAudio(String cami, String camiImatge, String nomAudio, 
            String codec, float durada, int kbps, Reproductor reproductor) throws AplicacioException {
        File fitxerImatge = new File(camiImatge);
        Audio a = new Audio(cami, fitxerImatge, nomAudio, codec, durada, kbps, reproductor);
        biblioteca.addFitxer(a);
    }
    
    /**
     * Mètode per esborrar un fitxer de la bibliotecaFitxers i dels àlbums mitjançant
     * l'objecte biblioteca i albums. Per cada album cridem al metode removeFitxer(file) 
     * que hem sobreescrit perquè elimini totes les ocurrencies de l'arxiu passat 
     * per parametre.
   
     * @param id -->posició del fitxer en la carpeta
     * @throws AplicacioException 
     */
    public void	esborrarFitxer(int id) throws AplicacioException{
        for(AlbumFitxersMultimedia album: albums){
            if(!album.getAlbum().isEmpty()){
                File fitxer = biblioteca.getAt(id);
                album.removeFitxer(fitxer);
            }
        }
        biblioteca.removeFitxer(id);
    }
    
     /**
     * Mètode per retornar una Llista amb tantes posicions com fitxers tingui la
     * biblioteca. Cada posició és un String amb la informació d'un fitxer
     * 
     * @return List
     */
    public List<String> mostrarBiblioteca() {
        List<String> aux;
        if (biblioteca.getSize() == 0) {
            String s = "\nNo hi ha cap fitxer!\n";
            aux = new ArrayList<String>(1);
            aux.add(s);
        } else {
            aux = new ArrayList<String>(biblioteca.getSize() + 1);
            aux.add("\nLlista de fitxers\n====================\n\n");
            for (int i = 0; i < biblioteca.getSize(); i++) {
                aux.add("[" + (i + 1) + "] " + biblioteca.getAt(i).toString()); //Mai hauria de rebre una excepció, però la estructura try-catch
                //és necessaria igual
            }
        }
        return aux;
    }
    
    /*****************************************************************/
    /*********************  Persistència de dades  *******************/
    /*****************************************************************/
    
    
    /**
     * Mètode per serialitzar les dades del projecte. Creem fitxer on guardarem
     * les dades transformades en un stream de bytes, l'introduïm en un objecte
     * del tipus FileOutputStream i aquest al seu torn també és passat com a paràmetre
     * al constructor d'un objecte ObjectutputStream. Un cop creat, hi escrivim
     * la informació de l'objecte amb el que el crida(controlador) i tanquem ambdòs
     * streams. En cas d'error, llancem una AplicacioException
     * @param file
     * @throws AplicacioException
     * 
     */
    public void guardarDadesDisc(String file) throws AplicacioException, IOException{
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        try{
            File fitxer	= new File(file);
            fout = new FileOutputStream(fitxer);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
            
        }catch(IOException s){
            throw new AplicacioException("Les dades no s'han pogut guardar amb éxit");
        }finally{
            oos.close();
            fout.close();
        }
    } 
        
    
    
    /**
     * Mètode per deserialitzar les dades d'un fitxer. Passem com a paràmetre el
     * path del fitxer, creem el file amb aquest path i l'introduïm com a paràmetre
     * per al constructor d'un objecte FileInputStream. Aquest l'utilitem com a paràmetre
     * per a ObjectInputStream i llegim els bytes de l'objecte. El retorn(un objecte
     * del tipus Object) és sotmès a un downcasting per a transformar-lo en tipus
     * Dades. Tanquem els dos streams i retornem data.
     * @param file
     * @return data
     * @throws AplicacioException
     * @throws java.io.IOException

     */
    public static Dades carregarDadesDisc(String file) throws AplicacioException, IOException{
        ObjectInputStream input = null;
        FileInputStream fin = null;
        try{
            Dades data = null;
            File fitxer = new File(file);
            fin = new FileInputStream(fitxer);
            input = new ObjectInputStream(fin);
            data = (Dades) input.readObject();
            return data;
        }catch(Exception c){
           throw new AplicacioException("Les dades no s'han pogut recuperar amb éxit");
        }finally{
            input.close();
            fin.close();
        }
        
                   
 
    }
    
    /*****************************************************************/
    /*********************  Gestió dels Albums    *******************/
    /*****************************************************************/
 
   
    /**
     * Mètode per a poder afegir un àlbum a l'Arraylist albums. Creem un àlbum de
     * mida 10 amb el títol que ens han passat per paràmetre. Si aquest ja es 
     * trobava a l'ArrayList, llancem una excepcio.
     * @param string
     * @throws edu.ub.prog2.utils.AplicacioException
     */
    public void afegirAlbum(String string)throws AplicacioException{
        AlbumFitxersMultimedia album = new AlbumFitxersMultimedia(string);
        if(containsTitle(albums, string)){
            throw new AplicacioException("L'àlbum ja existeix");
        }
        else{
            albums.add(album);
        }
        
    }
    
    /**
     * Mètode per a poder afegir un àlbum a l'Arraylist albums. Creem un àlbum de
     * la mida passada per aprametre amb el títol que ens han passat per paràmetre. 
     * Si aquest ja es trobava a l'ArrayList, llancem una excepcio.
     * @param string
     * @param mida
     * @throws edu.ub.prog2.utils.AplicacioException
     */
    public void afegirAlbum(String string, int mida) throws AplicacioException {//suposem llavors que els àlbums són de mida 10
         AlbumFitxersMultimedia album = new AlbumFitxersMultimedia(string, mida);
        if(containsTitle(albums, string)){
            throw new AplicacioException("L'àlbum ja existeix");
        }
        else{
            albums.add(album);
        }
    }

    /**
     * Aquest mètode retorna true si la col·lecció d'àlbums passada com a paràmetre
     * en conté un amb el mateix títol que el que passen com a paràmetre.
     * @param c
     * @param titol
     * @return boolean
     */
    public static boolean containsTitle(Collection<AlbumFitxersMultimedia> c, String titol) {
    for(AlbumFitxersMultimedia o : c) {
        if(o != null && o.getTitol().equals(titol)) {
            return true;
        }
    }
    return false;
}
    
    /**
     * Molt similar al mètode mostrarBiblioteca. Creem llista i hi anem afegint
     * els atributs dels elements de l'ArrayList albums mitjançant el mètode toString()
     * ja implementat a AlbumFitxersMultimedia.
     * @return List<String> aux
     */
    public List<String> mostrarAlbums(){
        List<String> aux;
        String str = "";
        if (albums.isEmpty()) {
            String s = "\nNo hi ha cap fitxer!\n";
            aux = new ArrayList<String>(1);
            aux.add(s);   
        }
        else{
            int j = 1;
            aux = new ArrayList<String>(albums.size() + 1);
            for (int i = 0; i < albums.size(); i++) {
                str = "[" + (i+1)+ "]" + albums.get(i).toString() + "\n";
                aux.add(str);
        }
        
        
        }
        return aux;
    }
        
    /**
     * Cridem el mètode de suport buscarAlbum amb el títol de l'àlbum. Si no existeix
     * el mètode, el retorn serà -1 i p.t. llancem una AplicacioExcepcio. Altrament,
     * l'eliminem de l'ArrayList, però no de la biblioteca.
     * @param titol
     * @throws AplicacioException 
     */
    public void eliminarAlbum(String titol) throws AplicacioException{
        int pos = buscarAlbum(titol);
        if(pos == -1){
            throw new AplicacioException("L'album associat a aquest títol no existeix");
        }
        else{
            albums.remove(pos);
        }
        
        
    }
    /**
     * Afegir un fitxer a un àlbum concret. Cridem mètode de suport buscarAlbum,
     * que retorna la posició de l'àlbum amb el que volem treballar. Si el títol
     * no consta entre els àlbums de l'ArrayList, llancem excepció. En cas contrari,
     * Afegim el fitxer que es troba a una posció donada en la biblioteca.
     * Tant amb buscarAlbum com amb getAt llancem una excepció del tipus AplicacioException
     * @param posBiblioteca
     * @param titol
     * @throws AplicacioException 
     */
    public void afegirFitxerAlbum(int posBiblioteca, String titol) throws AplicacioException{
        int pos = buscarAlbum(titol);
        if(pos == -1){
            throw new AplicacioException("L'album associat a aquest títol no existeix");
        }
        try{
            albums.get(pos).addFitxer(biblioteca.getAt(posBiblioteca));
        }catch(IndexOutOfBoundsException e){
            throw new AplicacioException("Index fora de rang");
        }
 
    }
    
    /**
     * Molt similar al mètode mostrarAlbums, però únicament emplenem una posició
     * de l'ArrayList, la de l'àlbum amb el títol donat com a paràmetre.
     * @param titol
     * @return 
     */
    public List<String> mostrarAlbum(String titol){
        int pos = buscarAlbum(titol);
        List<String> aux;
        String s = "";
        if (pos == -1) {
            s = "\nNo hi ha cap fitxer!\n";
            aux = new ArrayList<String>(1);
            aux.add(s);   
        }
        else{
            ArrayList<FitxerMultimedia> album = albums.get(pos).getAlbum();
            aux = new ArrayList<String>(album.size()+1);
            for(int i = 0; i < album.size(); i ++){
                s = "[" + (i+1) + "] " + album.get(i).toString();
                aux.add(s);
            }
   
        }  
        return aux;
    }
    
    
    /**
     * Eliminem el fitxer multimèdia d'un àlbum donat el seu títol. Comprovem que
     * l'àlbum existeix amb la seva posició. Si existeix, cridem al mètode removeFitxer
     * de CarpetaFitxers. En cas de no existir l'àlbum o de no donar una posició
     * de fitxer vàlida, llancem dues possibles excepcions del tipus AplicacioException.
     * @param posFitxer
     * @param titol
     * @throws AplicacioException 
     */
    public void eliminarFitxerAlbum(int posFitxer, String titol) throws AplicacioException{//si eliminem de l'àlbum, no l'eliminem de biblioteca
        int posAlbum = buscarAlbum(titol); 
        if(posAlbum == -1){
            throw new AplicacioException("L'album associat a aquest títol no existeix");
        }
        albums.get(posAlbum).removeFitxer(posFitxer);
    }
    
    
    
    /**
     * Mètode per comprovar si existeix l'àlbum mitjançan el mètode de suport
     * buscarAlbum.
     * @param string
     * @return 
     */
    public boolean existeixAlbum(String string){
        boolean existeix = false;
        int pos = buscarAlbum(string);
        if(pos != -1){
            existeix = true;
        }
        return existeix;
    }
    
    
    
    /**
     * Per tal d'evitar haver d'escriure el codi per trobar l'índex de l'string
     * a cada mètode, implementem el buscarAlbum. Iterem pels àlbums i amb un esquema
     * de cerca while-boolean, ens aturem a l'índex que coincideixi amb el títol.
     * @param titol
     * @return 
     */
    private int buscarAlbum(String titol){
        Iterator<AlbumFitxersMultimedia> a = albums.iterator();
        boolean trobat = false;
        int pos = -1;
        int posAlbum = -1;
        while(a.hasNext() && !trobat){
            AlbumFitxersMultimedia next = a.next();
            pos++;
            if(next.getTitol().equals(titol)){
                trobat = true;
                posAlbum = pos;
                
            }
    }
        return posAlbum;
    
}
    /**
     * Aquest metode recorre la Biblioteca i assigna a tots els fitxers el reproductor
     * passat per parametre
     * @param r
     */
    public void setReproductor(Reproductor r){
        FitxerReproduible aux;
        for(int i = 0; i < biblioteca.getSize(); i++){
            aux = (FitxerReproduible) biblioteca.getAt(i);
            aux.setReproductor(r);
        }
    }
}
