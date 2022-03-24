package com.sametozkan.bankauygulamasi;

class KrediKarti {
	private int kartNumarasi; //Kart numarasını tutan değişken.
	private double limit; //Kartın limitini tutan değişken.
	private double guncelBorc; //Kartın güncel borç miktarını tutan değişken.
	private double kullanilabilirLimit; //Kartın kullanılabilir limitini tutan değişken.

	//Constructor
	public KrediKarti(double limit, double guncelBorc) {
		this.limit = limit; //Limit argümanı, bu sınıftaki limit değişkenine atanır.
		this.guncelBorc = guncelBorc; //guncelBorc argümanı, bu sınıftaki guncelBorc değişkenine atanır.
		kartNumarasi = (int) (Math.random()*Math.pow(10,5)); //Kredi kartı numarası rastgele oluşturulur.
		kullanilabilirLimit = limit - guncelBorc;
	}

	//kartNumarasi için getter metodu.
	public int getKartNumarasi(){	
		return kartNumarasi;
	}
	
	//kartNumarasi için setter metodu.
	public void kartNumarasi(int kartNumarasi) {
		this.kartNumarasi = kartNumarasi;
	}
	
	//limit için getter metodu.
	public double getLimit(){	
		return limit;
	}
		
	//limit için setter metodu.
	public void setLimit(double limit) {
		this.limit = limit;
	}

	//guncelBorc için getter metodu.
	public double getGuncelBorc() {
		return guncelBorc;
	}

	//guncelBorc için setter metodu.
	public void setGuncelBorc(double guncelBorc){
		this.guncelBorc = guncelBorc;
	}
	
	//kullanılabilirLimit için getter metodu.
	public double getKullanilabilirLimit(){	
		return kullanilabilirLimit;
	}
		
	//kullanilabilirLimit için setter metodu.
	public void setKullanilabilirLimit(double kullanilabilirLimit) {
		this.kullanilabilirLimit = kullanilabilirLimit;
	}

	//Kredi kartı bilgilerini bir araya getiren toString metodu.
	@Override
	public String toString(){
		return "Kart Numarası: " + kartNumarasi + System.lineSeparator() + "Limit: " + limit + System.lineSeparator() +
				"Güncel Borç: " + guncelBorc + System.lineSeparator() + "Kullanılabilir limit: " +
				kullanilabilirLimit + System.lineSeparator();
	}
}