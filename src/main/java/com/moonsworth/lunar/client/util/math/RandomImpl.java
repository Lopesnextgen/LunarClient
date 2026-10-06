package com.moonsworth.lunar.client.util.math;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import javax.annotation.Nullable;
import lombok.Generated;

public class RandomImpl extends Random {
   @Nullable
   private Random field1 = null;

   private Random method1() {
      return this.field1 == null ? ThreadLocalRandom.current() : this.field1;
   }

   @Override
   public synchronized void setSeed(long number1) {
      if (this.field1 == null) {
         this.field1 = new Random(number1);
      } else {
         this.field1.setSeed(number1);
      }
   }

   @Override
   public boolean nextBoolean() {
      return this.method1().nextBoolean();
   }

   @Override
   public int nextInt() {
      return this.method1().nextInt();
   }

   @Override
   public int nextInt(int number1) {
      return this.method1().nextInt(number1);
   }

   @Override
   public int nextInt(int number1, int number2) {
      return this.method1().nextInt(number1, number2);
   }

   @Override
   public long nextLong() {
      return this.method1().nextLong();
   }

   @Override
   public long nextLong(long number1) {
      return this.method1().nextLong(number1);
   }

   @Override
   public long nextLong(long number1, long number3) {
      return this.method1().nextLong(number1, number3);
   }

   @Override
   public float nextFloat() {
      return this.method1().nextFloat();
   }

   @Override
   public float nextFloat(float value1) {
      return this.method1().nextFloat(value1);
   }

   @Override
   public float nextFloat(float value1, float value) {
      return this.method1().nextFloat(value1, value);
   }

   @Override
   public double nextDouble() {
      return this.method1().nextDouble();
   }

   @Override
   public double nextDouble(double value1) {
      return this.method1().nextDouble(value1);
   }

   @Override
   public double nextDouble(double value1, double value3) {
      return this.method1().nextDouble(value1, value3);
   }

   @Override
   public IntStream ints() {
      return this.method1().ints();
   }

   @Override
   public IntStream ints(long number1) {
      return this.method1().ints(number1);
   }

   @Override
   public IntStream ints(long number1, int number3, int value) {
      return this.method1().ints(number1, number3, value);
   }

   @Override
   public IntStream ints(int number1, int number2) {
      return this.method1().ints(number1, number2);
   }

   @Override
   public LongStream longs() {
      return this.method1().longs();
   }

   @Override
   public LongStream longs(long number1) {
      return this.method1().longs(number1);
   }

   @Override
   public LongStream longs(long number1, long number3, long value) {
      return this.method1().longs(number1, number3, value);
   }

   @Override
   public LongStream longs(long number1, long number3) {
      return this.method1().longs(number1, number3);
   }

   @Override
   public DoubleStream doubles() {
      return this.method1().doubles();
   }

   @Override
   public DoubleStream doubles(long number1) {
      return this.method1().doubles(number1);
   }

   @Override
   public DoubleStream doubles(long number1, double value3, double value) {
      return this.method1().doubles(number1, value3, value);
   }

   @Override
   public DoubleStream doubles(double value1, double value3) {
      return this.method1().doubles(value1, value3);
   }

   @Generated
   public RandomImpl() {
   }
}
