package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationAnchor;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import com.moonsworth.lunar.client.replay.timeline.ImageSegment;
import com.moonsworth.lunar.client.replay.timeline.TextSegment;
import com.moonsworth.lunar.client.replay.timeline.RewindIterator;
import com.moonsworth.lunar.client.replay.timeline.AudioSegment;
import com.moonsworth.lunar.client.replay.timeline.GameplaySegment;
import com.moonsworth.lunar.client.replay.timeline.TimelineSegment;
import com.moonsworth.lunar.client.replay.timeline.EffectSegment;
import com.moonsworth.lunar.client.replay.project.RewindPaths;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui4;
import com.moonsworth.lunar.client.replay.timeline.EffectTrack;
import com.moonsworth.lunar.client.replay.timeline.SoundTrack;
import com.moonsworth.lunar.client.replay.timeline.GameplayTrack;
import com.moonsworth.lunar.client.replay.timeline.Track;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.MediaPool;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.replay.project.RewindFileReader;
import com.moonsworth.lunar.client.replay.timeline.PropertyGroup;
import com.moonsworth.lunar.client.replay.audio.MediaAudioStream;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.replay.gui.TimelineBridgePayloads;
import com.moonsworth.lunar.client.replay.gui.SelectedKeyframe;
import com.moonsworth.lunar.client.replay.export.MediaExporterJsApi;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import org.apache.commons.lang3.Range;

public class RewindTimelineBridge extends RewindEditorContext implements DriverGuiExtension, Extension {
   public RewindTimelineBridge() {
   }

   public GuiIterator getProvider() {
      return !getRewind().method19() ? null : getRewindHandlers().method29();
   }

   public static boolean canPropagateEdit(UUID uuid0) {
      boolean flag1 = uuid0 != null && getSelectedLayer() != null && uuid0.equals(getSelectedLayer().getId());
      return flag1 && isLinkSelectionEnabled() || !flag1 && !LcuiScreen.method124();
   }

   @CallbackJS("cut")
   public static void cut(String text0) {
      ReplayTimeline highlight_31 = getTimeline();
      if (highlight_31 != null) {
         UndoRedoManager nameplate22 = getProject().method40();
         int number3 = highlight_31.method15();
         if (text0.isBlank()) {
            highlight_31.method5(nameplate22, number3);
         } else {
            UUID uuid4 = UUID.fromString(text0);
            nameplate22.method1();
            Track gui_25 = findTrackOfLayer(uuid4);
            HashSet set6 = new HashSet();
            if (gui_25 != null) {
               Set set7 = collectLinkedLayers(uuid4);
               gui_25.method5().method5(number3);
               set6.add(gui_25.getId());
               if (canPropagateEdit(uuid4)) {
                  for (UUID uuid9 : set7) {
                     Track gui_210 = findTrackOfLayer(uuid9);
                     if (gui_210 != null && !set6.contains(gui_210.getId())) {
                        gui_210.method5().method5(number3);
                        set6.add(gui_210.getId());
                     }
                  }
               }
            }

            nameplate22.endBatch();
         }

         refreshTimeline();
      }
   }

   @CallbackJS("addTrack")
   public static void addTrack(String text0) {
      ReplayTimeline highlight_31 = getTimeline();
      if (highlight_31 != null) {
         UndoRedoManager nameplate22 = getProject().method40();
         switch (text0) {
            case "effect":
               EffectTrack guiimpl5 = new EffectTrack(nameplate22, highlight_31.method1());
               highlight_31.method11().method2().add(guiimpl5);
               break;
            case "gameplay":
               GameplayTrack guiimpl36 = new GameplayTrack(nameplate22, highlight_31.method1());
               highlight_31.method11().method3().add(guiimpl36);
               break;
            case "audio":
               SoundTrack guiimpl27 = new SoundTrack(nameplate22, highlight_31.method1());
               highlight_31.method11().method4().add(guiimpl27);
         }

         refreshTimeline();
      }
   }

