package bulmaca;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class bulmaca4 extends ortak 
{
	static String[] bulmaca = {"la","antik","nara","es","fa","labne","an","as","t�r","af","eke"};
	static String[] sorular = {"Bir nota (1-2)","Eski �a�lara ait (6-10)","Hayk�rma, ba��rma (16-19)","Bilin�, �uur, Notada durak isareti (21-22)","Bir nota (24-25)","S�r�lebilir peynir (1-21)","En k�sa zaman, tarla s�n�r� (2-7)","Iskambilde birli, de�irmen (17-22)","Uzun ve geni� y�k ta��ma arac� (8-18)","Ba���lama (19-24)","Tecr�beli, kurnaz (5-15)"};
	
	static String[] bulmacasplit = new String[bulmaca.length];
	static String[] bulmacaindis = new String[sorular.length];
	
	static int yesil_sayisi;
	static int enable_sayisi;
	
	
	public bulmaca4()
	{
		//bulmacasplit'e ilk de�er nesne t�retilirken at�l�yor.
		System.arraycopy(ortak.noktaEkleBulmaca(bulmaca),0,bulmacasplit,0,bulmaca.length);
		
		System.arraycopy(ortak.noktaEkleIndis(bulmaca,sorular,ortak.yukari_asagi_indis_bul(bulmaca, sorular)),0,bulmacaindis,0,sorular.length);
		
		JTFleriDisableEt(bulmacaindis);
		
		JLabelOlustur(ortak.yukari_asagi_indis_bul(bulmaca, sorular));
		
		
		yesil_sayisi = 0;
		this.setTitle("BULMACA-4");
				
		//Label'lara sorular� yaz
		for(int i=0; i<sorular.length; i++) 
			jlab_soru[i].setText((i+1)+") "+sorular[i]);	
		
		//fazla label'lar� g�r�nmez yap
		if(sorular.length<11) 
			for(int i=10;i>=sorular.length;i--)
				jlab_soru[i].setVisible(false);
		
		
		//bulmacaya g�re t�klanmayacak olan jtf'leri ayarla
	/*	for(int i = 0; i<25; i++)
			if((i == 0)|| (i == 9)|| (i == 11)|| (i == 28))
			{				
				jtf_bulmaca[i].setText("X");
				jtf_bulmaca[i].disable();
				baslangic_disable_sayisi++;
			}
	*/
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) 
	{	
		if((KeyEvent.getKeyText(e.getKeyCode())!= "Backspace")&&(KeyEvent.getKeyText(e.getKeyCode())!= "Caps Lock")&&(KeyEvent.getKeyText(e.getKeyCode())!= "Delete"))
		{
			try
			{
				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_TAB);
				r.keyRelease(KeyEvent.VK_TAB);
			}
			catch(AWTException ex){}
		}
	}
	
		
	@Override
	public void actionPerformed(ActionEvent arg0)  
	{		
		//sonuclar� g�ster butonu
		if(arg0.getActionCommand().equals("buton_goster"))
		{
			//ilk for kelimeleri ve jtf indislerini noktalara g�re harflere par�alay�p diziye atacak
			for(int i=0; i<bulmaca.length; i++)
			{			
				String[] harfler = bulmacasplit[i].split("\\.");
				String[] indisler = bulmacaindis[i].split("\\.");
				//String[] indisler = bulmacaindis[i].split(Pattern.quote(".")); //alternatif olarak
				
				//diziye atanan de�erlerle jtf'ler setText edilir
				for(int k=0; k<harfler.length; k++)
					jtf_bulmaca[Integer.parseInt(indisler[k])].setText(harfler[k].toUpperCase(trlocale));						
			}
			
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Yeni Bir Oyuna Ba�lamak �ster Misiniz?","Tebrikler, Ba�ar�yla Bitirdiniz.",dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION)
			{
				main.setVisible(false);
				ortak.olustur();
			}
			else if(dialogResult == JOptionPane.NO_OPTION)
			{
				 main.dispatchEvent(new WindowEvent(main, WindowEvent.WINDOW_CLOSING));
			}
		}		
	}
	
	
	public static void harf_eslestirme(JTextField jtf, int indis, String harf)
	{
		
		String bulmaca_harf = "";
		
		//textfield geliyor, ka��nc� textfield oldu�u (indisi), ve o harf geliyor
		ilkForKir:
		for(int i=0; i<bulmaca.length; i++) //toplam kelime say�s� kadar d�n
		{			
			String[] harfler = bulmacasplit[i].split("\\.");
			String[] indisler = bulmacaindis[i].split("\\."); 
			//String[] indisler = bulmacaindis[i].split(Pattern.quote(".")); //alternatif olarak
			
			//diziye atanan de�erlerle jtf'lerin de�erleri olmas� gerekenle kontrol edilir
			for(int k=0; k<harfler.length; k++) //k. kelimenin uzunlu�u kadar d�n
					if(indisler[k].equals(indis+"")){bulmaca_harf=harfler[k]; break ilkForKir;}
		}
			
				if(harf.length()==1)
				{
					if(bulmaca_harf.equals(harf))
					{
						jtf.setBackground(Color.GREEN);
						jtf.disable();
						jtf.setDisabledTextColor(Color.BLUE);
						yesil_sayisi++;
						
						if((enable_sayisi)==yesil_sayisi)
						{
							int dialogButton = JOptionPane.YES_NO_OPTION;
							int dialogResult = JOptionPane.showConfirmDialog (null, "Yeni Bir Oyuna Ba�lamak �ster Misiniz?","Tebrikler, Ba�ar�yla Bitirdiniz.",dialogButton);
							if(dialogResult == JOptionPane.YES_OPTION)
							{
								main.setVisible(false);
								ortak.olustur();
							}
							else if(dialogResult == JOptionPane.NO_OPTION)
							{
								 main.dispatchEvent(new WindowEvent(main, WindowEvent.WINDOW_CLOSING));
							}
							
						}
					}
					else
						jtf.setBackground(Color.RED);						
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Kutucuklara 1'er tane HARF girmelisiniz.","HATA", JOptionPane.WARNING_MESSAGE);
					return;
				}	
	}
}