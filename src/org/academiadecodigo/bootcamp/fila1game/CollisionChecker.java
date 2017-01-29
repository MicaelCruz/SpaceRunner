package org.academiadecodigo.bootcamp.fila1game;

import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.GameObjects;
import org.academiadecodigo.bootcamp.fila1game.Representables.GameObjects.Obstacle1;
import org.academiadecodigo.bootcamp.fila1game.Representables.Player;
import org.academiadecodigo.bootcamp.fila1game.SimpleGFX.SimpleGfxPlayer;

public class CollisionChecker {

    private GameObjects[] objects;
    private Obstacle1 obstacle1;

    public CollisionChecker(/*GameObjects[] object*/ Obstacle1 obstacle1) {
        /*objects = object;*/
        this.obstacle1 = obstacle1;
    }

    public void checkCollision(SimpleGfxPlayer player) {

        // Distance between the center of each object
        int distanceOnX = getObjectCenterX() - getPlayerCenterX(player);
        int distanceOnY = getObjectCenterY() - getPlayerCenterY(player);

//        System.out.println("Distance on X :" + distanceOnX);
       // System.out.println("Distance on Y :" + distanceOnY);

        int playerInnerDistanceOnX = player.getWidth() / 2;
        int objectInnerDistanceOnX = obstacle1.getWidth() / 2;
        int sumOfInnerDistancesOnX = playerInnerDistanceOnX + objectInnerDistanceOnX;

        int playerInnerDistanceOnY = player.getHeight() / 2;
        int objectInnerDistanceOnY = obstacle1.getHeight() / 2;
        int sumOfInnerDistancesOnY = playerInnerDistanceOnY + objectInnerDistanceOnY;

        boolean collision = ((((player.getX()+player.getWidth()) > obstacle1.getPosX())
                && (player.getX() < (obstacle1.getPosX()+obstacle1.getWidth()))
                && (distanceOnY < sumOfInnerDistancesOnY)));

        int playerRightX = player.getX() + player.getWidth();
        int playerDownLeftPix = player.getY() + player.getHeight();

        int obstacleLeftX = obstacle1.getPosX();
        int obstacleUpLeftPix = obstacle1.getPosY();

        boolean collision2 = ((playerRightX - obstacleLeftX) >= obstacle1.getSpeed()) && playerDownLeftPix > obstacleUpLeftPix
                && player.getX() < obstacle1.getPosX();



        // Collision for damage

        if (collision2) {
            player.setPlayerDead();
//            player.setLife();
            System.out.println("COLLISION");
        }
    }

    private int getPlayerCenterX(SimpleGfxPlayer player) {
        // This is a pixel
        return (player.getX() + (player.getWidth() / 2));
    }

    private int getObjectCenterX() {
        // This is a pixel
        return (obstacle1.getPosX() + (obstacle1.getWidth() / 2));
    }

    private int getPlayerCenterY(SimpleGfxPlayer player) {
        //This is a pixel
        return (player.getY() + (player.getHeight() / 2));
    }

    private int getObjectCenterY() {
        //this is a pixel
        return (obstacle1.getPosY() + (obstacle1.getHeight() / 2));
    }

    public int distanceFromObjectOnY(SimpleGfxPlayer player) {
        return obstacle1.getPosY() - (player.getY() + player.getHeight());
    }

    public boolean isOnXWithObject(SimpleGfxPlayer player){
        return ((player.getX()+player.getWidth()) > obstacle1.getPosX())
                && (player.getX() < (obstacle1.getPosX()+obstacle1.getWidth()));
    }

    public boolean xIsPassed(SimpleGfxPlayer player){
        System.out.println(player.getX());
        System.out.println(obstacle1.getPosX()+obstacle1.getWidth());
        return player.getX() >= (obstacle1.getPosX()+obstacle1.getWidth()) ;
    }

}
