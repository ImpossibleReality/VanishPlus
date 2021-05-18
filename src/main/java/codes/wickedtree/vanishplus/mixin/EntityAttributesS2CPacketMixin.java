package codes.wickedtree.vanishplus.mixin;

import net.minecraft.network.packet.s2c.play.EntityAttributesS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import codes.wickedtree.vanishplus.mixinterface.EntityIDProvider;

@Mixin(EntityAttributesS2CPacket.class)
public class EntityAttributesS2CPacketMixin implements EntityIDProvider {
    @Shadow
    private int entityId;

    @Override
    public int getIdOnServer() {
        return entityId;
    }
}
