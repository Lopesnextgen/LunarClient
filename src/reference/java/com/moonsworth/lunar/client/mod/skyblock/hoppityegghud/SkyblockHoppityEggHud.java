package com.moonsworth.lunar.client.mod.skyblock.hoppityegghud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.SkyblockCalendar;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.ChocolateEggLocations;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.ChocolateEggLocationsDeserializer;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.ProfileIdListener;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents.SkyblockProfileChangeEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.ui.hud.HudRowAlignment;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.mod.skyblock.hoppityegghud.SkyblockEggLocator;
import com.moonsworth.lunar.client.mod.skyblock.hoppityegghud.SkyblockEggWaypoints;
import com.moonsworth.lunar.client.util.text.TimeFormatting;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import io.leangen.geantyref.TypeToken;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.Nullable;

public class SkyblockHoppityEggHud extends AbstractFeature {
   private final ProfileIdListener field8 = (ProfileIdListener)this.method34(ProfileIdListener.class);
   public static final Gson field9 = new GsonBuilder().registerTypeAdapter(ChocolateEggLocations.class, new ChocolateEggLocationsDeserializer()).create();
   private static final File field10 = new File(LunarConstants.field25 + File.separator + "skyblock_hoppity_eggs.json");
   private static final Pattern field11 = Pattern.compile(
      "^HOPPITY'S HUNT You found a Chocolate (?<type>Breakfast|Lunch|Dinner|Brunch|Déjeuner|Supper) Egg .*$"
   );
   private static final ResourceLocationBridge field12 = ResourceLocationBridge.create("lunar", "skyblock/hud/hoppity_breakfast_egg.png");
   private static final ResourceLocationBridge field13 = ResourceLocationBridge.create("lunar", "skyblock/hud/hoppity_lunch_egg.png");
   private static final ResourceLocationBridge field14 = ResourceLocationBridge.create("lunar", "skyblock/hud/hoppity_dinner_egg.png");
   private HashMap<String, Object2BooleanOpenHashMap<String>> field15;
   private final SkyblockEggWaypoints field16;
   private final SkyblockEggLocator field17;
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockHoppityEggLocator")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)OptionFactory.method7("skyblockHoppityAlwaysShowEggs").method31();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockHoppityEggWaypoints")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field21 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "skyblockHoppityEggWaypointColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(419495680))
      .method31();
   private final ToggleOption field22 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockHoppityEggDuplicates")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field23 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "skyblockHoppityDuplicateColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(436142080))
      .method31();
   private final ToggleOption field24 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockHoppityEggHud").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field25 = (ToggleOption)OptionFactory.method7("skyblockHoppityEggHudOutside").method31();
   private final MultiSelectOption field26 = (MultiSelectOption)((com.moonsworth.lunar.client.config.option.MultiSelectOption.Data)OptionFactory.method27(
            "skyBlockHoppityEggHudIslands"
         )
         .method2(SkyblockIsland.ids()))
      .method3(SkyblockIsland.ids())
      .method31();
   private long field27;
   private boolean field28;
   private boolean field29;
   private boolean field30;
   private boolean field31;
   private boolean field32;
   private boolean field33;

   public SkyblockHoppityEggHud(Skyblock skyblock1) {
      super(false);
      this.field16 = new SkyblockEggWaypoints(this, this.field20);
      this.field17 = new SkyblockEggLocator(this, this.field18);
      this.method4(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method4(ModTraits.field1, new SkyblockHoppityEggHud.Data());
      this.method4(ModTraits.field17, ModCategories.method2(SettingsPage.EVENT));
      this.method4(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method53(() -> this.method4(new SkyblockProfileChangeEvent(null, this.field8.method5())));
      this.handle(EventSecond.class, this::method2);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method3);
      this.handle(SkyblockProfileChangeEvent.class, this::method4);
      this.field24.CICORRHIOIIOORRRICCORIOIOCIHII(arg1x -> {
         if (arg1x) {
            ((MixinCore9Extension)this.method7(ModTraits.field1)).method16(30.0F, 80.0F);
         } else {
            ((MixinCore9Extension)this.method7(ModTraits.field1)).method16(0.0F, 0.0F);
         }
      });
   }

   public List<Framework7Extension> method9() {
      return List.of(this.field16, this.field17);
   }

   private void method2(EventSecond highlightimpl41) {
      if (SkyblockCalendar.isSpring()) {
         long number2 = SkyblockCalendar.getElapsedSeconds(2);
         if (this.field27 < 350L && number2 >= 350L) {
            this.field28 = false;
         } else if (this.field27 < 700L && number2 >= 700L) {
            this.field29 = false;
         } else if (this.field27 < 1050L & number2 >= 1050L) {
            this.field30 = false;
         } else if (this.field27 < 1550L && number2 >= 1550L) {
            this.field31 = false;
         } else if (this.field27 < 1900L && number2 >= 1900L) {
            this.field33 = false;
         } else if (this.field27 < 2250L & number2 >= 2250L) {
            this.field32 = false;
         }

         this.field27 = number2;
      }
   }

   private void method3(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (IslandUtils.isOnIsland()) {
         if (SkyblockCalendar.isSpring()) {
            String text2 = ChatFormatting.getTextWithoutFormattingCodes(data1.OROIIOCCOORRCRCIIHHOCCCRHICRCC());
            text2 = text2.replace("’", "'");
            if (text2.equals("There are no hidden Chocolate Rabbit Eggs nearby! Try again later!")) {
               this.field28 = true;
               this.field29 = true;
               this.field30 = true;
               this.field31 = true;
               this.field33 = true;
               this.field32 = true;
            } else {
               Matcher matcher3 = field11.matcher(text2);
               if (matcher3.matches()) {
                  switch (matcher3.group("type")) {
                     case "Breakfast":
                        this.field28 = true;
                        break;
                     case "Lunch":
                        this.field29 = true;
                        break;
                     case "Dinner":
                        this.field30 = true;
                        break;
                     case "Brunch":
                        this.field31 = true;
                        break;
                     case "Déjeuner":
                        this.field33 = true;
                        break;
                     case "Supper":
                        this.field32 = true;
                  }

                  ChocolateEggLocations fishing214 = this.method15();
                  if (fishing214 != null) {
                     for (Entry entry6 : fishing214.method1().entrySet()) {
                        String text7 = (String)entry6.getKey();
                        HashMap map8 = (HashMap)entry6.getValue();
                        if (map8 != null) {
                           for (Entry entry10 : map8.entrySet()) {
                              String text11 = (String)entry10.getKey();
                              com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.ChocolateEggLocations.Data data12 = (com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.ChocolateEggLocations.Data)entry10.getValue();
                              if (text2.endsWith(data12.getMessage())) {
                                 if (this.field15 == null) {
                                    this.field15 = new HashMap<>();
                                 }

                                 if (!this.field15.containsKey(text7)) {
                                    this.field15.put(text7, new Object2BooleanOpenHashMap());
                                 }

                                 this.field15.get(text7).put(text11, true);
                                 this.method13();
                                 return;
                              }
                           }
                        }
                     }

                     CrashReporter.method5(
                        new IllegalArgumentException(
                           "Could not find egg with message: \""
                              + text2
                              + "\"  at location ~={x:"
                              + Ref.method7().bridge$getBlockX()
                              + ",y:"
                              + Ref.method7().bridge$getBlockY()
                              + ",z:"
                              + Ref.method7().bridge$getBlockZ()
                              + "}"
                        ),
                        "SkyBlockHoppitysHunt"
                     );
                  }
               }
            }
         }
      }
   }

   private void method4(SkyblockProfileChangeEvent data151) {
      this.field15 = null;
      if (field10.exists()) {
         try {
            String text2 = data151.method2();
            JsonElement element3 = JsonParser.parseReader(new FileReader(field10));
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

            this.field15 = (HashMap<String, Object2BooleanOpenHashMap<String>>)LunarConstants.field22
               .fromJson(json6.getAsJsonObject(text2), (new TypeToken<HashMap<String, Object2BooleanOpenHashMap<String>>>() {}).getType());
         } catch (IOException exception7) {
            CrashReporter.method5(exception7, "Loading SkyBlock Hoppity Eggs");
         }
      }
   }

   private void method13() {
      String text1 = this.field8.method5();
      if (text1 != null) {
         try {
            Object obj2;
            if (field10.exists()) {
               try {
                  obj2 = JsonParser.parseReader(new FileReader(field10));
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

            JsonObject json6 = LunarConstants.field22.toJsonTree(this.field15).getAsJsonObject();
            json5.add(text1, json6);
            json4.add(text3, json5);

            try (FileWriter filewriter7 = new FileWriter(field10)) {
               LunarConstants.field22.toJson(json4, filewriter7);
            }
         } catch (IOException exception13) {
            CrashReporter.method5(exception13, "Saving SkyBlock Hoppity Eggs");
         }
      }
   }

   public boolean method14() {
      return this.field28 && this.field29 && this.field30 && this.field31 && this.field33 && this.field32;
   }

   public boolean method7(String text1, String text2) {
      if (!(Boolean)this.field22.get()) {
         return false;
      } else if (this.field15 == null) {
         return false;
      } else if (!this.field15.containsKey(text1)) {
         return false;
      } else {
         return !this.field15.get(text1).containsKey(text2) ? false : this.field15.get(text1).getBoolean(text2);
      }
   }

   public ChocolateEggLocations method15() {
      return ((Skyblock)((ChildModBinding)this.method7(ModTraits.field16)).method1()).method15().method16();
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field18, arg1xx -> arg1xx.method9(new ClientOption[]{this.field19}));
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field20, arg1xx -> arg1xx.method9(new ClientOption[]{this.field21, this.field22})
            );
            arg1x.method9(new ClientOption[]{this.field23})
               .method3(() -> !(Boolean)this.field20.get() || !(Boolean)this.field22.get());
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field24, arg1xx -> arg1xx.method9(new ClientOption[]{this.field25}));
            arg1x.method9(new ClientOption[]{this.field26});
         }
      );
   }

   public String getId() {
      return "SKYBLOCK_HOPPITY_EGG_HUD";
   }

   @Generated
   public ProfileIdListener method16() {
      return this.field8;
   }

   @Generated
   public HashMap<String, Object2BooleanOpenHashMap<String>> method17() {
      return this.field15;
   }

   @Generated
   public SkyblockEggWaypoints method19() {
      return this.field16;
   }

   @Generated
   public SkyblockEggLocator method21() {
      return this.field17;
   }

   @Generated
   public ToggleOption method22() {
      return this.field18;
   }

   @Generated
   public ToggleOption method23() {
      return this.field19;
   }

   @Generated
   public ToggleOption method24() {
      return this.field20;
   }

   @Generated
   public ColorOption method25() {
      return this.field21;
   }

   @Generated
   public ToggleOption method26() {
      return this.field22;
   }

   @Generated
   public ColorOption method27() {
      return this.field23;
   }

   @Generated
   public ToggleOption method28() {
      return this.field24;
   }

   @Generated
   public ToggleOption method29() {
      return this.field25;
   }

   @Generated
   public MultiSelectOption method30() {
      return this.field26;
   }

   @Generated
   public long method34() {
      return this.field27;
   }

   @Generated
   public boolean method35() {
      return this.field28;
   }

   @Generated
   public boolean method36() {
      return this.field29;
   }

   @Generated
   public boolean method37() {
      return this.field30;
   }

   @Generated
   public boolean method38() {
      return this.field31;
   }

   @Generated
   public boolean method39() {
      return this.field32;
   }

   @Generated
   public boolean method40() {
      return this.field33;
   }

   @Generated
   public void method30(HashMap<String, Object2BooleanOpenHashMap<String>> map1) {
      this.field15 = map1;
   }

   private class Data extends TypedHudRenderer<List<HudLine>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(10, 60, 120, 20, 100, 200);
      }

      @Nullable
      public List<HudLine> method2(boolean flag1) {
         return flag1
            ? List.of(
               new HudLine(
                  Component.text(
                     SkyblockHoppityEggHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("title", new Object[0]),
                     NamedTextColor.AQUA,
                     new TextDecoration[]{TextDecoration.BOLD}
                  )
               ),
               this.method9(SkyblockHoppityEggHud.Type.BREAKFAST, false, 1075000L),
               this.method9(SkyblockHoppityEggHud.Type.LUNCH, true, 225000L),
               this.method9(SkyblockHoppityEggHud.Type.DINNER, true, 575000L),
               this.method9(SkyblockHoppityEggHud.Type.BRUNCH, false, 2275000L),
               this.method9(SkyblockHoppityEggHud.Type.DEJEUNER, true, 1300000L),
               this.method9(SkyblockHoppityEggHud.Type.SUPPER, true, 1650000L)
            )
            : List.of(
               new HudLine(
                  Component.text(
                     SkyblockHoppityEggHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("title", new Object[0]),
                     NamedTextColor.AQUA,
                     new TextDecoration[]{TextDecoration.BOLD}
                  )
               ),
               this.method8(SkyblockHoppityEggHud.Type.BREAKFAST, SkyblockHoppityEggHud.this.field28),
               this.method8(SkyblockHoppityEggHud.Type.LUNCH, SkyblockHoppityEggHud.this.field29),
               this.method8(SkyblockHoppityEggHud.Type.DINNER, SkyblockHoppityEggHud.this.field30),
               this.method8(SkyblockHoppityEggHud.Type.BRUNCH, SkyblockHoppityEggHud.this.field31),
               this.method8(SkyblockHoppityEggHud.Type.DEJEUNER, SkyblockHoppityEggHud.this.field33),
               this.method8(SkyblockHoppityEggHud.Type.SUPPER, SkyblockHoppityEggHud.this.field32)
            );
      }

      public boolean method30() {
         return this.shouldRender() && super.HHRRRCCCHIOCOCRHHHRIHHCCRHORRI();
      }

      public boolean method4(boolean flag1) {
         return this.shouldRender() && super.method4(flag1);
      }

      private boolean shouldRender() {
         if (!(Boolean)SkyblockHoppityEggHud.this.field24.get()) {
            return false;
         } else if (!(Boolean)SkyblockHoppityEggHud.this.field25.get() && !IslandUtils.isOnIsland()) {
            return false;
         } else {
            return !SkyblockCalendar.isSpring() ? false : ((Set)SkyblockHoppityEggHud.this.field26.get()).contains(IslandUtils.getIsland().id());
         }
      }

      protected boolean method22() {
         return false;
      }

      protected boolean method20() {
         return false;
      }

      protected HudRowAlignment method16() {
         return HudRowAlignment.LEFT;
      }

      private HudLine method8(SkyblockHoppityEggHud.Type type1, boolean flag2) {
         int number3 = type1.time;
         long number4 = SkyblockCalendar.getElapsedSeconds(2);
         if (number4 > number3) {
            number3 += 2400;
         }

         long number6 = (number3 - number4) * 1000L;
         return this.method9(type1, flag2, number6);
      }

      private HudLine method9(SkyblockHoppityEggHud.Type type1, boolean flag2, long number3) {
         TextComponent text5 = (TextComponent)((TextComponent)type1.component.append(Component.text(": ")))
            .append(Component.text(TimeFormatting.method1(number3) + " ", NamedTextColor.YELLOW));
         if (flag2) {
            text5 = (TextComponent)text5.append(Component.text('✔', NamedTextColor.GREEN));
         } else {
            text5 = (TextComponent)text5.append(Component.text('✖', NamedTextColor.RED));
         }

         return new HudLine(type1.icon, text5);
      }
   }

   protected enum Type {
      BREAKFAST(Component.text("Breakfast", NamedTextColor.GOLD), SkyblockHoppityEggHud.field12, 350),
      LUNCH(Component.text("Lunch", NamedTextColor.BLUE), SkyblockHoppityEggHud.field13, 700),
      DINNER(Component.text("Dinner", NamedTextColor.GREEN), SkyblockHoppityEggHud.field14, 1050),
      BRUNCH(Component.text("Brunch", NamedTextColor.GOLD), SkyblockHoppityEggHud.field12, 1550),
      DEJEUNER(Component.text("Déjeuner", NamedTextColor.BLUE), SkyblockHoppityEggHud.field13, 1900),
      SUPPER(Component.text("Supper", NamedTextColor.GREEN), SkyblockHoppityEggHud.field14, 2250);

      private final TextComponent component;
      private final ResourceLocationBridge icon;
      private final int time;

      @Generated
      Type(TextComponent text3, ResourceLocationBridge horsestats144, int number5) {
         this.component = text3;
         this.icon = horsestats144;
         this.time = number5;
      }
   }
}
