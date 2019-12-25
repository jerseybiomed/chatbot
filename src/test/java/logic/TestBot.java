package logic;

import bot.Bot;
import bot.Command;
import game.Bandit;
import game.Roulette;
import logic.dialog.BanditLogic;
import logic.dialog.RouletteLogic;
import logic.dialog.StartLogic;
import logic.telegram.Player;

public class TestBot extends Bot {
    private Roulette roulette;
    private RouletteLogic rouletteLogic = new RouletteLogic();
	
	public TestBot(Roulette game2) {
        roulette = game2;
        roulette.setBot(this);
        rouletteLogic.setBot(this);
        roulette.Start();
        commands.add("/start", new Command("/start",
        		(player, args) -> System.out.println(String.join(" ", args))));
        commands.add("/back", new Command("/back",
                (player, args) -> System.out.println(String.join(" ", args))));
        commands.add("sayResult", new Command("sayResult",
                (player, args) -> System.out.println(String.join(" ", args))));
        commands.add("attention", new Command("attention",
                (player, args) -> System.out.println(String.join(" ", args))));
        commands.add("/roulette", new Command( "/roulette",
                (player, args) -> System.out.println(String.join(" ", args))));
        commands.add("/bet", new Command("/bet",
                (player, args) -> rouletteLogic.betRequest(player, args, roulette)));
        commands.add("/bandit", new Command("/bandit",
                (player, args) -> System.out.println(String.join(" ", args))));
        commands.replace("/roll", new Command("/roll",
                (player, args) -> System.out.println(String.join(" ", args))));
        commands.replace("/help", new Command("/help",
                (player, args) -> System.out.println(String.join(" ", args))));
        commands.add("/rules", new Command("/rules",
                (player, args) -> System.out.println(String.join(" ", args))));
        commands.replace("/balance", new Command("/balance",
                (player, args) -> System.out.println(String.join(" ", args))));
    }

    @Override
    public void perform(Player player, String[] args) {
        Bot.assertArgsNotNull(args);
        commands.get(args[0]).setArgs(args).setPlayer(player).run();
    }
    
    public RouletteLogic getRouletteLogic() {
    	return rouletteLogic;
    }

}
