package codes.wickedtree.vanishplus.mixin;

import net.minecraft.network.packet.s2c.play.ItemPickupAnimationS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import codes.wickedtree.vanishplus.mixinterface.EntityIDProvider;
import codes.wickedtree.vanishplus.mixinterface.IItemPickupAnimationS2CPacket;

@Mixin(ItemPickupAnimationS2CPacket.class)
public class ItemPickupAnimationS2CPacketMixin implements IItemPickupAnimationS2CPacket {
    @Shadow
    private int collectorEntityId;

    @Shadow private int entityId;

    @Override
    public int getIdOnServer() {
        return collectorEntityId;
    }

    @Override
    public int getItemIdOnServer() {
        return entityId;
    }
}