   @CallbackJS("addLayer")
   public static void addLayer(UUID uuid0, String text1) {
      ReplayTimeline highlight_32 = getTimeline();
      if (highlight_32 != null) {
         UndoRedoManager nameplate23 = getProject().method40();
         nameplate23.method1();

         try {
            MediaPool highlight_44 = getProject().method43().method5();
            Track gui_25 = findTrack(uuid0);
            RewindIterator rewinditerator6 = null;
            long number7 = 10000L;
            switch (text1) {
               case "text":
                  rewinditerator6 = new TextSegment(nameplate23);
                  break;
               case "effect":
                  rewinditerator6 = new EffectSegment(nameplate23);
                  break;
               case "image":
                  rewinditerator6 = new ImageSegment(nameplate23);
                  File file18 = Gui4.method7(null, getProject().method32(), null, MediaPool.field3);
                  if (file18 == null) {
                     nameplate23.method2();
                     return;
                  }

                  UUID uuid20 = highlight_44.method2(file18);
                  ((ImageSegment)rewinditerator6).method5(uuid20);
                  break;
               case "audio":
                  rewinditerator6 = new AudioSegment(nameplate23);
                  File file17 = Gui4.method7(null, getProject().method32(), null, MediaPool.field1);
                  if (file17 == null) {
                     nameplate23.method2();
                     return;
                  }

                  UUID uuid19 = highlight_44.method2(file17);
                  MediaAudioStream rewindhandlers2impl13 = new MediaAudioStream(
                     getProject().method32(),
                     "media://" + uuid19,
                     getProject().method42(),
                     highlight_44,
                     getProject().method44()
                  );
                  rewindhandlers2impl13.play();
                  ((AudioSegment)rewinditerator6).method9(rewindhandlers2impl13);
                  number7 = rewindhandlers2impl13.getDuration();
                  break;
               case "gameplay":
                  rewinditerator6 = new GameplaySegment(getProject().method41(), nameplate23);
                  File file9 = Gui4.method7(null, RewindPaths.field8, null, MediaPool.field2);
                  if (file9 == null) {
                     nameplate23.method2();
                     return;
                  }

                  UUID uuid10 = highlight_44.method2(file9);
                  RewindFileReader rewind314 = getProject().method43().method6().method4(uuid10);
                  int number15 = rewind314.method13().method5();
                  if (Ref.method3().bridge$getProtocolVersion() != number15) {
                     Ref.method4()
                        .method69()
                        .method6(NotificationType.ERROR, "Rewind", "The rewind file was created in a different version of Minecraft.")
                        .method9(NotificationAnchor.BOTTOM_RIGHT);
                     nameplate23.method2();
                     return;
                  }

                  ((GameplaySegment)rewinditerator6).method13(rewind314.method13().getId());
                  number7 = rewind314.method13().method1();
            }

            if (rewinditerator6 == null) {
               nameplate23.method2();
               return;
            }

            Range range21 = Range.between(highlight_32.method15(), highlight_32.method15() + (int)(number7 / highlight_32.method9()));
            gui_25.method5().method1(range21, rewinditerator6);
            rewinditerator6.method1(range21, getProject().method38(), nameplate23);
            getProject().method45().method6(range21, rewinditerator6, highlight_32.method9());
            nameplate23.endBatch();
         } catch (IOException exception16) {
            nameplate23.method2();
         }

         refreshMediaExporter();
         refreshTimeline();
      }
   }

   @CallbackJS("selectLayer")
   public static void method7(UUID uuid0) {
      Set set1 = getAdditionalSelectedLayers();

      for (Track gui_23 : getProject().method37().method11()) {
         for (Range range5 : gui_23.method5().method11().keySet()) {
            RewindIterator rewinditerator6 = (RewindIterator)gui_23.method5().method11().get(range5);
            if (rewinditerator6.getId().equals(uuid0)) {
               if ((LcuiScreen.isShiftKeyDown() || isMultiSelectEnabled()) && getSelectedLayer() != null) {
                  if (getSelectedLayer() != rewinditerator6) {
                     if (set1.contains(rewinditerator6)) {
                        set1.remove(rewinditerator6);
                     } else {
                        set1.add(rewinditerator6);
                     }
                  }
               } else {
                  method30(rewinditerator6);
                  RewindEditorContext.setLinkSelectionEnabled(!LcuiScreen.method124());
               }

               method30(null);
               break;
            }
         }
      }

      refreshProperties();
      refreshTimeline();
   }

