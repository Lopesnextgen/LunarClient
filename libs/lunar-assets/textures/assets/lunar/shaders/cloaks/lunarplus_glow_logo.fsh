#version 150

in vec2 uv;

layout(std140) uniform LunarFragmentUniforms {
    float TimeSeconds;
};

out vec4 fragColor;
// ============== Do not touch anything above! ==============
// LUNAR-SHADER-DEFINITION-MARKER

void main() {
    vec2 pixel = OutSize * uv;

    float logoSize = 200.0;
    vec2 logoPos = vec2(80.0, 150.0);

    vec2 uvLogo = (pixel - logoPos) / logoSize;

    vec2 diff = pixel - (logoPos + vec2(logoSize / 2.0) + vec2(0, 20));
    float r = diff.x * diff.x + diff.y * diff.y * 0.5;

    float velocitySum = abs(VelocitySmooth.x) + abs(VelocitySmooth.y) + abs(VelocitySmooth.z);
    float veloMul = clamp(velocitySum / 8.0, 0.0, 1.0);
    vec3 glowColor = LunarPlusColor;

    float grey = 0.21 * glowColor.r + 0.71 * glowColor.g + 0.07 * glowColor.b;
    vec3 bgColor = glowColor * (0.1 + veloMul * (0.1 + (1.0 - grey) * 0.2)) * (1.0 - clamp(r / 30000.0, 0.0, 1.0));

    vec4 lunarPixel;
    if (uvLogo.x >= 0.0 && uvLogo.x <= 1.0 && uvLogo.y >= 0.0 && uvLogo.y <= 1.0) {
        lunarPixel = texture2D(LunarLogo, uvLogo);
        lunarPixel.xyz *= mix(lunarPixel.xyz, glowColor, veloMul);
    }

    lunarPixel.xyz = mix(bgColor, lunarPixel.xyz, lunarPixel.a);
    fragColor = vec4(lunarPixel.xyz, 1.0);
}
