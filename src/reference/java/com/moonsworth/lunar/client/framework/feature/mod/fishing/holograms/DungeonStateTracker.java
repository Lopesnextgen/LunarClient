package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.PlayerInfoBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.ItemMapBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.NetHandlerPlayClientBridge;
import com.moonsworth.lunar.bridge.world.MapDataBridge;
import com.moonsworth.lunar.bridge.world.MapDecorationBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomSecretsAction;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.DungeonPlayerTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.WorldPosition;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.DungeonRoomTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.RoomStateHistory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.lotusfish.PlayerListEntryParser;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.DungeonFloor;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonMapListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.ActionBarStatsListener;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.DungeonMapResetEvent;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.DungeonRoomDetectedEvent;
import com.moonsworth.lunar.client.framework.listener.LocalPlayerNameListener;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.framework.Ref;
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

public class DungeonStateTracker {
   private final DungeonMapListener field1;
   private final ActionBarStatsListener field2;
   private final LocalPlayerNameListener field3;
   private static final Pattern field4 = Pattern.compile("(?<puzzle>.+): \\[✖].*");
   private static final Pattern field5 = Pattern.compile("(?<puzzle>.+): \\[✔]");
   private static final Pattern field6 = Pattern.compile("(?<puzzle>.+): \\[✦]");
   public static final Vector2i field7 = new Vector2i(-200, -200);
   public static final int field8 = 32;
   private final DungeonSync field9 = new DungeonSync(this);
   private final List<DungeonPlayerTracker> field10 = new ArrayList<>();
   private final Map<String, DungeonRoomTracker> field11 = new HashMap<>();
   private final Map<String, RoomStateHistory> field12 = new HashMap<>();
   private final Set<RoomStateHistory> field13 = new HashSet<>();
   private final Set<DungeonRoomTracker> field14 = new HashSet<>();
   private final List<DungeonRoomTracker> field15 = new ArrayList<>();
   private final List<RoomStateHistory> field16 = new ArrayList<>();
   private final Map<String, DungeonPlayerTracker> field17 = new HashMap<>();
   private final List<RoomPuzzle> field18 = new ArrayList<>();
   private final DungeonFloor field19;
   private final boolean field20;
   private final double field21;
   public int field22 = 15;
   public int field23 = 20;
   public Vector2i field24 = null;
   private boolean field25 = false;
   private com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonMapItem field26;
   private DungeonRoomTracker field27;
   private int field28;
   private boolean field29;
   private boolean field30;

   public DungeonStateTracker(DungeonFloor dungeonFloor, boolean flag, DungeonMapListener guirewindhandlershandler2_23, ActionBarStatsListener listener, LocalPlayerNameListener listener2) {
      this.field1 = guirewindhandlershandler2_23;
      this.field2 = listener;
      this.field3 = listener2;
      this.field21 = Ref.method3().bridge$getSystemTime();
      this.field19 = dungeonFloor;
      this.field20 = flag;
   }

   public void method1() {
      if (this.field20) {
         this.method6();
      }
   }

   public void method2(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonMapItem holograms21) {
      if (this.field20 && !this.field25) {
         com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonMapItem holograms22 = this.method8();
         if (holograms22 != null && holograms22.method2() == holograms21.method2()) {
            this.field26 = holograms21;
            this.method9(holograms21);
         }
      }
   }

