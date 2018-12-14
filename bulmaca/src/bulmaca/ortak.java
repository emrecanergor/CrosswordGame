package bulmaca;

import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.Random;

import javax.swing.*;

public class ortak extends JFrame implements ActionListener, KeyListener, FocusListener
{	
	JButton buton_goster;
	static JTextField[] jtf_bulmaca;
	
	static JLabel jlab_soru[] = new JLabel[11];
	static JLabel jlab_soldan_saga = new JLabel("SOLDAN-SAGA:");
	static JLabel jlab_yukaridan_asagiya = new JLabel("YUKARIDAN-ASAGIYA:");
	
	static JPanel jpn_sorular;
	static JPanel jpn_ana;
	
	Locale trlocale= new Locale("tr","TR");
	
	
	static int random_deger_bulmaca;
	
	static JFrame main;
	
	public ortak()
	{
		main = this;
		
		//random deðer üretip hangi bulmacayý seçip, nesnesini oluþturacaðýmý buluyorum
		try{
		//Random rand = new Random();
		//random_deger_bulmaca = rand.nextInt(10);
		
		
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex+" Hatasý","HATA MESAJI", JOptionPane.WARNING_MESSAGE);
		}
		
		this.setTitle("BULMACA");
		this.setDefaultCloseOperation(3);
		this.setSize(500,850);
		this.setLayout(new FlowLayout());

		jpn_ana = new JPanel();
		jpn_ana.setPreferredSize(new Dimension(450, 800));
		
		jpn_sorular = new JPanel();
		jpn_sorular.setPreferredSize(new Dimension(450, 340));
		
		JPanel jpn_buton = new JPanel();
		jpn_buton.setPreferredSize(new Dimension(450, 350));
		
		buton_goster = new JButton("Sonuclari Goster");
		buton_goster.addActionListener(this);
		buton_goster.setActionCommand("buton_goster");
		jpn_buton.add(buton_goster);		
		
		//bütün bulmaca textfield ve panellerini tutacak olan alan
		JPanel jpn_temel_ana = new JPanel();
		jpn_temel_ana.setPreferredSize(new Dimension(310,350));
		jpn_temel_ana.setAlignmentX(SwingConstants.CENTER);
		
		//yatay olarak 5 tane textfield eklenecek alan
		JPanel[] jpn_temel = new JPanel[5];
		//her bir textfield için panel 
		JPanel[] jpn_ic = new JPanel[25];
		//harf textfield'larý
		jtf_bulmaca = new JTextField[25];
		
		int sayac_bulmaca_satir = -1; //her 5 textfield'da yeni bir panel'e geçmek için kullanýlacak sayaç
		
