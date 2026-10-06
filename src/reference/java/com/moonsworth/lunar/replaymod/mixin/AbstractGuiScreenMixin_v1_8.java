package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.replaymod.lib.de.johni0702.minecraft.gui.GuiRenderer;
import com.replaymod.lib.de.johni0702.minecraft.gui.OffsetGuiRenderer;
import com.replaymod.lib.de.johni0702.minecraft.gui.RenderInfo;
import com.replaymod.lib.de.johni0702.minecraft.gui.container.AbstractGuiScreen;
import com.replaymod.lib.de.johni0702.minecraft.gui.container.AbstractGuiScreen.Background;
import com.replaymod.lib.de.johni0702.minecraft.gui.element.GuiLabel;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.lwjgl.Dimension;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.lwjgl.Point;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.lwjgl.ReadableDimension;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractGuiScreen.class)
public abstract class AbstractGuiScreenMixin_v1_8<T> {
   @Shadow
   private Background background;
   @Shadow
   private GuiLabel title;
   private ReadableDimension size;
   private RenderInfo renderInfo;
   private static int lunarPanoramaTimer;

   @Shadow
   public abstract GuiScreen toMinecraft();

   @ModifyVariable(method = "layout", at = @At("HEAD"), ordinal = 0, argsOnly = true)
   private ReadableDimension ichor$useInternalSize(ReadableDimension var1) {
      return var1 == null ? this.size : var1;
   }

   @Inject(method = "draw", at = @At("HEAD"))
   public void ichor$draw(GuiRenderer var1, ReadableDimension var2, RenderInfo var3, CallbackInfo var4) {
      this.size = var2;
      this.renderInfo = var3;
   }

   @Redirect(
      method = "draw",
      at = @At(value = "FIELD", target = "Lcom/replaymod/lib/de/johni0702/minecraft/gui/RenderInfo;layer:I", opcode = 180, ordinal = 0)
   )
   public int ichor$layer(RenderInfo var1) {
      return -4;
   }

   @Inject(method = "draw", at = @At("HEAD"))
   public void ichor$draw$background(GuiRenderer var1, ReadableDimension var2, RenderInfo var3, CallbackInfo var4) {
      BridgeExtension3_5 var5 = BridgeExtension3_5.method32();
      if (this.renderInfo.layer == 0) {
         switch (this.background) {
            case NONE:
            default:
               break;
            case DIRT:
               if (!Client.method109().method40().method64().method14().get()) {
                  this.toMinecraft().drawBackground(0);
                  break;
               }
            case DEFAULT:
               if (!Client.method109().method40().method64().method14().get()) {
                  this.toMinecraft().drawDefaultBackground();
                  break;
               } else if (ThreadModuleDump63.method8() == null) {
                  LcuiScreen.method126(var5, this.size.getWidth(), this.size.getHeight(), lunarPanoramaTimer, this.renderInfo.getPartialTick());
                  break;
               }
            case TRANSPARENT:
               int var6 = -1072689136;
               int var7 = -804253680;
               var1.drawRect(0, 0, this.size.getWidth(), this.size.getHeight(), var6, var6, var7, var7);
         }

         if (this.title != null) {
            int var9 = this.size.getWidth() / 2 - var2.getWidth() / 2;
            OffsetGuiRenderer var10 = new OffsetGuiRenderer(var1, new Point(var9, 10), new Dimension(0, 0));
            if (Client.method109().method40().method64().method14().get()) {
               String var8 = this.title.getText().toUpperCase().replace("", " ").trim();
               FontRegistry.method11().method6(var5, var8, this.size.getWidth() / 2.0F, 13.0F, -1);
            } else {
               this.title.draw(var10, var2, this.renderInfo);
            }
         }
      }
   }

   static {
      ClientEventBus.method29().method2(EventClientTick.class, var0 -> lunarPanoramaTimer++);
   }
}
