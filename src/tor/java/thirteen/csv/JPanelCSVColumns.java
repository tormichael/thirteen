package tor.java.thirteen.csv;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;

import tor.java.thirteen.Thirteen;
import JCommonTools.GBC;
import JCommonTools.JTreeTable.JTreeTable;
import JCommonTools.RefBook.rbNode;

public class JPanelCSVColumns extends JPanel 
{
	private CSVHeaderItem _header;
	private rbNode _rbHIType;
	private ResourceBundle _bnd;
	private GridBagLayout _gbl;
	private JTextField 	_txtVal;
	private JTextField 	_txtDelim;
	private JButton 		_btnRefresh;
	private ttmCSVColumns	_ttm;
	private JTreeTable			_treeTab;
	private  JScrollPane 		_spTT;

	public void setHeader(CSVHeaderItem aHeader)
	{
		_header = aHeader;
		if (_header != null)
		{
			_txtVal.setText(_header.getSource());
			_txtDelim.setText(_header.getDelimiter());
			_ttm.setHeader(aHeader);
			_treeTab.updateUI();
		}
	}
	
	public JPanelCSVColumns(rbNode aRBHIType)
	{
		_rbHIType = aRBHIType;
		_bnd = ResourceBundle.getBundle(Thirteen.CT_RESOURCE_TEXT);
		_header = null;
		_gbl = new GridBagLayout();
		this.setLayout(_gbl);

		JLabel lbl = new JLabel(_bnd.getString("Label"));
		_gbl.setConstraints(lbl, new GBC(0,0).setGridSpan(4, 1).setInsets(2,2,2,5).setFill(GBC.HORIZONTAL).setWeight(1.0, 0.0));
		this.add(lbl);
		_txtVal = new JTextField();
		_gbl.setConstraints(_txtVal, new GBC(0,1).setGridSpan(4, 1).setInsets(2,2,2,2).setFill(GBC.HORIZONTAL).setWeight(1.0, 0.0));
		this.add(_txtVal);
		lbl = new JLabel(_bnd.getString("Label"));
		_gbl.setConstraints(lbl, new GBC(0,2).setInsets(2,2,2,2).setFill(GBC.HORIZONTAL).setWeight(1.0, 0.0));
		this.add(lbl);
		_txtDelim = new JTextField(7);
		_gbl.setConstraints(_txtDelim, new GBC(1,2).setInsets(2,2,2,2));
		this.add(_txtDelim);
		_btnRefresh = new JButton(actRefresh);
		_gbl.setConstraints(_btnRefresh, new GBC(2,2).setInsets(2,20,2,2));
		this.add(_btnRefresh);

		_ttm = new ttmCSVColumns(_header, _rbHIType);
		_treeTab = new JTreeTable(_ttm);
		_treeTab.getTree().setRootVisible(true);
		_treeTab.getTree().setEditable(true);
		_spTT =  new JScrollPane(_treeTab);
		_gbl.setConstraints(_spTT, new GBC(0,3).setGridSpan(4, 1).setInsets(2,2,2,2).setFill(GBC.BOTH).setWeight(1.0, 1.0));
		this.add(_spTT);
	}
	
	private void _addNewTableTree()
	{
		if (_treeTab != null)
		{
			_gbl.removeLayoutComponent(_spTT);
			this.remove(_spTT);
			_spTT = null;
			_treeTab = null;
			_ttm = null;
		}
	}
	
	Action actRefresh = new AbstractAction() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (_header != null)
			{
				_header.setDelimiter(_txtDelim.getText());
				_header.Run();
				_ttm.setHeader(_header);
				_treeTab.updateUI();
			}
		}
	};
}
 