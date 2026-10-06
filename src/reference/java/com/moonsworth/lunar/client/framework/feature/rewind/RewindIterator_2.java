package com.moonsworth.lunar.client.framework.feature.rewind;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui7;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore4Task;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import java.io.File;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class RewindIterator_2 implements Rewind_4 {
   private final Deque<RewindIterator2> field1 = new LinkedList<>();
   private final List<File> field2 = new ArrayList<>();
   private final RewindHandlers5 field3;
   private final RewindhandlersNameplateCore4Task field4;
   private final RewindhandlersNameplateCore4 field5;

   public RewindIterator_2(RewindHandlers5 var1, RewindhandlersNameplateCore4Task var2, RewindhandlersNameplateCore4 var3) {
      this.field3 = var1;
      this.field4 = var2;
      this.field5 = var3;
      this.field1.add(new RewindIterator2(var1, var2, var3, false, true));
   }

   private boolean method1() {
      return ((Gui2Extension)this.field3.method15().method29().get()).getMs() < 300000L;
   }

   public void method6() {
      while (this.field1.size() > 1) {
         this.field1.remove().method10();
      }

      for (RewindIterator2 var2 : this.field1) {
         var2.method23(false);
      }

      this.field1.add(new RewindIterator2(this.field3, this.field4, this.field5, false, true));
   }

   @Override
   public List<Integer> method1(List<File> var1) {
      return Rewind_4.method13(var1, this.field2);
   }

   @Override
   public boolean method2() {
      for (RewindIterator2 var2 : this.field1) {
         if (var2.method2()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void pause() {
      this.field1.forEach(RewindIterator2::pause);
   }

   @Override
   public long method3() {
      return this.field1.isEmpty() ? 0L : this.field1.peekFirst().method4();
   }

   @Override
   public long method4() {
      return this.field1.isEmpty() ? 0L : this.field1.peekLast().method4();
   }

   @Override
   public boolean method5() {
      return this.field1.peekLast().method5();
   }

   @Override
   public void method6(boolean var1) {
      this.field1.forEach(var1x -> var1x.method6(var1));
   }

   @Override
   public void mark() {
      this.field1.forEach(RewindIterator2::mark);
   }

   @Override
   public synchronized void method7() {
      Iterator var1 = this.field1.descendingIterator();

      while (var1.hasNext()) {
         RewindIterator2 var2 = (RewindIterator2)var1.next();
         boolean var3 = var2.method21() != null;
         var2.method7();
         if (this.method1() && (var3 || var2.method21() != null)) {
            break;
         }
      }
   }

   @Override
   public Gui7 method8() {
      return this.field1.peekLast().method8();
   }

   @Override
   public synchronized void method9(Nameplate2 var1, int var2) {
      Iterator var3 = this.field1.descendingIterator();

      while (var3.hasNext()) {
         RewindIterator2 var4 = (RewindIterator2)var3.next();
         boolean var5 = var4.method21() != null;
         var4.method9(var1, var2);
         if (this.method1() && (var5 || var4.method21() != null)) {
            break;
         }
      }
   }

   @Override
   public synchronized void method10() {
      this.field1.forEach(RewindIterator2::method10);
   }

   @Override
   public synchronized boolean method11() {
      RewindIterator2 var1 = this.field1.remove();
      var1.method17().addAll(this.field2);
      boolean var2 = var1.method11();
      if (this.field1.isEmpty()) {
         this.field1.add(new RewindIterator2(this.field3, this.field4, this.field5, false, true));
      }

      return var2;
   }
}
