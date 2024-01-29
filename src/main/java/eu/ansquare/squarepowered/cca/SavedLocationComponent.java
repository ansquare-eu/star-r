package eu.ansquare.squarepowered.cca;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SavedLocationComponent implements AutoSyncedComponent {
	private NbtList list = new NbtList();
	private BlockPos selectionOne;
	private BlockPos selectionTwo;
	public SavedLocationComponent(LivingEntity entity){
		selectionOne = entity.getBlockPos();
		selectionTwo = entity.getBlockPos();
	}
	public Pair<BlockPos, RegistryKey<World>> get(int i){
		NbtElement e = list.get(i);
		if(i >= list.size()) return null;
		if(e instanceof NbtCompound compound){
			int x = compound.getInt("x");
			int y = compound.getInt("y");
			int z = compound.getInt("z");
			BlockPos pos = new BlockPos(x, y ,z);
			RegistryKey<World> worldRegistryKey = RegistryKey.of(RegistryKeys.WORLD, new Identifier(compound.getString("world")));

			return new Pair<>(pos, worldRegistryKey);
		}
		return null;
	}
	public void wipe(){
		list.clear();
	}
	public void put(int i, int x, int y, int z, ServerWorld world){
		NbtCompound compound = new NbtCompound();
		compound.putInt("x", x);
		compound.putInt("y", y);
		compound.putInt("z", z);
		compound.putString("world", world.getRegistryKey().getValue().toString());
		if(i >= list.size())list.add(i, compound);
		if(i < list.size())list.set(i , compound);
	}
	public int amount(){
		return list.size();
	}
	@Override
	public void readFromNbt(NbtCompound tag) {
		list = tag.getList("list", 10);
		selectionOne = NbtHelper.toBlockPos(tag.getCompound("selOne"));
		selectionTwo = NbtHelper.toBlockPos(tag.getCompound("selTwo"));
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.put("list", list);
		tag.put("selOne", NbtHelper.fromBlockPos(selectionOne));
		tag.put("selTwo", NbtHelper.fromBlockPos(selectionTwo));

	}

	public static BlockPos getSelOne(PlayerEntity player){
		if(SquareComponents.SAVED_LOCATION_COMPONENT.isProvidedBy(player)){
			return SquareComponents.SAVED_LOCATION_COMPONENT.get(player).selectionOne;
		}
		return player.getBlockPos();
	}
	public static BlockPos getSelTwo(PlayerEntity player){
		if(SquareComponents.SAVED_LOCATION_COMPONENT.isProvidedBy(player)){
			return SquareComponents.SAVED_LOCATION_COMPONENT.get(player).selectionTwo;
		}
		return player.getBlockPos();
	}
	public static void setSelOne(PlayerEntity player, BlockPos pos){
		SquareComponents.SAVED_LOCATION_COMPONENT.maybeGet(player).ifPresent(savedLocationComponent -> savedLocationComponent.selectionOne = pos);
	}
	public static void setSelTwo(PlayerEntity player, BlockPos pos){
		SquareComponents.SAVED_LOCATION_COMPONENT.maybeGet(player).ifPresent(savedLocationComponent -> savedLocationComponent.selectionTwo = pos);
	}
}
