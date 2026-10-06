package com.moonsworth.lunar.client.mod.skyblock.croptrackerhud;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.ProfileIdListener;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents.SkyblockProfileChangeEvent;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.LunarConstants;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class SkyblockCropTrackerHud extends AbstractFeature {
   private final ProfileIdListener field8 = (ProfileIdListener)this.method63(ProfileIdListener.class);
   private static final File field9 = new File(LunarConstants.field25 + File.separator + "skyblock_crop_drops.json");
   private static final Pattern field10 = Pattern.compile("^RARE CROP! (?<item>[\\w ]+)(?: \\(.+\\))*$");
   private static final NumberFormat field11 = NumberFormat.getIntegerInstance(Locale.ROOT);
   private static final ResourceLocationBridge field12 = ResourceLocationBridge.create("lunar", "skyblock/hud/cropie.png");
   private static final ResourceLocationBridge field13 = ResourceLocationBridge.create("lunar", "skyblock/hud/squash.png");
   private static final ResourceLocationBridge field14 = ResourceLocationBridge.create("lunar", "skyblock/hud/fermento.png");
   private static final ResourceLocationBridge field15 = ResourceLocationBridge.create("lunar", "skyblock/hud/helianthus.png");
   private SkyblockCropTrackerHud.CropDropCounts field16 = new SkyblockCropTrackerHud.CropDropCounts();
   private boolean field17;

   public SkyblockCropTrackerHud(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockCropTrackerHud.Data()));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.FARMING));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.GARDEN));
      this.method53(() -> this.method2(new SkyblockProfileChangeEvent(null, this.field8.method5())));
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method1);
      this.handle(SkyblockProfileChangeEvent.class, this::method2);
   }

   private void method1(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
      Matcher matcher3 = field10.matcher(text2);
      if (matcher3.matches()) {
         this.field16.addDrop(matcher3.group("item"));
         this.method13();
      }
   }

   private void method2(SkyblockProfileChangeEvent data151) {
      this.field16 = new SkyblockCropTrackerHud.CropDropCounts();
      if (field9.exists()) {
         try {
            String text2 = data151.method2();
            JsonElement element3 = JsonParser.parseReader(new FileReader(field9));
            if (!element3.isJsonObject()) {
               return;
            }

            JsonObject json4 = element3.getAsJsonObject();
            String text5 = this.mc.bridge$getSession().bridge$getProfile().getId().toString();
            if (!json4.has(text5)) {
               return;
            }

            JsonObject json6 = json4.getAsJsonObject(text5);
            if (!json6.has(text2)) {
               return;
            }

            this.field16 = (SkyblockCropTrackerHud.CropDropCounts)LunarConstants.field22.fromJson(json6.getAsJsonObject(text2), SkyblockCropTrackerHud.CropDropCounts.class);
         } catch (IOException exception7) {
            CrashReporter.method5(exception7, "Loading SkyBlock Crop Drops");
         }
      }
   }

   private void method13() {
      String text1 = this.field8.method5();
      if (text1 != null) {
         try {
            Object obj2;
            if (field9.exists()) {
               try {
                  obj2 = JsonParser.parseReader(new FileReader(field9));
               } catch (JsonParseException jsonparseexception12) {
                  obj2 = new JsonObject();
               }
            } else {
               obj2 = new JsonObject();
            }

            if (!obj2.isJsonObject()) {
               return;
            }

            String text3 = this.mc.bridge$getSession().bridge$getProfile().getId().toString();
            JsonObject json4 = obj2.getAsJsonObject();
            JsonObject json5 = json4.getAsJsonObject(text3);
            if (json5 == null) {
               json5 = new JsonObject();
            }

            JsonObject json6 = LunarConstants.field22.toJsonTree(this.field16).getAsJsonObject();
            json5.add(text1, json6);
            json4.add(text3, json5);

            try (FileWriter filewriter7 = new FileWriter(field9)) {
               LunarConstants.field22.toJson(json4, filewriter7);
            }
         } catch (IOException exception13) {
            CrashReporter.method5(exception13, "Saving SkyBlock Crop Drops");
         }
      }
   }

   public String getId() {
      return "SKYBLOCK_CROP_TRACKER_HUD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.method2(
            new OptionProvider[]{
               OptionFactory.method14("reset").method4(() -> this.field17 = true).method17(() -> this.field17),
               OptionFactory.method14("confirm").method4(() -> {
                  this.field16 = new SkyblockCropTrackerHud.CropDropCounts();
                  this.method13();
                  this.field17 = false;
               }).method17(() -> !this.field17)
            }
         )
      );
   }

   private class Data extends TypedHudRenderer<List<HudLine>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_RIGHT, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(50, 66, 100, 100, 200, 1000);
      }

      public List<HudLine> method2(boolean flag1) {
         return flag1
            ? this.method3(150, 90, 21, 12)
            : this.method3(
               SkyblockCropTrackerHud.this.field16.method1(),
               SkyblockCropTrackerHud.this.field16.method2(),
               SkyblockCropTrackerHud.this.field16.method3(),
               SkyblockCropTrackerHud.this.field16.method4()
            );
      }

      private List<HudLine> method3(int number1, int number2, int number3, int number4) {
         return List.of(
            new HudLine(
               Component.text(
                  SkyblockCropTrackerHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("cropTracker", new Object[0]),
                  NamedTextColor.AQUA,
                  new TextDecoration[]{TextDecoration.BOLD}
               )
            ),
            new HudLine(
               SkyblockCropTrackerHud.field12,
               TextComponentFactory.builder()
                  .method2("Cropie")
                  .method4(SkyblockCropTrackerHud.field11.format(number1))
                  .method5(NamedTextColor.GREEN)
                  .method7(number1 == 0 ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                  .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                  .build()
            ),
            new HudLine(
               SkyblockCropTrackerHud.field13,
               TextComponentFactory.builder()
                  .method2("Squash")
                  .method4(SkyblockCropTrackerHud.field11.format(number2))
                  .method5(NamedTextColor.BLUE)
                  .method7(number2 == 0 ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                  .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                  .build()
            ),
            new HudLine(
               SkyblockCropTrackerHud.field14,
               TextComponentFactory.builder()
                  .method2("Fermento")
                  .method4(SkyblockCropTrackerHud.field11.format(number3))
                  .method5(NamedTextColor.DARK_PURPLE)
                  .method7(number3 == 0 ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                  .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                  .build()
            ),
            new HudLine(
               SkyblockCropTrackerHud.field15,
               TextComponentFactory.builder()
                  .method2("Helianthus")
                  .method4(SkyblockCropTrackerHud.field11.format(number4))
                  .method5(NamedTextColor.GOLD)
                  .method7(number4 == 0 ? NamedTextColor.GRAY : NamedTextColor.GREEN)
                  .method12(this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH == null || (Boolean)this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get())
                  .build()
            )
         );
      }

      public boolean method4(boolean flag1) {
         return !super.method4(flag1) ? false : IslandUtils.getIsland() == SkyblockIsland.GARDEN;
      }

      public boolean method30() {
         return !super.HHRRRCCCHIOCOCRHHHRIHHCCRHORRI() ? false : IslandUtils.getIsland() == SkyblockIsland.GARDEN;
      }

      protected boolean method20() {
         return false;
      }

      protected boolean method22() {
         return false;
      }

      protected boolean method23() {
         return true;
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }
   }

   private static class CropDropCounts {
      @SerializedName("cropie")
      private int field1;
      @SerializedName("squash")
      private int field2;
      @SerializedName("fermento")
      private int field3;
      @SerializedName("helianthus")
      private int field4;

      private CropDropCounts() {
      }

      public void addDrop(String text1) {
         switch (text1) {
            case "Cropie":
               this.field1++;
               break;
            case "Squash":
               this.field2++;
               break;
            case "Fermento":
               this.field3++;
               break;
            case "Helianthus":
               this.field4++;
         }
      }

      @Generated
      public int method1() {
         return this.field1;
      }

      @Generated
      public int method2() {
         return this.field2;
      }

      @Generated
      public int method3() {
         return this.field3;
      }

      @Generated
      public int method4() {
         return this.field4;
      }
   }
}
