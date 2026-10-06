package com.moonsworth.lunar.client.framework.feature.rewind;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import java.util.List;
import org.apache.commons.lang3.Range;

public class RewindImpl2 extends Rewind_2 {
   public RewindImpl2(Nameplate2 var1) {
      super(var1);
   }

   @Override
   public Rewind_2 method2(Nameplate2 var1, Range<Integer> var2, Range<Integer> var3) {
      RewindImpl2 var4 = new RewindImpl2(var1);
      return (Rewind_2)this.method16(var4, var1, var2, var3);
   }

   @Override
   public List<String> method15() {
      return List.of("text");
   }

   @Override
   public String type() {
      return "text";
   }

   @Override
   public JsonElement method16(Highlight_3 var1, Range<Integer> var2, RewindHandlers var3, Runnable var4) {
      JsonObject var5 = super.method16(var1, var2, var3, var4).getAsJsonObject();
      var5.addProperty("thumbnail", "rewind/text.png");
      return var5;
   }
}