   @CallbackJS("selectLayers")
   public static void method7(UUID uuid0, UUID[] items1) {
      ReplayTimeline highlight_32 = getTimeline();
      if (highlight_32 != null) {
         Entry entry3 = highlight_32.method1().get(uuid0);
         if (entry3 != null) {
            method30((RewindIterator)entry3.getValue());
            RewindEditorContext.setLinkSelectionEnabled(!LcuiScreen.method124());
            method30(null);
            if (items1 != null) {
               Set set4 = getAdditionalSelectedLayers();

               for (UUID uuid8 : items1) {
                  if (uuid8 != null && !uuid8.equals(uuid0)) {
                     Entry entry9 = highlight_32.method1().get(uuid8);
                     if (entry9 != null) {
                        set4.add((RewindIterator)entry9.getValue());
                     }
                  }
               }
            }

            refreshProperties();
            refreshTimeline();
         }
      }
   }

   @CallbackJS("unselectLayer")
   public static void unselectLayer() {
      method30(null);
      refreshProperties();
      refreshTimeline();
   }

   @CallbackJS("moveLayer")
   public static void moveLayer(UUID uuid0, UUID uuid1, int number2) {
      ReplayTimeline highlight_33 = getTimeline();
      if (highlight_33 != null) {
         UndoRedoManager nameplate24 = getProject().method40();
         nameplate24.method1();
         RewindIterator rewinditerator5 = null;

         for (Track gui_27 : highlight_33.method11()) {
            for (Range range9 : gui_27.method5().method11().keySet()) {
               RewindIterator rewinditerator10 = (RewindIterator)gui_27.method5().method11().get(range9);
               if (rewinditerator10.getId().equals(uuid1)) {
                  rewinditerator5 = rewinditerator10;
                  if (uuid0 == null) {
                     uuid0 = gui_27.getId();
                  }
               }
            }
         }

         if (rewinditerator5 != null) {
            applyLayerMove(uuid0, uuid1, number2, canPropagateEdit(uuid1));
         }

         int number11 = Bridge.method20().getY();
         if (Bridge.getMinecraftVersion().method21()) {
            number11 = Ref.method3().bridge$logicalHeight() - number11;
         }

         if (rewinditerator5 == null && (uuid0 != null || number11 > Ref.method3().bridge$logicalHeight() / 1.5)) {
            MediaExporterJsApi.openMedia(uuid1, uuid0, Math.max(0, number2));
            refreshMediaExporter();
         }

         nameplate24.endBatch();
         refreshTimeline();
      }
   }

   private static void applyLayerMove(UUID uuid0, UUID uuid1, int number2, boolean flag3) {
      ReplayTimeline highlight_34 = getTimeline();
      if (highlight_34 != null) {
         Entry entry5 = highlight_34.method1().get(uuid1);
         if (entry5 != null) {
            Range range6 = (Range)entry5.getKey();
            RewindIterator rewinditerator7 = (RewindIterator)entry5.getValue();
            int number8 = number2 - (Integer)range6.getMinimum();
            if (flag3) {
               ArrayList list9 = new ArrayList();

               for (UUID uuid11 : collectLinkedLayers(uuid1)) {
                  Entry entry12 = highlight_34.method1().get(uuid11);
                  if (entry12 != null) {
                     list9.add(entry12);
                  }
               }

               list9.sort(Comparator.comparingInt(arg1x -> (int)(Math.signum(-number8) * ((Integer)((Range)arg1x.getKey()).getMinimum()).intValue())));

               for (Entry entry21 : list9) {
                  RewindIterator rewinditerator23 = (RewindIterator)entry21.getValue();
                  Track gui_213 = null;

                  for (Track gui_215 : getProject().method37().method11()) {
                     if (gui_215.method5().method11().containsValue(entry21.getValue())) {
                        gui_213 = gui_215;
                        break;
                     }
                  }

                  if (gui_213 != null) {
                     applyLayerMove(rewinditerator23.getId().equals(uuid1) ? uuid0 : gui_213.getId(), rewinditerator23.getId(), (Integer)((Range)entry21.getKey()).getMinimum() + number8, false);
                  }
               }
            } else {
               for (Track gui_219 : getProject().method37().method11()) {
                  RewindIterator rewinditerator22 = (RewindIterator)gui_219.method5().method11().get(range6);
                  if (rewinditerator22 != null && rewinditerator22.getId().equals(uuid1)) {
                     gui_219.method5().method8(range6);
                  }
               }

               Range range17 = Range.between(number2, number2 + ((Integer)range6.getMaximum() - (Integer)range6.getMinimum()));
               Track gui_220 = findTrack(uuid0);
               gui_220.method5().method1(range17, rewinditerator7);
            }
         }
      }
   }

