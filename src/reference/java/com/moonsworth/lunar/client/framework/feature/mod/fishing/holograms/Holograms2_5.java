package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_33;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5_4;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter2_3;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType23;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Holograms4Updater;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.Lotusfish2;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler2_2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler24;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase$Data3;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase$Data4;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler25;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector2i;

public class Holograms2_5 {
   private final GuiRewindhandlersHandler2_2 field1;
   private final GuiRewindhandlersHandler24 field2;
   private final GuiRewindhandlersHandler25 field3;
   private static final Pattern field4 = Pattern.compile("(?<puzzle>.+): \\[✖].*");
   private static final Pattern field5 = Pattern.compile("(?<puzzle>.+): \\[✔]");
   private static final Pattern field6 = Pattern.compile("(?<puzzle>.+): \\[✦]");
   public static final Vector2i field7 = new Vector2i(-200, -200);
   public static final int field8 = 32;
   private final Holograms_3 field9 = new Holograms_3(this);
   private final List<Holograms4Updater> field10 = new ArrayList<>();
   private final Map<String, Holograms4Iterator> field11 = new HashMap<>();
   private final Map<String, Rewindhandlers> field12 = new HashMap<>();
   private final Set<Rewindhandlers> field13 = new HashSet<>();
   private final Set<Holograms4Iterator> field14 = new HashSet<>();
   private final List<Holograms4Iterator> field15 = new ArrayList<>();
   private final List<Rewindhandlers> field16 = new ArrayList<>();
   private final Map<String, Holograms4Updater> field17 = new HashMap<>();
   private final List<Holograms4> field18 = new ArrayList<>();
   private final HighlightType field19;
   private final boolean field20;
   private final double field21;
   public int field22 = 15;
   public int field23 = 20;
   public Vector2i field24 = null;
   private boolean field25 = false;
   private com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms2 field26;
   private Holograms4Iterator field27;
   private int field28;
   private boolean field29;
   private boolean field30;

   public Holograms2_5(HighlightType var1, boolean var2, GuiRewindhandlersHandler2_2 var3, GuiRewindhandlersHandler24 var4, GuiRewindhandlersHandler25 var5) {
      this.field1 = var3;
      this.field2 = var4;
      this.field3 = var5;
      this.field21 = ThreadModuleDump63.method3().bridge$getSystemTime();
      this.field19 = var1;
      this.field20 = var2;
   }

   public void method1() {
      if (this.field20) {
         this.method6();
      }
   }

   public void method2(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms2 var1) {
      if (this.field20 && !this.field25) {
         com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms2 var2 = this.method8();
         if (var2 != null && var2.method2() == var1.method2()) {
            this.field26 = var1;
            this.method9(var1);
         }
      }
   }

   public void method3() {
      if (this.field20) {
         Holograms4Updater var1 = this.method29();
         Holograms4Iterator var2 = var1.method7();

         for (Holograms4Updater var4 : this.field10) {
            var4.tick();
         }

         if (var2 == null && this.field14.isEmpty() && !this.field10.isEmpty() && var1.method27().isPresent()) {
            Bridge6_10 var6 = var1.method27().get();
            Nameplate4 var9 = new Nameplate4(var6.bridge$getPosX(), var6.bridge$getPosZ(), this);
            Holograms_4.method13(this, Nameplate4.method1(var9.method8(), var9.method9(), this));
         }

         if (var2 != null && var2.method23().isEmpty()) {
            Holograms_4.method1(var2, false);
         }

         this.method4();
         if (var2 != null && !var1.method18() && this.field1.method10()) {
            int var7 = this.field1.method13();
            int var10 = this.field1.method15();
            if (var7 != -1 && (var2.method30().method1() != var7 || var2.method30().method7() != var10)) {
               var2.method13(var7);
               var2.method15(var10);
               if (!var2.method28().isEmpty()) {
                  Nameplate4 var5 = var2.method28().get(0);
                  this.method45().method2(new HologramsType23(new Vector2i(var5.method8(), var5.method9()), var7, var10));
               }
            }
         }

         Holograms4Iterator var8 = var2;
         if (var8 != null && var8.method23().isEmpty()) {
            var8 = null;
         }

         if (var8 != this.field27) {
            if (this.field20) {
               ClientEventBus.method29().method12(HighlightBase$Data3.class, HighlightBase$Data3::new);
               if (var8 != null) {
                  ClientEventBus.method29().method12(HighlightBase$Data4.class, HighlightBase$Data4::new);
               }
            }

            this.field27 = var8;
         }
      }
   }

