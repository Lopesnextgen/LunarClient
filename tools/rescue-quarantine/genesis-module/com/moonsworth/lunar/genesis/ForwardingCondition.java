package com.moonsworth.lunar.genesis;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

abstract class ForwardingCondition implements Condition {
   ForwardingCondition() {
   }

   abstract Condition delegate();

   @Override
   public void await() {
      this.delegate().await();
   }

   @Override
   public boolean await(long number1, TimeUnit timeunit3) {
      return this.delegate().await(number1, timeunit3);
   }

   @Override
   public void awaitUninterruptibly() {
      this.delegate().awaitUninterruptibly();
   }

   @Override
   public long awaitNanos(long number1) {
      return this.delegate().awaitNanos(number1);
   }

   @Override
   public boolean awaitUntil(Date date1) {
      return this.delegate().awaitUntil(date1);
   }

   @Override
   public void signal() {
      this.delegate().signal();
   }

   @Override
   public void signalAll() {
      this.delegate().signalAll();
   }
}
