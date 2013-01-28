package server;

import game.base.UsableActor;
import game.players.TangiblePlayer;

/**
 *
 * @author bootsman
 */
public interface ServerListener
{
    public void onConnect( TangiblePlayer player );
    public void onDisconnect( TangiblePlayer player );
    public void onSync( TangiblePlayer player );
    public void onSelect( TangiblePlayer player, UsableActor actor );
    public void onUse( TangiblePlayer player, UsableActor actor );
}