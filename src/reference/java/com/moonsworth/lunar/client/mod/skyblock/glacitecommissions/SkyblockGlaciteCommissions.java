package com.moonsworth.lunar.client.mod.skyblock.glacitecommissions;

import com.google.common.collect.Lists;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.GlaciteTunnelGraph;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockChat;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockCommission;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockCommission.Type;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.FishingType;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.CommissionListener;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.CommissionEvent.CommissionStartEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.collection.MapRemoval;
import com.moonsworth.lunar.client.util.math.WeightedValue;
import com.moonsworth.lunar.client.util.raytrace.Ray;
import com.moonsworth.lunar.client.util.raytrace.Raycaster;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;
import java.util.function.Predicate;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.apache.commons.lang3.mutable.MutableInt;
import org.joml.Vector3i;
import toxi.geom.Line3D;
import toxi.geom.Vec3D;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockGlaciteCommissions extends AbstractFeature {
   private final CommissionListener field8 = (CommissionListener)this.method63(CommissionListener.class);
   private final SimpleKeybindOption field9 = (SimpleKeybindOption)((Data)((Data)OptionFactory.method17("skipPositionKeyBind")
            .method18(this))
         .method2(KeyCode.KEY_G))
      .method31();
   private final FloatOption field10 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "glaciteCommissionLineThickness"
            )
            .method4(4.0F))
         .method8(1.0F, 10.0F))
      .method31();
   private final Map<Fishing2, Long> field11 = new HashMap<>();
   private final AxisAlignedBBBridge field12 = AxisAlignedBBBridge.method2(-124.0, 0.0, 190.0, 123.0, 256.0, 461.0);
   private boolean field13;
   private List<Fishing2> field14 = new ArrayList<>();
   private SkyBlockCommission field15;

   public SkyblockGlaciteCommissions(Skyblock skyblock1) {
      super(false);
      this.method16(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method16(ModTraits.field17, ModCategories.method2(SettingsPage.MINING));
      this.method16(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method2(this::onDisable);
      this.handle(HudRenderLegacyEvent.class, this::method10);
      this.handle(HudRenderLegacyEventAlt.class, this::method9);
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventTick.class, this::method8);
      this.handle(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.CommissionEvent.Data.class, this::method6);
      this.handle(CommissionStartEvent.class, this::method5);
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventSecond.class, this::method2);
      this.handle(EventWorldChange.class, this::method1);
      this.field9.method3(this::method14);
   }

   private void onDisable() {
      this.method1(null);
   }

   private void method1(EventWorldChange data31) {
      this.field15 = null;
      this.field14 = new ArrayList<>();
      this.field13 = false;
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.fishing.EventSecond highlightimpl41) {
      MapRemoval.method2(this.field11, arg0 -> arg0 > Ref.method3().bridge$getSystemTime() + 60000L);
   }

   public void method3(boolean flag1) {
      if (flag1) {
         this.method13();
      }
   }

   private void method13() {
      GlaciteTunnelGraph fishing1 = Ref.method4().method40().method82().method15().method10();
      if (fishing1 != null) {
         for (SkyBlockCommission fishing33 : this.field8.method8()) {
            if (fishing33.method2() == Type.COLLECT && fishing33.method4() != null) {
               this.field15 = fishing33;
               this.field14 = this.method11(fishing33.method4(), arg1x -> !this.field11.containsKey(arg1x), fishing1);
               return;
            }
         }
      }
   }

   private void method5(CommissionStartEvent data21) {
      GlaciteTunnelGraph fishing2 = Ref.method4().method40().method82().method15().method10();
      if (fishing2 != null) {
         if (this.field15 == null) {
            SkyBlockCommission fishing33 = data21.method1();
            if (fishing33.method2() == Type.COLLECT) {
               if (fishing33.method4() != null) {
                  this.field15 = fishing33;
                  this.field14 = this.method11(fishing33.method4(), arg1x -> !this.field11.containsKey(arg1x), fishing2);
               }
            }
         }
      }
   }

   private void method6(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.CommissionEvent.Data data1) {
      GlaciteTunnelGraph fishing2 = Ref.method4().method40().method82().method15().method10();
      if (fishing2 != null) {
         if (data1.method1().equals(this.field15)) {
            this.field15 = null;

            for (SkyBlockCommission fishing34 : this.field8.method8()) {
               if (fishing34.method2() == Type.COLLECT && fishing34.method4() != null) {
                  this.field15 = fishing34;
                  this.field14 = this.method11(fishing34.method4(), arg1x -> !this.field11.containsKey(arg1x), fishing2);
               }
            }
         }
      }
   }

   private void method14() {
      GlaciteTunnelGraph fishing1 = Ref.method4().method40().method82().method15().method10();
      if (fishing1 != null && this.field15 != null && !this.field14.isEmpty()) {
         SkyBlockChat.method1(this.method20("skip", new Object[0]));
         Fishing2 fishing22 = this.field14.get(this.field14.size() - 1);
         this.field11.put(fishing22, Ref.method3().bridge$getSystemTime());

         for (Fishing2 fishing24 : fishing1.method4().getNodes()) {
            if (fishing22.method5() == fishing24.method5()) {
               Ray sextension5 = Ray.method9(Raycaster.field4)
                  .method6(fishing22.method4(), fishing24.method4())
                  .method14((arg0, arg1x) -> !arg1x.bridge$isAir())
                  .method18();
               ((MissResult)sextension5.method8(Ref.method8()))
                  .method7(arg0 -> {}, () -> this.field11.put(fishing24, Ref.method3().bridge$getSystemTime()));
            }
         }

         this.field14 = this.method11(this.field15.method4(), arg1x -> !this.field11.containsKey(arg1x), fishing1);
      }
   }

   private void method8(com.moonsworth.lunar.client.event.mixin.fishing.EventTick highlightimpl21) {
      if (this.field14.size() > 1) {
         Bridge5Extension_5 bridge5extension_52 = Ref.method7();
         if (bridge5extension_52 != null) {
            boolean flag3 = this.field12.method10(bridge5extension_52.bridge$getPosX(), bridge5extension_52.bridge$getPosY(), bridge5extension_52.bridge$getPosZ());
            if (this.field13 && flag3) {
               this.method13();
               this.field13 = false;
            } else if (!flag3) {
               this.field14 = new ArrayList<>();
               this.field15 = null;
               this.field13 = true;
            } else {
               MutableInt mutableint4 = new MutableInt(-1);

               for (int index5 = 0; index5 < this.field14.size() - 1; index5++) {
                  Vec3iBridge horsestats206 = this.field14.get(index5).method4();
                  Vec3iBridge horsestats207 = this.field14.get(index5 + 1).method4();
                  float value8 = (float)bridge5extension_52.method15(horsestats206.bridge$getX(), horsestats206.bridge$getY(), horsestats206.bridge$getZ());
                  if (!(value8 > 100.0F)) {
                     if (value8 < 10.0F) {
                        mutableint4.setValue(index5);
                     } else {
                        Ray sextension9 = Ray.method9(Raycaster.field4)
                           .method6(bridge5extension_52.bridge$getBlockPos().bridge$add(new Vector3i(0, 2, 0)), horsestats207)
                           .method14((arg0, arg1x) -> !arg1x.bridge$isAir())
                           .method18();
                        int number10 = index5;
                        ((MissResult)sextension9.method8(Ref.method8())).method7(arg0 -> {}, () -> mutableint4.setValue(number10));
                     }
                  }
               }

               if (mutableint4.getValue() != -1) {
                  this.field14 = this.field14.subList(mutableint4.getValue() + 1, this.field14.size());
               }
            }
         }
      }
   }

   private void method9(HudRenderLegacyEventAlt highlightimpl41) {
      if (this.shouldRender()) {
         Bridge5Extension_5 bridge5extension_52 = Ref.method7();
         if (bridge5extension_52 != null) {
            if (this.field14.size() > 1) {
               Vec3iBridge horsestats203 = this.field14.get(0).method4();
               Vec3D vec3d4 = new Vec3D((float)(horsestats203.bridge$getX() + 0.5), (float)(horsestats203.bridge$getY() + 0.5), (float)(horsestats203.bridge$getZ() + 0.5));
               FishingType fishingtype5 = this.field15.method4();
               WorldRenderUtils.drawLineFromCamera(vec3d4, highlightimpl41.method3(), fishingtype5.getColor(), (Float)this.field10.get());
            }
         }
      }
   }

   private void method10(HudRenderLegacyEvent highlightimpl21) {
      if (this.shouldRender()) {
         HashSet set2 = new HashSet();

         for (int index3 = 0; index3 < this.field14.size(); index3++) {
            Vec3iBridge horsestats204 = this.field14.get(index3).method4();
            if (index3 + 1 < this.field14.size()) {
               Vec3iBridge horsestats205 = this.field14.get(index3 + 1).method4();
               Vec3D vec3d6 = new Vec3D((float)(horsestats204.bridge$getX() + 0.5), (float)(horsestats204.bridge$getY() + 0.5), (float)(horsestats204.bridge$getZ() + 0.5));
               Vec3D vec3d7 = new Vec3D((float)(horsestats205.bridge$getX() + 0.5), (float)(horsestats205.bridge$getY() + 0.5), (float)(horsestats205.bridge$getZ() + 0.5));
               set2.add(new Line3D(vec3d6, vec3d7));
            }
         }

         FishingType fishingtype8 = this.field15.method4();
         if (fishingtype8 == null) {
            CrashReporter.method5(new IllegalStateException("null resource value for destination"), "SkyblockGlaciteCommissionHelper.render");
         } else {
            EntityRenderDispatcherBridge bridge2_439 = Ref.method13();
            AbstractRenderContext bridgeextension_910 = highlightimpl21.method3();
            bridgeextension_910.push();
            bridgeextension_910.translate(-bridge2_439.bridge$renderPosX(), -bridge2_439.bridge$renderPosY(), -bridge2_439.bridge$renderPosZ());
            WorldRenderUtils.renderThickLines(bridgeextension_910, set2, fishingtype8.getColor(), (Float)this.field10.get(), false);
            Vec3iBridge horsestats2011 = this.field14.get(this.field14.size() - 1).method4();
            WorldRenderUtils.drawBoxAtCoordinate(bridgeextension_910, horsestats2011.bridge$toJoml(), 570490624);
            WorldRenderUtils.drawComponent(
               bridgeextension_910,
               Component.text(fishingtype8.getId()).color(TextColor.color(fishingtype8.getColor())),
               horsestats2011.bridge$getX() + 0.5,
               horsestats2011.bridge$getY() + 1.5,
               horsestats2011.bridge$getZ() + 0.5,
               true
            );
            bridgeextension_910.pop();
         }
      }
   }

   private boolean shouldRender() {
      return !IslandUtils.getIsland().containsPowderSources() ? false : this.field15 != null && !this.field14.isEmpty();
   }

   private List<Fishing2> method11(FishingType fishingtype1, Predicate<Fishing2> predicate2, GlaciteTunnelGraph fishing3) {
      Fishing2 fishing24 = this.method15();
      Map map5 = this.method12(fishing24, fishing3);
      Fishing2 fishing26 = null;
      float value7 = Float.MAX_VALUE;

      for (Entry entry9 : map5.entrySet()) {
         Fishing2 fishing210 = (Fishing2)entry9.getKey();
         if (fishing210.method5() == fishingtype1 && predicate2.test(fishing210)) {
            float value11 = ((com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing2.Data2)entry9.getValue()).method3();
            if (value11 < value7) {
               fishing26 = fishing210;
               value7 = value11;
            }
         }
      }

      if (fishing26 == null) {
         SkyBlockChat.method1(this.method20("outOfNodes", new Object[0]));
         return List.of();
      }

      ArrayList list12 = new ArrayList();

      for (Fishing2 fishing213 = fishing26;
         !fishing213.equals(fishing24);
         fishing213 = ((com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing2.Data2)map5.get(fishing213)).method4()
      ) {
         list12.add(fishing213);
      }

      return Lists.reverse(list12);
   }

   private Map<Fishing2, com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing2.Data2> method12(Fishing2 fishing21, GlaciteTunnelGraph fishing2) {
      HashMap map3 = new HashMap();

      for (Fishing2 fishing25 : fishing2.method4().getNodes()) {
         map3.put(fishing25, new com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing2.Data2(Float.MAX_VALUE, null));
      }

      map3.put(fishing21, new com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing2.Data2(0.0F, null));
      PriorityQueue priorityqueue15 = new PriorityQueue();
      HashMap map16 = new HashMap();

      for (Fishing2 fishing27 : fishing2.method4().getNodes()) {
         WeightedValue threadmoduledump848 = new WeightedValue(
            fishing27, ((com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing2.Data2)map3.get(fishing27)).method3()
         );
         priorityqueue15.add(threadmoduledump848);
         map16.put(fishing27, threadmoduledump848);
      }

      while (!priorityqueue15.isEmpty()) {
         WeightedValue threadmoduledump8417 = (WeightedValue)priorityqueue15.poll();
         Fishing2 fishing218 = (Fishing2)threadmoduledump8417.getValue();
         float value19 = threadmoduledump8417.value();
         map16.remove(fishing218);

         for (Fishing2 fishing210 : fishing2.method4().getConnectedNodesFor(fishing218)) {
            float value11 = ((com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing2.Data2)map3.get(fishing210)).method3();
            float value12 = fishing2.method1(fishing218, fishing210);
            float value13 = value19 + value12;
            if (!(value13 >= value11)) {
               map3.put(fishing210, new com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing2.Data2(value13, fishing218));
               priorityqueue15.remove(map16.get(fishing210));
               WeightedValue threadmoduledump8414 = new WeightedValue(fishing210, value13);
               priorityqueue15.add(threadmoduledump8414);
               map16.put(fishing210, threadmoduledump8414);
            }
         }
      }

      return map3;
   }

   private Fishing2 method15() {
      GlaciteTunnelGraph fishing1 = Ref.method4().method40().method82().method15().method10();
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (fishing1 != null && bridge5extension_52 != null) {
         double value3 = Double.MAX_VALUE;
         Fishing2 fishing25 = null;

         for (Fishing2 fishing27 : fishing1.method4().getNodes()) {
            double value8 = bridge5extension_52.method15(fishing27.method4().bridge$getX(), fishing27.method4().bridge$getY(), fishing27.method4().bridge$getZ());
            if (value8 < value3) {
               value3 = value8;
               fishing25 = fishing27;
            }
         }

         return fishing25;
      } else {
         return null;
      }
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_GLACITE_COMMISSIONS";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field9, this.field10});
   }
}
