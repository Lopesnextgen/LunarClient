package com.moonsworth.lunar.client.framework.feature.autotextactions;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.JsonPersistable;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lighting3Loader implements JsonPersistable {
   protected final ToggleOption field1 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("active").method4(true))
         .RRRCHRRHIOCOCIHCHIHHHHRORHROIR())
      .method31();
   protected final TextOption field2 = (TextOption)OptionFactory.method12("triggerKey").method31();
   protected final ToggleOption field3 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("regex").RRRCHRRHIOCOCIHCHIHHHHRORHROIR())
      .method31();
   protected final ToggleOption field4 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("contains").RRRCHRRHIOCOCIHCHIHHHHRORHROIR())
      .method31();
   protected final ToggleOption field5 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("caseSensitive").RRRCHRRHIOCOCIHCHIHHHHRORHROIR())
      .method31();
   protected final ToggleOption field6 = (ToggleOption)OptionFactory.method7("hideMessage").method31();
   protected final ToggleOption field7 = (ToggleOption)OptionFactory.method7("showTitleAction").method31();
   protected final TextOption field8 = (TextOption)OptionFactory.method12("titleText").method31();
   protected final ToggleOption field9 = (ToggleOption)OptionFactory.method7("desktopNotification").method31();
   protected final ToggleOption field10 = (ToggleOption)OptionFactory.method7("ingameNotification").method31();
   protected final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("playSound").RRRCHRRHIOCOCIHCHIHHHHRORHROIR())
      .method31();
   private Pattern pattern = null;
   private String field12 = null;

   public Lighting3Loader method1() {
      JsonObject var1 = new JsonObject();
      this.method1(var1);
      Lighting3Loader var2 = new Lighting3Loader();
      var2.load(var1);
      return var2;
   }

   public void load(JsonObject var1) {
      this.field1.load(var1);
      this.field2.load(var1);
      this.field3.load(var1);
      this.field4.load(var1);
      this.field5.load(var1);
      this.field6.load(var1);
      this.field7.load(var1);
      this.field8.load(var1);
      this.field9.load(var1);
      this.field10.load(var1);
      this.field11.load(var1);
   }

   public void method1(JsonObject var1) {
      this.field1.load(var1);
      this.field2.load(var1);
      this.field3.load(var1);
      this.field4.load(var1);
      this.field5.load(var1);
      this.field6.load(var1);
      this.field7.load(var1);
      this.field8.load(var1);
      this.field9.load(var1);
      this.field10.load(var1);
      this.field11.load(var1);
   }

   public String getDisplayName() {
      TranslationManager var1 = ThreadModuleDump63.method4().method67();
      String var2 = (String)this.field2.get();
      if (var2 != null && !var2.trim().isEmpty()) {
         if (var2.length() > 8) {
            var2 = var2.substring(0, 8) + "...";
         }

         StringBuilder var3 = new StringBuilder();
         var3.append(var2);
         var3.append(" (");
         if ((Boolean)this.field3.get()) {
            this.validate();
            if (this.pattern == null) {
               var3.append(var1.method2("gui.autoTextTriggers", "regexInvalid", new Object[0]));
            } else {
               var3.append(var1.method2("gui.autoTextTriggers", "regexValid", new Object[0]));
            }
         } else {
            if ((Boolean)this.field4.get()) {
               var3.append(var1.method2("gui.autoTextTriggers", "contains", new Object[0]));
            } else {
               var3.append(var1.method2("gui.autoTextTriggers", "literal", new Object[0]));
            }

            if ((Boolean)this.field5.get()) {
               var3.append(", ");
               var3.append(var1.method2("gui.autoTextTriggers", "caseSensitive", new Object[0]));
            }
         }

         var3.append(")");
         return var3.toString();
      } else {
         return var1.method2("gui.autoTextTriggers", "newTrigger", new Object[0]);
      }
   }

   public boolean matches(String var1) {
      if (!(Boolean)this.field1.get()) {
         return false;
      }

      String var2 = (String)this.field2.get();
      if (var2 == null || var2.trim().isEmpty()) {
         return false;
      }

      if ((Boolean)this.field3.get()) {
         this.validate();
         if (this.pattern == null) {
            return false;
         }

         Matcher var4 = this.pattern.matcher(var1);
         return var4.find();
      } else {
         if (!(Boolean)this.field5.get()) {
            var1 = var1.toLowerCase(Locale.ROOT);
            var2 = var2.toLowerCase(Locale.ROOT);
         }

         boolean var3 = !(Boolean)this.field4.get();
         return var3 ? var1.equals(var2) : var1.contains(var2);
      }
   }

   protected void validate() {
      String var1 = (String)this.field2.get();
      if (!(Boolean)this.field3.get()) {
         this.field12 = null;
      } else {
         if (!Objects.equals(this.field12, var1)) {
            this.field12 = var1;

            try {
               this.pattern = Pattern.compile(var1);
            } catch (Exception var3) {
               this.pattern = null;
            }
         }
      }
   }
}
