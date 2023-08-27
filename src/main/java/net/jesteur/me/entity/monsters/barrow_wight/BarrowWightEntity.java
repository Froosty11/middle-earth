package net.jesteur.me.entity.monsters.barrow_wight;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;


public class BarrowWightEntity extends HostileEntity {
    public BarrowWightEntity(EntityType<? extends HostileEntity> entityType, World world){
        super(entityType, world);
    }
}
