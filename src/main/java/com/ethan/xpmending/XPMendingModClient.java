package com.ethan.xpmending;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class XPMendingModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyBindings.register();
    }
}
