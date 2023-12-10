package jdtalley.spclaims.networking;

import jdtalley.spclaims.SinglePlayerClaims;
import jdtalley.spclaims.networking.packet.ClaimSyncDataS2CPacket;
import jdtalley.spclaims.networking.packet.ClaimingC2SPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier CLAIMING_ID = new Identifier(SinglePlayerClaims.MOD_ID, "claiming");
    public static final Identifier CLAIM_SYNC_ID = new Identifier(SinglePlayerClaims.MOD_ID, "claim_sync");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(CLAIMING_ID, ClaimingC2SPacket::receive);
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(CLAIM_SYNC_ID, ClaimSyncDataS2CPacket::receive);
    }
}
