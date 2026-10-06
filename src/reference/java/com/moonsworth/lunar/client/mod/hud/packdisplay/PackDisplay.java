package com.moonsworth.lunar.client.mod.hud.packdisplay;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ResourcePackBridge;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge8Extension33;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.event.resourcepack.EventResourcePackUpdate;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.mod.hud.shaderpackdisplay.ShaderPackDisplay;

public class PackDisplay extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("(?i)§[0-9A-F]");
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("packIcon").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("packDescription").method31();
   private final ToggleOption field11 = (ToggleOption)OptionFactory.method7("packExtension").method31();
   private final EnumOption<PackDisplay.Type> field12 = (EnumOption<PackDisplay.Type>)OptionFactory.method10("packOrder", PackDisplay.Type.FIRST)
      .method31();
   private final EnumOption<PackDisplay.PackTextMode> field13 = (EnumOption<PackDisplay.PackTextMode>)OptionFactory.method10(
         "titleReplacement", PackDisplay.PackTextMode.WHITE_ONLY
      )
      .method31();
   private final ColorOption field14 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
               "textColor"
            )
            .method14(() -> Client.method109().method67().method2("settings", "titleReplacementColor", new Object[0])))
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final EnumOption<PackDisplay.PackTextMode> field15 = (EnumOption<PackDisplay.PackTextMode>)OptionFactory.method10(
         "descriptionReplacement", PackDisplay.PackTextMode.NONE
      )
      .method31();
   private final ColorOption field16 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "descriptionReplacementColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("moveTitleDown").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("keepBold").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("keepItalic").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("keepUnderline").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field21 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("keepStrikethrough").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field22 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("keepObfuscated").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   @Nullable
   private ResourceLocationBridge field23;
   @Nullable
   private List<String> field24;
   private ResourcePackBridge field25;
   @Nullable
   private final ResourceLocationBridge field26;

   public PackDisplay() {
      super(false);
      this.method7(ModTraits.field1, new PackDisplay.Data());
      this.handle(EventResourcePackUpdate.class, arg1x -> this.method2(arg1x.method1()));
      Optional optional1 = this.mc.bridge$getMcDefaultResourcePack().bridge$getPackImage();
      Bridge8Extension33 bridge8extension332 = optional1.<Bridge8Extension33>map(arg0 -> Bridge.method8().method22(arg0)).orElse(null);
      if (bridge8extension332 == null) {
         LunarLogger.method5("Failed to load vanilla pack thumbnail", new Object[0]);
         this.field26 = null;
      } else {
         this.field26 = this.mc.bridge$getTextureManager().bridge$getDynamicTextureLocation("default-pack-display-thumbnail", bridge8extension332);
      }

      this.field12.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.method2(this.mc.bridge$getSelectedResourcePack()));
   }

   public String getId() {
      return "PACK_DISPLAY";
   }

   public void method3(boolean flag1) {
      if (flag1) {
         this.method2(this.mc.bridge$getSelectedResourcePack());
      }
   }

   private void method2(ResourcePackBridge bridge141) {
      if (this.field12.get() == PackDisplay.Type.FIRST) {
         List list2 = this.mc.bridge$getAllSelectedPacks();
         if (!list2.isEmpty()) {
            bridge141 = (ResourcePackBridge)list2.get(list2.size() - 1);
         }
      }

      this.field23 = this.method3(bridge141);
      this.field24 = this.method4(bridge141);
      this.field25 = bridge141;
   }

   @Nullable
   private ResourceLocationBridge method3(ResourcePackBridge bridge141) {
      Optional optional2 = bridge141.bridge$getPackImage();
      if (optional2.isPresent()) {
         Bridge8Extension33 bridge8extension333 = Bridge.method8().method22((BufferedImage)optional2.get());
         return this.mc.bridge$getTextureManager().bridge$getDynamicTextureLocation("pack-display-thumbnail", bridge8extension333);
      } else {
         return this.field26;
      }
   }

   @Nullable
   private List<String> method4(ResourcePackBridge bridge141) {
      if (bridge141 != null && !bridge141.bridge$getPackName().equals("textures")) {
         Bridge2_42 bridge2_422;
         try {
            bridge2_422 = bridge141.bridge$getDescription();
         } catch (IOException exception4) {
            return null;
         }

         return bridge2_422 == null
            ? null
            : Ref.method10().bridge$wrapLines(bridge2_422, 256).stream().map(arg0 -> TextBridge.asLegacyString(TextBridge.asAdventure(arg0))).toList();
      } else {
         return List.of(TextBridge.asLegacyString(TextBridge.asAdventure(this.method2("defaultDescription", new Object[0]))));
      }
   }

   protected List<Framework7Extension> method9() {
      return List.of(new ShaderPackDisplay(this));
   }

   private class Data extends TypedHudRenderer<Void> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_RIGHT);
      }

      public HudSize method15() {
         return HudSize.method1(12, 24, 64, 60, 100, 300);
      }

      @Nullable
      public Void method2(boolean flag1) {
         return null;
      }

      public boolean method4(boolean flag1) {
         return true;
      }

      public void method1(RootSettingsBuilder lightingextension231) {
         lightingextension231.method7(
            SettingsPage.GENERAL,
            arg1x -> {
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
                  new ClientOption[]{this.ICHRCHCIHROCHRCIRCCCCHCRCCCHOH(), PackDisplay.this.field9, PackDisplay.this.field10, PackDisplay.this.field11}
               );
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{PackDisplay.this.field17}).method3(PackDisplay.this.field10::get);
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{PackDisplay.this.field12});
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{PackDisplay.this.field13, PackDisplay.this.field15});
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.ORHRIHRICHICRCOCIIRIOICOIICHOI});
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.HOHRIORRHIIIOOIHOOOCOHOHOHCHII})
                  .method3(this.HIOICORHOCCRCOIHCRIIROIOIOIRIC::get);
               arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.HIOICORHOCCRCOIHCRIIROIOIOIRIC,
                  arg1xx -> {
                     arg1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.OCRCICOROIRHIOHCCIHHICORCHCOOO});
                     arg1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                        this.OIRIICOHRIHHOCCCIICCRRCICOOCHI,
                        arg1xxx -> arg1xxx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.HCHIRRHHICRCCIOOHCOICHHIORICHH})
                     );
                  }
               );
            }
         );
         lightingextension231.method7(
            SettingsPage.COLOR,
            arg1x -> {
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{PackDisplay.this.field14})
                  .method3(() -> PackDisplay.this.field13.get() == PackDisplay.PackTextMode.NONE);
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{PackDisplay.this.field16})
                  .method3(() -> PackDisplay.this.field15.get() == PackDisplay.PackTextMode.NONE);
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.OOOCCCRICCHOORCCRHHRHHCOOCORRC})
                  .method1(new ClientOption[]{this.HOHRIORRHIIIOOIHOOOCOHOHOHCHII});
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.HROIRCHIHORCHCHCRICOOOIOOHIRCH})
                  .method1(new ClientOption[]{this.HIOICORHOCCRCOIHCRIIROIOIOIRIC});
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.CRROIIOHCOROIIOROHHCHIRRCORCRH})
                  .method1(new ClientOption[]{this.OIRIICOHRIHHOCCCIICCRRCICOOCHI});
               arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
                  new ClientOption[]{
                     PackDisplay.this.field18, PackDisplay.this.field19, PackDisplay.this.field20, PackDisplay.this.field21, PackDisplay.this.field22
                  }
               );
            }
         );
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         MixinHelper_4 mixinhelper_45 = highlightimpl1.method2();
         String text6 = "";
         boolean flag7 = (Boolean)PackDisplay.this.field10.get() && PackDisplay.this.field24 != null && !PackDisplay.this.field24.isEmpty();
         if (PackDisplay.this.field25 != null
            && !PackDisplay.this.field25.bridge$getPackName().equals("textures")
            && !PackDisplay.this.field25.bridge$getPackName().equals("vanilla")) {
            text6 = PackDisplay.this.field25.bridge$getPackName();
            if (!(Boolean)PackDisplay.this.field11.get()) {
               text6 = text6.replace(".zip", "");
            }
         } else {
            text6 = PackDisplay.this.method2("default", new Object[0]);
         }

         text6 = this.method6(text6, false);
         boolean flag8 = (Boolean)this.HIOICORHOCCRCOIHCRIIROIOIOIRIC.get();
         boolean flag9 = !flag8 && (Boolean)this.HOHRIORRHIIIOOIHOOOCOHOHOHCHII.get();
         if (flag9) {
            text6 = "[" + text6 + "]";
         }

         float value10 = Ref.method10().bridge$getStringWidth(text6);
         int number11 = Ref.method10().method19();
         float value12 = number11;
         if (flag7 || !(Boolean)PackDisplay.this.field17.get() && PackDisplay.this.field24 != null) {
            value12 += PackDisplay.this.field24.size() * (number11 + 2.0F);
            if (flag7) {
               for (String text14 : PackDisplay.this.field24) {
                  value10 = Math.max(value10, Ref.method10().bridge$getStringWidth(text14));
               }
            }
         }

         float value29 = Math.max(value12, ((Integer)this.OCRCICOROIRHIOHCCIHHICORCHCOOO.get()).intValue()) + 4.0F;
         boolean flag30 = (Boolean)PackDisplay.this.field9.get() && PackDisplay.this.field23 != null;
         value10 += 8.0F + (flag30 ? value29 : 0.0F);
         this.method12(value10, value29);
         float value15 = value29 - value12;
         float value16 = value3 + value15 / 2.0F;
         float value17 = 4.0F + (flag30 ? value2 + value29 : value2);
         if (flag8) {
            this.HROIRCHIHORCHCHCRICOOOIOOHIRCH.method11(mixinhelper_45, value2, value3, value10, this.getHeight());
            if ((Boolean)this.OIRIICOHRIHHOCCCIICCRRCICOOCHI.get()) {
               this.CRROIIOHCOROIIOROHHCHIRRCORCRH.method11(mixinhelper_45, this, value2, value3, value10, this.getHeight(), (Float)this.HCHIRRHHICRCCIOOHCOICHHIORICHH.get());
            }
         }

         if (flag30) {
            LcuiScreen.method39(mixinhelper_45, PackDisplay.this.field23, this.getHeight() / 2.0F, value2, value3, -1);
         }

         boolean flag18 = (Boolean)this.ORHRIHRICHICRCOCIIRIOICOIICHOI.get();
         ColorOption lightingextension422219 = PackDisplay.this.field13.get() == PackDisplay.PackTextMode.NONE ? null : PackDisplay.this.field14;
         if (lightingextension422219 != null) {
            lightingextension422219.method43(
               mixinhelper_45, text6, value17, value16, (Boolean)this.ORHRIHRICHICRCOCIIRIOICOIICHOI.get(), flag9 ? this.OOOCCCRICCHOORCCRHHRHHCOOCORRC : null
            );
         } else if (flag9) {
            text6 = text6.substring(1, text6.length() - 1);
            mixinhelper_45.method19(Ref.method10(), "[", value17, value16, this.OOOCCCRICCHOORCCRHHRHHCOOCORRC.method14(value16), flag18);
            float value20 = value17;
            value20 += Ref.method10().bridge$getStringWidth("[");
            mixinhelper_45.method19(Ref.method10(), text6, value20, value16, -1, flag18);
            value20 += Ref.method10().bridge$getStringWidth(text6);
            mixinhelper_45.method19(Ref.method10(), "]", value20, value16, this.OOOCCCRICCHOORCCRHHRHHCOOCORRC.method14(value20 + value16), flag18);
         } else {
            mixinhelper_45.method19(Ref.method10(), text6, value17, value16, -1, flag18);
         }

         if (flag7) {
            float value33 = value16 + number11 + 2.0F;
            PackDisplay.PackTextMode type221 = (PackDisplay.PackTextMode)PackDisplay.this.field15.get();
            int number22 = type221 == PackDisplay.PackTextMode.NONE ? -1 : PackDisplay.this.field16.method13();

            for (String text24 : PackDisplay.this.field24) {
               mixinhelper_45.method19(Ref.method10(), this.method6(text24, true), value17, value33, number22, (Boolean)this.ORHRIHRICHICRCOCIIRIOICOIICHOI.get());
               value33 += number11 + 2;
            }
         }
      }

      private String method6(String text1, boolean flag2) {
         if (!(Boolean)PackDisplay.this.field18.get() && text1.contains("§l")) {
            text1 = text1.replace("§l", "");
         }

         if (!(Boolean)PackDisplay.this.field19.get() && text1.contains("§o")) {
            text1 = text1.replace("§o", "");
         }

         if (!(Boolean)PackDisplay.this.field20.get() && text1.contains("§n")) {
            text1 = text1.replace("§n", "");
         }

         if (!(Boolean)PackDisplay.this.field21.get() && text1.contains("§m")) {
            text1 = text1.replace("§m", "");
         }

         if (!(Boolean)PackDisplay.this.field22.get() && text1.contains("§k")) {
            text1 = text1.replace("§k", "");
         }

         if (!flag2 && PackDisplay.this.field13.get() == PackDisplay.PackTextMode.FULL || flag2 && PackDisplay.this.field15.get() == PackDisplay.PackTextMode.FULL) {
            text1 = PackDisplay.field8.matcher(text1).replaceAll("");
         }

         return text1;
      }
   }

   private enum Type implements OptionEnumValue {
      FIRST("first"),
      LAST("last");

      private final String id;

      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.method8(this.id, new Object[0]);
      }

      @Generated
      Type(String text3) {
         this.id = text3;
      }
   }

   private enum PackTextMode implements OptionEnumValue {
      NONE("none"),
      FULL("full_text"),
      WHITE_ONLY("white_only_text");

      private final String id;

      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.method8(this.id, new Object[0]);
      }

      @Generated
      PackTextMode(String text3) {
         this.id = text3;
      }
   }
}
