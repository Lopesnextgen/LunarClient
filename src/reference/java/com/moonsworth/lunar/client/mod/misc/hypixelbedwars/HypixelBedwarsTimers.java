package com.moonsworth.lunar.client.mod.misc.hypixelbedwars;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.event.entity.EventEntityJoinWorld;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import com.moonsworth.lunar.client.event.mixin.gui.EventPluginMessage;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.misc.hypixelbedwars.HypixelBedwars;
import com.moonsworth.lunar.client.util.text.TimeFormatting;
import com.moonsworth.lunar.client.framework.hud.HudTimer;
import com.moonsworth.lunar.client.framework.Ref;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

public class HypixelBedwarsTimers extends AbstractFeature {
   private final ToggleOption showTitle = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "showTitle"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showName = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "showName"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption showIcons = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "showIcons"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption reverseText = (ToggleOption)OptionFactory.method7("reverseText").method31();
   private final ToggleOption reverseOrder = (ToggleOption)OptionFactory.method7("reverseOrder").method31();
   private final ToggleOption textShadow = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "textShadow"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption background = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "background"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption border = (ToggleOption)OptionFactory.method7("border").method31();
   private final FloatOption borderThickness = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
               "borderThickness"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
         .method8(0.5F, 3.0F))
      .method31();
   private final ColorOption backgroundColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "backgroundColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976))
      .method31();
   private final ColorOption borderColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "borderColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1627389952))
      .method31();
   private final ColorOption titleColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "titleColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption timerNameColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "timerNameColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption durationColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "durationColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final TreeMap<Long, HypixelBedwarsTimers.Data> timers = new TreeMap<>();
   private final Set<Long> pendingSync = new HashSet<>();
   private final TreeMap<Long, HypixelBedwarsTimers.Data> defaultTimers = new TreeMap<>(
      new Builder()
         .put(0L, new HypixelBedwarsTimers.Data("Timer 1", null, false, 1500L))
         .put(1L, new HypixelBedwarsTimers.Data("Timer 2", null, false, 1500L))
         .build()
   );
   private final Cache<String, Map<Long, HypixelBedwarsTimers.Data>> timerCache = CacheBuilder.newBuilder()
      .expireAfterWrite(15L, TimeUnit.MINUTES)
      .build();
   private long lastWorldChange = 0L;

   public HypixelBedwarsTimers(HypixelBedwars hypixelbedwars1) {
      super(false);
      this.renderItemIcon(ModTraits.field16, ChildModBinding.method3(hypixelbedwars1));
      this.handle(EventTick.class, arg1x -> this.tickTimers());
      this.handle(EventDisconnect.class, arg1x -> {
         this.clearTimers();
         this.lastWorldChange = 0L;
      });
      this.handle(EventEntityJoinWorld.class, arg1x -> {
         if (arg1x.field1 == Ref.method3().bridge$getPlayer()) {
            if (Ref.method3().bridge$getSystemTime() - this.lastWorldChange > 1000L) {
               String text2 = this.getWorldId();
               if (text2 != null && !this.timers.isEmpty()) {
                  this.timerCache.put(text2, new TreeMap<>(this.timers));
               }

               this.clearTimers();
            }
         }
      });
      this.handle(EventPluginMessage.class, arg1x -> {
         String text2 = arg1x.getChannel();
         if ("BLC|T".equals(text2) || "badlion:timers".equals(text2)) {
            String text3 = new String(arg1x.getData(), StandardCharsets.UTF_8);
            Ref.method3().bridge$submit(() -> {
               try {
                  this.handleTimerMessage(text3);
               } catch (Throwable exception3x) {
                  LunarLogger.method5("Failed to process Timer item: " + exception3x.getMessage() + ", " + text3, new Object[0]);
               }
            });
         }
      });
      this.renderItemIcon(ModTraits.field1, new HypixelBedwarsTimers.BedwarsTimersHudElement());
   }

   private void renderItemIcon(MixinHelper_4 mixinhelper_41, ItemStackBridge bridgeextension_42, float value3, float value4) {
      mixinhelper_41.method44(arg0 -> arg0.method29().method6(arg0x -> {
         arg0x.IHORHICICIHRCOCRROCHHOROCHCHCR();
         Bridge.method14().method2();
      }));
      mixinhelper_41.method37(bridgeextension_42, value3, value4 + 1.0F, Ref.method3());
      mixinhelper_41.method44(arg0 -> arg0.method29().method6(arg0x -> {
         Bridge.method14().method3();
         arg0x.ICOHHORICHCROOOCOHIRIHOHORRCHH();
         arg0x.ICRCRICCCORRHICIHHIHORROOHIROO();
      }));
   }

   public String getId() {
      return "HYPIXEL_BEDWARS_TIMERS_CHILD_HUD";
   }

   public void registerOptions(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.method9(
            new ClientOption[]{this.showTitle, this.showName, this.showIcons, this.reverseText, this.reverseOrder, this.background, this.textShadow, this.border, this.borderThickness}
         )
      );
      lightingextension231.method7(
         SettingsPage.COLOR,
         arg1x -> arg1x.method9(new ClientOption[]{this.backgroundColor, this.borderColor, this.titleColor, this.timerNameColor, this.durationColor})
      );
   }

   private void tickTimers() {
      Iterator iterator1 = this.timers.keySet().iterator();

      while (iterator1.hasNext()) {
         long index2 = (Long)iterator1.next();
         HypixelBedwarsTimers.Data data4 = this.timers.get(index2);
         if (data4.field3.get() <= 0L) {
            if (data4.field2) {
               data4.method2(this.shouldRender(data4.duration));
            } else {
               iterator1.remove();
            }
         }
      }
   }

   private long shouldRender(long number1) {
      return (long)(number1 / 20.0 * 1000.0);
   }

   private void handleTimerMessage(@NotNull String text1) {
      if (((HypixelBedwars)((ChildModBinding)this.restoreCachedTimers(ModTraits.field16)).method1()).method21()) {
         int index2 = text1.indexOf("|");
         if (index2 != -1) {
            String text3 = text1.substring(0, index2);
            text1 = text1.substring(index2 + 1);
            JsonElement element4 = JsonParser.parseString(text1);
            if (!element4.isJsonNull() && element4.isJsonObject()) {
               JsonObject json5 = element4.getAsJsonObject();
               switch (text3) {
                  case "REGISTER":
                     this.clearTimers();
                     if (this.getWorldId() != null) {
                        this.timerCache.invalidateAll();
                     }
                     break;
                  case "CHANGE_WORLD":
                     this.lastWorldChange = Ref.method3().bridge$getSystemTime();
                     break;
                  case "ADD_TIMER":
                     this.invalidateCache(this.getWorldId());
                     long number16 = requireJsonElement(json5, "id").getAsLong();
                     HypixelBedwarsTimers.Data data18 = new HypixelBedwarsTimers.Data(
                        requireJsonElement(json5, "name").getAsString(),
                        requireJsonElement(json5, "item").getAsJsonObject(),
                        requireJsonElement(json5, "repeating").getAsBoolean(),
                        requireJsonElement(json5, "time").getAsLong()
                     );
                     data18.method2(this.shouldRender(data18.duration));
                     this.timers.put(number16, data18);
                     break;
                  case "REMOVE_TIMER":
                     this.invalidateCache(this.getWorldId());
                     long index15 = requireJsonElement(json5, "id").getAsLong();
                     if (this.timers.containsKey(index15)) {
                        this.timers.get(index15).field3.destroy();
                        this.timers.remove(index15);
                     }
                     break;
                  case "REMOVE_ALL_TIMERS":
                     this.invalidateCache(this.getWorldId());
                     this.clearTimers();
                     break;
                  case "UPDATE_TIMER":
                     this.restoreCachedTimers(this.getWorldId());
                     long index14 = requireJsonElement(json5, "id").getAsLong();
                     HypixelBedwarsTimers.Data data17 = this.timers.get(index14);
                     if (data17 != null) {
                        data17.name = requireJsonElement(json5, "name").getAsString();
                        data17.method3(requireJsonElement(json5, "item").getAsJsonObject());
                        data17.field2 = requireJsonElement(json5, "repeating").getAsBoolean();
                        data17.duration = requireJsonElement(json5, "time").getAsLong();
                        data17.method2(this.shouldRender(requireJsonElement(json5, "currentTime").getAsLong()));
                     }
                     break;
                  case "SYNC_TIMERS":
                     if (this.restoreCachedTimers(this.getWorldId())) {
                        this.pendingSync.addAll(this.timers.keySet());
                     }

                     long index8 = requireJsonElement(json5, "id").getAsLong();
                     long number10 = requireJsonElement(json5, "time").getAsLong();
                     if (this.pendingSync.remove(index8)) {
                        HypixelBedwarsTimers.Data data12 = this.timers.get(index8);
                        if (data12 != null) {
                           data12.method2(this.shouldRender(number10) - 550L);
                        }
                     }
               }
            } else {
               LunarLogger.method5("Invalid timers message {}", new Object[]{text1});
            }
         }
      }
   }

   private void invalidateCache(String text1) {
      if (text1 != null) {
         this.timerCache.invalidate(text1);
      }
   }

   private boolean restoreCachedTimers(String text1) {
      if (text1 != null) {
         Map map2 = (Map)this.timerCache.getIfPresent(text1);
         if (map2 != null) {
            this.timerCache.invalidate(text1);
            this.timers.putAll(map2);
            return true;
         }
      }

      return false;
   }

   private void clearTimers() {
      this.timers.values().forEach(arg0 -> arg0.field3.destroy());
      this.timers.clear();
      this.pendingSync.clear();
   }

   private String getWorldId() {
      HypixelBedwars hypixelbedwars1 = (HypixelBedwars)((ChildModBinding)this.restoreCachedTimers(ModTraits.field16)).method1();
      return !hypixelbedwars1.method21() ? null : HypixelLocationListener.field7.method7().field4;
   }

   private static JsonElement requireJsonElement(JsonObject json0, String text1) {
      if (json0.has(text1) && !json0.get(text1).isJsonNull()) {
         return json0.get(text1);
      } else {
         throw new NoSuchElementException("Timers message doesn't have required '" + text1 + "'");
      }
   }

   private static class Data {
      private String name;
      private ItemStackBridge item;
      private boolean repeating;
      private long duration;
      private HudTimer timer;

      private Data(String text1, JsonObject json2, boolean flag3, long number4) {
         this.name = text1;
         this.render(json2);
         this.repeating = flag3;
         this.duration = number4;
      }

      private String renderItemIcon() {
         if (this.timer == null) {
            return TimeFormatting.method1(this.duration);
         }

         long number1 = this.timer.get() + 1550L;
         return TimeFormatting.method1(number1);
      }

      private void registerOptions(long number1) {
         if (this.timer != null) {
            this.timer.destroy();
         }

         this.timer = new com.moonsworth.lunar.client.framework.hud.HudTimer.Data().method5(number1).method4().method7();
         this.timer.method2();
      }

      private void render(JsonObject json1) {
         if (json1 == null) {
            this.item = Bridge.method8().method39(Bridge.method34().method4());
         } else {
            String text2 = HypixelBedwarsTimers.requireJsonElement(json1, "type").getAsString().toLowerCase(Locale.ROOT);
            if (text2.equals("bed") && Ref.MC_VERSION >= 12) {
               text2 = "red_bed";
            }

            this.item = Bridge.method8().method38(Bridge.method28().method22(text2));
         }
      }
   }

   private class BedwarsTimersHudElement extends HudElementBase {
      public BedwarsTimersHudElement() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
      }

      public void render(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         MixinHelper_4 mixinhelper_45 = highlightimpl1.method2();
         mixinhelper_45.push();
         mixinhelper_45.method38(value2, value3, 0.0F);
         this.registerOptions(
            mixinhelper_45,
            flag4 && HypixelBedwarsTimers.this.timers.isEmpty() ? HypixelBedwarsTimers.this.defaultTimers : HypixelBedwarsTimers.this.timers
         );
         mixinhelper_45.pop();
      }

      private void registerOptions(MixinHelper_4 mixinhelper_41, TreeMap<Long, HypixelBedwarsTimers.Data> map2) {
         float value3 = 16.0F;
         if ((Boolean)HypixelBedwarsTimers.this.background.get()) {
            HypixelBedwarsTimers.this.backgroundColor.method11(mixinhelper_41, 0.0F, 0.0F, this.getWidth(), this.getHeight());
         }

         if ((Boolean)HypixelBedwarsTimers.this.border.get()) {
            HypixelBedwarsTimers.this.borderColor
               .method11(mixinhelper_41, this, 0.0F, 0.0F, this.getWidth(), this.getHeight(), (Float)HypixelBedwarsTimers.this.borderThickness.get());
         }

         boolean flag4 = (Boolean)HypixelBedwarsTimers.this.textShadow.get();
         boolean flag5 = (Boolean)HypixelBedwarsTimers.this.reverseText.get();
         float value6 = 2.0F;
         float value7 = 2.0F;
         if ((Boolean)HypixelBedwarsTimers.this.showTitle.get()) {
            String text8 = "§l" + HypixelBedwarsTimers.this.requireJsonElement("timers", new Object[0]);
            HypixelBedwarsTimers.this.titleColor.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, text8, value6, value7, flag4);
            value3 = Ref.method10().bridge$getStringWidth(text8) + 4.0F;
            value7 += 12.0F;
         }

         Iterator iterator17 = HypixelBedwarsTimers.this.reverseOrder.get() ? map2.descendingMap().values().iterator() : map2.values().iterator();

         while (iterator17.hasNext()) {
            value6 = 2.0F;
            float value9 = value7;
            float value10 = value6;
            HypixelBedwarsTimers.Data data11 = (HypixelBedwarsTimers.Data)iterator17.next();
            if ((Boolean)HypixelBedwarsTimers.this.showIcons.get()) {
               value10 += 14.0F;
               if (!flag5) {
                  HypixelBedwarsTimers.this.renderItemIcon(mixinhelper_41, data11.field1, value6 - 2.0F, value7 - 1.0F);
                  value6 += 16.0F;
               }
            }

            float value12 = 0.0F;
            if ((Boolean)HypixelBedwarsTimers.this.showName.get()) {
               String text13 = data11.name;
               value12 = Ref.method10().bridge$getStringWidth(text13);
               HypixelBedwarsTimers.this.timerNameColor.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, text13, value6, value7, flag4);
               value7 += 5.0F;
            }

            String text20 = data11.method1();
            value12 = Math.max(value12, Ref.method10().bridge$getStringWidth(text20));
            value7 += 4.0F;
            HypixelBedwarsTimers.this.durationColor.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, text20, value6, value7, flag4);
            if ((Boolean)HypixelBedwarsTimers.this.showIcons.get() && flag5) {
               value6 += value12 + 2.0F;
               value10++;
               HypixelBedwarsTimers.this.renderItemIcon(mixinhelper_41, data11.field1, value6, value9 - 1.0F);
            }

            value10 += value12 + 2.0F;
            value3 = Math.max(value3, value10 + 2.0F);
            value7 += 12.0F;
         }

         this.method58(value3, value7 - (HypixelBedwarsTimers.this.showName.get() ? 2 : 0));
      }

      public boolean shouldRender(boolean flag1) {
         boolean flag2 = ((HypixelBedwars)((ChildModBinding)HypixelBedwarsTimers.this.restoreCachedTimers(ModTraits.field16)).method1())
               .method35()
            && (flag1 || !HypixelBedwarsTimers.this.timers.isEmpty());
         if (!flag2) {
            this.method58(0.0F, 0.0F);
         }

         return flag2;
      }
   }
}
