package tor.java.thirteen.csv;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;

import tor.java.thirteen.Thirteen;
import tor.java.thirteen.parser.Parser;
import JCommonTools.CC;
import JCommonTools.CodeText;
import JCommonTools.GBC;
import JCommonTools.JTreeTable.JTreeTable;
import JCommonTools.RefBook.rbNode;

public class JPanelCSVColumns extends JPanel 
{
	private CSVHeaderItem _header;
	private String _sepa;
	
	private rbNode _rbHIType;
	private ResourceBundle _bnd;
	private GridBagLayout _gbl;
	private JTextField 	_txtVal;
	private JComboBox<CodeText> _cboType;
	
	private ButtonGroup 	_grpSepa;
	private JRadioButton 	_rdbSpace;
	private JRadioButton 	_rdbTab;
	private JRadioButton 	_rdbVLine;
	private JRadioButton 	_rdbComma;
	private JRadioButton 	_rdbSemicolon;
	private JRadioButton 	_rdbOther;
	private JTextField 		_txtOtherSepa; 

	private JButton 			_btnRefresh;

	private ttmCSVColumns	_ttm;
	private JTreeTable			_treeTab;
	private  JScrollPane 		_spTT;

	public void setHeader(CSVHeaderItem aHeader)
	{
		_header = aHeader;
		if (_header != null)
		{
			_txtVal.setText(_header.getSource());
			//_txtDelim.setText(_header.getDelimiter());
			//_ttm.setHeader(aHeader);
			//_treeTab.updateUI();
		}
	}

	public String getSeparator()
	{
		if (_sepa == CC.STR_EMPTY)
			return _txtOtherSepa.getText();
		else
			return _sepa;
	}
	public void setSeparator(String aSepa)
	{
		_sepa = aSepa;
		
		if (_sepa.equals(Parser.SEPARATOR_SPASE))
			_rdbSpace.setSelected(true);
		else if (_sepa.equals(Parser.SEPARATOR_TABULATION))
			_rdbTab.setSelected(true);
		else if (_sepa.equals(Parser.SEPARATOR_VERTICAL))
			_rdbVLine.setSelected(true);
		else if (_sepa.equals(Parser.SEPARATOR_COMMA))
			_rdbTab.setSelected(true);
		else if (_sepa.equals(Parser.SEPARATOR_SEMICOLON))
			_rdbTab.setSelected(true);
		else
		{
			_rdbOther.setSelected(true);
			_txtOtherSepa.setEnabled(true);
			_txtOtherSepa.setText(_sepa);
			_txtOtherSepa.requestFocus();
		}
	}
	
