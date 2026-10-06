package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension7;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import org.spongepowered.asm.mixin.Mixin;

@Annotation2(min = 5)
@Mixin(GuiScreenAdvancements.class)
public class GuiScreenAdvancementsMixin implements Bridge5Extension7 {
}
