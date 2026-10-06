package com.moonsworth.lunar.client.framework.feature.debug.optimizationdebugmod;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge7_2;
import com.moonsworth.lunar.client.mod.misc.debug.OptimizationDebugMod;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.util.KeepName;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import lombok.Generated;

@KeepName
public enum OptimizationDebugOption {
   SKIP_UNBIND_FRAME_BUFFER(() -> Ref.MC_VERSION >= 29 && isNvidia()),
   BETTER_TEXT_BATCHING(Ref.MC_VERSION >= 35),
   MULTIPLE_BUFFERS_PER_FENCE(true),
   ADD_SYNC_TO_FENCE(true),
   GECKO_CURRENT_PROGRAM_CACHE(Ref.MC_VERSION >= 6),
   CACHE_RESOURCELOCATION_TOSTRING(true, false),
   FASTER_PACK_LOADING(true),
   ALLOW_TURBO_ENTITIES(true),
   ALLOW_TURBO_BLOCK_ENTITIES(true),
   FAST_TURBO_EXTRACT(Ref.MC_VERSION >= 33),
   VULKAN_RENDER_PASS_COALESCING(Ref.MC_VERSION >= 39),
   ENABLE_RESOURCE_TRACKING(false),
   FASTER_ARMORSTAND_TICK(true),
   CULL_DISPLAY_ENTITIES(Ref.MC_VERSION >= 16),
   FAST_FRAMEBUFFER(true),
   FAST_TEXT(Ref.MC_VERSION >= 36),
   FRUSTUM_IMPROVEMENTS(Ref.MC_VERSION >= 39);

   private Boolean enabledByDefault;
   private final boolean canChangeAtRuntime;
   private final Supplier<Boolean> enabledSupplier;
   private static final AtomicReference<Object> toggleableEnabledOptis = new AtomicReference<>();
   private static final AtomicReference<Object> enabledOptis = new AtomicReference<>();
   private static final AtomicReference<Object> toggleableDisabledOptis = new AtomicReference<>();

   OptimizationDebugOption(boolean flag3) {
      this(flag3, true);
   }

   OptimizationDebugOption(boolean flag3, boolean flag4) {
      this.enabledByDefault = flag3;
      this.canChangeAtRuntime = flag4;
      this.enabledSupplier = () -> flag3;
   }

   OptimizationDebugOption(Supplier<Boolean> supplier3) {
      this(supplier3, true);
   }

   OptimizationDebugOption(Supplier<Boolean> supplier3, boolean flag4) {
      this.enabledByDefault = null;
      this.canChangeAtRuntime = flag4;
      this.enabledSupplier = supplier3;
   }

   public boolean isEnabledByDefault() {
      if (this.enabledByDefault == null) {
         this.enabledByDefault = this.enabledSupplier.get();
      }

      return this.enabledByDefault;
   }

   public boolean isEnabled() {
      return !this.canChangeAtRuntime ? this.isEnabledByDefault() : OptimizationDebugMod.method12(this);
   }

   public static boolean isNvidia() {
      Bridge7_2 bridge7_20 = Bridge.method42().method85();
      String text1 = bridge7_20.field3.toLowerCase(Locale.ROOT);
      String text2 = bridge7_20.field1.toLowerCase(Locale.ROOT);
      return text1.contains("nvidia") || text2.contains("nvidia");
   }

   @Generated
   public boolean isCanChangeAtRuntime() {
      return this.canChangeAtRuntime;
   }

   @Generated
   public static OptimizationDebugOption[] getToggleableEnabledOptis() {
      Object obj0 = toggleableEnabledOptis.get();
      if (obj0 == null) {
         synchronized (toggleableEnabledOptis) {
            obj0 = toggleableEnabledOptis.get();
            if (obj0 == null) {
               OptimizationDebugOption[] items2 = Arrays.stream(values())
                  .filter(arg0x -> arg0x.isEnabledByDefault() && arg0x.canChangeAtRuntime)
                  .toArray(OptimizationDebugOption[]::new);
               obj0 = items2 == null ? toggleableEnabledOptis : items2;
               toggleableEnabledOptis.set(obj0);
            }
         }
      }

      return (OptimizationDebugOption[])(obj0 == toggleableEnabledOptis ? null : obj0);
   }

   @Generated
   public static OptimizationDebugOption[] getEnabledOptis() {
      Object obj0 = enabledOptis.get();
      if (obj0 == null) {
         synchronized (enabledOptis) {
            obj0 = enabledOptis.get();
            if (obj0 == null) {
               OptimizationDebugOption[] items2 = Arrays.stream(values())
                  .filter(OptimizationDebugOption::isEnabledByDefault)
                  .toArray(OptimizationDebugOption[]::new);
               obj0 = items2 == null ? enabledOptis : items2;
               enabledOptis.set(obj0);
            }
         }
      }

      return (OptimizationDebugOption[])(obj0 == enabledOptis ? null : obj0);
   }

   @Generated
   public static OptimizationDebugOption[] getToggleableDisabledOptis() {
      Object obj0 = toggleableDisabledOptis.get();
      if (obj0 == null) {
         synchronized (toggleableDisabledOptis) {
            obj0 = toggleableDisabledOptis.get();
            if (obj0 == null) {
               OptimizationDebugOption[] items2 = Arrays.stream(values())
                  .filter(arg0x -> !arg0x.isEnabledByDefault() && arg0x.canChangeAtRuntime)
                  .toArray(OptimizationDebugOption[]::new);
               obj0 = items2 == null ? toggleableDisabledOptis : items2;
               toggleableDisabledOptis.set(obj0);
            }
         }
      }

      return (OptimizationDebugOption[])(obj0 == toggleableDisabledOptis ? null : obj0);
   }
}
