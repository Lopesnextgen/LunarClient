package com.moonsworth.lunar.client.mod.skyblock.collectedsecret;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.BlocksBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.tileentity.TileEntityChestBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.SecretType;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockChange;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import org.joml.Vector3i;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockCollectedSecret extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockSecretCollectedChime")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockLeverChime").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field10 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockHighlightOpenedChests")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field11 = (ColorOption)((Data)OptionFactory.method8("skyblockOpenedChestColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1140915968))
      .method31();
   private final ColorOption field12 = (ColorOption)((Data)OptionFactory.method8("skyblockLockedChestColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1157562368))
      .method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockHighlightFlickedLevers")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field14 = (ColorOption)((Data)OptionFactory.method8("skyblockFlickedLeverColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1140850943))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockSecretCollectedThroughWalls")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final Cache<Vector3i, Boolean> field16 = CacheBuilder.newBuilder().expireAfterWrite(1L, TimeUnit.SECONDS).build();
   private final Cache<Vector3i, Boolean> field17 = CacheBuilder.newBuilder().expireAfterWrite(1L, TimeUnit.SECONDS).build();
   private final Cache<Vector3i, Boolean> field18 = CacheBuilder.newBuilder().expireAfterWrite(2L, TimeUnit.SECONDS).build();
   private Vector3i field19;
   private long field20;

   public SkyblockCollectedSecret(Skyblock skyblock1) {
      super(false);
      this.method3(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method3(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method3(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON));
      this.handle(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.RoomSecretEvent.RoomSecretCollectedEvent.class, this::method3);
      this.handle(EventBlockChange.class, this::method1);
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventBlockUpdate.BlockUpdate.class, this::method4);
      this.handle(HudRenderLegacyEvent.class, arg1x -> {
         if (!(Boolean)this.field15.get()) {
            this.method5(arg1x.method3());
         }
      });
      this.handle(HudRenderLegacyEventAlt.class, arg1x -> {
         if ((Boolean)this.field15.get()) {
            this.method5(arg1x.method3());
         }
      });
      this.handle(EventWorldChange.class, this::method6);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method2);
   }

   private void method1(EventBlockChange highlightimpl91) {
      Vector3i vector3i2 = highlightimpl91.method1().bridge$toJoml();
      if (this.field18.asMap().containsKey(vector3i2)) {
         if (this.field19 == vector3i2 && Ref.method3().bridge$getSystemTime() - this.field20 <= 1000L) {
            WorldBridgeExtension itemcounter6extension3 = Ref.method8();
            if (itemcounter6extension3 != null) {
               if (itemcounter6extension3.bridge$getBlockEntity(Bridge.method8().method4(vector3i2.x(), vector3i2.y(), vector3i2.z())) instanceof TileEntityChestBridge hitcolor25 && hitcolor25.bridge$isVisuallyOpen()
                  )
                {
                  this.field18.invalidate(vector3i2);
                  this.field16.put(vector3i2, true);
               }
            }
         }
      }
   }

   private void method2(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (data1.OROIIOCCOORRCRCIIHHOCCCRHICRCC().equals("That chest is locked!")) {
         if (this.field19 != null) {
            this.field18.put(this.field19, true);
            this.field16.invalidate(this.field19);
         }
      }
   }

   private void method3(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.RoomSecretEvent.RoomSecretCollectedEvent data21) {
      SecretType hologramstype62 = data21.RHIHCOCRCCCCOHIOIOCRIIRRIHRHHR().getType();
      if (hologramstype62 != SecretType.FAIRY_SOUL) {
         if (hologramstype62 == SecretType.CHEST) {
            Vector3i vector3i3 = data21.method2().bridge$toJoml();
            if (!this.field16.asMap().containsKey(vector3i3)) {
               this.method13();
            }
         } else {
            this.method13();
         }
      }
   }

   private void method4(com.moonsworth.lunar.client.event.mixin.fishing.EventBlockUpdate.BlockUpdate data1) {
      BlocksBridge bridge_562 = Bridge.method34();
      if (data1.getBlock() == bridge_562.method23()) {
         Vector3i vector3i3 = data1.IIRHOIHIOOOCIIICROHOROCIOHHORC().bridge$toJoml();
         this.field19 = vector3i3;
         this.field20 = Ref.method3().bridge$getSystemTime();
         if (!this.field18.asMap().containsKey(vector3i3)) {
            this.field16.put(vector3i3, true);
         }
      } else if (data1.getBlock() == bridge_562.method47()) {
         this.field17.put(data1.IIRHOIHIOOOCIIICROHOROCIOHHORC().bridge$toJoml(), true);
         if ((Boolean)this.field8.get() && (Boolean)this.field9.get()) {
            this.method13();
         }
      }
   }

   private void method5(AbstractRenderContext bridgeextension_91) {
      if ((Boolean)this.field10.get() || (Boolean)this.field13.get()) {
         ConcurrentMap concurrentmap2 = this.field16.asMap();
         ConcurrentMap concurrentmap3 = this.field17.asMap();
         ConcurrentMap concurrentmap4 = this.field18.asMap();
         if (!concurrentmap2.isEmpty() || !concurrentmap3.isEmpty() || !concurrentmap4.isEmpty()) {
            WorldBridgeExtension itemcounter6extension5 = Ref.method8();
            if (itemcounter6extension5 != null) {
               EntityRenderDispatcherBridge bridge2_436 = Ref.method13();
               bridgeextension_91.push();
               DrawBufferBridge bridge2_327 = bridgeextension_91.method10(this.field15.get() ? LunarRenderTypes.field52 : LunarRenderTypes.field15);
               bridge2_327.method1();
               bridgeextension_91.translate(-bridge2_436.bridge$renderPosX(), -bridge2_436.bridge$renderPosY(), -bridge2_436.bridge$renderPosZ());
               if ((Boolean)this.field10.get()) {
                  for (Vector3i vector3i9 : concurrentmap2.keySet()) {
                     WorldRenderUtils.fillBox(bridge2_327, vector3i9.x(), vector3i9.y(), vector3i9.z(), vector3i9.x() + 1, vector3i9.y() + 1, vector3i9.z() + 1, this.field11.method1(0.0F));
                  }

                  for (Vector3i vector3i14 : concurrentmap4.keySet()) {
                     WorldRenderUtils.fillBox(bridge2_327, vector3i14.x(), vector3i14.y(), vector3i14.z(), vector3i14.x() + 1, vector3i14.y() + 1, vector3i14.z() + 1, this.field12.method1(0.0F));
                  }
               }

               if ((Boolean)this.field13.get()) {
                  for (Vector3i vector3i15 : concurrentmap3.keySet()) {
                     WorldRenderUtils.fillBox(bridge2_327, WorldRenderUtils.getAABB(itemcounter6extension5, vector3i15), this.field14.method1(0.0F));
                  }
               }

               bridge2_327.method17(BufferMode.BATCHED);
               BufferBuilderBridge bridge_2813 = bridgeextension_91.method11(
                  (Float)((Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1()).method19().get()
               );
               if ((Boolean)this.field10.get()) {
                  for (Vector3i vector3i10 : concurrentmap2.keySet()) {
                     WorldRenderUtils.drawBoxOutline(
                        bridge_2813,
                        vector3i10.x(),
                        vector3i10.y(),
                        vector3i10.z(),
                        vector3i10.x() + 1,
                        vector3i10.y() + 1,
                        vector3i10.z() + 1,
                        ColorUtils.method31(this.field11.method1(0.0F))
                     );
                  }

                  for (Vector3i vector3i19 : concurrentmap4.keySet()) {
                     WorldRenderUtils.drawBoxOutline(
                        bridge_2813,
                        vector3i19.x(),
                        vector3i19.y(),
                        vector3i19.z(),
                        vector3i19.x() + 1,
                        vector3i19.y() + 1,
                        vector3i19.z() + 1,
                        ColorUtils.method31(this.field12.method1(0.0F))
                     );
                  }
               }

               if ((Boolean)this.field13.get()) {
                  for (Vector3i vector3i20 : concurrentmap3.keySet()) {
                     WorldRenderUtils.drawBoxOutline(bridge_2813, WorldRenderUtils.getAABB(itemcounter6extension5, vector3i20), ColorUtils.method31(this.field14.method1(0.0F)));
                  }
               }

               bridge_2813.end();
               bridgeextension_91.pop();
            }
         }
      }
   }

   private void method6(EventWorldChange data31) {
      this.field16.invalidateAll();
      this.field18.invalidateAll();
      this.field17.invalidateAll();
   }

   private void method13() {
      if ((Boolean)this.field8.get()) {
         IslandUtils.playSound();
      }
   }

   public String getId() {
      return "SKYBLOCK_COLLECTED_SECRET";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.method9(new ClientOption[]{this.field8});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field10, arg1xx -> arg1xx.method9(new ClientOption[]{this.field11, this.field12})
            );
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field13, arg1xx -> arg1xx.method9(new ClientOption[]{this.field14}));
            arg1x.method9(new ClientOption[]{this.field15})
               .method3(() -> !(Boolean)this.field10.get() && !(Boolean)this.field13.get());
         }
      );
   }
}
