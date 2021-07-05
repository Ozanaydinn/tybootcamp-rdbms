package com.tybootcamp.ecomm;

import com.github.javafaker.Book;
import com.github.javafaker.Faker;
import com.tybootcamp.ecomm.entities.*;
import com.tybootcamp.ecomm.enums.Gender;
import com.tybootcamp.ecomm.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

@EnableJpaRepositories(basePackages = "com.tybootcamp.ecomm.repositories")
@SpringBootApplication
public class EcommApplication implements CommandLineRunner {
    private final CategoryRepository _categoryRepository;
    private final ProductJpaRepository _productJpaRepository;
    private final SellerRepository _sellerRepository;
    private final BasketRepository _basketRepository;
    private final SuperUserRepository _Super_userRepository;
    private final CustomerRepository _customerRepository;
    private final BasketProductRepository _basketProductRepository;

    public EcommApplication(CategoryRepository _categoryRepository, ProductJpaRepository _productJpaRepository, SellerRepository _sellerRepository, BasketRepository basketRepository, SuperUserRepository superUserRepository, CustomerRepository customerRepository, BasketProductRepository basketProductRepository) {
        this._categoryRepository = _categoryRepository;
        this._productJpaRepository = _productJpaRepository;
        this._sellerRepository = _sellerRepository;
        this._basketRepository = basketRepository;
        this._Super_userRepository = superUserRepository;
        this._customerRepository = customerRepository;
        _basketProductRepository = basketProductRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(EcommApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        //--------------Create two sellers-----------------------------------------
        Seller judy = new Seller((long)879);
        Profile judyProfile = new Profile(judy, "Judy", "Adams", Gender.Female);
        judyProfile.setBirthday(new SimpleDateFormat("MM/dd/yyyy").parse(("4/12/2010")));
        judyProfile.setEmailAddress("hi1@demo.com");
        judy.setProfile(judyProfile);
        _sellerRepository.save(judy);


        Seller michael = new Seller((long) 23);
        Profile michaelProfile = new Profile(michael, "Michael", "Martin", Gender.Male);
        michaelProfile.setEmailAddress("hi2@demo.com");
        michael.setProfile(michaelProfile);
        michael = _sellerRepository.save(michael);



        //--------------Create 4 different categories and save them--------------------
        Category artCategory = new Category("Art");
        Category wallDecorCategory = new Category("Wall Decor");
        Category babyCategory = new Category("Baby");
        Category toysCategory = new Category("Toys");
        artCategory = _categoryRepository.save(artCategory);
        wallDecorCategory = _categoryRepository.save(wallDecorCategory);
        _categoryRepository.save(babyCategory);
        _categoryRepository.save(toysCategory);


        //--------------Create a product in wall decor and art categories--------------
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("https://c.pxhere.com/photos/b1/ab/fantastic_purple_trees_beautiful_jacaranda_trees_pretoria_johannesburg_south_africa-1049314.jpg!d");
        imageUrls.add("https://c.pxhere.com/photos/90/da/jacaranda_trees_flowering_purple_stand_blossom_spring_plant-922332.jpg!d");

        Seller finalMichael = michael;
        Category finalArtCategory = artCategory;
        Category finalWallDecorCategory = wallDecorCategory;
        /*
        IntStream.range(1, 100).parallel().forEach(
                i -> {
                    Book book = new Faker().book();
                    String author = book.author();
                    Product pictureProduct = new Product(author, author,
                            book.title(),
                            42.34f, imageUrls, finalMichael, new HashSet<>(Arrays.asList(finalArtCategory, finalWallDecorCategory)));
                    _productJpaRepository.save(pictureProduct);
                }
        );

         */

        // Try adding a product to basket
        Product product = new Product("Lord of the Rings", "Action Figure", "A Frodo action figure from LOTR!", 123.50f, imageUrls, finalMichael, new HashSet<>(Arrays.asList(finalWallDecorCategory)));
        Basket ozanBasket = new Basket();
        BasketProduct bp1 = new BasketProduct(product, 2, ozanBasket);
        ozanBasket.setBasketProducts(new HashSet<>(Arrays.asList(bp1)));

        Customer ozan = new Customer("Ozan");
        Profile ozanProfile = new Profile(ozan, "Ozan", "Aydın",Gender.Male);
        ozanProfile.setEmailAddress("hi3@demo.com");
        ozan.setProfile(ozanProfile);
        ozan.setBasket(ozanBasket);
        _customerRepository.save(ozan);
    }
}
