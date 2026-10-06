package com.moonsworth.lunar.client.mod.misc;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension_2;
import com.moonsworth.lunar.bridge.BridgeExtension_9;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.hitcolor.Hitcolor2;
import com.moonsworth.lunar.client.framework.Framework;
import com.moonsworth.lunar.client.framework.Framework3;
import com.moonsworth.lunar.client.framework.Framework4;
import com.moonsworth.lunar.client.framework.Framework7Extension2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType8;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers.Data10;
import com.moonsworth.lunar.client.highlight.mixin.HighlightImpl.Data;
import com.moonsworth.lunar.client.highlight.mixin.nameplate.HighlightImpl2;
import com.moonsworth.lunar.client.lighting.LightingExtension443;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import org.joml.Vector3i;

public class SkyblockThreeWeirdos extends Framework7Extension2 {
   private final Pattern field8 = Pattern.compile("^(\\[NPC]) (\\w+): (.+)");
   private final List<Pattern> field9 = List.of(
      Pattern.compile("^The reward is not in my chest!$"),
      Pattern.compile("^At least one of them is lying, and the reward is not in (\\w+)'s chest!$"),
      Pattern.compile("^My chest doesn't have the reward\\. We are all telling the truth\\.$"),
      Pattern.compile("^My chest has the reward and I'm telling the truth!$"),
      Pattern.compile("^The reward isn't in any of our chests\\.$"),
      Pattern.compile("^Both of them are telling the truth. Also, (\\w+) has the reward in their chest!$")
   );
   private Vector3i field10 = null;

   public SkyblockThreeWeirdos(SkyblockDungeonPuzzles var1, LightingExtension443 var2) {
      super(true);
      this.HORHROIOIOICIRHIOCOICHHHIHCIIO(Framework.field16, Framework4.method4(false, var1));
      this.HORHROIOIOICIRHIOCOICHHHIHCIIO(Framework.field6, Framework3.method7(var2));
      this.RCIOICOHRIOIIRRRROCRHCIICRROHO(this::method13);
      this.handle(Data10.class, var1x -> this.method13());
      this.handle(Data.class, this::method3);
      this.handle(HighlightImpl2.class, this::method4);
      this.handle(com.moonsworth.lunar.client.highlight.mixin.fishing.HighlightImpl2.class, var1x -> this.method14());
   }

   public String getId() {
      return "SKYBLOCK_THREE_WEIRDOS";
   }

   protected void method1(boolean var1) {
   }

   public void method3(boolean var1) {
      if (var1) {
         SkyblockDungeonPuzzles var2 = (SkyblockDungeonPuzzles)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
         var2.method13();
      }
   }

   private void method3(Data var1) {
      SkyblockDungeonPuzzles var2 = (SkyblockDungeonPuzzles)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
      Holograms4Iterator var3 = var2.method47();
      if (var3 != null && var3.method25() == HologramsType8.THREE_WEIRDOS) {
         Holograms3 var4 = (Holograms3)var3.method23().orElse(null);
         if (var4 != null) {
            String var5 = var1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
            Matcher var6 = this.field8.matcher(var5);
            if (var6.find()) {
               String var7 = var6.group(2);
               String var8 = var6.group(3);

               for (Pattern var10 : this.field9) {
                  var6 = var10.matcher(var8);
                  if (var6.find()) {
                     Itemcounter6Extension var11 = ThreadModuleDump63.method8();
                     if (var11 == null) {
                        return;
                     }

                     BridgeExtension var12 = null;

                     for (BridgeExtension var14 : var11.bridge$getEntities()) {
                        if (var14 instanceof BridgeExtension_2) {
                           Component var15 = var14.bridge$getCustomName();
                           if (var15 != null) {
                              String var16 = Bridge5.getTextContent(var15);
                              if (var16.equals(var7)) {
                                 var12 = var14;
                                 break;
                              }
                           }
                        }
                     }

                     if (var12 == null) {
                        return;
                     }

                     this.field10 = new Vector3i((int)Math.floor(var12.bridge$getPosX()), 69, (int)Math.floor(var12.bridge$getPosZ()))
                        .add(var4.method28().asDirectionBridge().getVector());
                  }
               }
            }
         }
      }
   }

   private void method4(HighlightImpl2 var1) {
      SkyblockDungeonPuzzles var2 = (SkyblockDungeonPuzzles)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
      Holograms4Iterator var3 = var2.method47();
      if (var3 != null && var3.method25() == HologramsType8.THREE_WEIRDOS) {
         Vector3i var4 = this.field10;
         if (var4 != null) {
            BridgeExtension_9 var5 = var1.method3();
            Bridge2_43 var6 = ThreadModuleDump63.method13();
            var5.push();
            var5.translate(-var6.bridge$renderPosX(), -var6.bridge$renderPosY(), -var6.bridge$renderPosZ());
            Click.method1(var5, var4);
            var5.pop();
         }
      }
   }

   private void method13() {
      this.field10 = null;
   }

   private void method14() {
      SkyblockDungeonPuzzles var1 = (SkyblockDungeonPuzzles)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
      Holograms4Iterator var2 = var1.method47();
      if (var2 != null && var2.method25() == HologramsType8.THREE_WEIRDOS) {
         if (this.field10 != null) {
            Itemcounter6Extension var3 = ThreadModuleDump63.method8();
            if (var3 != null) {
               if (var3.bridge$getBlockEntity(Bridge.method8().method4(this.field10.x(), this.field10.y(), this.field10.z())) instanceof Hitcolor2 var5) {
                  if (var5.bridge$isVisuallyOpen()) {
                     this.field10 = null;
                  }
               } else {
                  this.field10 = null;
               }
            }
         }
      }
   }
}
