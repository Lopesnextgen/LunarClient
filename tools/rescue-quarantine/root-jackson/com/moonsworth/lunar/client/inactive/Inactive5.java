package com.moonsworth.lunar.client.inactive;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.Annotation27;
import com.moonsworth.lunar.client.Client;
import com.moonsworth.lunar.client.fov.mixin.Gui2Handler3;
import com.moonsworth.lunar.client.lighting.Lighting;
import com.moonsworth.lunar.client.lighting.LightingExtension;
import com.moonsworth.lunar.client.lighting.IntegerOption;
import com.moonsworth.lunar.client.lighting.ToggleOption;
import com.moonsworth.lunar.client.lighting.FloatOption;
import com.moonsworth.lunar.client.lighting.DropdownOption;
import com.moonsworth.lunar.client.lighting.IntegerOption.Data;
import com.moonsworth.lunar.client.markers.mixin.gui.Gui2;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;

public class Inactive5 implements Gui2 {
   private String id;
   private Inactive5.Type field1;
   private String field2;
   private Object defaultValue;
   private float field3 = 0.0F;
   private float field4 = 100.0F;
   private List<String> field5 = new ArrayList<>();

   @Override
   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("id", this.id);
      var1.add("defaultValue", Inactive5.Type.getJsonPrimitive(this.defaultValue));
      return var1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Inactive5 var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         if (Float.compare(this.method6(), var2.method6()) != 0) {
            return false;
         }

         if (Float.compare(this.method8(), var2.method8()) != 0) {
            return false;
         }