	public JPanelCSVColumns(rbNode aRBHIType, String aLabelSrc)
	{
		_rbHIType = aRBHIType;
		_bnd = ResourceBundle.getBundle(Thirteen.CT_RESOURCE_TEXT);
		_header = null;
		_gbl = new GridBagLayout();
		this.setLayout(_gbl);
		
		/// ROWi0 
		JLabel lbl = new JLabel(aLabelSrc);
		_gbl.setConstraints(lbl, new GBC(0,0).setInsets(2,2,2,2));
		this.add(lbl);
		lbl = new JLabel();
		_gbl.setConstraints(lbl, new GBC(1,0).setInsets(2,2,2,2));
		this.add(lbl);
		lbl = new JLabel(_bnd.getString("Label"));
		_gbl.setConstraints(lbl, new GBC(2,0).setInsets(2,2,2,2));
		this.add(lbl);
		/// ROWi1 
		_txtVal = new JTextField();
		_gbl.setConstraints(_txtVal, new GBC(0,1).setGridSpan(2, 1).setInsets(2,2,2,2).setFill(GBC.HORIZONTAL).setWeight(0.8, 0.0));
		this.add(_txtVal);
		_cboType = new JComboBox<CodeText>();
		_gbl.setConstraints(_cboType, new GBC(2,1).setInsets(2,2,2,2).setFill(GBC.HORIZONTAL).setWeight(0.2, 0.0));
		this.add(_cboType);
		/// ROWi2
		JPanel pnlSeparators = new JPanel(new FlowLayout	());
		pnlSeparators.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedSoftBevelBorder(), _bnd.getString("Label")));
			_grpSepa = new ButtonGroup();
			_rdbSpace = new JRadioButton(actSelected);
			_grpSepa.add(_rdbSpace);
			pnlSeparators.add(_rdbSpace);
			_rdbTab = new JRadioButton(actSelected);
			_grpSepa.add(_rdbTab);
			pnlSeparators.add(_rdbTab);
			_rdbVLine = new JRadioButton(actSelected);
			_grpSepa.add(_rdbVLine);
			pnlSeparators.add(_rdbVLine);
			_grpSepa.add(_rdbVLine);
			_rdbComma = new JRadioButton(actSelected);
			_grpSepa.add(_rdbComma);
			pnlSeparators.add(_rdbComma);
			_rdbSemicolon = new JRadioButton(actSelected);
			_grpSepa.add(_rdbSemicolon);
			pnlSeparators.add(_rdbSemicolon);
			JPanel pnlOther = new JPanel(new BorderLayout());
				_rdbOther = new JRadioButton(actSelected);
				_txtOtherSepa = new JTextField(7);
				_txtOtherSepa.setMinimumSize(new Dimension(_txtOtherSepa.getPreferredSize().width, _txtOtherSepa.getPreferredSize().height));
				_txtOtherSepa.setEnabled(false);
				pnlOther.add(_rdbOther, BorderLayout.WEST);
				pnlOther.add(_txtOtherSepa, BorderLayout.CENTER);
			_grpSepa.add(_rdbOther);
			pnlSeparators.add(pnlOther);
		_gbl.setConstraints(pnlSeparators, new GBC(0,2).setInsets(2,2,2,2).setGridSpan(3, 1).setFill(GBC.BOTH));
		this.add(pnlSeparators);
		/// ROWi3 
		lbl = new JLabel(aLabelSrc);
		_gbl.setConstraints(lbl, new GBC(0,3).setInsets(2,2,2,2));
		this.add(lbl);
		lbl = new JLabel();
		_gbl.setConstraints(lbl, new GBC(1,3).setInsets(2,2,2,2));
		this.add(lbl);
		_btnRefresh = new JButton(actRefresh);
		_btnRefresh.setText(_bnd.getString("Button.Refresh"));
		_gbl.setConstraints(_btnRefresh, new GBC(2,3).setInsets(2,2,2,2));
		this.add(_btnRefresh);
		/// ROWi4 ------------------------------------------------ TreeTable
		_ttm = new ttmCSVColumns(_header, _rbHIType);
		_treeTab = new JTreeTable(_ttm);
		_treeTab.getTree().setRootVisible(true);
		_treeTab.getTree().setEditable(true);
		_spTT =  new JScrollPane(_treeTab);
		_gbl.setConstraints(_spTT, new GBC(0,4).setGridSpan(4, 1).setInsets(2,2,2,2).setFill(GBC.BOTH).setWeight(1.0, 1.0));
		this.add(_spTT);

	
		_rdbSpace.setActionCommand(Parser.SEPARATOR_SPASE);
		_rdbTab.setActionCommand(Parser.SEPARATOR_TABULATION);
		_rdbVLine.setActionCommand(Parser.SEPARATOR_VERTICAL);
		_rdbComma.setActionCommand(Parser.SEPARATOR_COMMA);
		_rdbSemicolon.setActionCommand(Parser.SEPARATOR_SEMICOLON);
		_rdbOther.setActionCommand(CC.STR_EMPTY);
		
		_rdbSpace.setText(_bnd.getString("Separator.Name.Space"));
		_rdbTab.setText(_bnd.getString("Separator.Name.Tab"));
		_rdbVLine.setText(_bnd.getString("Separator.Name.VLine"));
		_rdbComma.setText(_bnd.getString("Separator.Name.Comma"));
		_rdbSemicolon.setText(_bnd.getString("Separator.Name.Semicolon"));
		_rdbOther.setText(_bnd.getString("Separator.Name.Other"));
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
				_header.setDelimiter(_sepa);
				_header.Run();
				_ttm.setHeader(_header);
				_treeTab.updateUI();
			}
		}
	};

	Action actSelected = new AbstractAction() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JRadioButton rdb = (JRadioButton) e.getSource();
			_sepa = rdb.getActionCommand();
			_txtOtherSepa.setEnabled(_sepa.length() == 0);
			if (_sepa.length() > 0)
				_txtOtherSepa.setText(CC.STR_EMPTY);
			else
				_txtOtherSepa.requestFocus();
			
		}
	};
}
 