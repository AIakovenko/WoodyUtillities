package ua.woodyutilities.views;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * @autor Alex Iakovenko
 * Date: 6/26/15
 * Time: 8:56 PM
 */
public class StatusRowRender extends DefaultListCellRenderer {

    private HashMap theChosen = new HashMap();

    public Component getListCellRendererComponent( JList list,
                                                   Object value, int index, boolean isSelected,
                                                   boolean cellHasFocus )
    {
        super.getListCellRendererComponent( list, value, index,
                isSelected, cellHasFocus );

        if( isSelected )
        {
            theChosen.put( value, "chosen" );
        }

        if( theChosen.containsKey( value ) )
        {
            setForeground( Color.red );
        }
        else
        {
            setForeground( Color.black );
        }

        return( this );
    }
}