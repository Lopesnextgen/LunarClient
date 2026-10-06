package com.moonsworth.lunar.client.replay.audio;

import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.files.FileHashUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Generated;
import org.apache.commons.io.FileUtils;

public class MusicTrackManager {
   private static final String field1 = "https://rewind.lunarclientcdn.com/music/";
   private boolean field2 = false;
   private final File field3 = LunarConstants.field7.resolve("rewind").resolve("music").toFile();
   private final List<MusicTrack> field4 = List.of(
      new MusicTrack("Constellation Convos @omarcameup.opus", "ae1c86c5", "sparkling"),
      new MusicTrack("Cutesy Bossanova @omarcameup.opus", "6472f72e", "palm-tree"),
      new MusicTrack("Speed @omarcameup.opus", "3568e4a8", "sunglasses"),
      new MusicTrack("Triumph @omarcameup.opus", "cfdc273f", "sword"),
      new MusicTrack("Wowie Zowie! @omarcameup.opus", "cd1ae311", "tada")
   );
   private final Set<String> field5 = this.field4.stream().map(MusicTrack::name).collect(Collectors.toSet());

   public MusicTrackManager() {
   }

   public void method1() {
      if (!this.field2) {
         this.field2 = true;

         for (MusicTrack highlight22 : this.field4) {
            File file3 = new File(this.field3, highlight22.name());
            if (file3.exists()) {
               String text4 = FileHashUtils.method4(file3.toPath());
               if (text4.equals(highlight22.method1())) {
                  continue;
               }
            }

            BackgroundExecutor.method6().execute(() -> {
               try {
                  FileUtils.copyURLToFile(new URL("https://rewind.lunarclientcdn.com/music/" + highlight22.name()), file3, 10000, 10000);
               } catch (IOException exception3x) {
                  CrashReporter.method5(exception3x, "Rewind Music");
               }

               RewindEditorContext.refreshMediaExporter();
            });
         }
      }
   }

   public boolean method2() {
      for (MusicTrack highlight22 : this.field4) {
         File file3 = new File(this.field3, highlight22.name());
         if (!file3.exists() || !FileHashUtils.method4(file3.toPath()).equals(highlight22.method1())) {
            return false;
         }
      }

      return true;
   }

   @Generated
   public File method3() {
      return this.field3;
   }

   @Generated
   public List<MusicTrack> method4() {
      return this.field4;
   }

   @Generated
   public Set<String> method5() {
      return this.field5;
   }
}
