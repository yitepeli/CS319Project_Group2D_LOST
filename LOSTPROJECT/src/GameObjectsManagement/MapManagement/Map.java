
package GameObjects;
/**
 * Author: Onur Sönmez
 *
 * Github: @sonmezonur
 *
 * Description: Reading IO components, than acquiring RGB components.
 *
 */

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Map {

    private BufferedImage map;
    private int height,width;

    /**
     * Construct by path.
     * @param String url Path of map image that will be added in.
     * @throws IOException
     */

    public Map (final String url) {
        map = null;
        try {
            map = ImageIO.read(new File(url));
            height = map.getHeight();
            width  = map.getWidth();
        }
        catch (IOException e) {}
    }

    /**
     * Construct new image by given red, green and blue pixels.
     * @param int[][] r Red pixels of image
     * @param int[][] g Green pixels of image
     * @param int[][] b Blue pixels of image
     */

    public Map(int[][] r, int[][] g, int [][] b) {

        width= r.length;
        height=r[0].length;
        map = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);//argb type

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = new Color (r[i][j],g[i][j],b[i][j]);
                map.setRGB(i, j, c.getRGB());
            }
        }
    }


    /**
     * Resolving red pixels of image.
     * @return int[][] Acquired red pixels
     */

    public int[][] resolveRedPixels() {
        int[][] redPixels = new int[width][height];

        for( int i = 0; i < width; i++ ){
            for( int j = 0; j < height; j++ ) {
                Color c = new Color(map.getRGB(i,j));
                redPixels[i][j] = c.getRed();
            }
        }
        return redPixels;
    }

    /**
     * Resolving green pixels of image.
     * @return int[][] Acquired green pixels
     */

    public int[][] resolveGreenPixels() {
        int[][] greenPixels = new int[width][height];

        for( int i = 0; i < width; i++ ) {
            for( int j = 0; j < height; j++ ) {
                Color c = new Color(map.getRGB(i,j));
                greenPixels[i][j] = c.getGreen();
            }
        }
        return greenPixels;
    }

    /**
     * Resolving blue pixels of image.
     * @return int[][] Acquired blue pixels
     */

    public int[][] resolveBluePixels() {
        int[][] bluePixels = new int[width][height];

        for( int i = 0; i < width; i++ ){
            for( int j = 0; j < height; j++ ) {
                Color c = new Color(map.getRGB(i,j));
                bluePixels[i][j] = c.getBlue();
            }
        }
        return bluePixels;
    }



    /**
     * Accessors
     */

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public BufferedImage getMap(){
        return map;
    }

}
