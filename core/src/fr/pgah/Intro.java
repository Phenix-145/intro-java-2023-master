package fr.pgah;



import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Intro extends ApplicationAdapter {
  SpriteBatch batch; //définition d'une variable
  // SpriteBatch type de variable -si commence par maj varible type object

  final int NB_SPRITES = 2;
  Texture[] imgs;

  double facteurTaille = 0.39;
  int vitesse = 3;
  int hauteurFenetre; 
  int largeurFenetre;
  int hauteurImage;
  boolean[] deplacementx;                      //int dircetionENx;
  boolean[] deplacementy;                      //int dircetionENy;
  int[] coordonneesY;
  int[] coordonneesX;
  int longueurEffective;
  int hauteurEffective; 
  // teste
  

  @Override
  public void create() {  
  // create() nom
  batch = new SpriteBatch();
  imgs = new Texture[2];
                                 //méthode
    imgs [NB_SPRITES] = new Texture("prototipe 3.jpg") ;//corp de méthode
    longueurEffective = (int) (this.imgs.getWidth() * facteurTaille);
    hauteurEffective = (int) (this.imgs.getHeight() * facteurTaille);
    coordonneesY[0] = 100;
    coordonneesX[0] = 100;
    hauteurFenetre = Gdx.graphics.getHeight();
    largeurFenetre = Gdx.graphics.getWidth();
    hauteurImage = hauteurEffective;

    

  }

  

  @Override
  public void render() {
    fondecran();
     //appel de méthode sur l'object batch
    //y ++;
    // x ++;
    testetBordures();
    déplacement();
    dessiner();
  }



  private void fondecran() {
    ScreenUtils.clear(0, 0, 0, 1);
  }



  private void dessiner() {
    batch.begin();
    batch.draw(imgs, coordonneesY,  coordonneesX, longueurEffective, hauteurEffective);
    batch.end();
  }



  private void déplacement() {
    for (int i =0; i <coordonneesX.length;i++){
    if (deplacementx[i]){  
        coordonneesX[i] = coordonneesX[i] + vitesse;
      }else{
        coordonneesX[i] = coordonneesX[i] -vitesse;}
      }
      for (int i =0; i <coordonneesY.length;i++){
    if (deplacementy[i]){
      coordonneesY[i] = coordonneesY[i] +vitesse;} 
      else{
        coordonneesY[i] = coordonneesY[i] -vitesse;
      } }
  }



  private void testetBordures() {
    testebordureverticale();
    testebordurehorizontale();
  }



  private void testebordurehorizontale() {
    for (int i =0; i <coordonneesY.length;i++){
    if (coordonneesY[i] + longueurEffective >= largeurFenetre){
      deplacementy[i] = false;
    }
    if(coordonneesY[i] <= 0){
      deplacementy[i]=true;}
    }
  }



  private void testebordureverticale() {
    for (int i =0; i <coordonneesX.length;i++) {
    if (coordonneesX[i] + hauteurEffective >= hauteurFenetre){
      deplacementx[i]=false;
    }
    if (coordonneesX[i] <= 0){
      deplacementx[i]=true;}
    }
  }
}
