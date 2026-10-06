package com.moonsworth.lunar.legacy.wrapper.util;

import com.moonsworth.lunar.bridge.EmissiveMetadataSectionBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import lombok.Generated;
import net.minecraft.client.resources.data.IMetadataSection;
import org.jetbrains.annotations.Nullable;

public class EmissiveMetadataSection implements EmissiveMetadataSectionBridge, IMetadataSection {
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
   public EmissiveMetadataSection(@Nullable String text1, boolean flag) {
      this.field1 = text1;
      this.field2 = flag;
   }
}
