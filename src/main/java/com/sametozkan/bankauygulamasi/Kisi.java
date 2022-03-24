package com.sametozkan.bankauygulamasi;

class Kisi {
	
	//Field
	private String ad; //Ad tutan değişken.
	private String soyad; //Soyad tutan değişken.
	private String email; //Email adresi tutan değişken.
	private long telefonNumarasi; //Telefon numarası tutan değişken.

	//Constructor
	public Kisi(String ad, String soyad, String email, long telefonNumarasi) {
		//Parametrelerle alınan argümanlar, sınıfın değişkenlerine atanır.
		this.ad = ad;
		this.soyad = soyad;
		this.email = email;
		this.telefonNumarasi = telefonNumarasi;
	}
	
	//ad değişkeni için getter metodu.
	public String getAd(){
		return ad;
	}
	
	//ad değişkeni için setter metodu.
	public void setAd(String ad){
		this.ad = ad;
	}
	
	//soyad değişkeni için getter metodu.
	public String getSoyad(){
		return soyad;
	}
	
	//soyad değişkeni için setter metodu.
	public void setSoyad(String soyad){
		this.soyad = soyad;
	}
	
	//email değişkeni için getter metodu.
	public String getEmail(){
		return email;
	}
	
	//email değişkeni için setter metodu.
	public void setEmail(String email){
		this.email = email;
	}
	
	//telefonNumarasi değişkeni için getter metodu.
	public long getTelefonNumarasi(){
		return telefonNumarasi;
	}
	
	//telefonNumarasi değişkeni için setter metodu.
	public void setTelefonNumarasi(long telefonNumarasi){
		this.telefonNumarasi = telefonNumarasi;
	}
	
	//Kişi bilgilerini bir araya getiren toString metodu.
	@Override
	public String toString() {
		return "Ad: " + ad + System.lineSeparator() + "Soyad: " + soyad + System.lineSeparator() + 
				"Email: " + email + System.lineSeparator() + "Telefon Numarası: " + 
				Long.toString(telefonNumarasi) + System.lineSeparator();
	}
	}