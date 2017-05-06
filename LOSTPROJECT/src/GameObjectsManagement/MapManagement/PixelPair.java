package GameObjectsManagement.MapManagement;

public class PixelPair extends Pair {

    private int pixel;

    /**
     * Construct.
     * @param row
     * @param column
     */

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
