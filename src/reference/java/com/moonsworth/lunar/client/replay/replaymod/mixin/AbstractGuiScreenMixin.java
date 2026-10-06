package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.framework.Ref;
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
public abstract class AbstractGuiScreenMixin<T> {
   @Shadow
   private Background background;
   @Shadow
   private GuiLabel title;
   private ReadableDimension size;
   private RenderInfo renderInfo;
   private static int lunarPanoramaTimer;

   public AbstractGuiScreenMixin() {
   }

   @Shadow
   public abstract GuiScreen toMinecraft();

   @ModifyVariable(method = "layout", at = @At("HEAD"), ordinal = 0, argsOnly = true)
   private ReadableDimension ichor$useInternalSize(ReadableDimension readabledimension1) {
      return readabledimension1 == null ? this.size : readabledimension1;
   }

   @Inject(method = "draw", at = @At("HEAD"))
   public void ichor$draw(GuiRenderer guirenderer1, ReadableDimension readabledimension2, RenderInfo renderinfo3, CallbackInfo callback4) {
      this.size = readabledimension2;
      this.renderInfo = renderinfo3;
   }

   @Redirect(
      method = "draw",
      at = @At(value = "FIELD", target = "Lcom/replaymod/lib/de/johni0702/minecraft/gui/RenderInfo;layer:I", opcode = 180, ordinal = 0)
   )
   public int ichor$layer(RenderInfo renderinfo1) {
      return -4;
   }

   @Inject(method = "draw", at = @At("HEAD"))
   public void ichor$draw$background(GuiRenderer guirenderer1, ReadableDimension readabledimension2, RenderInfo renderinfo3, CallbackInfo callback4) {
      BridgeExtension3_5 bridgeextension3_55 = BridgeExtension3_5.method32();
      if (this.renderInfo.layer == 0) {
         switch (this.background) {
            case NONE:
            default:
               break;
            case DIRT:
               if (!(Boolean)Client.method109().method40().method64().method14().get()) {
                  this.toMinecraft().drawBackground(0);
                  break;
               }
            case DEFAULT:
               if (!(Boolean)Client.method109().method40().method64().method14().get()) {
                  this.toMinecraft().drawDefaultBackground();
                  break;
               } else if (Ref.method8() == null) {
                  LcuiScreen.method126(bridgeextension3_55, this.size.getWidth(), this.size.getHeight(), lunarPanoramaTimer, this.renderInfo.getPartialTick());
                  break;
               }
            case TRANSPARENT:
               int number6 = -1072689136;
               int number7 = -804253680;
               guirenderer1.drawRect(0, 0, this.size.getWidth(), this.size.getHeight(), number6, number6, number7, number7);
         }

         if (this.title != null) {
            int number9 = this.size.getWidth() / 2 - readabledimension2.getWidth() / 2;
            OffsetGuiRenderer offsetguirenderer10 = new OffsetGuiRenderer(guirenderer1, new Point(number9, 10), new Dimension(0, 0));
            if ((Boolean)Client.method109().method40().method64().method14().get()) {
               String text8 = this.title.getText().toUpperCase().replace("", " ").trim();
               FontRegistry.method11().method6(bridgeextension3_55, text8, this.size.getWidth() / 2.0F, 13.0F, -1);
            } else {
               this.title.draw(offsetguirenderer10, readabledimension2, this.renderInfo);
            }
         }
      }
   }

   static {
      LunarEventBus.method29().method2(EventTick.class, arg0 -> lunarPanoramaTimer++);
   }
}
