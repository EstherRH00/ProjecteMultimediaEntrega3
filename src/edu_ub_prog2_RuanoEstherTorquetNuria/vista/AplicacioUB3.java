package edu_ub_prog2_RuanoEstherTorquetNuria.vista;

import edu.ub.prog2.RuanoEstherTorquetNuria.controlador.Controlador;
import edu.ub.prog2.utils.AplicacioException;

import edu.ub.prog2.utils.Menu;

import java.util.List;
import java.util.Scanner;

/**
 * @author Esther Ruano Hortoneda
 * @author Nuria Torquet Luna 
 * 
 * La classe AplicacioUB conté la lògica del
 * programa. Crea un objecte del tipus Menu i un del tipus Controlador. Cada
 * opció del menu respon a un mètode la classe Controlador. AplicacioUB recull
 * la informació per fer les crides pertinents als mètodes de Controlador. És la
 * classe amb la qual interactua l'usuari.
 */
public class AplicacioUB3 {

    /**
     * Atributs de la classe, creem variable de referència de tipus controlador
     * per a la crida de mètodes, tres enums per a gestionar les opcions del
     * menú i les respectives descripcions de cada element dels enums.
     */
    //Atributs
    private Controlador controlador;

    static private enum OpcionsMenuPrincipal {
        GESTIO_BIBLIOTECA, GESTIO_ALBUMS, CONTROL_REPRODUCCIO_VISIO, GUARDAR_DADES, RECUPERAR_DADES, SORTIR
    };

    static private enum OpcionsFitxer {
        ADD_FITXER, SHOW_BIBLIOTECA, REMOVE_FITXER, TORNAR_ENRERE
    };

    static private enum OpcionsTipusFitxer {
        ADD_VIDEO, ADD_AUDIO, TORNAR_ENRERE
    };

    static private enum OpcionsAlbums {
        ADD_ALBUM, SHOW_ALBUMS, REMOVE_ALBUM, MANAGE_ALBUM, TORNAR_ENRERE
    };

    static private enum OpcionsGestioAlbum {
        ADD_FITXER, SHOW_ALBUM, REMOVE_FITXER, TORNAR_ENRERE
    };

    static private enum OpcionsControlReproductor {
        PLAY_FILE, PLAY_BIBLIOTECA, PLAY_ALBUM, ON_OF_CONTINUE_PLAY, ON_OFF_SHUFFLE_PLAY, MANAGE_CURRENT_PLAY, TORNAR_ENRERE//Extra, no està a la pràctica
    };

    static private enum OpcionsReproduccio {
        CONTINUE, PAUSE, STOP, SKIP, TORNAR_ENRERE
    };

// Descripcio del menu principal
    private static String[] descMenuPrincipal = {
        "Gestió biblioteca",
        "Gestió dels àlbums",
        "Control de la reproducció i visió",
        "Guardar dades",
        "Recuperar dades",
        "Sortir"
    };

    // Descripció subMenu
    private static String[] descFitxer = {
        "Afegir fitxer multimèdia a la biblioteca",
        "Mostrar biblioteca",
        "Eliminar fitxer multimèdia",
        "Tornar enrere"
    };

    // Descripció subSubMenu
    private static String[] descTipusFitxer = {
        "Afegir vídeo",
        "Afegir àudio",
        "Tornar enrere"
    };
    private static String[] descAlbums = {
        "Afegir àlbum",
        "Mostrar àlbums",
        "Eliminar àlbum",
        "Gestionar àlbum",
        "Tornar enrere"
    };
    private static String[] descGestioAlbum = {
        "Afegir fitxer multimèdia",
        "Mostrar àlbum",
        "Eliminar fitxer multimèdia",
        "Tornar enrere"
    };
    private static String[] descControlReproductor = {
        "Reproduir un fitxer reproduïble",
        "Reproduir tota la biblioteca",
        "Reproduir un àlbum",
        "Activar/desactivar reproducció continua",
        "Activar/desactivar reproducció aleatòria",
        "Gestió reproducció en curs",
        "Tornar enrere"
    };
    private static String[] descOpcionsReproduccio = {
        "Re-emprèn",
        "Pausa",
        "Atura",
        "Salta",
        "Tornar enrere"
    };
    
    
    

