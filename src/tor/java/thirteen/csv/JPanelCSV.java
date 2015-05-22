package tor.java.thirteen.csv;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tor.java.thirteen.parser.Parser;
import JCommonTools.GBC;

public class JPanelCSV extends JPanel 
{
	private GridBagLayout _gbl;
	private JTextField 	_txtVal;
	private JButton 		_btnSetSeparator;
	
	private Parser _parser;
	private JPanelCSV[] _subPan; 
	
	public JPanelCSV (Parser aPar)
	{
		_gbl = new GridBagLayout();
		this.setLayout(_gbl);
		
		_parser = aPar;
		_subPan = null;
		
		_txtVal = new JTextField();
		if (_parser != null)
			_txtVal.setText(_parser.getSource());
		_gbl.setConstraints(_txtVal, new GBC(0,0).setIns(2).setWeight(1.0, 1.0));
		this.add(_txtVal);
		_btnSetSeparator = new JButton(actSetSeparator);
		actSetSeparator.putValue(Action.NAME, "<not defined>");
		_gbl.setConstraints(_txtVal, new GBC(1,0).setIns(2));
		this.add(_btnSetSeparator);
		_ShowPanel();
		
	}
	
	private void _ShowPanel()
	{
		if (_subPan != null && _subPan.length > 0)
		{
			for (int ii = 0; ii < _subPan.length; ii++)
				this.remove(_subPan[ii]);
		}

		if (_parser != null& _parser.getValue() != null && _parser.getValue().length > 0)
		{
			_subPan = new JPanelCSV[_parser.getValue().length];
			for (int ii = 0; ii < _subPan.length; ii++)
			{
				_subPan[ii] = new JPanelCSV(_parser.getValueParser()[ii]);
				_gbl.setConstraints(_subPan[ii], new GBC(0,0).setGridSpan(2, 1).setAnchor(GBC.EAST).setIns(2).setWeight(0.85, 1.0));
				this.add(_subPan[ii]);
			}
		}
	}
	
	Action actSetSeparator = new AbstractAction() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JDlgSeparatorDefine dlg = new JDlgSeparatorDefine();
			dlg.setVisible(true);
			if (dlg.isResultOk())
			{
				actSetSeparator.putValue(Action.NAME, "separator is ...");
				
			}
		}
	};

}
