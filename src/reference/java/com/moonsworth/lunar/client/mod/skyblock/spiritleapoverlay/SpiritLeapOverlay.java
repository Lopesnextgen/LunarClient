package com.moonsworth.lunar.client.mod.skyblock.spiritleapoverlay;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.SpiritLeapMap;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsHandler;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.BettermapVariant;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStats;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.BettermapSettings;
import com.moonsworth.lunar.client.framework.feature.mod.gui.mixin.SpiritLeapGuiContainer;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonScoreListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonMapListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.listener.ScoreboardListener;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot;
import com.moonsworth.lunar.client.event.render.EventRenderScreenOverlay.EventScreenInitPost;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import lombok.Generated;

public class SpiritLeapOverlay extends AbstractFeature {
   private final HighlightTypeListener field8 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final ScoreboardListener field9 = (ScoreboardListener)this.method63(ScoreboardListener.class);
   private final DungeonScoreListener field10 = (DungeonScoreListener)this.method63(DungeonScoreListener.class);
   private final DungeonMapListener field11 = (DungeonMapListener)this.method63(DungeonMapListener.class);
   private final EquippedItemListener field12 = (EquippedItemListener)this.method63(EquippedItemListener.class);
   private final SpiritLeapOverlay.Data field13 = new SpiritLeapOverlay.Data();
   private final EnumOption<SpiritLeapOverlay.Type> field14 = (EnumOption<SpiritLeapOverlay.Type>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "spiritLeapMenuMode", SpiritLeapOverlay.Type.NEW
      )
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("spiritLeapNames")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("highlightSpiritLeap")
      .method31();
   private final ColorOption field17 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "highlightColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method31();
   private final ToggleOption field18 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("highlightArcher")
      .method31();
   private final ToggleOption field19 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("highlightMage")
      .method31();
   private final ToggleOption field20 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("highlightTank")
      .method31();
   private final ToggleOption field21 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("highlightBerserk")
      .method31();
   private final ToggleOption field22 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("highlightHealer")
      .method31();
   private final ToggleOption field23 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("highlightPlayers")
      .method31();
   private final TextOption field24 = (TextOption)com.moonsworth.lunar.client.config.option.OptionFactory.method12("highlightPlayersNames")
      .method31();
   private final EnumOption<SpiritLeapOverlay.SpiritLeapDefaultView> field25 = (EnumOption<SpiritLeapOverlay.SpiritLeapDefaultView>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "spiritLeapMenuDefaultView", SpiritLeapOverlay.SpiritLeapDefaultView.MAP
      )
      .method31();
   private final ToggleOption field26 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "spiritLeapMenuDefaultPrimary"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final BettermapSettings field27 = new BettermapSettings(BettermapVariant.SPIRIT_LEAP, this.field12);
   private final HashMap<String, Character> field28 = new HashMap<>();
   private final HashSet<String> field29 = new HashSet<>();
   private final DungeonStats field30 = new HologramsHandler(this.field11, this.field10);
   private final SpiritLeapMap field31 = new SpiritLeapMap(this::method6, this.field30);

