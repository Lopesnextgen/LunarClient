package com.moonsworth.lunar.client.framework.feature;

import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindMod;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.Generated;

public class ModOptionOverrides {
   private final Framework7Extension field1;
   private final Map<String, Object> field2 = new HashMap<>();

   public <T> T method1(String text1, T value2) {
      this.method2(text1, value2);
      RewindMod rewind3 = Ref.method4().method40().method85();
      return (T)(!rewind3.method19() ? value2 : this.field2.getOrDefault(text1, value2));
   }

   private void method2(String text1, Object obj2) {
      Object obj3 = this.field2.get(text1);
      if (!Objects.equals(obj3, obj2)) {
         RewindMod rewind4 = Ref.method4().method40().method85();
         if (!rewind4.method19()) {
            this.set(text1, obj2);
         }

         if (rewind4.isRecording()) {
            RewindRecorder rewindhandlers55 = rewind4.method34();

            try {
               rewindhandlers55.method23().putIfAbsent(this.field1.getId(), new HashMap());
               ((Map)rewindhandlers55.method23().get(this.field1.getId())).put(text1, obj2);
            } catch (Exception exception7) {
               throw new RuntimeException(exception7);
            }
         }
      }
   }

   public void set(String text1, Object obj2) {
      this.field2.put(text1, obj2);
   }

   @Generated
   public ModOptionOverrides(Framework7Extension framework7extension1) {
      this.field1 = framework7extension1;
   }

   @Generated
   public Map<String, Object> method3() {
      return this.field2;
   }
}
