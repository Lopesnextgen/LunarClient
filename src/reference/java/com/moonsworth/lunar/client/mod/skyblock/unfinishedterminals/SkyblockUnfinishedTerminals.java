package com.moonsworth.lunar.client.mod.skyblock.unfinishedterminals;

import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.minecraft.MovingObjectPositionBridge;
import com.moonsworth.lunar.bridge.minecraft.MovingObjectTypeBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonFloorListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonScoreListener;
import com.moonsworth.lunar.client.event.entity.EventEntityRemove;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntities;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.text.WordUtils;
import org.joml.Vector3d;
import org.jspecify.annotations.Nullable;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockUnfinishedTerminals extends AbstractFeature {
   private final DungeonFloorListener field8 = (DungeonFloorListener)this.method13(DungeonFloorListener.class);
   private final DungeonScoreListener field9 = (DungeonScoreListener)this.method13(DungeonScoreListener.class);
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockUnfinishedTerminalWaypoints")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field11 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "skyblockUnfinishedTerminalWaypointColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1157562368))
      .method31();
   private final ColorOption field12 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "skyblockUnfinishedTerminalTextColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ToggleOption field13 = (ToggleOption)OptionFactory.method7("skyblockUnfinishedTerminalInteractionBox")
      .method31();
   private final ColorOption field14 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "skyblockUnfinishedTerminalInteractionBoxColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1157562368))
      .method31();
   private final ColorOption field15 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "skyblockUnfinishedTerminalLookedAtBoxColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1140915968))
      .method31();
   private final ObjectOpenHashSet<SkyblockUnfinishedTerminals.Data> field16 = new ObjectOpenHashSet();

   public SkyblockUnfinishedTerminals(Skyblock skyblock1) {
      super(false);
      this.method14(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method14(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method14(
         ModTraits.field19,
         DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON && this.field8.method6().getNumber() == 7 && this.field9.method10())
      );
      this.method51(this::onDisable);
      this.handle(EventEntitySpawn.class, this::method1);
      this.handle(EventEntityRemove.class, this::method2);
      this.handle(HudRenderLegacyEventAlt.class, this::method3);
      this.handle(EventRenderEntities.class, this::method4);
      this.handle(EventWorldChange.class, this::method7);
   }

   private void onDisable() {
      this.field16.clear();
   }

   private void method1(EventEntitySpawn highlightimpl6_21) {
      if (highlightimpl6_21.field1 instanceof EntityArmorStandBridge bridgeextension_22) {
         this.method8(bridgeextension_22);
         Component component7 = bridgeextension_22.bridge$getCustomName();
         if (component7 != null) {
            String text4 = TextBridge.getTextContent(component7);
            if (!text4.equals("CLICK HERE")) {
               SkyblockUnfinishedTerminals.Type type8 = SkyblockUnfinishedTerminals.Type.findByNametag(text4);
               if (type8 != null) {
                  SkyblockUnfinishedTerminals.Data data9 = new SkyblockUnfinishedTerminals.Data(bridgeextension_22, type8);
                  if (type8 == SkyblockUnfinishedTerminals.Type.TERMINAL) {
                     data9.method2((BridgeExtension)highlightimpl6_21.field2.bridge$getEntities(bridgeextension_22.bridge$getBoundingBox(), arg1x -> {
                        if (arg1x instanceof EntityArmorStandBridge && data9.method1(arg1x)) {
                           Component component2x = arg1x.bridge$getCustomName();
                           return component2x != null && TextBridge.getTextContent(component2x).equals("CLICK HERE");
                        } else {
                           return false;
                        }
                     }).stream().findFirst().orElse(null));
                  }

                  this.field16.add(data9);
               }
            } else {
               ObjectIterator objectiterator5 = this.field16.iterator();

               while (objectiterator5.hasNext()) {
                  SkyblockUnfinishedTerminals.Data data6 = (SkyblockUnfinishedTerminals.Data)objectiterator5.next();
                  if (data6.method1(bridgeextension_22)) {
                     data6.method2(bridgeextension_22);
                     break;
                  }
               }
            }
         }
      }
   }

   private void method2(EventEntityRemove highlightimpl121) {
      if (highlightimpl121.method1() instanceof EntityArmorStandBridge bridgeextension_22) {
         this.method8(bridgeextension_22);
      }
   }

   private void method3(HudRenderLegacyEventAlt highlightimpl41) {
      if ((Boolean)this.field10.get() && !this.field16.isEmpty()) {
         AbstractRenderContext bridgeextension_92 = highlightimpl41.method3();
         bridgeextension_92.push();
         EntityRenderDispatcherBridge bridge2_433 = Ref.method13();
         bridgeextension_92.translate(-bridge2_433.bridge$renderPosX(), -bridge2_433.bridge$renderPosY(), -bridge2_433.bridge$renderPosZ());
         this.method6(bridgeextension_92);
         bridgeextension_92.pop();
      }
   }

   private void method4(EventRenderEntities highlightimpl241) {
      if ((Boolean)this.field13.get() && !this.field16.isEmpty()) {
         AbstractRenderContext bridgeextension_92 = highlightimpl241.method1();
         float value3 = bridgeextension_92.method28();
         bridgeextension_92.push();
         bridgeextension_92.translate(-highlightimpl241.getX(), -highlightimpl241.getY(), -highlightimpl241.getZ());
         ObjectIterator objectiterator4 = this.field16.iterator();

         while (objectiterator4.hasNext()) {
            SkyblockUnfinishedTerminals.Data data5 = (SkyblockUnfinishedTerminals.Data)objectiterator4.next();
            if (data5.method7() == SkyblockUnfinishedTerminals.Type.TERMINAL) {
               WorldRenderUtils.drawFancyBox(bridgeextension_92, data5.method3(value3), this.method5(data5) ? this.field15.method14(0.0F) : this.field14.method14(0.0F), false);
            }
         }

         bridgeextension_92.pop();
      }
   }

   private boolean method5(SkyblockUnfinishedTerminals.Data data1) {
      MovingObjectPositionBridge horsestats212 = Ref.method3().bridge$getObjectMouseOver();
      if (horsestats212 != null && horsestats212.bridge$isTypeOfHit(MovingObjectTypeBridge.ENTITY)) {
         BridgeExtension bridgeextension3 = horsestats212.bridge$getEntityHit();
         return bridgeextension3.equals(data1.method6()) || bridgeextension3.equals(data1.method8());
      } else {
         return false;
      }
   }

   private void method6(AbstractRenderContext bridgeextension_91) {
      DrawBufferBridge bridge2_322 = bridgeextension_91.method10(LunarRenderTypes.field52);
      bridge2_322.method1();
      ObjectIterator objectiterator3 = this.field16.iterator();

      while (objectiterator3.hasNext()) {
         SkyblockUnfinishedTerminals.Data data4 = (SkyblockUnfinishedTerminals.Data)objectiterator3.next();
         WorldRenderUtils.fillBox(bridge2_322, data4.method5(), this.field11.method1(0.0F));
      }

      bridge2_322.method17(BufferMode.IMMEDIATE);
      Skyblock skyblock8 = (Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      BufferBuilderBridge bridge_289 = bridgeextension_91.method11((Float)skyblock8.method19().get());
      ObjectIterator objectiterator5 = this.field16.iterator();

      while (objectiterator5.hasNext()) {
         SkyblockUnfinishedTerminals.Data data6 = (SkyblockUnfinishedTerminals.Data)objectiterator5.next();
         WorldRenderUtils.drawBoxOutline(bridge_289, data6.method5(), ColorUtils.method31(this.field11.method1(0.0F)));
      }

      bridge_289.end();
      objectiterator5 = this.field16.iterator();

      while (objectiterator5.hasNext()) {
         SkyblockUnfinishedTerminals.Data data11 = (SkyblockUnfinishedTerminals.Data)objectiterator5.next();
         Vector3d vector3d7 = data11.method4();
         WorldRenderUtils.drawString(bridgeextension_91, data11.method7().getDisplayText(), vector3d7.x(), vector3d7.y() + 0.5, vector3d7.z(), this.field12.method14(0.0F), true);
      }
   }

   private void method7(EventWorldChange data31) {
      this.onDisable();
   }

   private void method8(BridgeExtension bridgeextension1) {
      this.field16.removeIf(arg1x -> arg1x.method6().equals(bridgeextension1));
      ObjectIterator objectiterator2 = this.field16.iterator();

      while (objectiterator2.hasNext()) {
         SkyblockUnfinishedTerminals.Data data3 = (SkyblockUnfinishedTerminals.Data)objectiterator2.next();
         if (bridgeextension1.equals(data3.method8())) {
            data3.method2(null);
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_UNFINISHED_TERMINALS";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field10, arg1xx -> arg1xx.method9(new ClientOption[]{this.field11, this.field12})
            );
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field13, arg1xx -> arg1xx.method9(new ClientOption[]{this.field14, this.field15})
            );
         }
      );
   }

   private static class Data {
      private final BridgeExtension field1;
      private final SkyblockUnfinishedTerminals.Type field2;
      private @Nullable BridgeExtension field3;

      public boolean method1(BridgeExtension bridgeextension1) {
         return this.field2 == SkyblockUnfinishedTerminals.Type.TERMINAL
            && this.field1.bridge$getPosX() == bridgeextension1.bridge$getPosX()
            && this.field1.bridge$getPosZ() == bridgeextension1.bridge$getPosZ()
            && this.field1.bridge$getBoundingBox().bridge$intersectsWith(bridgeextension1.bridge$getBoundingBox());
      }

      public void method2(@Nullable BridgeExtension bridgeextension1) {
         this.field3 = bridgeextension1;
      }

      public AxisAlignedBBBridge method3(float value1) {
         AxisAlignedBBBridge horsestats122 = this.field1.method11(value1);
         return this.field3 == null ? horsestats122 : horsestats122.bridge$union(this.field3.method11(value1));
      }

      public Vector3d method4() {
         return new Vector3d(this.field1.bridge$getPosX(), this.field1.bridge$getPosY() + this.field1.bridge$getHeight() + 0.5, this.field1.bridge$getPosZ());
      }

      public AxisAlignedBBBridge method5() {
         Vector3d vector3d1 = this.method4();
         int number2 = (int)Math.floor(vector3d1.x());
         int number3 = (int)Math.floor(vector3d1.y());
         int number4 = (int)Math.floor(vector3d1.z());
         return AxisAlignedBBBridge.method2(number2 - 0.01, number3 - 2.01, number4 - 0.01, number2 + 1.01, number3 - 1.01, number4 + 1.01);
      }

      @Generated
      public Data(BridgeExtension bridgeextension1, SkyblockUnfinishedTerminals.Type type2) {
         this.field1 = bridgeextension1;
         this.field2 = type2;
      }

      @Generated
      public BridgeExtension method6() {
         return this.field1;
      }

      @Generated
      public SkyblockUnfinishedTerminals.Type method7() {
         return this.field2;
      }

      @Generated
      public @Nullable BridgeExtension method8() {
         return this.field3;
      }
   }

   private enum Type {
      TERMINAL("Inactive Terminal"),
      DEVICE("Inactive"),
      LEVER("Not Activated");

      private final String nametagText;
      private final String displayText;

      Type(String text3) {
         this.nametagText = text3;
         this.displayText = WordUtils.capitalizeFully(this.name());
      }

      public static SkyblockUnfinishedTerminals.Type findByNametag(String text0) {
         if (text0 == null) {
            return null;
         }

         for (SkyblockUnfinishedTerminals.Type type4 : values()) {
            if (text0.endsWith(type4.nametagText)) {
               return type4;
            }
         }

         return null;
      }

      @Generated
      public String getDisplayText() {
         return this.displayText;
      }
   }
}
