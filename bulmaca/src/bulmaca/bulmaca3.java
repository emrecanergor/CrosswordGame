package bulmaca;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class bulmaca3 extends ortak 
{
	static String[] bulmaca = {"s�t","te","erzak","aka","tos","tef","er","zat","ako","takas"};
	static String[] sorular = {"memelilerin yavrular�n� besledi�i y�ksek besinli su (3-5)","m�hendis cetveli, Tell�r'�n simgesi (6-7)","uzun s�re saklanabilen yiyeceklerin genel ad� (11-15)","c�mert, mert, babacan, dost (18-20)","al�nla vuru� (23-25)","zilli �alg� (6-16)","asker, erken (7-12)","ki�i, kimse (13-23)","Odunu marangozlukta ve kaplamac�l�kta kullan�lan bir Afrika a�ac� (14-24)","Mal al�p kar��l���nda mal vererek �de�me, De�i� toku� (5-25)"};
	
	static String[] bulmacasplit = new String[bulmaca.length];
	static String[] bulmacaindis = new String[sorular.length];
	
	static int yesil_sayisi;
	static int enable_sayisi;
	
	
	public bulmaca3()
	{
		//bulmacasplit'e ilk de�er nesne t�retilirken at�l�yor.
		System.arraycopy(ortak.noktaEkleBulmaca(bulmaca),0,bulmacasplit,0,bulmaca.length);
		
		System.arraycopy(ortak.noktaEkleIndis(bulmaca,sorular,ortak.yukari_asagi_indis_bul(bulmaca, sorular)),0,bulmacaindis,0,sorular.length);
		
		JTFleriDisableEt(bulmacaindis);
		
		JLabelOlustur(ortak.yukari_asagi_indis_bul(bulmaca, sorular));
		
		
		yesil_sayisi = 0;
		this.setTitle("BULMACA-3");
				
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