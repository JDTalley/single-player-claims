package jdtalley.spclaims.item.custom;

import jdtalley.spclaims.networking.ModMessages;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.ChunkPos;

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
        }

        return ActionResult.SUCCESS;
    }
}
