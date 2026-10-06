package com.moonsworth.lunar.client.mod.skyblock.keyguardiantimer;

import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.RollingWindow;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.entity.EventEntityJoinWorld;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldLoad;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.text.TimeFormatting.Type;
import java.util.ArrayList;
import java.util.List;
import org.joml.Vector3i;

public class SkyblockKeyGuardianTimer extends AbstractFeature {
   private final List<SkyblockKeyGuardianTimer.Data> field8 = new ArrayList<>();
   private final RollingWindow<EntityArmorStandBridge> field9 = new RollingWindow(1);

   public SkyblockKeyGuardianTimer(Skyblock skyblock1) {
      super(true);
      this.method7(ModTraits.field16, ChildModBinding.method4(true, skyblock1));
      this.method7(ModTraits.field17, ModCategories.method2(SettingsPage.MINING));
      this.method7(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.CRYSTAL_HOLLOWS));
      this.handle(EventEntityJoinWorld.class, this::method1);
      this.handle(EventTick.class, this::method2);
      this.handle(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent.class, this::method3);
      this.handle(EventWorldLoad.class, this::method5);
      this.handle(EventSecond.class, this::method6);
      this.method51(this::onDisable);
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_KEY_GUARDIAN_TIMER";
   }

   private void method1(EventEntityJoinWorld highlightimpl201) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null && highlightimpl201.field1 instanceof EntityArmorStandBridge bridgeextension_23 && !(highlightimpl201.field1.method13(bridge5extension_52) > 225.0)) {
         this.field9.add(bridgeextension_23);
      }
   }

   private void method2(EventTick highlightimpl21) {
      for (EntityArmorStandBridge bridgeextension_23 : this.field9.advance()) {
         if (bridgeextension_23.bridge$getCustomName() == null
            || !ChatFormatting.getTextWithoutFormattingCodes(TextBridge.getTextContentForRendering(bridgeextension_23.bridge$getCustomName())).contains("Key Guardian")) {
            return;
         }

         this.method4(new Vector3i((int)bridgeextension_23.bridge$getPosX(), (int)bridgeextension_23.bridge$getPosY(), (int)bridgeextension_23.bridge$getPosZ())).method1();
      }
   }

   private void method3(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent highlightimpl21) {
      AbstractRenderContext bridgeextension_92 = highlightimpl21.method3();
      EntityRenderDispatcherBridge bridge2_433 = Ref.method13();
      bridgeextension_92.push();
      bridgeextension_92.translate(-bridge2_433.bridge$renderPosX(), -bridge2_433.bridge$renderPosY(), -bridge2_433.bridge$renderPosZ());
      this.field8.forEach(arg1x -> arg1x.method4(highlightimpl21.method3()));
      bridgeextension_92.pop();
   }

   private SkyblockKeyGuardianTimer.Data method4(Vector3i vector3i1) {
      for (SkyblockKeyGuardianTimer.Data data3 : this.field8) {
         if (data3.method6(vector3i1)) {
            data3.method7(vector3i1);
            return data3;
         }
      }

      SkyblockKeyGuardianTimer.Data data4 = new SkyblockKeyGuardianTimer.Data(vector3i1);
      this.field8.add(data4);
      return data4;
   }

   private void method5(EventWorldLoad data41) {
      this.onDisable();
   }

   private void onDisable() {
      this.field9.clear();
      this.field8.clear();
   }

   private void method6(EventSecond highlightimpl41) {
      long number2 = System.currentTimeMillis();
      this.field8.removeIf(arg2x -> arg2x.method3(number2));
   }

   private static class Data {
      private Vector3i field1;
      private long field2;

      public Data(Vector3i vector3i1) {
         this.field1 = vector3i1;
      }

      public void method1() {
         if (System.currentTimeMillis() - this.field2 >= 10000L) {
            this.field2 = System.currentTimeMillis();
         }
      }

      private long method2() {
         return this.field2 + 110000L;
      }

      public boolean method3(long number1) {
         return this.field2 + 300000L < number1;
      }

      public void method4(AbstractRenderContext bridgeextension_91) {
         WorldRenderUtils.drawString(bridgeextension_91, this.method5(this.method2()), this.field1.x() + 0.5, this.field1.y(), this.field1.z() + 0.5, -1, true);
      }

      private String method5(long number1) {
         long number3 = System.currentTimeMillis();
         return number3 > number1 ? ChatFormatting.GREEN + "READY! (Approx)" : ChatFormatting.YELLOW + Type.EASY_DYNAMIC_1.format(number1 - number3);
      }

      public boolean method6(Vector3i vector3i1) {
         return this.field1.distanceSquared(vector3i1) < 100L;
      }

      public void method7(Vector3i vector3i1) {
         this.field1 = vector3i1;
      }
   }
}
