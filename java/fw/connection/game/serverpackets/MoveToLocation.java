package fw.connection.game.serverpackets;

import fw.extensions.util.Location;
import fw.game.model.L2Object;

public class MoveToLocation extends L2GameServerPacket {

	private int objectId;
	private Location to,from;
	
	@Override
	public void read() {
		objectId = readD();
		to=new Location(readD(), readD(), readD());
		from=new Location(readD(), readD(), readD());
	}

	@Override
	public void excecute() {
		L2Object obj = getClient().getGameEngine().getWorld().getObject(objectId);
		if(obj == null) return;
		obj.setLoc(from);
		obj.setToLoc(to);
	}

}
