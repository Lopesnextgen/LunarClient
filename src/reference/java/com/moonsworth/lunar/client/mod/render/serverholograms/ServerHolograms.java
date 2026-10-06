package com.moonsworth.lunar.client.mod.render.serverholograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.mod.render.serverholograms.Serverholograms;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.config.Config;
import net.kyori.adventure.text.Component;
import org.lwjgl.opengl.GL11;

public class ServerHolograms extends AbstractFeature {
   public ServerHolograms() {
      super(true);
      this.handle(HudRenderLegacyEvent.class, arg1 -> this.renderHolograms(arg1.method3(), false));
      this.handle(HudRenderLegacyEventAlt.class, arg1 -> this.renderHolograms(arg1.method3(), true));
   }

   public String getId() {
      return "SERVER_HOLOGRAMS";
   }

   protected void method1(boolean flag1) {
   }

   protected ModDetails method20() {
      return null;
   }

   private void renderHolograms(AbstractRenderContext bridgeextension_91, boolean flag2) {
      if (!Bridge.getMinecraftVersion().method23()) {
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      }

      this.renderHologramLines(bridgeextension_91, flag2, false);
      this.renderHologramLines(bridgeextension_91, flag2, true);
   }

   private void renderHologramLines(AbstractRenderContext bridgeextension_91, boolean flag2, boolean flag3) {
      EntityRenderDispatcherBridge bridge2_434 = Ref.method13();

      for (Serverholograms serverholograms6 : Client.method109().method57().method3().values()) {
         if (serverholograms6.method1() != null && serverholograms6.method1().length > 0 && serverholograms6.isShowThroughWalls() == flag2) {
            for (int index7 = serverholograms6.method1().length - 1; index7 >= 0; index7--) {
               Component component8 = serverholograms6.method1()[serverholograms6.method1().length - index7 - 1];
               float value9 = (float)(serverholograms6.getX() - bridge2_434.bridge$renderPosX());
               float value10 = (float)(serverholograms6.getY() + 1.0 + index7 * 0.25F - bridge2_434.bridge$renderPosY());
               float value11 = (float)(serverholograms6.getZ() - bridge2_434.bridge$renderPosZ());
               this.renderHologramLine(bridgeextension_91, component8, value9, value10, value11, serverholograms6.method2(), serverholograms6.isBackground(), flag3);
            }
         }
      }

      bridgeextension_91.method15();
   }

   private void renderHologramLine(AbstractRenderContext bridgeextension_91, Component component2, float value3, float value4, float value5, boolean flag6, boolean flag7, boolean flag8) {
      if (flag7 || flag8) {
         Bridge10_2 bridge10_29 = Ref.method10();
         EntityRenderDispatcherBridge bridge2_4310 = Ref.method13();
         float value11 = 1.6F;
         float value12 = 0.016666668F * value11;
         if (Bridge.getMinecraftVersion() == Config.field1) {
            value4 = (float)(value4 - 0.5);
         }

         bridgeextension_91.push();
         bridgeextension_91.translate(value3, value4, value5);
         bridgeextension_91.method4((float)(-bridge2_4310.bridge$playerViewY()), 0.0F, 1.0F, 0.0F);
         bridgeextension_91.method4((float)bridge2_4310.bridge$playerViewX(), 1.0F, 0.0F, 0.0F);
         bridgeextension_91.scale(-value12, -value12, value12);
         if (flag8) {
            bridge10_29.method11(bridgeextension_91, component2, -bridge10_29.bridge$getStringWidth(component2) / 2.0F, 0.0F, -1, flag6);
         } else {
            int number13 = (int)(bridge10_29.bridge$getStringWidth(component2) / 2.0F);
            DrawBufferBridge bridge2_3214 = bridgeextension_91.method10(LunarRenderTypes.field51);
            bridge2_3214.method1();
            bridge2_3214.method2(-number13 - 1, -1.0, 0.05).method8(0.0F, 0.0F, 0.0F, 0.25F).method16();
            bridge2_3214.method2(-number13 - 1, 8.0, 0.05).method8(0.0F, 0.0F, 0.0F, 0.25F).method16();
            bridge2_3214.method2(number13 + 1, -1.0, 0.05).method8(0.0F, 0.0F, 0.0F, 0.25F).method16();
            bridge2_3214.method2(number13 + 1, 8.0, 0.05).method8(0.0F, 0.0F, 0.0F, 0.25F).method16();
            bridge2_3214.method17(BufferMode.BATCHED);
         }

         bridgeextension_91.pop();
      }
   }
}
