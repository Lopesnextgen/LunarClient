package com.moonsworth.lunar.client.framework.feature.debug.optimizationdebugmod;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge7_2;
import com.moonsworth.lunar.client.mod.misc.debug.OptimizationDebugMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.util.Annotation2;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import lombok.Generated;

@Annotation2
public enum OptimizationdebugmodType {
   SKIP_UNBIND_FRAME_BUFFER(() -> ThreadModuleDump63.MC_VERSION >= 29 && isNvidia()),
   BETTER_TEXT_BATCHING(ThreadModuleDump63.MC_VERSION >= 35),
   MULTIPLE_BUFFERS_PER_FENCE(true),
   ADD_SYNC_TO_FENCE(true),
   GECKO_CURRENT_PROGRAM_CACHE(ThreadModuleDump63.MC_VERSION >= 6),
   CACHE_RESOURCELOCATION_TOSTRING(true, false),
   FASTER_PACK_LOADING(true),
   ALLOW_TURBO_ENTITIES(true),
   ALLOW_TURBO_BLOCK_ENTITIES(true),
   FAST_TURBO_EXTRACT(ThreadModuleDump63.MC_VERSION >= 33),
   VULKAN_RENDER_PASS_COALESCING(ThreadModuleDump63.MC_VERSION >= 39),
   ENABLE_RESOURCE_TRACKING(false),
   FASTER_ARMORSTAND_TICK(true),
   CULL_DISPLAY_ENTITIES(ThreadModuleDump63.MC_VERSION >= 16),
   FAST_FRAMEBUFFER(true),
   FAST_TEXT(ThreadModuleDump63.MC_VERSION >= 36),
   FRUSTUM_IMPROVEMENTS(ThreadModuleDump63.MC_VERSION >= 39);

   private Boolean enabledByDefault;
   private final boolean canChangeAtRuntime;
   private final Supplier<Boolean> enabledSupplier;
   private static final AtomicReference<Object> toggleableEnabledOptis = new AtomicReference<>();
   private static final AtomicReference<Object> enabledOptis = new AtomicReference<>();
   private static final AtomicReference<Object> toggleableDisabledOptis = new AtomicReference<>();

   OptimizationdebugmodType(boolean var3) {
      this(var3, true);
   }

   OptimizationdebugmodType(boolean var3, boolean var4) {
      this.enabledByDefault = var3;
      this.canChangeAtRuntime = var4;
      this.enabledSupplier = () -> var3;
   }

   OptimizationdebugmodType(Supplier<Boolean> var3) {
      this(var3, true);
   }

   OptimizationdebugmodType(Supplier<Boolean> var3, boolean var4) {
      this.enabledByDefault = null;
      this.canChangeAtRuntime = var4;
      this.enabledSupplier = var3;
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
      Bridge7_2 var0 = Bridge.method42().method85();
      String var1 = var0.field3.toLowerCase(Locale.ROOT);
      String var2 = var0.field1.toLowerCase(Locale.ROOT);
      return var1.contains("nvidia") || var2.contains("nvidia");
   }

   @Generated
   public boolean isCanChangeAtRuntime() {
      return this.canChangeAtRuntime;
   }

   @Generated
   public static OptimizationdebugmodType[] getToggleableEnabledOptis() {
      Object var0 = toggleableEnabledOptis.get();
      if (var0 == null) {
         synchronized (toggleableEnabledOptis) {
            var0 = toggleableEnabledOptis.get();
            if (var0 == null) {
               OptimizationdebugmodType[] var2 = Arrays.stream(values())
                  .filter(var0x -> var0x.isEnabledByDefault() && var0x.canChangeAtRuntime)
                  .toArray(OptimizationdebugmodType[]::new);
               var0 = var2 == null ? toggleableEnabledOptis : var2;
               toggleableEnabledOptis.set(var0);
            }
         }
      }

      return (OptimizationdebugmodType[])(var0 == toggleableEnabledOptis ? null : var0);
   }

   @Generated
   public static OptimizationdebugmodType[] getEnabledOptis() {
      Object var0 = enabledOptis.get();
      if (var0 == null) {
         synchronized (enabledOptis) {
            var0 = enabledOptis.get();
            if (var0 == null) {
               OptimizationdebugmodType[] var2 = Arrays.stream(values())
                  .filter(OptimizationdebugmodType::isEnabledByDefault)
                  .toArray(OptimizationdebugmodType[]::new);
               var0 = var2 == null ? enabledOptis : var2;
               enabledOptis.set(var0);
            }
         }
      }

      return (OptimizationdebugmodType[])(var0 == enabledOptis ? null : var0);
   }

   @Generated
   public static OptimizationdebugmodType[] getToggleableDisabledOptis() {
      Object var0 = toggleableDisabledOptis.get();
      if (var0 == null) {
         synchronized (toggleableDisabledOptis) {
            var0 = toggleableDisabledOptis.get();
            if (var0 == null) {
               OptimizationdebugmodType[] var2 = Arrays.stream(values())
                  .filter(var0x -> !var0x.isEnabledByDefault() && var0x.canChangeAtRuntime)
                  .toArray(OptimizationdebugmodType[]::new);
               var0 = var2 == null ? toggleableDisabledOptis : var2;
               toggleableDisabledOptis.set(var0);
            }
         }
      }

      return (OptimizationdebugmodType[])(var0 == toggleableDisabledOptis ? null : var0);
   }
}
