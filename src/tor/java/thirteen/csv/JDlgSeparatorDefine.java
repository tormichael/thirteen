package tor.java.thirteen.csv;

import java.util.ResourceBundle;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import tor.java.thirteen.Thirteen;

public class JDlgSeparatorDefine extends JDialog 
{
	private ResourceBundle _bnd;

	private boolean _isResultOk;
	
	public boolean isResultOk()
	{
		return _isResultOk;
	}
	
	public JDlgSeparatorDefine()
	{
		_isResultOk = false;
		
		_bnd = ResourceBundle.getBundle(Thirteen.CT_RESOURCE_TEXT);
		this.setTitle(_bnd.getString("JDlgSD.Title"));
		this.setModal(true);
		this.setSize(300, 100);
		
		GridBagLayout gbl = new GridBagLayout();
		JPanel pnl =new JPanel(gbl);
		
		this.add(pnl, BorderLayout.NORTH);
		
		this.add(new JPanel(), BorderLayout.CENTER);
		
		JPanel pnlButton = new JPanel();
		JButton _btnOk = new JButton(actOk);
		_btnOk.setText(_bnd.getString("Button.Ok"));
		pnlButton.add(_btnOk);
		JButton _btnCancel = new JButton(actCancel);
		_btnCancel.setText(_bnd.getString("Button.Cancel"));
		pnlButton.add(_btnCancel);
		this.add(pnlButton, BorderLayout.SOUTH);
}

	Action actOk = new AbstractAction() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			_isResultOk = true;
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
	
}
