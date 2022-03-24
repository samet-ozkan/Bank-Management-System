package com.sametozkan.bankauygulamasi;

import com.sametozkan.bankauygulamasi.BankaHesabi;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

class Musteri extends Kisi {
	private int musteriNumarasi; //Müşteri numarası.
	private ArrayList<BankaHesabi> hesaplar = new ArrayList<>(); //Müşterinin banka hesaplarının tutulduğu ArrayList.
	private ArrayList<KrediKarti> krediKartlari = new ArrayList<>(); //Müşterinin kredi kartlarının tutulduğu ArrayList.
	
	//Constructor
	public Musteri(String ad, String soyad, String email, long telefonNumarasi) {
		super(ad, soyad, email, telefonNumarasi); //Kisi sinifinin constructor'ı çağrılır.
		musteriNumarasi = (int) (Math.random()*Math.pow(10, 4)); //Müşteri numarası rastgele verilir.
	}

	//musteriNumarasi değişkeni için getter metodu.
	public int getMusteriNumarasi(){
		return musteriNumarasi;
	}
	
	//musteriNumarasi değişkeni için setter metodu.
	public void setMusteriNumarasi(int musteriNumarasi) {
		this.musteriNumarasi = musteriNumarasi;
	}

	//hesaplar ArrayList'i için getter metodu.
	public ArrayList<BankaHesabi> getHesaplar(){
		return hesaplar;
	}
	
	//hesaplar ArrayList'i için setter metodu.
	public void setHesaplar(ArrayList<BankaHesabi> hesaplar) {
		this.hesaplar = hesaplar;
	}
	
	//krediKartlari ArrayList'i için getter metodu.
	public ArrayList<KrediKarti> getKrediKartlari(){
		return krediKartlari;
	}

	//krediKartlari ArrayList'i için setter metodu.
	public void setKrediKartlari(ArrayList<KrediKarti> krediKartlari) {
		this.krediKartlari = krediKartlari;
	}
	
	//Yeni hesap ekleme metodu.
	public void hesapEkle(String hesapTuru, double bakiye, JFrame frame) {
		//hesapTuru parametresinin argümanı "Vadesiz" ise vadesiz hesap oluşturulur.
		if(hesapTuru.equals("Vadesiz"))
			hesaplar.add(new VadesizHesap(bakiye));
		//hesapTuru parametresinin argümanı "Yatırım" ise yatırım hesabı oluşturulur.
		else if(hesapTuru.equals("Yatırım"))
			hesaplar.add(new YatirimHesabi(bakiye));
		//İkisi de değilse hata mesajı verilir.
		else
			JOptionPane.showMessageDialog(frame, "Lütfen hesap türünüzü kontrol edin.");
	}

	//Yeni kredi kartı ekleme metodu.
	public void krediKartiEkle(double limit, double guncelBorc){
		krediKartlari.add(new KrediKarti(limit, guncelBorc));
	}
	
	//Hesap silme metodu.
	public void hesapSil(int iban, JFrame frame){
		//Müşterinin banka hesapları taranır ve iban ile eşleşen banka hesabı bulunur.
		for(BankaHesabi bankaHesabi : hesaplar){
			if(bankaHesabi.getIban() == iban){
				//Bakiye sıfır ise hesap silinir.
				if(bankaHesabi.getBakiye() == 0) {
					hesaplar.remove(bankaHesabi);
				}
				//Bakiye sıfır değilse kullanıcı bilgilendirilir.
				else {
					JOptionPane.showMessageDialog(frame, "Lütfen bakiyenizi başka bir hesaba aktarın.");
				}
			}
		}
}

	//Kredi kartı silme metodu.
	public void krediKartiSil(int kartNumarasi, JFrame frame){
		//Müşterinin kredi kartları taranır ve kart numarası ile eşleşen kart bulunur.
		for(KrediKarti krediKarti : krediKartlari){
			if(krediKarti.getKartNumarasi() == kartNumarasi){
				//Kredi kartının borcu yoksa kart silinir.
				if(krediKarti.getGuncelBorc() == 0){
					krediKartlari.remove(krediKarti);
				}
				//Kredi kartının borcu varsa kullanıcı bilgilendirilir.
				else{
					JOptionPane.showMessageDialog(frame, "Lütfen borcunuzu ödeyin.");
				}
			}
		}
	}
	
	//Müşteri bilgilerini bir araya getiren toString metodu.
	@Override
	public String toString(){
		//Kisi sınıfının toString'i ile müşteri numarası birleştirilir.
		String string = super.toString() + "Müşteri Numarası: " + musteriNumarasi + System.lineSeparator();
		//Müşterinin banka hesapları boş ise string değişkenine "Banka hesabı mevcut değil." eklenir.
		if(hesaplar.isEmpty()) {
			string += "\nBanka hesabı mevcut değil." + System.lineSeparator();
		}
		
		//Müşterinin banka hesapları varsa bu hesaplar string değişkenine eklenir.
		else {
			int i = 1, y = 1; //Banka hesaplarının numaralandırılması için oluşturuldu.
			for(BankaHesabi hesap : hesaplar) {
				//BankaHesabı, VadesizHesap ise VadesizHesap'a downcast edilir ve string'e eklenir.
				if (hesap instanceof VadesizHesap) {
					string += System.lineSeparator() + "Vadesiz Hesap" + " (" + i + ") :" + System.lineSeparator();
					VadesizHesap vadesizHesap = (VadesizHesap) hesap;
					string += vadesizHesap.toString();
					i++;
				}
				//Hesap, vadesiz değilse yatırım hesabıdır. Yatırım hesabının bilgileri string'e eklenir.
				else {
					string += System.lineSeparator() + "Yatırım Hesabı" + " (" + y + ") :" + System.lineSeparator();
					YatirimHesabi yatirimHesabi = (YatirimHesabi) hesap;
					string += yatirimHesabi.toString();
					y++;
				}
			}
		}
		
		//Müşterinin kredi kartı yok ise "Kredi kartı mevcut değil." yazısı eklenir.
		if(krediKartlari.isEmpty()) {
			string += "\nKredi kartı mevcut değil." + System.lineSeparator();
		}
		//Kredi kartı varsa kartlar taranır ve her birinin toString metodu string değişkenine eklenir.
		else {
			int i = 1; //Kredi kartlarının numaralandırılması için oluşturuldu.
			for(KrediKarti krediKarti : krediKartlari) {
				string += System.lineSeparator() + "Kredi Kartı" + " (" + i + ") :" + System.lineSeparator();
				string += krediKarti.toString();
				i++;
			}
		}
		return string;
	}
}
