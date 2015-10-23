package tor.java.thirteen.csv;

import java.util.ArrayList;

import javax.swing.tree.TreePath;

import JCommonTools.TreeModelSupport;

/**
 * 
 * @author M.Tor
 * date created 13.10.2015
 * last modified 14.10.2015
 */
public class trmCSVColumns extends TreeModelSupport
{
	private String  _srcHdr;
	private ArrayList<CSVHeaderItem> _hdr;
	
	public ArrayList<CSVHeaderItem> getHItems()
	{
		return _hdr;
	}
	
	@Override
	public String toString() 
	{
		return _srcHdr;
	}
	
	public trmCSVColumns(String aHeader) 
	{
		_srcHdr = aHeader;
		_hdr = new ArrayList<CSVHeaderItem>();
	}
	
	@Override
	public Object getRoot() 
	{
		return this;
	}

	@Override
	public Object getChild(Object parent, int index) 
	{
		if (parent instanceof trmCSVColumns)
		{
			return ((trmCSVColumns) parent).getHItems().get(index);
		}
		else if (parent instanceof CSVHeaderItem)
		{
			return ((CSVHeaderItem) parent).getParserItems()[index];
		}
		else
		{
			return null;
		}
	}

	@Override
	public int getChildCount(Object parent) 
	{
		if (parent instanceof trmCSVColumns)
		{
			return ((trmCSVColumns) parent).getHItems().size();
		}
		else if (parent instanceof CSVHeaderItem)
		{
			return ((CSVHeaderItem) parent).getParserItems().length;
		}
		else
		{
			return 0;
		}
	}

	@Override
	public boolean isLeaf(Object node) 
	{
		if (node instanceof trmCSVColumns)
		{
			return true;
		}
		else if (node instanceof CSVHeaderItem)
		{
			return ((CSVHeaderItem) node).getParserItems().length > 0;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) 
	{
		if (parent instanceof trmCSVColumns)
		{
			return ((trmCSVColumns) parent).getHItems().indexOf(child);
		}
		else if (parent instanceof CSVHeaderItem)
		{
			return 0; //((CSVHeaderItem) parent).getHItems().indexOf(child);
		}
		else
		{
			return 0;
		}
	}

	/*
	@Override
	public void addTreeModelListener(TreeModelListener l) 
	{
		_listenerList.add(TreeModelListener.class, l);
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) 
	{
		_listenerList.remove(TreeModelListener.class, l);
	}
	
	protected void fireTreeStructureChanged(Object oldRoot)
	{
		TreeModelEvent event = new TreeModelEvent(this, new Object[] {oldRoot});
		EventListener[] listener = _listenerList.getListeners(TreeModelListener.class);
		for (int ii=0; ii < listener.length; ii++)
			((TreeModelListener)listener[ii]).treeNodesChanged(event);
	}
	*/
}
