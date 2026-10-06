package com.moonsworth.lunar.legacy.wrapper.util;

import com.moonsworth.lunar.bridge.Bridge3Extension2_2;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import lombok.Generated;
import net.minecraft.client.resources.data.IMetadataSection;
import org.jetbrains.annotations.Nullable;

public class Bridge3Extension2 implements Bridge3Extension2_2, IMetadataSection {
   @Nullable
   private final String field1;
   private final boolean field2;

   @Nullable
   public ResourceLocationBridge method1() {
      return this.field1 == null ? null : ResourceLocationBridge.create(this.field1);
   }

   public boolean method2() {
      return this.field2;
   }

   @Nullable
   @Generated
   public String method3() {
      return this.field1;
   }

   @Generated
   public boolean method4() {
      return this.field2;
   }

   @Generated
   public Bridge3Extension2(@Nullable String var1, boolean flag) {
      this.field1 = var1;
      this.field2 = flag;
   }
}
