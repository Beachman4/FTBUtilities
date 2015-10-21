package latmod.ftbu.api.client;

import ftb.lib.api.EventLM;
import latmod.ftbu.notification.ClickAction;
import latmod.ftbu.world.LMPlayerClient;

public class EventClickAction extends EventLM
{
	public final ClickAction click;
	public final LMPlayerClient playerSP;
	
	public EventClickAction(ClickAction c, LMPlayerClient p)
	{ click = c; playerSP = p; }
}