			for(int i=0;i<25;i++)
			{
			
			jpn_ic[i] = new JPanel();
			jpn_ic[i].setPreferredSize(new Dimension(54, 49));
			//jpn_ic[i].setAlignmentX(SwingConstants.CENTER);
				
			Font font = new Font("Arial", Font.BOLD,12);
			
			
			if(i%5==0){
			sayac_bulmaca_satir++;
			jpn_temel[(sayac_bulmaca_satir)] = new JPanel();
			//jpn_temel[sayac_bulmaca_satir].setBorder(BorderFactory.createLineBorder(Color.black)); //border
			jpn_temel[(sayac_bulmaca_satir)].setPreferredSize(new Dimension(308,61));
			
			jpn_temel_ana.add(jpn_temel[sayac_bulmaca_satir]);
			
			}

			jtf_bulmaca[i] = new JTextField(i+"");
			jtf_bulmaca[i].setPreferredSize(new Dimension(50,45));
			jtf_bulmaca[i].setHorizontalAlignment(jtf_bulmaca[i].CENTER);
			jtf_bulmaca[i].setAlignmentX(SwingConstants.CENTER);
			jtf_bulmaca[i].setText((i+1)+"");
			jtf_bulmaca[i].setFont(font);
			jtf_bulmaca[i].addKeyListener(this);
				JTextField kullanilan_jtf = jtf_bulmaca[i];
				int kullanilan_i = i;
			jtf_bulmaca[i].addFocusListener(new FocusListener(){
				String onceki_jtf_degeri="";
				String sonraki_jtf_degeri="";
				@Override
				public void focusGained(FocusEvent e)
				{
					onceki_jtf_degeri = (kullanilan_jtf.getText()).toLowerCase(trlocale);
					kullanilan_jtf.selectAll();
				}

				@Override
				public void focusLost(FocusEvent e) 
				{
					sonraki_jtf_degeri = (kullanilan_jtf.getText()).toLowerCase(trlocale);
					if(!(onceki_jtf_degeri.equals(sonraki_jtf_degeri)))
					{
					String jtf_deger = kullanilan_jtf.getText();		
					jtf_deger = jtf_deger.toLowerCase(trlocale);
					
					switch(random_deger_bulmaca)
					 {
					 case 0: bulmaca.harf_eslestirme(kullanilan_jtf, kullanilan_i, jtf_deger); break;
					 case 1: bulmaca2.harf_eslestirme(kullanilan_jtf, kullanilan_i, jtf_deger); break;
					 case 2: bulmaca3.harf_eslestirme(kullanilan_jtf, kullanilan_i, jtf_deger); break;
					 case 3: bulmaca4.harf_eslestirme(kullanilan_jtf, kullanilan_i, jtf_deger); break;
					 case 4: bulmaca5.harf_eslestirme(kullanilan_jtf, kullanilan_i, jtf_deger); break;
					 case 5: bulmaca6.harf_eslestirme(kullanilan_jtf, kullanilan_i, jtf_deger); break;
					 case 6: bulmaca7.harf_eslestirme(kullanilan_jtf, kullanilan_i, jtf_deger); break;
					 case 7: bulmaca8.harf_eslestirme(kullanilan_jtf, kullanilan_i, jtf_deger); break;
					 case 8: bulmaca9.harf_eslestirme(kullanilan_jtf, kullanilan_i, jtf_deger); break;
					 case 9: bulmaca10.harf_eslestirme(kullanilan_jtf, kullanilan_i, jtf_deger); break;
					 }
					
					jtf_deger = jtf_deger.toUpperCase(trlocale);
					kullanilan_jtf.setText(jtf_deger);
					}
				}
				});
			
			
			jpn_ic[i].add(jtf_bulmaca[i]);
			jpn_temel[sayac_bulmaca_satir].add(jpn_ic[i]);
			}
			
			jpn_ana.setLayout(new FlowLayout());
			
			jpn_ana.add(jpn_temel_ana);
			jpn_ana.add(jpn_sorular);
			jpn_ana.add(jpn_buton);
			
			this.add(jpn_ana);		
			
