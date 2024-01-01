package eu.ansquare.squarepowered.util;

import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.calio.data.SerializableDataType;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;

import java.util.List;

public class SquareDataTypes {
	public static final SerializableDataType<List<BlockState>> BLOCK_STATES = SerializableDataType.list(SerializableDataTypes.BLOCK_STATE);


}
