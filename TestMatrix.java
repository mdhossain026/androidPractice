package com.hossain.test;
import java.util.Random;


public class TestMatrix {

	public static void main(String[] args) {
		 // Test
        long start = System.nanoTime();
     
        //printHash();
		
        printB();
		System.out.println((System.nanoTime() - start) / 1000000000.0);

	}

	private static void printB() {
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
		    for (int j = 0; j < 1000; j++) {
		        if(r.nextInt(4) == 0) {
		            System.out.print("O");
		        } else {
		            System.out.print("B");
		        }
		    }

		   System.out.println("");
		 }
		
	}

	private static void printHash() {
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
		    for (int j = 0; j < 1000; j++) {
		        if(r.nextInt(4) == 0) {
		            System.out.print("O");
		        } else {
		            System.out.print("#");
		        }
		    }

		   System.out.println("");
		 }
	}

}
