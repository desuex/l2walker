package fw.game.model;

import java.util.ArrayList;
import java.util.Iterator;

import fw.connection.game.clientpackets.L2GameClientPacket;
import fw.connection.game.clientpackets.RequestMagicSkillUse;
import fw.extensions.util.GCArray;
import fw.game.GameEngine;

public final class L2Player extends L2Playable {

	private String _accountName;
	private int _karma, _pkKills, _pvpKills;
	private int _face, _hairStyle, _hairColor;
	private int _recomHave, _recomLeft, _fame,
	_ClassId,clanId;
	
	private boolean _hero = false;
	private int _lvl = 1;
	
	private int _curWeightPenalty = 0;
	
	private int sp,curLoad,maxLoad;
	private long exp;
	
	private GameEngine _GameEngine = null;
	
	private L2PlayerInventory _inventory = new L2PlayerInventory();
	private GCArray<L2Skill> _skills = null;
	
	// AI
	private Runnable _curAction = null;
	private ArrayList<L2PlayerEventListener> _evt_listeners = new ArrayList<L2PlayerEventListener>();
	
	
	public L2Player(int objectId) {
		super(objectId);
	}	

	public GameEngine getGameEngine() {
		return _GameEngine;
	}

	public void setGameEngine(GameEngine _GameEngine) {
		this._GameEngine = _GameEngine;
	}
	
	@Override
	public synchronized void sendPacket(L2GameClientPacket... mov){
		if(getGameEngine() == null) 
			return;
		for (int i = 0; i < mov.length; i++) 		
			getGameEngine().getGameConnection().sendPacket(mov[i]);
	}
	
	public synchronized void useSkill(int id){
		if(_skills == null)
			return;
		for (Iterator<L2Skill> el = _skills.iterator(); el.hasNext();) {
			L2Skill _el = el.next();
			if(_el.getSkill_id() == id){
				sendPacket(new RequestMagicSkillUse(id));
				return;
			}			
		}
	}
	
	public synchronized void onEvent(L2PlayerEvent evt){
		for (L2PlayerEventListener _l: _evt_listeners)
			_l.onPlayerEvent(evt);		
	}
	public synchronized void onEvent(L2PlayerEvent evt,int objId){
		for (L2PlayerEventListener _l: _evt_listeners)
			_l.onPlayerEvent(evt,objId);		
	}
	public synchronized void onEvent(L2PlayerEvent evt,L2Object objId){
		for (L2PlayerEventListener _l: _evt_listeners)
			_l.onPlayerEvent(evt,objId);		
	}
	public synchronized void addListener(L2PlayerEventListener l){
		if(!_evt_listeners.contains(l))
			_evt_listeners.add(l);
	}
	public synchronized void removeListener(L2PlayerEventListener l){
		if(_evt_listeners.contains(l))
			_evt_listeners.remove(l);
	}
	public synchronized void clearListeners(){		
			_evt_listeners.clear();
	}

	@Override
	public byte getLevel() {
		return (byte)_lvl;
	}
	public final void setLevel(final int lvl)
	{
		_lvl = lvl;
	}
	
	public int getFace()
	{
		return _face;
	}

	public void setFace(int face)
	{
		_face = face;
	}

	public int getHairColor()
	{
		return _hairColor;
	}

	public void setHairColor(int hairColor)
	{
		_hairColor = hairColor;
	}

	public int getHairStyle()
	{
		return _hairStyle;
	}

	public void setHairStyle(int hairStyle)
	{
		_hairStyle = hairStyle;
	}
	public int getPkKills()
	{
		return _pkKills;
	}

	public void setPkKills(final int pkKills)
	{
		_pkKills = pkKills;
	}
	public int getRecomHave()
	{
		return _recomHave;
	}

	public void setRecomHave(int value)
	{
		if(value > 255)
			_recomHave = 255;
		else if(value < 0)
			_recomHave = 0;
		else
			_recomHave = value;
	}

	public int getRecomLeft()
	{
		return _recomLeft;
	}

	public void setRecomLeft(final int value)
	{
		_recomLeft = value;
	}
	@Override
	public int getKarma()
	{
		return _karma;
	}
	public void setKarma(int karma)
	{
		if(karma < 0)
			karma = 0;

		if(_karma == karma)
			return;

		_karma = karma;
	}
	
	public int getWeightPenalty()
	{
		return _curWeightPenalty;
	}
	public int getPvpKills()
	{
		return _pvpKills;
	}

	public void setPvpKills(int pvpKills)
	{
		_pvpKills = pvpKills;
	}
	public int getClassId()
	{
		return _ClassId;
	}
	public int getClanId() {
		return clanId;
	}

	public void setClanId(int clanId) {
		this.clanId = clanId;
	}

	public int getSp() {
		return sp;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	public int getCurLoad() {
		return curLoad;
	}

	public void setCurLoad(int curLoad) {
		this.curLoad = curLoad;
	}

	public int getMaxLoad() {
		return maxLoad;
	}

	public void setMaxLoad(int maxLoad) {
		this.maxLoad = maxLoad;
	}

	public L2PlayerInventory getInventory() {
		if(_inventory == null)
			_inventory = new L2PlayerInventory();
		return _inventory;
	}

	public GCArray<L2Skill> getSkills() {
		return _skills;
	}

	public void setSkills(GCArray<L2Skill> _skills) {
		this._skills = _skills;
	}

	public Runnable getCurAction() {
		return _curAction;
	}

	public void setCurAction(Runnable _curAction) {
		this._curAction = _curAction;
	}
	public boolean isCurAction(){
		return (_curAction != null);
	}

}
