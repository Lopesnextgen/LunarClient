package com.moonsworth.lunar.client.replay.export;

import java.util.List;
import java.util.function.Function;
import lombok.Generated;

public enum VideoEncoder implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   AV1_VIDEOTOOLBOX("av1_videotoolbox", arg0 -> List.of("-c:v", "av1_videotoolbox", "-b:v", arg0 + "k", "-tag:v", "av01", "-allow_sw", "1"), true, true),
   AV1_NVENC(
      "av1_nvenc", arg0 -> List.of("-c:v", "av1_nvenc", "-preset", "p3", "-tune", "hq", "-rc", "vbr", "-b:v", arg0 + "k", "-rc-lookahead", "20"), true, false
   ),
   AV1_AMF("av1_amf", arg0 -> List.of("-c:v", "av1_amf", "-quality", "quality", "-rc", "vbr_peak", "-b:v", arg0 + "k"), true, false),
   AV1_QSV("av1_qsv", arg0 -> List.of("-c:v", "av1_qsv", "-preset", "medium", "-b:v", arg0 + "k"), true, false),
   VP9_VIDEOTOOLBOX("vp9_videotoolbox", arg0 -> List.of("-c:v", "vp9_videotoolbox", "-b:v", arg0 + "k", "-tag:v", "vp09", "-allow_sw", "1"), true, true),
   VP9_NVENC(
      "vp9_nvenc", arg0 -> List.of("-c:v", "vp9_nvenc", "-preset", "p3", "-tune", "hq", "-rc", "vbr", "-b:v", arg0 + "k", "-rc-lookahead", "16"), true, false
   ),
   VP9_AMF("vp9_amf", arg0 -> List.of("-c:v", "vp9_amf", "-quality", "quality", "-rc", "vbr_peak", "-b:v", arg0 + "k"), true, false),
   VP9_QSV("vp9_qsv", arg0 -> List.of("-c:v", "vp9_qsv", "-preset", "medium", "-b:v", arg0 + "k"), true, false),
   HEVC_VIDEOTOOLBOX("hevc_videotoolbox", arg0 -> List.of("-c:v", "hevc_videotoolbox", "-b:v", arg0 + "k", "-tag:v", "hvc1", "-allow_sw", "1"), true, true),
   HEVC_NVENC(
      "hevc_nvenc",
      arg0 -> List.of(
         "-c:v", "hevc_nvenc", "-preset", "p6", "-tune", "hq", "-rc", "vbr", "-b:v", arg0 + "k", "-tag:v", "hvc1", "-bf", "2", "-rc-lookahead", "20"
      ),
      true,
      false
   ),
   HEVC_AMF(
      "hevc_amf", arg0 -> List.of("-c:v", "hevc_amf", "-quality", "quality", "-rc", "vbr_peak", "-b:v", arg0 + "k", "-tag:v", "hvc1", "-bf", "2"), true, false
   ),
   HEVC_QSV("hevc_qsv", arg0 -> List.of("-c:v", "hevc_qsv", "-preset", "medium", "-look_ahead", "1", "-b:v", arg0 + "k", "-tag:v", "hvc1"), true, false),
   H264_VIDEOTOOLBOX("h264_videotoolbox", arg0 -> List.of("-c:v", "h264_videotoolbox", "-b:v", arg0 + "k", "-allow_sw", "1"), true, true),
   H264_NVENC(
      "h264_nvenc",
      arg0 -> {
         String text1 = arg0 + "k";
         String text2 = (int)(arg0.intValue() * 1.25) + "k";
         return List.of(
            "-c:v",
            "h264_nvenc",
            "-preset",
            "p6",
            "-tune",
            "hq",
            "-b:v",
            text1,
            "-bufsize",
            text1,
            "-maxrate",
            text2,
            "-qmin",
            "0",
            "-g",
            "250",
            "-bf",
            "3",
            "-b_ref_mode",
            "middle",
            "-temporal-aq",
            "1",
            "-rc-lookahead",
            "20",
            "-i_qfactor",
            "0.75",
            "-b_qfactor",
            "1.1"
         );
      },
      true,
      false
   ),
   H264_AMF("h264_amf", arg0 -> List.of("-c:v", "h264_amf", "-quality", "quality", "-rc", "vbr_peak", "-b:v", arg0 + "k"), true, false),
   H264_QSV("h264_qsv", arg0 -> List.of("-c:v", "h264_qsv", "-preset", "medium", "-look_ahead", "1", "-b:v", arg0 + "k"), true, false),
   AV1_LIBSVTAV1("libsvtav1", arg0 -> List.of("-c:v", "libsvtav1", "-b:v", arg0 + "k", "-preset", "8", "-g", "240", "-tune", "0"), false, false),
   AV1_LIBAOM("libaom-av1", arg0 -> List.of("-c:v", "libaom-av1", "-b:v", arg0 + "k", "-cpu-used", "4", "-row-mt", "1", "-tiles", "2x2"), false, false),
   VP9_LIBVPX("libvpx-vp9", arg0 -> List.of("-c:v", "libvpx-vp9", "-b:v", arg0 + "k", "-deadline", "good", "-row-mt", "1"), false, false),
   LIBX265(
      "libx265", arg0 -> List.of("-c:v", "libx265", "-b:v", arg0 + "k", "-preset", "medium", "-tune", "fastdecode", "-bf", "2", "-tag:v", "hvc1"), false, false
   ),
   LIBX264("libx264", arg0 -> {
      String text1 = arg0 + "k";
      String text2 = (int)(arg0.intValue() * 1.25) + "k";
      return List.of("-c:v", "libx264", "-b:v", text1, "-maxrate", text2, "-bufsize", text1, "-preset", "medium", "-tune", "fastdecode");
   }, false, false),
   ANIMATED_WEBP(
      "libwebp_anim",
      arg0 -> List.of("-c:v", "libwebp_anim", "-b:v", arg0 + "k", "-compression_level", "4", "-loop", "0", "-preset", "default", "-an"),
      false,
      false
   );

   private final String id;
   private final Function<Integer, List<String>> argumentsProvider;
   private final boolean hardware;
   private final boolean macOS;

   public List<String> getArguments(int number1) {
      return this.argumentsProvider.apply(number1);
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public Function<Integer, List<String>> getArgumentsProvider() {
      return this.argumentsProvider;
   }

   @Generated
   public boolean isHardware() {
      return this.hardware;
   }

   @Generated
   public boolean isMacOS() {
      return this.macOS;
   }

   @Generated
   VideoEncoder(String text3, Function<Integer, List<String>> function4, boolean flag5, boolean flag6) {
      this.id = text3;
      this.argumentsProvider = function4;
      this.hardware = flag5;
      this.macOS = flag6;
   }
}
