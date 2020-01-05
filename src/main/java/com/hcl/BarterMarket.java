package com.hcl;

public class BarterMarket {

	public static void main(String[] args) {
		
//		barterMarket(4, 8, 4, 3);
		barterMarket(393, 896, 787, 920);
	}

	public static int barterMarket(int comicBooks, int coins, int coinsNeeded, int coinsOffered) {
		
		int totalPriceOfComicBooks = (comicBooks*coinsOffered) + coins;
		
		int priceOfOneFictionBook = coinsOffered + coinsNeeded;
		
		int fictionBooks =  totalPriceOfComicBooks/priceOfOneFictionBook;
		
		System.out.println(fictionBooks);
		
		return fictionBooks;
	}
}
