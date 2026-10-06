package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.NameplateType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.NameplateType2;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class Holograms5_3 {
   private final Holograms3_3 field1;
   private NameplateType2 field2 = NameplateType2.NONE;
   @Nullable
   private Holograms2 field3 = null;

   public void startRecording() {
      this.field3 = new Holograms2();
      this.field2 = NameplateType2.NONE;
   }

   public void method1() {
      this.field3 = null;
      this.field2 = NameplateType2.NONE;
   }

   public Optional<Holograms2> method2(String var1, String var2) {
      Optional var3 = Optional.ofNullable(this.field3);
      if (this.field3 != null) {
         this.field3.method5(this.field2);
         Holograms3_3 var4 = SkyblockDungeonRoutes.method13().method16();
         var4.method10(this.field3, var1, var2);
         this.field3 = null;
         this.field2 = NameplateType2.NONE;
      }

      return var3;
   }

   public void method3(int[] var1, NameplateType var2) {
      if (this.field3 != null) {
         Holograms7 var3 = this.field3.method2();
         this.method4(var1, var2, var3);
      }
   }

   public void method4(int[] var1, NameplateType var2, Holograms7 var3) {
      if (this.field3 != null) {
         com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3 var4 = this.field1.method17().orElse(null);
         if (var4 != null) {
            int[] var5 = var4.method20(var1);
            if (!var3.method5(var2, var5)) {
               var3.method1(var2, var5);
               if (var2.isSectionSeparator()) {
                  this.field3.method4();
               }

               if (var2 == NameplateType.ETHERWARP) {
                  this.field3.method6();
               }
            }
         }
      }
   }

   public void method5(NameplateType2 var1) {
      if (this.field3 != null && this.field2 != NameplateType2.NONE) {
         this.field2 = var1;
      }
   }

   public void method6() {
      if (this.field3 != null) {
         this.field3.method7();
      }
   }

   public void method7() {
      if (this.field3 != null) {
         this.field3.method8();
      }
   }

   public void method8() {
      Bridge5Extension_5 var1 = ThreadModuleDump63.method7();
      if (this.field3 != null && var1 != null) {
         com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3 var2 = this.field1.method17().orElse(null);
         if (var2 != null) {
            int[] var3 = new int[]{(int)Math.floor(var1.bridge$getPosX()), (int)var1.bridge$getPosY(), (int)Math.floor(var1.bridge$getPosZ())};
            int[] var4 = var2.method20(var3);
            this.field3.method2().method2(var4);
         }
      }
   }

   public Optional<Holograms2> method9() {
      return Optional.ofNullable(this.field3);
   }

   public boolean method10() {
      return this.field3 != null;
   }

   public Optional<Holograms7> method11() {
      return this.method9().map(Holograms2::method2);
   }

   @Generated
   public Holograms5_3(Holograms3_3 var1) {
      this.field1 = var1;
   }
}
