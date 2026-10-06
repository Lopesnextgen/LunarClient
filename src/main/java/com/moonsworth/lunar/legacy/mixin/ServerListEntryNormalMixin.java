package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ServerListEntryNormalBridge;
import net.minecraft.client.gui.ServerListEntryNormal;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerListEntryNormal.class)
public abstract class ServerListEntryNormalMixin implements ServerListEntryNormalBridge {
   public ServerListEntryNormalMixin() {
   }
}
