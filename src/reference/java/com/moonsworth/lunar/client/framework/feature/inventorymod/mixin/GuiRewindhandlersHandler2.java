package com.moonsworth.lunar.client.framework.feature.inventorymod.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_27;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension8;
import com.moonsworth.lunar.bridge.Bridge5Extension_3;
import com.moonsworth.lunar.client.event.screen.ScreenOpenEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class GuiRewindhandlersHandler2 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final List<GuiRewindhandlersHandler2.Extension> field7 = new ArrayList<>();
   private long field8;
   private int field9;
   private int field10;
   @Nullable
   private Bridge5Extension6 field11;

   public GuiRewindhandlersHandler2() {
      this.handle(ScreenOpenEvent.class, this::method1);
   }

   private void method1(ScreenOpenEvent var1) {
      if (this.method3(var1.method1())) {
         if (!ThreadModuleDump63.method4().method40().method85().method19()) {
            this.field8 = ThreadModuleDump63.method14();
            this.field11 = var1.method1();
            Bridge3_27 var2 = Bridge.method20();
            this.field9 = var2.getX();
            this.field10 = var2.getY();
         }
      }
   }

   public void method2(GuiRewindhandlersHandler2.Extension var1) {
      this.field7.add(var1);
   }

   public boolean method3(@Nullable Bridge5Extension6 var1) {
      return var1 instanceof Bridge5Extension_3 || var1 instanceof Bridge5Extension8;
   }

   public boolean method4(@Nullable Bridge5Extension6 var1) {
      if (this.method3(var1) && this.field11 != null) {
         long var2 = ThreadModuleDump63.method14() - this.field8;

         for (GuiRewindhandlersHandler2.Extension var5 : this.field7) {
            if (var5.shouldPreserve(var1, this.field11, var2)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public int method5() {
      return this.field9;
   }

   public int method6() {
      return this.field10;
   }

   @FunctionalInterface
   public interface Extension {
      boolean shouldPreserve(Bridge5Extension6 var1, Bridge5Extension6 var2, long var3);
   }
}
