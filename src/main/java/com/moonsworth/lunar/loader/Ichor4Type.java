package com.moonsworth.lunar.loader;

import com.moonsworth.lunar.ichor.Ichor4;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public enum Ichor4Type implements Ichor4 {
   PRE_INIT,
   PRE_FORGE_PATCH,
   PRE_OPTIFINE_PATCH,
   FORGE_PATCH,
   OPTIFINE_PATCH,
   POST_FORGE_PATCH,
   POST_OPTIFINE_PATCH,
   INIT,
   INITIAL_REMAP,
   POST_REMAP,
   OMNIMIXIN_PROCESSING,
   EXTERNAL_REMAP,
   ACCESS_WIDEN,
   PRE_META_MIXIN,
   META_MIXIN(true, true),
   POST_META_MIXIN,
   PRE_MIXIN,
   MIXIN(true, false),
   POST_MIXIN,
   FINAL;

   private final boolean mixinRuntime;
   private final boolean sandboxMixinRuntime;

   Ichor4Type() {
      this(false, false);
   }

   Ichor4Type(boolean flag, boolean flag2) {
      this.mixinRuntime = flag;
      this.sandboxMixinRuntime = flag2;
   }

   @Override
   public boolean hasMixinRuntime() {
      return this.mixinRuntime;
   }

   @Override
   public boolean shouldUseParentAsMixinRuntime() {
      return !this.sandboxMixinRuntime;
   }

   @Override
   public boolean shouldUseClassBytes() {
      return this.ordinal() < POST_FORGE_PATCH.ordinal();
   }

   public static List<Ichor4Type> getApplicableStages(@Nullable Ichor4Type var0, @Nullable Ichor4Type var1) {
      if (var1 == null) {
         var1 = FINAL;
      }

      if (var0 == null) {
         var0 = INIT;
      }

      ArrayList var2 = new ArrayList();

      for (Ichor4Type var6 : values()) {
         if (var6.ordinal() >= var0.ordinal()) {
            if (var6.ordinal() > var1.ordinal()) {
               break;
            }

            var2.add(var6);
         }
      }

      return var2;
   }
}
