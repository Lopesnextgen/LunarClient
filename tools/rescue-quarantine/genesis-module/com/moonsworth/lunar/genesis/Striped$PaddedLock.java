package com.moonsworth.lunar.genesis;

import java.util.concurrent.locks.ReentrantLock;

class Striped$PaddedLock extends ReentrantLock {
   long unused1;
   long unused2;
   long unused3;

   Striped$PaddedLock() {
      super(false);
   }
}
