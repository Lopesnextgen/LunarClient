package com.moonsworth.lunar.client.framework.feature.killsounds;

import com.moonsworth.lunar.client.mod.combat.killsounds.KillSoundEntry;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class KillSoundSettings {
   private final KillSoundType field1;
   private int volume = 100;
   private String fileName;
   @Nullable
   private KillSoundEntry field2;

   void method1(KillSoundEntry killsoundchildmod1) {
      this.field2 = killsoundchildmod1;
   }

   public boolean method2() {
      return this.fileName != null && !this.fileName.isEmpty();
   }

   public boolean isEnabled() {
      return this.field2 != null && this.field2.isEnabled();
   }

   @Generated
   public KillSoundType method3() {
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
   public void setVolume(int number1) {
      this.volume = number1;
   }

   @Generated
   public void setFileName(String text) {
      this.fileName = text;
   }

   @Generated
   public void method6(@Nullable KillSoundEntry killsoundchildmod1) {
      this.field2 = killsoundchildmod1;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof KillSoundSettings killsounds2)) {
         return false;
      } else {
         if (!killsounds2.canEqual(this)) {
            return false;
         }

         if (this.getVolume() != killsounds2.getVolume()) {
            return false;
         }

         KillSoundType killsoundstype3 = this.method3();
         KillSoundType killsoundstype4 = killsounds2.method3();
         if (killsoundstype3 == null ? killsoundstype4 == null : killsoundstype3.equals(killsoundstype4)) {
            String text5 = this.getFileName();
            String text6 = killsounds2.getFileName();
            if (text5 == null ? text6 == null : text5.equals(text6)) {
               KillSoundEntry killsoundchildmod7 = this.method4();
               KillSoundEntry killsoundchildmod8 = killsounds2.method4();
               return killsoundchildmod7 == null ? killsoundchildmod8 == null : killsoundchildmod7.equals(killsoundchildmod8);
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof KillSoundSettings;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + this.getVolume();
      KillSoundType killsoundstype3 = this.method3();
      number2 = number2 * 59 + (killsoundstype3 == null ? 43 : killsoundstype3.hashCode());
      String text4 = this.getFileName();
      number2 = number2 * 59 + (text4 == null ? 43 : text4.hashCode());
      KillSoundEntry killsoundchildmod5 = this.method4();
      return number2 * 59 + (killsoundchildmod5 == null ? 43 : killsoundchildmod5.hashCode());
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
   public KillSoundSettings(KillSoundType killSoundType) {
      this.field1 = killSoundType;
   }
}
