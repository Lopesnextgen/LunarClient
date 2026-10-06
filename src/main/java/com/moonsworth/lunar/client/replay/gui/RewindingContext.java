package com.moonsworth.lunar.client.replay.gui;

import java.util.ArrayDeque;
import java.util.Deque;
import lombok.Generated;

public class RewindingContext {
   private final int field1 = 1000;
   private final Deque<Object[]> queue = new ArrayDeque<>(1000);
   private boolean field2;

   public RewindingContext() {
   }

   public void method1(Object... items1) {
      if (!this.field2) {
         while (this.queue.size() >= 1000) {
            this.queue.removeLast();
         }

         this.queue.push(items1);
      }
   }

   public Object[] method2() {
      return this.queue.pop();
   }

   public void clear() {
      this.queue.clear();
   }

   public boolean isEmpty() {
      return this.queue.isEmpty();
   }

   @Generated
   public void method3(boolean flag) {
      this.field2 = flag;
   }
}
