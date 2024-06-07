package net.jukoz.me.item.dataComponents;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.jukoz.me.item.ModDataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

public record HoodDataComponent(boolean enabled, String target) {

    private static final Codec<HoodDataComponent> BASE_CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(Codec.BOOL.fieldOf("enabled").forGetter(HoodDataComponent::enabled), Codec.STRING.fieldOf("target").forGetter(HoodDataComponent::target)).apply(instance, HoodDataComponent::new);
    });
    public static final Codec<HoodDataComponent> CODEC = Codec.withAlternative(BASE_CODEC, Codec.BOOL, (enabled) -> {
        return new HoodDataComponent(enabled, "base_hood");
    });
    public static final PacketCodec<ByteBuf, HoodDataComponent> PACKET_CODEC = PacketCodec.tuple(PacketCodecs.BOOL, HoodDataComponent::enabled, PacketCodecs.STRING, HoodDataComponent::target, HoodDataComponent::new);
    ;

    public HoodDataComponent(boolean enabled, String target) {
        this.enabled = enabled;
        this.target = target;
    }

    public static ItemStack setHood(ItemStack stack, boolean enabled, String target) {
        ItemStack itemStack = stack.copyWithCount(1);

        itemStack.set(ModDataComponentTypes.HOOD_DATA, new HoodDataComponent(enabled, target));
        return stack;
    }

    @Override
    public boolean enabled() {
        return enabled;
    }

    @Override
    public String target() {
        return target;
    }
}