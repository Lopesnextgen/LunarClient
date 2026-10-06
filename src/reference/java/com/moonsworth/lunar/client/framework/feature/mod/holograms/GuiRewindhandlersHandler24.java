package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemEntityBridge;
import com.moonsworth.lunar.bridge.Bridge_56;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms2_5;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType6;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.Holograms_7;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase$Data4;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers3;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.entity.EventEntityRemoval;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data2;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSoundPlay;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockModified;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldChanged;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockUpdateNotify.Data;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuiRewindhandlersHandler24 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final GuiRewindhandlersHandler2_2 field7 = (GuiRewindhandlersHandler2_2)this.method3(GuiRewindhandlersHandler2_2.class);
   private static final String field8 = "entity.bat.death";
   private static final String field9 = "entity.bat.hurt";
   private static final Pattern field10 = Pattern.compile("§7(\\d{1,2})/(\\d{1,2}) Secrets");
   private long field11;
   private Holograms_7 field12;

   public GuiRewindhandlersHandler24() {
      this.handle(HighlightBase$Data4.class, this::method1);
      this.handle(Data.class, this::method2);
      this.handle(EventSoundPlay.class, this::method3);
      this.handle(EventEntityRemoval.class, this::method4);
      this.handle(EventBlockModified.class, this::method5);
      this.handle(com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data.class, this::method6);
      this.handle(Data2.class, this::method7);
      this.handle(EventWorldChanged.class, this::method8);
   }

   private void method1(HighlightBase$Data4 var1) {
      this.field11 = ThreadModuleDump63.method3().bridge$getSystemTime();
   }

   private void method2(Data var1) {
      if (Click3.getIsland() == Gui2Extension3.DUNGEON) {
         Holograms2_5 var2 = this.field7.method5().orElse(null);
         if (var2 != null) {
            Holograms4Iterator var3 = var2.method29().method7();
            if (var3 != null) {
               com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3 var4 = var3.method23().orElse(null);
               if (var4 != null && var4.method26() != null) {
                  Set var5 = var3.method30().method9();
                  if (!var5.isEmpty()) {
                     Bridge3_23 var6 = var1.getBlock();
                     Vector3iBridge var7 = var1.method1();
                     Bridge_56 var8 = Bridge.method34();
                     if (var6 != var8.method23() && var6 != var8.method24()) {
                        if (var6 == var8.method9()) {
                           for (Holograms_7 var18 : var5) {
                              if (!var18.isFound()) {
                                 if (var18.getType() == HologramsType6.ESSENCE) {
                                    Vector3iBridge var21 = var4.method1(var18.getPos());
                                    if (var21.method1(var7)) {
                                       var18.setFound(true);
                                       this.method10(var18, var21);
                                       return;
                                    }
                                 }

                                 if (var18.getRedstoneKey() != null && !var18.getRedstoneKey().isEmpty()) {
                                    for (Vector3iBridge var12 : var18.getRedstoneKey()) {
                                       Vector3iBridge var13 = var4.method1(var12);
                                       if (var13.method1(var7)) {
                                          var18.method16(true);
                                          this.method9(var18, var13, Rewindhandlers3.Data.Type.REDSTONE_KEY_PICKUP);
                                       }
                                    }
                                 }
                              }
                           }
                        } else if (var6 == var8.method47()) {
                           for (Holograms_7 var19 : var5) {
                              if (!var19.isFound()) {
                                 ArrayList var23 = var19.getLevers();
                                 ArrayList var25 = var19.method10();
                                 if (var23 != null) {
                                    for (int var27 = 0; var27 < var23.size(); var27++) {
                                       if (!(Boolean)var25.get(var27)) {
                                          Vector3iBridge var14 = var4.method1((Vector3iBridge)var23.get(var27));
                                          if (var14.method1(var7)) {
                                             var19.method10().set(var27, true);
                                             this.method9(var19, var14, Rewindhandlers3.Data.Type.LEVER);
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        } else if (var6 == var8.method48()) {
                           for (Holograms_7 var20 : var5) {
                              if (!var20.isFound() && var20.getRedstoneKeyPlacement() != null && var20.method12()) {
                                 Vector3iBridge var24 = var4.method1(var20.getRedstoneKeyPlacement());
                                 boolean var26 = false;
                                 if (var24.method1(var7)) {
                                    var20.method17(true);
                                    var26 = true;
                                 }

                                 if (var26) {
                                    this.method10(var20, var24);
                                    return;
                                 }
                              }
                           }
                        }
                     } else {
                        for (Holograms_7 var10 : var5) {
                           if (!var10.isFound() && var10.getType() == HologramsType6.CHEST) {
                              Vector3iBridge var11 = var4.method1(var10.getPos());
                              if (var11.method1(var7)) {
                                 var10.setFound(true);
                                 this.field12 = var10;
                                 this.method10(var10, var11);
                                 return;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method3(EventSoundPlay var1) {
      if (Click3.getIsland() == Gui2Extension3.DUNGEON) {
         Holograms2_5 var2 = this.field7.method5().orElse(null);
         if (var2 != null) {
            Holograms4Iterator var3 = var2.method29().method7();
            if (var3 != null) {
               com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3 var4 = var3.method23().orElse(null);
               if (var4 != null && var4.method26() != null) {
                  Set var5 = var3.method30().method9();
                  if (!var5.isEmpty()) {
                     String var6 = var1.getPath();
                     if (var6.equals("entity.bat.death") || var6.equals("entity.bat.hurt")) {
                        Bridge5Extension_5 var7 = ThreadModuleDump63.method7();
                        Holograms_7 var8 = null;
                        Vector3iBridge var9 = null;
                        double var10 = 0.0;

                        for (Holograms_7 var13 : var5) {
                           if (!var13.isFound() && var13.getType() == HologramsType6.BAT) {
                              Vector3iBridge var14 = var4.method1(var13.getPos());
                              double var15 = var7.ORICHRORRORHORHOIHCRHOORCRRHOI(var14.bridge$getX(), var14.bridge$getY(), var14.bridge$getZ());
                              if (var8 == null || var15 < var10) {
                                 var8 = var13;
                                 var9 = var14;
                                 var10 = var15;
                              }
                           }
                        }

                        if (var8 != null) {
                           var8.setFound(true);
                           this.method10(var8, var9);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method4(EventEntityRemoval var1) {
      if (Click3.getIsland() == Gui2Extension3.DUNGEON) {
         if (var1.method1() instanceof ItemEntityBridge var2) {
            if (var2.bridge$getItemStack().bridge$getDisplayName().charAt(0) == 167) {
               Bridge5Extension_5 var17 = ThreadModuleDump63.method7();
               if (!(var17.method2(var2) > 100.0)) {
                  Holograms2_5 var4 = this.field7.method5().orElse(null);
                  if (var4 != null) {
                     Holograms4Iterator var5 = var4.method29().method7();
                     if (var5 != null) {
                        com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3 var6 = var5.method23().orElse(null);
                        if (var6 != null && var6.method26() != null) {
                           Set var7 = var5.method30().method9();
                           if (!var7.isEmpty()) {
                              Holograms_7 var8 = null;
                              Vector3iBridge var9 = null;
                              double var10 = 0.0;

                              for (Holograms_7 var13 : var7) {
                                 if (!var13.isFound() && var13.getType() == HologramsType6.ITEM_DROP) {
                                    Vector3iBridge var14 = var6.method1(var13.getPos());
                                    double var15 = var17.method15(var14.bridge$getX(), var14.bridge$getY(), var14.bridge$getZ());
                                    if (var8 == null || var15 < var10) {
                                       var8 = var13;
                                       var9 = var14;
                                       var10 = var15;
                                    }
                                 }
                              }

                              if (var8 != null && !(var10 >= 49.0)) {
                                 var8.setFound(true);
                                 this.method10(var8, var9);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method5(EventBlockModified var1) {
      if (Click3.getIsland() == Gui2Extension3.DUNGEON) {
         Holograms2_5 var2 = this.field7.method5().orElse(null);
         if (var2 != null) {
            Holograms4Iterator var3 = var2.method29().method7();
            if (var3 != null) {
               com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3 var4 = var3.method23().orElse(null);
               if (var4 != null && var4.method26() != null) {
                  Set var5 = var3.method30().method9();
                  if (!var5.isEmpty()) {
                     Bridge_56 var6 = Bridge.method34();
                     if (var1.method3().bridge$getBlock() == var6.method3()) {
                        Vector3iBridge var7 = var1.method1();

                        for (Holograms_7 var9 : var5) {
                           if (!var9.isFound() && var9.getSuperboom() != null) {
                              Vector3iBridge var10 = var4.method1(var9.getSuperboom());
                              if (var10.method1(var7)) {
                                 var9.method15(true);
                                 this.method9(var9, var10, Rewindhandlers3.Data.Type.SUPERBOOM);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method6(com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data var1) {
      if (Click3.getIsland() == Gui2Extension3.DUNGEON) {
         Holograms2_5 var2 = this.field7.method5().orElse(null);
         if (var2 != null) {
            Holograms4Iterator var3 = var2.method29().method7();
            if (var3 != null) {
               com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3 var4 = var3.method23().orElse(null);
               if (var4 != null && var4.method26() != null) {
                  Set var5 = var3.method30().method9();
                  if (!var5.isEmpty()) {
                     String var6 = var1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
                     if (!var6.equals("SOUL! You found a Fairy Soul!") && !var6.equals("You have already found that Fairy Soul!")) {
                        if (this.field12 != null && var6.equals("That chest is locked!")) {
                           this.field12.setFound(false);
                           this.method9(this.field12, var4.method1(this.field12.getPos()), Rewindhandlers3.Data.Type.LOCKED_CHEST);
                           this.field12 = null;
                        }
                     } else {
                        for (Holograms_7 var8 : var5) {
                           if (!var8.isFound() && var8.getType() == HologramsType6.FAIRY_SOUL) {
                              var8.setFound(true);
                              this.method10(var8, var4.method1(var8.getPos()));
                              return;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method7(Data2 var1) {
      if (Click3.getIsland() == Gui2Extension3.DUNGEON) {
         Holograms2_5 var2 = this.field7.method5().orElse(null);
         if (var2 != null) {
            Holograms4Iterator var3 = var2.method29().method7();
            if (var3 != null) {
               com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3 var4 = var3.method23().orElse(null);
               if (var4 != null && var4.method26() != null) {
                  Set var5 = var3.method30().method9();
                  if (!var5.isEmpty()) {
                     if (ThreadModuleDump63.method3().bridge$getSystemTime() - this.field11 >= 2000L) {
                        String var6 = var1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
                        Matcher var7 = field10.matcher(var6);
                        if (var7.find()) {
                           int var8 = Integer.parseInt(var7.group(1));
                           int var9 = Integer.parseInt(var7.group(2));
                           if (var8 >= var9) {
                              for (Holograms_7 var11 : var5) {
                                 if (!var11.isFound() && var11.getType() != HologramsType6.FAIRY_SOUL) {
                                    var11.setFound(true);
                                    this.method9(var11, var4.method1(var11.getPos()), Rewindhandlers3.Data.Type.ROOM_COMPLETED);
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method8(EventWorldChanged var1) {
      this.field12 = null;
   }

   private void method9(Holograms_7 var1, Vector3iBridge var2, Rewindhandlers3.Data.Type var3) {
      ClientEventBus.method29().method12(Rewindhandlers3.Data.class, () -> new Rewindhandlers3.Data(var1, var2, var3));
   }

   private void method10(Holograms_7 var1, Vector3iBridge var2) {
      ClientEventBus.method29().method12(Rewindhandlers3.Data2.class, () -> new Rewindhandlers3.Data2(var1, var2));
   }
}