   public void method3() {
      if (this.field20) {
         DungeonPlayerTracker holograms4updater1 = this.method29();
         DungeonRoomTracker holograms4iterator2 = holograms4updater1.method7();

         for (DungeonPlayerTracker holograms4updater4 : this.field10) {
            holograms4updater4.tick();
         }

         if (holograms4iterator2 == null && this.field14.isEmpty() && !this.field10.isEmpty() && holograms4updater1.method27().isPresent()) {
            Bridge6_10 bridge6_106 = holograms4updater1.method27().get();
            WorldPosition nameplate49 = new WorldPosition(bridge6_106.bridge$getPosX(), bridge6_106.bridge$getPosZ(), this);
            RoomTemplateDetector.method13(this, WorldPosition.method1(nameplate49.method8(), nameplate49.method9(), this));
         }

         if (holograms4iterator2 != null && holograms4iterator2.method23().isEmpty()) {
            RoomTemplateDetector.method1(holograms4iterator2, false);
         }

         this.method4();
         if (holograms4iterator2 != null && !holograms4updater1.method18() && this.field1.method10()) {
            int number7 = this.field1.method13();
            int number10 = this.field1.method15();
            if (number7 != -1 && (holograms4iterator2.method30().method1() != number7 || holograms4iterator2.method30().method7() != number10)) {
               holograms4iterator2.method13(number7);
               holograms4iterator2.method15(number10);
               if (!holograms4iterator2.method28().isEmpty()) {
                  WorldPosition nameplate45 = holograms4iterator2.method28().get(0);
                  this.method45().method2(new RoomSecretsAction(new Vector2i(nameplate45.method8(), nameplate45.method9()), number7, number10));
               }
            }
         }

         DungeonRoomTracker holograms4iterator8 = holograms4iterator2;
         if (holograms4iterator8 != null && holograms4iterator8.method23().isEmpty()) {
            holograms4iterator8 = null;
         }

         if (holograms4iterator8 != this.field27) {
            if (this.field20) {
               LunarEventBus.method29().method12(DungeonMapResetEvent.class, DungeonMapResetEvent::new);
               if (holograms4iterator8 != null) {
                  LunarEventBus.method29().method12(DungeonRoomDetectedEvent.class, DungeonRoomDetectedEvent::new);
               }
            }

            this.field27 = holograms4iterator8;
         }
      }
   }

