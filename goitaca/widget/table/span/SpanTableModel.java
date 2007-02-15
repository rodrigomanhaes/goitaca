package goitaca.widget.table.span;

public interface SpanTableModel 
{
	void setSpan(int column, int row, int width, int height);
	void removeSpan(int column, int row);
	boolean isSpaned(int column, int row);
	boolean isHorizontalSpaned(int column, int row);
	boolean isVerticalSpaned(int column, int row);
	int[] getSpan(int column, int row);
}
