package com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.lunarclient.BetterJson;
import com.lunarclient.generated.skyblockprofileresponse.profile.Member;
import com.lunarclient.generated.skyblockprofileresponse.profile.member.playerdata.Experience;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.SkillLevelCalculator;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.CoordinatesType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.SkillXpSource;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.ScoreboardSection;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkillXpUpdateEvent;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.EventActionBarMessage;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.event.mixin.gui.EventTabListUpdate;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.text.RomanNumeralParser;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class SkillXpListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final File field7 = new File(LunarConstants.field25 + File.separator + "skyblock_skill_levels.json");
   private final SkyblockScoreboardParser field8 = (SkyblockScoreboardParser)this.method3(SkyblockScoreboardParser.class);
   private final com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener field9 = (com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener)this.method3(
      com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener.class
   );
   private final ProfileIdListener field10 = (ProfileIdListener)this.method3(ProfileIdListener.class);
   public static final Pattern field11 = Pattern.compile("\\+(?<xp>[\\d.,]+) (?<skill>[a-zA-Z ]+) \\((?<progress>[\\d,]+)/(?<needed>[\\d,]+[kmbKMB]?)\\)");
   public static final Pattern field12 = Pattern.compile("\\+(?<xp>[\\d.,]+) (?<skill>[a-zA-Z ]+) \\((?<progress>[\\d.]+)%\\)");
   private static final Pattern field13 = Pattern.compile("^ {3}SKILL LEVEL UP (?<skill>[a-zA-Z ]+) [IVXL]+➜(?<level>[IVXL]+)$");
   private static final Pattern field14 = Pattern.compile("^ (?<skill>[a-zA-Z ]+)(?<levelExists> (?<level>\\d+))?: (?<progress>[\\d.]+%|MAX)$");
   private static final Pattern field15 = Pattern.compile("^(?<skill>[a-zA-Z ]+?)(?<levelExists> (?<level>[IVXL]+))?$");
   private static final Pattern field16 = Pattern.compile("^Progress to Level [IVXL]+: (?<progress>[\\d.]+)%$");
   private static final Pattern field17 = Pattern.compile("^\\s+ (?<progress>[\\d.,]+)/(?<progressNeeded>[\\d.,kmbKMB]+)$");
   private static final Pattern field18 = Pattern.compile("^\\s+ (?<progress>[\\d.,]+)$");
   private final SkyblockProfileCache field19 = (SkyblockProfileCache)this.method3(SkyblockProfileCache.class);
   private final Object2IntOpenHashMap<CoordinatesType> field20 = new Object2IntOpenHashMap();
   private final Object2DoubleOpenHashMap<CoordinatesType> field21 = new Object2DoubleOpenHashMap();
   private CoordinatesType field22;
   private CoordinatesType field23;
   private boolean field24;
   private boolean field25;

   public SkillXpListener() {
      this.handle(EventActionBarMessage.class, this::method5);
      this.handle(TypedChatMessage.class, this::method6);
      this.handle(EventTabListUpdate.class, this::method7);
      this.handle(EventSlotUpdate.class, this::method8);
      this.handle(SkyblockProfileEvents.SkyblockProfileLoadEvent.class, arg1 -> this.method13());
      this.handle(SkyblockProfileEvents.SkyblockProfileChangeEvent.class, this::method9);
      this.handle(EventWorldChange.class, arg1 -> this.field24 = false);
      this.handle(SkyblockProfileEvents.SkyblockProfileIdEvent.class, arg1 -> this.field24 = true);
   }

   private long method1(CoordinatesType coordinatestype1) {
      return this.method2(this.field20.getOrDefault(coordinatestype1, 0), coordinatestype1);
   }

   private long method2(int index1, CoordinatesType coordinatestype2) {
      HashMap map3 = Ref.method4().method40().method82().method15().method22();
      IntArrayList intarraylist4;
      if (coordinatestype2 == CoordinatesType.RUNECRAFTING) {
         intarraylist4 = (IntArrayList)map3.get("runecrafting");
      } else if (coordinatestype2 == CoordinatesType.SOCIAL) {
         intarraylist4 = (IntArrayList)map3.get("social");
      } else {
         intarraylist4 = (IntArrayList)map3.get("skill");
      }

      return index1 >= intarraylist4.size() ? 0L : intarraylist4.getInt(index1);
   }

   public int method3(CoordinatesType coordinatestype1) {
      return this.field20.getOrDefault(coordinatestype1, 0);
   }

   public double method4(CoordinatesType coordinatestype1) {
      return this.field21.getOrDefault(coordinatestype1, 0.0);
   }

   private void method5(EventActionBarMessage data21) {
      if (this.field24) {
         String text2 = data21.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
         CoordinatesType coordinatestype3 = null;
         double value4 = 0.0;
         double value6 = 0.0;
         double value8 = 0.0;
         double value10 = 0.0;
         long number12 = 0L;
         Matcher matcher14 = field12.matcher(text2);
         if (matcher14.find()) {
            coordinatestype3 = CoordinatesType.of(matcher14.group("skill"));
            if (coordinatestype3 == null) {
               return;
            }

            value4 = Double.parseDouble(matcher14.group("xp").replace(",", ""));
            value10 = Double.parseDouble(matcher14.group("progress"));
            double value15 = value10 / 100.0;
            number12 = this.method1(coordinatestype3);
            value8 = number12 * value15;
            value6 = SkillLevelCalculator.getXpForLevel(this.method3(coordinatestype3), value15, coordinatestype3);
         } else {
            Matcher matcher18 = field11.matcher(text2);
            if (matcher18.find()) {
               coordinatestype3 = CoordinatesType.of(matcher18.group("skill"));
               if (coordinatestype3 == null) {
                  return;
               }

               String text16 = matcher18.group("needed").replace(",", "");
               number12 = this.method12(text16);
               if (number12 == 0L) {
                  com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.MaxLevels coordinates17 = Ref.method4()
                     .method40()
                     .method82()
                     .method15()
                     .method24();
                  if (coordinates17 != null) {
                     this.method11(coordinatestype3, coordinates17.getMaxLevels().getInt(coordinatestype3));
                  }
               }

               value4 = Double.parseDouble(matcher18.group("xp").replace(",", ""));
               value8 = NumberUtils.method2(matcher18.group("progress").replace(",", ""));
               value10 = number12 > 0L ? value8 / number12 * 100.0 : 100.0;
               value6 = SkillLevelCalculator.getXpForLevel(this.method3(coordinatestype3), 0.0, coordinatestype3) + value8;
            }
         }

         if (coordinatestype3 != null) {
            this.field22 = coordinatestype3;
            if (coordinatestype3.isTrackable()) {
               this.field23 = coordinatestype3;
            }

            this.method10(coordinatestype3, SkillXpSource.ACTION_BAR, value6, value4, value8, value10, number12);
         }
      }
   }

   private void method6(TypedChatMessage data1) {
      if (this.field24) {
         Matcher matcher2 = field13.matcher(data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH());
         if (matcher2.matches()) {
            CoordinatesType coordinatestype3 = CoordinatesType.of(matcher2.group("skill"));
            if (coordinatestype3 != null) {
               try {
                  this.method11(coordinatestype3, RomanNumeralParser.romanToInt(matcher2.group("level")));
               } catch (IllegalArgumentException illegalargumentexception5) {
                  CrashReporter.method5(illegalargumentexception5, "Skill XP Listener");
               }
            }
         }
      }
   }

   private void method7(EventTabListUpdate highlightimpl31) {
      if (this.field24) {
         ScoreboardSection lotusfish32 = this.field8.method6().get("skills");
         if (lotusfish32 != null) {
            for (String text4 : lotusfish32.method3()) {
               Matcher matcher5 = field14.matcher(text4);
               if (matcher5.matches()) {
                  CoordinatesType coordinatestype6 = CoordinatesType.of(matcher5.group("skill"));
                  if (coordinatestype6 != null) {
                     int number7 = 0;
                     if (matcher5.group("levelExists") != null) {
                        number7 = Integer.parseInt(matcher5.group("level"));
                     }

                     this.method11(coordinatestype6, number7);
                     String text8 = matcher5.group("progress").replaceAll("[^\\d.]", "");
                     if (!text8.isEmpty()) {
                        double value9 = Double.parseDouble(text8) / 100.0;
                        double value11 = SkillLevelCalculator.getXpForLevel(number7, value9, coordinatestype6);
                        long number13 = this.method2(number7, coordinatestype6);
                        double value15 = value9 * number13;
                        this.method10(coordinatestype6, SkillXpSource.TAB_LIST, value11, 0.0, value15, value9 * 100.0, number13);
                     }
                  }
               }
            }
         }
      }
   }

   private void method8(EventSlotUpdate highlightimpl1) {
      if (this.field24) {
         if (this.field9.method7() == SkyblockMenuType.YOUR_SKILLS) {
            ItemStackBridge bridgeextension_42 = highlightimpl1.method3();
            if (bridgeextension_42 != null && !bridgeextension_42.bridge$isEmpty()) {
               String text3 = ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_42.bridge$getDisplayName());
               Matcher matcher4 = field15.matcher(text3);
               if (matcher4.matches()) {
                  CoordinatesType coordinatestype5 = CoordinatesType.of(matcher4.group("skill"));
                  if (coordinatestype5 != null) {
                     int number6 = 0;
                     if (matcher4.group("levelExists") != null) {
                        try {
                           number6 = RomanNumeralParser.romanToInt(matcher4.group("level"));
                        } catch (IllegalArgumentException illegalargumentexception21) {
                           CrashReporter.method5(illegalargumentexception21, "Skill XP Listener");
                        }
                     }

                     this.method11(coordinatestype5, number6);
                     double value7 = 0.0;
                     double value9 = 0.0;
                     long number11 = this.method2(0, coordinatestype5);
                     double value13 = 0.0;

                     for (String text16 : SkyblockItemUtil.method15(bridgeextension_42)) {
                        Matcher matcher17 = field16.matcher(text16);
                        if (matcher17.matches()) {
                           value7 = Double.parseDouble(matcher17.group("progress"));
                        } else {
                           Matcher matcher18 = field17.matcher(text16);
                           if (matcher18.matches()) {
                              String text22 = matcher18.group("progress").replace(",", "");
                              value9 = Double.parseDouble(text22);
                              String text23 = matcher18.group("progressNeeded").replace(",", "");
                              number11 = this.method12(text23);
                              value13 = SkillLevelCalculator.getXpForLevel(number6, 0.0, coordinatestype5) + value9;
                              break;
                           }

                           Matcher matcher19 = field18.matcher(text16);
                           if (matcher19.matches()) {
                              String text20 = matcher19.group("progress").replace(",", "");
                              value9 = Double.parseDouble(text20);
                              number11 = 0L;
                              value13 = value9;
                              value7 = 100.0;
                              break;
                           }
                        }
                     }

                     this.method10(coordinatestype5, SkillXpSource.SKILLS_MENU, value13, 0.0, value9, value7, number11);
                  }
               }
            }
         }
      }
   }

   private void method9(SkyblockProfileEvents.SkyblockProfileChangeEvent data151) {
      this.field21.clear();
      this.field20.clear();
   }

   private void method10(CoordinatesType coordinatestype1, SkillXpSource coordinatestype22, double value3, double value5, double value7, double value9, long number11) {
      if (!(value3 < 0.0)) {
         double value13 = value3 - this.method4(coordinatestype1);
         if (!this.field21.containsKey(coordinatestype1)) {
            LunarEventBus.method29().method12(SkillXpUpdateEvent.class, () -> new SkillXpUpdateEvent(coordinatestype1, coordinatestype22, value5, 0.0, value7, value9, number11));
            this.field21.put(coordinatestype1, value3);
         } else if (coordinatestype22 == SkillXpSource.SKILLS_MENU || value13 > 0.0) {
            LunarEventBus.method29().method12(SkillXpUpdateEvent.class, () -> new SkillXpUpdateEvent(coordinatestype1, coordinatestype22, value5, value13, value7, value9, number11));
            this.field21.put(coordinatestype1, value3);
         } else if (coordinatestype22 == SkillXpSource.ACTION_BAR) {
            LunarEventBus.method29().method12(SkillXpUpdateEvent.class, () -> new SkillXpUpdateEvent(coordinatestype1, coordinatestype22, value5, 0.0, value7, value9, number11));
         }
      }
   }

   private void method11(CoordinatesType coordinatestype1, int number2) {
      int number3 = this.field20.put(coordinatestype1, number2);
      if (number3 != number2) {
         this.method15();
      }
   }

   private long method12(String text1) {
      char character2 = text1.charAt(text1.length() - 1);
      if (Character.isDigit(character2)) {
         return NumberUtils.method1(text1);
      }

      int number3 = switch (character2) {
         case 'B', 'b' -> 1000000000;
         case 'K', 'k' -> 1000;
         case 'M', 'm' -> 1000000;
         default -> 1;
      };
      return NumberUtils.method1(text1.substring(0, text1.length() - 1)) * number3;
   }

   private void method13() {
      Map map1 = this.method14(this.field10.method6(), this.field10.method5());
      if (map1 != null) {
         this.field20.putAll(map1);
      } else {
         com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.MaxLevels coordinates2 = Ref.method4()
            .method40()
            .method82()
            .method15()
            .method24();
         if (coordinates2 != null) {
            Member member3 = this.field19.method9();
            if (member3 != null) {
               Experience experience4 = member3.playerData().experience();
               if (experience4.elm().exists()) {
                  for (Entry entry6 : experience4.elm().asMapOpt(BetterJson::asDouble).entrySet()) {
                     String text7 = ((String)entry6.getKey()).substring(((String)entry6.getKey()).indexOf(95) + 1);
                     double value8 = (Double)entry6.getValue();
                     CoordinatesType coordinatestype10 = CoordinatesType.of(text7);
                     if (coordinatestype10 != null) {
                        int number11 = SkillLevelCalculator.getLevel(value8, coordinatestype10);
                        if (number11 != -1) {
                           this.field21.put(coordinatestype10, value8);
                           this.field20.put(coordinatestype10, number11);
                        }
                     }
                  }

                  if (!this.field20.isEmpty()) {
                     this.method15();
                  }
               }
            }
         }
      }
   }

   @Nullable
   private Map<CoordinatesType, Integer> method14(String text1, String text2) {
      if (!field7.exists()) {
         return null;
      }

      if (text1 != null && text2 != null) {
         try {
            JsonElement element3 = JsonParser.parseReader(new FileReader(field7));
            if (!element3.isJsonObject()) {
               return null;
            }

            JsonObject json4 = element3.getAsJsonObject();
            if (!json4.has(text1)) {
               return null;
            }

            JsonObject json5 = json4.getAsJsonObject(text1);
            return !json5.has(text2)
               ? null
               : (Map)LunarConstants.field22.fromJson(json5.getAsJsonObject(text2), (new TypeToken<Object2IntOpenHashMap<CoordinatesType>>() {}).getType());
         } catch (IOException exception6) {
            CrashReporter.method5(exception6, "Loading SkyBlock Skill Levels");
            return null;
         }
      } else {
         return null;
      }
   }

   private void method15() {
      String text1 = this.field10.method5();
      if (text1 != null) {
         try {
            Object obj2;
            if (field7.exists()) {
               try {
                  obj2 = JsonParser.parseReader(new FileReader(field7));
               } catch (JsonParseException jsonparseexception12) {
                  obj2 = new JsonObject();
               }
            } else {
               obj2 = new JsonObject();
            }

            if (!obj2.isJsonObject()) {
               return;
            }

            String text3 = this.field10.method6();
            JsonObject json4 = obj2.getAsJsonObject();
            JsonObject json5 = json4.getAsJsonObject(text3);
            if (json5 == null) {
               json5 = new JsonObject();
            }

            JsonObject json6 = LunarConstants.field22.toJsonTree(this.field20).getAsJsonObject();
            json5.add(text1, json6);
            json4.add(text3, json5);

            try (FileWriter filewriter7 = new FileWriter(field7)) {
               LunarConstants.field22.toJson(json4, filewriter7);
            }
         } catch (IOException exception13) {
            CrashReporter.method5(exception13, "Saving SkyBlock Skill Levels");
         }
      }
   }

   protected void onEnable() {
      if (!this.field25) {
         this.method13();
         this.field25 = true;
      }

      if (this.field10.method5() != null) {
         this.field24 = true;
      }
   }

   @Generated
   public CoordinatesType method16() {
      return this.field22;
   }

   @Generated
   public CoordinatesType method17() {
      return this.field23;
   }
}