			this.setVisible(true);
		
	
}//const. bitiþ
	
	@Override
	public void actionPerformed(ActionEvent arg0){}
	
	
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

	
	@Override
	public void focusGained(FocusEvent arg0){}
	@Override
	public void focusLost(FocusEvent arg0){}
	
	
	public static void JLabelOlustur(int yukari_asagi_indis)
	{
		Font font = new Font("Arial", Font.BOLD,18);
		jlab_soldan_saga.setFont(font);
		jpn_sorular.add(jlab_soldan_saga);
		
		jlab_yukaridan_asagiya.setFont(font);
				
		for(int i=0 ; i<11 ; i++)
		{
			if(i == yukari_asagi_indis)
				jpn_sorular.add(jlab_yukaridan_asagiya);
			
			jlab_soru[i] = new JLabel(""+i); 	//k'yý string bir deðiþkene, Integer.toString fonksiyonu ile dönüþtürüp atmayý denedim olmadý
			jlab_soru[i].setHorizontalAlignment(SwingConstants.LEFT); 	//yazýlarý sola yapýþtýrmak istedim
			jlab_soru[i].setPreferredSize(new Dimension(430, 20));	//her bir label alta insin diye 500,850 olan ana panele bu þekilde eklemeyi deniyorum
			jpn_sorular.add(jlab_soru[i]);	//sorularýn bulunduðu panel'e teker teker eklemek istedim		
		}
	}
	
	
	public static void JTFleriDisableEt(String[] bulmaca_indis)
	{
		String[] dizi_indisler;
		int enablesayisi = 0;
		
			for(int i=0; i<25; i++)
			{
				jtf_bulmaca[i].setText("X");
				jtf_bulmaca[i].disable();
			}
			
			for(int k=0; k<bulmaca_indis.length; k++)
			{
				dizi_indisler = bulmaca_indis[k].split("\\.");
				
				for(int j=0; j<dizi_indisler.length;j++)
				{
					for(int i=0; i<25; i++)
					{
					//if(!dizi_indisler[k].equals("."))
					//{					}
						if(i == Integer.parseInt(dizi_indisler[j]))
						{
							if(!jtf_bulmaca[i].isEnabled())
							{
								enablesayisi++;
								jtf_bulmaca[i].enable();
								jtf_bulmaca[i].setText(""+(i+1));
							}
						break;
						}
					}	
				}
			}
			
			switch(random_deger_bulmaca)
			 {
			 case 0: bulmaca.enable_sayisi = enablesayisi; break;
			 case 1: bulmaca2.enable_sayisi = enablesayisi; break;
			 case 2: bulmaca3.enable_sayisi = enablesayisi; break;
			 case 3: bulmaca4.enable_sayisi = enablesayisi; break;
			 case 4: bulmaca5.enable_sayisi = enablesayisi; break;
			 case 5: bulmaca6.enable_sayisi = enablesayisi; break;
			 case 6: bulmaca7.enable_sayisi = enablesayisi; break;
			 case 7: bulmaca8.enable_sayisi = enablesayisi; break;
			 case 8: bulmaca9.enable_sayisi = enablesayisi; break;
			 case 9: bulmaca10.enable_sayisi = enablesayisi; break;
			 }
		}
	
	
	public static String[] noktaEkleBulmaca(String[] dizi)
	{
		String[] yeni_dizi = new String[dizi.length];
		String eski = "";
		String yeni = "";
		
		for(int i=0; i<dizi.length; i++)
		{
			eski = dizi[i];
			yeni="";
			for(int k=0; k<eski.length(); k++)
			{
				if(k!=(eski.length()-1))
				{
					yeni+=eski.substring(k, (k+1))+".";
				}
				else
				{
					yeni+=eski.substring(k, (k+1));
					yeni_dizi[i]=yeni;
				}
			}
		}
		return yeni_dizi;
	}

	public static int yukari_asagi_indis_bul(String[] dizi_bulmaca, String[] dizi_sorular)
	{
		String bulmaca="";
		String sorular_indis="";

		int indisin_baslangic_indisi = -1;
		int indis_uzunlugu = -1;
		int hedef_indis_uzunlugu;
		int hedef_indisin_baslangic_indisi;
		int ilk_indis;
		int hedef_indis;
		int bulunan_indis;
		
		for(int i=0; i<dizi_sorular.length; i++)
		{			
			bulmaca = dizi_bulmaca[i];
			sorular_indis = dizi_sorular[i];
		
			indisin_baslangic_indisi = sorular_indis.indexOf("(")+1;
			hedef_indisin_baslangic_indisi = sorular_indis.indexOf("-")+1;
			hedef_indis_uzunlugu = (sorular_indis.indexOf(")")-sorular_indis.indexOf("-"))-1;
			indis_uzunlugu = (sorular_indis.indexOf("-")-sorular_indis.indexOf("("))-1;
			
			ilk_indis = Integer.parseInt(sorular_indis.substring(indisin_baslangic_indisi,(indisin_baslangic_indisi+indis_uzunlugu)))-1;
			hedef_indis = (ilk_indis+1 + (bulmaca.length()-1));
			bulunan_indis = Integer.parseInt(sorular_indis.substring(hedef_indisin_baslangic_indisi,(hedef_indisin_baslangic_indisi+hedef_indis_uzunlugu)));
			
			if((bulunan_indis != hedef_indis))
				return i;
		}
		return dizi_sorular.length;
	}
	
	
	
	public static String[] noktaEkleIndis(String[] dizi_bulmaca, String[] dizi_sorular ,int yukari_kacinci_indisten_basliyor)
	{
		String[] yeni_indis_dizi = new String[dizi_bulmaca.length];
		String eski_bulmaca = "";
		String eski_indis="";
		String yeni_indis ="";
		int indisin_baslangic_indisi = -1;
		int ilk_indis = -1;
		int indis_uzunlugu = -1;
		
		for(int i=0; i<dizi_sorular.length; i++)
		{			
			eski_bulmaca = dizi_bulmaca[i];
			eski_indis = dizi_sorular[i];
			yeni_indis="";
		
			indisin_baslangic_indisi = eski_indis.indexOf("(")+1;
			indis_uzunlugu = (eski_indis.indexOf("-")-eski_indis.indexOf("("))-1;
			
			ilk_indis = Integer.parseInt(eski_indis.substring(indisin_baslangic_indisi,(indisin_baslangic_indisi+indis_uzunlugu)))-1;
			
			if((indisin_baslangic_indisi != -1)&&(indis_uzunlugu != -1)&&(ilk_indis != -1))
		{
			if((i < yukari_kacinci_indisten_basliyor))
			{
				//soldan-saga
				//nokta koyarak 1'er indis arttýrarak yaz (kelimeuzunluðu-1 kere)
			
				
				for(int k=0; k<eski_bulmaca.length(); k++)
				{
					if(k!=(eski_bulmaca.length()-1))
					{
						yeni_indis+=(ilk_indis+k)+".";
					}
					else
					{
						yeni_indis+=((ilk_indis+k)+"");
						yeni_indis_dizi[i]=yeni_indis;
					}
				}	
			}
			else
			{
				//yukaridan-asagi
				//nokta koyarak 5er arttýr (kelimeuzunluðu-1 kere)	
				for(int k=0; k<eski_bulmaca.length(); k++)
				{
					if(k!=(eski_bulmaca.length()-1))
					{
						yeni_indis+=(ilk_indis+(k*5))+".";
					}
					else
					{
						yeni_indis+=((ilk_indis+(k*5))+"");
						yeni_indis_dizi[i]=yeni_indis;
					}
				}
			}
		
		}
		}
		return yeni_indis_dizi;
	}
	
	public static void randomDegerDondur()
	{
		Random rand = new Random();
		ortak.random_deger_bulmaca = rand.nextInt(10);
		
	}
	
	
	public static void olustur()
	{
		ortak.randomDegerDondur();
		
		 switch(random_deger_bulmaca)
		 {
		 case 0: new bulmaca(); break;
		 case 1: new bulmaca2(); break;
		 case 2: new bulmaca3(); break;
		 case 3: new bulmaca4(); break;
		 case 4: new bulmaca5(); break;
		 case 5: new bulmaca6(); break;
		 case 6: new bulmaca7(); break;
		 case 7: new bulmaca8(); break;
		 case 8: new bulmaca9(); break;
		 case 9: new bulmaca10(); break;
		 }
		 
	}
	
	public static void main(String[] args)
	{		
		ortak.randomDegerDondur();
		
		 switch(random_deger_bulmaca)
		 {
		 case 0: new bulmaca(); break;
		 case 1: new bulmaca2(); break;
		 case 2: new bulmaca3(); break;
		 case 3: new bulmaca4(); break;
		 case 4: new bulmaca5(); break;
		 case 5: new bulmaca6(); break;
		 case 6: new bulmaca7(); break;
		 case 7: new bulmaca8(); break;
		 case 8: new bulmaca9(); break;
		 case 9: new bulmaca10(); break;
		 }
		 
	}
}