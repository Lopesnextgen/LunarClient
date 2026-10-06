package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.rewindhandlers;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.IResourcePackBridge;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class Nameplate2Iterator2 extends Nameplate2 {
   private List<String> packs;

   @Override
   public void method1(ByteBufLoader var1) {
      int var2 = var1.readVarInt();
      this.packs = new ArrayList<>();

      for (int var3 = 0; var3 < var2; var3++) {
         this.packs.add(var1.readString());
      }
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method11(this.packs.size());

      for (String var3 : this.packs) {
         var1.method1(var3);
      }
   }

   @Override
   public void method3(Nameplate4 var1) {
      if (!var1.method13().isEmpty() || !this.packs.isEmpty()) {
         ArrayList var2 = new ArrayList<>(this.packs);
         File var3 = new File(ThreadModuleDump63.method3().bridge$getMcDataDir(), "resourcepacks");
         ArrayList var4 = new ArrayList();

         for (String var6 : this.packs) {
            File var7 = var3.toPath().resolve(var6).toFile();

            try {
               var4.add(Bridge.method8().method79(var7, false));
            } catch (Exception var9) {
               var2.remove(var6);
            }
         }

         for (IResourcePackBridge var11 : var1.method13()) {
            var11.bridge$close();
         }

         var1.method27(var4);
         var1.method28(var2);
      }
   }

   @Generated
   public Nameplate2Iterator2(List<String> var1) {
      this.packs = var1;
   }

   @Generated
   public Nameplate2Iterator2() {
   }
}
