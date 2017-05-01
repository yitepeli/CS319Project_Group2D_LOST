package GameManagement;

/**
 *  Author: Onur Sönmez
 *
 *  Github: @sonmezonur
 *
 *  Description: By using Map class which splits all RGB components, processes the image according to the user position.
 */

//importing external files
import GameObjectsManagement.MapManagement.*;
import GameObjectsManagement.AreaManagement.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class MapManager {

    private int[][] redPixels, greenPixels, bluePixels;//pixel stores
    private Map processedMap,islandMap;//root image vs processed image
    private HashMap<String, ArrayList<PixelPair>> hashmap;//visited pixel container

    /**
     * Construct.
     */

    MapManager(){
        islandMap = new Map("/images/Map.png");//initial root image
        hashmap = new HashMap<>();
        init();
    }

    /**
     * Retrieving and initializing pixels.
     */

    private void init(){
        redPixels = islandMap.resolveRedPixels();
        greenPixels = islandMap.resolveGreenPixels();
        bluePixels = islandMap.resolveBluePixels();
    }

    /**
     * Processing map with helper methods.
     * @param Area Current position of player
     */

    public void processMapp(Area curPosition){//No need to resolve pixels every time!
        int[][] rp = darkenMap(redPixels, curPosition, "red");
        int[][] gp = darkenMap(greenPixels, curPosition, "green");
        int[][] bp = darkenMap(bluePixels, curPosition, "blue");
        curPosition.setVisited(true); // this partition is visited
        processedMap = updateShadows(rp,gp,bp);
    }

    /**
     * In order to visualize the movement of player, darkening the parts except from the range of user.
     * @param int[][] arr Pixels
     * @param Area curPosition Current position of player
     * @param String type Type of pixel(red,green or blue)
     * @return int[][] New formatted pixels
     */

    private int[][] darkenMap(int[][] arr, Area curPosition, String type) {

        int x = curPosition.getAreaType().getX();
        int y = curPosition.getAreaType().getY();
        int dimension = 150; // user range

        int[][] newArr = new int[islandMap.getWidth()][islandMap.getHeight()];

        boolean isOverloadedBefore = (hashmap.get(type) != null);//some work added before...

        ArrayList<PixelPair> visitedPixels;

        if(isOverloadedBefore){
            visitedPixels = hashmap.get(type);
        }
        else{
            visitedPixels = new ArrayList<>();
        }

        for(int i=0; i < islandMap.getWidth(); i++ ){
            for(int j=0; j < islandMap.getHeight(); j++){

                if( Math.pow(i - x, 2) + Math.pow(j - y, 2)  <= Math.pow(dimension, 2)){//drawing cycled range
                    newArr[i][j] = arr[i][j];
                    if(!curPosition.isVisited()){
                        PixelPair newPair  = new PixelPair(i,j);
                        newPair.setPixel(arr[i][j]);//storing pixels
                        visitedPixels.add(newPair);
                    }
                }
                else{
                    newArr[i][j] = 0;//darkening out of range
                }
            }
        }
        if(!visitedPixels.isEmpty() && !curPosition.isVisited() && !isOverloadedBefore) {//type safety
            hashmap.put(type, visitedPixels);    // Storing list of visited pixels in hashmap according to its key( red, blue or green)
        }
        return newArr;
    }

    /**
     * Shadowing the visited parts, after the user changes the position.
     * @param int[][] Red Pixels of processed image
     * @param int[][] Green Pixels of processed image
     * @param int[][] Blue Pixels of processed image
     * @return Map Creates processed map
     */

    private Map updateShadows(int[][] redPixels, int[][] greenPixels, int[][] bluePixels){

        for(int i = 0; hashmap.get("red") != null && i < hashmap.get("red").size(); i++){

            PixelPair redPair = hashmap.get("red").get(i);
            PixelPair bluePair = hashmap.get("blue").get(i);
            PixelPair greenPair = hashmap.get("green").get(i);
            int rX = redPair.getRow(), rY = redPair.getColumn();
            int bX = bluePair.getRow(), bY = bluePair.getColumn();
            int gX = greenPair.getRow(), gY = greenPair.getColumn();

            if(redPixels[rX][rY] == 0 && bluePixels[bX][bY] == 0 && greenPixels[gX][gY] == 0){// Checks for RGB(0,0,0) -> blacks pixels
                redPixels[rX][rY] = round(redPair.getPixel() - 100);
                greenPixels[gX][gY] = round(greenPair.getPixel() - 100);
                bluePixels[bX][bY] = round(bluePair.getPixel() - 100);
            }
        }
        return new Map(redPixels,greenPixels,bluePixels);
    }

    /**
     * Truncating the values so as to prevent dead pixels in visualization.
     * @param int Pixel value
     * @return int
     */

    private int round(int value){
        if(value < 0)
            return 0;
        if(value > 255)
            return 255;
        return value;
    }

    /**
     * Accessors
     */

    public BufferedImage getProcessedMap(){
        return  processedMap.getMap();
    }
    public Map getInitialMap(){
        return islandMap;
    }
}
