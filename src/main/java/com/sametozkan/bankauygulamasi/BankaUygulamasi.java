package com.sametozkan.bankauygulamasi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BankaUygulamasi {
	
	//Banka personellerini tutan ArrayList.
	public static ArrayList<BankaPersoneli> bankaPersoneli = new ArrayList<>();
	
	//Personellerin personel ID'lerinin bulunduğu liste.
	public static JList personelListesi = new JList(personelListesiModeli());
	
	//Müşterilerin müşteri numaralarının bulunduğu liste.
	public static JList musteriListesi = new JList(musteriListesiModeli());
	
	/* Müşterilerin müşteri numaralarını çeken metot. Yeni bir müşteri eklendiğinde musteriListesi'nin 
	 * güncellenmesi için bu metodun döndüğü listModel, musteriListesi isimli JList'e set edilecek. */
	public static DefaultListModel musteriListesiModeli() {
		DefaultListModel listModel = new DefaultListModel();
		for(BankaPersoneli personel : bankaPersoneli) {
			for(Musteri musteri : personel.getMusteriler()) {
				listModel.addElement(musteri.getMusteriNumarasi());
			}
		}
		return listModel;
	}

	/*Banka personellerinin personel ID'lerini çeken metot. Yeni bir personel eklendiğinde personelListesi'nin
	 *güncellenmesi için bu metodun döndüğü listModel, personelListesi isimli JList'e set edilecek. */
	public static DefaultListModel personelListesiModeli() {
		DefaultListModel listModel = new DefaultListModel();
		for (BankaPersoneli personel : bankaPersoneli) {
			listModel.addElement(personel.getPersonelID());
		}
		return listModel;
	}
        
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Banka Uygulaması"); //Uygulama çerçevesi.  
		JPanel panel = new JPanel(new BorderLayout(15,15)); //Ana panel.
		JPanel bankaIslemPaneli = new JPanel(new FlowLayout()); //Banka işlem butonlarının bulunduğu panel.
		JPanel musteriIslemPaneli = new JPanel(new GridLayout(11,1,10,10)); //Müşteri işlem butonlarını içeren panel.
		JPanel listeBilgiPaneli = new JPanel (new GridLayout(2,1,10,10)); //listePaneli ile bilgiPaneli'ni birleştiren panel.
		JPanel listePaneli = new JPanel(new GridLayout(2,2,10,10)); //Müşteri numaraları ve personel ID listelerini içeren panel.
		JPanel bilgiPaneli = new JPanel(new GridLayout(1,2,10,10)); //Müşteri ve personel bilgilerini içeren panel.
		
		//Banka işlem butonları:
		JButton musteriEkle = new JButton("Müşteri Ekle"); 
		JButton personelEkle = new JButton("Personel Ekle");
		
		//Müşteri işlem butonları:
		JButton hesapEkle = new JButton("Hesap Ekle"); 
		JButton hesapSil = new JButton("Hesap Sil"); 
		JButton krediKartiEkle = new JButton("Kredi Kartı Ekle");
		JButton krediKartiSil = new JButton("Kredi Kartı Sil");
		JButton paraTransferi = new JButton("Para Transferi");
		JButton krediKartiBorcOde = new JButton("Kredi Kartı Borç Öde");
		JButton paraCek = new JButton("Para Çek");
		JButton paraEkle = new JButton("Para Ekle");

		/* Uygulamanın açıldığı ekranın boyutunu öğrenmek için oluşturuldu. getHeight() ve getWidth() metotları
		 * ile yükseklik ve genişlik bilgileri alınacak.*/
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); 

		//Müşteri ve personel bilgilerini gösteren metin alanları.
		JTextArea musteriBilgileri = new JTextArea("Müşteri bilgilerini görmek ve işlem"
		+ "\nyapmak için listeden seçim\nyapmalısınız.");
		JTextArea personelBilgileri = new JTextArea("Personel bilgilerini görmek için"
		+ "\nlisteden seçim yapmalısınız.");
		
		//Listelere ve metin alanlarına scroll eklenir.
		JScrollPane musteriListesiPane = new JScrollPane(musteriListesi);
		JScrollPane personelListesiPane = new JScrollPane(personelListesi);
		JScrollPane musteriBilgileriPane = new JScrollPane(musteriBilgileri);
		JScrollPane personelBilgileriPane = new JScrollPane(personelBilgileri);
		
		//musteriIslemPaneli'ne eklenen etiketler:
		JLabel musteriIslemleri = new JLabel("Müşteri işlemleri:" , SwingConstants.CENTER);
		JLabel vadesizHesapIslemleri = new JLabel("Vadesiz hesap işlemleri:", SwingConstants.CENTER);
		JLabel yatirimHesabiIslemleri = new JLabel("Yatırım hesabı işlemleri:", SwingConstants.CENTER);
		
		//listePaneli'ne eklenen etiketler:
		JLabel musteriSec = new JLabel("Müşteri seç:", SwingConstants.CENTER);
		JLabel personelSec = new JLabel("Personel seç: ", SwingConstants.CENTER);
		
		//Oluşturulan componentler ilgili panellere eklenir.
		//Componentleri ayrı panellere ekleyip ana panelde birleştirmemin sebebi birden fazla layout kullanmak.
		bankaIslemPaneli.add(musteriEkle);
		bankaIslemPaneli.add(personelEkle);
		musteriIslemPaneli.add(musteriIslemleri);
		musteriIslemPaneli.add(hesapEkle);
		musteriIslemPaneli.add(hesapSil);
		musteriIslemPaneli.add(krediKartiEkle);
		musteriIslemPaneli.add(krediKartiSil);
		musteriIslemPaneli.add(vadesizHesapIslemleri);
		musteriIslemPaneli.add(krediKartiBorcOde);
		musteriIslemPaneli.add(paraTransferi);
		musteriIslemPaneli.add(yatirimHesabiIslemleri);
		musteriIslemPaneli.add(paraCek);
		musteriIslemPaneli.add(paraEkle);
		listePaneli.add(musteriSec);
		listePaneli.add(personelSec);
		listePaneli.add(musteriListesiPane);
		listePaneli.add(personelListesiPane);
		bilgiPaneli.add(musteriBilgileriPane);
		bilgiPaneli.add(personelBilgileriPane);
		listeBilgiPaneli.add(listePaneli);
		listeBilgiPaneli.add(bilgiPaneli);
		
		//Oluşturulan paneller, ana panele eklenir.
		panel.add(bankaIslemPaneli, BorderLayout.NORTH);
		panel.add(musteriIslemPaneli, BorderLayout.WEST);
		panel.add(listeBilgiPaneli, BorderLayout.CENTER);
		panel.add(new JLabel("Samet Özkan 20360859033", SwingConstants.CENTER), BorderLayout.SOUTH);
		
		/* Müşteri numaralarının bulunduğu listeden seçim yapıldığında müşterinin bilgilerini
		 * metin alanına set eden listener. */
		musteriListesi.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					//Listeden seçilen müşterinin numarası alınır.
					//Bazen NullPointerException hatası verdiği için try - catch kullanıldı.
					int musteriNumarasi = 0;
					try {
						musteriNumarasi = (int) musteriListesi.getSelectedValue();
					}
					catch(Exception e) {
						;
					}
					/* Tüm personellerin müşterileri taranır ve seçilen müşteri numarası ile 
					 * eşleşen müşterinin bilgileri toString metoduyla alınarak metin alanına set edilir. */ 
					for(BankaPersoneli personel : BankaUygulamasi.bankaPersoneli){
						for(Musteri musteri : personel.getMusteriler()){
							if(musteriNumarasi == musteri.getMusteriNumarasi()){
								String string = musteri.toString();
								musteriBilgileri.setText(string);
								
							}
						}
					}        
				}
			}
		});
		
		/* Personel ID'lerinin bulunduğu listeden seçim yapıldığında personelin bilgilerini
		 * metin alanına set eden listener. */
		personelListesi.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					//Listeden seçilen personelin ID'si alınır.
					//Bazen NullPointerException verdiği için try - catch kullanıldı.
					int personelID = 0;
					try {
						personelID = (int) personelListesi.getSelectedValue();
					}
					catch(Exception e) {
						;
					}
					
					/* Tüm personeller taranır ve seçilen ID ile eşleşen personelin
					 * bilgileri toString metodu ile alınır ve metin alanına set edilir.*/ 
					for(BankaPersoneli personel : BankaUygulamasi.bankaPersoneli){
						if(personelID == personel.getPersonelID()){
							String string = personel.toString();
							personelBilgileri.setText(string);                           
						}
					}
				}
			}
		});

		//"Müşteri Ekle" butonuna basıldığında müşteri ekleme ekranını açan listener.
		musteriEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new JFrame("Müşteri Ekle"); //Uygulama çerçevesi.
				JPanel panel = new JPanel(new GridLayout(6,2,10,10)); // 6 Satırlı 2 sütunlu, GridLayout panel.
				
				//Etiketler:
				JLabel ad = new JLabel("Ad:");
				JLabel soyad = new JLabel("Soyad:");
				JLabel email = new JLabel("Email:");
				JLabel telefon = new JLabel("Telefon:");
				JLabel personel = new JLabel("Personel:");
				
				//Input almak için oluşturulan TextPane'ler:
				JTextPane adPane = new JTextPane();
				JTextPane soyadPane = new JTextPane();
				JTextPane emailPane = new JTextPane();
				JTextPane telefonPane = new JTextPane();
				
				/* ComboBox'a eklenmek için oluşturulan ComboBox modeli. Banka personellerinin tutulduğu
				 * ArrayList'teki her personelin ID, ad ve soyad bilgileri modele eklenir. */
				DefaultComboBoxModel comboBoxModeli = new DefaultComboBoxModel();
				for(BankaPersoneli bp : BankaUygulamasi.bankaPersoneli) {
					comboBoxModeli.addElement(bp.getPersonelID() + " " + bp.getAd()
					+ " " + bp.getSoyad());
				}
				
				//Personel bilgilerini içeren ComboBox.
				JComboBox comboBox = new JComboBox(comboBoxModeli);
				
				JButton ekle = new JButton("Ekle");
				
				/* "Ekle" butonuna basıldığında textPane'lerden alınan inputları kullanarak, comboBox'tan 
				 * seçilen personelin musteriler ArrayList'ine yeni bir müşteri ekleyen listener. */
				ekle.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
						/*ComboBox'ta seçilen personelin ID, ad ve soyad bilgileri 
						 * split ile kesilerek diziye aktarılır. */
						String[] personel = comboBox.getSelectedItem().toString().split(" ");
						/* Tüm banka personelleri taranır ve personel[0] ile yani ID bilgisi ile eşleşen personelin
						 * musteriler ArrayList'ine yeni bir müşteri eklenir. */
						for(BankaPersoneli bp : BankaUygulamasi.bankaPersoneli) {
							if (Integer.parseInt(personel[0]) == bp.getPersonelID()) {
								try {
								bp.getMusteriler().add(new Musteri(adPane.getText(),
        						soyadPane.getText(), emailPane.getText(),
        						Long.parseLong(telefonPane.getText())));
								}
								catch(Exception e) {
								if (e instanceof NumberFormatException) {
									JOptionPane.showMessageDialog(frame, "Geçersiz telefon numarası.");
								}	
								}
							}
						}
						//Müşteri listesi güncellenir.
						musteriListesi.setModel(musteriListesiModeli());
						}
						catch (Exception e) {
							JOptionPane.showMessageDialog(frame, "Geçersiz işlem.");
						}
						}
        	
				});

				
				panel.add(ad);
				panel.add(adPane);
				panel.add(soyad);
				panel.add(soyadPane);
				panel.add(email);
				panel.add(emailPane);
				panel.add(telefon);
				panel.add(telefonPane);
				panel.add(personel);
				panel.add(comboBox);
				panel.add(ekle);
				frame.add(panel);
				frame.setSize((int)size.getWidth()/5, (int)size.getHeight()/4);
				frame.setLocation((int) size.getWidth()*4/10, (int) size.getHeight()*3/8);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		
		//"Personel Ekle" butonuna basıldığında personel ekleme ekranını açan listener.
		personelEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new JFrame("Personel Ekle"); //Uygulama çerçevesi.
				JPanel panel = new JPanel(new GridLayout(5,2,10,10)); //5 Satırlı 2 sütunlu, GridLayout panel.
				
				//Etiketler:
				JLabel ad = new JLabel("Ad:");
				JLabel soyad = new JLabel("Soyad:");
				JLabel email = new JLabel("Email:");
				JLabel telefon = new JLabel("Telefon:");
				
				//Input almak için oluşturulan TextPane'ler:
				JTextPane adPane = new JTextPane();
				JTextPane soyadPane = new JTextPane();
				JTextPane emailPane = new JTextPane();
				JTextPane telefonPane = new JTextPane();
				
				JButton ekle = new JButton("Ekle");
        
				/* "Ekle" butonuna basıldığında TextPane'lerdeki inputları kullanarak bankaPersoneli ArrayList'ine
				 * yeni bir personel ekleyen listener. */
				ekle.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//bankaPersoneli ArrayList'ine yeni personel eklenir.
						try{bankaPersoneli.add(new BankaPersoneli(adPane.getText(),
											soyadPane.getText(), emailPane.getText(),
											Long.parseLong(telefonPane.getText())));
						}
						catch(Exception e) {
							JOptionPane.showMessageDialog(frame, "Geçersiz telefon numarası.");
						}
						//Personel listesi güncellenir.
						personelListesi.setModel(personelListesiModeli());
					}
        	
				}); 
        
				panel.add(ad);
				panel.add(adPane);
				panel.add(soyad);
				panel.add(soyadPane);
				panel.add(email);
				panel.add(emailPane);
				panel.add(telefon);
				panel.add(telefonPane);
				panel.add(ekle);
				frame.add(panel);
				frame.setSize((int)size.getWidth()/5, (int)size.getHeight()/4);
				frame.setLocation((int) size.getWidth()*4/10, (int) size.getHeight()*3/8);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		
		//"Hesap Ekle" butonuna basıldığında hesap ekleme ekranını açan listener.
		hesapEkle.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(musteriListesi.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frame, "Lütfen listeden müşteri seçip tekrar deneyin.");
				}
				else {
				JFrame frame = new JFrame("Hesap Ekle"); //Uygulama çerçevesi.
				JPanel panel = new JPanel(new GridLayout(3,2,10,10)); //3 Satırlı 2 sütunlu, GridLayout panel.
				
				//Etiketler:
				JLabel bakiye = new JLabel("Bakiye:");
				JLabel hesapTuru = new JLabel("Hesap Türü:");
				
				//Bakiye bilgisini alan TextPane.
				JTextPane bakiyePane = new JTextPane();
				
				//Yeni hesabın türünün seçileceği ComboBox.
				//Model olarak "Vadesiz" ve "Yatırım" stringlerini içeren 2 elemanlı dizi verildi.
				DefaultComboBoxModel comboBoxModeli = new DefaultComboBoxModel(new String[] {"Vadesiz", "Yatırım"});
				JComboBox comboBox = new JComboBox(comboBoxModeli);
				
				JButton ekle = new JButton("Ekle");
				
				/* "Ekle" butonuna basıldığında banka personellerinin tüm müşterilerini tarayan ve listede
				 * seçili olan müşteri numarası ile eşleşen müşterinin hesapEkle() metodunu çağıran listener. */
				ekle.addActionListener(new ActionListener() {
		
					public void actionPerformed(ActionEvent arg0) {
						for(BankaPersoneli personel : bankaPersoneli) {
							for(Musteri musteri : personel.getMusteriler()) {
								if (musteri.getMusteriNumarasi() == (int) musteriListesi.getSelectedValue()) {
									//ComboBox ile seçilen hesap türü ve girilen bakiye bilgisi argüman olarak verilir.
									try {
										//Bakiye negatif olamaz.
										if(Double.parseDouble(bakiyePane.getText()) >= 0) {
										musteri.hesapEkle(comboBox.getSelectedItem().toString(), 
													Double.parseDouble(bakiyePane.getText()),
													frame);
										}
										else {
											JOptionPane.showMessageDialog(frame, "Bakiye negatif olamaz.");
										}
										//Müşteri bilgilerinin gösterildiği metin alanı güncellenir.
										musteriBilgileri.setText(musteri.toString());
									}
									catch (Exception e) {
										JOptionPane.showMessageDialog(frame, "Lütfen bakiye giriniz.");
									}
									
								}	
							}
						}
					}
				});
	
    
				panel.add(bakiye);
				panel.add(bakiyePane);
				panel.add(hesapTuru);
				panel.add(comboBox);
				panel.add(ekle);
				frame.add(panel);
				frame.setSize((int)size.getWidth()/5, (int)size.getHeight()/7);
				frame.setLocation((int) size.getWidth()*4/10, (int) size.getHeight()*6/14);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
			}
			}
		});

		//"Hesap Sil" butonuna basıldığında hesap silme ekranını açan listener.
		hesapSil.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent arg0) {
				if(musteriListesi.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frame, "Lütfen listeden müşteri seçip tekrar deneyin.");
				}
				else {
				JFrame frame = new JFrame("Hesap Sil"); //Uygulama çerçevesi.
				JPanel panel = new JPanel(new GridLayout(2,2,10,10)); //2 Satırlı 2 sütunlu, GridLayout panel.
				
				//Listede seçili müşterinin banka hesapları comboBoxModeli'ne eklenir ve comboBox'a set edilir.
				JLabel hesapSec = new JLabel("Hesap Seç:");
				DefaultComboBoxModel comboBoxModeli = new DefaultComboBoxModel();
				JComboBox comboBox = new JComboBox();
				
				for(BankaPersoneli personel : BankaUygulamasi.bankaPersoneli) {
					for(Musteri musteri : personel.getMusteriler()) {
						if (musteri.getMusteriNumarasi() == (int) musteriListesi.getSelectedValue()) {
							for(BankaHesabi hesap : musteri.getHesaplar()) {
								comboBoxModeli.addElement(hesap.getIban());
							}
							comboBox.setModel(comboBoxModeli);
						}	
					}	
				}
				
				JButton sil = new JButton("Sil"); //Sil butonu.
					
				/* "Sil" butonuna basıldığında tüm personellerin müşterilerini tarayan ve listede seçili
				 * müşteri numarası ile eşleşen müşterinin hesapSil() metodunu çağıran listener.*/
				sil.addActionListener(new ActionListener() {
		
					public void actionPerformed(ActionEvent arg0) {
						for(BankaPersoneli personel : BankaUygulamasi.bankaPersoneli) {
							for(Musteri musteri : personel.getMusteriler()) {
								if (musteri.getMusteriNumarasi() == (int) musteriListesi.getSelectedValue()) {
									try{
									//ComboBox'da seçilen IBAN alınır.
									int iban = (int)comboBox.getSelectedItem();
									//iban ve frame hesapSil() metoduna argüman olarak verilir.
									musteri.hesapSil(iban, frame);
									//Müşteri bilgilerinin gösterildiği metin alanı güncellenir.
									musteriBilgileri.setText(musteri.toString());
									}
									catch (Exception e) {
										JOptionPane.showMessageDialog(frame, "Hesap bulunamadı.");
									}
								}
							}
						}
					}
				});	
	
				panel.add(hesapSec);
				panel.add(comboBox);
				panel.add(sil);
				frame.add(panel);
				frame.setSize((int)size.getWidth()/5, (int)size.getHeight()/9);
				frame.setLocation((int) size.getWidth()*4/10, (int) size.getHeight()*8/18);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
				}
			}
		});
		
		//"Kredi Kartı Ekle" butonuna basıldığında kredi kartı ekleme ekranını açan listener.
		krediKartiEkle.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if(musteriListesi.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frame, "Lütfen listeden müşteri seçip tekrar deneyin.");
				}
				else {
				JFrame frame = new JFrame("Kredi Kartı Ekle"); //Uygulama çerçevesi.
				JPanel panel = new JPanel(new GridLayout(3,2,10,10)); //3 Satırlı 2 sütunlu, GridLayout panel.
				JLabel limit = new JLabel("Limit:"); //Limit etiketi.
				JLabel guncelBorc = new JLabel("Güncel Borç:"); //Güncel borç etiketi.
				JTextPane limitPane = new JTextPane(); //Kredi kartının limiti için text bölümü.
				JTextPane guncelBorcPane = new JTextPane(); //Kredi kartının güncel borcu için text bölümü.
				JButton ekle = new JButton("Ekle"); //Ekle butonu.
				
				
				/* "Ekle" butonuna basıldığında müşteri listesinde seçili olan müşteri numarası ile eşleşen
				 * müşterinin krediKartiEkle() metodunu çağıran listener.*/
				ekle.addActionListener(new ActionListener() {
	
					public void actionPerformed(ActionEvent arg0) {
						for(BankaPersoneli personel : BankaUygulamasi.bankaPersoneli) {
							for(Musteri musteri : personel.getMusteriler()) {
								if (musteri.getMusteriNumarasi() == (int) musteriListesi.getSelectedValue()) {
									try{
									//Girilen limit değeri alınır.
									double limit = Double.parseDouble(limitPane.getText());
									//Girilen güncel borç değeri alınır.
									double guncelBorc = Double.parseDouble(guncelBorcPane.getText());
									//krediKartiEkle() metoduna limit ve guncelBorc argüman olarak verilir.
									musteri.krediKartiEkle(limit, guncelBorc);
									//Müşteri bilgilerinin gösterildiği alan güncellenir.
									musteriBilgileri.setText(musteri.toString());
									}
									catch (Exception e) {
										JOptionPane.showMessageDialog(frame, "Lütfen tüm alanları doldurunuz.");
									}
								}	
							}
						}
					}
				});


				panel.add(limit);
				panel.add(limitPane);
				panel.add(guncelBorc);
				panel.add(guncelBorcPane);
				panel.add(ekle);
				frame.add(panel);
				frame.setSize((int) size.getWidth()/5, (int) size.getHeight()/7);
				frame.setLocation((int) size.getWidth()*4/10, (int) size.getHeight()*6/14);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
				}
				}
		});
		
		//"Kredi Kartı Sil" butonuna basıldığında kredi kartı silme ekranını açan listener.
		krediKartiSil.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if(musteriListesi.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frame, "Lütfen listeden müşteri seçip tekrar deneyin.");
				}
				else {
				JFrame frame = new JFrame("Kredi Kartı Ekle"); //Uygulama çerçevesi.
				JPanel panel = new JPanel(new GridLayout(2,2,10,10)); //2 Satırlı 2 sütunlu, GridLayout panel.
				
				//Listede seçili müşterinin kredi kartları comboBoxModeli'ne eklenir ve comboBox'a set edilir.
				JLabel kartSec = new JLabel("Kart Seç:");
				DefaultComboBoxModel comboBoxModeli = new DefaultComboBoxModel();
				JComboBox comboBox = new JComboBox();
				for(BankaPersoneli personel : bankaPersoneli) {
					for(Musteri musteri : personel.getMusteriler()) {
						if (musteri.getMusteriNumarasi() == (int) musteriListesi.getSelectedValue()) {
							for(KrediKarti krediKarti : musteri.getKrediKartlari()) {
								comboBoxModeli.addElement(krediKarti.getKartNumarasi());
							}
							comboBox.setModel(comboBoxModeli);
						}	
					}	
				}
				
				
				//Sil butonu.
				JButton sil = new JButton("Sil"); 
				
				/* "Sil" butonuna basıldığında tüm personellerin müşterilerini tarayan ve listede seçili
				 * müşteri numarasıyla eşleşen müşterinin krediKartıSil() metodunu çağıran listener.*/
				sil.addActionListener(new ActionListener() {
	
					public void actionPerformed(ActionEvent arg0) {
						for(BankaPersoneli personel : BankaUygulamasi.bankaPersoneli) {
							for(Musteri musteri : personel.getMusteriler()) {
								if (musteri.getMusteriNumarasi() == (int) musteriListesi.getSelectedValue()) {
									try{
										//ComboBox'da seçilen kart numarası bir değişkene atanır.
										int kartNumarasi = (int)comboBox.getSelectedItem();
										//kartNumarasi ve frame argüman olarak verilir.
										musteri.krediKartiSil(kartNumarasi, frame);
										//Müşteri bilgilerinin gösterildiği text alanı güncellenir.
										musteriBilgileri.setText(musteri.toString());
										}
									catch(Exception e) {
										JOptionPane.showMessageDialog(frame, "Kart bulunamadı.");
									}
								}	
							}	
						}
					}
				});

				panel.add(kartSec);
				panel.add(comboBox);
				panel.add(sil);
				frame.add(panel);
				frame.setSize((int)size.getWidth()/5, (int)size.getHeight()/9);
				frame.setLocation((int) size.getWidth()*4/10, (int) size.getHeight()*8/18);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
				}
				}
		});

		//"Kredi Kartı Borç Öde" butonuna basıldığında kredi kartı borç ödeme ekranını açan listener.
		krediKartiBorcOde.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if(musteriListesi.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frame, "Lütfen listeden müşteri seçip tekrar deneyin.");
				}
				else {
				JFrame frame = new JFrame("Kredi Kartı Borç Öde"); //Uygulama çerçevesi.
				JPanel panel = new JPanel(new GridLayout(4,2,10,10)); //4 Satırlı 2 sütunlu, GridLayout panel.
				JLabel vadesizHesap = new JLabel("Vadesiz Hesap:"); //Vadesiz hesap etiketi.
				JLabel krediKarti = new JLabel("Kredi Kartı:"); //Kredi kartı etiketi.
				JLabel miktar = new JLabel("Miktar"); //Miktar etiketi.
				JTextPane miktarPane = new JTextPane(); //Miktar bilgisi için input bölümü.
				int musteriNumarasi = (int) musteriListesi.getSelectedValue();
				
				//Vadesiz hesapları içeren ComboBox modeli ve ComboBox.
				DefaultComboBoxModel vadesizHesapModeli = new DefaultComboBoxModel();
				JComboBox vadesizHesaplar = new JComboBox();
				
				//Kredi kartlarını içeren ComboBox modeli ve ComboBox.
				DefaultComboBoxModel krediKartiModeli = new DefaultComboBoxModel();
				JComboBox krediKartlari = new JComboBox();
				
				//Öde butonu.
				JButton ode = new JButton("Öde");
				
				//Vadesiz hesap ve kredi kartı ComboBox'larının ayarlandığı iç içe for döngüsü.
				for(BankaPersoneli personel : BankaUygulamasi.bankaPersoneli) {
					for(Musteri musteri : personel.getMusteriler()) {
						if(musteri.getMusteriNumarasi() == musteriNumarasi) {
							
							//Müşterinin vadesiz hesapları ComboBoxModel'e eklenir.
							for(BankaHesabi vh : musteri.getHesaplar()) {
								if(vh instanceof VadesizHesap) {
									vadesizHesapModeli.addElement(vh.getIban());
								}
							}
							
							//Müşterinin kredi kartları ComboBoxModel'e eklenir.
							for(KrediKarti kk : musteri.getKrediKartlari()) {
								krediKartiModeli.addElement(kk.getKartNumarasi());
							}
							
							//ComboBoxModel'ler ComboBox'lara set edilir.
							vadesizHesaplar.setModel(vadesizHesapModeli);
							krediKartlari.setModel(krediKartiModeli);
						}
					}
				}
				
				/* "Öde" butonuna basıldığında ComboBox ile seçilen vadesiz hesaptan kredi kartı borcunun
				 * girilen miktar kadarını ödeyen listener.*/
				ode.addActionListener(new ActionListener() {
	
					public void actionPerformed(ActionEvent arg0) {
						for(BankaPersoneli personel : BankaUygulamasi.bankaPersoneli) {
							for(Musteri musteri : personel.getMusteriler()) {
								if (musteri.getMusteriNumarasi() == (int) musteriListesi.getSelectedValue()) {
									try{
										//Seçilen vadesizHesap'ın IBAN bilgisi alınır.
										int iban = (int) vadesizHesaplar.getSelectedItem();
										//Seçilen krediKarti'nin numarası alınır.
										int kartNumarasi = (int) krediKartlari.getSelectedItem();
										//JTextPane'e girilen miktar alınır.
										double miktar = Double.parseDouble(miktarPane.getText());
					
										for(BankaHesabi bankaHesabi : musteri.getHesaplar()) {
											if(bankaHesabi.getIban() == iban) {
												//BankaHesabı, VadesizHesap'a downcast edilir.
												VadesizHesap vadesizHesap = (VadesizHesap) bankaHesabi;
												//krediKartiBorcOdeme() metoduna argümanlar verilir.
												vadesizHesap.krediKartiBorcOdeme(musteri, kartNumarasi, miktar, frame);
												//Müşteri bilgilerinin gösterildiği metin alanı güncellenir.
												musteriBilgileri.setText(musteri.toString());
											}
										}
									}
									catch(Exception e) {
										JOptionPane.showMessageDialog(frame, "Geçersiz işlem.");
									}
								}
					
							}
						}
					}
				});

				panel.add(vadesizHesap);
				panel.add(vadesizHesaplar);
				panel.add(krediKarti);
				panel.add(krediKartlari);
				panel.add(miktar);
				panel.add(miktarPane);
				panel.add(ode);
				frame.add(panel);
				frame.setSize((int) size.getWidth()/5, (int) size.getHeight()/5);
				frame.setLocation((int) size.getWidth()*4/10, (int) size.getHeight()*4/10);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
				}
				}
		});
		
		//"Para Transferi" butonuna basıldığında para transferi ekranını açan listener.
		paraTransferi.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if(musteriListesi.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frame, "Lütfen listeden müşteri seçip tekrar deneyin.");
				}
				else {
				JFrame frame = new JFrame("Para Transferi"); //Uygulama çerçevesi.
				JPanel panel = new JPanel(new GridLayout(4,2,10,10)); //4 Satırlı 2 sütunlu, GridLayout panel.
				
				//Etiketler:
				JLabel hesapSec = new JLabel("Hesap seç:");
				JLabel iban = new JLabel("IBAN");
				JLabel miktar = new JLabel("Miktar:");
				
				//Kullanıcıdan iban ve miktar değerlerinin alınması için TextPane'ler:
				JTextPane ibanPane = new JTextPane();
				JTextPane miktarPane = new JTextPane();
				
				//Vadesiz hesap seçiminin yapıldığı ComboBox ve ComboBox modeli.
				DefaultComboBoxModel vadesizHesapListesi = new DefaultComboBoxModel();
				JComboBox vadesizHesaplar = new JComboBox();
				
				//Listede seçili olan müşteri numarası alınır.
				int musteriNumarasi = (int)musteriListesi.getSelectedValue();
				
				//ComboBox modeline müşterinin vadesiz hesapları eklenir.
				for(BankaPersoneli personel : BankaUygulamasi.bankaPersoneli) {
					for(Musteri musteri : personel.getMusteriler()) {
						if(musteri.getMusteriNumarasi() == musteriNumarasi) {
							for(BankaHesabi hesap : musteri.getHesaplar()) {
								//BankaHesabı, VadesizHesap ise vadesizHesapListesi'ne eklenir.
								if(hesap instanceof VadesizHesap) {
									vadesizHesapListesi.addElement(hesap.getIban());
								}
							}
							//ComboBox modeli, ComboBox'a set edilir.
							vadesizHesaplar.setModel(vadesizHesapListesi);
						}
					}
				}

				JButton gonder = new JButton("Gönder");

				/* "Gönder" butonuna basıldığında VadesizHesap'tan parayı çekip girilen IBAN'a sahip vadesiz
				 * hesaba parayı gönderen listener.*/
				gonder.addActionListener(new ActionListener() {
	
					public void actionPerformed(ActionEvent arg0) {
		
						for(BankaPersoneli personel : BankaUygulamasi.bankaPersoneli) {
							for(Musteri musteri : personel.getMusteriler()){
								for(BankaHesabi hesap : musteri.getHesaplar()) {
									if(hesap.getIban() == (int) vadesizHesaplar.getSelectedItem()) {
										try{
											//paraTransferi() metoduna erişmek için BankaHesabı downcast edilir.
											VadesizHesap vadesizHesap = (VadesizHesap) hesap;
											//Girilen iban alınır.
											int iban = Integer.parseInt(ibanPane.getText());
											//Girilen miktar alınır.
											double miktar = Double.parseDouble(miktarPane.getText());
											//paraTransferi() metoduna argümanlar verilir.
											vadesizHesap.paraTransferi(iban, miktar, frame);
											//Müşteri bilgileri güncellenir.
											musteriBilgileri.setText(musteri.toString());
											}
										catch(Exception e) {
											JOptionPane.showMessageDialog(frame, "Geçersiz işlem.");
										}
									}
								}
							}}
					}
				});

				panel.add(hesapSec);
				panel.add(vadesizHesaplar);
				panel.add(iban);
				panel.add(ibanPane);
				panel.add(miktar);
				panel.add(miktarPane);
				panel.add(gonder);
				frame.add(panel);
				frame.setSize((int) size.getWidth()/5, (int) size.getHeight()/5);
				frame.setLocation((int) size.getWidth()*4/10, (int) size.getHeight()*4/10);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
				}
				}
		});
		
		//"Para Çek" butonuna basıldığında yatırım hesabından para çekme ekranını açan listener.
		paraCek.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if(musteriListesi.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frame, "Lütfen listeden müşteri seçip tekrar deneyin.");
				}
				else {
				JFrame frame = new JFrame("Para Ekle"); //Uygulama çerçevesi.
				JPanel panel = new JPanel(new GridLayout(4,2,10,10)); //4 Satırlı 2 sütunlu, GridLayout panel.
				
				//Etiketler:
				JLabel yatirimHesabiSec = new JLabel("Yatırım Hesabından Çek:");
				JLabel vadesizHesapSec = new JLabel("Vadesiz Hesaba Ekle:");
				JLabel miktar = new JLabel("Miktar:");
				
				//Vadesiz ve yatırım hesapları için ComboBox'lar:
				DefaultComboBoxModel vadesizHesapListesi = new DefaultComboBoxModel();
				JComboBox vadesizHesaplar = new JComboBox();
				DefaultComboBoxModel yatirimHesabiListesi = new DefaultComboBoxModel();
				JComboBox yatirimHesaplari = new JComboBox();
				
				//Listede seçili olan müşteri numarası alınır.
				int musteriNumarasi = (int) musteriListesi.getSelectedValue();
				
				//Miktarın girileceği metin bölümü.
				JTextPane miktarPane = new JTextPane();
				
				//Müşterinin vadesiz ve yatırım hesapları ComboBox'lara eklenir.
				for(BankaPersoneli personel : BankaUygulamasi.bankaPersoneli) {
					for(Musteri musteri : personel.getMusteriler()) {
						if(musteri.getMusteriNumarasi() == musteriNumarasi) {
							try {
							for(BankaHesabi hesap : musteri.getHesaplar()) {
								//BankaHesabi, YatirimHesabi ise yatırım hesabı ComboBox modeline eklenir.
								if(hesap instanceof YatirimHesabi) {
									yatirimHesabiListesi.addElement(hesap.getIban());
								}
								//BankaHesabi, VadesizHesap ise vadesiz hesap ComboBox modeline eklenir.
								if(hesap instanceof VadesizHesap) {
									vadesizHesapListesi.addElement(hesap.getIban());
								}
							}
							
							//ComboBox modelleri, ComboBox'lara set edilir.
							yatirimHesaplari.setModel(yatirimHesabiListesi);
							vadesizHesaplar.setModel(vadesizHesapListesi);
							}
							catch(Exception e) {
								JOptionPane.showMessageDialog(frame, "Geçersiz işlem.");
							}
						}
					}
				}

				JButton paraCek = new JButton("Para Çek");
				
				/* "Para Çek" butonuna basıldığında seçilen yatırım hesabından seçilen vadesiz
				 * hesaba para gönderen listener.*/
				paraCek.addActionListener(new ActionListener() {
	
					public void actionPerformed(ActionEvent arg0) {
		
						for(BankaPersoneli personel : BankaUygulamasi.bankaPersoneli) {
							for(Musteri musteri : personel.getMusteriler()){
								for(BankaHesabi hesap : musteri.getHesaplar()) {
									try {
									if(hesap.getIban() == (int) yatirimHesaplari.getSelectedItem()) {
										/* paraCek() metoduna erişmek için BankaHesabı, 
										 * YatirimHesabi'na downcast edilir.*/
										YatirimHesabi yatirimHesabi = (YatirimHesabi) hesap;
										//paraCek() metoduna argümanlar verilir.
										yatirimHesabi.paraCek(musteri, (int) vadesizHesaplar.getSelectedItem(), 
															Double.parseDouble(miktarPane.getText()), frame);
										//Müşteri bilgileri güncellenir.
										musteriBilgileri.setText(musteri.toString());
									}}
									catch (Exception e) {
										JOptionPane.showMessageDialog(frame, "Geçersiz işlem. Lütfen tüm alanları doldurun.");
									}
								}
							}}
					}
				});

				panel.add(yatirimHesabiSec);
				panel.add(yatirimHesaplari);
				panel.add(vadesizHesapSec);
				panel.add(vadesizHesaplar);
				panel.add(miktar);
				panel.add(miktarPane);
				panel.add(paraCek);
				frame.add(panel);
				frame.setSize((int) size.getWidth()/3, (int) size.getHeight()/5);
				frame.setLocation((int) size.getWidth()*2/6, (int) size.getHeight()*4/10);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
				}
				}
		});	
		
		//"Para Ekle" butonuna basıldığında yatırım hesabına para ekleme ekranını açan listener.
		paraEkle.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if(musteriListesi.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(frame, "Lütfen listeden müşteri seçip tekrar deneyin.");
				}
				else {
				JFrame frame = new JFrame("Para Ekle"); //Uygulama çerçevesi.
				JPanel panel = new JPanel(new GridLayout(4,2,10,10)); //4 Satırlı 2 sütunlu, GridLayout panel.
				
				//Etiketler:
				JLabel yatirimHesabiSec = new JLabel("Yatırım Hesabına Ekle:");
				JLabel vadesizHesapSec = new JLabel("Vadesiz Hesaptan Çek:");
				JLabel miktar = new JLabel("Miktar:");
				
				//Miktarın girileceği metin bölümü.
				JTextPane miktarPane = new JTextPane();
				
				//Vadesiz ve yatırım hesapları için ComboBox'lar:
				DefaultComboBoxModel vadesizHesapListesi = new DefaultComboBoxModel();
				JComboBox vadesizHesaplar = new JComboBox();
				DefaultComboBoxModel yatirimHesabiListesi = new DefaultComboBoxModel();
				JComboBox yatirimHesaplari = new JComboBox();
				
				//Listede seçili olan müşterinin numarası alınır.
				int musteriNumarasi = (int)musteriListesi.getSelectedValue();

				//Müşterinin vadesiz ve yatırım hesapları ComboBox'lara eklenir.
				for(BankaPersoneli personel : BankaUygulamasi.bankaPersoneli) {
					for(Musteri musteri : personel.getMusteriler()) {
						if(musteri.getMusteriNumarasi() == musteriNumarasi) {
							try {
							for(BankaHesabi hesap : musteri.getHesaplar()) {
								//BankaHesabi, YatirimHesabi ise yatırım hesabı ComboBox modeline eklenir.
								if(hesap instanceof YatirimHesabi) {
									yatirimHesabiListesi.addElement(hesap.getIban());
								}
								//BankaHesabi, VadesizHesap ise vadesiz hesap ComboBox modeline eklenir.
								if(hesap instanceof VadesizHesap) {
									vadesizHesapListesi.addElement(hesap.getIban());
								}
							}
							
							//ComboBox modelleri, ComboBox'lara set edilir.
							yatirimHesaplari.setModel(yatirimHesabiListesi);
							vadesizHesaplar.setModel(vadesizHesapListesi);
							}
							catch(Exception e) {
								JOptionPane.showMessageDialog(frame, "Geçersiz işlem.");
							}	
						}
					}
				}
				
				JButton paraEkle = new JButton("Para Ekle");
				
				/* "Para Ekle" butonuna basıldığında seçilen yatırım hesabından seçilen vadesiz hesaba
				 * girilen miktar kadar para çeken listener.*/
				paraEkle.addActionListener(new ActionListener() {
	
					public void actionPerformed(ActionEvent arg0) {
		
						for(BankaPersoneli personel : BankaUygulamasi.bankaPersoneli) {
							for(Musteri musteri : personel.getMusteriler()){
								for(BankaHesabi hesap : musteri.getHesaplar()) {
									try {
									if(hesap.getIban() == (int) yatirimHesaplari.getSelectedItem()) {
										//BankaHesabi, YatirimHesabi'na downcast edilir.
										YatirimHesabi yatirimHesabi = (YatirimHesabi) hesap;
										//paraEkle() metoduna argümanlar verilir.
										yatirimHesabi.paraEkle(musteri, (int) vadesizHesaplar.getSelectedItem(), 
															Double.parseDouble(miktarPane.getText()), frame);
										//Müşteri bilgileri güncellenir.
										musteriBilgileri.setText(musteri.toString());			
										}
									}
									catch (Exception e) {
										JOptionPane.showMessageDialog(frame, "Geçersiz işlem. Lütfen tüm alanları doldurun.");
									}
								}		
							}}
					}
				});

				panel.add(vadesizHesapSec);
				panel.add(vadesizHesaplar);
				panel.add(yatirimHesabiSec);
				panel.add(yatirimHesaplari);
				panel.add(miktar);
				panel.add(miktarPane);
				panel.add(paraEkle);
				frame.add(panel);
				frame.setSize((int) size.getWidth()/3, (int) size.getHeight()/5);
				frame.setLocation((int) size.getWidth()*2/6, (int) size.getHeight()*4/10);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
				}
				}
		});
		
		//Örnek banka personeli ve müşteri.
		BankaPersoneli ornekPersonel = new BankaPersoneli("Örnek", "Personel", "ornekpersonel@yahoo.com", 1112223333);
		bankaPersoneli.add(ornekPersonel);
		Musteri ornekMusteri = new Musteri("Örnek", "Müşteri", "ornekmusteri@outlook.com", 1231233233);
		ornekPersonel.getMusteriler().add(ornekMusteri);
		ornekMusteri.hesapEkle("Vadesiz", 3500, frame);
		ornekMusteri.hesapEkle("Yatırım", 275.50, frame);
		ornekMusteri.krediKartiEkle(1000, 100);
		ornekMusteri.krediKartiEkle(500, 172.10);
		musteriListesi.setModel(musteriListesiModeli());
		personelListesi.setModel(personelListesiModeli());
		
	
		frame.add(panel);
		frame.setSize((int) size.getWidth()/2, (int) size.getHeight()/2);
		frame.setLocation((int) size.getWidth()/4, (int) size.getHeight()/4);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	}
