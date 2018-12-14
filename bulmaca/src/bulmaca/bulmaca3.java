package bulmaca;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class bulmaca3 extends ortak 
{
	static String[] bulmaca = {"süt","te","erzak","aka","tos","tef","er","zat","ako","takas"};
	static String[] sorular = {"memelilerin yavrularýný beslediði yüksek besinli su (3-5)","mühendis cetveli, Tellür'ün simgesi (6-7)","uzun süre saklanabilen yiyeceklerin genel adý (11-15)","cömert, mert, babacan, dost (18-20)","alýnla vuruþ (23-25)","zilli çalgý (6-16)","asker, erken (7-12)","kiþi, kimse (13-23)","Odunu marangozlukta ve kaplamacýlýkta kullanýlan bir Afrika aðacý (14-24)","Mal alýp karþýlýðýnda mal vererek ödeþme, Deðiþ tokuþ (5-25)"};
	
	static String[] bulmacasplit = new String[bulmaca.length];
	static String[] bulmacaindis = new String[sorular.length];
	
	static int yesil_sayisi;
	static int enable_sayisi;
	
	
	public bulmaca3()
	{
		//bulmacasplit'e ilk deðer nesne türetilirken atýlýyor.
		System.arraycopy(ortak.noktaEkleBulmaca(bulmaca),0,bulmacasplit,0,bulmaca.length);
		
		System.arraycopy(ortak.noktaEkleIndis(bulmaca,sorular,ortak.yukari_asagi_indis_bul(bulmaca, sorular)),0,bulmacaindis,0,sorular.length);
		
		JTFleriDisableEt(bulmacaindis);
		
		JLabelOlustur(ortak.yukari_asagi_indis_bul(bulmaca, sorular));
		
		
		yesil_sayisi = 0;
		this.setTitle("BULMACA-3");
				
		//Label'lara sorularý yaz
		for(int i=0; i<sorular.length; i++) 
			jlab_soru[i].setText((i+1)+") "+sorular[i]);	
		
		//fazla label'larý görünmez yap
		if(sorular.length<11) 
			for(int i=10;i>=sorular.length;i--)
				jlab_soru[i].setVisible(false);
		
		
		//bulmacaya göre týklanmayacak olan jtf'leri ayarla
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
		//sonuclarý göster butonu
		if(arg0.getActionCommand().equals("buton_goster"))
		{
			//ilk for kelimeleri ve jtf indislerini noktalara göre harflere parçalayýp diziye atacak
			for(int i=0; i<bulmaca.length; i++)
			{			
				String[] harfler = bulmacasplit[i].split("\\.");
				String[] indisler = bulmacaindis[i].split("\\.");
				//String[] indisler = bulmacaindis[i].split(Pattern.quote(".")); //alternatif olarak
				
				//diziye atanan deðerlerle jtf'ler setText edilir
				for(int k=0; k<harfler.length; k++)
					jtf_bulmaca[Integer.parseInt(indisler[k])].setText(harfler[k].toUpperCase(trlocale));						
			}
			
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Yeni Bir Oyuna Baþlamak Ýster Misiniz?","Tebrikler, Baþarýyla Bitirdiniz.",dialogButton);
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
		
		//textfield geliyor, kaçýncý textfield olduðu (indisi), ve o harf geliyor
		ilkForKir:
		for(int i=0; i<bulmaca.length; i++) //toplam kelime sayýsý kadar dön
		{			
			String[] harfler = bulmacasplit[i].split("\\.");
			String[] indisler = bulmacaindis[i].split("\\."); 
			//String[] indisler = bulmacaindis[i].split(Pattern.quote(".")); //alternatif olarak
			
			//diziye atanan deðerlerle jtf'lerin deðerleri olmasý gerekenle kontrol edilir
			for(int k=0; k<harfler.length; k++) //k. kelimenin uzunluðu kadar dön
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
							int dialogResult = JOptionPane.showConfirmDialog (null, "Yeni Bir Oyuna Baþlamak Ýster Misiniz?","Tebrikler, Baþarýyla Bitirdiniz.",dialogButton);
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