package com.moonsworth.lunar.loader;

import com.moonsworth.lunar.ichor.IchorStage;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public enum PipelineStage implements IchorStage {
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

   PipelineStage() {
      this(false, false);
   }

   PipelineStage(boolean flag, boolean flag2) {
      this.mixinRuntime = flag;
      this.sandboxMixinRuntime = flag2;
   }

   public boolean hasMixinRuntime() {
      return this.mixinRuntime;
   }

   public boolean shouldUseParentAsMixinRuntime() {
      return !this.sandboxMixinRuntime;
   }

   public boolean shouldUseClassBytes() {
      return this.ordinal() < POST_FORGE_PATCH.ordinal();
   }

   public static List<PipelineStage> getApplicableStages(@Nullable PipelineStage ichor4type0, @Nullable PipelineStage ichor4type1) {
      if (ichor4type1 == null) {
         ichor4type1 = FINAL;
      }

      if (ichor4type0 == null) {
         ichor4type0 = INIT;
      }

      ArrayList list2 = new ArrayList();

      for (PipelineStage ichor4type6 : values()) {
         if (ichor4type6.ordinal() >= ichor4type0.ordinal()) {
            if (ichor4type6.ordinal() > ichor4type1.ordinal()) {
               break;
            }

            list2.add(ichor4type6);
         }
      }

      return list2;
   }
}
