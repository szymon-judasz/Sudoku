package sudoku;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameState {
	List<Group> groupList;
        List<Group> horizontal;
        List<Group> vertical;
        int x;
        int y;
        public GameState()
        {
            groupList = new ArrayList<>();
            horizontal = new ArrayList<>();
            vertical = new ArrayList<>();
        }
        public Cell findCell(int posx, int posy)
        {
            for(Group group : horizontal)
                for (Cell cell : group.cells)
                    if(cell.getX() == posx && cell.getY() == posy)
                        return cell;
            return null;
        }
        
        private void registerListeners()
        {
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
	
        public static GameState example()
        {
            GameState gs = new GameState();
            gs.x = 4;
            gs.y = 4;
            Cell aa, ab, ac, ad, ba, bb, bc, bd, ca, cb, cc, cd, da, db, dc, dd;
            // 1 row
            aa = new Cell(0, true, 0, 0);
            ab = new Cell(0, true, 0, 1);
            ac = new Cell(0, true, 0, 2);
            ad = new Cell(0, true, 0, 3);
            
            ba = new Cell(0, true, 1, 0);
            bb = new Cell(0, true, 1, 1);
            bc = new Cell(0, true, 1, 2);
            bd = new Cell(0, true, 1, 3);
            
            ca = new Cell(1, false, 2, 0);
            cb = new Cell(2, false, 2, 1);
            cc = new Cell(0, true, 2, 2);
            cd = new Cell(0, true, 2, 3);
            
            da = new Cell(0, true, 3, 0);
            db = new Cell(0, true, 3, 1);
            dc = new Cell(3, false, 3, 2);
            dd = new Cell(0, true, 3, 3);
            
            // horizontal groups
            Group g = new Group();
            g.addCell(aa);
            g.addCell(ab);
            g.addCell(ac);
            g.addCell(ad);
            gs.horizontal.add(g);
            
            g = new Group();
            g.addCell(ba);
            g.addCell(bb);
            g.addCell(bc);
            g.addCell(bd);
            gs.horizontal.add(g);
            
            g = new Group();
            g.addCell(ca);
            g.addCell(cb);
            g.addCell(cc);
            g.addCell(cd);
            gs.horizontal.add(g);
            
            g = new Group();
            g.addCell(da);
            g.addCell(db);
            g.addCell(dc);
            g.addCell(dd);
            gs.horizontal.add(g);
            
            g = new Group();
            g.addCell(aa);
            g.addCell(ba);
            g.addCell(ca);
            g.addCell(da);
            gs.vertical.add(g);
            
            g = new Group();
            g.addCell(ab);
            g.addCell(bb);
            g.addCell(cb);
            g.addCell(db);
            gs.vertical.add(g);
            
            g = new Group();
            g.addCell(ac);
            g.addCell(bc);
            g.addCell(cc);
            g.addCell(dc);
            gs.vertical.add(g);
            
            g = new Group();
            g.addCell(ad);
            g.addCell(bd);
            g.addCell(cd);
            g.addCell(dd);
            gs.vertical.add(g);

            g = new Group();
            g.addCell(aa);
            g.addCell(ab);
            g.addCell(ba);
            g.addCell(ca);
            gs.groupList.add(g);
            
            g = new Group();
            g.addCell(ac);
            g.addCell(ad);
            g.addCell(bd);
            g.addCell(cd);
            gs.groupList.add(g);
            
            g = new Group();
            g.addCell(bb);
            g.addCell(cb);
            g.addCell(db);
            g.addCell(da);
            gs.groupList.add(g);
            
            g = new Group();
            g.addCell(bc);
            g.addCell(cc);
            g.addCell(dc);
            g.addCell(dd);
            gs.groupList.add(g);
            
            gs.registerListeners();
            return gs;
        }
}
