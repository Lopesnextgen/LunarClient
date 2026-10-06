package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Nameplate2Impl5 extends Nameplate2 {
   private String message;

   @Override
   public void method1(ByteBufLoader var1) {
      this.message = var1.readString();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method1(this.message);
   }

   @Override
   public void method3(Nameplate4 var1) {
      ThreadModuleDump63.method3().bridge$openChat(this.message);
   }

   @Override
   public Nameplate2 method4(Nameplate4 var1) {
      return new Nameplate2Impl2();
   }

   @Generated
   public Nameplate2Impl5(String var1) {
      this.message = var1;
   }

   @Generated
   public Nameplate2Impl5() {
   }
}