   private void method4() {
      if (!Ref.method4().method40().method85().method17(arg0 -> arg0.method41().method18() / 50L > 20L)) {
         NetHandlerPlayClientBridge bridgeextension_71 = Ref.method9();
         if (bridgeextension_71 != null) {
            int index2 = 0;
            int index3 = 0;
            int index4 = 0;

            for (PlayerInfoBridge bridge2_336 : bridgeextension_71.bridge$getSortedPlayerInfoMap()) {
               if (bridge2_336.bridge$getDisplayName() != null) {
                  String text7 = TextBridge.getTextContent(bridge2_336.bridge$getDisplayName());
                  Matcher matcher8 = field4.matcher(text7.trim());
                  int number9 = index2 + index3 + index4;
                  if (matcher8.matches()) {
                     this.method5(
                        number9, matcher8.group("puzzle"), com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.OPENED
                     );
                     index2++;
                  } else {
                     Matcher matcher10 = field5.matcher(text7.trim());
                     if (matcher10.matches()) {
                        this.method5(
                           number9, matcher10.group("puzzle"), com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED
                        );
                        index3++;
                     } else {
                        Matcher matcher11 = field6.matcher(text7.trim());
                        if (matcher11.matches()) {
                           this.method5(
                              number9, matcher11.group("puzzle"), com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.OPENED
                           );
                           index4++;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void method5(int index1, String text2, com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState state) {
      if (this.field18.size() <= index1) {
         this.field18.add(new RoomPuzzle(this, text2, state));
      } else {
         RoomPuzzle holograms44 = this.field18.get(index1);
         holograms44.method2(text2, state);
      }
   }

   private void method6() {
      NetHandlerPlayClientBridge bridgeextension_71 = Ref.method9();
      if (bridgeextension_71 != null) {
         this.field10.clear();
         String text2 = this.field3.method5();
         PlayerListEntryParser.Data data3 = null;
         PlayerInfoBridge bridge2_334 = null;
         int number5 = 0;
         DungeonPlayerTracker holograms4updater6 = null;
         boolean flag7 = false;
         boolean flag8 = false;

         for (PlayerInfoBridge bridge2_3310 : bridgeextension_71.bridge$getSortedPlayerInfoMap()) {
            if (bridge2_3310.bridge$getDisplayName() != null) {
               String text11 = TextBridge.getTextContent(bridge2_3310.bridge$getDisplayName());
               PlayerListEntryParser.Data data12 = PlayerListEntryParser.method1(text11);
               if (data12 == null) {
                  if (flag7) {
                     flag8 = true;
                     flag7 = false;
                  } else if (flag8) {
                     if (text11.trim().startsWith("Revive Stones: ")) {
                        String[] items13 = text11.split(": ");
                        if (items13.length >= 2) {
                           try {
                              int number14 = Integer.parseInt(items13[1]);
                              if (holograms4updater6 == null) {
                                 number5 = number14;
                              } else {
                                 holograms4updater6.method34(number14);
                              }
                           } catch (NumberFormatException numberformatexception15) {
                           }
                        }
                     }

                     flag8 = false;
                  }
               } else {
                  flag7 = true;
                  if (data12.playerName().equals(text2)) {
                     data3 = data12;
                     bridge2_334 = bridge2_3310;
                     holograms4updater6 = null;
                  } else {
                     DungeonPlayerTracker holograms4updater17 = this.method7(bridge2_3310);
                     this.field10.add(holograms4updater17);
                     holograms4updater17.method9(data12.playerName(), data12.method3(), data12.method4());
                     holograms4updater17.method31(bridge2_3310);
                     holograms4updater6 = holograms4updater17;
                  }
               }
            }
         }

         if (data3 != null) {
            DungeonPlayerTracker holograms4updater16 = this.method7(bridge2_334);
            this.field10.add(holograms4updater16);
            holograms4updater16.method9(data3.playerName(), data3.method3(), data3.method4());
            holograms4updater16.method31(bridge2_334);
            holograms4updater16.method34(number5);
         }
      }
   }

   private DungeonPlayerTracker method7(PlayerInfoBridge bridge2_331) {
      return this.field17.computeIfAbsent(bridge2_331.bridge$getGameProfile().getName(), arg2 -> new DungeonPlayerTracker(bridge2_331, this));
   }

   private com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonMapItem method8() {
      if (Ref.method7() == null) {
         return null;
      }

      ItemStackBridge bridgeextension_41 = (ItemStackBridge)Ref.method7().bridge$getInventory().bridge$getMainInventory().get(8);
      if (bridgeextension_41 == null || bridgeextension_41.bridge$isEmpty() || bridgeextension_41.bridge$getItem() != Bridge.method28().method30() || !bridgeextension_41.bridge$hasDisplayName()) {
         return this.field26;
      }

      if (!bridgeextension_41.bridge$getDisplayName().contains("Magical Map")) {
         return null;
      }

      if (this.method48()) {
         return null;
      }

      if (bridgeextension_41.bridge$getItem() instanceof ItemMapBridge bridge5_42) {
         MapDataBridge itemcounter2_35 = bridge5_42.bridge$getMapData(bridgeextension_41, Ref.method8());
         Integer number4 = bridge5_42.bridge$getMapId(bridgeextension_41, Ref.method8());
         if (itemcounter2_35 != null && number4 != null) {
            this.field26 = new com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonMapItem(itemcounter2_35, number4);
            return this.field26;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private void method9(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonMapItem holograms21) {
      if (holograms21 != null) {
         this.method10(holograms21.method1().bridge$getMapDecorations());
         this.method11(holograms21);
         if (this.field24 != null) {
            this.method12(holograms21);
         }
      }
   }

   private void method10(Map<String, MapDecorationBridge> map) {
      if (this.field24 != null) {
         long number2 = Ref.method3().bridge$getSystemTime();
         int index4 = 0;

         for (int index5 = 0; index5 < map.size(); index5++) {
            MapDecorationBridge itemcounter_26 = (MapDecorationBridge)map.get("icon-" + index5);
            if (itemcounter_26 != null) {
               if (index4 >= this.field10.size()) {
                  return;
               }

               DungeonPlayerTracker holograms4updater7;
               for (holograms4updater7 = this.field10.get(index4); holograms4updater7.isDead(); holograms4updater7 = this.field10.get(index4)) {
                  if (++index4 >= this.field10.size()) {
                     return;
                  }
               }

               if (number2 - holograms4updater7.method15() < 1500L) {
                  index4++;
               } else {
                  double value8 = (itemcounter_26.bridge$getX() - this.field24.x() * 2 + 128) / 256.0 * 6.0 * 32.0 + field7.x();
                  double value10 = (itemcounter_26.bridge$getY() - this.field24.y() * 2 + 128) / 256.0 * 6.0 * 32.0 + field7.y();
                  double value12 = itemcounter_26.bridge$getRot() * 360 / 16.0 + 180.0;
                  holograms4updater7.method5(value12, 500L);
                  holograms4updater7.method30().method6(value8, 500L);
                  holograms4updater7.method30().method7(value10, 500L);
                  index4++;
               }
            }
         }
      }
   }

   private void method11(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonMapItem holograms21) {
      if (this.field24 == null) {
         Vector2i vector2i2 = null;
         int number3 = 0;
         byte[] items4 = holograms21.method1().bridge$getColors();

         for (byte index5 = 0; index5 < 128; index5 += 5) {
            for (byte index6 = 0; index6 < 128; index6 += 5) {
               if (items4[index5 + index6 * 128] == 30) {
                  int index7 = index5;
                  int index8 = index6;

                  while (items4[index7 - 1 + index8 * 128] == 30) {
                     index7--;
                  }

                  while (items4[index7 + (index8 - 1) * 128] == 30) {
                     index8--;
                  }

                  int index9 = 0;

                  while (items4[index7 + index9 + 1 + index8 * 128] == 30) {
                     index9++;
                  }

                  if (index9 != 0 && index9 > number3) {
                     number3 = index9;
                     int number10 = index9 * 4 / 3;
                     int number11 = index7 % number10;
                     int number12 = index8 % number10;
                     int number13 = this.field19.getNumber();
                     if (number13 == 0 || number13 == 1) {
                        number11 += number10;
                     }

                     if (number13 == 0) {
                        number12 += number10;
                     }

                     vector2i2 = new Vector2i(number11, number12);
                  }
               }
            }
         }

         if (vector2i2 != null) {
            this.field22 = number3;
            this.field23 = number3 * 4 / 3;
            this.field24 = vector2i2;
         }
      }
   }

   private void method12(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.DungeonMapItem holograms21) {
      if (this.field24.x() > 0 && this.field24.y() > 0) {
         byte[] items2 = holograms21.method1().bridge$getColors();

         for (int index3 = 0; index3 < 6; index3++) {
            for (int index4 = 0; index4 < 6; index4++) {
               int index5 = this.field24.x() + index3 * this.field23;
               int number6 = this.field24.y() + index4 * this.field23;
               if (index5 + this.field22 < 128 && number6 + this.field22 / 2 + 3 < 128) {
                  byte number7 = items2[index5 + number6 * 128];
                  MapRoomType hologramstype58 = MapColorCodes.field20.get(Integer.valueOf(number7));
                  if (hologramstype58 != null) {
                     DungeonRoomTracker holograms4iterator9 = this.method13(index3, index4);
                     boolean flag10 = holograms4iterator9 == null;
                     DungeonRoomTracker holograms4iterator11 = null;
                     byte number12 = items2[index5 + number6 * 128 - 128];
                     if (number12 != 0) {
                        holograms4iterator11 = this.method13(index3, index4 - 1);
                     }

                     byte number13 = items2[index5 + this.field22 + number6 * 128 + 1];
                     byte number14 = items2[index5 + this.field23 + number6 * 128 - 128];
                     if (number13 != 0 && number14 != 0 && holograms4iterator11 == null) {
                        holograms4iterator11 = this.method13(index3 + 1, index4 - 1);
                     }

                     byte number15 = items2[index5 - 1 + number6 * 128];
                     if (number15 != 0 && holograms4iterator11 == null) {
                        holograms4iterator11 = this.method13(index3 - 1, index4);
                     }

                     if (holograms4iterator9 == null && holograms4iterator11 == null) {
                        holograms4iterator9 = new DungeonRoomTracker(this, hologramstype58);
                     }

                     if (holograms4iterator9 != null) {
                        if (holograms4iterator9.method30().method6() != hologramstype58 && (holograms4iterator9.method30().method6() == MapRoomType.UNKNOWN || hologramstype58 != MapRoomType.UNKNOWN)) {
                           holograms4iterator9.method16(hologramstype58);
                        }

                        byte number16 = items2[index5 + this.field22 / 2 + (number6 + this.field22 / 2 + 3) * 128];
                        if (number16 != number7) {
                           com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState hologramstype217 = MapColorCodes.field21
                              .get(Integer.valueOf(number16));
                           if (hologramstype217 != null) {
                              holograms4iterator9.method10(hologramstype217);
                           }
                        }

                        if (holograms4iterator9.method30().method2() == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.ADJACENT
                           && number16 == number7) {
                           holograms4iterator9.method10(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.OPENED);
                        }
                     }

                     if (holograms4iterator11 != holograms4iterator9) {
                        if (holograms4iterator11 != null && holograms4iterator9 != null) {
                           this.field14.remove(holograms4iterator11);

                           for (WorldPosition nameplate433 : holograms4iterator11.method28()) {
                              holograms4iterator9.method5(nameplate433);
                           }
                        }

                        if (holograms4iterator9 == null) {
                           holograms4iterator9 = holograms4iterator11;
                        }

                        if (flag10) {
                           holograms4iterator9.method5(WorldPosition.method1(index3, index4, this));
                        }
                     }
                  }
               }
            }
         }

         for (int index18 = 0; index18 < 6; index18++) {
            for (int index19 = 0; index19 < 6; index19++) {
               int index20 = this.field24.x() + index18 * this.field23;
               int number21 = this.field24.y() + index19 * this.field23;
               if (index20 < 128 - this.field23 && number21 < 128 - this.field23) {
                  byte number22 = items2[index20 + 1 + this.field22 + (number21 + this.field22 / 2) * 128];
                  MapRoomType hologramstype523 = MapColorCodes.field20.get(Integer.valueOf(number22));
                  if (hologramstype523 != null) {
                     DungeonRoomTracker holograms4iterator24 = this.method13(index18, index19);
                     DungeonRoomTracker holograms4iterator26 = this.method13(index18 + 1, index19);
                     if (holograms4iterator24 != null && holograms4iterator26 != null && holograms4iterator24 != holograms4iterator26) {
                        RoomStateHistory rewindhandlers28 = this.method15(index18, index19, index18 + 1, index19);
                        if (rewindhandlers28 == null) {
                           rewindhandlers28 = new RoomStateHistory(this, hologramstype523, WorldPosition.method1(index18, index19, this), WorldPosition.method1(index18 + 1, index19, this));
                           this.method22(rewindhandlers28);
                        }

                        if (rewindhandlers28.method8() != hologramstype523) {
                           rewindhandlers28.method1(hologramstype523);
                        }
                     }
                  }

                  byte number25 = items2[index20 + this.field22 / 2 + (number21 + this.field22 + 1) * 128];
                  MapRoomType hologramstype527 = MapColorCodes.field20.get(Integer.valueOf(number25));
                  if (hologramstype527 != null) {
                     DungeonRoomTracker holograms4iterator29 = this.method13(index18, index19);
                     DungeonRoomTracker holograms4iterator30 = this.method13(index18, index19 + 1);
                     if (holograms4iterator29 != null && holograms4iterator30 != null && holograms4iterator29 != holograms4iterator30) {
                        RoomStateHistory rewindhandlers31 = this.method15(index18, index19, index18, index19 + 1);
                        if (rewindhandlers31 == null) {
                           rewindhandlers31 = new RoomStateHistory(this, hologramstype527, WorldPosition.method1(index18, index19, this), WorldPosition.method1(index18, index19 + 1, this));
                           this.method22(rewindhandlers31);
                        }

                        if (rewindhandlers31.method8() != hologramstype527) {
                           rewindhandlers31.method1(hologramstype527);
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

   public DungeonRoomTracker method13(int number1, int number2) {
      String text3 = number1 + "," + number2;
      return this.field11.get(text3);
   }

   public DungeonRoomTracker method14(WorldPosition nameplate41) {
      return this.method13(nameplate41.method8(), nameplate41.method9());
   }

   public RoomStateHistory method15(int number1, int number2, int number3, int number4) {
      String text5 = number1 + "," + number2 + "," + number3 + "," + number4;
      return this.field12.get(text5);
   }

   public RoomStateHistory method16(WorldPosition nameplate41, WorldPosition nameplate42) {
      return this.method15(nameplate41.method8(), nameplate41.method9(), nameplate42.method8(), nameplate42.method9());
   }

   public void method17(RoomStateHistory rewindhandlers1) {
      this.field13.add(rewindhandlers1);
   }

   public void method18(RoomStateHistory rewindhandlers1) {
      this.field13.remove(rewindhandlers1);
   }

   public Collection<RoomStateHistory> method19() {
      return this.field13;
   }

   public Collection<DungeonPlayerTracker> getPlayers() {
      return this.field10;
   }

   public void method20(DungeonRoomTracker dungeonRoomTracker, WorldPosition nameplate42) {
      String text3 = nameplate42.method8() + "," + nameplate42.method9();
      this.field11.put(text3, dungeonRoomTracker);
      this.field14.add(dungeonRoomTracker);
   }

   public void method21(WorldPosition nameplate41) {
      String text2 = nameplate41.method8() + "," + nameplate41.method9();
      this.field11.remove(text2);
   }

   public void method22(RoomStateHistory rewindhandlers1) {
      String text2 = rewindhandlers1.method6().method8() + "," + rewindhandlers1.method6().method9() + "," + rewindhandlers1.method7().method8() + "," + rewindhandlers1.method7().method9();
      this.field12.put(text2, rewindhandlers1);
      DungeonRoomTracker holograms4iterator3 = this.method14(rewindhandlers1.method6());
      if (holograms4iterator3 != null) {
         holograms4iterator3.method29().add(rewindhandlers1);
      }

      DungeonRoomTracker holograms4iterator4 = this.method14(rewindhandlers1.method7());
      if (holograms4iterator4 != null) {
         holograms4iterator4.method29().add(rewindhandlers1);
      }
   }

   public Collection<DungeonRoomTracker> method23() {
      return this.field14;
   }

   public Collection<RoomStateHistory> method24() {
      return this.field12.values();
   }

   public Collection<DungeonRoomTracker> method25() {
      return this.field15.isEmpty() ? this.method23() : this.field15;
   }

   public Collection<RoomStateHistory> method26() {
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
   public DungeonPlayerTracker method29() {
      if (this.field10.isEmpty()) {
         this.field10.add(new DungeonPlayerTracker(null, this));
      }

      return this.field10.get(this.field10.size() - 1);
   }

   public void method30() {
      if (IslandUtils.getIsland() == com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland.DUNGEON) {
         DungeonPlayerTracker holograms4updater1 = this.method29();
         holograms4updater1.method41(this.field2.method6());
         holograms4updater1.method43(this.field2.method5());
      }
   }

   public Optional<DungeonPlayerTracker> method31(String text) {
      return this.field10.stream().filter(arg1x -> arg1x.method20(false).equals(text)).findAny();
   }

   public Optional<DungeonPlayerTracker> getPlayer(UUID uuid1) {
      return this.field10.stream().filter(arg1x -> uuid1.equals(arg1x.getUuid())).findAny();
   }

   public void method32() {
      for (DungeonRoomTracker holograms4iterator2 : this.method23()) {
         if (holograms4iterator2.method30().method6() == MapRoomType.BLOOD) {
            holograms4iterator2.method10(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.CLEARED);
         }
      }
   }

   public void method33() {
      if (!this.field25) {
         for (DungeonPlayerTracker holograms4updater2 : this.getPlayers()) {
            holograms4updater2.method2(false);
         }
      }

      this.field25 = true;
   }

   public int method34() {
      return this.field19.getNumber();
   }

   public boolean method35(RoomPuzzle holograms41) {
      for (DungeonRoomTracker holograms4iterator3 : this.method23()) {
         if (holograms4iterator3.method30().method6() == MapRoomType.PUZZLE
            && holograms4iterator3.method30().method2() != com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.ADJACENT
            && !holograms4iterator3.method18()) {
            holograms4iterator3.method19(holograms41);
            return true;
         }
      }

      return false;
   }

   public void method36(long number1) {
      for (DungeonPlayerTracker holograms4updater4 : this.getPlayers()) {
         holograms4updater4.method21(number1);
      }

      if (number1 == 0L) {
         this.field15.clear();
         this.field16.clear();
      } else {
         if (this.field15.size() < this.field14.size()) {
            this.field15.clear();

            for (DungeonRoomTracker holograms4iterator10 : this.method23()) {
               DungeonRoomTracker holograms4iterator5 = new DungeonRoomTracker(this, MapRoomType.UNKNOWN);
               holograms4iterator5.method26(holograms4iterator10.getEvents());
               this.field15.add(holograms4iterator5);
            }
         }

         if (this.field16.size() < this.field12.size()) {
            this.field16.clear();

            for (RoomStateHistory rewindhandlers11 : this.method24()) {
               RoomStateHistory rewindhandlers14 = new RoomStateHistory(this, null, rewindhandlers11.method6(), rewindhandlers11.method7());
               rewindhandlers14.method3(rewindhandlers11.getEvents());
               this.field16.add(rewindhandlers14);
            }
         }

         for (DungeonRoomTracker holograms4iterator12 : this.field15) {
            holograms4iterator12.method21(number1);
         }

         for (RoomStateHistory rewindhandlers13 : this.field16) {
            rewindhandlers13.method4(number1);
         }
      }
   }

   public void method37() {
      this.field30 = true;

      for (DungeonPlayerTracker holograms4updater2 : this.getPlayers()) {
         holograms4updater2.method2(true);
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

   public DungeonRoomTracker method42() {
      return this.field14.stream().filter(arg0 -> arg0.method30().method6() == MapRoomType.SPAWN).findFirst().orElse(null);
   }

   public void method43() {
      this.field29 = true;
   }

   public void method44() {
      for (DungeonRoomTracker holograms4iterator2 : this.field14) {
         PuzzleType hologramstype83 = holograms4iterator2.method25();
         if (hologramstype83 != null && (hologramstype83 == PuzzleType.BLAZE_LOWER || hologramstype83 == PuzzleType.BLAZE_UPPER)) {
            if (holograms4iterator2.method30().method1() < holograms4iterator2.method14()) {
               holograms4iterator2.method10(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.CLEARED);
            } else {
               holograms4iterator2.method10(com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.RoomState.COMPLETED);
            }
         }
      }
   }

   @Generated
   public DungeonSync method45() {
      return this.field9;
   }

   @Generated
   public DungeonFloor method46() {
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
