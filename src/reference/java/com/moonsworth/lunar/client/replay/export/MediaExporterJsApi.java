package com.moonsworth.lunar.client.replay.export;

import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationAnchor;
import com.moonsworth.lunar.client.replay.timeline.ImageSegment;
import com.moonsworth.lunar.client.replay.timeline.AudioSegment;
import com.moonsworth.lunar.client.replay.project.RewindPaths;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui4;
import com.moonsworth.lunar.client.replay.timeline.EffectTrack;
import com.moonsworth.lunar.client.replay.timeline.SoundTrack;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.MediaPool;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.replay.project.RewindFileCache;
import com.moonsworth.lunar.client.replay.project.RewindFileReader;
import com.moonsworth.lunar.client.replay.audio.MediaAudioStream;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.replay.gui.LayerPropertiesContext;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;
import java.util.Map.Entry;
import org.apache.commons.lang3.Range;

public class MediaExporterJsApi extends RewindEditorContext implements DriverGuiExtension, Extension {
   public MediaExporterJsApi() {
   }

   public GuiIterator getProvider() {
      return !getRewind().method19() ? null : getRewindHandlers().method30();
   }

   @CallbackJS("onClick")
   public static void onClick(UUID uuid0) {
      ReplayTimeline highlight_31 = getProject().method37();
      if (highlight_31 != null) {
         openMedia(uuid0, null, highlight_31.method15());
      }
   }

   public static void openMedia(UUID uuid0, UUID uuid1, int number2) {
      ReplayTimeline highlight_33 = getProject().method37();
      if (highlight_33 != null) {
         if (!getProject().method43().method5().method8(uuid0)) {
            LayerPropertiesContext nameplate4 = ((ReplayContext)getRewindHandlers().method42().get()).method9();
            nameplate4.method2();
            nameplate4.cleanup();
            MediaPool highlight_45 = getProject().method43().method5();
            RewindFileCache rewind6 = getProject().method43().method6();
            UndoRedoManager nameplate27 = getProject().method40();
            highlight_45.method16(
               getProject(),
               uuid0,
               () -> {
                  try {
                     RewindFileReader rewind35x = rewind6.method4(uuid0);
                     int number6x = rewind35x.method13().method5();
                     if (Ref.method3().bridge$getProtocolVersion() != number6x) {
                        Ref.method4()
                           .method69()
                           .method6(NotificationType.ERROR, "RewindFileCache", "The rewind file was created in a different version of Minecraft.")
                           .method9(NotificationAnchor.BOTTOM_RIGHT);
                        return;
                     }

                     nameplate27.method1();
                     getProject().method7(rewind35x, number2, uuid1);
                     nameplate27.endBatch();
                  } catch (IOException exception7x) {
                     nameplate27.method2();
                     exception7x.printStackTrace();
                     Ref.method4()
                        .method69()
                        .method6(NotificationType.ERROR, "RewindFileCache", "Failed to load the media.")
                        .method9(NotificationAnchor.BOTTOM_RIGHT);
                  }
               },
               () -> {
                  try {
                     nameplate27.method1();
                     AudioSegment rewinditerator226x = new AudioSegment(getProject().method40());
                     MediaAudioStream rewindhandlers2impl7x = new MediaAudioStream(
                        getProject().method32(),
                        "media://" + uuid0,
                        getProject().method42(),
                        highlight_45,
                        getProject().method44()
                     );
                     rewindhandlers2impl7x.play();
                     rewinditerator226x.method9(rewindhandlers2impl7x);
                     int number8 = (int)(rewindhandlers2impl7x.getDuration() / highlight_33.method9());
                     Range range9 = Range.between(number2, number2 + number8);
                     SoundTrack[] items10 = highlight_33.method11().method4().toArray(new SoundTrack[0]);
                     SoundTrack guiimpl211 = null;

                     for (SoundTrack guiimpl215 : items10) {
                        if (guiimpl215.getId().equals(uuid1) || uuid1 == null) {
                           guiimpl211 = guiimpl215;
                           break;
                        }
                     }

                     if (guiimpl211 == null) {
                        guiimpl211 = new SoundTrack(getProject().method40(), highlight_33.method1());
                        highlight_33.method11().method4().add(guiimpl211);
                     }

                     guiimpl211.method5().method1(range9, rewinditerator226x);
                     rewinditerator226x.method5(range9, getProject().method38(), getProject().method40());
                     getProject().method45().method6(range9, rewinditerator226x, highlight_33.method9());
                     nameplate27.endBatch();
                  } catch (IOException exception16) {
                     nameplate27.method2();
                     exception16.printStackTrace();
                     Ref.method4()
                        .method69()
                        .method6(NotificationType.ERROR, "RewindFileCache", "Failed to load the media.")
                        .method9(NotificationAnchor.BOTTOM_RIGHT);
                  }
               },
               () -> {
                  nameplate27.method1();
                  ImageSegment rewindimpl5x = new ImageSegment(getProject().method40());
                  rewindimpl5x.method5(uuid0);
                  int number6x = (int)(10000.0 / highlight_33.method9());
                  Range range7x = Range.between(number2, number2 + number6x);
                  EffectTrack[] items8 = highlight_33.method11().method2().toArray(new EffectTrack[0]);
                  EffectTrack guiimpl9 = null;

                  for (EffectTrack guiimpl13 : items8) {
                     if (guiimpl13.getId().equals(uuid1) || uuid1 == null) {
                        guiimpl9 = guiimpl13;
                        break;
                     }
                  }

                  if (guiimpl9 == null) {
                     guiimpl9 = new EffectTrack(getProject().method40(), highlight_33.method1());
                     highlight_33.method11().method2().add(guiimpl9);
                  }

                  guiimpl9.method5().method1(range7x, rewindimpl5x);
                  rewindimpl5x.method16(range7x, getProject().method38(), getProject().method40());
                  getProject().method45().method6(range7x, rewindimpl5x, highlight_33.method9());
                  nameplate27.endBatch();
               }
            );
            refreshTimeline();
         }
      }
   }

