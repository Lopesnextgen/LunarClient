package com.moonsworth.lunar.client.mod.skyblock.gardenpests;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferBuilderBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.ChatMessageQueue;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.ScoreboardSection;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.ProfileIdListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockScoreboardParser;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents.SkyblockProfileChangeEvent;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.event.screen.EventScreenOpen;
import com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramItem;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.event.mixin.gui.EventTabListUpdate;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntListIterator;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2i;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockGardenPests extends AbstractFeature {
   private final ScreenTitleListener field8 = (ScreenTitleListener)this.method63(ScreenTitleListener.class);
   private final SkyblockScoreboardParser field9 = (SkyblockScoreboardParser)this.method63(SkyblockScoreboardParser.class);
   private final ProfileIdListener field10 = (ProfileIdListener)this.method63(ProfileIdListener.class);
   private final EquippedItemListener field11 = (EquippedItemListener)this.method63(EquippedItemListener.class);
   private final ProfileIdListener field12 = (ProfileIdListener)this.method63(ProfileIdListener.class);
   private static final File field13 = new File(LunarConstants.field25 + File.separator + "skyblock_garden_plots.json");
   private static final int field14 = 96;
   private static final Int2ObjectOpenHashMap<Vector2i> field15 = new Int2ObjectOpenHashMap();
   private static final Int2IntOpenHashMap field16 = new Int2IntOpenHashMap();
   private static final ResourceLocationBridge field17 = ResourceLocationBridge.create("lunar", "skyblock/hud/mosquito.png");
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockHighlightPests").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockHighlightSprayed").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockPestPlotBorders").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field21 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockPestPlotBorderVacuum")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field22 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockPestTimerHud").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ModifierKeybindOption field23 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "skyblockTpToPestKeybind"
         )
         .method18(this))
      .method31();
   private final ColorOption field24 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "skyblockPestHighlightColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
      .method31();
   private final ColorOption field25 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "skyblockSprayedHighlightColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-16711936))
      .method31();
   private final ColorOption field26 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "skyblockPlotBorderGridColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-256))
      .method31();
   private final ColorOption field27 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "skyblockPlotBorderCornerColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
      .method31();
   private final ObjectOpenHashSet<SlotBridge> field28 = new ObjectOpenHashSet();
   private final ObjectOpenHashSet<SlotBridge> field29 = new ObjectOpenHashSet();
   private final IntList field30 = new IntArrayList();
   private HashMap<Integer, String> field31 = new HashMap<>();
   private String field32 = "Unknown";
   private boolean field33;

   public SkyblockGardenPests(Skyblock skyblock1) {
      super(false);
      this.method7(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method7(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockGardenPests.Data()));
      this.method7(ModTraits.field17, ModCategories.method2(SettingsPage.FARMING));
      this.method7(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.GARDEN));
      this.field22.CICORRHIOIIOORRRICCORIOIOCIHII(arg1x -> {
         if (arg1x) {
            ((MixinCore9Extension)this.method7(ModTraits.field1)).method16(15.0F, 140.0F);
         } else {
            ((MixinCore9Extension)this.method7(ModTraits.field1)).method16(0.0F, 0.0F);
         }
      });
      field15.put(1, new Vector2i(-48, -144));
      field15.put(2, new Vector2i(-144, -48));
      field15.put(3, new Vector2i(48, -48));
      field15.put(4, new Vector2i(-48, 48));
      field15.put(5, new Vector2i(-144, -144));
      field15.put(6, new Vector2i(48, -144));
      field15.put(7, new Vector2i(-144, 48));
      field15.put(8, new Vector2i(48, 48));
      field15.put(9, new Vector2i(-48, -240));
      field15.put(10, new Vector2i(-240, -48));
      field15.put(11, new Vector2i(144, -48));
      field15.put(12, new Vector2i(-48, 144));
      field15.put(13, new Vector2i(-144, -240));
      field15.put(14, new Vector2i(48, -240));
      field15.put(15, new Vector2i(-240, -144));
      field15.put(16, new Vector2i(144, -144));
      field15.put(17, new Vector2i(-240, 48));
      field15.put(18, new Vector2i(144, 48));
      field15.put(19, new Vector2i(-144, 144));
      field15.put(20, new Vector2i(48, 144));
      field15.put(21, new Vector2i(-240, -240));
      field15.put(22, new Vector2i(144, -240));
      field15.put(23, new Vector2i(-240, 144));
      field15.put(24, new Vector2i(144, 144));
      field15.forEach((arg0, arg1x) -> {
         int number2 = (arg1x.x() + 240) / 96;
         int number3 = (arg1x.y() + 240) / 96;
         int number4 = number3 * 9 + number2 + 2;
         field16.put(number4, arg0);
      });
      this.method53(() -> this.method7(new SkyblockProfileChangeEvent(null, this.field12.method5())));
      this.handle(EventSlotUpdate.class, this::method1);
      this.handle(EventRenderHologramItem.class, this::method2);
      this.handle(EventTabListUpdate.class, this::method3);
      this.handle(HudRenderLegacyEvent.class, this::method5);
      this.handle(EventScreenOpen.class, this::method6);
      this.handle(SkyblockProfileChangeEvent.class, this::method7);
      this.field23.method3(this::method13);
   }

   private void method1(EventSlotUpdate highlightimpl1) {
      if ((Boolean)this.field18.get() || (Boolean)this.field19.get()) {
         String text2 = this.field8.method5();
         if (text2 != null && text2.equals("Configure Plots")) {
            int index3 = highlightimpl1.getSlot();
            if (index3 >= 0 && index3 < 54) {
               ItemStackBridge bridgeextension_44 = highlightimpl1.method3();
               if (bridgeextension_44 != null && field16.containsKey(index3)) {
                  String text5 = ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_44.bridge$getDisplayName());
                  String text6 = text5.substring(text5.indexOf(45) + 2);
                  this.field31.put(field16.get(index3), text6);
                  this.field33 = true;
               }

               SlotBridge bridge3_189 = (SlotBridge)this.field8.method6().bridge$inventorySlots().get(index3);

               for (String text7 : SkyblockItemUtil.method14(highlightimpl1.method3())) {
                  String text8 = ChatFormatting.getTextWithoutFormattingCodes(text7);
                  if (!text8.isEmpty()) {
                     if ((Boolean)this.field19.get() && text8.startsWith("Sprayed with ")) {
                        this.field29.add(bridge3_189);
                        return;
                     }

                     if ((Boolean)this.field18.get() && (text8.charAt(0) == 3424 || text8.charAt(0) == '\ue018')) {
                        this.field28.add(bridge3_189);
                        return;
                     }
                  }
               }
            }
         }
      }
   }

   private void method2(EventRenderHologramItem data51) {
      if ((Boolean)this.field18.get() || (Boolean)this.field19.get()) {
         String text2 = this.field8.method5();
         if (text2 != null && text2.equals("Configure Plots")) {
            SlotBridge bridge3_183 = data51.IHIRRHICIIHIHCRRHOHHOOHOHCHHHI();
            if (this.field28.contains(bridge3_183)) {
               data51.method1(this.field24.method14(0.0F));
            } else if (this.field29.contains(bridge3_183)) {
               data51.method1(this.field25.method14(0.0F));
            }
         }
      }
   }

   private void method3(EventTabListUpdate highlightimpl31) {
      ScoreboardSection lotusfish32 = (ScoreboardSection)this.field9.method6().get("pests");
      if (lotusfish32 != null) {
         this.field30.clear();

         for (String text4 : lotusfish32.method3()) {
            if (text4.startsWith(" Plots: ")) {
               String[] items5 = text4.substring(text4.indexOf(":") + 2).split(", ");

               for (String text9 : items5) {
                  this.method4(text9);
               }
            } else if (text4.startsWith(" Cooldown: ")) {
               this.field32 = text4.substring(text4.indexOf(":") + 2);
            }
         }
      }
   }

   private void method4(String text1) {
      int index2;
      try {
         index2 = Integer.parseInt(text1.trim());
      } catch (NumberFormatException numberformatexception4) {
         return;
      }

      if (field15.containsKey(index2)) {
         this.field30.add(index2);
      }
   }

   private void method5(HudRenderLegacyEvent highlightimpl21) {
      if ((Boolean)this.field20.get()) {
         if (!this.field30.isEmpty()) {
            if ((Boolean)this.field21.get()) {
               String text2 = this.field11.method9();
               if (!text2.contains("_VACUUM") && !text2.endsWith("_LASSO")) {
                  return;
               }
            }

            WorldBridgeExtension itemcounter6extension19 = Ref.method8();
            if (itemcounter6extension19 != null) {
               EntityRenderDispatcherBridge bridge2_433 = Ref.method13();
               if (bridge2_433 != null) {
                  byte number4 = 67;
                  byte number5 = 99;
                  AbstractRenderContext bridgeextension_96 = highlightimpl21.method3();
                  bridgeextension_96.method25(1.0F, 1.0F, 1.0F, 1.0F);
                  bridgeextension_96.push();

                  try {
                     bridgeextension_96.translate(-bridge2_433.bridge$renderPosX(), -bridge2_433.bridge$renderPosY(), -bridge2_433.bridge$renderPosZ());
                     BufferBuilderBridge bridge_287 = bridgeextension_96.method11(1.0F);
                     int number8 = this.field26.method1(0.0F);
                     int number9 = this.field27.method1(0.0F);
                     IntListIterator intlistiterator10 = this.field30.iterator();

                     while (intlistiterator10.hasNext()) {
                        int index11 = (Integer)intlistiterator10.next();
                        Vector2i vector2i12 = (Vector2i)field15.get(index11);
                        if (vector2i12 != null) {
                           int number13 = vector2i12.x();
                           int number14 = vector2i12.y();
                           bridge_287.method1(number8);

                           for (byte index15 = 4; index15 < 96; index15 += 4) {
                              bridge_287.method3(number13 + index15, number4, number14, number13 + index15, number5, number14);
                              bridge_287.method3(number13, number4, number14 + index15, number13, number5, number14 + index15);
                              bridge_287.method3(number13 + 96, number4, number14 + index15, number13 + 96, number5, number14 + index15);
                              bridge_287.method3(number13 + index15, number4, number14 + 96, number13 + index15, number5, number14 + 96);
                           }

                           for (byte index20 = number4; index20 <= number5; index20 += 4) {
                              bridge_287.method1(index20 % 16 == 3 ? number9 : number8);
                              bridge_287.method3(number13, index20, number14, number13 + 96, index20, number14);
                              bridge_287.method3(number13, index20, number14, number13, index20, number14 + 96);
                              bridge_287.method3(number13 + 96, index20, number14, number13 + 96, index20, number14 + 96);
                              bridge_287.method3(number13, index20, number14 + 96, number13 + 96, index20, number14 + 96);
                           }

                           bridge_287.method1(number9);
                           bridge_287.method3(number13, number4, number14, number13, number5, number14);
                           bridge_287.method3(number13 + 96, number4, number14, number13 + 96, number5, number14);
                           bridge_287.method3(number13 + 96, number4, number14 + 96, number13 + 96, number5, number14 + 96);
                           bridge_287.method3(number13, number4, number14 + 96, number13, number5, number14 + 96);
                        }
                     }

                     bridge_287.end();
                  } finally {
                     bridgeextension_96.pop();
                  }
               }
            }
         }
      }
   }

   public void method6(EventScreenOpen highlightimpl91) {
      if (this.field33) {
         String text2 = this.field10.method5();
         if (text2 != null) {
            try {
               Object obj3;
               if (field13.exists()) {
                  try {
                     obj3 = JsonParser.parseReader(new FileReader(field13));
                  } catch (JsonParseException jsonparseexception11) {
                     obj3 = new JsonObject();
                  }
               } else {
                  obj3 = new JsonObject();
               }

               if (!obj3.isJsonObject()) {
                  return;
               }

               JsonObject json4 = obj3.getAsJsonObject();
               JsonObject json5 = LunarConstants.field22.toJsonTree(this.field31).getAsJsonObject();
               json4.add(text2, json5);

               try (FileWriter filewriter6 = new FileWriter(field13)) {
                  LunarConstants.field22.toJson(json4, filewriter6);
               }
            } catch (IOException exception12) {
               CrashReporter.method5(exception12, "Saving SkyBlock Garden Plots");
            }

            this.field33 = false;
         }
      }
   }

   public void method7(SkyblockProfileChangeEvent data151) {
      this.field31 = new HashMap<>();
      if (field13.exists()) {
         try {
            String text2 = data151.method2();
            JsonElement element3 = JsonParser.parseReader(new FileReader(field13));
            if (!element3.isJsonObject()) {
               return;
            }

            JsonObject json4 = element3.getAsJsonObject();
            if (!json4.has(text2)) {
               return;
            }

            this.field31 = (HashMap<Integer, String>)LunarConstants.field22
               .fromJson(json4.getAsJsonObject(text2), (new TypeToken<Map<Integer, String>>() {}).getType());
         } catch (IOException exception5) {
            CrashReporter.method5(exception5, "Loading SkyBlock Garden Plots");
         }
      }
   }

   private void method13() {
      if (!this.field30.isEmpty()) {
         int index1 = this.field30.getInt(0);
         String text2 = this.field31.get(index1);
         if (text2 == null) {
            Ref.method4().method69().method7(NotificationType.ERROR, NotificationManager.method15("openPlotMenu", new Object[0]));
         } else {
            ChatMessageQueue.method1("/tptoplot " + text2);
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_GARDEN_PESTS";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field18, arg1xx -> arg1xx.method9(new ClientOption[]{this.field24}));
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field19, arg1xx -> arg1xx.method9(new ClientOption[]{this.field25}));
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field20, arg1xx -> arg1xx.method9(new ClientOption[]{this.field21, this.field26, this.field27})
            );
            arg1x.method9(new ClientOption[]{this.field23, this.field22});
         }
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   private class Data extends TypedHudRenderer<HudLine> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT, false, true);
      }

      public HudSize method15() {
         return HudSize.method1(10, 20, 30, 100, 200, 320);
      }

      @Nullable
      public HudLine method2(boolean flag1) {
         if (flag1) {
            return this.method3("2m 34s");
         } else {
            return IslandUtils.getIsland() != SkyblockIsland.GARDEN ? null : this.method3(SkyblockGardenPests.this.field32);
         }
      }

      private HudLine method3(String text1) {
         NamedTextColor namedtextcolor2 = NamedTextColor.YELLOW;
         if (text1.equals("MAX PESTS")) {
            namedtextcolor2 = NamedTextColor.RED;
         } else if (text1.equals("READY")) {
            namedtextcolor2 = NamedTextColor.GREEN;
         }

         return new HudLine(
            SkyblockGardenPests.field17,
            TextComponentFactory.builder()
               .method2("Pest Cooldown")
               .method4(text1)
               .method5(NamedTextColor.GOLD)
               .method7(namedtextcolor2)
               .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
               .build()
         );
      }

      public boolean method4(boolean flag1) {
         if (!(Boolean)SkyblockGardenPests.this.field22.get()) {
            return false;
         } else {
            return !flag1 && IslandUtils.getIsland() != SkyblockIsland.GARDEN ? false : super.method4(flag1);
         }
      }

      public boolean method30() {
         return !SkyblockGardenPests.this.field22.get() ? false : super.HHRRRCCCHIOCOCRHHHRIHHCCRHORRI();
      }

      protected boolean method23() {
         return true;
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }
   }
}
