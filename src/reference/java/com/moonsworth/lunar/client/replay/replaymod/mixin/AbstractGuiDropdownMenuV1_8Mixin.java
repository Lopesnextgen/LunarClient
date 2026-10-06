package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.replaymod.lib.de.johni0702.minecraft.gui.GuiRenderer;
import com.replaymod.lib.de.johni0702.minecraft.gui.RenderInfo;
import com.replaymod.lib.de.johni0702.minecraft.gui.element.AbstractComposedGuiElement;
import com.replaymod.lib.de.johni0702.minecraft.gui.element.advanced.AbstractGuiDropdownMenu;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.lwjgl.ReadableDimension;
import java.util.List;
import java.util.function.Function;
import net.minecraft.client.renderer.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractGuiDropdownMenu.class)
public abstract class AbstractGuiDropdownMenuV1_8Mixin<V, T extends AbstractGuiDropdownMenu<V, T>> extends AbstractComposedGuiElement {
   @Shadow
   private Function<V, String> toString;
   private final ResourceLocationBridge downArrowIcon = ResourceLocationBridge.create("lunar", "icons/down-arrow-16x16.png");

   public AbstractGuiDropdownMenuV1_8Mixin() {
   }

   @Shadow
   public abstract V getSelectedValue();

   @Inject(method = "draw", at = @At("HEAD"), cancellable = true)
   public void draw(GuiRenderer guirenderer1, ReadableDimension readabledimension2, RenderInfo renderinfo3, CallbackInfo callback4) {
      BridgeExtension3_5 bridgeextension3_55 = BridgeExtension3_5.method32();
      boolean flag6 = (Boolean)Client.method109().method40().method64().method14().get();
      if (flag6 && renderinfo3.layer != 1) {
         super.draw(guirenderer1, readabledimension2, renderinfo3);
         if (renderinfo3.layer == 0) {
            LcuiScreen.method28(bridgeextension3_55, guirenderer1.getOpenGlOffset().getX(), guirenderer1.getOpenGlOffset().getY(), readabledimension2.getWidth(), readabledimension2.getHeight(), 4.0F, -1728053248);
            String text7 = this.toString.apply(this.getSelectedValue()).toUpperCase();
            float value8 = FontRegistry.method11().method4(text7);
            if (value8 >= readabledimension2.getWidth() - 10.0F) {
               List list9 = FontRegistry.method7().method25(text7, readabledimension2.getWidth() - 10.0F);
               float value10 = 0.0F;

               for (String text12 : list9) {
                  FontRegistry.method7()
                     .method7(bridgeextension3_55, text12, guirenderer1.getOpenGlOffset().getX() + readabledimension2.getWidth() / 2.0F - 4.0F, guirenderer1.getOpenGlOffset().getY() + 4 + value10, -1);
                  value10 += 6.0F;
               }
            } else {
               FontRegistry.method11()
                  .method7(
                     bridgeextension3_55,
                     text7,
                     guirenderer1.getOpenGlOffset().getX() + readabledimension2.getWidth() / 2.0F - 4.0F,
                     guirenderer1.getOpenGlOffset().getY() + (readabledimension2.getHeight() - 8) / 2.0F,
                     -1
                  );
            }

            LcuiScreen.method38(
               bridgeextension3_55, this.downArrowIcon, 4.0F, guirenderer1.getOpenGlOffset().getX() + readabledimension2.getWidth() - 12.0F, guirenderer1.getOpenGlOffset().getY() + 6.0F, -1
            );
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
         }

         callback4.cancel();
      }
   }
}
