package com.moonsworth.lunar.client.framework.listener;

import com.moonsworth.lunar.client.event.input.InputAction;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseButton;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongList;
import java.util.function.LongPredicate;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

public class CpsListener extends DynamicListener {
   private final LongList field7 = new LongArrayList();
   private final LongList field8 = new LongArrayList();
   private final LongList field9 = new LongArrayList();
   private static final LongPredicate field10 = arg0 -> arg0 < System.currentTimeMillis() - 1000L;

   public CpsListener() {
      this.handle(EventTick.class, this::method1);
      this.handle(EventMouseButton.class, this::method2);
   }

   private void method1(EventTick event) {
      this.field7.removeIf(field10);
      this.field8.removeIf(field10);
      this.field9.removeIf(field10);
   }

   private void method2(EventMouseButton event) {
      if (Ref.method3().bridge$getCurrentScreen() == null && event.method4() == InputAction.DOWN) {
         long index2 = System.currentTimeMillis();
         if (event.method2() == 0) {
            this.field7.add(index2);
            if (Ref.method7() != null && !Ref.method7().bridge$isUsingItem()) {
               this.field8.add(index2);
            }
         }

         if (event.method2() == 1) {
            this.field9.add(index2);
         }
      }
   }

   public int method3(boolean flag1, boolean flag) {
      return flag1 ? this.method5(flag) : this.method8();
   }

   public void method5() {
      this.field7.clear();
   }

   public int method5(boolean flag1) {
      return flag1 ? this.field8.size() : this.field7.size();
   }

   public int method6() {
      return this.field8.size();
   }

   public void method7() {
      this.field9.clear();
   }

   public int method8() {
      return this.field9.size();
   }
}
