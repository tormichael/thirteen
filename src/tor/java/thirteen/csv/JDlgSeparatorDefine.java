package tor.java.thirteen.csv;

import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import JCommonTools.AsRegister;
import JCommonTools.CC;
import JCommonTools.CodeText;
import JCommonTools.GBC;
import JCommonTools.DB.dDBDriver;
import tor.java.thirteen.Thirteen;
import tor.java.thirteen.dic.wdpPerson;
import tor.java.thirteen.parser.Parser;

public class JDlgSeparatorDefine extends JDialog 
{
	private ResourceBundle _bnd;
	private AsRegister _reg;
	private String _sepa;

	private JTextField 	_txtVal;
	private JComboBox<CodeText> _cboType;
	private JComboBox<CodeText> _cboNumSubCol;
	
	private ButtonGroup _grpSepa;
	private JRadioButton _rdbSpace;
	private JRadioButton _rdbTab;
	private JRadioButton _rdbVLine;
	private JRadioButton _rdbComma;
	private JRadioButton _rdbSemicolon;
	
	private JRadioButton _rdbOther;
	private JTextField _txtOtherSepa; 

	private boolean _isResultOk;
	
	public boolean isResultOk()
	{
		return _isResultOk;
	}

	public String getText()
	{
		return _txtVal.getText();
	}
	public void setText(String aText)
	{
		_txtVal.setText(aText);
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
	
	public void setPreferencesPath(String aRefPath)
	{
		if (aRefPath != null && aRefPath.length() > 0)
		{
			_reg = new AsRegister();
			_reg.set_node(Preferences.userRoot().node(aRefPath+"/JDlgSeparatorDefine" ));
			_reg.LoadWindowSize(this);
			_reg.LoadWindowLocation(this);
			
			addWindowListener(new WindowAdapter() 
			{
				@Override
				public void windowClosing(WindowEvent e) 
				{
					_savePreferences();
					super.windowClosing(e);
				}
			});
		}
	}
	
	/**
	 * 
	 */
	public JDlgSeparatorDefine()
	{
		_isResultOk = false;
		
		_bnd = ResourceBundle.getBundle(Thirteen.CT_RESOURCE_TEXT);
		this.setTitle(_bnd.getString("JDlgSD.Title"));
		this.setModal(true);
		this.setSize(300, 100);
		
		GridBagLayout _gbl = new GridBagLayout();
		//this.setLayout(_gbl);
		JPanel pnl =new JPanel(_gbl);
		/// ROWi0 
		JLabel lbl = new JLabel(_bnd.getString("Label"));
		_gbl.setConstraints(lbl, new GBC(0,0).setInsets(2,2,2,2));
		pnl.add(lbl);
		lbl = new JLabel();
		_gbl.setConstraints(lbl, new GBC(1,0).setInsets(2,2,2,2));
		pnl.add(lbl);
		lbl = new JLabel(_bnd.getString("Label"));
		_gbl.setConstraints(lbl, new GBC(2,0).setInsets(2,2,2,2));
		pnl.add(lbl);
		/// ROWi1 
		_txtVal = new JTextField();
		_gbl.setConstraints(_txtVal, new GBC(0,1).setGridSpan(2, 1).setInsets(2,2,2,2).setFill(GBC.HORIZONTAL).setWeight(0.8, 0.0));
		pnl.add(_txtVal);
		_cboType = new JComboBox<CodeText>();
		_gbl.setConstraints(_cboType, new GBC(2,1).setInsets(2,2,2,2).setFill(GBC.HORIZONTAL).setWeight(0.2, 0.0));
		pnl.add(_cboType);
		/// ROWi2 
		GridBagLayout gbl = new GridBagLayout();
		_grpSepa = new ButtonGroup();
		JPanel pnlSeparators = new JPanel(gbl);
		pnlSeparators.setBorder(BorderFactory.createTitledBorder(_bnd.getString("JDlgSD.Panel.Sepa.Title")));
		//pnlSeparators.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			_rdbSpace = new JRadioButton(actSelected);
			gbl.setConstraints(_rdbSpace, new GBC(0,0).setIns(2).setAnchor(GBC.WEST));
			pnlSeparators.add(_rdbSpace);
			_grpSepa.add(_rdbSpace);
			_rdbTab = new JRadioButton(actSelected);
			gbl.setConstraints(_rdbTab, new GBC(0,1).setIns(2).setAnchor(GBC.WEST));
			pnlSeparators.add(_rdbTab);
			_grpSepa.add(_rdbTab);
			_rdbVLine = new JRadioButton(actSelected);
			gbl.setConstraints(_rdbVLine, new GBC(0,2).setIns(2).setAnchor(GBC.WEST));
			pnlSeparators.add(_rdbVLine);
			_grpSepa.add(_rdbVLine);
			_rdbComma = new JRadioButton(actSelected);
			gbl.setConstraints(_rdbComma, new GBC(1,0).setIns(2).setAnchor(GBC.WEST));
			pnlSeparators.add(_rdbComma);
			_grpSepa.add(_rdbComma);
			_rdbSemicolon = new JRadioButton(actSelected);
			gbl.setConstraints(_rdbSemicolon, new GBC(1,1).setIns(2).setAnchor(GBC.WEST));
			pnlSeparators.add(_rdbSemicolon);
			_grpSepa.add(_rdbSemicolon);
			JPanel pnlOther = new JPanel(new BorderLayout());
				_rdbOther = new JRadioButton(actSelected);
				_txtOtherSepa = new JTextField();
				_txtOtherSepa.setEnabled(false);
				pnlOther.add(_rdbOther, BorderLayout.WEST);
				pnlOther.add(_txtOtherSepa, BorderLayout.CENTER);
			gbl.setConstraints(pnlOther, new GBC(1,2).setIns(2).setAnchor(GBC.WEST).setFill(GBC.HORIZONTAL));
			pnlSeparators.add(pnlOther);
			_grpSepa.add(_rdbOther);

		_gbl.setConstraints(pnlSeparators, new GBC(0,2).setGridSpan(3, 1).setInsets(2,2,2,2).setFill(GBC.HORIZONTAL).setWeight(1.0, 0.0));
		pnl.add(pnlSeparators);
		/// ROWi3 
		lbl = new JLabel();
		_gbl.setConstraints(lbl, new GBC(0,3).setInsets(2,2,2,2).setFill(GBC.HORIZONTAL).setWeight(0.4, 0.0));
		pnl.add(lbl);
		_cboNumSubCol = new JComboBox<CodeText>();
		_gbl.setConstraints(_cboNumSubCol, new GBC(1,3).setGridSpan(2, 1).setInsets(2,2,2,2).setFill(GBC.HORIZONTAL));
		pnl.add(_cboNumSubCol);
		/// ROWi4 
		JPanel pnlSpace = new JPanel();
		_gbl.setConstraints(pnlSpace, new GBC(0,4).setGridSpan(3, 1).setInsets(2,2,2,2).setWeight(1.0, 1.0));
		pnl.add(pnlSpace);
		/// ROWi5 
		JPanel pnlButton = new JPanel();
			JButton _btnOk = new JButton(actOk);
			_btnOk.setText(_bnd.getString("Button.Ok"));
			pnlButton.add(_btnOk);
			JButton _btnCancel = new JButton(actCancel);
			_btnCancel.setText(_bnd.getString("Button.Cancel"));
			pnlButton.add(_btnCancel);
		_gbl.setConstraints(pnlButton, new GBC(0,5).setGridSpan(3, 1).setInsets(2,2,2,2).setFill(GBC.HORIZONTAL).setWeight(1.0, 0.0));
		pnl.add(pnlButton);
		
		this.add(pnl, BorderLayout.CENTER);

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

		_cboType.addItem(new CodeText(0, ""));
		wdpPerson[] wdpp = wdpPerson.values();
		for (int ii = 0; ii < wdpp.length; ii++)
			_cboType.addItem(new CodeText(wdpp[ii].getCode(), wdpp[ii].getTitle()));

		_cboNumSubCol.addItem(new CodeText(0, "not"));
		for (int ii = 2; ii < 10; ii++)
			_cboNumSubCol.addItem(new CodeText(ii, CC.STR_EMPTY+ii));

	}

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
	
	Action actOk = new AbstractAction() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			_isResultOk = true;
			_savePreferences();
			JDlgSeparatorDefine.this.setVisible(false);
		}
	};

	Action actCancel = new AbstractAction() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JDlgSeparatorDefine.this.setVisible(false);
		}
	};

	private void _savePreferences()
	{
		if (_reg != null)
		{
			_reg.SaveWindowSize(this);
			_reg.SaveWindowLocation(this);
		}
	}
}
