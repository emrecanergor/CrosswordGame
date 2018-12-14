package bulmaca;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class bulmaca extends ortak 
{
	static String[] bulmaca = {"ahenk","at","re","ran","ata","adana","ra","nara","en"};
	static String[] sorular = {"Uyum(1-5)","Bir hayvan(11-12)","Bir nota(14-15)","Nazým Hikmet'in Soyadý(18-20)","Baba, cet(21-23)","Bir büyükþehirimiz(1-21)","Güneþ Tanrýsý(18-23)","Haykýrma, baðýrma(4-19)","geniþlik(15-20)"};
	
	static String[] bulmacasplit = new String[bulmaca.length];
	static String[] bulmacaindis = new String[sorular.length];
	
	//static String[] bulmacasplit = {"a.h.e.n.k","a.t","r.e","r.a.n","a.t.a","a.d.a.n.a","r.a","n.a.r.a","e.n"};
	//static String[] bulmacaindis = {"0.1.2.3.4","10.11","13.14","17.18.19","20.21.22","0.5.10.15.20","17.22","3.8.13.18","14.19"};
	
	static int yesil_sayisi;
	static int enable_sayisi;
	
	
	public bulmaca()
	{
		//bulmacasplit'e ilk deðer nesne türetilirken atýlýyor.
		System.arraycopy(ortak.noktaEkleBulmaca(bulmaca),0,bulmacasplit,0,bulmaca.length);
		
		System.arraycopy(ortak.noktaEkleIndis(bulmaca,sorular,ortak.yukari_asagi_indis_bul(bulmaca, sorular)),0,bulmacaindis,0,sorular.length);
	
		JTFleriDisableEt(bulmacaindis);
		
		JLabelOlustur(ortak.yukari_asagi_indis_bul(bulmaca, sorular)); //kaçýncý indis yukaridan-asagiya baslangici
		//
		
		
		yesil_sayisi = 0;
		
		this.setTitle("BULMACA-1");
				
		//Label'lara sorularý yaz
		for(int i=0; i<sorular.length; i++) 
			jlab_soru[i].setText((i+1)+") "+sorular[i]);	
		
		//fazla label'larý görünmez yap
		if(sorular.length<11) 
			for(int i=10;i>=sorular.length;i--)
				jlab_soru[i].setVisible(false);
		
		/*//geliþtirildi, algoritma eklendi -->  JTFleriDisableEt(bulmacaindis);
		//bulmacaya göre týklanmayacak olan jtf'leri ayarla
		for(int i = 0; i<25; i++)
			if((i == 6)|| (i == 7)|| (i == 9)|| (i == 12)|| (i == 16)|| (i == 23)|| (i == 24))
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