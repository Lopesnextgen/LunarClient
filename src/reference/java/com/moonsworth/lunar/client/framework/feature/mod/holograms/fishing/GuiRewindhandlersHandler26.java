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
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.Coordinates;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.CoordinatesType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.CoordinatesType2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.Gui3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.Lotusfish3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.HighlightType;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightImpl4;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data2;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldChanged;
import com.moonsworth.lunar.client.event.mixin.gui.SlotUpdateEvent;
import com.moonsworth.lunar.client.event.mixin.gui.TabListUpdateEvent;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.util.ThreadModuleDump40;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump83;
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

public class GuiRewindhandlersHandler26 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private static final File field7 = new File(ThreadModuleDump48.field25 + File.separator + "skyblock_skill_levels.json");
   private final GuiRewindhandlersHandler23 field8 = (GuiRewindhandlersHandler23)this.method3(GuiRewindhandlersHandler23.class);
   private final com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler22 field9 = (com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler22)this.method3(
      com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler22.class
   );
   private final GuiRewindhandlersHandler212 field10 = (GuiRewindhandlersHandler212)this.method3(GuiRewindhandlersHandler212.class);
   public static final Pattern field11 = Pattern.compile("\\+(?<xp>[\\d.,]+) (?<skill>[a-zA-Z ]+) \\((?<progress>[\\d,]+)/(?<needed>[\\d,]+[kmbKMB]?)\\)");
   public static final Pattern field12 = Pattern.compile("\\+(?<xp>[\\d.,]+) (?<skill>[a-zA-Z ]+) \\((?<progress>[\\d.]+)%\\)");
   private static final Pattern field13 = Pattern.compile("^ {3}SKILL LEVEL UP (?<skill>[a-zA-Z ]+) [IVXL]+➜(?<level>[IVXL]+)$");
   private static final Pattern field14 = Pattern.compile("^ (?<skill>[a-zA-Z ]+)(?<levelExists> (?<level>\\d+))?: (?<progress>[\\d.]+%|MAX)$");
   private static final Pattern field15 = Pattern.compile("^(?<skill>[a-zA-Z ]+?)(?<levelExists> (?<level>[IVXL]+))?$");
   private static final Pattern field16 = Pattern.compile("^Progress to Level [IVXL]+: (?<progress>[\\d.]+)%$");
   private static final Pattern field17 = Pattern.compile("^\\s+ (?<progress>[\\d.,]+)/(?<progressNeeded>[\\d.,kmbKMB]+)$");
   private static final Pattern field18 = Pattern.compile("^\\s+ (?<progress>[\\d.,]+)$");
   private final GuiRewindhandlersHandler22 field19 = (GuiRewindhandlersHandler22)this.method3(GuiRewindhandlersHandler22.class);
   private final Object2IntOpenHashMap<CoordinatesType> field20 = new Object2IntOpenHashMap();
   private final Object2DoubleOpenHashMap<CoordinatesType> field21 = new Object2DoubleOpenHashMap();
   private CoordinatesType field22;
   private CoordinatesType field23;
   private boolean field24;
   private boolean field25;

   public GuiRewindhandlersHandler26() {
      this.handle(Data2.class, this::method5);
      this.handle(Data.class, this::method6);
      this.handle(TabListUpdateEvent.class, this::method7);
      this.handle(SlotUpdateEvent.class, this::method8);
      this.handle(Rewindhandlers.Data14.class, var1 -> this.method13());
      this.handle(Rewindhandlers.Data15.class, this::method9);
      this.handle(EventWorldChanged.class, var1 -> this.field24 = false);
      this.handle(Rewindhandlers.Data13.class, var1 -> this.field24 = true);
   }

   private long method1(CoordinatesType var1) {
      return this.method2(this.field20.getOrDefault(var1, 0), var1);
   }

   private long method2(int var1, CoordinatesType var2) {
      HashMap var3 = ThreadModuleDump63.method4().method40().method82().method15().method22();
      IntArrayList var4;
      if (var2 == CoordinatesType.RUNECRAFTING) {
         var4 = (IntArrayList)var3.get("runecrafting");
      } else if (var2 == CoordinatesType.SOCIAL) {
         var4 = (IntArrayList)var3.get("social");
      } else {
         var4 = (IntArrayList)var3.get("skill");
      }

      return var1 >= var4.size() ? 0L : var4.getInt(var1);
   }

   public int method3(CoordinatesType var1) {
      return this.field20.getOrDefault(var1, 0);
   }

   public double method4(CoordinatesType var1) {
      return this.field21.getOrDefault(var1, 0.0);
   }

   private void method5(Data2 var1) {
      if (this.field24) {
         String var2 = var1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
         CoordinatesType var3 = null;
         double var4 = 0.0;
         double var6 = 0.0;
         double var8 = 0.0;
         double var10 = 0.0;
         long var12 = 0L;
         Matcher var14 = field12.matcher(var2);
         if (var14.find()) {
            var3 = CoordinatesType.of(var14.group("skill"));
            if (var3 == null) {
               return;
            }

            var4 = Double.parseDouble(var14.group("xp").replace(",", ""));
            var10 = Double.parseDouble(var14.group("progress"));
            double var15 = var10 / 100.0;
            var12 = this.method1(var3);
            var8 = var12 * var15;
            var6 = Coordinates.getXpForLevel(this.method3(var3), var15, var3);
         } else {
            Matcher var18 = field11.matcher(var2);
            if (var18.find()) {
               var3 = CoordinatesType.of(var18.group("skill"));
               if (var3 == null) {
                  return;
               }

               String var16 = var18.group("needed").replace(",", "");
               var12 = this.method12(var16);
               if (var12 == 0L) {
                  com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.Coordinates var17 = ThreadModuleDump63.method4()
                     .method40()
                     .method82()
                     .method15()
                     .method24();
                  if (var17 != null) {
                     this.method11(var3, var17.getMaxLevels().getInt(var3));
                  }
               }

               var4 = Double.parseDouble(var18.group("xp").replace(",", ""));
               var8 = ThreadModuleDump40.method2(var18.group("progress").replace(",", ""));
               var10 = var12 > 0L ? var8 / var12 * 100.0 : 100.0;
               var6 = Coordinates.getXpForLevel(this.method3(var3), 0.0, var3) + var8;
            }
         }

         if (var3 != null) {
            this.field22 = var3;
            if (var3.isTrackable()) {
               this.field23 = var3;
            }

            this.method10(var3, CoordinatesType2.ACTION_BAR, var6, var4, var8, var10, var12);
         }
      }
   }

   private void method6(Data var1) {
      if (this.field24) {
         Matcher var2 = field13.matcher(var1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH());
         if (var2.matches()) {
            CoordinatesType var3 = CoordinatesType.of(var2.group("skill"));
            if (var3 != null) {
               try {
                  this.method11(var3, ThreadModuleDump83.parseRoman(var2.group("level")));
               } catch (IllegalArgumentException var5) {
                  Inventorymod2.method5(var5, "Skill XP Listener");
               }
            }
         }
      }
   }

   private void method7(TabListUpdateEvent var1) {
      if (this.field24) {
         Lotusfish3 var2 = this.field8.method6().get("skills");
         if (var2 != null) {
            for (String var4 : var2.method3()) {
               Matcher var5 = field14.matcher(var4);
               if (var5.matches()) {
                  CoordinatesType var6 = CoordinatesType.of(var5.group("skill"));
                  if (var6 != null) {
                     int var7 = 0;
                     if (var5.group("levelExists") != null) {
                        var7 = Integer.parseInt(var5.group("level"));
                     }

                     this.method11(var6, var7);
                     String var8 = var5.group("progress").replaceAll("[^\\d.]", "");
                     if (!var8.isEmpty()) {
                        double var9 = Double.parseDouble(var8) / 100.0;
                        double var11 = Coordinates.getXpForLevel(var7, var9, var6);
                        long var13 = this.method2(var7, var6);
                        double var15 = var9 * var13;
                        this.method10(var6, CoordinatesType2.TAB_LIST, var11, 0.0, var15, var9 * 100.0, var13);
                     }
                  }
               }
            }
         }
      }
   }

   private void method8(SlotUpdateEvent var1) {
      if (this.field24) {
         if (this.field9.method7() == HighlightType.YOUR_SKILLS) {
            ItemStackBridge var2 = var1.method3();
            if (var2 != null && !var2.bridge$isEmpty()) {
               String var3 = AdventureChatFormatting.getTextWithoutFormattingCodes(var2.bridge$getDisplayName());
               Matcher var4 = field15.matcher(var3);
               if (var4.matches()) {
                  CoordinatesType var5 = CoordinatesType.of(var4.group("skill"));
                  if (var5 != null) {
                     int var6 = 0;
                     if (var4.group("levelExists") != null) {
                        try {
                           var6 = ThreadModuleDump83.parseRoman(var4.group("level"));
                        } catch (IllegalArgumentException var21) {
                           Inventorymod2.method5(var21, "Skill XP Listener");
                        }
                     }

                     this.method11(var5, var6);
                     double var7 = 0.0;
                     double var9 = 0.0;
                     long var11 = this.method2(0, var5);
                     double var13 = 0.0;

                     for (String var16 : Gui3.method15(var2)) {
                        Matcher var17 = field16.matcher(var16);
                        if (var17.matches()) {
                           var7 = Double.parseDouble(var17.group("progress"));
                        } else {
                           Matcher var18 = field17.matcher(var16);
                           if (var18.matches()) {
                              String var22 = var18.group("progress").replace(",", "");
                              var9 = Double.parseDouble(var22);
                              String var23 = var18.group("progressNeeded").replace(",", "");
                              var11 = this.method12(var23);
                              var13 = Coordinates.getXpForLevel(var6, 0.0, var5) + var9;
                              break;
                           }

                           Matcher var19 = field18.matcher(var16);
                           if (var19.matches()) {
                              String var20 = var19.group("progress").replace(",", "");
                              var9 = Double.parseDouble(var20);
                              var11 = 0L;
                              var13 = var9;
                              var7 = 100.0;
                              break;
                           }
                        }
                     }

                     this.method10(var5, CoordinatesType2.SKILLS_MENU, var13, 0.0, var9, var7, var11);
                  }
               }
            }
         }
      }
   }

   private void method9(Rewindhandlers.Data15 var1) {
      this.field21.clear();
      this.field20.clear();
   }

   private void method10(CoordinatesType var1, CoordinatesType2 var2, double var3, double var5, double var7, double var9, long var11) {
      if (!(var3 < 0.0)) {
         double var13 = var3 - this.method4(var1);
         if (!this.field21.containsKey(var1)) {
            ClientEventBus.method29().method12(HighlightImpl4.class, () -> new HighlightImpl4(var1, var2, var5, 0.0, var7, var9, var11));
            this.field21.put(var1, var3);
         } else if (var2 == CoordinatesType2.SKILLS_MENU || var13 > 0.0) {
            ClientEventBus.method29().method12(HighlightImpl4.class, () -> new HighlightImpl4(var1, var2, var5, var13, var7, var9, var11));
            this.field21.put(var1, var3);
         } else if (var2 == CoordinatesType2.ACTION_BAR) {
            ClientEventBus.method29().method12(HighlightImpl4.class, () -> new HighlightImpl4(var1, var2, var5, 0.0, var7, var9, var11));
         }
      }
   }

   private void method11(CoordinatesType var1, int var2) {
      int var3 = this.field20.put(var1, var2);
      if (var3 != var2) {
         this.method15();
      }
   }

   private long method12(String var1) {
      char var2 = var1.charAt(var1.length() - 1);
      if (Character.isDigit(var2)) {
         return ThreadModuleDump40.method1(var1);
      }

      int var3 = switch (var2) {
         case 'B', 'b' -> 1000000000;
         case 'K', 'k' -> 1000;
         case 'M', 'm' -> 1000000;
         default -> 1;
      };
      return ThreadModuleDump40.method1(var1.substring(0, var1.length() - 1)) * var3;
   }

   private void method13() {
      Map var1 = this.method14(this.field10.method6(), this.field10.method5());
      if (var1 != null) {
         this.field20.putAll(var1);
      } else {
         com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.Coordinates var2 = ThreadModuleDump63.method4()
            .method40()
            .method82()
            .method15()
            .method24();
         if (var2 != null) {
            Member var3 = this.field19.method9();
            if (var3 != null) {
               Experience var4 = var3.playerData().experience();
               if (var4.elm().exists()) {
                  for (Entry var6 : var4.elm().asMapOpt(BetterJson::asDouble).entrySet()) {
                     String var7 = ((String)var6.getKey()).substring(((String)var6.getKey()).indexOf(95) + 1);
                     double var8 = (Double)var6.getValue();
                     CoordinatesType var10 = CoordinatesType.of(var7);
                     if (var10 != null) {
                        int var11 = Coordinates.getLevel(var8, var10);
                        if (var11 != -1) {
                           this.field21.put(var10, var8);
                           this.field20.put(var10, var11);
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
   private Map<CoordinatesType, Integer> method14(String var1, String var2) {
      if (!field7.exists()) {
         return null;
      }

      if (var1 != null && var2 != null) {
         try {
            JsonElement var3 = JsonParser.parseReader(new FileReader(field7));
            if (!var3.isJsonObject()) {
               return null;
            }

            JsonObject var4 = var3.getAsJsonObject();
            if (!var4.has(var1)) {
               return null;
            }

            JsonObject var5 = var4.getAsJsonObject(var1);
            return !var5.has(var2)
               ? null
               : (Map)ThreadModuleDump48.field22.fromJson(var5.getAsJsonObject(var2), (new TypeToken<Object2IntOpenHashMap<CoordinatesType>>() {}).getType());
         } catch (IOException var6) {
            Inventorymod2.method5(var6, "Loading SkyBlock Skill Levels");
            return null;
         }
      } else {
         return null;
      }
   }

   private void method15() {
      String var1 = this.field10.method5();
      if (var1 != null) {
         try {
            Object var2;
            if (field7.exists()) {
               try {
                  var2 = JsonParser.parseReader(new FileReader(field7));
               } catch (JsonParseException var12) {
                  var2 = new JsonObject();
               }
            } else {
               var2 = new JsonObject();
            }

            if (!var2.isJsonObject()) {
               return;
            }

            String var3 = this.field10.method6();
            JsonObject var4 = var2.getAsJsonObject();
            JsonObject var5 = var4.getAsJsonObject(var3);
            if (var5 == null) {
               var5 = new JsonObject();
            }

            JsonObject var6 = ThreadModuleDump48.field22.toJsonTree(this.field20).getAsJsonObject();
            var5.add(var1, var6);
            var4.add(var3, var5);

            try (FileWriter var7 = new FileWriter(field7)) {
               ThreadModuleDump48.field22.toJson(var4, var7);
            }
         } catch (IOException var13) {
            Inventorymod2.method5(var13, "Saving SkyBlock Skill Levels");
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
