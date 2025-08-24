package com.ethan.xpmending;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import net.minecraft.server.network.ServerPlayerEntity;

public class XPMendingMod implements ModInitializer {

    public static final String MOD_ID = "xpmending";
    public static final Identifier REPAIR_PACKET_ID = new Identifier(MOD_ID, "repair_request");

    @Override
    public void onInitialize() {
        // Register the packet handler for the server
        ServerPlayNetworking.registerGlobalReceiver(REPAIR_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            server.execute(() -> {
                RepairHandler.repairWithXP(player);
            });
        });
    }
}
