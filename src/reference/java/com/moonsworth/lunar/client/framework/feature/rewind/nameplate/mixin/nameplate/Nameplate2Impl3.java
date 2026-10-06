package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.nameplate;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3Extension_4;
import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.Bridge7_9;
import com.moonsworth.lunar.bridge.BridgeType2_2;
import com.moonsworth.lunar.bridge.PacketDirection;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import io.netty.buffer.Unpooled;
import lombok.Generated;

public class Nameplate2Impl3 extends Nameplate2 {
   private int id;
   private byte[] data;
   private PacketDirection field1;
   private BridgeType2_2 field2;
   private Bridge3_21 field3;

   public Nameplate2Impl3(int var1, byte[] var2, PacketDirection var3, BridgeType2_2 var4) {
      this.id = var1;
      this.data = var2;
      this.field1 = var3;
      this.field2 = var4;
   }

   public Nameplate2Impl3(Bridge3_21 var1) {
      this.field3 = var1;
   }

   @Override
   public void method1(ByteBufLoader var1) {
      this.id = var1.readVarInt();
      this.data = var1.readByteArray();
      this.field1 = var1.method9(PacketDirection.class);
      this.field2 = var1.method9(BridgeType2_2.class);
      this.field3 = this.method3();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method11(this.id);
      var1.method2(this.data);
      var1.method10(this.field1);
      var1.method10(this.field2);
   }

   private Bridge3_21 method3() {
      if (this.field3 != null) {
         return this.field3;
      }

      Bridge7_9 var1 = Bridge.method8().method23(Unpooled.wrappedBuffer(this.data));
      Bridge3_21 var2 = Bridge.method59().method1(this.field1, this.field2, this.id, var1);
      var1.bridge$release();
      return var2;
   }

   @Override
   public void method3(Nameplate4 var1) {
      Bridge3_21 var2 = this.method3();
      if (!(var2 instanceof Bridge3Extension_4)) {
         Bridge.method59().method35(var1x -> var1.method10().method1(var1x), var2);
         var2.bridge$handle(var1.method17());
      }
   }

   @Override
   public Nameplate2 method4(Nameplate4 var1) {
      Bridge3_21 var2 = Bridge.method59().method36(() -> var1.method10().method2(), this.method3());
      return var2 == null ? null : new Nameplate2Impl3(var2);
   }

   @Override
   public String name() {
      if (this.field2 == BridgeType2_2.CONFIGURATION) {
         return "Configuration Packet";
      }

      Bridge3_21 var1;
      try {
         var1 = this.method3();
      } catch (Exception var3) {
         return super.name();
      }

      String var2 = var1.getClass().getSimpleName();
      if (var1.getClass().getEnclosingClass() != null) {
         var2 = var1.getClass().getEnclosingClass().getSimpleName() + "." + var2;
      }

      return var2;
   }

   @Override
   public String data() {
      if (this.field2 == BridgeType2_2.CONFIGURATION) {
         return "";
      }

      Bridge3_21 var1;
      try {
         var1 = this.method3();
      } catch (Exception var3) {
         return var3.getMessage();
      }

      return this.method5(var1);
   }

   @Generated
   public Nameplate2Impl3() {
   }

   @Generated
   public int getId() {
      return this.id;
   }

   @Generated
   public PacketDirection method6() {
      return this.field1;
   }
}
