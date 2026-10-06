package com.moonsworth.lunar.client.mod.skyblock.chatcommands;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.SpacerWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework11;
import com.moonsworth.lunar.client.framework.mod.Framework2;
import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing3Impl;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing3Impl10;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing3Impl11;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing3Impl12;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing3Impl13;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing3Impl2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing3Impl3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing3Impl4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing3Impl5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing3Impl6;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing3Impl7;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing3Impl8;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing3Impl9;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing3Iterator2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing3Iterator_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing3_3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing6;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Gui2Extension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click8;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler25;
import com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.GuiRewindhandlersHandler22;
import com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.Rewindhandlers3;
import com.moonsworth.lunar.client.event.mixin.EventNameplateExtensionLegacy;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.LabelOption;
import com.moonsworth.lunar.client.config.option.MultiSelectOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import lombok.Generated;
import org.apache.commons.lang3.StringUtils;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockChatCommands extends AbstractFeature {
   private static final List<Fishing3_3> field8 = List.of(
      new Fishing3Impl7(),
      new Fishing3Iterator_2(),
      new Fishing3Impl5(),
      new Fishing3Impl11(),
      new Fishing3Iterator2(),
      new Fishing3Impl4(),
      new Fishing3Impl3(),
      new Fishing3Impl(),
      new Fishing3Impl12(),
      new Fishing3Impl6(),
      new Fishing3Impl9(),
      new Fishing3Impl8(),
      new Fishing3Impl10(),
      new Fishing3Impl2(),
      new Fishing3Impl13()
   );
   private final GuiRewindhandlersHandler2 field9 = (GuiRewindhandlersHandler2)this.method19(GuiRewindhandlersHandler2.class);
   private final GuiRewindhandlersHandler22 field10 = (GuiRewindhandlersHandler22)this.method19(GuiRewindhandlersHandler22.class);
   private final GuiRewindhandlersHandler25 field11 = (GuiRewindhandlersHandler25)this.method19(GuiRewindhandlersHandler25.class);
   private final TextOption field12 = (TextOption)((TextOption.Data)OptionFactory.method12("commandPrefix")
         .method2("!"))
      .method31();
   private final Map<Fishing3_3, Fishing6> field13 = new HashMap<>();
   private final Map<String, Fishing3_3> field14 = new HashMap<>();
   private final Click8<String> field15 = new Click8<>(5);

   public SkyblockChatCommands(Skyblock var1) {
      super(false);
      this.method14(Framework.field16, Framework4.method3(var1));
      this.method14(Framework.field17, Framework2.method2(SettingsPage.CHAT));
      this.method14(Framework.field19, Framework11.method1(this, Click3::hasIsland));
      this.handle(EventNameplateExtensionLegacy.class, this::method1);
      this.handle(EventClientTick.class, this::method2);

      for (Fishing3_3 var3 : field8) {
         this.field14.put(var3.getCommand(), var3);

         for (String var5 : var3.getAliases()) {
            this.field14.put(var5, var3);
         }
      }
   }

   private void method1(EventNameplateExtensionLegacy var1) {
      String var2 = this.field12.get();
      Rewindhandlers.Extension var3 = var1.method1();
      String var4 = var3.message();
      if (var4.startsWith(var2)) {
         String var5 = var4.substring(var2.length());
         if (!var5.isEmpty()) {
            String[] var6 = var5.split("\\s+");
            String var7 = var6[0].toLowerCase(Locale.ROOT);
            Fishing3_3 var8 = this.field14.get(var7);
            if (var8 != null) {
               Fishing6 var9 = this.field13.get(var8);
               if (var9.isEnabled()) {
                  if (!var3.method2() || var9.method1()) {
                     Gui2Extension var10 = Gui2Extension.of(var3);
                     if (var10 != null && var9.method2(var10)) {
                        if (var8.method2()) {
                           Rewindhandlers3 var11 = this.field9.method7().orElse(null);
                           if (var11 == null) {
                              return;
                           }

                           if (!this.field11.method5().equals(var11.method2())) {
                              return;
                           }
                        }

                        String[] var12 = var6.length > 1 ? Arrays.copyOfRange(var6, 1, var6.length) : new String[0];
                        var8.method6(var7, var1.method1().method1(), var12, var10);
                     }
                  }
               }
            }
         }
      }
   }

   private void method2(EventClientTick var1) {
      Collection var2 = this.field15.next();
      Bridge5Extension_5 var3 = ThreadModuleDump63.method7();
      if (var3 != null) {
         for (String var5 : var2) {
            ThreadModuleDump63.method7().bridge$sendChatMessage(var5);
         }
      }
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.GENERAL,
         var1x -> {
            var1x.method9(new ClientOption[]{this.field12});

            for (Fishing3_3 var3 : field8) {
               String var4 = StringUtils.capitalize(var3.getCommand());
               ToggleOption var5 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("chatCommand" + var4 + "Enabled")
                     .method4(var3.isEnabledByDefault()))
                  .method31();
               LabelOption var6 = this.method6("chatCommand" + var4 + "Info", var3);
               ToggleOption var7 = this.method4(
                  "chatCommand" + var4 + "AllowSelfCommand", var3.method3(), ThreadModuleDump63.method4().method67().method2("settings", "allowSelfCommand")
               );
               MultiSelectOption var8 = this.method5(
                  "chatCommand" + var4 + "ChatTypes", new ArrayList<>(Gui2Extension.ids()), var3.method5(), "chatCommandChatTypes"
               );
               this.field13.put(var3, new Fishing6(var5, var7, var8));
               var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(var5, var3x -> var3x.method9(new ClientOption[]{var6, var7, var8}));
            }
         }
      );
   }

   private ToggleOption method4(@Annotation(method1 = Annotation.Type.SETTING) String var1, boolean var2, String var3) {
      return (ToggleOption)((ToggleOption.ToggleOptionBuilder)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7(var1).method4(var2))
            .ROICHOCCIOCHCIHOIHIHICCORIROCC(var3))
         .method31();
   }

   private MultiSelectOption method5(
      @Annotation(method1 = Annotation.Type.SETTING) String var1, Collection<String> var2, Set<String> var3, String var4
   ) {
      return (MultiSelectOption)((MultiSelectOption.Data)((MultiSelectOption.Data)OptionFactory.method27(var1)
               .method3(var2)
               .method2(var3))
            .method6(var0 -> ThreadModuleDump63.method4().method67().method2("settings", var0))
            .OIRHORRROCHOIRCRHHORHRCIIRHROO(var4))
         .method31();
   }

   private LabelOption method6(@Annotation(method1 = Annotation.Type.SETTING_LABELS) String var1, Fishing3_3 var2) {
      String var3 = String.join(", ", var2.method1());
      return (LabelOption)((LabelOption.Data)OptionFactory.method15(var1)
            .HORHROIOIOICIRHIOCOICHHHIHCIIO((var2x, var3x) -> new SpacerWidget(var2x, var3x) {
               @Override
               public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2x, boolean var3xx) {
                  FontRegistry.method11().method13(var1, this.option.get() + " (" + var3 + ")", this.x, this.y + 3.0F, -2894893);
               }

               @Override
               public float getHeight() {
                  return this.getOption().isHidden() ? 0.0F : 14.0F;
               }
            }))
         .method31();
   }

   @Override
   public String getId() {
      return "SKYBLOCK_CHAT_COMMANDS";
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method1(Calculator2Handler.field5).method11(this);
   }

   @Generated
   public GuiRewindhandlersHandler2 method13() {
      return this.field9;
   }

   @Generated
   public GuiRewindhandlersHandler22 method14() {
      return this.field10;
   }

   @Generated
   public Click8<String> method15() {
      return this.field15;
   }
}
