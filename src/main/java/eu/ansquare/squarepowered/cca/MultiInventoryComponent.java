package eu.ansquare.squarepowered.cca;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import eu.ansquare.squarepowered.Squarepowered;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class MultiInventoryComponent implements AutoSyncedComponent {

	private int current = -1;

	private NbtList list = new NbtList();
	private NbtList main = new NbtList();

	private boolean preventItemTransfer = false;
	private boolean noDroppingBlocks = false;

	@Nullable
	public static void load(PlayerEntity player){
		if(SquareComponents.MULTI_INVENTORY.isProvidedBy(player)){
			MultiInventoryComponent component = SquareComponents.MULTI_INVENTORY.get(player);
			if(component.current >= component.list.size()) return;
			NbtList list1 = component.current < 0 ? component.main : component.list.getList(component.current);
			player.getInventory().readNbt(list1);
		}
		SquareComponents.MULTI_INVENTORY.sync(player);
	}
	public static void save(PlayerEntity player){
		if(SquareComponents.MULTI_INVENTORY.isProvidedBy(player)){
			MultiInventoryComponent component = SquareComponents.MULTI_INVENTORY.get(player);
			NbtList list = player.getInventory().writeNbt(new NbtList());
			if(component.current < 0){
				component.main = list;
			}else if(component.list.size() > component.current) {
				component.list.set(component.current, list);
			}
			else{
				component.list.add(component.current, list);
			}
		}
		SquareComponents.MULTI_INVENTORY.sync(player);
	}
	public static void set(PlayerEntity player, int i){
		if(SquareComponents.MULTI_INVENTORY.isProvidedBy(player)){
			MultiInventoryComponent component = SquareComponents.MULTI_INVENTORY.get(player);
			save(player);
			component.current = i;
			load(player);
		}
	}
	@Override
	public void readFromNbt(NbtCompound tag) {
		list = tag.getList("list", NbtElement.LIST_TYPE);
		main = tag.getList("main", NbtElement.COMPOUND_TYPE);
		preventItemTransfer = tag.getBoolean("prevent_item_transfer");
		noDroppingBlocks = tag.getBoolean("no_drop");
		current = tag.getInt("current");
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.put("list", list);
		tag.put("main", main);
		tag.putBoolean("prevent_item_transfer", preventItemTransfer);
		tag.putBoolean("noDroppingBlocks", noDroppingBlocks);
		tag.putInt("current", current);
	}
}
