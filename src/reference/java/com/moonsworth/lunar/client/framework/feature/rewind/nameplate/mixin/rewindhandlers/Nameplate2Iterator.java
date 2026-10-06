package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.rewindhandlers;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.IResourcePackBridge;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind3;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class Nameplate2Iterator extends Nameplate2 {
   private List<Integer> field1;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = var1.method3();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method5(this.field1);
   }

   @Override
   public void method3(Nameplate4 var1) {
      if (!var1.method11().isEmpty() || !this.field1.isEmpty()) {
         ArrayList var2 = new ArrayList();

         for (int var4 : this.field1) {
            Rewind3 var5 = var1.method6().method40().method35();
            if (var5 != null) {
               File var6 = new File(Gui.field6, var5.method13().getId().toString());
               File var7 = new File(var6, String.valueOf(var4));
               var2.add(Bridge.method8().method79(var7, true));
            }
         }

         for (IResourcePackBridge var9 : var1.method11()) {
            var9.bridge$close();
         }

         var1.method25(var2);
         var1.method26(this.field1);
      }
   }

   @Generated
   public Nameplate2Iterator(List<Integer> var1) {
      this.field1 = var1;
   }

   @Generated
   public Nameplate2Iterator() {
   }
}