    /**
     * Constructor sense paràmentres o s'instancia el controlador.
     */
    public AplicacioUB3() {
        //AplicacioUB2 (component vista) declararà un atribut de tipus Controlador que haurà de ser inicialitzat en el constructor de AplicacioUB3. 
        controlador = new Controlador();
    }

    /**
     * Mètode principal: Crea l'Scanner, instancia l'aplicacioUB1 i crida al
     * mètode manager d'aquesta. {@link #manager(java.util.Scanner)}
     */
    public void gestioAplicacioUB() {
        // atributs i variables
        Scanner sc = new Scanner(System.in);
        // Creem un objecte principal
        this.managerMenuPrincipal(sc);
    }

    /**
     * El mètode manager s'encarrega de la gestió del menú principal.
     * Inicialitzem l'objecte menu amb l'enum OpcionsMenuPrincipal i cridem el
     * mètode setDescripcions de la classe Menu per tal d'associar cada
     * descripció amb l'element de l'enum corresponent. A continuació imprimim
     * les opcions, inicialitzem una variable del tipus OpcionsMenuPrincipal i
     * demanem a l'usuari que n'esculli una. Cridem els mètodes privats de
     * suport depenent de l'opció escollida.
     */
    private void managerMenuPrincipal(Scanner sc) {

        // Creem l'objecte per al menu. Li passem com a primer parametre el nom del menu
        Menu<OpcionsMenuPrincipal> menu = new Menu<OpcionsMenuPrincipal>("Menu Principal", OpcionsMenuPrincipal.values());

        // Assignem la descripcio de les opcions
        menu.setDescripcions(descMenuPrincipal);

        // Obtenim una opcio des del menu i fem les accions pertinents
        OpcionsMenuPrincipal opcio = null;
        do {
            // Mostrem les opcions del menu
            menu.mostrarMenu();

            // Demanem una opcio
                opcio = (OpcionsMenuPrincipal)getOption(sc, 0, OpcionsMenuPrincipal.values().length);
            

            // Fem les accions necessaries
            switch (opcio) {
                case GESTIO_BIBLIOTECA:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 1: Gestió biblioteca");
                    //implementar opcio: crida al subMenu
                    managerFitxer(sc);
                    break;
                case GESTIO_ALBUMS:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 2: Gestió dels àlbums");
                    //implementar opcio: crida al subMenu
                    managerAlbum(sc);
                    break;
                case CONTROL_REPRODUCCIO_VISIO:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 3: Control de la reproducció i visió");
                    //implementar opcio: crida al subMenu
                    managerControlReproductor(sc);
                    break;
                case GUARDAR_DADES:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 4: Guardar dades");
                    //implementar opcio: guardar dades
                    guardarDades(sc);
                    break;
                case RECUPERAR_DADES:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 5: Recuperar dades");
                    //implementar opcio: crecuperar dades
                    recuperarDades(sc);
                    break;
                case SORTIR:
                    if(controlador.finestraOberta()){
                        try{
                            controlador.tancarFinestraReproductor();
                        }catch(AplicacioException e){
                            System.out.println("Hi ha hagut un error a l'hora de tancar la finestra de reproducció");
                        }
                    }      
                    System.out.println("Fins aviat!");
                    break;
            }
        } while (opcio != OpcionsMenuPrincipal.SORTIR);
    }

    /**
     * Gestió del subMenu, mateix raonament que amb manager()
     */
    private void managerFitxer(Scanner sc) {

        // Creem l'objecte per al menu. Li passem com a primer parametre el nom del menu
        Menu<OpcionsFitxer> menu = new Menu<>("Menu Secundari 1", OpcionsFitxer.values());

        // Assignem la descripcio de les opcions
        menu.setDescripcions(descFitxer);

        OpcionsFitxer opcio = null;

        // Mostrem les opcions del menu
        do {
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = (OpcionsFitxer)getOption(sc, 1, OpcionsFitxer.values().length);

            // Fem les accions necessaries
            //Com que no té opció "tornar enrere", només l'executem una vegada
            switch (opcio) {
                case ADD_FITXER:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 1.1: Afegir fitxer multimèdia a la biblioteca");
                    //implementar opcio: crida al subSubMenu
                    managerTipusFitxer(sc);
                    break;
                case SHOW_BIBLIOTECA:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 1.2: Mostrar biblioteca");
                    //implementar opcio: mostrar biblioteca
                    List<String> aux = controlador.mostrarBiblioteca();
                    System.out.println(aux);
                    break;
                case REMOVE_FITXER:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 1.3: Eliminar fitxer multimèdia");
                    //implementar opcio: eliminar fitxer
                    removeFitxer(sc);
                    break;
                case TORNAR_ENRERE:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 1.4: Tornar al menú principal");
                    break;
            }
        } while (opcio != OpcionsFitxer.TORNAR_ENRERE);
    }

