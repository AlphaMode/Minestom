package net.minestom.server.command.builder.arguments;

import net.minestom.server.command.builder.NodeMaker;
import net.minestom.server.network.packet.server.play.DeclareCommandsPacket;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

/**
 * Represents an argument which will take all the remaining of the command.
 * <p>
 * Example: Hey I am a string
 */
public class ArgumentStringArray extends Argument<String[]> {

    public ArgumentStringArray(String id) {
        super(id, true, true);
    }

    @NotNull
    @Override
    public String[] parse(@NotNull String input) {
        return input.split(Pattern.quote(StringUtils.SPACE));
    }

    @Override
    public void processNodes(@NotNull NodeMaker nodeMaker, boolean executable) {
        DeclareCommandsPacket.Node argumentNode = simpleArgumentNode(this, executable, false, false);

        argumentNode.parser = "brigadier:string";
        argumentNode.properties = packetWriter -> {
            packetWriter.writeVarInt(2); // Greedy phrase
        };

        nodeMaker.addNodes(new DeclareCommandsPacket.Node[]{argumentNode});
    }
}
