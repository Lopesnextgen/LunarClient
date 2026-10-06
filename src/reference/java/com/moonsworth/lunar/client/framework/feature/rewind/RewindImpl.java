package com.moonsworth.lunar.client.framework.feature.rewind;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_4;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.Annotation7;
import java.io.File;
import java.util.List;
import java.util.UUID;
import lombok.Generated;
import org.apache.commons.lang3.Range;

@Annotation7
public class RewindImpl extends Rewind_2 {
   @SerializedName("source")
   private UUID field7 = null;

   public RewindImpl(Nameplate2 var1) {
      super(var1);
   }

   @Override
   public Rewind_2 method2(Nameplate2 var1, Range<Integer> var2, Range<Integer> var3) {
      RewindImpl var4 = new RewindImpl(var1);
      return (Rewind_2)this.method16(var4, var1, var2, var3);
   }

   @Override
   public List<String> method15() {
      return List.of("image");
   }

   @Override
   public String type() {
      return "image";
   }

   @Override
   public JsonElement method16(Highlight_3 var1, Range<Integer> var2, RewindHandlers var3, Runnable var4) {
      JsonObject var5 = super.method16(var1, var2, var3, var4).getAsJsonObject();
      File var6 = var3.method40().method43().method5().method6(this.field7);
      if (var6 != null) {
         var5.addProperty("thumbnail", Highlight_4.method15(var6));
      }

      return var5;
   }

   @Generated
   public UUID method4() {
      return this.field7;
   }

   @Generated
   public void method5(UUID var1) {
      this.field7 = var1;
   }
}
