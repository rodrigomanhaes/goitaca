package goitaca.widget.table.span; 

import java.util.HashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

public abstract class AbstractSpanTableModel extends AbstractTableModel implements SpanTableModel 
{
	private static final long serialVersionUID = 755141004045870447L;
	
	private Map<IntegerPair, IntegerPair> map = new HashMap<IntegerPair, IntegerPair>();
	private Map<IntegerPair, IntegerPair> spanned = new HashMap<IntegerPair, IntegerPair>();
	
	public void setSpan(int column, int row, int width, int height)
	{
		map.put(IntegerPair.get(column, row), new IntegerPair(width, height));
		
		for (int ccount = column; ccount <= column + width - 1; ccount++)
			for (int rcount = row; rcount <= row + height - 1; rcount++)
				if (!(rcount == row && ccount == column))
					spanned.put(IntegerPair.get(ccount, rcount), IntegerPair.get(column, row));
	}
	
	public void removeSpan(int column, int row)
	{
		IntegerPair pair = map.remove(IntegerPair.get(column, row));
		
		for (int ccount = column; ccount <= column + pair.x - 1; ccount++)
			for (int rcount = row; rcount <= row + pair.y - 1; rcount++)
				if (!(rcount == row && ccount == column))
					spanned.remove(IntegerPair.get(ccount, rcount));
	}
	
	public boolean isSpaned(int column, int row)
	{
		return spanned.containsKey(IntegerPair.get(column, row)); 
	}
	
	public boolean isHorizontalSpaned(int column, int row)
	{
		IntegerPair span = spanned.get(IntegerPair.get(column, row)); 
		if (span != null)
			return column > span.x;
		return false;
	}
	
	public boolean isVerticalSpaned(int column, int row)
	{
		IntegerPair span = spanned.get(IntegerPair.get(column, row)); 
		if (span != null)
			return row > span.y;
		return false;
	}
	
	/**
	 * 
	 * @param column
	 * @param row
	 * @return a 2-element array containing width and height of cell spaning
	 */
	public int[] getSpan(int column, int row)
	{
		int[] result = new int[2];
		
		IntegerPair span = map.get(IntegerPair.get(column, row));
		if (span != null)
		{
			result[0] = span.x;
			result[1] = span.y;
			
			return result;
		}
		return null;
	}
}

class IntegerPair
{
	int x, y;
	
	public IntegerPair(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object pair)
	{
		return
			pair instanceof IntegerPair ?
				((IntegerPair) pair).x == this.x && ((IntegerPair) pair).y == this.y : false;		
	}
	
	@Override
	public String toString()
	{
		return 
			new StringBuilder()
				.append("[")
				.append(x)
				.append(",")
				.append(y)
				.append("]")
				.toString();
	}
	
	private static Map<String, IntegerPair> map = new HashMap<String, IntegerPair>();
	
	public static IntegerPair get(int column, int row)
	{
		String key = ""+column+row; 
		if (map.get(key) == null)
			map.put(key, new IntegerPair(column, row));
		return map.get(key);
	}
}