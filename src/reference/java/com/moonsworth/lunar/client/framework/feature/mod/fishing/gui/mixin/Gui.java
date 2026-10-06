package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin;

import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.mod.Module;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Map;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

public class Gui {
   @SerializedName("name")
   private final String field1;
   @SerializedName("displayName")
   private final String field2;
   @SerializedName("maxLevel")
   private final int field3;
   @SerializedName("hypermaxLevel")
   private final int field4;
   @SerializedName("abbreviation")
   private final String field5;
   public static final Pattern field6 = Pattern.compile("(?<enchantName>[[a-zA-Z-] ]+) ([0-9IVXLCDM]+)(?:,|$)");

   public Gui(String var1, String var2, int var3, int var4, String var5) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = var5;
   }

   @Nullable
   public static Gui method1(String var0) {
      Module var1 = ThreadModuleDump63.method4().method40().method82().method15();
      Map var2 = var1.method25();
      return var2 == null ? null : (Gui)var2.get(var0);
   }

   public boolean method2() {
      return this.field1.startsWith("ULTIMATE_");
   }

   @SerializedName("name")
   public String name() {
      return this.field1;
   }

   @SerializedName("displayName")
   public String displayName() {
      return this.field2;
   }

   @SerializedName("maxLevel")
   public int method3() {
      return this.field3;
   }

   @SerializedName("hypermaxLevel")
   public int method4() {
      return this.field4;
   }

   @SerializedName("abbreviation")
   public String method5() {
      return this.field5;
   }
}
