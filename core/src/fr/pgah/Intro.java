package fr.pgah;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Intro extends ApplicationAdapter {

  // Déclaration de toutes les variables du programme
  final int NBSprite = 30;
  SpriteBatch batch; // pour pouvoir dessiner à l'écran
  Texture[] imgs; // contient les images de chaque sprite
  int[] coordonneesX; // les coordonnées en X de chaque sprite
  int[] coordonneesY; // les coordonnées en Y de chaque sprite
  int[] hauteursImgs; // la hauteur de chaque sprite
  int[] largeursImgs; // la largeur de chaque sprite
  boolean[] versLeHaut; // indique, pour chaque sprite, s'il va vers le haut ou pas
  boolean[] versLaDroite; // indique, pour chaque sprite, s'il va vers la droite ou pas
  int hauteurFenetre; // la hauteur de la fenêtre (unique, pas un tableau)
  int largeurFenetre; // la largeur de la fenêtre
  int[] vitesse;

  // Définition de la méthode create
  // C'est là qu'on initialise toutes les variables (on leur donne une valeur).
  // Exécutée une seule fois au lancement du programme.
  // Attention : c'est juste quelque chose de décidé par la bibliothèque libgdx,
  // pas quelque chose d'usuel en Java
  public void create() {
    //imgs[0] = new Texture("dar.png");
    hauteurFenetre = Gdx.graphics.getHeight();
    largeurFenetre = Gdx.graphics.getWidth();

    batch = new SpriteBatch();
    imgs = new Texture[NBSprite];
    vitesse = new int[NBSprite];
    versLaDroite = new boolean[NBSprite];
    versLeHaut = new boolean[NBSprite];
    largeursImgs = new int[NBSprite];
    hauteursImgs = new int[NBSprite];
    coordonneesY = new int[NBSprite];
    coordonneesX = new int[NBSprite];
     // type objet => instanciation (new)

     // tableau = type objet aussi ; ici : 2 Textures

    // Chaque élément du tableau est lui-même un objet (Texture)
    // Il faut donc instancier une Texture pour chaque élément
      
    for (int i = 0; i < NBSprite; i++) {
      imgs[i] = new Texture("bomb.png");
      coordonneesX[i] = 0;
      coordonneesY[i] = 0;
      hauteursImgs[i] = imgs[i].getHeight();
      largeursImgs[i] = imgs[i].getWidth();
      versLeHaut[i] = true; // au début, le 1er spite va monter
      versLaDroite[i] = true;
      vitesse[i] =4; 
    }
      imgs[0] = new Texture("dar.png");
   



  }


  // Définition de la méthode render
  // Exécutée par la bibliothèque 60 fois par secondes (pour avoir un rendu
  // fluide)
  // Attention : c'est juste quelque chose de décidé par la bibliothèque libgdx,
  // pas quelque chose d'usuel en Java
  public void render() {

    // Chacune de ces 4 lignes est un "appel de méthode"
    // Lors d'un appel de méthode, le flux d'exécution se déplace dans la méthode
    // correspondante, l'exécute complètement, puis revient à l'appelant qui
    // continue
    // son traitement
    // Ici on a donc 4 "sauts" dans les méthodes respectives

    // Ces appels n'utilisent pas la notation pointée car les méthodes se trouvent
    // dans cette classe

    reinitialiserArrierePlan();
    testerBordures();
    majCoordonnees();
    dessiner();

    // Notez comme le fait d'avoir décomposé la méthode en plusieurs autres méthodes
    // améliore la clarté et la concision de cette méhode

  }

  // Repeint l'arrière-plan
  public void reinitialiserArrierePlan() {

    // Un autre appel de méthode, cette fois vers la classe ScreenUtils,
    // code provenant de la bibliothèque libgdx qu'on n'a pas écrit.
    // Utilise la notation pointée.
    // Les paramètres de la méthode (entre parenthèses) précisent la couleur
    // souhaitée pour l'arrière-plan en RGBA (Red, Green, Blue, Alpha)
    ScreenUtils.clear(0, 0, 0, 1);
  }

  // Définition de la méthode testerBordures.
  // En vérifiant les coordonnées de chaque sprite,
  // s'assure qu'ils restent dans la fenêtre en modifiant
  // les tableaux de direction si besoin
  public void testerBordures() {

    // Pour tous les indices i de 0 à 1, faire...
    for (int i = 0; i < NBSprite; i++) {

      // Si le sprite tape en haut...
      if (coordonneesY[i] + hauteursImgs[i] >= hauteurFenetre) {
        // on retient qu'il doit maintenant aller vers le bas
        versLeHaut[i] = false;
      }

      // Si le sprite tape en bas...
      if (coordonneesY[i] < 0) {
        // on retient qu'il doit maintenant aller vers le haut
        versLeHaut[i] = true;
      }

      // Si le sprite tape à droite...
      if (coordonneesX[i] + largeursImgs[i] >= largeurFenetre) {
        // on retient qu'il doit maintenant aller vers la gauche
        versLaDroite[i] = false;
      }

      // Si le sprite tape à gauche...
      if (coordonneesX[i] < 0) {
        // on retient qu'il doit maintenant aller vers la droite
        versLaDroite[i] = true;
      }
    }
  }


  // Définition de la méthode majCoordonnees.
  // Utilise les tableaux de direction pour modifier
  // les coordonnées de chaque sprite
  private void majCoordonnees() {

    // MAJ coordonnées en X

    // Pour tous les indices de 0 à 1, faire...
    for (int i = 0; i < NBSprite; i++) {
      // Si le sprite i va vers la droite
      if (versLaDroite[i]) {
        // on augmente X
        coordonneesX[i] = coordonneesX[i] + vitesse[i]; // incrémentation
      } else { // sinon
        // on diminue X (il va vers la gauche)
        coordonneesX[i] = coordonneesX[i] - vitesse[i]; // décrémentation
      }
    }


    // MAJ coordonnées en Y

    // Pour tous les indices de 0 à 1, faire...
    for (int i = 0; i < NBSprite; i++) {
      // Si le sprite i va vers le haut
      if (versLeHaut[i]) {
        // on augmente Y
        coordonneesY[i] = coordonneesY[i] + vitesse[i]; // incrémentation
      } else { // sinon
        // on dinminue Y (il va vers la gauche)
        coordonneesY[i] = coordonneesY[i] - vitesse[i]; // décrémentation
      }
    }
  }

  // Définition de la méthode dessiner.
  // Dessine chaque sprite à l'écran au bonne coordonnées.
  private void dessiner() {
    // On indique au SpriteBatch qu'on va dessiner
    batch.begin();

    // Pour tous les indices i de 0 à 1, faire...
    for (int i = 0; i < NBSprite; i++) {
      // Dessiner le sprite i avec la bonne texture et les bonnes coordonnées
      batch.draw(imgs[i], coordonneesX[i], coordonneesY[i]);
    }

    // On indique au SpriteBatch qu'on a fini de dessiner
    batch.end();
  }
}

