package game.base;

import game.base.listeners.UseListener;

/**
 *
 * @author roland
 */
public interface Usable
{    
    /**
     * Add a use listener.
     * @param listener 
     */
    public void addUseListener( UseListener listener );
    
    /**
     * Remove a use listener.
     * @param listener 
     */
    public void removeUseListener( UseListener listener );
    
    /**
     * Uses the actor and fires the associated listeners.
     */ 
    public void use();
}