   @CallbackJS("moveLayers")
   public static void moveLayers(TimelineBridgePayloads.LayerMove[] items0) {
      ReplayTimeline highlight_31 = getTimeline();
      if (highlight_31 != null && items0 != null && items0.length != 0) {
         ArrayList list2 = new ArrayList();

         for (TimelineBridgePayloads.LayerMove data36 : items0) {
            if (data36 != null && data36.field1 != null && data36.field2 != null && highlight_31.method1().get(data36.field1) != null) {
               list2.add(data36);
            }
         }

         if (!list2.isEmpty()) {
            list2.sort(Comparator.comparingInt(arg1x -> {
               Entry entry2x = highlight_31.method1().get(arg1x.field1);
               int number3 = (Integer)((Range)entry2x.getKey()).getMinimum();
               int number4 = arg1x.frame - number3;
               return (int)(Math.signum(-number4) * number3);
            }));
            UndoRedoManager nameplate27 = getProject().method40();
            nameplate27.method1();

            for (TimelineBridgePayloads.LayerMove data39 : list2) {
               applyLayerMove(data39.field2, data39.field1, Math.max(0, data39.frame), false);
            }

            nameplate27.endBatch();
            refreshTimeline();
         }
      }
   }

   @CallbackJS("trimLayer")
   public static void trimLayer(UUID uuid0, int number1, int number2) {
      ReplayTimeline highlight_33 = getTimeline();
      if (highlight_33 != null) {
         Set set4;
         if (canPropagateEdit(uuid0)) {
            set4 = collectLinkedLayers(uuid0);
         } else {
            set4 = new HashSet();
            set4.add(uuid0);
            set4.addAll(getAdditionalSelectedLayers().stream().map(RewindIterator::getId).toList());
         }

         Entry entry5 = highlight_33.method1().get(uuid0);
         if (entry5 != null) {
            Range range6 = (Range)entry5.getKey();
            UndoRedoManager nameplate27 = getProject().method40();
            nameplate27.method1();

            for (UUID uuid9 : set4) {
               Entry entry10 = highlight_33.method1().get(uuid9);
               if (entry10 != null) {
                  Range range11 = (Range)entry10.getKey();
                  RewindIterator rewinditerator12 = (RewindIterator)entry10.getValue();
                  if (Objects.equals(range11.getMinimum(), range6.getMinimum()) && Objects.equals(range11.getMaximum(), range6.getMaximum())) {
                     Track gui_213 = null;

                     for (Track gui_215 : getProject().method37().method11()) {
                        if (gui_215.method5().method11().containsValue(rewinditerator12)) {
                           gui_213 = gui_215;
                           break;
                        }
                     }

                     if (gui_213 != null) {
                        Range range23 = Range.between(number1, number2);
                        if (!range23.equals(range11)) {
                           gui_213.method5().method8(range11);
                           if ((Integer)range23.getMaximum() - (Integer)range23.getMinimum() > 0) {
                              gui_213.method5().method1(range23, rewinditerator12);
                              if (!Objects.equals(range23.getMinimum(), range11.getMinimum())) {
                                 int number24 = (Integer)range23.getMinimum() - (Integer)range11.getMinimum();

                                 for (PropertyGroup fishing2iterator17 : rewinditerator12.method18().values()) {
                                    for (KeyframeProperty fishing2loader19 : fishing2iterator17.method12().values()) {
                                       HashMap map20 = new HashMap();

                                       for (Object obj22 : new HashMap(fishing2loader19.method27()).keySet()) {
                                          if ((Integer)obj22 != Integer.MIN_VALUE) {
                                             map20.put((Integer)obj22 - number24, (KeyframeProperty.Keyframe)fishing2loader19.method27().remove(obj22));
                                          }
                                       }

                                       for (Entry entry31 : map20.entrySet()) {
                                          fishing2loader19.method27().put(entry31.getKey(), entry31.getValue());
                                       }
                                    }
                                 }
                              }

                              int number25 = rewinditerator12.method19();
                              int number26 = (int)(((Integer)range23.getMaximum() - (Integer)range23.getMinimum()) * rewinditerator12.method7());
                              nameplate27.method4(() -> rewinditerator12.method25(number25), () -> rewinditerator12.method25(number26));
                              rewinditerator12.method25(number26);
                              if (rewinditerator12 instanceof TimelineSegment rewinditerator2_227) {
                                 int number28 = rewinditerator2_227.method8();
                                 int number29 = number1 - (Integer)range11.getMinimum();
                                 nameplate27.method4(() -> rewinditerator2_227.method9(number28), () -> rewinditerator2_227.method9(number28 + number29));
                                 rewinditerator2_227.method9(number28 + number29);
                              }
                           }
                        }
                     }
                  }
               }
            }

            nameplate27.endBatch();
            refreshTimeline();
         }
      }
   }

