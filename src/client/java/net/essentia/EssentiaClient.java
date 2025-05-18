import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import net.minecraft.client.gui.DrawContext;

public class EssentiaClient implements ClientModInitializer {
    public static KeyBinding toggleEssentiaOverlayKey;
    private boolean isOverlayVisible = false;

    @Override
    public void onInitializeClient() {
        toggleEssentiaOverlayKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.essentia.overlay",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R, // Changed default key
                "category.essentia"
        ));

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            if (isOverlayVisible) {
                renderEssentiaOverlay(drawContext);
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toggleEssentiaOverlayKey.wasPressed()) {
                isOverlayVisible = !isOverlayVisible;
            }
        });
    }

    private void renderEssentiaOverlay(DrawContext drawContext) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null && client.textRenderer != null) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();
            int x = 10; // Adjust X position
            int y = 30; // Adjust Y position
            int color = 0xFFFFFFFF; // White text

            drawContext.drawText(client.textRenderer, Text.literal("Essentia Overlay"), x, y, color, true);

            // You can add more UI elements here, like friend list snippets, etc.
        }
    }
}