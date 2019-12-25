package logic.dialog;

import bot.Bot;
import bot.Command;
import bot.ECommands;
import game.Bandit;
import game.Roulette;
import logic.Publisher;
import logic.telegram.Player;

public class Casino extends Bot {
    private Bandit bandit;
    private BanditLogic banditLogic = new BanditLogic();
    private Roulette roulette;
    private RouletteLogic rouletteLogic = new RouletteLogic();
    private Publisher<String> publisher;

    public Casino(Bandit game1, Roulette game2) {
        bandit = game1;
        roulette = game2;
        roulette.setBot(this);
        rouletteLogic.setBot(this);
        roulette.Start();
        commands.add("/start", new Command("/start",
                (player, args) -> publisher.reply(player, StartLogic.startRequest())));
        commands.add("/back", new Command("/back",
                (player, args) -> publisher.reply(player,
                        player.getCurrentGame().equals("roulette") ? rouletteLogic.backRequest(player, roulette)
                                : player.getCurrentGame().equals("bandit") ? banditLogic.backRequest() : "Choose")));
        commands.add("sayResult", new Command("sayResult",
                (player, args) -> publisher.reply(player, rouletteLogic.checkResult(player, args[1], roulette))));
        commands.add("attention", new Command("attention",
                (player, args) -> publisher.reply(player, args[1])));
        commands.add("/roulette", new Command( "/roulette",
                (player, args) -> publisher.reply(player, rouletteLogic.rouletteRequest(player, roulette))));
        commands.add("/bet", new Command("/bet",
                (player, args) -> rouletteLogic.betRequest(player, args, roulette)));
        commands.add("/bandit", new Command("/bandit",
                (player, args) -> publisher.reply(player, banditLogic.banditRequest(player))));
        commands.replace("/roll", new Command("/roll",
                (player, args) -> publisher.reply(player, banditLogic.rollRequest(player, args[1], bandit))));
        commands.replace("/help", new Command("/help",
                (player, args) -> publisher.reply(player,
                        player.getCurrentGame().equals("bandit") ? banditLogic.helpRequest()
                                : player.getCurrentGame().equals("roulette") ? rouletteLogic.helpRequest() : "")));
        commands.add("/rules", new Command("/rules",
                (player, args) -> publisher.reply(player,
                        player.getCurrentGame().equals("bandit") ? banditLogic.rulesRequest()
                                : player.getCurrentGame().equals("roulette") ? rouletteLogic.rulesRequest() : "")));
        commands.replace("/balance", new Command("/balance",
                (player, args) -> publisher.reply(player,
                        player.getCurrentGame().equals("bandit") ? player.getBanditBalance().toString()
                                : player.getCurrentGame().equals("roulette") ? player.getRouletteBalance().toString()
                                : "")));
    }

    @Override
    public void perform(Player player, String[] args) {
        Bot.assertArgsNotNull(args);
        commands.get(args[0]).setArgs(args).setPlayer(player).run();
    }

    @Override
    public void listen(Publisher<String> from, Player player, String args) {
        publisher = from;
        this.perform(player, args.split(" "));
    }

}
