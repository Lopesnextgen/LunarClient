package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.EntityItemBridge;
import com.moonsworth.lunar.bridge.BlocksBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.SecretType;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.RoomSecret;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.DungeonRoomDetectedEvent;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.RoomSecretEvent;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.entity.EventEntityRemove;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage.EventActionBarMessage;
import com.moonsworth.lunar.client.event.mixin.fishing.EventPlaySound;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockChange;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockUpdate.BlockUpdate;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DungeonSecretListener extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final DungeonMapListener field7 = (DungeonMapListener)this.method3(DungeonMapListener.class);
   private static final String field8 = "entity.bat.death";
   private static final String field9 = "entity.bat.hurt";
   private static final Pattern field10 = Pattern.compile("§7(\\d{1,2})/(\\d{1,2}) Secrets");
   private long field11;
   private RoomSecret field12;

   public DungeonSecretListener() {
      this.handle(DungeonRoomDetectedEvent.class, this::method1);
      this.handle(BlockUpdate.class, this::method2);
      this.handle(EventPlaySound.class, this::method3);
      this.handle(EventEntityRemove.class, this::method4);
      this.handle(EventBlockChange.class, this::method5);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method6);
      this.handle(EventActionBarMessage.class, this::method7);
      this.handle(EventWorldChange.class, this::method8);
   }

   private void method1(DungeonRoomDetectedEvent highlightbase$data41) {
      this.field11 = Ref.method3().bridge$getSystemTime();
   }

   private void method2(BlockUpdate data1) {
      if (IslandUtils.getIsland() == SkyblockIsland.DUNGEON) {
         DungeonStateTracker holograms2_52 = this.field7.method5().orElse(null);
         if (holograms2_52 != null) {
            DungeonRoomTracker holograms4iterator3 = holograms2_52.method29().method7();
            if (holograms4iterator3 != null) {
               com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms34 = holograms4iterator3.method23().orElse(null);
               if (holograms34 != null && holograms34.method26() != null) {
                  Set set5 = holograms4iterator3.method30().method9();
                  if (!set5.isEmpty()) {
                     Bridge3_23 bridge3_236 = data1.getBlock();
                     Vec3iBridge horsestats207 = data1.IIRHOIHIOOOCIIICROHOROCIOHHORC();
                     BlocksBridge bridge_568 = Bridge.method34();
                     if (bridge3_236 != bridge_568.method23() && bridge3_236 != bridge_568.method24()) {
                        if (bridge3_236 == bridge_568.method9()) {
                           for (RoomSecret holograms_718 : set5) {
                              if (!holograms_718.isFound()) {
                                 if (holograms_718.getType() == SecretType.ESSENCE) {
                                    Vec3iBridge horsestats2021 = holograms34.method1(holograms_718.getPos());
                                    if (horsestats2021.method1(horsestats207)) {
                                       holograms_718.setFound(true);
                                       this.method10(holograms_718, horsestats2021);
                                       return;
                                    }
                                 }

                                 if (holograms_718.getRedstoneKey() != null && !holograms_718.getRedstoneKey().isEmpty()) {
                                    for (Vec3iBridge horsestats2012 : holograms_718.getRedstoneKey()) {
                                       Vec3iBridge horsestats2013 = holograms34.method1(horsestats2012);
                                       if (horsestats2013.method1(horsestats207)) {
                                          holograms_718.method16(true);
                                          this.method9(holograms_718, horsestats2013, RoomSecretEvent.Data.Type.REDSTONE_KEY_PICKUP);
                                       }
                                    }
                                 }
                              }
                           }
                        } else if (bridge3_236 == bridge_568.method47()) {
                           for (RoomSecret holograms_719 : set5) {
                              if (!holograms_719.isFound()) {
                                 ArrayList list23 = holograms_719.getLevers();
                                 ArrayList list25 = holograms_719.method10();
                                 if (list23 != null) {
                                    for (int index27 = 0; index27 < list23.size(); index27++) {
                                       if (!(Boolean)list25.get(index27)) {
                                          Vec3iBridge horsestats2014 = holograms34.method1((Vec3iBridge)list23.get(index27));
                                          if (horsestats2014.method1(horsestats207)) {
                                             holograms_719.method10().set(index27, true);
                                             this.method9(holograms_719, horsestats2014, RoomSecretEvent.Data.Type.LEVER);
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        } else if (bridge3_236 == bridge_568.method48()) {
                           for (RoomSecret holograms_720 : set5) {
                              if (!holograms_720.isFound() && holograms_720.getRedstoneKeyPlacement() != null && holograms_720.method12()) {
                                 Vec3iBridge horsestats2024 = holograms34.method1(holograms_720.getRedstoneKeyPlacement());
                                 boolean flag26 = false;
                                 if (horsestats2024.method1(horsestats207)) {
                                    holograms_720.method17(true);
                                    flag26 = true;
                                 }

                                 if (flag26) {
                                    this.method10(holograms_720, horsestats2024);
                                    return;
                                 }
                              }
                           }
                        }
                     } else {
                        for (RoomSecret holograms_710 : set5) {
                           if (!holograms_710.isFound() && holograms_710.getType() == SecretType.CHEST) {
                              Vec3iBridge horsestats2011 = holograms34.method1(holograms_710.getPos());
                              if (horsestats2011.method1(horsestats207)) {
                                 holograms_710.setFound(true);
                                 this.field12 = holograms_710;
                                 this.method10(holograms_710, horsestats2011);
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

   private void method3(EventPlaySound highlightimpl131) {
      if (IslandUtils.getIsland() == SkyblockIsland.DUNGEON) {
         DungeonStateTracker holograms2_52 = this.field7.method5().orElse(null);
         if (holograms2_52 != null) {
            DungeonRoomTracker holograms4iterator3 = holograms2_52.method29().method7();
            if (holograms4iterator3 != null) {
               com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms34 = holograms4iterator3.method23().orElse(null);
               if (holograms34 != null && holograms34.method26() != null) {
                  Set set5 = holograms4iterator3.method30().method9();
                  if (!set5.isEmpty()) {
                     String text6 = highlightimpl131.getPath();
                     if (text6.equals("entity.bat.death") || text6.equals("entity.bat.hurt")) {
                        Bridge5Extension_5 bridge5extension_57 = Ref.method7();
                        RoomSecret holograms_78 = null;
                        Vec3iBridge horsestats209 = null;
                        double value10 = 0.0;

                        for (RoomSecret holograms_713 : set5) {
                           if (!holograms_713.isFound() && holograms_713.getType() == SecretType.BAT) {
                              Vec3iBridge horsestats2014 = holograms34.method1(holograms_713.getPos());
                              double value15 = bridge5extension_57.method15(horsestats2014.bridge$getX(), horsestats2014.bridge$getY(), horsestats2014.bridge$getZ());
                              if (holograms_78 == null || value15 < value10) {
                                 holograms_78 = holograms_713;
                                 horsestats209 = horsestats2014;
                                 value10 = value15;
                              }
                           }
                        }

                        if (holograms_78 != null) {
                           holograms_78.setFound(true);
                           this.method10(holograms_78, horsestats209);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method4(EventEntityRemove highlightimpl121) {
      if (IslandUtils.getIsland() == SkyblockIsland.DUNGEON) {
         if (highlightimpl121.method1() instanceof EntityItemBridge bridgeextension522) {
            if (bridgeextension522.bridge$getItemStack().bridge$getDisplayName().charAt(0) == 167) {
               Bridge5Extension_5 bridge5extension_517 = Ref.method7();
               if (!(bridge5extension_517.method2(bridgeextension522) > 100.0)) {
                  DungeonStateTracker holograms2_54 = this.field7.method5().orElse(null);
                  if (holograms2_54 != null) {
                     DungeonRoomTracker holograms4iterator5 = holograms2_54.method29().method7();
                     if (holograms4iterator5 != null) {
                        com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms36 = holograms4iterator5.method23().orElse(null);
                        if (holograms36 != null && holograms36.method26() != null) {
                           Set set7 = holograms4iterator5.method30().method9();
                           if (!set7.isEmpty()) {
                              RoomSecret holograms_78 = null;
                              Vec3iBridge horsestats209 = null;
                              double value10 = 0.0;

                              for (RoomSecret holograms_713 : set7) {
                                 if (!holograms_713.isFound() && holograms_713.getType() == SecretType.ITEM_DROP) {
                                    Vec3iBridge horsestats2014 = holograms36.method1(holograms_713.getPos());
                                    double value15 = bridge5extension_517.method15(horsestats2014.bridge$getX(), horsestats2014.bridge$getY(), horsestats2014.bridge$getZ());
                                    if (holograms_78 == null || value15 < value10) {
                                       holograms_78 = holograms_713;
                                       horsestats209 = horsestats2014;
                                       value10 = value15;
                                    }
                                 }
                              }

                              if (holograms_78 != null && !(value10 >= 49.0)) {
                                 holograms_78.setFound(true);
                                 this.method10(holograms_78, horsestats209);
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

   private void method5(EventBlockChange highlightimpl91) {
      if (IslandUtils.getIsland() == SkyblockIsland.DUNGEON) {
         DungeonStateTracker holograms2_52 = this.field7.method5().orElse(null);
         if (holograms2_52 != null) {
            DungeonRoomTracker holograms4iterator3 = holograms2_52.method29().method7();
            if (holograms4iterator3 != null) {
               com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms34 = holograms4iterator3.method23().orElse(null);
               if (holograms34 != null && holograms34.method26() != null) {
                  Set set5 = holograms4iterator3.method30().method9();
                  if (!set5.isEmpty()) {
                     BlocksBridge bridge_566 = Bridge.method34();
                     if (highlightimpl91.method3().bridge$getBlock() == bridge_566.method3()) {
                        Vec3iBridge horsestats207 = highlightimpl91.method1();

                        for (RoomSecret holograms_79 : set5) {
                           if (!holograms_79.isFound() && holograms_79.getSuperboom() != null) {
                              Vec3iBridge horsestats2010 = holograms34.method1(holograms_79.getSuperboom());
                              if (horsestats2010.method1(horsestats207)) {
                                 holograms_79.method15(true);
                                 this.method9(holograms_79, horsestats2010, RoomSecretEvent.Data.Type.SUPERBOOM);
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

   private void method6(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (IslandUtils.getIsland() == SkyblockIsland.DUNGEON) {
         DungeonStateTracker holograms2_52 = this.field7.method5().orElse(null);
         if (holograms2_52 != null) {
            DungeonRoomTracker holograms4iterator3 = holograms2_52.method29().method7();
            if (holograms4iterator3 != null) {
               com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms34 = holograms4iterator3.method23().orElse(null);
               if (holograms34 != null && holograms34.method26() != null) {
                  Set set5 = holograms4iterator3.method30().method9();
                  if (!set5.isEmpty()) {
                     String text6 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
                     if (!text6.equals("SOUL! You found a Fairy Soul!") && !text6.equals("You have already found that Fairy Soul!")) {
                        if (this.field12 != null && text6.equals("That chest is locked!")) {
                           this.field12.setFound(false);
                           this.method9(this.field12, holograms34.method1(this.field12.getPos()), RoomSecretEvent.Data.Type.LOCKED_CHEST);
                           this.field12 = null;
                        }
                     } else {
                        for (RoomSecret holograms_78 : set5) {
                           if (!holograms_78.isFound() && holograms_78.getType() == SecretType.FAIRY_SOUL) {
                              holograms_78.setFound(true);
                              this.method10(holograms_78, holograms34.method1(holograms_78.getPos()));
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

   private void method7(EventActionBarMessage data21) {
      if (IslandUtils.getIsland() == SkyblockIsland.DUNGEON) {
         DungeonStateTracker holograms2_52 = this.field7.method5().orElse(null);
         if (holograms2_52 != null) {
            DungeonRoomTracker holograms4iterator3 = holograms2_52.method29().method7();
            if (holograms4iterator3 != null) {
               com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomInstance holograms34 = holograms4iterator3.method23().orElse(null);
               if (holograms34 != null && holograms34.method26() != null) {
                  Set set5 = holograms4iterator3.method30().method9();
                  if (!set5.isEmpty()) {
                     if (Ref.method3().bridge$getSystemTime() - this.field11 >= 2000L) {
                        String text6 = data21.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
                        Matcher matcher7 = field10.matcher(text6);
                        if (matcher7.find()) {
                           int number8 = Integer.parseInt(matcher7.group(1));
                           int number9 = Integer.parseInt(matcher7.group(2));
                           if (number8 >= number9) {
                              for (RoomSecret holograms_711 : set5) {
                                 if (!holograms_711.isFound() && holograms_711.getType() != SecretType.FAIRY_SOUL) {
                                    holograms_711.setFound(true);
                                    this.method9(holograms_711, holograms34.method1(holograms_711.getPos()), RoomSecretEvent.Data.Type.ROOM_COMPLETED);
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

   private void method8(EventWorldChange data31) {
      this.field12 = null;
   }

   private void method9(RoomSecret holograms_71, Vec3iBridge horsestats202, RoomSecretEvent.Data.Type type3) {
      LunarEventBus.method29().method12(RoomSecretEvent.Data.class, () -> new RoomSecretEvent.Data(holograms_71, horsestats202, type3));
   }

   private void method10(RoomSecret holograms_71, Vec3iBridge horsestats202) {
      LunarEventBus.method29().method12(RoomSecretEvent.RoomSecretCollectedEvent.class, () -> new RoomSecretEvent.RoomSecretCollectedEvent(holograms_71, horsestats202));
   }
}
