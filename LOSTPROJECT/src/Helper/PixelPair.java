package Helper;

/**
 * Created by onursonmez on 19/02/17.
 */
public class PixelPair extends Pair {

    private int pixel;

    public PixelPair(int row, int column){
        super(row,column);
    }

    public void setPixel(int pixel){
        this.pixel = pixel;
    }

    public int getPixel(){
        return pixel;
    }


}
