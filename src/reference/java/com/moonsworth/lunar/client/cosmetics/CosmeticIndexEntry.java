package com.moonsworth.lunar.client.cosmetics;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.moonsworth.lunar.client.cosmetics.gecko.ItemRenderMaterial;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.Generated;

public class CosmeticIndexEntry {
   @SerializedName("id")
   private final int field1;
   @SerializedName("name")
   private final String field2;
   @JsonAdapter(CosmeticIndexEntry.Data.class)
   @SerializedName("resource")
   private final String resource;
   @SerializedName("category")
   private final String field3;
   @SerializedName("indexType")
   private final String field4;
   @SerializedName("morph")
   private final String field5;
   @SerializedName("morphDuration")
   private final Integer field6;
   @SerializedName("geckolibCosmetic")
   private final boolean field7;
   @SerializedName("special")
   private final boolean field8;
   @SerializedName("animated")
   private final boolean field9;
   @SerializedName("item_material")
   private final ItemRenderMaterial field10;
   @SerializedName("hideOnProfileExternal")
   private final boolean field11;
   @SerializedName("colors")
   private final List<String> field12;
   @SerializedName("tags")
   private final List<String> field13;
   @SerializedName("releasedAt")
   private final Date field14;
   private boolean field15;

   public String method1() {
      int var1 = this.resource.indexOf(58);
      return var1 >= 0 ? this.resource.substring(var1 + 1) : this.resource;
   }

   public Optional<CosmeticCategoryType> method2() {
      return CosmeticCategoryType.from(this.field3);
   }

   public boolean method3(CosmeticCategoryType var1) {
      return this.method2().map(var1::equals).orElse(false);
   }

   @Generated
   public int getId() {
      return this.field1;
   }

   @Generated
   public String getName() {
      return this.field2;
   }

   @Generated
   public String getResource() {
      return this.resource;
   }

   @Generated
   public String method5() {
      return this.field4;
   }

   @Generated
   public String method6() {
      return this.field5;
   }

   @Generated
   public Integer method7() {
      return this.field6;
   }

   @Generated
   public boolean method8() {
      return this.field7;
   }

   @Generated
   public boolean isSpecial() {
      return this.field8;
   }

   @Generated
   public boolean method9() {
      return this.field9;
   }

   @Generated
   public ItemRenderMaterial method10() {
      return this.field10;
   }

   @Generated
   public boolean method11() {
      return this.field11;
   }

   @Generated
   public List<String> getColors() {
      return this.field12;
   }

   @Generated
   public List<String> method12() {
      return this.field13;
   }

   @Generated
   public Date method13() {
      return this.field14;
   }

   @Generated
   public boolean method14() {
      return this.field15;
   }

   @Generated
   @Override
   public String toString() {
      return "CosmeticIndexEntry(id="
         + this.getId()
         + ", name="
         + this.getName()
         + ", resource="
         + this.getResource()
         + ", category="
         + this.method2()
         + ", indexType="
         + this.method5()
         + ", morph="
         + this.method6()
         + ", morphDuration="
         + this.method7()
         + ", geckolibCosmetic="
         + this.method8()
         + ", special="
         + this.isSpecial()
         + ", animated="
         + this.method9()
         + ", itemRenderMaterial="
         + this.method10()
         + ", hideOnProfileExternal="
         + this.method11()
         + ", colors="
         + this.getColors()
         + ", tags="
         + this.method12()
         + ", releasedAt="
         + this.method13()
         + ", isDev="
         + this.method14()
         + ")";
   }

   @Generated
   public CosmeticIndexEntry(
      int var1,
      String var2,
      String var3,
      String var4,
      String var5,
      String var6,
      Integer var7,
      boolean var8,
      boolean var9,
      boolean var10,
      ItemRenderMaterial var11,
      boolean var12,
      List<String> var13,
      List<String> var14,
      Date var15,
      boolean var16
   ) {
      this.field1 = var1;
      this.field2 = var2;
      this.resource = var3;
      this.field3 = var4;
      this.field4 = var5;
      this.field5 = var6;
      this.field6 = var7;
      this.field7 = var8;
      this.field8 = var9;
      this.field9 = var10;
      this.field10 = var11;
      this.field11 = var12;
      this.field12 = var13;
      this.field13 = var14;
      this.field14 = var15;
      this.field15 = var16;
   }

   @Generated
   public void method15(boolean var1) {
      this.field15 = var1;
   }

   private static class Data extends TypeAdapter<String> {
      public void write(JsonWriter var1, String var2) {
         var1.value(var2);
      }

      public String read(JsonReader var1) {
         String var2 = var1.nextString();
         int var3 = var2.indexOf(58);
         if (var3 >= 0) {
            var2 = var2.substring(var3 + 1);
         }

         return "lunar-jit:" + var2;
      }
   }
}
