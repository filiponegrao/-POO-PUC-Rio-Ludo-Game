/**
 * 
 */
package model;

import java.util.Observable;

/**
 * @author fernandacarvalho
 *
 *
 */
public class ObservableTeam extends Observable
{
	private Team currentTeam;
	
	public void setValue(Team team)
	{
		this.currentTeam = team;
		setChanged();
		notifyObservers(this.currentTeam);
	}
}