   private void method4() {
      if (!ThreadModuleDump63.method4().method40().method85().method17(var0 -> var0.method41().method18() / 50L > 20L)) {
         ClientPacketListenerBridge var1 = ThreadModuleDump63.method9();
         if (var1 != null) {
            int var2 = 0;
            int var3 = 0;
            int var4 = 0;

            for (Bridge2_33 var6 : var1.bridge$getSortedPlayerInfoMap()) {
               if (var6.bridge$getDisplayName() != null) {
                  String var7 = AdventureTextBridge.getTextContent(var6.bridge$getDisplayName());
                  Matcher var8 = field4.matcher(var7.trim());
                  int var9 = var2 + var3 + var4;
                  if (var8.matches()) {
                     this.method5(
                        var9, var8.group("puzzle"), com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.OPENED
                     );
                     var2++;
                  } else {
                     Matcher var10 = field5.matcher(var7.trim());
                     if (var10.matches()) {
                        this.method5(
                           var9, var10.group("puzzle"), com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED
                        );
                        var3++;
                     } else {
                        Matcher var11 = field6.matcher(var7.trim());
                        if (var11.matches()) {
                           this.method5(
                              var9, var11.group("puzzle"), com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.OPENED
                           );
                           var4++;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method5(int var1, String var2, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2 var3) {
      if (this.field18.size() <= var1) {
         this.field18.add(new Holograms4(this, var2, var3));
      } else {
         Holograms4 var4 = this.field18.get(var1);
         var4.method2(var2, var3);
      }
   }

   private void method6() {
      ClientPacketListenerBridge var1 = ThreadModuleDump63.method9();
      if (var1 != null) {
         this.field10.clear();
         String var2 = this.field3.method5();
         Lotusfish2.Data var3 = null;
         Bridge2_33 var4 = null;
         int var5 = 0;
         Holograms4Updater var6 = null;
         boolean var7 = false;
         boolean var8 = false;

         for (Bridge2_33 var10 : var1.bridge$getSortedPlayerInfoMap()) {
            if (var10.bridge$getDisplayName() != null) {
               String var11 = AdventureTextBridge.getTextContent(var10.bridge$getDisplayName());
               Lotusfish2.Data var12 = Lotusfish2.method1(var11);
               if (var12 == null) {
                  if (var7) {
                     var8 = true;
                     var7 = false;
                  } else if (var8) {
                     if (var11.trim().startsWith("Revive Stones: ")) {
                        String[] var13 = var11.split(": ");
                        if (var13.length >= 2) {
                           try {
                              int var14 = Integer.parseInt(var13[1]);
                              if (var6 == null) {
                                 var5 = var14;
                              } else {
                                 var6.method34(var14);
                              }
                           } catch (NumberFormatException var15) {
                           }
                        }
                     }

                     var8 = false;
                  }
               } else {
                  var7 = true;
                  if (var12.playerName().equals(var2)) {
                     var3 = var12;
                     var4 = var10;
                     var6 = null;
                  } else {
                     Holograms4Updater var17 = this.method7(var10);
                     this.field10.add(var17);
                     var17.method9(var12.playerName(), var12.method3(), var12.method4());
                     var17.method31(var10);
                     var6 = var17;
                  }
               }
            }
         }

         if (var3 != null) {
            Holograms4Updater var16 = this.method7(var4);
            this.field10.add(var16);
            var16.method9(var3.playerName(), var3.method3(), var3.method4());
            var16.method31(var4);
            var16.method34(var5);
         }
      }
   }

   private Holograms4Updater method7(Bridge2_33 var1) {
      return this.field17.computeIfAbsent(var1.bridge$getGameProfile().getName(), var2 -> new Holograms4Updater(var1, this));
   }

   private com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms2 method8() {
      if (ThreadModuleDump63.method7() == null) {
         return null;
      }

      ItemStackBridge var1 = (ItemStackBridge)ThreadModuleDump63.method7().bridge$getInventory().bridge$getMainInventory().get(8);
      if (var1 == null || var1.bridge$isEmpty() || var1.bridge$getItem() != Bridge.method28().method30() || !var1.bridge$hasDisplayName()) {
         return this.field26;
      }

      if (!var1.bridge$getDisplayName().contains("Magical Map")) {
         return null;
      }

      if (this.method48()) {
         return null;
      }

      if (var1.bridge$getItem() instanceof Bridge5_4 var2) {
         Itemcounter2_3 var5 = var2.bridge$getMapData(var1, ThreadModuleDump63.method8());
         Integer var4 = var2.bridge$getMapId(var1, ThreadModuleDump63.method8());
         if (var5 != null && var4 != null) {
            this.field26 = new com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms2(var5, var4);
            return this.field26;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private void method9(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms2 var1) {
      if (var1 != null) {
         this.method10(var1.method1().bridge$getMapDecorations());
         this.method11(var1);
         if (this.field24 != null) {
            this.method12(var1);
         }
      }
   }

   private void method10(Map<String, Itemcounter_2> var1) {
      if (this.field24 != null) {
         long var2 = ThreadModuleDump63.method3().bridge$getSystemTime();
         int var4 = 0;

         for (int var5 = 0; var5 < var1.size(); var5++) {
            Itemcounter_2 var6 = (Itemcounter_2)var1.get("icon-" + var5);
            if (var6 != null) {
               if (var4 >= this.field10.size()) {
                  return;
               }

               Holograms4Updater var7;
               for (var7 = this.field10.get(var4); var7.isDead(); var7 = this.field10.get(var4)) {
                  if (++var4 >= this.field10.size()) {
                     return;
                  }
               }

               if (var2 - var7.method15() < 1500L) {
                  var4++;
               } else {
                  double var8 = (var6.bridge$getX() - this.field24.x() * 2 + 128) / 256.0 * 6.0 * 32.0 + field7.x();
                  double var10 = (var6.bridge$getY() - this.field24.y() * 2 + 128) / 256.0 * 6.0 * 32.0 + field7.y();
                  double var12 = var6.bridge$getRot() * 360 / 16.0 + 180.0;
                  var7.method5(var12, 500L);
                  var7.method30().method6(var8, 500L);
                  var7.method30().method7(var10, 500L);
                  var4++;
               }
            }
         }
      }
   }

   private void method11(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms2 var1) {
      if (this.field24 == null) {
         Vector2i var2 = null;
         int var3 = 0;
         byte[] var4 = var1.method1().bridge$getColors();

         for (byte var5 = 0; var5 < 128; var5 += 5) {
            for (byte var6 = 0; var6 < 128; var6 += 5) {
               if (var4[var5 + var6 * 128] == 30) {
                  int var7 = var5;
                  int var8 = var6;

                  while (var4[var7 - 1 + var8 * 128] == 30) {
                     var7--;
                  }

                  while (var4[var7 + (var8 - 1) * 128] == 30) {
                     var8--;
                  }

                  int var9 = 0;

                  while (var4[var7 + var9 + 1 + var8 * 128] == 30) {
                     var9++;
                  }

                  if (var9 != 0 && var9 > var3) {
                     var3 = var9;
                     int var10 = var9 * 4 / 3;
                     int var11 = var7 % var10;
                     int var12 = var8 % var10;
                     int var13 = this.field19.getNumber();
                     if (var13 == 0 || var13 == 1) {
                        var11 += var10;
                     }

                     if (var13 == 0) {
                        var12 += var10;
                     }

                     var2 = new Vector2i(var11, var12);
                  }
               }
            }
         }

         if (var2 != null) {
            this.field22 = var3;
            this.field23 = var3 * 4 / 3;
            this.field24 = var2;
         }
      }
   }

   private void method12(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms2 var1) {
      if (this.field24.x() > 0 && this.field24.y() > 0) {
         byte[] var2 = var1.method1().bridge$getColors();

         for (int var3 = 0; var3 < 6; var3++) {
            for (int var4 = 0; var4 < 6; var4++) {
               int var5 = this.field24.x() + var3 * this.field23;
               int var6 = this.field24.y() + var4 * this.field23;
               if (var5 + this.field22 < 128 && var6 + this.field22 / 2 + 3 < 128) {
                  byte var7 = var2[var5 + var6 * 128];
                  HologramsType5 var8 = Holograms3_6.field20.get(Integer.valueOf(var7));
                  if (var8 != null) {
                     Holograms4Iterator var9 = this.method13(var3, var4);
                     boolean var10 = var9 == null;
                     Holograms4Iterator var11 = null;
                     byte var12 = var2[var5 + var6 * 128 - 128];
                     if (var12 != 0) {
                        var11 = this.method13(var3, var4 - 1);
                     }

                     byte var13 = var2[var5 + this.field22 + var6 * 128 + 1];
                     byte var14 = var2[var5 + this.field23 + var6 * 128 - 128];
                     if (var13 != 0 && var14 != 0 && var11 == null) {
                        var11 = this.method13(var3 + 1, var4 - 1);
                     }

                     byte var15 = var2[var5 - 1 + var6 * 128];
                     if (var15 != 0 && var11 == null) {
                        var11 = this.method13(var3 - 1, var4);
                     }

                     if (var9 == null && var11 == null) {
                        var9 = new Holograms4Iterator(this, var8);
                     }

                     if (var9 != null) {
                        if (var9.method30().method6() != var8 && (var9.method30().method6() == HologramsType5.UNKNOWN || var8 != HologramsType5.UNKNOWN)) {
                           var9.method16(var8);
                        }

                        byte var16 = var2[var5 + this.field22 / 2 + (var6 + this.field22 / 2 + 3) * 128];
                        if (var16 != var7) {
                           com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2 var17 = Holograms3_6.field21
                              .get(Integer.valueOf(var16));
                           if (var17 != null) {
                              var9.method10(var17);
                           }
                        }

                        if (var9.method30().method2() == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.ADJACENT
                           && var16 == var7) {
                           var9.method10(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.OPENED);
                        }
                     }

                     if (var11 != var9) {
                        if (var11 != null && var9 != null) {
                           this.field14.remove(var11);

                           for (Nameplate4 var33 : var11.method28()) {
                              var9.method5(var33);
                           }
                        }

                        if (var9 == null) {
                           var9 = var11;
                        }

                        if (var10) {
                           var9.method5(Nameplate4.method1(var3, var4, this));
                        }
                     }
                  }
               }
            }
         }

         for (int var18 = 0; var18 < 6; var18++) {
            for (int var19 = 0; var19 < 6; var19++) {
               int var20 = this.field24.x() + var18 * this.field23;
               int var21 = this.field24.y() + var19 * this.field23;
               if (var20 < 128 - this.field23 && var21 < 128 - this.field23) {
                  byte var22 = var2[var20 + 1 + this.field22 + (var21 + this.field22 / 2) * 128];
                  HologramsType5 var23 = Holograms3_6.field20.get(Integer.valueOf(var22));
                  if (var23 != null) {
                     Holograms4Iterator var24 = this.method13(var18, var19);
                     Holograms4Iterator var26 = this.method13(var18 + 1, var19);
                     if (var24 != null && var26 != null && var24 != var26) {
                        Rewindhandlers var28 = this.method15(var18, var19, var18 + 1, var19);
                        if (var28 == null) {
                           var28 = new Rewindhandlers(this, var23, Nameplate4.method1(var18, var19, this), Nameplate4.method1(var18 + 1, var19, this));
                           this.method22(var28);
                        }

                        if (var28.method8() != var23) {
                           var28.method1(var23);
                        }
                     }
                  }

                  byte var25 = var2[var20 + this.field22 / 2 + (var21 + this.field22 + 1) * 128];
                  HologramsType5 var27 = Holograms3_6.field20.get(Integer.valueOf(var25));
                  if (var27 != null) {
                     Holograms4Iterator var29 = this.method13(var18, var19);
                     Holograms4Iterator var30 = this.method13(var18, var19 + 1);
                     if (var29 != null && var30 != null && var29 != var30) {
                        Rewindhandlers var31 = this.method15(var18, var19, var18, var19 + 1);
                        if (var31 == null) {
                           var31 = new Rewindhandlers(this, var27, Nameplate4.method1(var18, var19, this), Nameplate4.method1(var18, var19 + 1, this));
                           this.method22(var31);
                        }

                        if (var31.method8() != var27) {
                           var31.method1(var27);
                        }
                     }
                  }
               }
            }
         }
      } else {
         this.field24 = null;
      }
   }

   public Holograms4Iterator method13(int var1, int var2) {
      String var3 = var1 + "," + var2;
      return this.field11.get(var3);
   }

   public Holograms4Iterator method14(Nameplate4 var1) {
      return this.method13(var1.method8(), var1.method9());
   }

   public Rewindhandlers method15(int var1, int var2, int var3, int var4) {
      String var5 = var1 + "," + var2 + "," + var3 + "," + var4;
      return this.field12.get(var5);
   }

   public Rewindhandlers method16(Nameplate4 var1, Nameplate4 var2) {
      return this.method15(var1.method8(), var1.method9(), var2.method8(), var2.method9());
   }

   public void method17(Rewindhandlers var1) {
      this.field13.add(var1);
   }

   public void method18(Rewindhandlers var1) {
      this.field13.remove(var1);
   }

   public Collection<Rewindhandlers> method19() {
      return this.field13;
   }

   public Collection<Holograms4Updater> getPlayers() {
      return this.field10;
   }

   public void method20(Holograms4Iterator var1, Nameplate4 var2) {
      String var3 = var2.method8() + "," + var2.method9();
      this.field11.put(var3, var1);
      this.field14.add(var1);
   }

   public void method21(Nameplate4 var1) {
      String var2 = var1.method8() + "," + var1.method9();
      this.field11.remove(var2);
   }

   public void method22(Rewindhandlers var1) {
      String var2 = var1.method6().method8() + "," + var1.method6().method9() + "," + var1.method7().method8() + "," + var1.method7().method9();
      this.field12.put(var2, var1);
      Holograms4Iterator var3 = this.method14(var1.method6());
      if (var3 != null) {
         var3.method29().add(var1);
      }

      Holograms4Iterator var4 = this.method14(var1.method7());
      if (var4 != null) {
         var4.method29().add(var1);
      }
   }

   public Collection<Holograms4Iterator> method23() {
      return this.field14;
   }

   public Collection<Rewindhandlers> method24() {
      return this.field12.values();
   }

   public Collection<Holograms4Iterator> method25() {
      return this.field15.isEmpty() ? this.method23() : this.field15;
   }

   public Collection<Rewindhandlers> method26() {
      return this.field16.isEmpty() ? this.method24() : this.field16;
   }

   public int method27() {
      if (this.field24 == null) {
         return switch (this.field19.getNumber()) {
            case 0, 1, 2, 3 -> 5;
            default -> 6;
         };
      } else {
         return (128 - this.field24.x) / this.field23;
      }
   }

   public int method28() {
      if (this.field24 == null) {
         return this.method34() <= 4 ? 5 : 6;
      } else {
         return (128 - this.field24.y) / this.field23;
      }
   }

   @NotNull
   public Holograms4Updater method29() {
      if (this.field10.isEmpty()) {
         this.field10.add(new Holograms4Updater(null, this));
      }

      return this.field10.get(this.field10.size() - 1);
   }

   public void method30() {
      if (Click3.getIsland() == com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3.DUNGEON) {
         Holograms4Updater var1 = this.method29();
         var1.method41(this.field2.method6());
         var1.method43(this.field2.method5());
      }
   }

   public Optional<Holograms4Updater> method31(String var1) {
      return this.field10.stream().filter(var1x -> var1x.method20(false).equals(var1)).findAny();
   }

   public Optional<Holograms4Updater> getPlayer(UUID var1) {
      return this.field10.stream().filter(var1x -> var1.equals(var1x.getUuid())).findAny();
   }

   public void method32() {
      for (Holograms4Iterator var2 : this.method23()) {
         if (var2.method30().method6() == HologramsType5.BLOOD) {
            var2.method10(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.CLEARED);
         }
      }
   }

   public void method33() {
      if (!this.field25) {
         for (Holograms4Updater var2 : this.getPlayers()) {
            var2.method2(false);
         }
      }

      this.field25 = true;
   }

   public int method34() {
      return this.field19.getNumber();
   }

   public boolean method35(Holograms4 var1) {
      for (Holograms4Iterator var3 : this.method23()) {
         if (var3.method30().method6() == HologramsType5.PUZZLE
            && var3.method30().method2() != com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.ADJACENT
            && !var3.method18()) {
            var3.method19(var1);
            return true;
         }
      }

      return false;
   }

   public void method36(long var1) {
      for (Holograms4Updater var4 : this.getPlayers()) {
         var4.method21(var1);
      }

      if (var1 == 0L) {
         this.field15.clear();
         this.field16.clear();
      } else {
         if (this.field15.size() < this.field14.size()) {
            this.field15.clear();

            for (Holograms4Iterator var10 : this.method23()) {
               Holograms4Iterator var5 = new Holograms4Iterator(this, HologramsType5.UNKNOWN);
               var5.method26(var10.getEvents());
               this.field15.add(var5);
            }
         }

         if (this.field16.size() < this.field12.size()) {
            this.field16.clear();

            for (Rewindhandlers var11 : this.method24()) {
               Rewindhandlers var14 = new Rewindhandlers(this, null, var11.method6(), var11.method7());
               var14.method3(var11.getEvents());
               this.field16.add(var14);
            }
         }

         for (Holograms4Iterator var12 : this.field15) {
            var12.method21(var1);
         }

         for (Rewindhandlers var13 : this.field16) {
            var13.method4(var1);
         }
      }
   }

   public void method37() {
      this.field30 = true;

      for (Holograms4Updater var2 : this.getPlayers()) {
         var2.method2(true);
      }
   }

   public boolean method38() {
      return this.field30;
   }

   public boolean method39() {
      return false;
   }

   public void method40() {
      this.field28++;
   }

   public void method41() {
      this.field28--;
   }

   public Holograms4Iterator method42() {
      return this.field14.stream().filter(var0 -> var0.method30().method6() == HologramsType5.SPAWN).findFirst().orElse(null);
   }

   public void method43() {
      this.field29 = true;
   }

   public void method44() {
      for (Holograms4Iterator var2 : this.field14) {
         HologramsType8 var3 = var2.method25();
         if (var3 != null && (var3 == HologramsType8.BLAZE_LOWER || var3 == HologramsType8.BLAZE_UPPER)) {
            if (var2.method30().method1() < var2.method14()) {
               var2.method10(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.CLEARED);
            } else {
               var2.method10(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.COMPLETED);
            }
         }
      }
   }

   @Generated
   public Holograms_3 method45() {
      return this.field9;
   }

   @Generated
   public HighlightType method46() {
      return this.field19;
   }

   @Generated
   public double method47() {
      return this.field21;
   }

   @Generated
   public boolean method48() {
      return this.field25;
   }

   @Generated
   public int method49() {
      return this.field28;
   }

   @Generated
   public boolean method50() {
      return this.field29;
   }
}
