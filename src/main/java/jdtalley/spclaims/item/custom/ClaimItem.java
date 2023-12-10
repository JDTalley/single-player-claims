package jdtalley.spclaims.item.custom;

import jdtalley.spclaims.networking.ModMessages;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.ChunkPos;

import java.util.Objects;

public class ClaimItem extends Item {
    public ClaimItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {


        if(context.getWorld().isClient()) {
            ChunkPos chunkPos = context.getWorld().getChunk(context.getBlockPos()).getPos();
            PacketByteBuf buffer = PacketByteBufs.create();
            buffer.writeLong(chunkPos.toLong());
            ClientPlayNetworking.send(ModMessages.CLAIMING_ID, buffer);
            /*PlayerEntity player = context.getPlayer();

            boolean claimResult = ClaimData.addClaim((IEntityDataSaver) player, chunkPos);
            
            if (!claimResult) {
                player.sendMessage(Text.literal("Chunk " + chunkPos.toString() + " Already Owned!"), false);
            } else {
                player.sendMessage(Text.literal("Chunk " + chunkPos.toString() + " Now Owned!"), false);

                context.getStack().damage(2,context.getPlayer(),
                        playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
            }

            player.sendMessage(Text.literal(ClaimData.getClaims((IEntityDataSaver) player)), false);*/
        }

        return ActionResult.SUCCESS;
    }

    /*private boolean isOwnedChunk(ChunkPos positionClicked) {
        return Objects.equals(positionClicked, new ChunkPos(0, 0));
    }*/
}
