package com.moonsworth.lunar.client.mod.misc.autotextactions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.apollo.module.autotexthotkey.AutoTextHotkeyModule;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.network.apollo.AutoTextHotkeyApolloHandler;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.ui.widget.KeybindCaptureWidget;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen;
import com.moonsworth.lunar.client.ui.menu.FeatureSettingsWidget;
import com.moonsworth.lunar.client.ui.menu.ModMenuWidget;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.listener.KeybindOptionListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ButtonOption;
import com.moonsworth.lunar.client.config.option.AutoTextHotkeyOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.AutoTextHotkeyOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntListIterator;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Map.Entry;
import lombok.Generated;

public class AutoTextHotkey extends AbstractFeature {
   private List<AutoTextHotkeyOption> field8;
   private final Object2IntMap<AutoTextHotkeyOption> field9 = new Object2IntOpenHashMap();
   private final Object2LongMap<AutoTextHotkeyOption> field10 = new Object2LongOpenHashMap();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("notifyOnBlockedInput").method4(true))
      .method31();
   private ButtonOption field12;
   private final int field13 = 50;
   private final int field14 = 3;
   private int field15 = 3;
   private long field16;

   public AutoTextHotkey() {
      super(false);
      this.handle(EventTick.class, arg1 -> {
         if (Ref.method7() != null && Ref.method7().bridge$isBlocking()) {
            this.field16 = System.currentTimeMillis();
         }
      });
   }

   public String getId() {
      return "AUTO_TEXT_HOTKEY";
   }

   public void method4() {
      ArrayList list1 = new ArrayList();

      for (int index2 = 0; index2 < 3; index2++) {
         list1.add(this.method9(index2 + 1));
      }

      this.method2(list1);
      this.method17();
   }

   public void load(JsonObject json1) {
      JsonObject json2 = new JsonObject();
      if (json1.has("options")) {
         json2 = json1.get("options").getAsJsonObject();
      }

      ArrayList list3 = new ArrayList();
      IntArrayList intarraylist4 = new IntArrayList();

      for (Entry entry6 : json2.entrySet()) {
         if (((String)entry6.getKey()).endsWith("hotkey") || ((String)entry6.getKey()).endsWith("_hk_hk")) {
            String text7 = ((String)entry6.getKey()).substring(0, ((String)entry6.getKey()).length() - 6);
            int index8 = Integer.parseInt(text7);
            intarraylist4.add(index8);
            this.field15 = Math.max(this.field15, index8);
         }
      }

      while (intarraylist4.size() < 3) {
         this.field15++;
         intarraylist4.add(this.field15);
      }

      intarraylist4.sort(Integer::compare);
      IntListIterator intlistiterator9 = intarraylist4.iterator();

      while (intlistiterator9.hasNext()) {
         int number10 = (Integer)intlistiterator9.next();
         list3.add(this.method9(number10));
      }

      this.method2(list3);
      super.load(json1);
      this.method17();
   }

   private void method2(ArrayList<AutoTextHotkeyOption> list1) {
      for (AutoTextHotkeyOption lightingextension4963 : this.field8) {
         lightingextension4963.method7().remove();
         KeybindOptionListener.method2(lightingextension4963.method7());
      }

      this.field8 = list1;
      OptionContainer framework54 = (OptionContainer)this.method7(ModTraits.field14);
      framework54.method1().clear();
      framework54.method1().add(this.field11);
      framework54.method1().addAll(this.field8);
      framework54.method1().add(this.field12);
      this.method14(null, false);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      this.field12 = (ButtonOption)OptionFactory.method14("addHotKey").method4(() -> {
         if (this.field15 < 50) {
            this.field15++;
            AutoTextHotkeyOption lightingextension4961x = this.method9(this.field15);
            this.field8.add(lightingextension4961x);
            OptionContainer framework52 = (OptionContainer)this.method7(ModTraits.field14);
            framework52.method1().clear();
            framework52.method1().add(this.field11);
            framework52.method1().addAll(this.field8);
            framework52.method1().add(this.field12);
            this.method14(null, true);
         }
      }).method31();
      this.field8 = new ArrayList<>();
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field11})).method2(() -> this.method15().isEmpty());
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field12})).method2(() -> this.field15 >= 50);
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field6}).method11(this);
   }

   private Optional<AutoTextHotkeyApolloHandler> method13() {
      return Ref.method4().method84().method3(AutoTextHotkeyModule.class).map(arg0 -> (AutoTextHotkeyApolloHandler)arg0);
   }

   public boolean method14() {
      return this.method13().filter(arg0 -> (Boolean)arg0.getOptions().get(AutoTextHotkeyModule.BLOCK_CHAT_MESSAGE_TEXT_INPUTS)).isPresent();
   }

   public List<String> method15() {
      return this.method13()
         .filter(arg0 -> (Boolean)arg0.getOptions().get(AutoTextHotkeyModule.BLOCK_TEXT_INPUTS))
         .map(arg0 -> (List<String>)arg0.getOptions().get(AutoTextHotkeyModule.BLOCKED_TEXT_INPUTS))
         .orElse(Collections.emptyList());
   }

   private void method16() {
      if ((Boolean)this.field11.get()) {
         Ref.method4().method69().method3(NotificationManager.method15("hotkeyBlockedByServerPopup", new Object[0]));
      }
   }

   private AutoTextHotkeyOption method9(int number1) {
      String text2 = number1 + "hotkey";
      AutoTextHotkeyOption lightingextension4963 = (AutoTextHotkeyOption)((Data)((Data)OptionFactory.method16(text2).method2("/Command"))
            .method18(this))
         .method3(number1)
         .method4(KeyCode.KEY_NONE)
         .method31();
      lightingextension4963.method7().method3(() -> {
         Bridge5Extension_5 bridge5extension_52x = Ref.method7();
         if (bridge5extension_52x != null && !bridge5extension_52x.bridge$isUsingItem() && !bridge5extension_52x.bridge$isBlocking() && System.currentTimeMillis() - this.field16 >= 100L) {
            if (!((String)lightingextension4963.get()).isBlank() && !((String)lightingextension4963.get()).isEmpty() && !((String)lightingextension4963.get()).equalsIgnoreCase("/Command")) {
               if (this.field10.containsKey(lightingextension4963) && System.currentTimeMillis() - this.field10.get(lightingextension4963) < 1000L) {
                  this.field9.put(lightingextension4963, this.field9.getOrDefault(lightingextension4963, 0) + 1);
                  if (this.field9.get(lightingextension4963) > 2) {
                     return;
                  }
               } else {
                  this.field9.remove(lightingextension4963);
               }

               this.field10.put(lightingextension4963, System.currentTimeMillis());
               String text3x = (String)lightingextension4963.get();
               if (this.method15().stream().anyMatch(arg1xx -> text3x.toLowerCase().startsWith(arg1xx.toLowerCase()))) {
                  this.method16();
               } else {
                  if (text3x.startsWith("/")) {
                     bridge5extension_52x.bridge$sendCommand(text3x);
                  } else {
                     if (this.method14()) {
                        this.method16();
                        return;
                     }

                     bridge5extension_52x.bridge$sendChatMessage(text3x);
                  }
               }
            }
         }
      });
      return lightingextension4963;
   }

   public void method10(String text1) {
      for (AutoTextHotkeyOption lightingextension4963 : this.field8) {
         if (!lightingextension4963.getId().equalsIgnoreCase(text1) && lightingextension4963.method8()) {
            lightingextension4963.method5(false);
         }
      }
   }

   public void method11(String text1, KeybindCaptureWidget calculator2iterator32332) {
      this.method10("");

      for (AutoTextHotkeyOption lightingextension4964 : this.field8) {
         if (lightingextension4964.getId().equalsIgnoreCase(text1)) {
            if (this.field8.size() <= 3) {
               calculator2iterator32332.method4();
               calculator2iterator32332.method5();
            } else {
               this.field8.remove(lightingextension4964);
               ((OptionContainer)this.method7(ModTraits.field14)).method1().remove(lightingextension4964);
               lightingextension4964.method7().remove();
               KeybindOptionListener.method2(lightingextension4964.method7());
            }
            break;
         }
      }

      this.method17();
      this.method14(calculator2iterator32332, true);
   }

   private void method17() {
      ArrayList list1 = new ArrayList();

      for (int index2 = 0; index2 < this.field8.size(); index2++) {
         AutoTextHotkeyOption lightingextension4963 = this.field8.get(index2);
         lightingextension4963.method7().remove();
         KeybindOptionListener.method2(lightingextension4963.method7());
         AutoTextHotkeyOption lightingextension4964 = this.method9(index2 + 1);
         JsonObject json5 = new JsonObject();
         lightingextension4963.method1(json5);
         this.method13(json5, lightingextension4963.getIndex() + "hotkey", lightingextension4964.getIndex() + "hotkey");
         lightingextension4964.load(json5);
         list1.add(lightingextension4964);
      }

      this.field15 = list1.size();
      this.field8 = list1;
      OptionContainer framework56 = (OptionContainer)this.method7(ModTraits.field14);
      framework56.method1().clear();
      framework56.method1().add(this.field11);
      framework56.method1().addAll(this.field8);
      framework56.method1().add(this.field12);
   }

   private void method13(JsonObject json1, String text2, String text3) {
      for (Entry entry5 : new HashSet(json1.entrySet())) {
         if (((String)entry5.getKey()).startsWith(text2)) {
            json1.remove((String)entry5.getKey());
            json1.add(((String)entry5.getKey()).replace(text2, text3), (JsonElement)entry5.getValue());
            if (((JsonElement)entry5.getValue()).isJsonObject()) {
               this.method13(((JsonElement)entry5.getValue()).getAsJsonObject(), text2, text3);
            }
         }
      }
   }

   private void method14(com.moonsworth.lunar.client.ui.widget.GuiWidget calculator2handler1, boolean flag2) {
      if (this.mc.bridge$getCurrentScreen() instanceof Bridge5Extension62 bridge5extension623) {
         Bridge7_8 bridge7_810 = bridge5extension623.method2();
         if (bridge7_810 instanceof MainMenuButton bridge7task5 && bridge7task5.method17() instanceof Bridge7_8 bridge7_86) {
            bridge7_810 = bridge7_86;
         }

         if (bridge7_810 instanceof FeatureSettingsScreen bridge7iterator11
            && bridge7iterator11.method10().method17() instanceof FeatureSettingsWidget calculator2iterator312
            && calculator2iterator312.getFeature() instanceof AutoTextHotkey) {
            FeatureSettingsWidget calculator2iterator314 = new FeatureSettingsWidget((ModMenuWidget)bridge7iterator11.method10().method17().method12().orElse(null), this);
            if (flag2) {
               List list8 = ((FeatureSettingsWidget)bridge7iterator11.method10().method17()).ROICIOIRHHCOHHCHCCORHRCROORRRC();
               int index9 = Math.max(list8.size() - 13, 0);
               if (calculator2handler1 != null) {
                  index9 = Math.min(index9, Math.max(list8.indexOf(calculator2handler1) - 7, 0));
               }

               calculator2iterator314.method19((com.moonsworth.lunar.client.ui.widget.GuiWidget)list8.get(index9));
            }

            bridge7iterator11.method10().method2(calculator2iterator314);
         }
      }
   }

   public boolean method7() {
      return false;
   }

   @Generated
   public List<AutoTextHotkeyOption> method19() {
      return this.field8;
   }

   @Generated
   public int method21() {
      return 3;
   }
}