         String var3 = this.getId();
         String var4 = var2.getId();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            Inactive5.Type var5 = this.method2();
            Inactive5.Type var6 = var2.method2();
            if (var5 == null ? var6 == null : var5.equals(var6)) {
               String var7 = this.method3();
               String var8 = var2.method3();
               if (var7 == null ? var8 == null : var7.equals(var8)) {
                  Object var9 = this.getDefaultValue();
                  Object var10 = var2.getDefaultValue();
                  if (var9 == null ? var10 == null : var9.equals(var10)) {
                     List var11 = this.method10();
                     List var12 = var2.method10();
                     return var11 == null ? var12 == null : var11.equals(var12);
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof Inactive5;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + Float.floatToIntBits(this.method6());
      var2 = var2 * 59 + Float.floatToIntBits(this.method8());
      String var3 = this.getId();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      Inactive5.Type var4 = this.method2();
      var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      String var5 = this.method3();
      var2 = var2 * 59 + (var5 == null ? 43 : var5.hashCode());
      Object var6 = this.getDefaultValue();
      var2 = var2 * 59 + (var6 == null ? 43 : var6.hashCode());
      List var7 = this.method10();
      return var2 * 59 + (var7 == null ? 43 : var7.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "GeckolibCosmeticOption(id="
         + this.getId()
         + ", type="
         + this.method2()
         + ", molangQuery="
         + this.method3()
         + ", defaultValue="
         + this.getDefaultValue()
         + ", minValue="
         + this.method6()
         + ", maxValue="
         + this.method8()
         + ", enumValues="
         + this.method10()
         + ")";
   }

   @Annotation27("id")
   @Generated
   public String getId() {
      return this.id;
   }

   @Annotation27("id")
   @Generated
   public void setId(String var1) {
      this.id = var1;
   }

   @Annotation27("type")
   @Generated
   public Inactive5.Type method2() {
      return this.field1;
   }

   @Annotation27("type")
   @Generated
   public void method2(Inactive5.Type var1) {
      this.field1 = var1;
   }

   @Annotation27("molang_query")
   @Generated
   public String method3() {
      return this.field2;
   }

   @Annotation27("molang_query")
   @Generated
   public void method4(String var1) {
      this.field2 = var1;
   }

   @Annotation27("default")
   @Generated
   public Object getDefaultValue() {
      return this.defaultValue;
   }

   @Annotation27("default")
   @Generated
   public void setDefaultValue(Object var1) {
      this.defaultValue = var1;
   }

   @Annotation27("min")
   @Generated
   public float method6() {
      return this.field3;
   }

   @Annotation27("min")
   @Generated
   public void method7(float var1) {
      this.field3 = var1;
   }

   @Annotation27("max")
   @Generated
   public float method8() {
      return this.field4;
   }

   @Annotation27("max")
   @Generated
   public void method9(float var1) {
      this.field4 = var1;
   }

   @Annotation27("enum_values")
   @Generated
   public List<String> method10() {
      return this.field5;
   }

   @Annotation27("enum_values")
   @Generated
   public void method11(List<String> var1) {
      this.field5 = var1;
   }

   public enum Type {
      INT,
      FLOAT,
      BOOLEAN,
      ENUM;

      public static void loadDefaults(Gui2Handler3 var0, Inactive5 var1) {
         JsonObject var2 = var0.method5();
         if (!var2.has(var1.id)) {
            var2.add(var1.id, getJsonPrimitive(var1.defaultValue));
         }
      }

      public static LightingExtension getOption(Gui2Handler3 var0, Inactive5 var1) {
         loadDefaults(var0, var1);
         JsonObject var2 = var0.method5();
         String var3 = findOrFormatName("settings", var1.id);

         Object var4 = switch (var1.method2()) {
            case BOOLEAN -> {
               ToggleOption var8 = (ToggleOption)((com.moonsworth.lunar.client.lighting.ToggleOption.ToggleOptionBuilder)((com.moonsworth.lunar.client.lighting.ToggleOption.ToggleOptionBuilder)Lighting.method7(
                           var1.id
                        )
                        .OOOIROIIOCOOHICRIRHHHRROHHHHIO((Boolean)var1.getDefaultValue()))
                     .ROICHOCCIOCHCIHOIHIHICCORIROCC(var3))
                  .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
               var8.OIRHOOIICOCIOOHICRRRICORIHHIHC(var2.get(var1.id).getAsBoolean());
               yield var8;
            }
            case INT -> {
               IntegerOption var7 = (IntegerOption)((Data)((Data)((Data)Lighting.method4(var1.id)
                           .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(((Number)var1.getDefaultValue()).intValue()))
                        .OCRRICRIORICCCRHIOHORCICIHHICO(Math.round(var1.field3), Math.round(var1.field4)))
                     .ROICHOCCIOCHCIHOIHIHICCORIROCC(var3))
                  .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
               var7.method1(var2.get(var1.id).getAsInt());
               yield var7;
            }
            case FLOAT -> {
               FloatOption var6 = (FloatOption)((com.moonsworth.lunar.client.lighting.FloatOption.Data)((com.moonsworth.lunar.client.lighting.FloatOption.Data)((com.moonsworth.lunar.client.lighting.FloatOption.Data)Lighting.method2(
                              var1.id
                           )
                           .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(((Number)var1.getDefaultValue()).floatValue()))
                        .HRRCROICHIIROIHRCOIHRRHCCRIIRH(var1.field3, var1.field4))
                     .ROICHOCCIOCHCIHOIHIHICCORIROCC(var3))
                  .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
               var6.method1(var2.get(var1.id).getAsFloat());
               yield var6;
            }
            case ENUM -> {
               DropdownOption var5 = (DropdownOption)((com.moonsworth.lunar.client.lighting.DropdownOption.Data)((com.moonsworth.lunar.client.lighting.DropdownOption.Data)Lighting.method24(
                           var1.id, var2.get(var1.id).getAsString()
                        )
                        .method4(var1.field5)
                        .HHRROIIHRRICIIHIIHICRHHRHOHHOO(Codec.STRING))
                     .ROICHOCCIOCHCIHOIHIHICCORIROCC(var3))
                  .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
               var5.OIRHOOIICOCIOOHICRRRICORIHHIHC(var2.get(var1.id).getAsString());
               yield var5;
            }
         };
         var4.method8(var2x -> var2.add(var1.id, getJsonPrimitive(var2x)));
         return (LightingExtension)var4;
      }

      private static String findOrFormatName(String var0, String var1) {
         return !Client.method109().method67().method5(var0, var1)
            ? WordUtils.capitalize(var1.replaceAll("_", " "))
            : Client.method109().method67().method2(var0, var1, new Object[0]);
      }

      public static JsonPrimitive getJsonPrimitive(Object var0) {
         if (var0 instanceof String var1) {
            return new JsonPrimitive(var1);
         } else if (var0 instanceof Number var2) {
            return new JsonPrimitive(var2);
         } else if (var0 instanceof Boolean var3) {
            return new JsonPrimitive(var3);
         } else {
            throw new IllegalArgumentException("Must be a string, number, or boolean");
         }
      }
   }
}
