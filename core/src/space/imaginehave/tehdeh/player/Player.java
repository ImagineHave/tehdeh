package space.imaginehave.tehdeh.player;

import space.imaginehave.tehdeh.agent.AgentMob;

public class Player {
	private int score;
	private int money;
	private int health;
	
	public void agentProfitted(AgentMob agent) {
		score += agent.getScore();
		money += agent.getMoney();
	}
	
	public void pay(int value) {
		money -= value;
	}
	
	public void hurt(AgentMob agent) {
		health-= agent.getDamage();
	}
}