   @CallbackJS("setShowKeyframes")
   public static void setShowKeyframes(int number0, boolean flag1) {
      Track gui_22 = getTrackByReversedIndex(number0);
      if (gui_22 != null) {
         gui_22.method7(flag1);
         refreshTimeline();
      }
   }

   @CallbackJS("setTrackEnabled")
   public static void setTrackEnabled(int number0, boolean flag1) {
      Track gui_22 = getTrackByReversedIndex(number0);
      if (gui_22 != null) {
         gui_22.setEnabled(flag1);
         refreshTimeline();
      }
   }

   @CallbackJS("deleteTrack")
   public static void getProvider(int number0) {
      Track gui_21 = getTrackByReversedIndex(number0);
      if (gui_21 != null) {
         ReplayTimeline highlight_32 = getTimeline();
         if (highlight_32 != null) {
            Set set3 = highlight_32.method11().method2();
            if (set3.contains(gui_21)) {
               set3.remove(gui_21);
            }

            Set set4 = highlight_32.method11().method3();
            if (set4.contains(gui_21)) {
               set4.remove(gui_21);
            }

            Set set5 = highlight_32.method11().method4();
            if (set5.contains(gui_21)) {
               set5.remove(gui_21);
            }

            refreshTimeline();
         }
      }
   }

   @CallbackJS("selectKeyframe")
   public static void selectKeyframe(UUID uuid0, String text1, String text2, int number3, boolean flag4) {
      RewindIterator rewinditerator5 = null;
      Range range6 = null;

      for (Track gui_28 : getProject().method37().method11()) {
         for (Range range10 : gui_28.method5().method11().keySet()) {
            RewindIterator rewinditerator11 = (RewindIterator)gui_28.method5().method11().get(range10);
            if (rewinditerator11.getId().equals(uuid0)) {
               rewinditerator5 = rewinditerator11;
               range6 = range10;
               break;
            }
         }
      }

      if (rewinditerator5 != null) {
         PropertyGroup fishing2iterator12 = method30(rewinditerator5.method18().values(), text1.split("/"), 0);
         KeyframeProperty fishing2loader13 = method30(rewinditerator5, text1, text2);
         if (fishing2loader13 != null) {
            ReplayTimeline highlight_314 = getTimeline();
            if (highlight_314 != null) {
               method30(new SelectedKeyframe(fishing2iterator12, fishing2loader13, number3));
               method30(null);
               if (flag4) {
                  highlight_314.method8(number3 + (Integer)range6.getMinimum());
               }

               refreshProperties();
               refreshTimeline();
            }
         }
      }
   }

   @CallbackJS("selectKeyframes")
   public static void selectKeyframes(TimelineBridgePayloads.KeyframeTarget[] items0) {
      if (items0 != null && items0.length != 0) {
         ReplayTimeline highlight_31 = getTimeline();
         if (highlight_31 != null) {
            SelectedKeyframe coordinates42 = null;
            LinkedHashSet set3 = new LinkedHashSet();

            for (TimelineBridgePayloads.KeyframeTarget data7 : items0) {
               if (data7 != null && data7.field1 != null && data7.field2 != null && data7.field3 != null) {
                  Entry entry8 = highlight_31.method1().get(data7.field1);
                  if (entry8 != null) {
                     RewindIterator rewinditerator9 = (RewindIterator)entry8.getValue();
                     PropertyGroup fishing2iterator10 = method30(rewinditerator9.method18().values(), data7.field2.split("/"), 0);
                     KeyframeProperty fishing2loader11 = method30(rewinditerator9, data7.field2, data7.field3);
                     if (fishing2iterator10 != null && fishing2loader11 != null) {
                        SelectedKeyframe coordinates412 = new SelectedKeyframe(fishing2iterator10, fishing2loader11, data7.frame);
                        if (coordinates42 == null) {
                           coordinates42 = coordinates412;
                           highlight_31.method8(coordinates42.method3() + (Integer)((Range)entry8.getKey()).getMinimum());
                        } else {
                           set3.add(coordinates412);
                        }
                     }
                  }
               }
            }

            if (coordinates42 != null) {
               method30(null);
               method30(coordinates42);
               getAdditionalSelectedKeyframes().addAll(set3);
               refreshProperties();
               refreshTimeline();
            }
         }
      }
   }

