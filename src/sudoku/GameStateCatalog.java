package sudoku;
import java.util.ArrayList;
import java.util.List;
public class GameStateCatalog {
    public static GameDescription get(int i)
    {
        return entryCollection.get(i);
    }
    private static List<GameDescription> entryCollection;
    public static Integer numberOfLevels;
    static {
        entryCollection = new ArrayList<>();
        GameDescription gameStateCatalogEntry;
        gameStateCatalogEntry= new GameDescription();
        gameStateCatalogEntry.groupMap = ""
                + "0011"
                + "0321"
                + "0321"
                + "3322";
        gameStateCatalogEntry.valueMap = ""
                + "0000"
                + "0000"
                + "1200"
                + "0030";
        entryCollection.add(gameStateCatalogEntry);
        
        gameStateCatalogEntry= new GameDescription();
        gameStateCatalogEntry.groupMap = ""
                + "000011"
                + "002211"
                + "322441"
                + "322441"
                + "334455"
                + "335555";
        gameStateCatalogEntry.valueMap = ""
                + "000001"
                + "040000"
                + "000020"
                + "050000"
                + "000030"
                + "500000";
        entryCollection.add(gameStateCatalogEntry);
        
        gameStateCatalogEntry= new GameDescription(); //2098
        gameStateCatalogEntry.groupMap = ""
                + "000011"
                + "222001"
                + "222111"
                + "334445"
                + "334545"
                + "334555";
        gameStateCatalogEntry.valueMap = ""
                + "304060"
                + "000000"
                + "000000"
                + "102030"
                + "040006"
                + "000002";
        entryCollection.add(gameStateCatalogEntry);
        numberOfLevels = entryCollection.size();
        
    }
    
}
