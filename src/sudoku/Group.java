package sudoku;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Group implements PropertyChangeListener, VetoableChangeListener {

    List<Cell> cells;

    public Group() {
        cells = new ArrayList<>();
    }

    public void addCell(Cell c) {
        cells.add(c);
    }

    @Override
    public void propertyChange(PropertyChangeEvent arg0) {
        if (!arg0.getPropertyName().equals(Action.VALUE_SET.toString())) {
            return;
        }
        cells.stream().filter((c) -> c.getValue() == (int) arg0.getOldValue()).forEach((c) -> {
            c.removeConflictingCell((Cell) arg0.getSource());
        });

        cells.stream().filter((c) -> c.getValue() == (int) arg0.getNewValue()).forEach((c) -> {
            c.addConflictingCell((Cell) arg0.getSource());
            ((Cell) arg0.getSource()).addConflictingCell(c);
        });
    }

    @Override
    public void vetoableChange(PropertyChangeEvent arg0)
            throws PropertyVetoException {
        if ((int) arg0.getNewValue() > cells.size() || (int) arg0.getNewValue() < 0)
        {
            throw new PropertyVetoException("Number must be in range of 0 and " + cells.size() , arg0);
        }
        
        if (arg0.getPropertyName().equals(Action.TRYING_TO_SET_VAL.toString())) {
            for (int i = 0; i < cells.size(); i++) {
                if (cells.get(i).isModifiable() == false && cells.get(i).getValue() == (int) arg0.getNewValue()) {
                    if (!cells.get(i).equals(arg0.getSource())) {
                        throw new PropertyVetoException(
                                "This number already exists", arg0);
                    }
                }
            }
        }
    }
}
