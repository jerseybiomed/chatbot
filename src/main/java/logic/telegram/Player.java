package logic.telegram;

public class Player {
    private String playerNickname = "Player";
    private int rouletteBalance = 0;
    private int banditBalance = 0;
    private String currentGame = "start";

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

    public void setCurrentGame(String menu) {currentGame = menu;}

    public String getCurrentGame() {return currentGame;}

    public void setPlayerNickname(int nick) {playerNickname += nick;}

    public String getPlayerNickname() {return playerNickname;}

}
