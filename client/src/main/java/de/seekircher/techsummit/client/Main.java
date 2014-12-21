/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.seekircher.techsummit.client;

import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author snowbird
 */
public class Main {
    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch().start();
        
        System.out.println(new DemoClient().echo("Norbert", 10, true));
        System.out.println("Execution took " + stopwatch.stop().elapsed(TimeUnit.MILLISECONDS) + " msecs");
    }
}