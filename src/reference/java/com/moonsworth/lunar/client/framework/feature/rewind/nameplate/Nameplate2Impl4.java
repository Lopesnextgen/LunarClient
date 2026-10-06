package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import lombok.Generated;

public class Nameplate2Impl4 extends Nameplate2 {
   private Nameplate2Impl4.Type field1;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = var1.method9(Nameplate2Impl4.Type.class);
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method10(this.field1);
   }

   @Override
   public void method3(Nameplate4 var1) {
      switch (this.field1) {
         case DISCONNECT:
            DisconnectEvent.method2(true);
      }
   }

   @Generated
   public Nameplate2Impl4(Nameplate2Impl4.Type var1) {
      this.field1 = var1;
   }

   @Generated
   public Nameplate2Impl4() {
   }

   @Generated
   public Nameplate2Impl4.Type method4() {
      return this.field1;
   }

   public enum Type {
      DISCONNECT;
   }
}
