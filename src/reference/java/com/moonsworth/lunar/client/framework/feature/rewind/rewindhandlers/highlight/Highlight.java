package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.highlight;

import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.files.Files_5;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Generated;
import org.apache.commons.io.FileUtils;

public class Highlight {
   private static final String field1 = "https://rewind.lunarclientcdn.com/music/";
   private boolean field2 = false;
   private final File field3 = ThreadModuleDump48.field7.resolve("rewind").resolve("music").toFile();
   private final List<Highlight2> field4 = List.of(
      new Highlight2("Constellation Convos @omarcameup.opus", "ae1c86c5", "sparkling"),
      new Highlight2("Cutesy Bossanova @omarcameup.opus", "6472f72e", "palm-tree"),
      new Highlight2("Speed @omarcameup.opus", "3568e4a8", "sunglasses"),
      new Highlight2("Triumph @omarcameup.opus", "cfdc273f", "sword"),
      new Highlight2("Wowie Zowie! @omarcameup.opus", "cd1ae311", "tada")
   );
   private final Set<String> field5 = this.field4.stream().map(Highlight2::name).collect(Collectors.toSet());

   public void method1() {
      if (!this.field2) {
         this.field2 = true;

         for (Highlight2 var2 : this.field4) {
            File var3 = new File(this.field3, var2.name());
            if (var3.exists()) {
               String var4 = Files_5.method4(var3.toPath());
               if (var4.equals(var2.method1())) {
                  continue;
               }
            }

            ThreadModuleDump37.method6().execute(() -> {
               try {
                  FileUtils.copyURLToFile(new URL("https://rewind.lunarclientcdn.com/music/" + var2.name()), var3, 10000, 10000);
               } catch (IOException var3x) {
                  Inventorymod2.method5(var3x, "Rewind Music");
               }

               Coordinates.refreshMediaExporter();
            });
         }
      }
   }

   public boolean method2() {
      for (Highlight2 var2 : this.field4) {
         File var3 = new File(this.field3, var2.name());
         if (!var3.exists() || !Files_5.method4(var3.toPath()).equals(var2.method1())) {
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
   public List<Highlight2> method4() {
      return this.field4;
   }

   @Generated
   public Set<String> method5() {
      return this.field5;
   }
}
