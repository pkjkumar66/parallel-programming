package com.pkj.parallelProgramming.promise;

import java.util.concurrent.CompletableFuture;

public class basic {
    public static CompletableFuture<Integer> create() {
        return CompletableFuture.supplyAsync(() -> compute());
    }

    private static int compute() {
        System.out.println("In compute: " + Thread.currentThread());
        return 2;
    }

    public static void main(String[] args) {
        System.out.println("In main: " + Thread.currentThread());
        CompletableFuture<Integer> future = create();

        sleep(100);
        future.thenAccept(data -> printIt(data));
    }

    private static void printIt(Integer data) {
        System.out.println(data + " -- " + Thread.currentThread());
    }

    public static boolean sleep(int ms) {
        try {
            Thread.sleep(ms);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }
}
