package com.moonsworth.lunar.client.network.server;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class ServerIconEntry {
   private final List<String> field1 = new ArrayList<>();
   @Nullable
   private ResourceLocationBridge field2;

   public ServerIconEntry(String text, String text2) {
      this.field1.add(text);
      if (text.startsWith("*.")) {
         this.field1.add(text.substring(2));
      }

      if (text2 != null) {
         try {
            this.field2 = ResourceLocationBridge.create("lunar", text2);
         } catch (Exception exception4) {
            this.field2 = null;
         }
      }

      if (this.field2 == null) {
         this.field2 = LcuiScreen.field2;
      }
   }

   @Generated
   public List<String> method1() {
      return this.field1;
   }

   @Nullable
   @Generated
   public ResourceLocationBridge getResource() {
      return this.field2;
   }
}
