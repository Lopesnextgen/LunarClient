package com.moonsworth.lunar.client.mod.misc.chat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.ui.widget.AdvancedOptionWidget;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.event.mixin.EventChatSend;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.JsonConfigurable;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChatCommandAliases extends AbstractFeature {
   private final List<ChatCommandAliases.Data> field8 = new ArrayList<>();

   protected ChatCommandAliases(Chat chat1) {
      super(false);
      this.method10(ModTraits.field16, ChildModBinding.method3(chat1));
      this.method10(ModTraits.field17, ModCategories.method2(SettingsPage.GENERAL));
      this.handle(EventChatSend.class, this::method4);
   }

   public String getId() {
      return "CHAT_COMMAND_ALIASES_CHILD";
   }

   public void load(JsonObject json1) {
      this.field8.clear();
      this.method3(ModTraits.field14).ifPresent(arg0 -> arg0.method1().clear());
      json1.remove("options");
      super.load(json1);
      if (json1.has("aliases")) {
         for (JsonElement element4 : json1.getAsJsonArray("aliases")) {
            JsonObject json5 = element4.getAsJsonObject();
            ChatCommandAliases.Data data6 = new ChatCommandAliases.Data();
            data6.load(json5);
            this.field8.add(data6);
         }
      }

      this.method5();
      LcuiScreen.method145();
   }

   public void method1(JsonObject json1) {
      super.method1(json1);
      json1.remove("options");
      if (!this.field8.isEmpty()) {
         JsonArray array2 = new JsonArray();

         for (ChatCommandAliases.Data data4 : this.field8) {
            JsonObject json5 = new JsonObject();
            data4.method1(json5);
            array2.add(json5);
         }

         json1.add("aliases", array2);
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);

      for (ChatCommandAliases.Data data3 : this.field8) {
         lightingextension231.method7(data3.field4, arg2 -> {
            arg2.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{data3.field1, data3.field2, data3.field3});
            arg2.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new OptionProvider[]{OptionFactory.method14("remove").method4(() -> {
               this.field8.remove(data3);
               this.method5();
               LcuiScreen.method145();
               this.method13();
            })});
         });
      }

      lightingextension231.method9(new OptionProvider[]{OptionFactory.method14("add").method4(() -> {
         ChatCommandAliases.Data data1x = new ChatCommandAliases.Data();
         this.field8.add(data1x);
         this.method5();
         LcuiScreen.method145();
         data1x.field4.method3(true);
         this.method13();
      })});
   }

   private void method13() {
      LcuiScreen.method134()
         .ifPresent(
            arg1 -> {
               for (OptionWidget calculator2iterator33 : ((com.moonsworth.lunar.client.ui.menu.FeatureSettingsWidget)arg1.method10().method17())
                  .ROICIOIRHHCOHHCHCCORHRCROORRRC()) {
                  if (calculator2iterator33 instanceof AdvancedOptionWidget calculator2iterator32244 && calculator2iterator32244.method14() == this && calculator2iterator32244.getOption() instanceof ToggleOption lightingextension4435) {
                     lightingextension4435.method3(true);
                  }
               }
            }
         );
   }

   private void method4(EventChatSend highlightimpl31) {
      if (!highlightimpl31.isCancelled() && Ref.method7() != null) {
         if (highlightimpl31.getMessage().startsWith("/")) {
            String text2 = this.method5(highlightimpl31.getMessage());
            if (text2 != null) {
               if (this.method5(text2) != null) {
                  return;
               }

               highlightimpl31.setMessage(text2);
            }
         }
      }
   }

   private String method5(String text1) {
      for (ChatCommandAliases.Data data3 : this.field8) {
         if ((Boolean)data3.field4.get()) {
            String text4 = data3.getAlias();
            String text5 = data3.getCommand();
            if (!text4.isEmpty() && !text5.isEmpty()) {
               String text6 = text1;
               if (!(Boolean)data3.field3.get()) {
                  text4 = text4.toLowerCase(Locale.ROOT);
                  text5 = text5.toLowerCase(Locale.ROOT);
                  text6 = text6.toLowerCase(Locale.ROOT);
               }

               String[] items7 = text6.split("\\s+");
               if (items7[0].equals(text4)) {
                  return text5 + text1.substring(text4.length());
               }
            }
         }
      }

      return null;
   }

   private class Data implements JsonConfigurable {
      private final TextOption field1 = (TextOption)OptionFactory.method12("alias").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      private final TextOption field2 = (TextOption)OptionFactory.method12("command").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      private final ToggleOption field3 = (ToggleOption)OptionFactory.method7("caseSensitive").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      private final ToggleOption field4 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("active")
               .HIIOIOORCIICIRROCCRIOHRHOOIRCR(this::getName))
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();

      private Data() {
      }

      private String getName() {
         String text1 = this.getAlias();
         String text2 = this.getCommand();
         if (text2.isEmpty() && text1.isEmpty()) {
            return ChatCommandAliases.this.method4("empty", new Object[0]);
         }

         String text3 = this.field3.get() ? " (" + ChatCommandAliases.this.method4("caseSensitive", new Object[0]) + ")" : "";
         return text1 + " -> " + text2 + text3;
      }

      public void load(JsonObject json1) {
         this.field4.load(json1);
         this.field1.load(json1);
         this.field2.load(json1);
         this.field3.load(json1);
      }

      public void method1(JsonObject json1) {
         this.field4.HRICOROOOCCOCOROCRHHCRRIRCOICO(json1);
         this.field1.HRICOROOOCCOCOROCRHHCRRIRCOICO(json1);
         this.field2.HRICOROOOCCOCOROCRHHCRRIRCOICO(json1);
         this.field3.HRICOROOOCCOCOROCRHHCRRIRCOICO(json1);
      }

      public String getAlias() {
         String text1 = (String)this.field1.get();
         if (text1 == null) {
            text1 = "";
         }

         if (!text1.isEmpty() && text1.charAt(0) != '/') {
            text1 = "/" + text1;
         }

         return text1.trim();
      }

      public String getCommand() {
         String text1 = (String)this.field2.get();
         if (text1 == null) {
            text1 = "";
         }

         if (!text1.isEmpty() && text1.charAt(0) != '/') {
            text1 = "/" + text1;
         }

         return text1.trim();
      }
   }
}
