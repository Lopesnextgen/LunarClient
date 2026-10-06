package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.fishing;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import java.util.UUID;
import lombok.Generated;

public class Nameplate2Impl3 extends Nameplate2 {
   private UUID id;
   private boolean field1;

   @Override
   public void method1(ByteBufLoader var1) {
      this.id = var1.method14();
      this.field1 = var1.readBoolean();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method15(this.id);
      var1.writeBoolean(this.field1);
   }

   @Override
   public void method3(Nameplate4 var1) {
      if (!var1.method18() || !this.field1) {
         var1.method6().method40().method27(this.id.toString());
      }
   }

   @Generated
   public Nameplate2Impl3(UUID var1, boolean flag) {
      this.id = var1;
      this.field1 = flag;
   }

   @Generated
   public Nameplate2Impl3() {
   }
}
