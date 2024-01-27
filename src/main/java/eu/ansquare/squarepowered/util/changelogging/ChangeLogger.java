package eu.ansquare.squarepowered.util.changelogging;

import net.minecraft.block.BlockState;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.*;

public class ChangeLogger {
	protected static Map<UUID, ChangeLogger> INSTANCES = new HashMap<>();

	public static ChangeLogger getOrCreate(ServerPlayerEntity player) {
		if (INSTANCES.containsKey(player.getUuid())) return INSTANCES.get(player.getUuid());
		ChangeLogger logger = new ChangeLogger();
		INSTANCES.put(player.getUuid(), logger);
		return logger;
	}

	public static boolean changeAndLog(ServerWorld world, BlockPos pos, BlockState nextState, ServerPlayerEntity player) {
		ChangeLogger logger = getOrCreate(player);
		boolean bool = logger.add(new Change(pos, world, world.getBlockState(pos), nextState));
		world.setBlockState(pos, nextState);
		return bool;
	}

	private boolean open = false;
	public Set<Change> progress;
	public int index;
	public LinkedList<Set<Change>> log = new LinkedList<>();

	public void open() {
		if (!open) {
			progress = new HashSet<>();

			open = true;
		}
	}

	public boolean add(Change change) {
		return open ? progress.add(change) : false;
	}

	public void close() {
		if (open) {
			log.addLast(progress);
			index = log.size();
			open = false;
		}
	}

	public void undo() {
		if (!open) {
			index--;
			if (index >= 0) {
				Set<Change> set = log.get(index);
				set.forEach(change -> {
					change.world().setBlockState(change.pos(), change.prevState());
				});
			}
		}
	}

	public void redo() {
		if (!open) {
			if (index < log.size()) {
				Set<Change> set = log.get(index);
				set.forEach(change -> {
					change.world().setBlockState(change.pos(), change.nextState());
				});
				index++;
			}
		}
	}
}
