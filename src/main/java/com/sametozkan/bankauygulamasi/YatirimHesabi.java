package com.sametozkan.bankauygulamasi;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

class YatirimHesabi extends BankaHesabi {

	private String hesapTuru = "Yatırım"; //Banka hesabının türü.

	//Constructor
	public YatirimHesabi(double bakiye) {
		super(bakiye); //BankaHesabi sınıfının constructor'ı çağrılır.
	}

	//Para ekle metodu (Vadesiz hesaptan yatırım hesabına).
	public void paraEkle(Musteri musteri, int iban, double miktar, JFrame frame) {
		//Müşterinin banka hesapları taranır.
		for(BankaHesabi bankaHesabi : musteri.getHesaplar()){
			//IBAN bilgisi eşleşen hesapta işlem yapılır.
			if(bankaHesabi.getIban() == iban){
				if(bankaHesabi.getBakiye() >= miktar) {
				/* Vadesiz hesabın bakiyesi miktar kadar azaltılır.
				 * (Uygulama arayüzünde sadece vadesiz hesaplar listelendiği için
				 * gönderilen IBAN, bir vadesiz hesaba aittir.) */
				bankaHesabi.setBakiye(bankaHesabi.getBakiye() - miktar);
				//Yatırım hesabının bakiyesi miktar kadar arttırılır.
				super.setBakiye(super.getBakiye() + miktar);
				}
				else {
					JOptionPane.showMessageDialog(frame, "Vadesiz hesabınızın bakiyesi yetersiz.");
				}
			}
		}
	}

	//Para çek metodu (Yatırım hesabından vadesiz hesaba).
	public void paraCek(Musteri musteri, int iban, double miktar, JFrame frame){
		//Müşterinin banka hesapları taranır.
		for(BankaHesabi bankaHesabi : musteri.getHesaplar()){
			//iban bilgisi eşleşen hesapta işlem yapılır.
			if(bankaHesabi.getIban() == iban){
				if(super.getBakiye() >= miktar) {
				/* Vadesiz hesabın bakiyesi miktar kadar arttırılır.
				 * (Uygulama arayüzünde sadece vadesiz hesaplar listelendiği için
				 * gönderilen IBAN, bir vadesiz hesaba aittir.) */
				bankaHesabi.setBakiye(bankaHesabi.getBakiye() + miktar);
				//Yatırım hesabının bakiyesi miktar kadar azaltılır.
				super.setBakiye(super.getBakiye() - miktar);
				}
				else {
					JOptionPane.showMessageDialog(frame, "Yatırım hesabınızın bakiyesi yetersiz.");
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
	
	//Yatırım hesabının bilgilerini bir araya getiren toString metodu.
	@Override
	public String toString(){
		return super.toString() + "Hesap Türü: " + hesapTuru + System.lineSeparator();
	}
}	