/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clien.view;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package integrategui;
//
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//
///**
// * This class handles all the scene changes. In the whole game, there is only
// * one Stage that is used. That is why this class takes in the primaryStage as a
// * parameter in the constructor. All the scene changes are done on this stage.
// * Because this stage is a saved state across the game, the same SceneManager
// * object has to be used throughout the game. The implication is that every
// * public method this class provides takes in a SceneManager object as a
// * parameter. Each of these public methods goes to a separate Scene. So from the
// * Scene classes, to instigate a scene switch, any one of the provided public
// * methods can be called with the current SceneManager object passed in as a
// * parameter.
// */
//public class SceneManager  {
//
//    public static final int FRAMES_PER_SECOND = 60;
//    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
//    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
//
//    private Stage stage;
//    private Timeline animation;
//
//    public SceneManager(Stage primaryStage) {
//        this.stage = primaryStage;
//        this.animation = new Timeline();
//        stage.show();
//    }
//
//    public void goToMenuScene(SceneManager sceneManager) {
//        animation.stop();
//        Menu menu = new Menu(sceneManager);
//        Scene menuScene = menu.init(Main.SIZE, Main.SIZE);
//        stage.setScene(menuScene);
//    }
//
//    private void setGameLoop(KeyFrame frame) {
//        animation = new Timeline();
//        animation.setCycleCount(Timeline.INDEFINITE);
//        animation.getKeyFrames().add(frame);
//        animation.play();
//    }
//}
