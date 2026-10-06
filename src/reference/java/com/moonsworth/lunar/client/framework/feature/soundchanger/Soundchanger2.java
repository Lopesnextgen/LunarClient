package com.moonsworth.lunar.client.framework.feature.soundchanger;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump46;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Arrays;
import lombok.Generated;
import org.apache.commons.lang3.StringUtils;

public final class Soundchanger2 {
   private final ResourceLocationBridge field1;
   private int volume;
   private String prettyName;
   private String[] field2;
   private String field3;

   public Soundchanger2(ResourceLocationBridge var1, int value) {
      this.field1 = var1;
      this.volume = value;
      String var3 = this.field1.bridge$getPath();
      String[] var4 = var3.split("\\.");
      this.field2 = Arrays.copyOfRange(var4, 0, var4.length - 1);
      this.field3 = var4[var4.length - 1];
      this.prettyName = ThreadModuleDump46.method4(var4[var4.length - 1]);
   }

   public String method1() {
      return StringUtils.join(this.field2, ".");
   }

   public String method2() {
      return this.method1() + "." + this.field3;
   }

   public void setVolume(int var1) {
      if (ThreadModuleDump63.method4().method40() != null && ThreadModuleDump63.method4().method40().method58().isEnabled()) {
         ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$getSoundEngine().bridge$setPlayingSoundVolume(this.field1, var1 * 0.01F);
         this.volume = var1;
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
   public void setPrettyName(String var1) {
      this.prettyName = var1;
   }

   @Generated
   public void method6(String[] var1) {
      this.field2 = var1;
   }

   @Generated
   public void method7(String var1) {
      this.field3 = var1;
   }
}
