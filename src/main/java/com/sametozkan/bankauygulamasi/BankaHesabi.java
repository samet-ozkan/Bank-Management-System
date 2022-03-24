package com.sametozkan.bankauygulamasi;

class BankaHesabi {
	private int iban; //Hesabın IBAN bilgisini tutan değişken.
	private double bakiye; //Hesabın bakiye bilgisini tutan değişken.

	//Constructor
	BankaHesabi(double bakiye) {
		this.bakiye = bakiye; //Bakiye argümanı, bu sınıftaki bakiye değişkenine atanır.
		iban = (int) (Math.random()*Math.pow(10, 5)); //IBAN rastgele oluşturulur.
	}
	
	//iban değişkeni için getter metodu.
	public int getIban(){
		return iban;
	}
	
	//iban değişkeni için setter metodu.
	public void setIban(int iban) {
		this.iban = iban;
	}

	//bakiye değişkeni için getter metodu.
	public double getBakiye(){
		return bakiye;
	}

	//bakiye değişkeni için setter metodu.
	public void setBakiye(double bakiye){
		this.bakiye = bakiye;
	}
	
	//Banka hesabının bilgilerini bir araya getiren toString metodu.
	@Override
	public String toString() {
		return "IBAN: " + iban + System.lineSeparator() + "Bakiye: " + bakiye + System.lineSeparator();
	}
}