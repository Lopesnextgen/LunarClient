package com.moonsworth.lunar.client.command;

import com.moonsworth.lunar.client.util.ThreadModuleDump27;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public abstract class MixinNameplate {
   private final List<MixinNameplate> field1 = new ArrayList<>();
   @Nullable
   private MixinHelper field2;

   public MixinNameplate method1(MixinNameplate var1) {
      this.field1.add(var1);
      return this;
   }

   public MixinNameplate method2(MixinHelper var1) {
      this.field2 = var1;
      return this;
   }

   public List<MixinNameplate> getChildren() {
      return Collections.unmodifiableList(this.field1);
   }

   public abstract boolean method3(ThreadModuleDump27 var1, MixinHelper22 var2);

   @Nullable
   @Generated
   public MixinHelper method4() {
      return this.field2;
   }
}
