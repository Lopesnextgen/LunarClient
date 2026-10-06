package com.moonsworth.lunar.client.framework.feature.killsounds;

import com.moonsworth.lunar.client.mod.combat.killsounds.KillSoundEntry;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class Killsounds {
   private final KillsoundsType field1;
   private int volume = 100;
   private String fileName;
   @Nullable
   private KillSoundEntry field2;

   void method1(KillSoundEntry var1) {
      this.field2 = var1;
   }

   public boolean method2() {
      return this.fileName != null && !this.fileName.isEmpty();
   }

   public boolean isEnabled() {
      return this.field2 != null && this.field2.isEnabled();
   }

   @Generated
   public KillsoundsType method3() {
      return this.field1;
   }

   @Generated
   public int getVolume() {
      return this.volume;
   }

   @Generated
   public String getFileName() {
      return this.fileName;
   }

   @Nullable
   @Generated
   public KillSoundEntry method4() {
      return this.field2;
   }

   @Generated
   public void setVolume(int var1) {
      this.volume = var1;
   }

   @Generated
   public void setFileName(String var1) {
      this.fileName = var1;
   }

   @Generated
   public void method6(@Nullable KillSoundEntry var1) {
      this.field2 = var1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Killsounds var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         if (this.getVolume() != var2.getVolume()) {
            return false;
         }

         KillsoundsType var3 = this.method3();
         KillsoundsType var4 = var2.method3();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            String var5 = this.getFileName();
            String var6 = var2.getFileName();
            if (var5 == null ? var6 == null : var5.equals(var6)) {
               KillSoundEntry var7 = this.method4();
               KillSoundEntry var8 = var2.method4();
               return var7 == null ? var8 == null : var7.equals(var8);
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof Killsounds;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + this.getVolume();
      KillsoundsType var3 = this.method3();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      String var4 = this.getFileName();
      var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      KillSoundEntry var5 = this.method4();
      return var2 * 59 + (var5 == null ? 43 : var5.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "KillSoundSettings(type="
         + this.method3()
         + ", volume="
         + this.getVolume()
         + ", fileName="
         + this.getFileName()
         + ", linked="
         + this.method4()
         + ")";
   }

   @Generated
   public Killsounds(KillsoundsType var1) {
      this.field1 = var1;
   }
}
