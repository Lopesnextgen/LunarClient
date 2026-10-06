package com.moonsworth.lunar.client.mod.skyblock.terminalsolvers;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.BlockButtonBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BlocksBridge;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.practice.TerminalSolverModule;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockChange;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockUpdate.BlockUpdate;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;
import org.joml.Vector3i;

public class SkyblockSimonSays extends TerminalSolverModule {
   private final List<Vector3i> field11 = new ArrayList<>();
   private int field12;
   private int field13;
   private boolean field14;

   public SkyblockSimonSays(SkyblockTerminalSolvers skyblockterminalsolvers1, ToggleOption lightingextension4432) {
      super(null);
      this.method3(ModTraits.field16, ChildModBinding.method4(false, skyblockterminalsolvers1));
      this.method3(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.method5(this::onDisable);
      this.handle(HudRenderLegacyEvent.class, this::method4);
      this.handle(EventBlockChange.class, this::method3);
      this.handle(BlockUpdate.class, arg1x -> {
         this.method5(arg1x);
         this.method6(arg1x);
      });
      this.handle(EventWorldChange.class, this::method2);
   }

   private void onDisable() {
      this.field11.clear();
      this.field12 = 0;
   }

   public String getId() {
      return "SKYBLOCK_SIMON_SAYS";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(EventWorldChange data31) {
      this.field11.clear();
      this.field12 = 0;
   }

   private void method3(EventBlockChange highlightimpl91) {
      int number2 = highlightimpl91.method1().bridge$getX();
      int number3 = highlightimpl91.method1().bridge$getY();
      int number4 = highlightimpl91.method1().bridge$getZ();
      BlocksBridge bridge_565 = Bridge.method34();
      Bridge3_23 bridge3_236 = highlightimpl91.method3().bridge$getBlock();
      if (number3 >= 120 && number3 <= 123 && number4 >= 92 && number4 <= 95) {
         if (!this.method8(highlightimpl91)) {
            return;
         }

         if (number2 == 111) {
            if (bridge3_236 != bridge_565.method10()) {
               return;
            }

            if (highlightimpl91.method2().bridge$getBlock() == bridge_565.method10()) {
               return;
            }

            if (this.field14) {
               this.reset();
               this.field13++;
            }

            Vector3i vector3i7 = highlightimpl91.method1().bridge$toJoml();
            this.field11.add(vector3i7);
            if (this.field11.size() > this.field13 + 2) {
               this.field11.remove(0);
            }
         }
      } else if (number2 == 110 && number3 == 121 && number4 == 91 && this.method7(highlightimpl91)) {
         this.reset();
         this.field13 = 0;
      }
   }

   private void method4(HudRenderLegacyEvent highlightimpl21) {
      if (this.field12 < this.field11.size()) {
         EntityRenderDispatcherBridge bridge2_432 = Ref.method13();
         AbstractRenderContext bridgeextension_93 = highlightimpl21.method3();
         bridgeextension_93.push();
         bridgeextension_93.translate(-bridge2_432.bridge$renderPosX(), -bridge2_432.bridge$renderPosY(), -bridge2_432.bridge$renderPosZ());
         SkyblockTerminalSolvers skyblockterminalsolvers4 = (SkyblockTerminalSolvers)((ChildModBinding)this.method7(ModTraits.field16)).method1();
         WorldRenderUtils.highlightBlockFace(bridgeextension_93, this.field11.get(this.field12), skyblockterminalsolvers4.method35(), HorsestatsType_2.WEST);
         if ((Boolean)skyblockterminalsolvers4.method37().get()) {
            ColorOption lightingextension42225 = skyblockterminalsolvers4.method38();

            for (int index6 = this.field12 + 1; index6 < this.field11.size(); index6++) {
               WorldRenderUtils.highlightBlockFace(bridgeextension_93, this.field11.get(index6), lightingextension42225, HorsestatsType_2.WEST);
               lightingextension42225 = skyblockterminalsolvers4.method39();
            }

            bridgeextension_93.pop();
         }
      }
   }

   private void method5(BlockUpdate data1) {
      SkyblockTerminalSolvers skyblockterminalsolvers2 = (SkyblockTerminalSolvers)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      if ((Boolean)skyblockterminalsolvers2.method40().get() && !skyblockterminalsolvers2.method41().isKeyDown()) {
         if (!this.field11.isEmpty()) {
            Vector3i vector3i3 = data1.method1().bridge$toJoml();
            int number4 = vector3i3.x();
            int number5 = vector3i3.y();
            int number6 = vector3i3.z();
            if (number4 == 110 && number5 >= 120 && number5 <= 123 && number6 >= 92 && number6 <= 95 && this.field11.indexOf(vector3i3.add(1, 0, 0)) < this.field12) {
               data1.setCancelled(true);
            }
         }
      }
   }

   private void method6(BlockUpdate data1) {
      if (!data1.isCancelled()) {
         if (!this.field11.isEmpty()) {
            Vector3i vector3i2 = data1.method1().bridge$toJoml();
            int number3 = vector3i2.x();
            int number4 = vector3i2.y();
            int number5 = vector3i2.z();
            if (number3 == 110 && number4 >= 120 && number4 <= 123 && number5 >= 92 && number5 <= 95) {
               this.field12++;
               this.field14 = true;
            }
         }
      }
   }

   private boolean method7(EventBlockChange highlightimpl91) {
      BlockStateBridge bridge2_172 = highlightimpl91.method2();
      Bridge3_23 bridge3_233 = bridge2_172.bridge$getBlock();
      BlockStateBridge bridge2_174 = highlightimpl91.method3();
      Bridge3_23 bridge3_235 = bridge2_174.bridge$getBlock();
      if (bridge3_233 != Bridge.method34().method11()) {
         return false;
      } else if (bridge3_235 != Bridge.method34().method11()) {
         return false;
      } else {
         return ((BlockButtonBridge)bridge3_233).bridge$isPowered(bridge2_172) ? false : ((BlockButtonBridge)bridge3_235).bridge$isPowered(bridge2_174);
      }
   }

   private boolean method8(EventBlockChange highlightimpl91) {
      Bridge3_23 bridge3_232 = highlightimpl91.method3().bridge$getBlock();
      if (bridge3_232 == Bridge.method34().method11()) {
         if (!((BlockButtonBridge)bridge3_232).bridge$isPowered(highlightimpl91.method3())) {
            return true;
         }
      } else if (bridge3_232 != Bridge.method34().method10() && bridge3_232 != Bridge.method34().method3()) {
         return false;
      }

      return true;
   }

   private void reset() {
      this.field11.clear();
      this.field12 = 0;
      this.field14 = false;
   }
}
