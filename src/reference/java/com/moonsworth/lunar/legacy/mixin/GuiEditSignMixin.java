package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.GuiEditSignBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.tileentity.TileEntitySign;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiEditSign.class)
public abstract class GuiEditSignMixin implements GuiEditSignBridge {
   @Final
   @Shadow
   public TileEntitySign tileSign;
   @Shadow
   public int editLine;

   public GuiEditSignMixin() {
   }

   public int bridge$getEditLine() {
      return this.editLine;
   }

   public String bridge$getLine(int index1) {
      return Ref.MC_VERSION >= 1 ? this.tileSign.signText[index1].getUnformattedText() : this.tileSign.signText$v1_7[index1];
   }
}
