package sudoku;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
public class Cell {
	private int value; // -1 means that is empty
	private boolean _isModifiable;
	private int posX, posY;
        private Collection<Cell> _conflicting_cells;
	public Cell(int val, boolean canBeModified, int posY, int posX)
	{
		value = val;
		_isModifiable = canBeModified;
                this.posX = posX;
                this.posY = posY;
                //_noConflicts = 0;
                vcs = new VetoableChangeSupport(this);
                pcs = new PropertyChangeSupport(this);
                _conflicting_cells = new ArrayList<>();
	}
        
	//private int _noConflicts;
        public boolean isConflict()
        {
            return _conflicting_cells.size() > 0;
            //return _noConflicts != 0;
        }
        
        /*public void incrementConflict()
        {
            _noConflicts++;
        }
        public void decrementConflict()
        {
            _noConflicts--;
        }*/
        public int getX()
        {
            return posX;
        }
        public int getY()
        {
            return posY;
        }
        
	private PropertyChangeSupport pcs;
	private VetoableChangeSupport vcs;
	
	
	public synchronized void setValue(int newValue) throws PropertyVetoException
	{
		if(_isModifiable)
		{
			int oldValue = value;
			vcs.fireVetoableChange(Action.TRYING_TO_SET_VAL.toString(), oldValue, newValue);
			value = newValue;
			pcs.firePropertyChange(Action.VALUE_SET.toString(), oldValue, newValue);
		}
	}
	
    public synchronized void addPropertyChangeListener (PropertyChangeListener lst)
        { pcs.addPropertyChangeListener(lst); }
    public synchronized void removePropertyChangeListener (PropertyChangeListener lst)
        { pcs.removePropertyChangeListener(lst); }
    public synchronized void addVetoableChangeListener (VetoableChangeListener lst)
        { vcs.addVetoableChangeListener(lst); }
    public synchronized void removeVetoableChangeListener (VetoableChangeListener lst)
        { vcs.removeVetoableChangeListener(lst); }
	
	public synchronized int getValue()
	{
		return value;
	}
	
        public boolean isModifiable()
        {
            return _isModifiable;
        }
        public void addConflictingCell(Cell c)
        {
            if(_conflicting_cells.contains(c) || c.getValue() == 0 || this == c)
                return;
            _conflicting_cells.add(c);
        }
        public void removeConflictingCell(Cell c)
        {
            _conflicting_cells.remove(c);
        }
        public void removeAllConflictingCells()
        {
            _conflicting_cells.clear();
        }
	
}
