package com.sametozkan.bankauygulamasi;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


class VadesizHesap extends BankaHesabi {

	private String hesapTuru = "Vadesiz"; //Banka hesabının türü.
	
	//Constructor
	public VadesizHesap(double bakiye) {
		super(bakiye); //BankaHesabi sınıfının constructor'ı çağrılır.
	}
	
	//Para transferi metodu.
	public void paraTransferi(int aliciIban, double miktar, JFrame frame) {
		//aliciIban parametresine verilen argüman taranır.
		for(BankaPersoneli personel : BankaUygulamasi.bankaPersoneli) {
			for(Musteri musteri : personel.getMusteriler()) {
				for(BankaHesabi hesap : musteri.getHesaplar()) {
					//aliciIban argümanı ile eşleşen hesapta işlemler yapılır.
					if(hesap.getIban() == aliciIban){
						if(super.getBakiye() >= miktar) {
						//Alıcı hesabın bakiyesine miktar eklenir.
						hesap.setBakiye(hesap.getBakiye() + miktar);
						//Vadesiz hesabın bakiyesinden miktar düşer.
						super.setBakiye(super.getBakiye() - miktar);
						}
						//Gönderilmek istenen miktar, bakiyeden büyük ise uyarı verilir.
						else {
							JOptionPane.showMessageDialog(frame, "Yetersiz bakiye.");
						}
					}
					//Geçersiz IBAN girilmişse uyarı mesajı verilir.
					else {
						JOptionPane.showMessageDialog(frame, "Lütfen geçerli bir vadesiz hesap IBAN giriniz.");
					}
				}
			}
		}
	}

	//Kredi kartı borç ödeme metodu.
	public void krediKartiBorcOdeme(Musteri musteri, int kartNumarasi, double miktar, JFrame frame) {
		//Müşterinin kredi kartları taranır.
		for(KrediKarti krediKarti : musteri.getKrediKartlari()) {
        	//Argüman olarak verilen kartNumarasi ile eşleşen kredi kartında işlem yapılır.
			if(krediKarti.getKartNumarasi() == kartNumarasi){
        		//Miktar, bakiyeden büyük olamaz.
				if(super.getBakiye() >= miktar) {
				//Kredi kartının güncel borcu miktar kadar azaltılır.
				krediKarti.setGuncelBorc(krediKarti.getGuncelBorc() - miktar);
        		//Vadesiz hesabın bakiyesi miktar kadar azaltılır.
				super.setBakiye(super.getBakiye() - miktar);
				//Kredi kartının kullanılabilir limiti miktar kadar artar.
				krediKarti.setKullanilabilirLimit(krediKarti.getKullanilabilirLimit() + miktar);
        		}
        		else {
        			JOptionPane.showMessageDialog(frame, "Yetersiz bakiye.");
        		}
        	}
        }
    }
	
	//hesapTuru değişkeni için getter metodu.
		public String getHesapTuru() {
			return hesapTuru;
		}
		
		//hesapTuru değişkeni için setter metodu.
		public void setHesapTuru(String hesapTuru) {
			this.hesapTuru = hesapTuru;
		}

	//Vadesiz hesap bilgilerini bir araya getiren toString metodu.
	@Override
	public String toString() {
		return super.toString() + "Hesap Türü: " + hesapTuru + System.lineSeparator();
	}
	}
