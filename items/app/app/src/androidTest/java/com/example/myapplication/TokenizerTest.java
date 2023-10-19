package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
/**
 * @author u7587847 Yongsong
 * This class test tokenizer if could token correctly
 */

public class TokenizerTest {
    private static Tokenizer tokenizer;
    private static final String Input1 = "year<2013;price>30";
    private static final String Input2 = "price=39;year>2000";
    private static final String Input3 = "price>39;year>2000";
    private static final String Input4 = "year>2003;price=0";
    private static final String Input5 = "price>90;year>2000";
    private static final String Input6 = "year>2000;price>1000";
    private static final String Input7 = "price=100;year<2000";
    private static final String Input8 = "year>2000;price>44";
    private static final String Input9 = "price=920;year<2001";
    private static final String Input10 = "price=50;year>2000";



    @Test(timeout=1000)
    public void testInput1Token() {
        tokenizer = new Tokenizer(Input1);

        // check the type of the first token
        assertEquals("wrong token price",30, tokenizer.getPrice());

        // check the actual token value"
        assertEquals("wrong token operator", ">", tokenizer.getPriceOperator());
    }
    @Test(timeout=1000)
    public void testInput2Token() {
        tokenizer = new Tokenizer(Input2);

        // check the type of the first token
        assertEquals("wrong token price",39, tokenizer.getPrice());

        // check the actual token value"
        assertEquals("wrong token operator", "=", tokenizer.getPriceOperator());
    }

    @Test(timeout=1000)
    public void testInput3Token() {
        tokenizer = new Tokenizer(Input3);

        // check the type of the first token
        assertEquals("wrong token price",39, tokenizer.getPrice());

        // check the actual token value"
        assertEquals("wrong token operator", ">", tokenizer.getPriceOperator());
    }
    @Test(timeout=1000)
    public void testInput4Token() {
        tokenizer = new Tokenizer(Input4);

        // check the type of the first token
        assertEquals("wrong token price",0, tokenizer.getPrice());

        // check the actual token value"
        assertEquals("wrong token operator", "=", tokenizer.getPriceOperator());
    }
    @Test(timeout=1000)
    public void testInput5Token() {
        tokenizer = new Tokenizer(Input5);

        // check the type of the first token
        assertEquals("wrong token price",90, tokenizer.getPrice());

        // check the actual token value"
        assertEquals("wrong token operator", ">", tokenizer.getPriceOperator());
    }
    @Test(timeout=1000)
    public void testInput6Token() {
        tokenizer = new Tokenizer(Input6);

        // check the type of the first token
        assertEquals("wrong token price",1000, tokenizer.getPrice());

        // check the actual token value"
        assertEquals("wrong token operator", ">", tokenizer.getPriceOperator());
    }
    @Test(timeout=1000)
    public void testInput7Token() {
        tokenizer = new Tokenizer(Input7);

        // check the type of the first token
        assertEquals("wrong token price",100, tokenizer.getPrice());

        // check the actual token value"
        assertEquals("wrong token operator", "=", tokenizer.getPriceOperator());
    }
    @Test(timeout=1000)
    public void testInput8Token() {
        tokenizer = new Tokenizer(Input8);

        // check the type of the first token
        assertEquals("wrong token price",44, tokenizer.getPrice());

        // check the actual token value"
        assertEquals("wrong token operator", ">", tokenizer.getPriceOperator());
    }
    @Test(timeout=1000)
    public void testInput9Token() {
        tokenizer = new Tokenizer(Input9);

        // check the type of the first token
        assertEquals("wrong token price",920, tokenizer.getPrice());

        // check the actual token value"
        assertEquals("wrong token operator", "=", tokenizer.getPriceOperator());
    }
    @Test(timeout=1000)
    public void testInput10Token() {
        tokenizer = new Tokenizer(Input10);

        // check the type of the first token
        assertEquals("wrong token price",50, tokenizer.getPrice());

        // check the actual token value"
        assertEquals("wrong token operator", "=", tokenizer.getPriceOperator());
    }

}
