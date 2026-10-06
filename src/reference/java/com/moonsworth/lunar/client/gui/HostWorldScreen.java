package com.moonsworth.lunar.client.gui;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.glintcolorizer.Glintcolorizer;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.gui.ConfirmScreen;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.ui.widget.TextLabelWidget;
import com.moonsworth.lunar.client.ui.widget.ProgressBarWidget;
import com.moonsworth.lunar.client.coordinates.Coordinates5;
import com.moonsworth.lunar.client.coordinates.Gui2Handler;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.KeyCombo;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump79;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HostWorldScreen extends com.moonsworth.lunar.client.ui.LcuiPopupScreen {
   private final String field25;
   private ToggleOption field26;
   private TextOption field27;
   private EnumOption<HostWorldScreen.Type> field28;
   private EnumOption<HostWorldScreen.Type2> field29;
   private EnumOption<HostWorldScreen.Type4> field30;
   private ToggleOption field31;
   private IntegerOption field32;
   private final TextLabelWidget field33;
   private final TextLabelWidget field34;
   private final ProgressBarWidget field35;
   private final boolean field36 = ThreadModuleDump63.method4().method81().method28() != null;
   private HostWorldScreen.Type3 field37 = HostWorldScreen.Type3.VALID;
   private int field38 = 0;

   public HostWorldScreen(String var1) {
      super(256.0F, 272.0F);
      this.field24 = 30.0F;
      this.field25 = var1;
      this.field35 = new ProgressBarWidget(null, ResourceLocationBridge.create("lunar", "icons/mainmenu/exit-17x17.png"));
      this.field35.method7(6.0F);
      this.field35.method9(6.0F);
      this.field33 = new TextLabelWidget(null, this.field36 ? "save" : "hostWorld");
      this.field33
         .method15(
            () -> this.field37 != HostWorldScreen.Type3.VALID
               ? ThreadModuleDump23.method10(128, 128, 128, 255)
               : ThreadModuleDump23.method10(255, 255, 255, 255)
         );
      this.field34 = new TextLabelWidget(null, "closeWorld");
      this.field34.method12(new AnimatedValue(ThreadModuleDump23.method10(255, 77, 79, 38), ThreadModuleDump23.method10(255, 77, 79, 76)));
   }

   @Override
   protected List<GuiWidget> method25() {
      return ImmutableList.of();
   }

   @Override
   protected List<ClientOption<?>> method2() {
      int var1 = ThreadModuleDump79.findFreePort();
      boolean var2 = ThreadModuleDump63.method4().method81().method28() != null;
      if (var1 <= 0) {
         var1 = 25564;
      }

      Coordinates5 var3 = ThreadModuleDump63.method4().method81().method29();
      Glintcolorizer var4 = ThreadModuleDump63.method3().bridge$getIntegratedServer();
      boolean var5 = true;
      int var6 = var1;
      HostWorldScreen.Type var7 = HostWorldScreen.Type.Survival;
      HostWorldScreen.Type2 var8 = HostWorldScreen.Type2.Normal;
      HostWorldScreen.Type4 var9 = HostWorldScreen.Type4.InviteOnly;
      boolean var10 = false;
      int var11 = 12;
      if (var3 != null) {
         var5 = var3.method2();
         var6 = var3.getPort();
         var7 = var3.method4();
         var8 = var3.method5();
         var9 = !var2 && var3.method7() != null ? var3.method7() : var3.method6();
         var10 = var3.method8();
         var11 = var3.method9();
      }

      if (!var2 && var4 != null) {
         var5 = var4.bridge$isAllowCheats();
         var7 = HostWorldScreen.Type.values()[var4.bridge$getGameType().getId()];
         var8 = HostWorldScreen.Type2.byId(var4.bridge$getDifficulty().getId());
      }

      int var12 = ThreadModuleDump63.method4().method81().getMaxWorldPlayerCount();
      if (var11 > var12) {
         var11 = var12;
      }

      ArrayList var13 = new ArrayList();
      var13.add(OptionFactory.method15("worldOptions").method31());
      var13.add(
         this.field26 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("allowCheats").OOOIROIIOCOOHICRIRHHHRROHHHHIO(var5)).method31()
      );
      var13.add(this.field28 = (EnumOption<HostWorldScreen.Type>)OptionFactory.method10("gameMode", var7).method31());
      var13.add(this.field29 = (EnumOption<HostWorldScreen.Type2>)OptionFactory.method10("difficulty", var8).method31());
      var13.add(
         this.field32 = (IntegerOption)((Data)((Data)OptionFactory.method4("maxSlots").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(var11))
               .method7(2, var12))
            .method31()
      );
      var13.add(OptionFactory.method15("privacySettings").method31());
      var13.add(this.field30 = (EnumOption<HostWorldScreen.Type4>)OptionFactory.method10("worldPrivacy", var9).method31());
      var13.add(
         this.field31 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("autoHostOnJoin").OOOIROIIOCOOHICRIRHHHRROHHHHIO(var10))
            .method31()
      );
      this.field27 = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)OptionFactory.method12("customPort")
            .HIIIOHRRROCICIOIORRRIRCRCHHIII(String.valueOf(var6)))
         .method31();
      if (!var2) {
         var13.add(OptionFactory.method15("advancedSettings").method31());
         var13.add(this.field27);
      }

      return var13;
   }

   @Override
   public void init() {
      this.field35
         .RIIICIRHRCIHOOOORHOICRIICCCRHR(
            this.field21 + this.field19 - 17.5F, this.field22 + 6.0F, 14.0F, 14.0F
         );
      float var1 = this.field36 ? 50.0F : 82.0F;
      this.field33
         .RIIICIRHRCIHOOOORHOICRIICCCRHR(
            this.field21 + this.field19 - (7.0F + var1),
            this.field22 + this.field20 - 22.0F,
            var1,
            18.0F
         );
      this.field34
         .RIIICIRHRCIHOOOORHOICRIICCCRHR(
            this.field21 + this.field19 - 152.0F,
            this.field22 + this.field20 - 22.0F,
            92.0F,
            18.0F
         );
   }

   @Override
   public void update() {
      this.method3();
   }

   private void method3() {
      try {
         int var1 = Integer.parseInt((String)this.field27.get());
         if (var1 < 1024 || var1 > 65535) {
            this.field37 = HostWorldScreen.Type3.INVALID;
         } else if (this.field38 != var1) {
            this.field38 = var1;
            this.field37 = !this.field36 && ThreadModuleDump79.isPortOpen(var1) ? HostWorldScreen.Type3.ALREADY_IN_USE : HostWorldScreen.Type3.VALID;
         } else if (this.field37 == HostWorldScreen.Type3.INVALID) {
            this.field37 = HostWorldScreen.Type3.VALID;
         }
      } catch (NumberFormatException var2) {
         this.field37 = HostWorldScreen.Type3.INVALID;
      }
   }

   @Override
   public void method10(MixinHelper_4 var1, MarkerModel.Data2 var2) {
      var1.method44(var0 -> var0.method29().method14());
      com.moonsworth.lunar.client.ui.LcuiScreen.method94(
         var1, this.field21, this.field22 + 24.0F, this.field19, 0.5F, 553648127
      );
      com.moonsworth.lunar.client.ui.LcuiScreen.method103(
         var1, this.field21, this.field22 + 1.0F, this.field19, 23.0F, 5.0F, 620756992
      );
      com.moonsworth.lunar.client.ui.LcuiScreen.method56(
         var1,
         this.field21 - 1.0F,
         this.field22,
         this.field19 + 2.0F,
         this.field20,
         4.0F,
         1073741824
      );
      com.moonsworth.lunar.client.ui.LcuiScreen.method56(
         var1,
         this.field21,
         this.field22 + 1.0F,
         this.field19,
         this.field20 - 2.0F,
         3.0F,
         553648127
      );
      com.moonsworth.lunar.client.ui.LcuiScreen.method117(
         var1,
         this.field21,
         this.field22 + 1.0F,
         this.field19,
         this.field20 - 2.0F,
         5.0F,
         Integer.MIN_VALUE
      );
      com.moonsworth.lunar.client.ui.LcuiScreen.method101(
         var1,
         this.field21,
         this.field22 + this.field20 - 25.0F,
         this.field19,
         24.0F,
         5.0F,
         1073741824,
         false,
         false,
         true,
         true
      );
      float var3 = this.method22() / 2.0F;
      float var4 = this.method23() / 2.0F - (this.field20 / 2.0F - 6.0F);
      FontRegistry.method19()
         .method14(
            var1,
            this.method1("title", new Object[]{ThreadModuleDump63.method3().bridge$getPlayer().bridge$getDisplayName()}),
            var3,
            var4,
            -1
         );
      if (this.field37 == HostWorldScreen.Type3.INVALID) {
         FontRegistry.method11()
            .method13(
               var1,
               this.method1("portError", new Object[0]),
               this.field21 + 15.0F,
               var4 + 212.0F,
               ThreadModuleDump23.method10(200, 20, 0, 255)
            );
      } else if (this.field37 == HostWorldScreen.Type3.ALREADY_IN_USE) {
         FontRegistry.method11()
            .method13(
               var1,
               this.method1("portErrorInUse", new Object[0]),
               this.field21 + 15.0F,
               var4 + 212.0F,
               ThreadModuleDump23.method10(225, 0, 0, 255)
            );
      }

      this.field35.method3(var1, var2, true);
      if (!ThreadModuleDump63.method4().method81().method33() && !this.field36) {
         FontRegistry.method13()
            .method14(
               var1,
               this.method1("hostedDisabled", new Object[0]),
               this.method22() / 2.0F,
               this.field22 + this.field20 - 18.0F,
               ThreadModuleDump23.method10(225, 0, 0, 255)
            );
      } else {
         this.field33.method3(var1, var2, true);
         if (this.field36) {
            this.field34.method3(var1, var2, true);
         }
      }

      HostWorldScreen.Type4 var5 = (HostWorldScreen.Type4)this.field30.get();
      if (var5 == HostWorldScreen.Type4.InviteOnly) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method140(
            var1,
            this.field36 ? "inviteOnlyHostedHint" : "inviteOnlyHint",
            this,
            (KeyCombo)Client.method109().method41().method8().method22().get(),
            FontRegistry.method11(),
            var3,
            var4 + this.field20 - 46.0F,
            true,
            -1862270977,
            -1358954496,
            -1325400065
         );
      } else {
         String var6 = switch (var5) {
            case Everyone -> this.field36 ? "everyoneHostedHint" : "everyoneHint";
            case Friends -> this.field36 ? "friendsHostedHint" : "friendsHint";
            case LAN -> this.field36 ? "lanHostedHint" : "lanHint";
            case Closed -> this.field36 ? "closedHostedHint" : "closedHint";
            case InviteOnly -> throw new IllegalArgumentException("Should be dealt with above");
         };
         FontRegistry.method11()
            .method14(var1, this.method1(var6, new Object[0]), var3, var4 + this.field20 - 46.0F, -1862270977);
      }

      var1.method44(var0 -> var0.method29().method15());
      super.method10(var1, var2);
   }

   @Override
   public String getLanguagePath() {
      return super.getLanguagePath() + "." + this.field25;
   }

   @Override
   public void method11(MarkerModel.Data2 var1, int var2) {
      this.method3();
      if (this.field33.method3(var1) && this.field37 == HostWorldScreen.Type3.VALID && ThreadModuleDump63.method4().method81().method33()) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method15();
         this.method6();
         ThreadModuleDump63.method3().bridge$displayScreen(null);
      } else if (this.field36 && this.field34.method3(var1)) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method15();
         this.method10();
      } else if (this.field35.method3(var1)) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method15();
         ThreadModuleDump63.method3().bridge$displayScreen(null);
      }
   }

   private void method6() {
      Coordinates5 var1 = ThreadModuleDump63.method4().method81().method29();
      Coordinates5 var2 = Coordinates5.method1()
         .method1((Boolean)this.field26.get())
         .method5((HostWorldScreen.Type2)this.field29.get())
         .method4((HostWorldScreen.Type)this.field28.get())
         .method3(Integer.parseInt((String)this.field27.get()))
         .method9((Integer)this.field32.get())
         .method6((HostWorldScreen.Type4)this.field30.get())
         .method7(this.field36 && var1 != null && var1.method7() != null ? var1.method7() : (HostWorldScreen.Type4)this.field30.get())
         .method8((Boolean)this.field31.get())
         .method10();
      if (!this.field36) {
         ThreadModuleDump63.method4().method81().method5(var2);
      } else {
         ThreadModuleDump63.method4().method81().method9(var2);
      }
   }

   private void method10() {
      String var1 = "closeWorldPrompt";
      Set var2 = ThreadModuleDump63.method4().method81().method30();
      long var3;
      synchronized (var2) {
         var3 = var2.stream().filter(Gui2Handler::method2).count() - 1L;
      }

      if (var3 <= 0L) {
         ThreadModuleDump63.method4().method81().method4();
         ThreadModuleDump63.method3().bridge$displayScreen(null);
         ThreadModuleDump63.method4().method69().method3(NotificationManager.method15("closeWorldNotification"));
      } else {
         if (var3 > 1L) {
            var1 = "closeWorldPromptPlural";
         }

         Bridge5Extension6 var5 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
         ConfirmScreen var6 = new ConfirmScreen(var1, var1x -> {
            if (!var1x) {
               ThreadModuleDump63.method3().bridge$displayScreen(var5);
            } else {
               ThreadModuleDump63.method4().method81().method4();
               ThreadModuleDump63.method3().bridge$displayScreen(null);
               ThreadModuleDump63.method4().method69().method3(NotificationManager.method15("closeWorldNotification"));
            }
         });
         var6.method7(var3);
         ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(var6));
      }
   }

   @Override
   public void method12(MarkerModel.Data2 var1, int var2) {
   }

   @Override
   public void method14(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }

   public enum Type implements OptionEnumValue {
      Survival,
      Creative,
      Adventure,
      Spectator;

      public String id() {
         return this.name();
      }
   }

   public enum Type2 implements OptionEnumValue {
      Easy(1, "easy"),
      Normal(2, "normal"),
      Hard(3, "hard"),
      Peaceful(0, "peaceful");

      public final int id;
      public final String key;

      Type2(int var3, String var4) {
         this.id = var3;
         this.key = var4;
      }

      public String id() {
         return this.name();
      }

      public static HostWorldScreen.Type2 byId(int var0) {
         for (HostWorldScreen.Type2 var4 : values()) {
            if (var4.id == var0) {
               return var4;
            }
         }

         return Normal;
      }
   }

   public enum Type3 {
      VALID,
      INVALID,
      ALREADY_IN_USE;
   }

   public enum Type4 implements OptionEnumValue {
      Everyone("privacyEveryone"),
      InviteOnly("privacyInviteOnly"),
      Friends("privacyFriends"),
      LAN("privacyLAN"),
      Closed("privacyClosed");

      public final String id;

      Type4(String var3) {
         this.id = var3;
      }

      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.method1(this.id, new Object[0]);
      }
   }
}
