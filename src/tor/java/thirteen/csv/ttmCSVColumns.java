package tor.java.thirteen.csv;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.tree.DefaultTreeModel;

import tor.java.thirteen.Thirteen;
import JCommonTools.CC;
import JCommonTools.JTreeTable.AbstractTreeTableModel;
import JCommonTools.RefBook.rbNode;

public class ttmCSVColumns extends AbstractTreeTableModel 
{
	private ResourceBundle _bnd;
	//private CSVHeaderItem _hdr;
	
	//private DefaultTreeModel _trm;
	private rbNode _rbn;
	//private DefaultComboBoxModel<String> _modCboDelim;
	
	public void setHeader(CSVHeaderItem aHeader)
	{
		//_hdr = aHeader;
		root = aHeader;
	}
	
	public ttmCSVColumns(CSVHeaderItem aHeader, rbNode aRefNode) 
	{
		super (aHeader);

		//_hdr = aHeader;
		_rbn = aRefNode;
		_bnd = ResourceBundle.getBundle(Thirteen.CT_RESOURCE_TEXT);
	}
	
	@Override
	public int getColumnCount() 
	{
		return 3;
	}

	@Override
	public String getColumnName(int column) 
	{
		String str = null;
		switch (column) 
		{
		case 0:
			str = _bnd.getString("JPanelCSVCol.Table.Column.Name.Title");
			break;
		case 1:
			str = _bnd.getString("JPanelCSVCol.Table.Column.Name.Type");
			break;
		case 2:
			str = _bnd.getString("JPanelCSVCol.Table.Column.Name.Delim");
			break;

		default:
			break;
		}
		return str;
	}

	@Override
	public boolean isLeaf(Object node) 
	{
		if (node instanceof CSVHeaderItem && ((CSVHeaderItem)node).getParserItems() != null)
			return ((CSVHeaderItem)node).getParserItems().length == 0;
		else
			return true;
	}
	
	@Override
	public Object getValueAt(Object node, int column) 
	{
		String ret = null;
		if (node instanceof CSVHeaderItem)
		{
			CSVHeaderItem hi = (CSVHeaderItem) node;
			switch (column) {
			case 0:
				ret = hi.getSource();
				break;
			case 1:
			{
				if (hi.getTypeHI() > 0)
				{
					rbNode nd = _rbn.findByCode(hi.getTypeHI()); 
					if (nd != null)
						ret =  nd.getName();
				}
				break;
			}
			case 2:
				ret = hi.getDelimiter();
				break;
//			default:
//				break;
			}
			
		}

		return ret;
	}
	
	@Override
	public Object getChild(Object parent, int index) 
	{
		if (parent instanceof CSVHeaderItem)
		{
			return ((CSVHeaderItem)parent).getParserItems()[index];
		}
		else
		{
			return null;
		}
	}

	@Override
	public int getChildCount(Object parent) 
	{
		if (parent instanceof CSVHeaderItem)
		{
			return ((CSVHeaderItem)parent).getParserItems().length;
		}
		else
		{
			return 0;
		}
	}

	@Override
	public boolean isCellEditable(Object node, int column) 
	{
		boolean ret = super.isCellEditable(node, column);
		if (column >= 1)
			ret = true;
		return ret;
	}

	@Override
	public void setValueAt(Object aValue, Object node, int column) 
	{
	    switch(column) 
	    { 
	       case 1: 
	       {
	    	   rbNode nd =_rbn.findByName(aValue.toString()); 
	    	   if (nd  != null)
	    	   {
		    	   CSVHeaderItem hi = (CSVHeaderItem) node;
		    	   hi.setTypeHI(nd.getId());
	    	   }
	       }
	       case 2:
	       {
	    	   CSVHeaderItem hi = (CSVHeaderItem) node;
	    	   hi.setDelimiter(aValue.toString());
	       }
	       default: 
				super.setValueAt(aValue, node, column);
	    }
	}
	
}
