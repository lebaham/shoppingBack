package com.shopping.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoppingApplication {

	public static void main(String[] args)
	{
		int i = 20;
		int j = 0;
		try{
			System.out.println(i/j);
		}catch (ClassCastException e){
			e.printStackTrace();
		}finally {
			System.out.println("action faite automatiquement");
		}
		System.out.println("coucou steve!!");
		SpringApplication.run(ShoppingApplication.class, args);
	}

}

