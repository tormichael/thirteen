package tor.java.thirteen.card;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

public class tBin 
{
	private byte[] Bin;
	private String Fmt;
	private String Type;
	private String Note;
	private int NN;
	
	
	
	public byte[] getBin() {
		return Bin;
	}

	public void setBin(byte[] bin) {
		Bin = bin;
	}

	public String getFmt() {
		return Fmt;
	}



	public void setFmt(String fmt) {
		Fmt = fmt;
	}



	public String getType() {
		return Type;
	}



	public void setType(String type) {
		Type = type;
	}



	public String getNote() {
		return Note;
	}



	public void setNote(String note) {
		Note = note;
	}



	public int getNN() {
		return NN;
	}



	public void setNN(int nN) {
		NN = nN;
	}



	private void tbin() 
	{
		Bin = null;
		Fmt = null;
		Type = null;
		Note = null;
		NN = 0;
	}

	
	public void SaveImageAsBin(Image aImg)
	{
		if (aImg instanceof BufferedImage)
		{
			BufferedImage bi = (BufferedImage) aImg;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try
			{
				//ImageIO.write(bi, "jpg", baos); PNG
				ImageIO.write(bi, "PNG", baos); 
				baos.flush();
				Bin = baos.toByteArray();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			
		}
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException 
	{
		tBin bin = new tBin();
		
		bin.setBin(Bin.clone());
		bin.setFmt(new String(Fmt));
		bin.setType(new String(Type));
		bin.setNote(new String(Note));
		bin.setNN(NN);
		return bin;
	}
}
