package com.moonsworth.lunar.client.framework.feature.rewind.highlight;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.util.Annotation7;
import java.io.File;
import lombok.Generated;

@Annotation7
public class HighlightImpl extends Highlight_3 {
   @SerializedName("savePath")
   private String field10;
   private File field11;

   public HighlightImpl(Nameplate2 var1) {
      super(var1);
   }

   @Generated
   public void setSavePath(String var1) {
      this.field10 = var1;
   }

   @Generated
   public void method2(File var1) {
      this.field11 = var1;
   }

   @Generated
   public String method4() {
      return this.field10;
   }

   @Generated
   public File method5() {
      return this.field11;
   }
}
