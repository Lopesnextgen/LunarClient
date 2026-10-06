package com.moonsworth.lunar.client.mod.skyblock.galateamobhighlight;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge5_18;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityItemRenderStateProvider;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.particle.ParticleType;
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
import com.moonsworth.lunar.client.framework.feature.mod.holograms.HologramEntityListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.EntitySubscription;
import com.moonsworth.lunar.client.event.entity.EventEntityRemove;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashSet;
import org.joml.Vector3i;

public class SkyblockGalateaMobHighlight extends AbstractFeature {
   private final HologramEntityListener field8 = (HologramEntityListener)this.method63(HologramEntityListener.class);
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "skyblockHighlightHideonleafs"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field10 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "skyblockHideonleafHighlightColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(2130706687))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "skyblockHighlightInvisibugs"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field12 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("skyblockInvisibugsColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(2130771712))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("skyblockInvisibugShowText")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("skyblockHighlightMudworms")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field15 = (ColorOption)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8("skyblockMudwormColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(2147467467))
      .method31();
   private final EntitySubscription<Bridge5_18> field16 = this.field8.method6().method1(Bridge5_18.class).method6(this, this.field9::get);
   private final HashSet<BridgeExtension> field17 = new HashSet<>();
   private final HashSet<BridgeExtension> field18 = new HashSet<>();
   private final HashSet<BridgeExtension> field19 = new HashSet<>();
   private final HashSet<Vector3i> field20 = new HashSet<>();

   public SkyblockGalateaMobHighlight(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.FORAGING));
      this.method2(
         ModTraits.field19,
         DynamicCondition.method1(
            this,
            () -> IslandUtils.getIsland() == SkyblockIsland.GALATEA || IslandUtils.getIsland() == SkyblockIsland.TORRHUS_CANYON || IslandUtils.getIsland() == SkyblockIsland.SAFARI
         )
      );
      this.method51(this::onDisable);
      this.handle(EventEntitySpawn.class, this::method1);
      this.handle(EventEntityRemove.class, this::method2);
      this.handle(EventSpawnParticle.class, this::method3);
      this.handle(EventTick.class, this::method4);
      this.handle(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent.class, arg1x -> {
         this.method5(arg1x);
         this.method6(arg1x);
         this.method7(arg1x);
      });
      this.handle(EventWorldChange.class, this::method8);
   }

   private void onDisable() {
      this.method8(null);
   }

   private void method1(EventEntitySpawn highlightimpl6_21) {
      if ((Boolean)this.field14.get()) {
         BridgeExtension bridgeextension2 = highlightimpl6_21.field1;
         if (bridgeextension2 instanceof EntityItemRenderStateProvider bridgeextension2_93) {
            ItemStackBridge bridgeextension_44 = bridgeextension2_93.bridge$getItemStack();
            if (bridgeextension_44 == null || bridgeextension_44.bridge$isEmpty()) {
               return;
            }

            if (bridgeextension_44.bridge$getItem() != Bridge.method28().method43()) {
               return;
            }

            this.field20.add(bridgeextension2.bridge$getBlockPos().bridge$toJoml());
         }
      }
   }

   private void method2(EventEntityRemove highlightimpl121) {
      BridgeExtension bridgeextension2 = highlightimpl121.method1();
      if (bridgeextension2 instanceof EntityItemRenderStateProvider) {
         this.field20.remove(bridgeextension2.bridge$getBlockPos().bridge$toJoml());
      } else if (bridgeextension2 instanceof EntityArmorStandBridge) {
         this.field17.remove(bridgeextension2);
         this.field18.remove(bridgeextension2);
      }
   }

   private void method3(EventSpawnParticle highlightimpl151) {
      if ((Boolean)this.field11.get()) {
         if (IslandUtils.getIsland() == SkyblockIsland.GALATEA) {
            if (highlightimpl151.method2() == ParticleType.CRIT) {
               double value2 = highlightimpl151.getPosX();
               double value4 = highlightimpl151.getPosY();
               double value6 = highlightimpl151.getPosZ();
               AxisAlignedBBBridge horsestats128 = AxisAlignedBBBridge.method2(value2, value4, value6, value2, value4, value6);

               for (BridgeExtension bridgeextension11 : Ref.method8()
                  .bridge$getEntities(horsestats128.method12(0.5, 1.0, 0.5), arg0 -> arg0 instanceof EntityArmorStandBridge)) {
                  if (!this.field19.contains(bridgeextension11) && !this.field18.contains(bridgeextension11)) {
                     EntityArmorStandBridge bridgeextension_212 = (EntityArmorStandBridge)bridgeextension11;
                     if (bridgeextension_212.bridge$getCustomName() == null
                        && this.method9(bridgeextension_212.bridge$getHelmet())
                        && this.method9(bridgeextension_212.bridge$getChestplate())
                        && this.method9(bridgeextension_212.bridge$getLeggings())
                        && this.method9(bridgeextension_212.bridge$getBoots())) {
                        this.field17.add(bridgeextension11);
                        return;
                     }
                  }
               }
            }
         }
      }
   }

   private void method4(EventTick highlightimpl21) {
      if ((Boolean)this.field11.get()) {
         if (IslandUtils.getIsland() == SkyblockIsland.GALATEA) {
            if (!this.field17.isEmpty()) {
               this.field18.removeIf(arg1x -> {
                  if (this.method10(arg1x) && arg1x.bridge$getPosY() != arg1x.bridge$lastTickY()) {
                     this.field19.add(arg1x);
                     return true;
                  } else {
                     return false;
                  }
               });
               this.field17.removeIf(arg1x -> {
                  if (this.method10(arg1x)) {
                     if (arg1x.bridge$getPosY() == arg1x.bridge$lastTickY()) {
                        this.field18.add(arg1x);
                     } else {
                        this.field19.add(arg1x);
                     }

                     return true;
                  } else {
                     return false;
                  }
               });
            }
         }
      }
   }

   private void method5(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent highlightimpl21) {
      if ((Boolean)this.field9.get()) {
         if (!this.field16.isEmpty()) {
            AbstractRenderContext bridgeextension_92 = highlightimpl21.method3();
            EntityRenderDispatcherBridge bridge2_433 = Ref.method13();
            bridgeextension_92.push();
            bridgeextension_92.translate(-bridge2_433.bridge$renderPosX(), -bridge2_433.bridge$renderPosY(), -bridge2_433.bridge$renderPosZ());
            DrawBufferBridge bridge2_324 = bridgeextension_92.method10(LunarRenderTypes.field15);
            bridge2_324.method1();

            for (BridgeExtension bridgeextension6 : this.field16) {
               AxisAlignedBBBridge horsestats127 = bridgeextension6.method11(highlightimpl21.method5());
               WorldRenderUtils.fillBox(
                  bridge2_324,
                  horsestats127.bridge$getMinX() - 0.01,
                  horsestats127.bridge$getMinY() - 0.01,
                  horsestats127.bridge$getMinZ() - 0.01,
                  horsestats127.bridge$getMaxX() + 0.01,
                  horsestats127.bridge$getMaxY() + 0.01,
                  horsestats127.bridge$getMaxZ() + 0.01,
                  this.field10.method1(0.0F)
               );
            }

            bridge2_324.method17(BufferMode.BATCHED);
            Skyblock skyblock10 = (Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1();
            BufferBuilderBridge bridge_2811 = bridgeextension_92.method11((Float)skyblock10.method19().get());

            for (BridgeExtension bridgeextension8 : this.field16) {
               AxisAlignedBBBridge horsestats129 = bridgeextension8.method11(highlightimpl21.method5());
               WorldRenderUtils.drawBoxOutline(
                  bridge_2811,
                  horsestats129.bridge$getMinX() - 0.01,
                  horsestats129.bridge$getMinY() - 0.01,
                  horsestats129.bridge$getMinZ() - 0.01,
                  horsestats129.bridge$getMaxX() + 0.01,
                  horsestats129.bridge$getMaxY() + 0.01,
                  horsestats129.bridge$getMaxZ() + 0.01,
                  ColorUtils.method31(this.field10.method1(0.0F))
               );
            }

            bridge_2811.end();
            bridgeextension_92.pop();
         }
      }
   }

   private void method6(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent highlightimpl21) {
      if ((Boolean)this.field11.get()) {
         if (IslandUtils.getIsland() == SkyblockIsland.GALATEA) {
            if (!this.field18.isEmpty()) {
               float value2 = highlightimpl21.method5();
               AbstractRenderContext bridgeextension_93 = highlightimpl21.method3();
               EntityRenderDispatcherBridge bridge2_434 = Ref.method13();
               bridgeextension_93.push();
               bridgeextension_93.translate(-bridge2_434.bridge$renderPosX(), -bridge2_434.bridge$renderPosY(), -bridge2_434.bridge$renderPosZ());
               DrawBufferBridge bridge2_325 = bridgeextension_93.method10(LunarRenderTypes.field15);
               bridge2_325.method1();

               for (BridgeExtension bridgeextension7 : this.field18) {
                  double value8 = bridgeextension7.method8(value2);
                  double value10 = bridgeextension7.method9(value2);
                  double value12 = bridgeextension7.method10(value2);
                  WorldRenderUtils.fillBox(bridge2_325, value8 - 0.5, value10 + 0.3, value12 - 0.5, value8 + 0.5, value10 + 1.3, value12 + 0.5, this.field12.method1(0.0F));
               }

               bridge2_325.method17(BufferMode.BATCHED);
               Skyblock skyblock16 = (Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1();
               BufferBuilderBridge bridge_2817 = bridgeextension_93.method11((Float)skyblock16.method19().get());

               for (BridgeExtension bridgeextension9 : this.field18) {
                  double value21 = bridgeextension9.method8(value2);
                  double value23 = bridgeextension9.method9(value2);
                  double value14 = bridgeextension9.method10(value2);
                  WorldRenderUtils.drawBoxOutline(bridge_2817, value21 - 0.5, value23 + 0.3, value14 - 0.5, value21 + 0.5, value23 + 1.3, value14 + 0.5, this.field12.method1(0.0F));
               }

               bridge_2817.end();
               if ((Boolean)this.field13.get()) {
                  for (BridgeExtension bridgeextension20 : this.field18) {
                     double value22 = bridgeextension20.method8(value2);
                     double value24 = bridgeextension20.method9(value2);
                     double value25 = bridgeextension20.method10(value2);
                     WorldRenderUtils.drawString(bridgeextension_93, "Invisibug", value22, value24 + 1.8, value25, -1, true);
                  }
               }

               bridgeextension_93.pop();
            }
         }
      }
   }

   private void method7(com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent highlightimpl21) {
      if ((Boolean)this.field14.get()) {
         if (!this.field20.isEmpty()) {
            AbstractRenderContext bridgeextension_92 = highlightimpl21.method3();
            EntityRenderDispatcherBridge bridge2_433 = Ref.method13();
            bridgeextension_92.push();
            bridgeextension_92.translate(-bridge2_433.bridge$renderPosX(), -bridge2_433.bridge$renderPosY(), -bridge2_433.bridge$renderPosZ());
            DrawBufferBridge bridge2_324 = bridgeextension_92.method10(LunarRenderTypes.field15);
            bridge2_324.method1();

            for (Vector3i vector3i6 : this.field20) {
               int number7 = vector3i6.x();
               int number8 = vector3i6.y();
               int number9 = vector3i6.z();
               WorldRenderUtils.fillBox(bridge2_324, number7 - 0.01, number8 - 0.01, number9 - 0.01, number7 + 1.01, number8 + 1.01, number9 + 1.01, this.field15.method1(0.0F));
            }

            bridge2_324.method17(BufferMode.BATCHED);
            Skyblock skyblock12 = (Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1();
            BufferBuilderBridge bridge_2813 = bridgeextension_92.method11((Float)skyblock12.method19().get());

            for (Vector3i vector3i15 : this.field20) {
               int number16 = vector3i15.x();
               int number10 = vector3i15.y();
               int number11 = vector3i15.z();
               WorldRenderUtils.drawBoxOutline(bridge_2813, number16 - 0.01, number10 - 0.01, number11 - 0.01, number16 + 1.01, number10 + 1.01, number11 + 1.01, this.field15.method1(0.0F));
            }

            bridge_2813.end();
            bridgeextension_92.pop();
         }
      }
   }

   private void method8(EventWorldChange data31) {
      this.field17.clear();
      this.field18.clear();
      this.field19.clear();
      this.field20.clear();
   }

   private boolean method9(ItemStackBridge bridgeextension_41) {
      return bridgeextension_41 == null || bridgeextension_41.bridge$isEmpty();
   }

   private boolean method10(BridgeExtension bridgeextension1) {
      return bridgeextension1.bridge$getPosX() != bridgeextension1.bridge$lastTickX()
         || bridgeextension1.bridge$getPosY() != bridgeextension1.bridge$lastTickY()
         || bridgeextension1.bridge$getPosZ() != bridgeextension1.bridge$lastTickZ();
   }

   public String getId() {
      return "SKYBLOCK_GALATEA_MOB_HIGHLIGHT";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field9, arg1xx -> arg1xx.method9(new ClientOption[]{this.field10}));
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field11, arg1xx -> arg1xx.method9(new ClientOption[]{this.field12, this.field13})
            );
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field14, arg1xx -> arg1xx.method9(new ClientOption[]{this.field15}));
         }
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method2(new String[]{"mudworm highlight"}).method11(this);
   }
}