   @CallbackJS("unselectKeyframe")
   public static void unselectKeyframe() {
      method30(null);
      refreshProperties();
      refreshTimeline();
   }

   @CallbackJS("moveKeyframe")
   public static void moveKeyframe(int number0) {
      if (getSelectedKeyframe() != null) {
         UndoRedoManager nameplate21 = getProject().method40();
         KeyframeProperty fishing2loader2 = getSelectedKeyframe().method2();
         int index3 = getSelectedKeyframe().method3();
         nameplate21.method1();
         KeyframeProperty.Keyframe data4 = getSelectedKeyframe().method2().method27().remove(index3);
         if (data4 != null) {
            fishing2loader2.method27().put(number0, data4);
            method30(new SelectedKeyframe(getSelectedKeyframe().method1(), fishing2loader2, number0));
         }

         nameplate21.endBatch();
         refreshTimeline();
         refreshProperties();
         getSelectedKeyframe().method2().method8();
      }
   }

   @CallbackJS("moveKeyframes")
   public static void unselectLayer(TimelineBridgePayloads.KeyframeMove[] items0) {
      ReplayTimeline highlight_31 = getTimeline();
      if (highlight_31 != null && items0 != null && items0.length != 0) {
         UndoRedoManager nameplate22 = getProject().method40();
         nameplate22.method1();
         ArrayList list3 = new ArrayList();
         HashSet set4 = new HashSet();
         HashMap map5 = new HashMap();

         for (TimelineBridgePayloads.KeyframeMove data29 : items0) {
            if (data29 != null && data29.field1 != null && data29.field3 != null && data29.field4 != null) {
               Entry entry10 = highlight_31.method1().get(data29.field1);
               if (entry10 != null) {
                  KeyframeProperty fishing2loader11 = method30((RewindIterator)entry10.getValue(), data29.field3, data29.field4);
                  if (fishing2loader11 != null) {
                     KeyframeProperty.Keyframe data12 = (KeyframeProperty.Keyframe)fishing2loader11.method27().remove(data29.field5);
                     if (data12 != null) {
                        list3.add(new TimelineBridgePayloads.PendingKeyframeMove(fishing2loader11, data29.field6, data12));
                        set4.add(fishing2loader11);
                        map5.computeIfAbsent(fishing2loader11, arg0x -> new HashMap<>()).put(data29.field5, data29.field6);
                     }
                  }
               }
            }
         }

         for (TimelineBridgePayloads.PendingKeyframeMove data415 : list3) {
            data415.method1().method27().put(data415.method2(), data415.method3());
         }

         nameplate22.endBatch();

         for (KeyframeProperty fishing2loader16 : set4) {
            fishing2loader16.method8();
         }

         applyFrameRemap(map5);
         refreshTimeline();
         refreshProperties();
      }
   }

   @CallbackJS("linkLayers")
   public static void unselectKeyframe(boolean flag0) {
      if (getSelectedLayer() != null) {
         UndoRedoManager nameplate21 = getProject().method40();
         ReplayTimeline highlight_32 = getTimeline();
         if (highlight_32 != null) {
            Set set3 = collectLinkedLayers(null);
            if (flag0) {
               highlight_32.method11().method6().method8(nameplate21, set3);
            } else {
               highlight_32.method11().method6().method10(nameplate21, set3);
            }

            getAdditionalSelectedLayers().clear();
            refreshTimeline();
            refreshProperties();
         }
      }
   }

   @CallbackJS("startLayersSelection")
   public static void startLayersSelection() {
      setMultiSelectEnabled(true);
   }

   @CallbackJS("stopLayersSelection")
   public static void stopLayersSelection() {
      setMultiSelectEnabled(false);
      refreshProperties();
   }
}
