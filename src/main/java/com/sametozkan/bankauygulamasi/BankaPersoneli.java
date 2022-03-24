package com.sametozkan.bankauygulamasi;

import java.util.ArrayList;

class BankaPersoneli extends Kisi {
	private int personelID; //Personel ID'sini tutan değişken.
	private ArrayList<Musteri> musteriler = new ArrayList<>(); //Personelin müşterilerini tutan ArrayList.

	//Constructor
	public BankaPersoneli(String ad, String soyad, String email, long telefonNumarasi) {
		super(ad, soyad, email, telefonNumarasi); //Kisi sınıfının constructor'ı çağrılır.
		personelID = (int) (Math.random()*1000); //Personel ID rastgele verilir.
	}
	
	//musteriler ArrayList'i için getter metodu.
	public ArrayList<Musteri> getMusteriler() {
		return musteriler;
	}
	
	//musteriler ArrayList'i için setter metodu.
	public void setMusteriler(ArrayList<Musteri> musteriler) {
		this.musteriler = musteriler;
	}
	
	//personelID değişkeni için getter metodu.
	public int getPersonelID(){
		return personelID;
	}

	//personelID değişkeni için setter metodu.
	public void setPersonelID(int personelID){
		this.personelID = personelID;
	}
	
	//Personel bilgilerini bir araya getiren toString metodu.
	@Override
	public String toString() {
		return super.toString() + "Personel ID: " + personelID +
		System.lineSeparator();
}
}