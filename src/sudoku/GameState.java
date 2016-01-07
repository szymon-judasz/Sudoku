package sudoku;

import java.util.ArrayList;
import java.util.List;

public class GameState {

    List<Group> groupList;
    List<Group> horizontal;
    List<Group> vertical;
    int x;
    int y;

    public GameState() {
        groupList = new ArrayList<>();
        horizontal = new ArrayList<>();
        vertical = new ArrayList<>();
    }

    public Cell findCell(int posx, int posy) {
        for (Group group : horizontal) {
            for (Cell cell : group.cells) {
                if (cell.getX() == posx && cell.getY() == posy) {
                    return cell;
                }
            }
        }
        return null;
    }

    private void registerListeners() {
        ArrayList<Group> g = new ArrayList<>();
        g.addAll(groupList);
        g.addAll(horizontal);
        g.addAll(vertical);
        g.stream().forEach((group) -> {
            group.cells.stream().map((cell) -> {
                cell.addPropertyChangeListener(group);
                return cell;
            }).forEach((cell) -> {
                cell.addVetoableChangeListener(group);
            });
        });
    }

    static class GameCreator // can be moved to another file since all fields in GameState are public
    {

        GameState createGameState(GameDescription gd) {
            GameState result = new GameState();
            result.x = (int) Math.sqrt(gd.groupMap.length());
            result.y = result.x;
            if (gd.groupMap.length() != gd.valueMap.length()) {
                throw new IllegalArgumentException("Different sizes of maps");
            }
            if (result.x * result.x != gd.groupMap.length()) {
                throw new IllegalArgumentException("Map size must be a square of natural number");
            }

            for (int i = 0; i < result.x; i++) {
                result.groupList.add(new Group());
                result.horizontal.add(new Group());
                result.vertical.add(new Group());

            }

            for (int i = 0; i < gd.groupMap.length(); i++) {
                int _x = i % result.x;
                int _y = i / result.y;
                int value = gd.valueMap.charAt(i) - '0';
                int groupNumber = gd.groupMap.charAt(i) - '0';
                Cell parsedCell = new Cell(value, value == 0, _y, _x);

                result.groupList.get(groupNumber).addCell(parsedCell);
                result.horizontal.get(_y).addCell(parsedCell);
                result.vertical.get(_x).addCell(parsedCell);
            }
            result.registerListeners();
            return result;
        }
    }
}
