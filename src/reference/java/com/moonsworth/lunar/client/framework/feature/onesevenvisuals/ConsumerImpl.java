package com.moonsworth.lunar.client.framework.feature.onesevenvisuals;

import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.GlBlendFactor;
import com.moonsworth.lunar.bridge.GlMatrixMode;
import com.moonsworth.lunar.bridge.DepthFunction;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemGlint;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.mod.combat.hitcolor.HitColor;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod.Type;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.function.Consumer;
import lombok.Generated;

@VersionGate(max = 5)
public class ConsumerImpl implements Consumer<EventRenderItemGlint> {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create("textures/misc/enchanted_item_glint.png");
   private final ToggleOption field2;

   public void method1(EventRenderItemGlint highlightimpl31) {
      if (!highlightimpl31.isCancelled() && (Boolean)this.field2.get()) {
         if (!Ref.method4().method40().method26().method3(highlightimpl31)) {
            OverlayMod overlaymod2 = Ref.method4().method40().method84();
            if (overlaymod2.getGlintMode() == Type.INVENTORY_ONLY) {
               if (highlightimpl31.method2() != com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemGlint.GlintTarget.GUI) {
                  return;
               }
            } else if (overlaymod2.getGlintMode() == Type.NONE) {
               return;
            }

            AbstractRenderContext bridgeextension_93 = highlightimpl31.method7();
            if (highlightimpl31.method2() == com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemGlint.GlintTarget.EQUIPPED_ARMOR) {
               HitColor hitcolor4 = Ref.method4().method40().method14();
               if (highlightimpl31.method5() != null && hitcolor4.isEnabled() && (Boolean)hitcolor4.method15().get()) {
                  EntityLivingBridge bridgeextension2_55 = (EntityLivingBridge)highlightimpl31.method5();
                  if (bridgeextension2_55.bridge$getHurtTime() > 0 || bridgeextension2_55.bridge$getDeathTime() > 0.0F) {
                     highlightimpl31.setCancelled(true);
                  }
               }
            } else {
               if (!EventRenderItemGlint.method1() || highlightimpl31.method4() == null) {
                  return;
               }

               highlightimpl31.setCancelled(true);
               bridgeextension_93.method6(false);
               bridgeextension_93.method7(DepthFunction.GL_EQUAL);
               bridgeextension_93.method11();
               bridgeextension_93.method2(GlBlendFactor.GL_SRC_COLOR, GlBlendFactor.GL_ONE);
               Ref.method3().bridge$getTextureManager().bridge$bindTexture(field1);
               bridgeextension_93.method14();
               bridgeextension_93.method4(GlBlendFactor.GL_SRC_COLOR, GlBlendFactor.GL_ONE, GlBlendFactor.GL_ONE, GlBlendFactor.GL_ZERO);
               bridgeextension_93.method8(GlMatrixMode.GL_TEXTURE);
               float value7 = 8.0F;
               int number8 = -7309112;
               if (highlightimpl31.method2() != com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemGlint.GlintTarget.GUI) {
                  number8 = -11062661;
                  value7 = 4.0F;
               }

               float value6 = (float)(Ref.method3().bridge$getSystemTime() % 3000L) / 3000.0F / value7;
               bridgeextension_93.push();
               bridgeextension_93.scale(value7, value7, value7);
               bridgeextension_93.translate(value6, 0.0, 0.0);
               bridgeextension_93.method4(-50.0F, 0.0F, 0.0F, 1.0F);
               Ref.method3().bridge$getRenderItem().bridge$renderModel(highlightimpl31.method4(), number8);
               bridgeextension_93.pop();
               value6 = (float)(Ref.method3().bridge$getSystemTime() % 4873L) / 4873.0F / value7;
               bridgeextension_93.push();
               bridgeextension_93.scale(value7, value7, value7);
               if (highlightimpl31.method2() != com.moonsworth.lunar.client.event.mixin.highlight.EventRenderItemGlint.GlintTarget.GUI) {
                  bridgeextension_93.translate(-value6, 0.0, 0.0);
                  bridgeextension_93.method4(10.0F, 0.0F, 0.0F, 1.0F);
               } else {
                  bridgeextension_93.translate(value6, 0.0, 0.0);
                  bridgeextension_93.method4(-50.0F, 0.0F, 0.0F, 1.0F);
               }

               Ref.method3().bridge$getRenderItem().bridge$renderModel(highlightimpl31.method4(), number8);
               bridgeextension_93.pop();
               bridgeextension_93.method8(GlMatrixMode.GL_MODELVIEW);
               bridgeextension_93.method2(GlBlendFactor.GL_SRC_ALPHA, GlBlendFactor.GL_ONE_MINUS_SRC_ALPHA);
               bridgeextension_93.method10();
               bridgeextension_93.method7(DepthFunction.GL_LEQUAL);
               bridgeextension_93.method6(true);
               bridgeextension_93.method4(GlBlendFactor.GL_SRC_ALPHA, GlBlendFactor.GL_ONE_MINUS_SRC_ALPHA, GlBlendFactor.GL_ONE, GlBlendFactor.GL_ZERO);
            }
         }
      }
   }

   @Generated
   public ConsumerImpl(ToggleOption lightingextension4431) {
      this.field2 = lightingextension4431;
   }
}
