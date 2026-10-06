package com.moonsworth.lunar.client.tps;

import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.LoadableHandler;
import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTickPhase;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class FogIterator implements LoadableHandler, EventRegistrar {
   private TpsType field1 = TpsType.POST_RENDER;
   private final List<Tps> field2 = new ArrayList<>();
   private final List<Tps> field3 = new ArrayList<>();

   @Override
   public void init() {
      this.method1(EventRenderTickPhase.EventRenderTickBegin.class, () -> this.method3(TpsType.COLLECTING));
   }

   public void method1(Tps var1) {
      if (this.field1.isAfter(TpsType.COLLECTING)) {
         Slayer.error("Invalid Opaque Submit Call", new TpsException(this.field1, var1));
      } else {
         this.field2.add(var1);
      }
   }

   public void method2(Tps var1) {
      if (this.field1.isAfter(TpsType.COLLECTING)) {
         Slayer.error("Invalid Translucent Submit Call", new TpsException(this.field1, var1));
      } else {
         this.field3.add(var1);
      }
   }

   public void method3(TpsType var1) {
      if (var1 != this.field1) {
         if (var1 == TpsType.COLLECTING || !var1.isBefore(this.field1)) {
            this.field1 = var1;
            if (var1 == TpsType.RENDERING_OPAQUE) {
               this.method4();
            } else if (var1 == TpsType.RENDERING_TRANSLUCENT) {
               this.method5();
            } else if (var1 == TpsType.COLLECTING) {
               this.field2.clear();
               this.field3.clear();
            }
         }
      }
   }

   private void method4() {
      if (!this.field2.isEmpty()) {
         for (Tps var2 : this.field2) {
            var2.draw();
         }

         this.field2.clear();
      }
   }

   private void method5() {
      if (!this.field3.isEmpty()) {
         for (Tps var2 : this.field3) {
            var2.draw();
         }

         this.field3.clear();
      }
   }

   public boolean method6() {
      return this.field1 == TpsType.COLLECTING;
   }

   @Override
   public void close() {
   }

   @Generated
   public TpsType method7() {
      return this.field1;
   }
}
