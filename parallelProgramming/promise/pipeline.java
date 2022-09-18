package com.pkj.parallelProgramming.promise;

import java.util.concurrent.CompletableFuture;

public class pipeline {
    public static void main(String[] args) {
        CompletableFuture<Integer> future = new CompletableFuture<Integer>();
        buildpipeline(future);
        sleep(100);
        provideData(future);

    }

    private static void provideData(CompletableFuture<Integer> future) {
        System.out.println("data provided to do operation");
        future.complete(2);
    }

    private static boolean sleep(int ms) {
        try {
            Thread.sleep(ms);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }

    private static void buildpipeline(CompletableFuture<Integer> future) {
        future
                .thenApply(data -> data * 2)
                .thenApply(data -> data + 1)
                .thenAccept(data -> System.out.println(data));
        System.out.println("build the pipeline");
    }
}
