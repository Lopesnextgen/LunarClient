package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.client.replay.recording.ReplayLocation;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore4Task;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import java.io.File;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator2;
import com.moonsworth.lunar.client.replay.export.ReplayClipDuration;

public class ReplayHandlerImpl implements ReplayHandler {
   private final Deque<RewindIterator2> field1 = new LinkedList<>();
   private final List<File> field2 = new ArrayList<>();
   private final RewindRecorder field3;
   private final RewindhandlersNameplateCore4Task field4;
   private final RewindhandlersNameplateCore4 field5;

   public ReplayHandlerImpl(RewindRecorder rewindhandlers51, RewindhandlersNameplateCore4Task rewindhandlersnameplatecore4task2, RewindhandlersNameplateCore4 rewindhandlersnameplatecore43) {
      this.field3 = rewindhandlers51;
      this.field4 = rewindhandlersnameplatecore4task2;
      this.field5 = rewindhandlersnameplatecore43;
      this.field1.add(new RewindIterator2(rewindhandlers51, rewindhandlersnameplatecore4task2, rewindhandlersnameplatecore43, false, true));
   }

   private boolean method1() {
      return ((ReplayClipDuration)this.field3.method15().method29().get()).getMs() < 300000L;
   }

   public void method6() {
      while (this.field1.size() > 1) {
         this.field1.remove().method10();
      }

      for (RewindIterator2 rewinditerator22 : this.field1) {
         rewinditerator22.method23(false);
      }

      this.field1.add(new RewindIterator2(this.field3, this.field4, this.field5, false, true));
   }

   @Override
   public List<Integer> method1(List<File> list1) {
      return ReplayHandler.method13(list1, this.field2);
   }

   @Override
   public boolean method2() {
      for (RewindIterator2 rewinditerator22 : this.field1) {
         if (rewinditerator22.method2()) {
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
   public void method6(boolean flag1) {
      this.field1.forEach(arg1x -> arg1x.method6(flag1));
   }

   @Override
   public void mark() {
      this.field1.forEach(RewindIterator2::mark);
   }

   @Override
   public synchronized void method7() {
      Iterator iterator1 = this.field1.descendingIterator();

      while (iterator1.hasNext()) {
         RewindIterator2 rewinditerator22 = (RewindIterator2)iterator1.next();
         boolean flag3 = rewinditerator22.method21() != null;
         rewinditerator22.method7();
         if (this.method1() && (flag3 || rewinditerator22.method21() != null)) {
            break;
         }
      }
   }

   @Override
   public ReplayLocation method8() {
      return this.field1.peekLast().method8();
   }

   @Override
   public synchronized void method9(ReplayPacket nameplate21, int number2) {
      Iterator iterator3 = this.field1.descendingIterator();

      while (iterator3.hasNext()) {
         RewindIterator2 rewinditerator24 = (RewindIterator2)iterator3.next();
         boolean flag5 = rewinditerator24.method21() != null;
         rewinditerator24.method9(nameplate21, number2);
         if (this.method1() && (flag5 || rewinditerator24.method21() != null)) {
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
      RewindIterator2 rewinditerator21 = this.field1.remove();
      rewinditerator21.method17().addAll(this.field2);
      boolean flag2 = rewinditerator21.method11();
      if (this.field1.isEmpty()) {
         this.field1.add(new RewindIterator2(this.field3, this.field4, this.field5, false, true));
      }

      return flag2;
   }
}
