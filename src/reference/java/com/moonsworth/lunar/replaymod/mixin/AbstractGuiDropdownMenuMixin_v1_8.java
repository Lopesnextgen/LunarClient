package com.moonsworth.lunar.replaymod.mixin;

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
public abstract class AbstractGuiDropdownMenuMixin_v1_8<V, T extends AbstractGuiDropdownMenu<V, T>> extends AbstractComposedGuiElement {
   @Shadow
   private Function<V, String> toString;
   private final ResourceLocationBridge downArrowIcon = ResourceLocationBridge.create("lunar", "icons/down-arrow-16x16.png");

   @Shadow
   public abstract V getSelectedValue();

   @Inject(method = "draw", at = @At("HEAD"), cancellable = true)
   public void draw(GuiRenderer var1, ReadableDimension var2, RenderInfo var3, CallbackInfo var4) {
      BridgeExtension3_5 var5 = BridgeExtension3_5.method32();
      boolean var6 = Client.method109().method40().method64().method14().get();
      if (var6 && var3.layer != 1) {
         super.draw(var1, var2, var3);
         if (var3.layer == 0) {
            LcuiScreen.method28(var5, var1.getOpenGlOffset().getX(), var1.getOpenGlOffset().getY(), var2.getWidth(), var2.getHeight(), 4.0F, -1728053248);
            String var7 = this.toString.apply(this.getSelectedValue()).toUpperCase();
            float var8 = FontRegistry.method11().method4(var7);
            if (var8 >= var2.getWidth() - 10.0F) {
               List var9 = FontRegistry.method7().method25(var7, var2.getWidth() - 10.0F);
               float var10 = 0.0F;

               for (String var12 : var9) {
                  FontRegistry.method7()
                     .method7(var5, var12, var1.getOpenGlOffset().getX() + var2.getWidth() / 2.0F - 4.0F, var1.getOpenGlOffset().getY() + 4 + var10, -1);
                  var10 += 6.0F;
               }
            } else {
               FontRegistry.method11()
                  .method7(
                     var5,
                     var7,
                     var1.getOpenGlOffset().getX() + var2.getWidth() / 2.0F - 4.0F,
                     var1.getOpenGlOffset().getY() + (var2.getHeight() - 8) / 2.0F,
                     -1
                  );
            }

            LcuiScreen.method38(
               var5, this.downArrowIcon, 4.0F, var1.getOpenGlOffset().getX() + var2.getWidth() - 12.0F, var1.getOpenGlOffset().getY() + 6.0F, -1
            );
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
         }

         var4.cancel();
      }
   }
}
