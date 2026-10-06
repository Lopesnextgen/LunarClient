package com.moonsworth.lunar.client.mod.hud.ping;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.PlayerInfoBridge;
import com.moonsworth.lunar.bridge.ServerDataBridge;
import com.moonsworth.lunar.bridge.OldServerPingerBridge;
import com.moonsworth.lunar.client.chat.translation.SharedTranslations;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.Nullable;

public class Ping extends AbstractFeature {
   private static final ExecutorService field8 = Executors.newSingleThreadExecutor(new DefaultThreadFactory("lunar-ping-mod-thread", true));
   private int field9 = 0;
   private int field10 = -1;
   private final OldServerPingerBridge field11;
   private final List<Integer> field12 = new ArrayList<>();
   private int field13 = -1;
   private int field14 = -1;
   private long field15 = -1L;
   private int field16 = -1;
   private final IntegerOption field17 = (IntegerOption)((Data)((Data)OptionFactory.method4("updateIntervalSec").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(5))
         .method7(1, 120))
      .method31();
   private final EnumOption<Ping.Type> field18 = (EnumOption<Ping.Type>)OptionFactory.method10("pingMode", Ping.Type.LATEST)
      .method31();
   private final IntegerOption field19 = (IntegerOption)((Data)((Data)OptionFactory.method4("averageSamples").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(3))
         .method7(2, 20))
      .method31();
   private final ToggleOption field20 = (ToggleOption)OptionFactory.method7("pingSpikeDetection").method31();
   private final IntegerOption field21 = (IntegerOption)((Data)((Data)OptionFactory.method4("mediumSpikeThreshold").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(20))
         .method7(1, 200))
      .method31();
   private final IntegerOption field22 = (IntegerOption)((Data)((Data)OptionFactory.method4("largeSpikeThreshold").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(50))
         .method7(1, 200))
      .method31();
   private final ColorOption field23 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "mediumSpikeColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-28416))
      .method31();
   private final ColorOption field24 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "largeSpikeColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-65536))
      .method31();
   private final ToggleOption field25 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("pingShowMs").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ToggleOption field26 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showPingPrefix").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final TextOption field27 = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)OptionFactory.method12(
            "pingPrefix"
         )
         .method2("Ping: "))
      .method3(10)
      .method31();
   protected final ColorOption field28 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "pingPrefixColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ToggleOption field29 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("dynamicPingColor").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field30 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "pingNumberColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption field31 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "lowPingNumberColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141291))
      .method31();
   private final ColorOption field32 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "mediumPingNumberColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption field33 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "highPingNumberColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-43691))
      .method31();
   private final ColorOption field34 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "extremePingNumberColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-5636096))
      .method31();
   private final PingHud field35 = new PingHud(this);
   private final PingNametag field36 = new PingNametag(this);

   public Ping() {
      super(false);
      this.field11 = Bridge.method8().method27();
      this.handle(EventSecond.class, arg1 -> {
         if ((this.field10 != -1 || this.field9 != 0) && this.field9 < (this.field10 == 0 ? 1 : (Integer)this.field17.get())) {
            this.field9++;
         } else {
            this.field9 = 0;
            this.method13();
         }
      });
      this.handle(EventTick.class, arg1 -> {
         field8.execute(this.field11::bridge$tick);
         ServerDataBridge bridge3_192 = this.mc.bridge$getCurrentServerData();
         if (bridge3_192 != null) {
            int number3 = (int)bridge3_192.bridge$getPingToServer();
            this.method6(this.field16, number3);
            this.field16 = number3;
         }
      });
      this.handle(EventDisconnect.class, arg1 -> this.clear());
   }

   public String getId() {
      return "PING";
   }

   protected List<Framework7Extension> method9() {
      return ImmutableList.of(this.field35, this.field36);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.method9(new ClientOption[]{this.field17, this.field18});
            arg1x.method9(new ClientOption[]{this.field19}).method3(() -> this.field18.get() != Ping.Type.AVERAGE);
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field20, arg1xx -> arg1xx.method9(new ClientOption[]{this.field21, this.field23, this.field22, this.field24})
            );
         }
      );
      lightingextension231.method1(
         "displayOptions",
         arg1x -> {
            arg1x.method9(new ClientOption[]{this.field25});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field26, arg1xx -> arg1xx.method9(new ClientOption[]{this.field27, this.field28})
            );
            arg1x.method9(new ClientOption[]{this.field29});
            arg1x.method9(new ClientOption[]{this.field30}).method3(this.field29::get);
            arg1x.method9(new ClientOption[]{this.field31, this.field32, this.field33, this.field34})
               .method3(() -> !(Boolean)this.field29.get());
         }
      );
   }

   public TextComponent method3(int number1, boolean flag2, @Nullable ColorOption lightingextension42223) {
      TextComponent text4 = Component.text("");
      text4 = (TextComponent)text4.append(
         Component.text(
            (number1 < 0 ? "..." : number1) + (this.field25.get() ? SharedTranslations.field2 : ""),
            TextColor.color(lightingextension42223 != null ? lightingextension42223.getColor() : this.method8(number1).getColor())
         )
      );
      if (flag2 && this.field14 != -1 && (Boolean)this.field20.get()) {
         ColorOption lightingextension42225 = Math.abs(this.field14) >= this.field22.get() ? this.field24 : this.field23;
         text4 = (TextComponent)text4.append(Component.text(" (" + (this.field14 > 0 ? "+" : "") + this.field14 + "%)", TextColor.color(lightingextension42225.method13())));
      }

      return text4;
   }

   private void method13() {
      ServerDataBridge bridge3_191 = this.mc.bridge$getCurrentServerData();
      if (bridge3_191 != null) {
         field8.execute(() -> {
            try {
               this.field11.bridge$ping(bridge3_191);
            } catch (UnknownHostException unknownhostexception3) {
            }
         });
         int number2 = (Integer)this.method15("ping", (int)bridge3_191.bridge$getPingToServer());
         if (number2 >= 0) {
            this.method5(number2);
         }
      }
   }

   private void method5(int index1) {
      this.method6(this.field10, index1);
      this.field10 = index1;
      this.field12.add(index1);
      int number2 = this.field12.size();
      if (number2 >= (Integer)this.field19.get()) {
         Iterator iterator3 = this.field12.iterator();
         int number4 = 0;

         while (iterator3.hasNext()) {
            number4 += iterator3.next();
            iterator3.remove();
         }

         number4 /= number2;
         this.field13 = number4;
      }
   }

   private void method6(int number1, int number2) {
      long number3 = Ref.method3().bridge$getSystemTime();
      if (this.field15 != -1L && number3 - this.field15 >= 5000L) {
         this.field14 = -1;
         this.field15 = -1L;
      }

      if (number1 != -1 && number2 != -1) {
         float value5 = number2 - number1;
         int number6 = number1 <= 0 ? 0 : (int)(value5 / number1 * 100.0F);
         if (Math.abs(number6) >= (Integer)this.field21.get()) {
            this.field14 = number6;
            this.field15 = number3;
            this.field10 = number2;
         }
      }
   }

   public int method14() {
      return this.field18.get() == Ping.Type.AVERAGE && this.field13 != -1 ? this.field13 : this.field10;
   }

   public ColorOption method8(int number1) {
      if (number1 < 0 || !(Boolean)this.field29.get()) {
         return this.field30;
      } else if (number1 < 65) {
         return this.field31;
      } else if (number1 < 120) {
         return this.field32;
      } else {
         return number1 < 250 ? this.field33 : this.field34;
      }
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field4}).method11(this);
   }

   public void method3(boolean flag1) {
      this.clear();
   }

   private void clear() {
      this.field9 = 0;
      this.field10 = -1;
      this.field13 = -1;
      this.field14 = -1;
      this.field15 = -1L;
      if (this.field12 != null) {
         this.field12.clear();
      }
   }

   @Nullable
   public PlayerInfoBridge method11(UUID uuid1) {
      return Ref.method7() != null && Ref.method7().bridge$getSendQueue() != null
         ? Ref.method7().bridge$getSendQueue().bridge$getPlayerInfo(uuid1)
         : null;
   }

   @Generated
   public ToggleOption method15() {
      return this.field25;
   }

   @Generated
   public PingHud method16() {
      return this.field35;
   }

   @Generated
   public PingNametag method17() {
      return this.field36;
   }

   private enum Type implements OptionEnumValue {
      LATEST("latest"),
      AVERAGE("averaged");

      private final String id;

      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.method51(this.id, new Object[0]);
      }

      @Generated
      Type(String text3) {
         this.id = text3;
      }
   }
}
