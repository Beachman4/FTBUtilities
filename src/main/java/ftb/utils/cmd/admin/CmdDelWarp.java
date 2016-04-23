package ftb.utils.cmd.admin;

import ftb.lib.api.cmd.*;
import ftb.utils.FTBU;
import ftb.utils.world.FTBUWorldDataMP;
import net.minecraft.command.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class CmdDelWarp extends CommandLM
{
	public CmdDelWarp()
	{ super("delwarp", CommandLevel.OP); }
	
	@Override
	public String getCommandUsage(ICommandSender ics)
	{ return '/' + commandName + " <ID>"; }
	
	@Override
	public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender ics, String[] args, BlockPos pos)
	{
		if(args.length == 1) return getListOfStringsMatchingLastWord(args, FTBUWorldDataMP.get().warps.list());
		
		return super.getTabCompletionOptions(server, ics, args, pos);
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender ics, String[] args) throws CommandException
	{
		checkArgs(args, 1);
		if(FTBUWorldDataMP.get().warps.set(args[0], null))
		{
			ics.addChatMessage(FTBU.mod.chatComponent("cmd.warp_del", args[0]));
			return;
		}
		throw new CommandException("ftbu.cmd.warp_not_set", args[0]);
	}
}