    /**
     * Gestió del subMenu, mateix raonament que amb manager()
     */
    private void managerTipusFitxer(Scanner sc) {

        // Creem l'objecte per al menu. Li passem com a primer parametre el nom del menu
        Menu<OpcionsTipusFitxer> menu = new Menu<>("Menu Terciari 1", OpcionsTipusFitxer.values());

        // Assignem la descripcio de les opcions
        menu.setDescripcions(descTipusFitxer);

        // Obtenim una opcio des del menu i fem les accions pertinents
        OpcionsTipusFitxer opcio = null;
        do {
            // Mostrem les opcions del menu
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = (OpcionsTipusFitxer)getOption(sc, 2, OpcionsTipusFitxer.values().length);

            // Fem les accions necessaries
            switch (opcio) {
                case ADD_VIDEO:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 1.1.1: Afegir vídeo");
                    //implementar opcio: afegir video
                    addVideo(sc);
                    break;
                case ADD_AUDIO:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 1.1.2: Afegir àudio");
                    //implementar opcio: afegir audio
                    addAudio(sc);
                    break;
                case TORNAR_ENRERE:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 1.1.3: Tornar al menú anterior");
                    break;
            }
        } while (opcio != OpcionsTipusFitxer.TORNAR_ENRERE);
    }

    private void managerAlbum(Scanner sc) {//ADD_ALBUM, SHOW_ALBUM, REMOVE_ALBUM, MANAGE_ALBUM, TORNAR_ENRERE

        // Creem l'objecte per al menu. Li passem com a primer parametre el nom del menu
        Menu<OpcionsAlbums> menu = new Menu<>("Menu Secundari 2", OpcionsAlbums.values());

        // Assignem la descripcio de les opcions
        menu.setDescripcions(descAlbums);

        // Obtenim una opcio des del menu i fem les accions pertinents
        OpcionsAlbums opcio = null;
        do {
            // Mostrem les opcions del menu
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = (OpcionsAlbums)getOption(sc, 3, OpcionsAlbums.values().length);

            // Fem les accions necessaries
            switch (opcio) {
                case ADD_ALBUM:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 2.1: Afegir àlbum");
                    //implementar opcio: afegir àlbum
                    afegirAlbum(sc);
                    break;
                case SHOW_ALBUMS:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 2.2: Mostrar àlbums");
                    //implementar opcio: mostrar àlbum
                   List<String> aux = controlador.mostrarLlistatAlbums();
                    System.out.println(aux);
                    break;
                case REMOVE_ALBUM:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 2.3: Eliminar àlbum");
                    //implementar opcio: eliminar àlbum
                   esborrarAlbum(sc);
                    break;
                case MANAGE_ALBUM:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 2.4: Gestionar àlbum");
                    //implementar opci0o: gestionar àlbum                    
                    managerGestioAlbum(sc);
                    break;
                case TORNAR_ENRERE:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 2.5: Tornar al menú anterior");
                    break;
            }
        } while (opcio != OpcionsAlbums.TORNAR_ENRERE);
    }
    private void managerGestioAlbum(Scanner sc) {//ADD_FITXER, SHOW_ALBUM, REMOVE_FITXER, TORNA_ENRERE

        // Creem l'objecte per al menu. Li passem com a primer parametre el nom del menu
        Menu<OpcionsGestioAlbum> menu = new Menu<>("Menu Terciari 2", OpcionsGestioAlbum.values());

        // Assignem la descripcio de les opcions
        menu.setDescripcions(descGestioAlbum);
        // Obtenim una opcio des del menu i fem les accions pertinents
        OpcionsGestioAlbum opcio = null;
        String titolAlbum = selectAlbum(sc); // retorna l'àlbum que volem gestionar
        
        if(!titolAlbum.equals("n")){ 
        do {
            // Mostrem les opcions del menu
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = (OpcionsGestioAlbum)getOption(sc, 4, OpcionsGestioAlbum.values().length);
            


            // Fem les accions necessaries
            switch (opcio) {
                case ADD_FITXER:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 2.4.1: Afegir fitxer multimèdia");
                    //implementar opcio: afegir fitxer multimèdia
                    afegirFitxerAlbum(titolAlbum, sc);
                    break;
                case SHOW_ALBUM:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 2.4.2: Mostrar àlbum");
                    //implementar opcio: mostrar àlbum
                    List<String> aux = controlador.mostrarAlbum(titolAlbum);
                    System.out.println(aux);
                    break;   
                case REMOVE_FITXER:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 2.4.2: Eliminar fitxer multimèdia");
                    //implementar opcio: eliminar fitxer multimèdia
                    eliminarFitxerAlbum(titolAlbum, sc);
                    break;    
                case TORNAR_ENRERE:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 2.4.3: Tornar al menú anterior");
                    break;
            }
        } while (opcio != OpcionsGestioAlbum.TORNAR_ENRERE);
    }
    }
    