   public SpiritLeapOverlay(Skyblock skyblock1, GuiIterator guiiterator2) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON));
      this.handle(com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.ItemRender.class, this::method5);
      this.handle(com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotAfterItems.class, this::method3);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, arg1x -> {
         if (IslandUtils.getIsland() == SkyblockIsland.DUNGEON) {
            this.method4(arg1x);
         }
      });
      this.handle(EventScreenInitPost.class, this::method1);
      this.field24.CICORRHIOIIOORRRICCORIOIOCIHII(arg1x -> {
         this.field29.clear();
         if (!arg1x.isEmpty()) {
            String[] items2x = arg1x.split("[ ,]+");

            for (String text6 : items2x) {
               this.field29.add(text6.toLowerCase(Locale.ROOT));
            }
         }
      });
      this.field25.CICORRHIOIIOORRRICCORIOIOCIHII(arg2x -> {
         this.field13.field1 = arg2x.id();
         guiiterator2.method3("spiritLeap", this.field13.provide());
      });
   }

   private void method1(EventScreenInitPost data51) {
      if (this.field8.method7() == SkyblockMenuType.SPIRIT_LEAP) {
         if (!this.method14()) {
            if (data51.method1() instanceof GuiContainerBridge bridge5extension_32) {
               Ref.method3().bridge$displayScreen(Bridge.method8().method18(new SpiritLeapGuiContainer(DriverRouteRegistry.field16, bridge5extension_32)));
            }
         }
      }
   }

   public void method2(AbstractRenderContext bridgeextension_91, com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5 data52) {
      if (this.isEnabled() && !this.method14()) {
         BettermapSettings holograms_93 = !this.field26.get()
            ? this.field27
            : Ref.method4().method40().method82().method100().method13();
         holograms_93.method54(true);
         this.field31.method1(bridgeextension_91, holograms_93, data52);
         holograms_93.method54(false);
      }
   }

   private void method3(com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotAfterItems data21) {
      if (this.field8.method7() == SkyblockMenuType.SPIRIT_LEAP) {
         if ((Boolean)this.field15.get()) {
            GuiContainerBridge bridge5extension_32 = (GuiContainerBridge)data21.method3();
            List list3 = bridge5extension_32.bridge$inventorySlots();
            int index4 = 1;

            for (int index5 = 9; index5 <= 17; index5++) {
               SlotBridge bridge3_186 = (SlotBridge)list3.get(index5);
               if (bridge3_186 != null) {
                  ItemStackBridge bridgeextension_47 = bridge3_186.bridge$getItemStack();
                  if (bridgeextension_47 != null && bridgeextension_47.bridge$getItem() == Bridge.method28().method5()) {
                     int number8 = (int)Ref.method10().bridge$getStringWidth(bridgeextension_47.bridge$getDisplayName());
                     int number9;
                     byte number10;
                     switch (index4) {
                        case 1:
                           number9 = -number8 - 15;
                           number10 = 16;
                           break;
                        case 2:
                        default:
                           number9 = -number8 / 2 - 3;
                           number10 = -2;
                           break;
                        case 3:
                           number9 = -number8 / 2 - 3;
                           number10 = 34;
                           break;
                        case 4:
                           number9 = 8;
                           number10 = 16;
                     }

                     number9 += bridge5extension_32.bridge$getGuiLeft() + bridge3_186.bridge$getXDisplayPosition();
                     number10 += bridge5extension_32.bridge$getGuiTop() + bridge3_186.bridge$getYDisplayPosition();
                     MixinHelper_4 mixinhelper_411 = data21.method5();
                     mixinhelper_411.push();
                     int number12 = number9;
                     int number13 = number10;
                     LcuiScreen.method85(mixinhelper_411, Collections.singletonList(bridgeextension_47.bridge$getDisplayName()), number12, number13);
                     mixinhelper_411.pop();
                     index4++;
                  }
               }
            }
         }
      }
   }

   private void method4(EventChatMessage highlightimpl1) {
      String text2 = highlightimpl1.method6();
      if (text2.equals("Starting in 1 second.")) {
         this.field28.clear();

         for (String text5 : this.field9.method6()) {
            if (text5.charAt(0) == '[') {
               String text6 = text5.substring(4, text5.lastIndexOf(" "));
               this.field28.put(text6, text5.charAt(1));
            }
         }
      }
   }

   private void method5(EventRenderContainerSlot highlightimpl151) {
      if (this.field8.method7() == SkyblockMenuType.SPIRIT_LEAP) {
         if ((Boolean)this.field16.get()) {
            if (!this.field28.isEmpty()) {
               GuiContainerBridge bridge5extension_32 = (GuiContainerBridge)highlightimpl151.method3();
               List list3 = bridge5extension_32.bridge$inventorySlots();

               for (int index4 = 9; index4 <= 17; index4++) {
                  SlotBridge bridge3_185 = (SlotBridge)list3.get(index4);
                  if (bridge3_185 != null) {
                     ItemStackBridge bridgeextension_46 = bridge3_185.bridge$getItemStack();
                     if (bridgeextension_46 != null && bridgeextension_46.bridge$getItem() == Bridge.method28().method5()) {
                        String text7 = ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_46.bridge$getDisplayName());
                        if (!this.field28.containsKey(text7)) {
                           return;
                        }

                        boolean flag8;
                        if ((Boolean)this.field23.get() && this.field29.contains(text7.toLowerCase(Locale.ROOT))) {
                           flag8 = true;
                        } else {
                           char character9 = this.field28.get(text7);
                           switch (character9) {
                              case 'A':
                                 flag8 = (Boolean)this.field18.get();
                                 break;
                              case 'B':
                                 flag8 = (Boolean)this.field21.get();
                                 break;
                              case 'H':
                                 flag8 = (Boolean)this.field22.get();
                                 break;
                              case 'M':
                                 flag8 = (Boolean)this.field19.get();
                                 break;
                              case 'T':
                                 flag8 = (Boolean)this.field20.get();
                                 break;
                              default:
                                 continue;
                           }
                        }

                        if (flag8) {
                           LcuiScreen.method127(highlightimpl151.method5(), bridge5extension_32, bridge3_185, this.field17.method14(0.0F));
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void method6(String text1) {
      if (Ref.method3().bridge$getCurrentScreen() instanceof Bridge5Extension62 bridge5extension622) {
         SpiritLeapGuiContainer bridge7impl10 = bridge5extension622.method2() instanceof SpiritLeapGuiContainer bridge7impl4 ? bridge7impl4 : null;
         if (bridge7impl10 == null) {
            Ref.method7().bridge$closeScreen();
         } else {
            GuiContainerBridge bridge5extension_311 = bridge7impl10.method4();
            if (bridge5extension_311 == null) {
               Ref.method7().bridge$closeScreen();
            } else {
               List list12 = bridge5extension_311.bridge$inventorySlots();

               for (int index6 = 9; index6 <= 17; index6++) {
                  SlotBridge bridge3_187 = (SlotBridge)list12.get(index6);
                  if (bridge3_187 != null) {
                     ItemStackBridge bridgeextension_48 = bridge3_187.bridge$getItemStack();
                     if (bridgeextension_48 != null && bridgeextension_48.bridge$getItem() == Bridge.method28().method5()) {
                        String text9 = ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_48.bridge$getDisplayName());
                        if (text9.equals(text1)) {
                           bridge5extension_311.bridge$clickSlot(bridge3_187);
                           break;
                        }
                     }
                  }
               }

               Ref.method7().bridge$closeScreen();
            }
         }
      }
   }

   @ConstantName
   public String getId() {
      return "SPIRIT_LEAP_OVERLAY";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      lightingextension231.method9(new ClientOption[]{this.field14});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field15})).method2(this::method13);
      ((SettingsSectionImpl)lightingextension231.method7(this.field16, arg1x -> {
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field17, this.field18, this.field19, this.field20, this.field21, this.field22});
         arg1x.method7(this.field23, arg1xx -> arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field24}));
      })).method2(this::method13);
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field25})).method2(this::method14);
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field26}))
         .method2(() -> this.method14() || this.field25.isHidden());
      this.field27.method2(lightingextension231, () -> (Boolean)this.field26.get() || this.method14() || this.field25.isHidden());
   }

   private boolean method13() {
      return this.field14.get() != SpiritLeapOverlay.Type.LEGACY;
   }

   private boolean method14() {
      return this.field14.get() != SpiritLeapOverlay.Type.NEW;
   }

   @Generated
   public EnumOption<SpiritLeapOverlay.SpiritLeapDefaultView> method15() {
      return this.field25;
   }

   @Generated
   public BettermapSettings method16() {
      return this.field27;
   }

   @Generated
   public DungeonStats method17() {
      return this.field30;
   }

   @Generated
   public SpiritLeapMap method19() {
      return this.field31;
   }

   private static class Data implements JsonProvider {
      @SerializedName("defaultMapView")
      String field1;

      private Data() {
      }

      public JsonElement provide() {
         JsonObject json1 = new JsonObject();
         json1.addProperty("defaultMapView", this.field1);
         return json1;
      }
   }

   private enum Type implements OptionEnumValue {
      LEGACY("legacy"),
      NEW("new");

      private final String id;

      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.method51(this.id, new Object[0]);
      }

      @Generated
      Type(String text3) {
         this.id = text3;
      }
   }

   public enum SpiritLeapDefaultView implements OptionEnumValue {
      WHEEL("wheel"),
      MAP("map");

      private final String id;

      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.method51(this.id, new Object[0]);
      }

      @Generated
      SpiritLeapDefaultView(String text3) {
         this.id = text3;
      }
   }
}
