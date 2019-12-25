package logic.telegram;

public class Player {
    private String playerNickname = "Player";
    private int rouletteBalance = 0;
    private int banditBalance = 0;
    private String currentGame = "start";
    private String nextGame = "start";

    public void setBanditBalance(int banditBalance) {
        this.banditBalance = banditBalance;
    }

    public Integer getBanditBalance() {
        return banditBalance;
    }

    public void setRouletteBalance(int rouletteBalance) {
        this.rouletteBalance = rouletteBalance;
    }

    public Integer getRouletteBalance() {return rouletteBalance;}

    public void changeCurrentGame() {currentGame = nextGame;}

    public String getCurrentGame() {return currentGame;}

    public void setPlayerNickname(int nick) {playerNickname += nick;}

    public String getPlayerNickname() {return playerNickname;}

    public void setNextGame(String menu) {nextGame = menu;}

    public String getNextGame() {return nextGame;}

}
