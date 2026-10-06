package com.moonsworth.lunar.client.mod.misc.autotextactions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.autotextactions.AutoTextAction;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.ui.notification.DesktopNotifier;
import com.moonsworth.lunar.client.framework.Ref;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import com.moonsworth.lunar.client.mod.misc.chat.Chat;

public class AutoTextActions extends AbstractFeature {
   private final List<AutoTextAction> actions = new ArrayList<>();
   private final Set<String> field8 = new LinkedHashSet<>();
   private int field9 = -1;

   public AutoTextActions() {
      super(false);
      this.method8(TypedChatMessage.class, this::method3, 101);
      this.handle(EventRenderHudBase.class, this::method4);
      this.handle(EventTick.class, arg1 -> {
         if (!this.field8.isEmpty()) {
            if (this.mc.bridge$getPlayer() == null) {
               this.field8.clear();
            } else if (this.field9 >= 0) {
               this.field9--;
            }
         }
      });
   }

   public String getId() {
      return "AUTO_TEXT_ACTIONS";
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method1(new ModCategory[]{ModCategory.field6})
         .method2(new String[]{"alert", "alerts", "notification", "notifications"})
         .method11(this);
   }

   public void method4() {
      super.method4();
      this.actions.clear();
      this.field8.clear();
      this.method13();
   }

   private void method3(TypedChatMessage data1) {
      if (!data1.isCancelled()) {
         String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();

         for (AutoTextAction lighting3loader4 : this.actions) {
            if (lighting3loader4.matches(text2)) {
               if ((Boolean)lighting3loader4.field6.get()) {
                  data1.cancel();
               }

               if ((Boolean)lighting3loader4.field11.get()) {
                  this.mc.bridge$getSoundHandler().method1(Chat.field9);
               }

               if (!lighting3loader4.field9.isHidden() && (Boolean)lighting3loader4.field9.get()) {
                  DesktopNotifier.method2(this.method13("notificationTitle", new Object[0]), text2, MessageType.INFO);
               }

               if (!lighting3loader4.field10.isHidden() && (Boolean)lighting3loader4.field10.get()) {
                  this.field4.method69().method6(NotificationType.INFO, this.method13("notificationTitle", new Object[0]), text2);
               }

               if ((Boolean)lighting3loader4.field7.get()) {
                  String text5 = (String)lighting3loader4.field8.get();
                  if (text5 != null) {
                     text5 = text5.trim();
                     if (!text5.isEmpty()) {
                        text5 = ChatFormatting.getTextWithFormattingCodesFromAmpersand(text5);
                        this.field8.add(text5);
                     }
                  }
               }
            }
         }
      }
   }

   private void method4(EventRenderHudBase highlightimpl1) {
      if (this.field8.isEmpty()) {
         this.field9 = -1;
      } else {
         String text2 = this.field8.iterator().next();
         if (this.field9 == 0) {
            this.field8.remove(text2);
            this.field9 = -1;
            if (this.field8.isEmpty()) {
               return;
            }
         }

         byte number3 = 10;
         byte number4 = 10;
         byte number5 = 40;
         if (this.field9 == -1) {
            this.field9 = 60;
         } else if (this.field9 > 0) {
            MixinHelper_4 mixinhelper_46 = highlightimpl1.method2();
            float value7 = this.field9 - mixinhelper_46.method43();
            int number8 = 255;
            if (this.field9 > 50) {
               float value9 = 60.0F - value7;
               number8 = (int)(value9 * 255.0F / 10.0F);
            }

            if (this.field9 <= 10) {
               number8 = (int)(value7 * 255.0F / 10.0F);
            }

            number8 = Math.min(255, Math.max(number8, 0));
            if (number8 > 8) {
               this.mc.bridge$getGuiIngame().bridge$clearTitle();
               mixinhelper_46.push();
               mixinhelper_46.method39((float)(highlightimpl1.method3().method10() / 2.0), (float)highlightimpl1.method3().method11() / 2.0F);
               mixinhelper_46.scale(4.0F, 4.0F, 4.0F);
               mixinhelper_46.method19(
                  Ref.method10(),
                  text2,
                  -Ref.method10().bridge$getStringWidth(text2) / 2.0F,
                  -10.0F,
                  16777215 | number8 << 24 & 0xFF000000,
                  true
               );
               mixinhelper_46.pop();
            }
         }
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      for (AutoTextAction lighting3loader3 : this.actions) {
         lighting3loader3.validate();
         lightingextension231.method7(lighting3loader3::getDisplayName, arg2 -> arg2.HORHROIOIOICIRHIOCOICHHHIHCIIO(lighting3loader3.field1, arg2x -> {
            arg2x.method13("triggerSetup");
            arg2x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{lighting3loader3.field2, lighting3loader3.field3});
            arg2x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{lighting3loader3.field4, lighting3loader3.field5}).method3(lighting3loader3.field3::get);
            arg2x.HOHIHOCHHRCRRIIORHHOROHIROCCCC();
            arg2x.method13("actionSetup");
            arg2x.HORHROIOIOICIRHIOCOICHHHIHCIIO(lighting3loader3.field7, arg1xxx -> arg1xxx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{lighting3loader3.field8}));
            arg2x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{lighting3loader3.field6, lighting3loader3.field11});
            arg2x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{lighting3loader3.field10, lighting3loader3.field9}).method3(() -> !DesktopNotifier.method1());
            arg2x.HOHIHOCHHRCRRIIORHHOROHIROCCCC();
            arg2x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new OptionProvider[]{OptionFactory.method14("clone").method4(() -> {
               try {
                  AutoTextAction lighting3loader2xx = lighting3loader3.method1();
                  lighting3loader3.field1.method3(false);
                  lighting3loader2xx.field1.method3(true);
                  this.actions.add(lighting3loader2xx);
               } catch (IOException exception3x) {
                  CrashReporter.method5(exception3x, "Cloning Text Action");
               }

               this.method13();
            }), OptionFactory.method14("remove").method4(() -> {
               this.actions.remove(lighting3loader3);
               this.method13();
            })});
         }));
      }

      lightingextension231.method13();
      lightingextension231.method9(new OptionProvider[]{OptionFactory.method14("addTrigger").method4(() -> {
         AutoTextAction lighting3loader1x = new AutoTextAction();
         lighting3loader1x.field1.method3(true);
         this.actions.add(lighting3loader1x);
         this.method13();
      })});
   }

   public void method1(JsonObject json1) {
      super.method1(json1);
      json1.remove("options");
      JsonArray array2 = new JsonArray();
      this.actions.forEach(arg1x -> {
         JsonObject json2x = new JsonObject();

         try {
            arg1x.method1(json2x);
         } catch (IOException exception4) {
            CrashReporter.method5(exception4, "Saving action in Auto Text Actions");
         }

         array2.add(json2x);
      });
      json1.add("actions", array2);
   }

   public void load(JsonObject json1) {
      this.actions.clear();
      OptionContainer framework52 = (OptionContainer)this.method7(ModTraits.field14);
      framework52.method1().clear();
      json1.remove("options");
      super.load(json1);
      if (json1.has("actions")) {
         for (JsonElement element5 : json1.getAsJsonArray("actions")) {
            AutoTextAction lighting3loader6 = new AutoTextAction();

            try {
               lighting3loader6.load(element5.getAsJsonObject());
            } catch (IOException exception8) {
               CrashReporter.method5(exception8, "Loading action in Auto Text Actions");
            }

            this.actions.add(lighting3loader6);
         }
      }

      framework52.method5(this);
   }

   private void method13() {
      this.method5();
      LcuiScreen.method145();
   }
}
