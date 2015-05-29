package tor.java.thirteen.csv;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tor.java.thirteen.parser.Parser;
import JCommonTools.CC;
import JCommonTools.GBC;

public class JPanelCSV extends JPanel 
{
	private GridBagLayout _gbl;
	private JTextField 	_txtVal;
	private JButton 		_btnSetSeparator;
	
	private Parser _parser;
	private JPanelCSV[] _subPan; 

	private String _regPath;
	public void setPreferencesPath(String aRefPath)
	{
		if (aRefPath != null)
		{
			_regPath = aRefPath+"/JPanelCSV" ;
			if (_subPan != null)
				for (int ii = 0; ii < _subPan.length; ii++)
					_subPan[ii].setPreferencesPath(_regPath);
		}
		else
		{
			_regPath = null;
		}
	}
	
	public JPanelCSV (Parser aPar)
	{
		_parser = aPar;
		_subPan = null;
		_regPath = null;
		
		_gbl = new GridBagLayout();
		this.setLayout(_gbl);
		//pnlData.setBorder(BorderFactory.createTitledBorder(_wld.getString("TitledBorder.fCSV.CSVFile")));
		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//this.setBorder(BorderFactory.createRaisedBevelBorder());
		
		_txtVal = new JTextField();
		_gbl.setConstraints(_txtVal, new GBC(0,0).setInsets(2,2,2,10).setFill(GBC.HORIZONTAL).setWeight(1.0, 0.0));
		this.add(_txtVal);
		_btnSetSeparator = new JButton(actSetSeparator);
		actSetSeparator.putValue(Action.NAME, "<not defined>");
		_gbl.setConstraints(_btnSetSeparator, new GBC(1,0).setIns(2).setAnchor(GBC.EAST));
		this.add(_btnSetSeparator);
		
		if (_parser != null)
			_txtVal.setText(_parser.getSource());

		_ShowPanel();
	}
	
	private void _ShowPanel()
	{
		if (_subPan != null && _subPan.length > 0)
		{
			for (int ii = 0; ii < _subPan.length; ii++)
				this.remove(_subPan[ii]);
		}

		if (_parser != null && _parser.getValue() != null && _parser.getValue().length > 1)
		{
			_subPan = new JPanelCSV[_parser.getValue().length];
			for (int ii = 0; ii < _subPan.length; ii++)
			{
				_subPan[ii] = new JPanelCSV(_parser.getValueParser()[ii]);
				_gbl.setConstraints(_subPan[ii], new GBC(0,ii+1).setGridSpan(2, 1).setFill(GBC.HORIZONTAL).setInsets(2, 30, 2, 2).setWeight(0.9, 0.0));
				_subPan[ii].setPreferencesPath(_regPath);
				this.add(_subPan[ii]);
			}
		}
		
		this.repaint();
		this.validate();
	}
	
	Action actSetSeparator = new AbstractAction() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JDlgSeparatorDefine dlg = new JDlgSeparatorDefine();
			dlg.setPreferencesPath(_regPath);
			String sepa = _parser.getSeparator();
			dlg.setSeparator(sepa);
			dlg.setVisible(true);
			if (dlg.isResultOk())
			{
				if (!sepa.equals(dlg.getSeparator()))
				{
					_parser.setSeparator(dlg.getSeparator());
					_parser.Run(_txtVal.getText());
					_ShowPanel();
				}
				actSetSeparator.putValue(Action.NAME, "separator is ...");
			}
		}
	};

}
