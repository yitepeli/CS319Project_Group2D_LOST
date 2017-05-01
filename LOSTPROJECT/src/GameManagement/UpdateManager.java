package GameManagement;

/**
 * Created by onursonmez on 23/04/2017.
 */
import GameObjectsManagement.AreaManagement.*;


public class UpdateManager {

    private Area positionOfUser;

    public UpdateManager(){
        createAreas();
    }


    /**
     * Creating linked-list for areas according to given format of multidimensional array and arranging neighbours.
     */

    private void createAreas(){

        AreaType[][] areaTypes = new AreaType[][]{
                {AreaType.Mountain1,AreaType.Iceland1,AreaType.Iceland2},
                {AreaType.Mountain2,AreaType.DarkForest1,AreaType.DarkForest2},
                {AreaType.Forest1,AreaType.Forest2,AreaType.AbandonedVillage},
                {AreaType.VolcanoZone1,AreaType.Jungle1,AreaType.Beach},
                {AreaType.VolcanoZone2,AreaType.Jungle2,AreaType.Swamp}
        };

        int rowSize = areaTypes.length; // 5
        int columnSize = areaTypes[0].length; // 3
        Area area = new Area(areaTypes[0][0]);
        for(int i = 0; i < rowSize; i++){
            Area base = area;
            for(int j = 0; j < columnSize; j++){

                if(area.getRightNeighbour() == null && j + 1 < columnSize){

                    Area newArea;
                    if(area.getUpNeighbour() != null){
                        newArea = area.getUpNeighbour().getRightNeighbour().getDownNeighbour();//cycle
                    }
                    else{
                        newArea = new Area(areaTypes[i][j + 1]);
                    }
                    area.setRightNeighbour(newArea);
                    newArea.setLeftNeighbour(area);
                }
                if(area.getDownNeighbour()  == null && i + 1 < rowSize){
                    Area newArea = new Area((areaTypes[i + 1][j]));
                    area.setDownNeighbour(newArea);
                    newArea.setUpNeighbour(area);
                }

                if(area.getAreaType() == AreaType.Forest1){//starting area type
                    positionOfUser = area;
                } // arranging initial position of user (linked list)

                if(area.getRightNeighbour() != null){
                    area = area.getRightNeighbour();
                }
            }
            if(base.hasDownNeighbour())
                area = base.getDownNeighbour(); // down column
        }
    }

    public Area getPositionOfUser(){
        return positionOfUser;
    }
}