    private void managerControlReproductor(Scanner sc) {//PLAY_FILE, PLAY_BIBLIOTECA, PLAY_ALBUM, ON_OF_CONTINUE_PLAY, ON_OFF_SHUFFLE_PLAY, MANAGE_CURRENT_PLAY, TORNAR_ENRERE

        // Creem l'objecte per al menu. Li passem com a primer parametre el nom del menu
        Menu<OpcionsControlReproductor> menu = new Menu<>("Menu Secundari 3", OpcionsControlReproductor.values());

        // Assignem la descripcio de les opcions
        menu.setDescripcions(descControlReproductor);

        // Obtenim una opcio des del menu i fem les accions pertinents
        OpcionsControlReproductor opcio = null;
        do {
            // Mostrem les opcions del menu
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = (OpcionsControlReproductor)getOption(sc, 5, OpcionsControlReproductor.values().length);

            // Fem les accions necessaries
            switch (opcio) {
                case PLAY_FILE:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 3.1: Reproduir fitxer reproduïble");
                    //implementar opcio:reproduir fitxer reproduïble
                    reproduirFitxer(sc);
                    break;
                case PLAY_BIBLIOTECA:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 3.2: Reproduir tota la biblioteca");
                    //implementar opcio: reproduir tota la biblioteca
                    reproduirBiblioteca();
                    break;
                case PLAY_ALBUM:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 3.3: Reproduir un àlbum");
                    //implementar opcio: Reproduir un àlbum
                    reproduirAlbum(sc);
                    break;
                case ON_OF_CONTINUE_PLAY:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 3.4: Activar/desactivar reproducció continua");
                    //implementar opcio: Activar/desactivar reproducció continua
                    boolean ciclica = controlador.OnOffCiclica();
                    if(ciclica) {System.out.println("Reproducció cíclica activada");}
                    else {System.out.println("Reproducció cíclica desactivada");}
                    break;
                case ON_OFF_SHUFFLE_PLAY:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 3.5: Activar/desactivar reproducció aleatòria");
                    //implementar opcio: Activar/desactivar reproducció aleatòria
                    boolean aleatoria = controlador.OnOffAleatoria();
                    if(aleatoria) {System.out.println("Reproducció aleatòria activada");}
                    else {System.out.println("Reproducció aleatòria desactivada");}
                    break;    
                case MANAGE_CURRENT_PLAY:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 1.1.2: Gestió reproducció en curs");
                    //implementar opcio: Gestió reproducció en curs
                    managerReproduccio(sc);
                    break;
                case TORNAR_ENRERE:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 1.1.3: Tornar al menú anterior");
                    break;
            }
        } while (opcio != OpcionsControlReproductor.TORNAR_ENRERE);
    }
    private void managerReproduccio(Scanner sc) {//CONTINUE, PAUSE, STOP, SKIP, TORNAR_ENRERE

        // Creem l'objecte per al menu. Li passem com a primer parametre el nom del menu
        Menu<OpcionsReproduccio> menu = new Menu<>("Menu Terciari 3", OpcionsReproduccio.values());

        // Assignem la descripcio de les opcions
        menu.setDescripcions(descOpcionsReproduccio);

        // Obtenim una opcio des del menu i fem les accions pertinents
        OpcionsReproduccio opcio = null;
        do {
            // Mostrem les opcions del menu
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = (OpcionsReproduccio)getOption(sc, 6, OpcionsReproduccio.values().length);

            // Fem les accions necessaries
            switch (opcio) {
                case CONTINUE:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 3.6.1: Re-emprèn reproducció");
                    //implementar opcio: re-emprèn reproducció
                    try{
                        controlador.reemprenReproduccio();
                    } catch (AplicacioException e) {
                    System.out.println(e.getMessage());
                    }
                    break;
                case PAUSE:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 3.6.2: Pausa reproducció");
                    //implementar opcio: pausa
                    try{
                        controlador.pausaReproduccio();
                    } catch (AplicacioException e) {
                    System.out.println(e.getMessage());
                    }
                    break;
                case STOP:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 3.6.3: Atura reproducció");
                    //implementar opcio: atura
                    try{
                        controlador.aturaReproduccio();
                    }catch(AplicacioException e) {
                    System.out.println(e.getMessage());
                    }
                    break; 
                
                case SKIP:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 3.6.4: Salta reproducció");
                    //implementar opcio: salta
                    try{
                        controlador.saltaReproduccio();
                    }catch(AplicacioException e) {
                    System.out.println(e.getMessage());
                    }
                    break;     
                    
                case TORNAR_ENRERE:
                    // Mostrem un missatge indicant que s'ha triat aquesta opcio
                    System.out.println("Has triat l'opció 3.6.5: Tornar al menú anterior");
                    break;
            }
        } while (opcio != OpcionsReproduccio.TORNAR_ENRERE);
    }
    
    
    
    
    

