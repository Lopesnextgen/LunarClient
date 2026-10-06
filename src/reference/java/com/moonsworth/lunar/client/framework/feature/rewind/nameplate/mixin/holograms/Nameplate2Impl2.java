package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventUseItemOnBlockLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;
import org.joml.Vector3d;
import org.joml.Vector3i;

public class Nameplate2Impl2 extends Nameplate2 {
   private EventUseItemOnBlockLegacy field1;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = new EventUseItemOnBlockLegacy(
         new Vector3i(var1.readVarInt(), var1.readVarInt(), var1.readVarInt()),
         var1.readVarInt(),
         var1.readVarInt(),
         new Vector3d(var1.readDouble(), var1.readDouble(), var1.readDouble()),
         var1.readBoolean(),
         var1.readBoolean()
      );
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method11(this.field1.method1().x());
      var1.method11(this.field1.method1().y());
      var1.method11(this.field1.method1().z());
      var1.method11(this.field1.method2());
      var1.method11(this.field1.method3());
      var1.writeDouble(this.field1.method4().x);
      var1.writeDouble(this.field1.method4().y);
      var1.writeDouble(this.field1.method4().z);
      var1.writeBoolean(this.field1.method5());
      var1.writeBoolean(this.field1.method6());
   }

   @Override
   public void method3(Nameplate4 var1) {
      ThreadModuleDump63.method3()
         .bridge$getPlayerController()
         .bridge$useItemOn(
            this.field1.method1(), this.field1.method2(), this.field1.method3(), this.field1.method4(), this.field1.method5(), this.field1.method6()
         );
   }

   @Generated
   public Nameplate2Impl2(EventUseItemOnBlockLegacy var1) {
      this.field1 = var1;
   }

   @Generated
   public Nameplate2Impl2() {
   }
}
