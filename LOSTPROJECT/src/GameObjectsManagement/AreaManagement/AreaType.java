/**
 * Created by onursonmez 
 *
 */
package GameObjectsManagement.AreaManagement;


//EnumSet

public enum AreaType {
    Forest1(245, 432, "Forest1"),
    Forest2(400, 430, "Forest2"),
    Jungle1(353, 600, "Jungle1"),
    Jungle2(367, 716, "Jungle2"),
    DarkForest1(320, 260, "DarkForest1"),
    DarkForest2(420, 239, "DarkForest2"),
    Mountain1(150, 150, "Mountain1"),
    Mountain2(150, 300, "Mountain2"),
    AbandonedVillage(620, 365, "AbandonedVillage"),
    Beach(630, 545, "Beach"),
    VolcanoZone1(150, 600, "VolcanoZone1"),
    VolcanoZone2(183, 750, "VolcanoZone2"),
    Swamp(652, 694, "Swamp"),
    Iceland1(380, 100, "Iceland1"),
    Iceland2(580, 100, "Iceland2");

    private int x, y;
    private String areaName;

    AreaType(int x, int y, String areaName){
        this.x = x;
        this.y = y;
        this.areaName = areaName;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public String getAreaName(){
        return areaName;
    }
}
