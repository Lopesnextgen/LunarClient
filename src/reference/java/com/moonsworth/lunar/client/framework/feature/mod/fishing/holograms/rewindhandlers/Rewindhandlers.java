package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Nameplate4;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class Rewindhandlers {
   private final Nameplate4 field1;
   private final Nameplate4 field2;
   private final Holograms2_5 field3;
   private HologramsType5 field4;
   private List<Holograms_5> field5 = new ArrayList<>();
   private int field6 = -1;

   public Rewindhandlers(Holograms2_5 var1, HologramsType5 hologramsType5, Nameplate4 var3, Nameplate4 var4) {
      this.field3 = var1;
      this.field4 = null;
      this.field1 = var3;
      this.field2 = var4;
      this.method1(hologramsType5);
   }

   public void method1(HologramsType5 var1) {
      if (this.field4 != var1) {
         this.field5.add(new Holograms_5(this.field4, var1));
         this.field4 = var1;
         if (this.field4 != HologramsType5.BLOOD && this.field4 != HologramsType5.WITHER_DOOR) {
            this.field3.method18(this);
         } else {
            this.field3.method17(this);
         }
      }
   }

   public void method2(HologramsType5 var1) {
      this.field4 = var1;
   }

   public void method3(List<Holograms_5> var1) {
      this.field5 = var1;
   }

   public void method4(long var1) {
      while (this.method5(var1)) {
      }
   }

   private boolean method5(long var1) {
      if (this.field6 >= 0) {
         Holograms_5 var3 = this.field5.get(this.field6);
         if (var3.getTimestamp() > var1) {
            this.field6--;
            var3.method2(this);
            return true;
         }
      }

      if (this.field5.size() <= this.field6 + 1) {
         return false;
      } else {
         Holograms_5 var4 = this.field5.get(this.field6 + 1);
         if (var4.getTimestamp() < var1) {
            this.field6++;
            var4.method1(this);
            return true;
         } else {
            return false;
         }
      }
   }

   public List<Holograms_5> getEvents() {
      return this.field5;
   }

   @Generated
   public Nameplate4 method6() {
      return this.field1;
   }

   @Generated
   public Nameplate4 method7() {
      return this.field2;
   }

   @Generated
   public HologramsType5 method8() {
      return this.field4;
   }
}
