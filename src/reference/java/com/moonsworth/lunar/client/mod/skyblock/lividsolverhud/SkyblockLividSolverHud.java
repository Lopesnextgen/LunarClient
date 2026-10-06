package com.moonsworth.lunar.client.mod.skyblock.lividsolverhud;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension2;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil.DyeColor;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonFloorListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonScoreListener;
import com.moonsworth.lunar.client.event.entity.EventEntityRemove;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockChange;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderEntityBase.EventRenderEntity;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

public class SkyblockLividSolverHud extends AbstractFeature {
   private final DungeonFloorListener field8 = (DungeonFloorListener)this.method63(DungeonFloorListener.class);
   private final DungeonScoreListener field9 = (DungeonScoreListener)this.method63(DungeonScoreListener.class);
   private static final Vec3iBridge field10 = Bridge.method8().method4(5, 108, 25);
   private static final ResourceLocationBridge field11 = ResourceLocationBridge.create("lunar", "skyblock/hud/livid.png");
   private final ToggleOption field12 = (ToggleOption)OptionFactory.method7("showLividHealth").method31();
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("boxLivid").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field14 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "lividBoxColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1140916223))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hideWrongLividNametags").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private BridgeExtension field16;
   private BridgeExtension field17;

   public SkyblockLividSolverHud(Skyblock skyblock1) {
      super(false);
      this.method1(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method1(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockLividSolverHud.Data()));
      this.method1(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method1(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON && this.field8.method6().getNumber() == 5));
      this.method3(this::onDisable);
      this.handle(EventBlockChange.class, this::method2);
      this.handle(EventSecond.class, this::method3);
      this.handle(HudRenderLegacyEvent.class, this::method4);
      this.handle(EventRenderEntity.class, this::method5);
      this.handle(EventEntityRemove.class, this::method6);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method7);
      this.handle(EventWorldChange.class, this::method8);
   }

   private void method1(DyeColor type21) {
      if (type21 != null) {
         String text2 = this.method10(type21);
         String text3 = this.method9(type21);
         if (text2 != null && text3 != null) {
            for (BridgeExtension bridgeextension5 : Ref.method8().bridge$getEntities()) {
               if (bridgeextension5 instanceof EntityArmorStandBridge) {
                  Component component7 = bridgeextension5.bridge$getCustomName();
                  if (component7 != null && TextBridge.asLegacyString(component7).startsWith(text2)) {
                     this.field16 = bridgeextension5;
                  }
               } else if (bridgeextension5 instanceof Bridge5Extension2 bridge5extension26 && bridge5extension26.bridge$getName().equals(text3)) {
                  this.field17 = bridge5extension26;
               }
            }
         }
      }
   }

   private void method2(EventBlockChange highlightimpl91) {
      if (this.field9.method10()) {
         Vec3iBridge horsestats202 = highlightimpl91.method1();
         if (horsestats202.bridge$getX() == field10.bridge$getX() && horsestats202.bridge$getY() == field10.bridge$getY() && horsestats202.bridge$getZ() == field10.bridge$getZ()) {
            DyeColor type23 = SkyblockItemUtil.method10(highlightimpl91.method3().bridge$getBlock(), Ref.method8(), horsestats202);
            this.method1(type23);
         }
      }
   }

   private void method3(EventSecond highlightimpl41) {
      if (this.field9.method10()) {
         WorldBridgeExtension itemcounter6extension2 = Ref.method8();
         Bridge3_23 bridge3_233 = itemcounter6extension2.method2(field10).bridge$getBlock();
         DyeColor type24 = SkyblockItemUtil.method10(bridge3_233, itemcounter6extension2, field10);
         this.method1(type24);
      }
   }

   private void method4(HudRenderLegacyEvent highlightimpl21) {
      if ((Boolean)this.field13.get()) {
         if (this.field9.method10() && this.field17 != null && !this.field17.bridge$isRemoved()) {
            AbstractRenderContext bridgeextension_92 = highlightimpl21.method3();
            EntityRenderDispatcherBridge bridge2_433 = Ref.method13();
            bridgeextension_92.push();
            bridgeextension_92.translate(-bridge2_433.bridge$renderPosX(), -bridge2_433.bridge$renderPosY(), -bridge2_433.bridge$renderPosZ());
            AxisAlignedBBBridge horsestats124 = this.field17.method11(highlightimpl21.method5());
            DrawBufferBridge bridge2_325 = bridgeextension_92.method10(LunarRenderTypes.field15);
            bridge2_325.method1();
            WorldRenderUtils.fillBox(bridge2_325, horsestats124, this.field14.method1(0.0F));
            bridge2_325.method17(BufferMode.BATCHED);
            BufferBuilderBridge bridge_286 = bridgeextension_92.method11((Float)Ref.method4().method40().method82().method19().get());
            WorldRenderUtils.drawBoxOutline(bridge_286, horsestats124, ColorUtils.method31(this.field14.method1(0.0F)));
            bridge_286.end();
            bridgeextension_92.pop();
         }
      }
   }

   private void method5(EventRenderEntity data81) {
      if ((Boolean)this.field15.get()) {
         BridgeExtension bridgeextension2 = data81.method1();
         if (this.field9.method10() && this.field17 != null && bridgeextension2 != this.field16) {
            if (bridgeextension2 instanceof EntityArmorStandBridge) {
               Component component3 = bridgeextension2.bridge$getCustomName();
               if (component3 != null) {
                  String text4 = TextBridge.asLegacyString(component3);
                  if (text4.contains("Livid") && !bridgeextension2.equals(this.field16)) {
                     data81.setCancelled(true);
                  }
               }
            }
         }
      }
   }

   private void method6(EventEntityRemove highlightimpl121) {
      if (this.field9.method10()) {
         BridgeExtension bridgeextension2 = highlightimpl121.method1();
         if (bridgeextension2 == this.field17) {
            this.field17 = null;
         } else if (bridgeextension2 == this.field16) {
            this.field16 = null;
         }
      }
   }

   private void method7(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
      if (this.field9.method10() && text2.endsWith("Impossible! How did you figure out which one I was?!")) {
         this.field17 = null;
         this.field16 = null;
      }
   }

   private void onDisable() {
      this.field17 = null;
      this.field16 = null;
   }

   private void method8(EventWorldChange data31) {
      this.field17 = null;
      this.field16 = null;
   }

   private String method9(DyeColor type21) {
      return switch (type21) {
         case WHITE -> "Vendetta Livid";
         case MAGENTA -> "Crossed Livid";
         case YELLOW -> "Arcade Livid";
         case LIME -> "Smile Livid";
         case GRAY -> "Doctor Livid";
         case PURPLE -> "Purple Livid";
         case BLUE -> "Scream Livid";
         case GREEN -> "Frog Livid";
         case RED -> "Hockey Livid";
         default -> null;
      };
   }

   private String method10(DyeColor type21) {
      String text2;
      switch (type21) {
         case WHITE:
            text2 = ChatFormatting.WHITE.toString();
            break;
         case MAGENTA:
            text2 = ChatFormatting.LIGHT_PURPLE.toString();
            break;
         case YELLOW:
            text2 = ChatFormatting.YELLOW.toString();
            break;
         case LIME:
            text2 = ChatFormatting.GREEN.toString();
            break;
         case GRAY:
            text2 = ChatFormatting.GRAY.toString();
            break;
         case PURPLE:
            text2 = ChatFormatting.DARK_PURPLE.toString();
            break;
         case BLUE:
            text2 = ChatFormatting.BLUE.toString();
            break;
         case GREEN:
            text2 = ChatFormatting.DARK_GREEN.toString();
            break;
         case RED:
            text2 = ChatFormatting.RED.toString();
            break;
         default:
            return null;
      }

      return text2 + "﴾";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> {
         arg1x.method9(new ClientOption[]{this.field12});
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field13, arg1xx -> arg1xx.method9(new ClientOption[]{this.field14}));
         arg1x.method9(new ClientOption[]{this.field15});
      });
      this.field12.CICORRHIOIIOORRRICCORIOIOCIHII(arg1x -> {
         if (arg1x) {
            ((MixinCore9Extension)this.method7(ModTraits.field1)).method16(15.0F, 80.0F);
         } else {
            ((MixinCore9Extension)this.method7(ModTraits.field1)).method16(0.0F, 0.0F);
         }
      });
   }

   public String getId() {
      return "SKYBLOCK_LIVID_SOLVER_HUD";
   }

   private class Data extends TypedHudRenderer<HudLine> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, false, true);
      }

      public HudSize method15() {
         return HudSize.method1(10, 20, 30, 20, 120, 200);
      }

      @Nullable
      public HudLine method2(boolean flag1) {
         if (SkyblockLividSolverHud.this.field16 != null && !SkyblockLividSolverHud.this.field16.bridge$isRemoved()) {
            return new HudLine(SkyblockLividSolverHud.field11, SkyblockLividSolverHud.this.field16.bridge$getCustomName());
         } else {
            return flag1 ? new HudLine(SkyblockLividSolverHud.field11, Component.text("﴾ Livid 5.0M❤ ﴿")) : null;
         }
      }

      public boolean method4(boolean flag1) {
         return !SkyblockLividSolverHud.this.field12.get() ? false : super.method4(flag1);
      }

      public boolean method30() {
         return !SkyblockLividSolverHud.this.field12.get() ? false : super.HHRRRCCCHIOCOCRHHHRIHHCCRHORRI();
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }
   }
}
