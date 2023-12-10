package jdtalley.spclaims;

import jdtalley.spclaims.networking.ModMessages;
import net.fabricmc.api.ClientModInitializer;

public class SinglePlayerClaimsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
    }
}
