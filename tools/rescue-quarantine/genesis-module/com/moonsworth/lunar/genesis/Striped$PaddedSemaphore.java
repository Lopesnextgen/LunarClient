package com.moonsworth.lunar.genesis;

import java.util.concurrent.Semaphore;

class Striped$PaddedSemaphore extends Semaphore {
   long unused1;
   long unused2;
   long unused3;

   Striped$PaddedSemaphore(int number1) {
      super(number1, false);
   }
}
