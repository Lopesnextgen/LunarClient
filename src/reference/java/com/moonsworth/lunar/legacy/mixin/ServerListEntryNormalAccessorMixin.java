package com.moonsworth.lunar.legacy.mixin;

import java.util.concurrent.ThreadPoolExecutor;
import net.minecraft.client.gui.ServerListEntryNormal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ServerListEntryNormal.class)
public interface ServerListEntryNormalAccessorMixin {
   @Mutable
   @Accessor("EXECUTOR")
   static void bridge$setExecutor(ThreadPoolExecutor threadpoolexecutor0) {
      throw new AssertionError();
   }
}
