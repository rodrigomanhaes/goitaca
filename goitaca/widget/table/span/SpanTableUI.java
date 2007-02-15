package goitaca.widget.table.span;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class SpanTableUI extends BasicTableUI 
{
	@Override
	public void paint(Graphics gfx, JComponent ignored) 
    {
	    int ncols = table.getColumnCount();
	    int nrows = table.getRowCount();
	    if (nrows == 0 || ncols == 0)
	      return;
	
	    Rectangle clip = gfx.getClipBounds();
	
	    // Determine the range of cells that are within the clip bounds.
	    Point p1 = new Point(clip.x, clip.y);
	    int c0 = table.columnAtPoint(p1);
	    if (c0 == -1)
	    	c0 = 0;
	    int r0 = table.rowAtPoint(p1);
	    if (r0 == -1)
	    	r0 = 0;
	    Point p2 = new Point(clip.x + clip.width, clip.y + clip.height);
	    int cn = table.columnAtPoint(p2);
	    if (cn == -1)
	    	cn = table.getColumnCount() - 1;
	    int rn = table.rowAtPoint(p2);
	    if (rn == -1)
	    	rn = table.getRowCount() - 1;
	
	    int columnMargin = table.getColumnModel().getColumnMargin();
	    int rowMargin = table.getRowMargin();
	
	    TableColumnModel cmodel = table.getColumnModel();
	    int[] widths = new int[cn + 1];
	    for (int i = c0; i <= cn; i++)
	        widths[i] = cmodel.getColumn(i).getWidth() - columnMargin;
	    
	    Rectangle bounds = table.getCellRect(r0, c0, false);
	    // The left boundary of the area being repainted.
	    int left = bounds.x;
	    
	    // The top boundary of the area being repainted.
	    int top = bounds.y;
	    
	    SpanTableModel model = (SpanTableModel) table.getModel();
	    
	    // paint the cell contents
	    Color grid = table.getGridColor();    
	    for (int r = r0; r <= rn; ++r)
	    {
	        for (int c = c0; c <= cn; ++c)
	        {
	            bounds.width = widths[c];
	            
	            int[] span = model.getSpan(c, r); 
	            if (span != null)
	            {
	            	Rectangle spanBounds = new Rectangle(bounds);
	            	for (int spanColumn = c+1; spanColumn <= c + span[0] - 1; spanColumn++)
	            		//spanBounds.width += widths[spanColumn] + columnMargin + 1; 
	            		spanBounds.width += table.getColumnModel().getColumn(spanColumn).getWidth() + 1;
	            	for (int spanRow = r+1; spanRow <= r + span[1] - 1; spanRow++)
	            		spanBounds.height += table.getRowHeight(spanRow) + rowMargin;
	            	paintCell(gfx, r, c, spanBounds, table.getCellRenderer(r, c));
	            }
	            else if (!model.isSpaned(c, r))
	            	paintCell(gfx, r, c, bounds, table.getCellRenderer(r, c));
	            bounds.x += table.getColumnModel().getColumn(c).getWidth();
	        }
	        bounds.x = left;
	        bounds.y += table.getRowHeight(r);
	        // Update row height for tables with custom heights.
	        bounds.height = table.getRowHeight(r + 1) - rowMargin;
	    }
	    
	    // paint vertical grid lines
	    if (grid != null && table.getShowVerticalLines())
	    {    
	        Color save = gfx.getColor();
	        gfx.setColor(grid);
	        int x = left - columnMargin;
	        for (int c = c0; c <= cn; ++c)
	        {
	            // The vertical grid is draw right from the cells, so we 
	            // add before drawing.
	            x += widths[c] + columnMargin;
	            
	            int ycount = top;
	            for (int r = r0; r <= rn; r++)
	            {
	            	if (!model.isHorizontalSpaned(c+1, r))
	            		gfx.drawLine(x, ycount, x, ycount+table.getRowHeight(r)-1);
	            	ycount += table.getRowHeight(r);
	            }
	        }
	        gfx.setColor(save);
	    }
	
	    // paint horizontal grid lines    
	    if (grid != null && table.getShowHorizontalLines())
        {    
	        Color save = gfx.getColor();
	        gfx.setColor(grid);
	        int y = top - rowMargin;
	        for (int r = r0; r <= rn; ++r)
	          {
	            // The horizontal grid is draw below the cells, so we 
	            // add before drawing.
	            y += table.getRowHeight(r);
	            int xcount = left;
	            for (int c = c0; c <= cn; c++)
	            {
	            	if (!model.isVerticalSpaned(c, r+1))
	            		gfx.drawLine(xcount, y, xcount + table.getColumnModel().getColumn(c).getWidth(), y);
	            	xcount += table.getColumnModel().getColumn(c).getWidth();
	            }
	          }
	        gfx.setColor(save);
	    }
	}
	
	protected void paintCell(Graphics g, int row, int col, Rectangle bounds,
		TableCellRenderer rend)
	{
		Component comp = table.prepareRenderer(rend, row, col);
		rendererPane.paintComponent(g, comp, table, bounds);
	}
}