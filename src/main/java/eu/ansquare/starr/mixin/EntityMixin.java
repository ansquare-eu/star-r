package eu.ansquare.starr.mixin;

import eu.ansquare.starr.util.datasaving.IDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements IDataSaver {
	private NbtCompound entityNBTData;
	@Override
	public NbtCompound getEntityData(){
		if(entityNBTData == null){
			entityNBTData = new NbtCompound();
		}
		return entityNBTData;
	}
	@Inject(method = "writeNbt", at = @At("HEAD"))
	protected void onWriteNbt(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir){
		if(entityNBTData != null){
			nbt.put("starr.superdata", entityNBTData);
		}
	}
	@Inject(method = "readNbt", at = @At("HEAD"))
	protected void onReadNbt(NbtCompound nbt, CallbackInfo ci){
		if(nbt.contains("starr.superdata", 10)){
			entityNBTData = nbt.getCompound("starr.superdata");
		}
	}
}