   @CallbackJS("importMedia")
   public static void importMedia(UUID uuid0) {
      ReplayTimeline highlight_31 = getTimeline();
      if (highlight_31 != null) {
         MediaPool highlight_42 = getProject().method43().method5();
         File[] items3 = Gui4.method8(null, RewindPaths.field8, null, MediaPool.field4);
         if (items3 != null) {
            for (File file7 : items3) {
               UUID uuid8 = highlight_42.method2(file7);
               highlight_42.method13(uuid8, uuid0);
            }

            refreshMediaExporter();
         }
      }
   }

   @CallbackJS("removeMedia")
   public static void removeMedia(UUID uuid0) {
      MediaPool highlight_41 = getProject().method43().method5();
      boolean flag2 = highlight_41.method8(uuid0);
      String text3 = flag2 ? highlight_41.method11(uuid0, null) : null;
      File file4 = highlight_41.method3(uuid0);
      refreshMediaExporter();
      refreshTimeline();
      if (flag2) {
         Ref.method4()
            .method69()
            .method6(NotificationType.SUCCESS, "RewindFileCache", Ref.method4().method67().method2("popups", "deletedFolder", new Object[]{text3}))
            .method9(NotificationAnchor.BOTTOM_RIGHT);
      } else if (file4 != null) {
         Ref.method4()
            .method69()
            .method6(NotificationType.SUCCESS, "RewindFileCache", Ref.method4().method67().method2("popups", "deletedMedia", new Object[]{file4.getName()}))
            .method9(NotificationAnchor.BOTTOM_RIGHT);
      }
   }

   @CallbackJS("newFolder")
   public static void newFolder(UUID uuid0) {
      MediaPool highlight_41 = getProject().method43().method5();
      highlight_41.method10(uniqueFolderName(highlight_41, uuid0), uuid0);
      refreshMediaExporter();
   }

   @CallbackJS("rename")
   public static void newFolder(UUID uuid0, String text1) {
      MediaPool highlight_42 = getProject().method43().method5();
      highlight_42.method12(uuid0, text1);
      refreshMediaExporter();
      refreshTimeline();
   }

   @CallbackJS("moveMedia")
   public static void moveMedia(UUID uuid0, UUID uuid1) {
      MediaPool highlight_42 = getProject().method43().method5();
      highlight_42.method13(uuid0, uuid1);
      refreshMediaExporter();
   }

   private static String uniqueFolderName(MediaPool highlight_40, UUID uuid1) {
      HashSet set2 = new HashSet();

      for (Entry entry4 : highlight_40.method7()) {
         if (Objects.equals(highlight_40.method9((UUID)entry4.getKey()), uuid1)) {
            set2.add((String)entry4.getValue());
         }
      }

      String text5 = "New Folder";
      if (!set2.contains(text5)) {
         return text5;
      }

      int index6 = 2;

      while (set2.contains(text5 + " " + index6)) {
         index6++;
      }

      return text5 + " " + index6;
   }
}
