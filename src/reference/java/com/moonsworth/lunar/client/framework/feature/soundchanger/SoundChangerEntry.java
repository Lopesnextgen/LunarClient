package com.moonsworth.lunar.client.framework.feature.soundchanger;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.text.TextUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Arrays;
import lombok.Generated;
import org.apache.commons.lang3.StringUtils;

public final class SoundChangerEntry {
   private final ResourceLocationBridge field1;
   private int volume;
   private String prettyName;
   private String[] field2;
   private String field3;

   public SoundChangerEntry(ResourceLocationBridge horsestats141, int value) {
      this.field1 = horsestats141;
      this.volume = value;
      String text3 = this.field1.bridge$getPath();
      String[] items4 = text3.split("\\.");
      this.field2 = Arrays.copyOfRange(items4, 0, items4.length - 1);
      this.field3 = items4[items4.length - 1];
      this.prettyName = TextUtils.capitalize(items4[items4.length - 1]);
   }

   public String method1() {
      return StringUtils.join(this.field2, ".");
   }

   public String method2() {
      return this.method1() + "." + this.field3;
   }

   public void setVolume(int value) {
      if (Ref.method4().method40() != null && Ref.method4().method40().method58().isEnabled()) {
         Ref.method3().bridge$getSoundHandler().bridge$getSoundEngine().bridge$setPlayingSoundVolume(this.field1, value * 0.01F);
         this.volume = value;
      }
   }

   @Generated
   public ResourceLocationBridge getLocation() {
      return this.field1;
   }

   @Generated
   public int getVolume() {
      return this.volume;
   }

   @Generated
   public String getPrettyName() {
      return this.prettyName;
   }

   @Generated
   public String[] method3() {
      return this.field2;
   }

   @Generated
   public String method4() {
      return this.field3;
   }

   @Generated
   public void setPrettyName(String text1) {
      this.prettyName = text1;
   }

   @Generated
   public void method6(String[] items1) {
      this.field2 = items1;
   }

   @Generated
   public void method7(String text1) {
      this.field3 = text1;
   }
}
