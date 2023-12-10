package jdtalley.spclaims.networking.packet;

import jdtalley.spclaims.util.ClaimData;
import jdtalley.spclaims.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.ChunkPos;

public class ClaimingC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        // Server only code
        ChunkPos chunkPos = new ChunkPos(buf.readLong());

        if (ClaimData.isChunkOwned((IEntityDataSaver) player, chunkPos.toLong())) {
            player.sendMessage(Text.literal("Chunk " + chunkPos + " Already Claimed!")
                    .fillStyle(Style.EMPTY.withColor(Formatting.GOLD)), false);
        } else {
            ClaimData.addClaim((IEntityDataSaver) player, chunkPos.toLong());

            player.sendMessage(Text.literal("Chunk " + chunkPos + " Now Claimed!")
                    .fillStyle(Style.EMPTY.withColor(Formatting.GOLD)), false);

            player.getMainHandStack().damage(1, player,
                    serverPlayerEntity -> serverPlayerEntity.sendToolBreakStatus((serverPlayerEntity.getActiveHand())));
        }
    }
}
