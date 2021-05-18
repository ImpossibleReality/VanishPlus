package codes.wickedtree.vanishplus.mixin;

import net.minecraft.network.packet.s2c.play.EntitySetHeadYawS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import codes.wickedtree.vanishplus.mixinterface.EntityIDProvider;

@Mixin(EntitySetHeadYawS2CPacket.class)
public class EntitySetHeadYawS2CPacketMixin implements EntityIDProvider {
    @Shadow private int entity;

    @Override
    public int getIdOnServer() {
        return entity;
    }
}
