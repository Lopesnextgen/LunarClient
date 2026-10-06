package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.GuiAchievementsBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.gui.achievement.GuiAchievements;
import org.spongepowered.asm.mixin.Mixin;

@VersionGate(max = 1)
@Mixin(GuiAchievements.class)
public class GuiAchievementsMixin implements GuiAchievementsBridge {
   public GuiAchievementsMixin() {
   }
}