    /**
     * ******************************************************************
     */
    /**
     * *********** Metodes per guardar/recuperar les dades  ************
     */
    /**
     * ******************************************************************
     */
    /**
     * Demanem a l'usuari el camí on vol guardar el seu fitxer. Intentem cridar
     * el mètode destinat a aquesta funció de la classe controlador. En cas de
     * reportar-se qualsevol error, és rep un error de la classe
     * AplicacioException
     *
     * @param sc
     */
    private void guardarDades(Scanner sc) {
        String camiDesti;
        System.out.println("On vols guardar les dades? ");
        camiDesti = sc.next();
        try {
            controlador.guardarDadesDisc(camiDesti);
            System.out.println("Dades guardades amb èxit!\n");
        } catch (AplicacioException e) {
            System.out.println(e.getMessage());

        }
    }

    /**
     * El mateix raonament que amb guardarDades()
     *
     * @param sc
     */
    private void recuperarDades(Scanner sc) {
        String camiOrigen;
        System.out.println("Introdueix el camí d'on vols recuperar les dades");
        camiOrigen = sc.next();
        try {
            controlador.carregarDadesDisc(camiOrigen);
            System.out.println("Dades recuperades amb èxit!\n");
        } catch (AplicacioException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * ******************************************************************
     */
    /**
     * *************** Metodes per gestionar fitxers  ******************
     */
    /**
     * ******************************************************************
     */
    /**
     * Mètode de suport per a esborrar fitxer de la carpetaFitxers. Donem dues
     * opcions, si l'usuari coneix l'índex del fitxer simplement demanem que
     * introdueixi l'índex i cridem el mètode pertinent de la classe
     * Controlador. En cas contrari, imprimim tots els elements de la
     * carpetaFitxers amb el mètode mostrarCarpeta de Controlador i a
     * continuació demanem l'índex. En tot moment, si reportem qualsevol error,
     * tenim l'estructura try-catch en la crida dels mètodes de Controlador per
     * tal d'imprimir l'error que s'ha reportat. Si l'usuari no introdueix una
     * de les dues lletres(S o N) se li demana indefinidament que introdueixi N
     * o S fins que fagi cas.
     *
     * @param sc
     */
    private int removeFitxer(Scanner sc) {
        String tria;
        int index;
        //preguntem per l'índex i facilitem input
        System.out.println("Coneixes l'índex del fitxer que vols eliminar? (S/N)");
        tria = sc.next();
        while (!tria.equals("S") && !tria.equals("N")) {
            System.out.println("Introdueix S o N");
            tria = sc.next();
        }
        if (tria.equals("N")) {
            List<String> aux = controlador.mostrarBiblioteca();
            System.out.println(aux);
        }
        System.out.println("Introdueix l'índex del fitxer a eliminar? (S/N)");
        index = onlyIntegers(sc);
        
            try {
                //i l'esborrem
                controlador.esborrarFitxer(index-1);
                System.out.println("Fitxer eliminat amb èxit!\n");
            //si no, AplicacioExcepcio    
            } catch (AplicacioException e) {
                System.out.println(e.getMessage());
        }
        
        return index;
    }
    

 

    /**
     * Mètode de suport per a afegir un vídeo a la carpetaFitxers. Demanem a
     * l'usuari els atributs corresponents al vídeo(cami, descripcio, codec,
     * durada...) i provem de cridar el mètode afegirVideo de la classe
     * Controlador. En cas de no ser possible, tenim l'estructura try-catch per
     * gestionar l'excepció i imprimir l'error per pantalla.
     *
     * @param sc
     */
    private void addVideo(Scanner sc) {
        String cami, codec, descripcio;
        float durada, fps;
        int amplada, alcada;
        
        
        System.out.println("Introdueix el camí del vídeo");
        cami = sc.next();
        sc.nextLine();//neteja tot allò que hagi quedat i no serveixi en el next()      
        System.out.println("Introdueix la descripció del vídeo");
        descripcio = sc.nextLine();//aquí ja el tenim net per afegir línea
        codec = extractCodec(cami);
        System.out.println("Introdueix la durada del vídeo");
        durada = onlyFloats(sc);
        System.out.println("Introdueix l'alçada i l'amplada del vídeo, separats per espais");
        alcada = onlyIntegers(sc);
        amplada = onlyIntegers(sc);
        System.out.println("Introdueix les fps del vídeo");
        fps = onlyFloats(sc);
        try {
            controlador.afegirVideo(cami, descripcio, codec, durada, alcada, amplada, fps);
            System.out.println("Fitxer afegit amb èxit!\n");
        } catch (AplicacioException e) {

            System.out.println(e.getMessage());
        }
    }

    /**
     * Mètode de suport per a afegir un àudio a la carpetaFitxers. Demanem a
     * l'usuari els atributs corresponents al vídeo(cami, descripcio, codec,
     * durada...) i provem de cridar el mètode afegirVideo de la classe
     * Controlador. En cas de no ser possible, tenim l'estructura try-catch per
     * gestionar l'excepció i imprimir l'error per pantalla.
     *
     * @param sc
     */
    private void addAudio(Scanner sc) {
        String cami, camiImatge, codec, descripcio;
        float durada;
        int kbps;

        System.out.println("Introdueix el camí de l'àudio");
        cami = sc.next();
        
        System.out.println("Introdueix el camí de la imatge de l'àudio");
        camiImatge = sc.next();
        sc.nextLine();
        System.out.println("Introdueix la descripció de l'àudio");
        descripcio = sc.next();
        codec = extractCodec(cami);
        System.out.println("Introdueix la durada del àudio");
        durada = onlyFloats(sc);
        System.out.println("Introdueix els kbps de l'àudio");
        kbps = onlyIntegers(sc);

        try {
            controlador.afegirAudio(cami, camiImatge, descripcio, codec, durada, kbps);
            System.out.println("Fitxer afegit amb èxit!\n");
        } catch (AplicacioException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * ******************************************************************
     */
    /**
     * *************** Metodes per gestionar àlbums  ******************
     */
    /**
     * ******************************************************************
     */
    /**
     * Demanem a l'usuari el títol de l'àlbum i cridem al mètode afegirAlbum de
     * controlador. En cas de ja existir aquest titol, rebem una excepció
     *
     * @param sc
     */
    private void afegirAlbum(Scanner sc) {
        String titol, tria;
        int mida;
        System.out.println("Introdueix el títol de l'àlbum que vols crear");
        titol = sc.next();
        System.out.println("Vols que l'àlbum sigui de 10 fitxers) (S/N)");
        tria = sc.next();
        while (!tria.equals("S") && !tria.equals("N")) {
            System.out.println("Introdueix S o N");
            tria = sc.next();
        }
        if (tria.equals("N")) {
            System.out.println("De quina mida vols l'àlbum?");
            mida = onlyIntegers(sc);
            try {
                controlador.afegirAlbum(titol, mida);
                System.out.println("Àlbum afegit amb èxit");
            } catch (AplicacioException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                controlador.afegirAlbum(titol);
                System.out.println("Àlbum afegit amb èxit");
            } catch (AplicacioException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    /**
     * Demanem el títol de l'àlbum a l'usuari. Provem d'esborar-lo cridant al mètode
     * esborrarAlbum de controlador. En cas de no trobar-se l'àlbum amb aquest títol,
     * rebem una excepció.
     * @param sc 
     */
    private void esborrarAlbum(Scanner sc){
        String tria, titol;
        //preguntem pel títol i facilitem input
        System.out.println("Coneixes el títol de l'àlbum que vols eliminar? (S/N)");
        tria = sc.next();
        while (!tria.equals("S") && !tria.equals("N")) {
            System.out.println("Introdueix S o N");
            tria = sc.next();
        }
        if (tria.equals("N")) {
            List<String> aux = controlador.mostrarLlistatAlbums();
            System.out.println(aux);
        }
        System.out.println("Introdueix el títol de l'àlbum que vols eliminar");
        titol = sc.next();
        try{
            controlador.esborrarAlbum(titol);
            System.out.println("Àlbum eliminat amb èxit");
        }catch(AplicacioException e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Mètode per a triar un àlbum d'entre els que hi han. Imprimim tots els
     * àlbums i demanem a l'usuari que ens introdueixi el títol. Si el títol existeix, 
     * el retornem. En cas contrari, indiquem que el títol no és vàlid i assignem
     * a l'string títol un char "n" per a que no entri en el submenu managerGestioAlbum
     * @param sc
     * @return 
     */
     private String selectAlbum(Scanner sc){
         System.out.println("Escull un àlbum de la llista mitjançat el seu nom");
         List<String> aux = controlador.mostrarLlistatAlbums();
         System.out.println(aux);
        String titol = sc.next();
        if(controlador.existeixAlbum(titol)){
            return titol;
        }
        else{
            System.out.println("Títol no vàlid");
         return "n";
     }
    }
    
    /**
     * Mètode per afegir un fitxer a l'àlbum indicat. Passem com a paràmetres el títol
     * de l'àlbum, imprimim per pantalla tots els fitxers disponibles a la biblioteca.
     * Demanem l'índex del fitxer. Provem de cridar el mètode afegirFitxer de 
     * controlador. En cas de que l'índex del fitxer surti de rang, rebem excepció.
     * @param titol
     * @param sc 
     */
    private void afegirFitxerAlbum(String titol, Scanner sc){
        List<String> aux = controlador.mostrarBiblioteca();
        System.out.println(aux);
        System.out.println("Introdueix l'índex del fitxer que vols guardar");        
        int pos = onlyIntegers(sc);
        try{
            controlador.afegirFitxer(titol, pos-1);
            System.out.println("Fitxer afegit amb èxit");
        }catch(AplicacioException e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * Mètode per eliminar fitxer d'un àlbum concret, no de la biblioteca. Demanem
     * índex del fitxer que es vol eliminar, imprimim el conjunt de fitxers de la
     * biblioteca amb el mètode mostrarAlbum de controlador. Provem d'esborra
     * el fitxer amb el mètode de controlador esborrarFitxer. En cas que l'índex
     * no existeixi, rebem una excepció.
     * @param titol
     * @param sc 
     */
    private void eliminarFitxerAlbum(String titol, Scanner sc){
        System.out.println("Introdueix l'índex del fitxer que vols eliminar");
        List<String> aux = controlador.mostrarAlbum(titol);
        System.out.println(aux);
        int pos = onlyIntegers(sc);
        try{
            controlador.esborrarFitxer(titol, pos-1);
            System.out.println("Fitxer eliminat amb èxit");
        }catch(AplicacioException e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * ******************************************************************
     */
    /**
     * *************** Metodes per gestionar reproducció  ***************
     */
    /**
     * ******************************************************************
     */
    
    /**
     * Demanem la posició del fitxer en la biblioteca  i cridem el mètode
     * reproduirFitxer(int index) de la classe Controlador. En cas de no ser
     * correcte, rebem una excepció i l'imprimim per pantalla.
     * 
     * @param sc 
     */
    private void reproduirFitxer(Scanner sc){
        System.out.println("Introdueix l'índex del fitxer que vols reproduir");
        int indxFile = onlyIntegers(sc);
        try{
            controlador.reproduirFitxer(indxFile-1);
        }catch(AplicacioException e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Cridem el mètode reproduirCarpeta() de Controlador per a reproduir tota 
     * la biblioteca de fitxers. En cas de sorgir un error, rebem 
     * una excepció i l'imprimim per pantalla.
     */
    private void reproduirBiblioteca(){
        try{
            controlador.reproduirCarpeta();
        }catch(AplicacioException a){
            System.out.println(a.getMessage());
        }
    }
    
    /**
     * Demanem el títol de l'àlbum que es vol reproduir i cridem el mètode 
     * reproduirCarpeta(String titol) de la classe Controlador per a reproduir
     * tots els fitxers de l'àlbum associat. En cas de sorgir un error, rebem 
     * una excepció i l'imprimim per pantalla.
     * @param sc 
     */
    private void reproduirAlbum(Scanner sc){
        System.out.println("Introduieix el títol de l'àlbum que vols reproduir");
        String titol = sc.next();
        try{
            controlador.reproduirCarpeta(titol);
        }catch(AplicacioException e){
            System.out.println(e.getMessage());
        }
    }
    
     /**
     * ******************************************************************
     */
    /**
     * ****** Metodes per gestionar els errors d'input de l'user  *******
     */
    /**
     * ******************************************************************
     */
    
    /**
     * Mètode per a evitar errors a causa de l'input de l'usuari. En cas que
     * l'string introduit no sigui un double, retornem false
     * @param testDouble
     * @return 
     */
    private float onlyFloats(Scanner sc){       
        
        float number;
        boolean first = true;
        do {
            if(!first) System.out.print("Input no vàlid, torna a introduir l'índex -> ");
            first = false;
            while (!sc.hasNextFloat()) {
                System.out.print("Això no és un nombre, torna a intentar-ho! -> ");
                sc.next(); 
                }
            number = sc.nextFloat();
        } while (number <= 0);
        return number;            
}
    
    /**
     * Mètode per a evitar errors a causa de l'input de l'usuari. En cas que
     * l'string introduit no sigui un double, retornem false
     * @param testDouble
     * @return 
     */
    private int onlyIntegers(Scanner sc, int range){        
        int number;
        do {
            System.out.print("Introdueix l'opció desitjada -> ");
            while (!sc.hasNextInt()) {
                System.out.print("Això no és un nombre, torna a intentar-ho! -> ");
                sc.next(); 
                }
            number = sc.nextInt();
            
        } while (number <= 0 || number > range);
        return number;
}
    /**
     * Mètode per a evitar errors a causa de l'input de l'usuari. En cas que
     * l'string introduit no sigui un double, retornem false
     * @param testDouble
     * @return 
     */
    private int onlyIntegers(Scanner sc){
        boolean first = true;
        int number;
        do {
            if(!first) System.out.print("Input no vàlid, torna a introduir l'índex -> ");
            first = false;
            while (!sc.hasNextInt()) {
                System.out.print("Això no és un nombre, torna a intentar-ho! -> ");
                sc.next(); 
                }
            number = sc.nextInt();
        } while (number < 0);
        return number;
}
    /**
     * Mètode de suport per no haver de demanar a l'usuari dos vegades pel codec.
     * Simplement utilitzem la funció substring per separar les últimes lletres
     * després de l'últim punt i guardar-ho com a còdec.
     * En cas de no haver-hi extensió en el path, es retorna un string buit.
     * @param path
     * @return extension
     */
    private String extractCodec( String path){
        
        String extension= path.substring(path.lastIndexOf(".")+1);
        
        return extension;
    }   
        
    /**
     * Mètode que sobreescriu getOpcio de la classe Menu. Mitjançant la sobreescriptura,
     * evitem així que el projecte s'aturi en cas d'introduir una opció diferent
     * d'un integer.
     */
    private Object getOption(Scanner sc, int num, int range){
        int opt = onlyIntegers(sc, range) -1;
        if(num == 0) return OpcionsMenuPrincipal.values()[opt]; 
        if(num == 1) return OpcionsFitxer.values()[opt];
        if(num == 2) return OpcionsTipusFitxer.values()[opt];
        if(num == 3) return  OpcionsAlbums.values()[opt];
        if(num == 4) return OpcionsGestioAlbum.values()[opt];
        if(num == 5) return OpcionsControlReproductor.values()[opt];
        if(num == 6) return OpcionsReproduccio.values()[opt] ;
        else{
            return null;
        }
      
    }
}


