package bulmaca;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class bulmaca2 extends ortak 
{
	static String[] bulmaca = {"nihai","nal","tay","adana","ray","bar","in","da","hatay","alan","yat"};
	static String[] sorular = {"Son, sonuncu (1-5)","At'ýn týrnaklarýna çakýlan, ayaðýn þekline uygun demir parçasý(7-9)","at yavrusu (13-15)","01 plakalý ilimiz (16-20)","Demiryolu (21-23)","Basýnç birimi, beyaz küf (11-21)","maðara (2-8)","Rusça'da onay anlamýna sahip kelime (17-22)","Künefesiyle meþhur ilimiz (3-23)","Meydan, yüz ölçümü (4-19)","Özel gezinti gemisi (15-25)"};
	
	static String[] bulmacasplit = new String[bulmaca.length];
	static String[] bulmacaindis = new String[sorular.length];
	
	//static String[] bulmacaindis = {"0.1.2.3.4","6.7.8","12.13.14","15.16.17.18.19","20.21.22","10.15.20","1.6","16.21","2.7.12.17.22","3.8.13.18","14.19.24"};
	
	static int yesil_sayisi;
	static int enable_sayisi;
	
	
	public bulmaca2()
	{
		//bulmacasplit'e ilk deðer nesne türetilirken atýlýyor.
		System.arraycopy(ortak.noktaEkleBulmaca(bulmaca),0,bulmacasplit,0,bulmaca.length);
		
		System.arraycopy(ortak.noktaEkleIndis(bulmaca,sorular,ortak.yukari_asagi_indis_bul(bulmaca, sorular)),0,bulmacaindis,0,sorular.length);
		
		JTFleriDisableEt(bulmacaindis);
		
		JLabelOlustur(ortak.yukari_asagi_indis_bul(bulmaca, sorular));
		
		yesil_sayisi = 0;
		
		this.setTitle("BULMACA-2");
				
		//Label'lara sorularý yaz
		for(int i=0; i<sorular.length; i++) 
			jlab_soru[i].setText((i+1)+") "+sorular[i]);	
		
		//fazla label'larý görünmez yap
		if(sorular.length<11) 
			for(int i=10;i>=sorular.length;i--)
				jlab_soru[i].setVisible(false);
		
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